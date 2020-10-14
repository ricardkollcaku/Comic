package com.senders.comic.util;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.senders.comic.data.model.Comic;
import com.senders.comic.data.dto.XKCDComicDTO;
import com.senders.comic.domain.util.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TestObjectMapper {

    String title = "title";
    String description = "description";
    String url = "https://www.google.com";
    String img = "http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png";
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

    @Test
    public void  testSynEntryXKCD(){


        SyndContentImpl syndDescription = new SyndContentImpl();
        syndDescription.setValue(description);

        SyndContentImpl syndImg = new SyndContentImpl();
        syndImg.setValue(getImgHtml());
        List<SyndContent> contentsImg = new ArrayList<>();
        contentsImg.add(syndImg);
        SyndEntryImpl syndEntry = new SyndEntryImpl();
        syndEntry.setTitle(title);
        syndEntry.setDescription(syndDescription);
        syndEntry.setUri(url);
        syndEntry.setContents(contentsImg);
        syndEntry.setPublishedDate(ObjectMapper.map(date));



        Comic comic = ObjectMapper.map(syndEntry);
        Assertions.assertThat(comic.getTitle()).isEqualTo(title);
        Assertions.assertThat(comic.getDescription()).isEqualTo(description);
        Assertions.assertThat(comic.getLink()).isEqualTo(url);
        Assertions.assertThat(comic.getImg()).isEqualTo(img);
        Assertions.assertThat(comic.getDate()).isEqualTo(date);
    }

    private String getImgHtml() {
        return   "<div class=\"wp-block-image\"><figure class=\"aligncenter size-full is-resized\"><img loading=\"lazy\" src=\"http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png\" alt=\"\" class=\"wp-image-7794\" width=\"768\" height=\"499\" srcset=\"http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png 1080w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-300x195.png 300w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-1024x665.png 1024w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-768x498.png 768w\" sizes=\"(max-width: 768px) 100vw, 768px\" /></figure></div>\n" +
                "<div class=\"feedflare\">\n" +
                "<a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:yIl2AUoC8zA\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?d=yIl2AUoC8zA\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:qj6IDK7rITs\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?d=qj6IDK7rITs\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:gIN9vFwOqvQ\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?i=EKcE_QiSL8U:dd7nvpYc42s:gIN9vFwOqvQ\" border=\"0\"></img></a>\n" +
                "</div><img src=\"http://feeds.feedburner.com/~r/PoorlyDrawnLines/~4/EKcE_QiSL8U\" height=\"1\" width=\"1\" alt=\"\"/>";

    }
}
