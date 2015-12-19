package com.cgr;

import java.util.HashMap;
import java.util.Map;

public class Main {

   private static Map argsMap = new HashMap();

   public static void main(String[] args) {
      Params pArgs = Params.fromCommandLineArgs(args);
      System.out.println("Hello world program arguments: " + pArgs.size());
      System.out.println(pArgs.toString());
   }
}
