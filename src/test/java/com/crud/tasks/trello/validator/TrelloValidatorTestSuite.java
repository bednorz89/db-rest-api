package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void shouldFetchFilteredList() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "test", trelloLists));
        trelloBoard.add(new TrelloBoard("2", "My board", trelloLists));
        //When
        List<TrelloBoard> returnedBoard = trelloValidator.validateTrelloBoards(trelloBoard);
        //Then
        assertNotNull(returnedBoard);
        assertEquals(1, returnedBoard.size());
        assertEquals("My board", returnedBoard.get(0).getName());
    }

    @Test
    public void shouldFetchEmptyListWhenEmptyListGiven() {
        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        //When
        List<TrelloBoard> returnedBoard = trelloValidator.validateTrelloBoards(trelloBoard);
        //Then
        assertNotNull(returnedBoard);
        assertEquals(0, returnedBoard.size());
    }
}
