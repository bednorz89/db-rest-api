package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "description", "top", "card-id-1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotEquals(trelloCard, trelloCardDto);
        assertTrue(trelloCardDto.getName().equals(trelloCard.getName()) &&
                trelloCardDto.getDescription().equals(trelloCard.getDescription()) &&
                trelloCardDto.getPos().equals(trelloCard.getPos()) &&
                trelloCardDto.getListId().equals(trelloCard.getListId()));
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card 1", "description", "top", "card-id-1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotEquals(trelloCard, trelloCardDto);
        assertTrue(trelloCardDto.getName().equals(trelloCard.getName()) &&
                trelloCardDto.getDescription().equals(trelloCard.getDescription()) &&
                trelloCardDto.getPos().equals(trelloCard.getPos()) &&
                trelloCardDto.getListId().equals(trelloCard.getListId()));
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("id1", "list1", false));
        trelloList.add(new TrelloList("id2", "list2", true));
        trelloList.add(new TrelloList("id3", "list3", false));
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(trelloList.size(), trelloListDto.size());
        assertEquals("list1", trelloListDto.get(0).getName());
        assertTrue(trelloListDto.get(1).isClosed());
        assertEquals("id3", trelloListDto.get(2).getId());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("id1", "list1", false));
        trelloListDto.add(new TrelloListDto("id2", "list2", true));
        trelloListDto.add(new TrelloListDto("id3", "list3", false));
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(trelloList.size(), trelloListDto.size());
        assertEquals("list1", trelloList.get(0).getName());
        assertTrue(trelloList.get(1).isClosed());
        assertEquals("id3", trelloList.get(2).getId());
    }

    @Test
    public void testMapToBoardDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("id1", "list1", false));
        trelloList.add(new TrelloList("id2", "list2", true));
        trelloList.add(new TrelloList("id3", "list3", false));

        List<TrelloList> trelloList2 = new ArrayList<>();
        trelloList2.add(new TrelloList("id4", "list4", false));
        trelloList2.add(new TrelloList("id5", "list5", true));

        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("boardId1", "test board", trelloList));
        boards.add(new TrelloBoard("boardId2", "test board 2", trelloList2));
        //When
        List<TrelloBoardDto> boardsDto = trelloMapper.mapToBoardDto(boards);
        //Then
        assertEquals(boards.size(), boardsDto.size());
        assertEquals(2, boardsDto.get(1).getLists().size());
        assertEquals("boardId2", boardsDto.get(1).getId());
        assertEquals("test board", boardsDto.get(0).getName());
    }

    @Test
    public void testMapToBoard() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("id1", "list1", false));
        trelloListDto.add(new TrelloListDto("id2", "list2", true));
        trelloListDto.add(new TrelloListDto("id3", "list3", false));

        List<TrelloListDto> trelloListDto2 = new ArrayList<>();
        trelloListDto2.add(new TrelloListDto("id4", "list4", false));
        trelloListDto2.add(new TrelloListDto("id5", "list5", true));

        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        boardsDto.add(new TrelloBoardDto("boardIdDto1", "test board", trelloListDto));
        boardsDto.add(new TrelloBoardDto("boardIdDto2", "test board 2", trelloListDto2));
        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoard(boardsDto);
        //Then
        assertEquals(boards.size(), boardsDto.size());
        assertEquals(boards.get(0).getLists().size(), 3);
        assertEquals("boardIdDto2", boardsDto.get(1).getId());
        assertEquals("test board", boardsDto.get(0).getName());
    }
}
