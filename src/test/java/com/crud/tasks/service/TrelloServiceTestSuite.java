package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "testing", "top", "listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "tester", "http://test.com");
        //When
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        CreatedTrelloCardDto card = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", card.getId());
        assertEquals("tester", card.getName());
        assertEquals("http://test.com", card.getShortUrl());
        verify(emailService, times(1)).send(any());
    }
}
