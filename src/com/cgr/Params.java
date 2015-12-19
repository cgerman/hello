/**
 * Created by carlos on 19/12/2015.
 */
package com.cgr;

import java.util.LinkedHashMap;

public class Params extends LinkedHashMap {

   private Params() {
   }

   public static Params fromCommandLineArgs(String[] args) {
      Params p = new Params();
      p.parseCommandLineArgs(args);
      return p;
   }

   public boolean contains(String paramName) {
      return get(paramName) != null;
   }

   //////////////////////////////////

   protected void parseCommandLineArgs(String[] args) {
      for (int i = 0; i < args.length; i++) {
         String arg = args[i];
         String nextArg = (i + 1 < args.length ? args[i + 1] : null);

         String paramName;
         String paramValue;
         if (arg.startsWith("-")) {
            paramName = arg;
            if (nextArg == null || nextArg.startsWith("-")) {
               paramValue = null;
            } else {
               paramValue = nextArg;
               // In this loop step we are "consuming" two args of 
               // the initial args array (args[i] and args[i+1]).
               // So we must increment i a second time.
               i++;
            }
         } else {
            paramName = arg;
            paramValue = null;
         }
         put(paramName, paramValue);
      }
   }
}
