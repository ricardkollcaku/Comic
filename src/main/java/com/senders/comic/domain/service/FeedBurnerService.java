package com.senders.comic.domain.service;

import com.senders.comic.data.model.Comic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeedBurnerService implements IComicService {
    @Override
    public Flux<Comic> getTopComics(int elementPerRequest) {
        return Flux.just(new Comic());
    }
}
