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
   }

   public void testCommandLineParams () {
      String[] args = "a b -c d -e f -g -h".split(" ");
      Params p = Params.fromCommandLineArgs(args);
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

      assertEquals(p.size(), 6);
      assertEquals(p.get("a"), "");
      assertEquals(p.get("b"), "");
      assertEquals(p.get("-c"), "d");
      assertEquals(p.get("-e"), "f");
      assertEquals(p.get("-g"), "");
      assertEquals(p.get("-h"), "");
   } 
}