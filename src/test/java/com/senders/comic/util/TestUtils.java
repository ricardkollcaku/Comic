package com.senders.comic.util;

import com.senders.comic.domain.util.Utils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestUtils {
    @Test
    public void testGetImage(){
        String html = "<div class=\"wp-block-image\"><figure class=\"aligncenter size-full is-resized\"><img loading=\"lazy\" src=\"http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png\" alt=\"\" class=\"wp-image-7794\" width=\"768\" height=\"499\" srcset=\"http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png 1080w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-300x195.png 300w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-1024x665.png 1024w, http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me-768x498.png 768w\" sizes=\"(max-width: 768px) 100vw, 768px\" /></figure></div>\n" +
                "<div class=\"feedflare\">\n" +
                "<a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:yIl2AUoC8zA\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?d=yIl2AUoC8zA\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:qj6IDK7rITs\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?d=qj6IDK7rITs\" border=\"0\"></img></a> <a href=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?a=EKcE_QiSL8U:dd7nvpYc42s:gIN9vFwOqvQ\"><img src=\"http://feeds.feedburner.com/~ff/PoorlyDrawnLines?i=EKcE_QiSL8U:dd7nvpYc42s:gIN9vFwOqvQ\" border=\"0\"></img></a>\n" +
                "</div><img src=\"http://feeds.feedburner.com/~r/PoorlyDrawnLines/~4/EKcE_QiSL8U\" height=\"1\" width=\"1\" alt=\"\"/>";
        String finalUrl = "http://www.poorlydrawnlines.com/wp-content/uploads/2020/10/listen-to-me.png";
        Assertions.assertThat(Utils.getImage(html)).isEqualTo(finalUrl);
    }


    @Test
    public void  testParseUrl(){
        String jsonUrlStructure = "https://xkcd.com/{postNumberNumber}/info.0.json";
        String key = "{postNumberNumber}";
        int position = 3;
        String finalUrl = "https://xkcd.com/3/info.0.json";
        Assertions.assertThat(Utils.parseUrl(position,jsonUrlStructure,key)).isEqualTo(finalUrl);
    }
}
