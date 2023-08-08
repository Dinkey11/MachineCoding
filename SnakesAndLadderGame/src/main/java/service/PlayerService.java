package service;

import models.Player;

import java.util.HashMap;

public class PlayerService {

    private HashMap<Integer, Player> playerMap = new HashMap<>();

    public Player addPlayer(Integer id){
        Player p = new Player(id, 0);
        playerMap.put(id, p);
        return p;
    }

    public Player getPlayer(Integer id){
        return playerMap.getOrDefault(id, null);
    }


}
