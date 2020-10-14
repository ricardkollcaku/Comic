package com.senders.comic.domain.util;

import com.rometools.rome.feed.synd.SyndContent;

import java.util.List;

public class Utils {
    public static String parseUrl(Integer integer, String jsonUrlStructure, String comicPositionKey) {
        return jsonUrlStructure.replace(comicPositionKey, String.valueOf(integer));
    }

    public static String getImage(List<SyndContent> contents) {
        if (contents == null || contents.size() == 0)
            return "";
        return Utils.getImage(contents.get(0).getValue());

    }

    public static String getImage(String html) {
        try {
            String key = "srcset=\"";
            String value = html;
            int startPosition = value.indexOf(key) + 8;
            int endPosition = value.indexOf(".png", startPosition) + 4;
            return value.substring(startPosition, endPosition);
        } catch (Exception e) {
            return "";
        }
    }


}
