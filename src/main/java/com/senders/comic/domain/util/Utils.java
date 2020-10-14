package com.senders.comic.domain.util;
public class Utils {
    public static String parseUrl(Integer integer, String jsonUrlStructure,String comicPositionKey) {
        return jsonUrlStructure.replace(comicPositionKey, String.valueOf(integer));
    }

}
