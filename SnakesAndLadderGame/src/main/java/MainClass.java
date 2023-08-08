import models.Board;
import models.Player;
import service.BoardService;
import service.GameService;
import service.NormalDice;
import service.PlayerService;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class MainClass {

    public static void main(String[] args){
        BoardService boardService = BoardService.getInstance();
        PlayerService playerService = new PlayerService();

        Board board = boardService.createBoard();
        boardService.addSnakes(1,  14, 7);
        boardService.addSnakes(1,  27, 13);
        boardService.addSnakes(1,  29, 6);
        boardService.addLadder(1,  5, 18);
        boardService.addLadder(1,  15, 27);

        Player p1 = playerService.addPlayer(1);
        Player p2 = playerService.addPlayer(2);
        Player p3 = playerService.addPlayer(3);
        Player p4 = playerService.addPlayer(4);
        Player p5 = playerService.addPlayer(5);

        Deque<Player> players = new ArrayDeque<>();
        players.addLast(p1);
        players.addLast(p2);
        players.addLast(p3);
        players.addLast(p4);
        players.addLast(p5);

        GameService game = new GameService(board, new NormalDice(), players);

        HashMap<Integer, Player> winners = game.playGame();

        String ans= players.toString();
        System.out.println(ans);

    }
}
