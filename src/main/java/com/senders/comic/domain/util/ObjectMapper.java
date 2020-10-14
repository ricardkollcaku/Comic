package com.senders.comic.domain.util;

import com.rometools.rome.feed.synd.SyndEntry;
import com.senders.comic.data.model.Comic;
import com.senders.comic.data.dto.XKCDComicDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ObjectMapper {
    public static Comic map(XKCDComicDTO xkcdComicDTO, String url) {
        Comic comic = new Comic();
        comic.setTitle(xkcdComicDTO.getTitle());
        comic.setDescription(xkcdComicDTO.getAlt());
        comic.setLink(url);
        comic.setImg(xkcdComicDTO.getImg());
        comic.setDate(LocalDate.of(Integer.parseInt(xkcdComicDTO.getYear()), Integer.parseInt(xkcdComicDTO.getMonth()), Integer.parseInt(xkcdComicDTO.getDay())));
        return comic;
    }

    public static Comic map (SyndEntry syndEntry){
        Comic comic = new Comic();
        comic.setTitle(syndEntry.getTitle());
        comic.setDescription(syndEntry.getDescription().getValue());
        comic.setLink(syndEntry.getUri());
        comic.setImg(Utils.getImage(syndEntry.getContents()));
        comic.setDate(map(syndEntry.getPublishedDate()));
        return comic;
    }


    private static LocalDate map(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date map(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay( ZoneId.systemDefault()).toInstant());
    }
}
