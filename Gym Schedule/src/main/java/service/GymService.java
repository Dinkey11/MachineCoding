package service;

import models.Gym;
import models.GymClass;
import repository.GymRepository;

import java.util.HashMap;

public class GymService {

    private GymRepository gymRepository;

    private static GymService gymService;

    public static GymService getInstance(){
        if(gymService == null)
            gymService = new GymService();

        return gymService;
    }

    public GymService() {
        this.gymRepository = GymRepository.getInstance();
    }

    public Gym addGym(String name, Integer location, Integer maxCapacity){
        Gym gym = new Gym(name, location, maxCapacity);
        gym = gymRepository.addGym(gym);
        return gym;
    }

    public boolean removeClass(Integer gymId, Integer classId){
        Gym gym = gymService.getGym(gymId);
        if(gym == null){
            System.out.println("GYM with id = "+ gymId + " doesnt exist");
            return false;
        }

        GymClass gymClass = gym.getClasses().getOrDefault(classId, null);
        if(gymClass == null){
            System.out.println("GYM class with id = "+ classId + " doesnt exist");
            return false;
        }

        HashMap<Integer, GymClass> existingClassesMap = gym.getClasses();
        existingClassesMap.remove(classId);
        gym.setClasses(existingClassesMap);
        return true;
    }

    public void removeGym(Integer id){
        gymRepository.removeGym(id);
    }

    public Gym getGym(Integer id){
        return gymRepository.getGym(id);
    }
}
