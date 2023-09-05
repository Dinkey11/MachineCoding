package repository;

import models.Gym;
import models.GymClass;

import java.util.HashMap;

public class GymRepository {

    private static GymRepository gymRepository = null;

    public static GymRepository getInstance(){
        if(gymRepository == null)
            gymRepository = new GymRepository();

        return gymRepository;
    }

    private HashMap<Integer, Gym> gymMap;

    public GymRepository() {
        this.gymMap = new HashMap<>();
    }

    public Gym addGym(Gym gym){
        gymMap.put(gym.getId(), gym);
        return gym;
    }

    public Gym getGym(Integer id){
        return gymMap.getOrDefault(id, null);
    }

    public void removeGym(Integer id){
        gymMap.remove(id);
        return;
    }
}
