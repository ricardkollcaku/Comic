package com.senders.comic.domain.service;

import com.senders.comic.data.model.Comic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
@Service
public class XKCDComicService implements IComicService{
    @Override
    public Flux<Comic> getTopComics(int elementPerRequest) {
        return Flux.just(new Comic());    }
}
