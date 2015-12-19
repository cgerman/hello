/**
 * Created by carlos on 19/12/2015.
 */
package com.cgr;

import java.util.HashMap;
import java.util.Iterator;

public class ProgramArguments extends HashMap {

   public ProgramArguments(String[] args) {
      parseArgs(args);
   }

   private void parseArgs(String[] args) {
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
            paramName = String.valueOf(i);
            paramValue = arg;
         }
         put(paramName, paramValue);
      }
   }
}
