package com.senders.comic.domain.service;

import com.senders.comic.data.model.Comic;
import reactor.core.publisher.Flux;

public interface IComicService {
    Flux<Comic> getTopComics(int elementPerRequest);
}
