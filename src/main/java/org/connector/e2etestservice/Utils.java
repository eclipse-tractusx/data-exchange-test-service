package org.connector.e2etestservice;

public class Utils {
    public static String removeLastSlashFromURL(String url) {
        if(url.substring(url.length() - 1).equalsIgnoreCase("/")) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }
}
