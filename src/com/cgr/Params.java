/**
 * Created by carlos on 19/12/2015.
 */
package com.cgr;

import java.util.Iterator;
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

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("{");
      Iterator iter = keySet().iterator();
      while (iter.hasNext()) {
         String paramName = (String) iter.next();
         String paramValue = (String) get(paramName);
         sb.append(paramName);
         if (paramValue != null) {
            sb.append("=").append(paramValue);
         }
         if (iter.hasNext()) {
            sb.append(", ");
         }
      }
      sb.append("}");
      return sb.toString();
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
