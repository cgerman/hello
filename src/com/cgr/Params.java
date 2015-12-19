/**
 * Created by carlos on 19/12/2015.
 */
package com.cgr;

import java.net.MalformedURLException;
import java.net.URL;
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

   public static Params fromUrl(String url) {
      Params p = new Params();
      p.parseUrl(url);
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
         if (!paramValue.isEmpty()) {
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
      if (args == null) return;
      for (int i = 0; i < args.length; i++) {
         String arg = args[i];
         String nextArg = (i + 1 < args.length ? args[i + 1] : null);

         String paramName;
         String paramValue;
         if (arg.startsWith("-")) {
            paramName = arg;
            if (nextArg == null || nextArg.startsWith("-")) {
               paramValue = "";
            } else {
               paramValue = nextArg;
               // In this loop step we are "consuming" two args of 
               // the initial args array (args[i] and args[i+1]).
               // So we must increment i a second time.
               i++;
            }
         } else {
            paramName = arg;
            paramValue = "";
         }
         put(paramName, paramValue);
      }
   }

   protected void parseUrl(String url) {
      if (url == null || url.isEmpty()) return;

      try {
         URL u = new URL(url);
         String qs = u.getQuery();
         if (qs == null) return;
         
         String[] params = qs.split("&");
         for (int i = 0; i < params.length; i++) {
            String param = params[i];
            String[] kvPair = param.split("=");
            if (kvPair.length != 2) {
               throw new IllegalArgumentException("Malformed url or query string: " + url);
            }
            String paramName = kvPair[0];
            String paramValue = kvPair[1];
            put(paramName, paramValue);
         }
      } catch (MalformedURLException e) {
         throw new IllegalArgumentException("Malformed url or query string: " + url);
      }
/*
      int startPos = url.lastIndexOf("?");
      if (startPos == -1) startPos = 0;
      else startPos++;
      String qs = url.substring(startPos);
*/

   }
}
