package com.senders.comic.util;

import com.senders.comic.domain.util.Utils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestUtils {
    @Test
    public void  testParseUrl(){
        String jsonUrlStructure = "https://xkcd.com/{postNumberNumber}/info.0.json";
        String key = "{postNumberNumber}";
        int position = 3;
        String finalUrl = "https://xkcd.com/3/info.0.json";
        Assertions.assertThat(Utils.parseUrl(position,jsonUrlStructure,key)).isEqualTo(finalUrl);
    }
}
