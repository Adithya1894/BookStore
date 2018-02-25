package com.adithya.bookstore.util;

public class TextUtil {

    //this method replaces the double or triple spaces in the  string.
    public String sanitize(String textToSanitize){
        return textToSanitize.replaceAll("\\s+", " ");
    }


}
