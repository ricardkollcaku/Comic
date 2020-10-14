package com.senders.comic.domain.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.senders.comic.data.model.Comic;
import com.senders.comic.domain.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedBurnerService implements IComicService {
    @Value("${url.rss.feedburner.feeds}")
    private String rssFeedsUrl;

    @Override
    public Flux<Comic> getTopComics(int elementPerRequest) {
        return Flux.fromIterable(getFeeds(rssFeedsUrl))
                .take(elementPerRequest)
                .map(ObjectMapper::map);
    }

    List<SyndEntry> getFeeds(String rssFeedsUrl) {
        try (XmlReader reader = new XmlReader(new URL(rssFeedsUrl))) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            return feed.getEntries();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
