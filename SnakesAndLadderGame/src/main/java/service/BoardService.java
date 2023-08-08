package service;

import models.Board;
import repository.BoardRepository;

import java.util.HashMap;

public class BoardService {

    public static BoardService boardService =  null;

    public static BoardService getInstance(){
        if(boardService == null){
            boardService = new BoardService();
            return boardService;
        }
        return boardService;
    }

    private BoardRepository repository;

    public BoardService() {
        this.repository = BoardRepository.getInstance();
    }

    //CreateNewBoard
    public Board createBoard(){
        return repository.addNewBoard();
    }

    //AddSnakes
    public Board addSnakes(Integer id, Integer start, Integer end){
        Board board = repository.getBoard(id);
        if(board == null) return null;
        HashMap<Integer, Integer> snakes = board.getSnakes();
        snakes.put(start, end);
        board.setSnakes(snakes);
        return board;
    }

    //AddLadders
    public Board addLadder(Integer id, Integer start, Integer end){
        Board board = repository.getBoard(id);
        if(board == null) return null;
        HashMap<Integer, Integer> ladders= board.getLadders();
        ladders.put(start, end);
        board.setLadders(ladders);
        return board;
    }
}
