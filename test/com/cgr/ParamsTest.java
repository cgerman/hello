/**
 * Created by carlos on 19/12/2015.
 */
package com.cgr;

import junit.framework.TestCase;

public class ParamsTest extends TestCase {

   public ParamsTest(String name) {
      super(name);
   }

   protected void runTest() throws Throwable {
      testCommandLineParams();
      testQueryString();
   }

   public void testCommandLineParams () {
      Params p = Params.fromCommandLineArgs(null);
      assertEquals(p.size(), 0);
      
      String[] args = "a b -c d -e f -g -h".split(" ");
      p=Params.fromCommandLineArgs(args);
      assertTrue(p.contains("a"));
      assertTrue(p.contains("b"));
      assertTrue(p.contains("-c"));
      assertFalse(p.contains("d"));
      assertTrue(p.contains("-e"));
      assertFalse(p.contains("f"));
      assertTrue(p.contains("-g"));
      assertTrue(p.contains("-h"));

      assertFalse(p.contains("-a"));
      assertFalse(p.contains("c"));
      assertFalse(p.contains("x"));

      assertEquals(p.size(), 6);
      assertEquals(p.get("a"), "");
      assertEquals(p.get("b"), "");
      assertEquals(p.get("-c"), "d");
      assertEquals(p.get("-e"), "f");
      assertEquals(p.get("-g"), "");
      assertEquals(p.get("-h"), "");
   } 
   
   public void testQueryString() {
      Params p = Params.fromUrl(null);
      assertEquals(p.size(), 0);

      p = Params.fromUrl("");
      assertEquals(p.size(), 0);

      p = Params.fromUrl("http://domain.com/contextPath/a=b"); // missing "?"
      assertEquals(p.size(), 0);

      try {
         p = Params.fromUrl("abc");
         fail("Malformed urls should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {}

      try {
         p = Params.fromUrl("a=b");
         fail("Malformed urls should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {}

      try {
         p = Params.fromUrl("?a=b");
         fail("Malformed urls should throw IllegalArgumentException");
      } catch (IllegalArgumentException e) {}


      p = Params.fromUrl("http://domain.com/contextPath?a=b&c=d&e=f");
      assertEquals(p.size(), 3);
      assertTrue(p.contains("a"));
      assertTrue(p.contains("c"));
      assertTrue(p.contains("e"));
      assertFalse(p.contains("b"));
      assertFalse(p.contains("d"));
      assertFalse(p.contains("f"));
      assertEquals(p.get("a"), "b");
      assertEquals(p.get("c"), "d");
      assertEquals(p.get("e"), "f");
   }
}