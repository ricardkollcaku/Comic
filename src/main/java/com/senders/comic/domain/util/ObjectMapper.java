package com.senders.comic.domain.util;

import com.senders.comic.data.dto.XKCDComicDTO;
import com.senders.comic.data.model.Comic;

import java.time.LocalDate;

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
}
