package com.cgr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

   private static Map argsMap = new HashMap();

   public static void main(String[] args) {
      ProgramArguments pArgs = new ProgramArguments(args);
      System.out.println("Hello world program arguments: " + pArgs.size());
      Iterator iter = pArgs.keySet().iterator();
      while (iter.hasNext()) {
         String argName = (String) iter.next();
         String argValue = (String) pArgs.get(argName);
         System.out.println("   name=" + argName + ", value=" + argValue);
      }
   }
}
