package com.senders.comic.domain.service;

import com.senders.comic.data.model.Comic;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class TestComicService {
    @Autowired
    private ComicService comicService;
    @MockBean
    FeedBurnerService feedBurnerService;
    @MockBean
    XKCDComicService xkcdComicService;

    @TestConfiguration
    static class TestComicServiceConfig {
        @Bean
        public ComicService comicService() {
            return new ComicService(1);
        }
    }


    @Test
    public void testGetTopComics(){
        Mockito.when(feedBurnerService.getTopComics(1))
                .thenReturn(Flux.just(getTestComic(1)));
        Mockito.when(xkcdComicService.getTopComics(1))
                .thenReturn(Flux.just(getTestComic(2)));

        StepVerifier.create(comicService.getLastComics())
                .assertNext(e -> {
                    Assertions.assertThat(e.getTitle().equals(getTestComic(1).getTitle()));
                })
                .assertNext(e -> {
                    Assertions.assertThat(e.getTitle().equals(getTestComic(2).getTitle()));
                }).verifyComplete();
    }

    @Test
    public void testSortTopComics(){
        Comic comic1 = getTestComic(1);
        Comic comic2 = getTestComic(2);
        comic1.setDate(LocalDate.of(2020,1,5));
        comic2.setDate(LocalDate.of(2020,1,7));

        Mockito.when(feedBurnerService.getTopComics(1))
                .thenReturn(Flux.just(comic1));
        Mockito.when(xkcdComicService.getTopComics(1))
                .thenReturn(Flux.just(comic2));

        StepVerifier.create(comicService.getLastComics())
                .assertNext(e -> {
                    Assertions.assertThat(e.getTitle().equals(comic2.getTitle()));
                })
                .assertNext(e -> {
                    Assertions.assertThat(e.getTitle().equals(comic1.getTitle()));
                }).verifyComplete();
    }




    private Comic getTestComic(int a){
        Comic comic = new Comic();
        comic.setDate(LocalDate.now());
        comic.setTitle("test"+a);
        comic.setDescription("sdfsfd");
        return  comic;
    }
}
