package models;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {

    private static AtomicInteger ID_GEN = new AtomicInteger(1);

    private Integer id;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;

    public Board() {
        this.id = ID_GEN.getAndIncrement();
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public HashMap<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void setSnakes(HashMap<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public HashMap<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(HashMap<Integer, Integer> ladders) {
        this.ladders = ladders;
    }

    public Integer getId() {
        return id;
    }
}
