package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {
    public List<TrelloBoard> mapToBoard(final List<TrelloBoardDto> trelloBoardDtos) {
        return trelloBoardDtos.stream()
                .map(tb -> new TrelloBoard(tb.getId(), tb.getName(), mapToList(tb.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(tb -> new TrelloBoardDto(tb.getId(), tb.getName(), mapToListDto(tb.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(tl -> new TrelloList(tl.getId(), tl.getName(), tl.isClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(tl -> new TrelloListDto(tl.getId(), tl.getName(), tl.isClosed()))
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
