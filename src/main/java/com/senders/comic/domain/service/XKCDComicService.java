package com.senders.comic.domain.service;

import com.senders.comic.data.model.Comic;
import com.senders.comic.data.dto.XKCDComicDTO;
import com.senders.comic.domain.util.ObjectMapper;
import com.senders.comic.domain.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class XKCDComicService implements IComicService {
    @Value("${xkcd.json.url.top-comic}")
    private   String topComicUrl;
    @Value("${url.structure.element.key}")
    private   String comicPositionKey;
    @Value("${xkcd.url.structure}")
    private   String urlStructure;
    @Value("${xkcd.json.url.structure}")
    private String jsonUrlStructure;

    @Override
    public Flux<Comic> getTopComics(int elementPerRequest) {
        return getTopComicsLength(topComicUrl)
                .flatMapMany(topComicPosition -> getTopComics(elementPerRequest, topComicPosition, jsonUrlStructure));
    }

    private Flux<Comic> getTopComics(int elementPerRequest, Integer topComicPosition, String jsonUrlStructure) {
        return Flux.range(topComicPosition - elementPerRequest + 1, elementPerRequest)
                .flatMap(integer -> getComic(integer, jsonUrlStructure));
    }

    private Mono<Comic> getComic(Integer integer, String jsonUrlStructure) {
        return WebClient.create(Utils.parseUrl(integer, jsonUrlStructure,comicPositionKey))
                .get()
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(XKCDComicDTO.class))
                .map(xkcdComicDTO -> ObjectMapper.map(xkcdComicDTO, Utils.parseUrl(integer, urlStructure,comicPositionKey)));
    }


    Mono<Integer> getTopComicsLength(String url) {
        return WebClient.create(url)
                .get()
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(XKCDComicDTO.class))
                .map(XKCDComicDTO::getNum);
    }


}
