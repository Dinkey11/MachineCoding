package service;

import models.Board;
import models.Player;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class GameService {

    private Board board;
    private Deque<Player> playersQueue;
    private NormalDice dice;
    private HashMap<Integer, Player> res;
    private static AtomicInteger winnerPos = new AtomicInteger(1);

    public GameService(Board board, NormalDice dice, Deque<Player> players) {
        this.board = board;
        this.playersQueue = players;
        this.dice = dice;
        this.res = new HashMap<>();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Queue<Player> getPlayersQueue() {
        return playersQueue;
    }

    public void setPlayersQueue(Deque<Player> playersQueue) {
        this.playersQueue = playersQueue;
    }

    public NormalDice getDice() {
        return dice;
    }

    public void setDice(NormalDice dice) {
        this.dice = dice;
    }

    public HashMap<Integer, Player> playGame(){
        while(playersQueue.size() > 1){
            Player player = playersQueue.pollFirst();
            Integer noOfJumps = dice.rollDice();
            updatePlayerPosition(player, noOfJumps);
            if(reachedEnd(player)){
                res.put(winnerPos.getAndIncrement(), player);
            }else {
                playersQueue.addLast(player);
            }
        }
        return res;
    }

    private void updatePlayerPosition(Player player, Integer noOfJumps){
        HashMap<Integer, Integer> snakes = board.getSnakes();
        HashMap<Integer, Integer> ladders = board.getLadders();

        Integer nextPos = player.getPosition() + noOfJumps;

        nextPos = snakes.getOrDefault(nextPos, nextPos);
        nextPos = ladders.getOrDefault(nextPos, nextPos);

        player.setPosition(nextPos);
        return;
    }

    private Boolean reachedEnd(Player player){
        int pos = player.getPosition();
        return pos >= 30;
    }
}
