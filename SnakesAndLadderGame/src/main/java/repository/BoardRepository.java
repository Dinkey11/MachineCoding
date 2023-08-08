package repository;

import models.Board;

import java.util.HashMap;

public class BoardRepository {

    private HashMap<Integer, Board> boardMap;

    public static BoardRepository boardRepository = null;

    public static BoardRepository getInstance(){
        if(boardRepository == null){
            boardRepository = new BoardRepository();
            return boardRepository;
        }
        return boardRepository;
    }

    public BoardRepository() {
        this.boardMap = new HashMap<>();
    }

    //AddBoard
    public Board addNewBoard(){
        Board board = new Board();
        boardMap.put(board.getId(), board);
        return board;
    }

    //getBoard
    public Board getBoard(Integer id){
        return boardMap.getOrDefault(id, null);
    }


}
