package com.senders.comic.presantation.controller.v1;

import com.senders.comic.data.model.Comic;
import com.senders.comic.domain.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping()
public class ComicController {
    @Autowired
    private ComicService comicService;
    @GetMapping()
    Flux<Comic> getComics(){
        return comicService.getLastComics();
    }
}
