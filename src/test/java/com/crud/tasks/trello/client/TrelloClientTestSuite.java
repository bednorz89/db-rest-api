package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTestSuite {

    @InjectMocks
    private TrelloClient trelloClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("dawid");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        // Given
        TrelloBoardDto[] trelloBoardss = new TrelloBoardDto[1];
        trelloBoardss[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());
        URI uri = new URI("http://test.com/members/dawid/boards?key=test&token=test&fields=name,id&lists=all");
        //When
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoardss);
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test task", "Test description", "top", "test_id");
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20description&pos=top&idList=test_id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Test task", "http://test.com");
        // When
        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCardDto);
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        // Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/dawid/boards?key=test&token=test&fields=name,id&lists=all");
        // When
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        boolean isEmpty = trelloClient.getTrelloBoards().isEmpty();
        // Then
        assertTrue(isEmpty);
    }
}