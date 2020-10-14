package com.senders.comic.util;

import com.senders.comic.data.dto.XKCDComicDTO;
import com.senders.comic.data.model.Comic;
import com.senders.comic.domain.util.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)

public class TestObjectMapper {

    String title = "title";
    String description = "description";
    String url = "https://www.google.com";
    String img = "https://www.google.com/img.png";
    int year = 2020;
    int month = 1;
    int day = 1;
    LocalDate date = LocalDate.of(year,month,day);

    @Test
    public void  testMapXKCD(){

        XKCDComicDTO xkcdComicDTO = new XKCDComicDTO();
        xkcdComicDTO.setTitle(title);
        xkcdComicDTO.setAlt(description);
        xkcdComicDTO.setLink(url);
        xkcdComicDTO.setImg(img);
        xkcdComicDTO.setYear(String.valueOf(year));
        xkcdComicDTO.setMonth(String.valueOf(month));
        xkcdComicDTO.setDay(String.valueOf(day));

        Comic comic = ObjectMapper.map(xkcdComicDTO,url);
        Assertions.assertThat(comic.getTitle()).isEqualTo(title);
        Assertions.assertThat(comic.getDescription()).isEqualTo(description);
        Assertions.assertThat(comic.getLink()).isEqualTo(url);
        Assertions.assertThat(comic.getImg()).isEqualTo(img);
        Assertions.assertThat(comic.getDate()).isEqualTo(date);
    }

}
