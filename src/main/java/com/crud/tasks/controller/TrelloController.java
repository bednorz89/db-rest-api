package com.crud.tasks.controller;

import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
        trelloClient.getTrelloBoards().stream()
                .filter(b -> b.getId() != null && b.getName() != null && b.getName().contains("Kodilla"))
                .forEach(b -> System.out.println(b.getId() + " " + b.getName()));
    }
}