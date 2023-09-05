package service;

import models.ClassType;
import models.Gym;
import models.GymClass;
import repository.GymClassRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GymClassService {

    private GymClassRepository classRepository;
    private GymService gymService;

    private static GymClassService gymClassService;

    public static GymClassService getInstance(){
        if(gymClassService == null)
            gymClassService = new GymClassService();
        return gymClassService;
    }
    public GymClassService() {
        this.gymService = GymService.getInstance();
        this.classRepository = GymClassRepository.getInstance();
    }

    public GymClass addNewClass(Integer gymId, Integer maxLimit, Integer start, Integer end, ClassType classType){
        GymClass gymClass = new GymClass(gymId, maxLimit, start, end, classType);
        Gym gym = gymService.getGym(gymId);
        if(gym == null){
            System.out.println("GYM with id = "+ gymId + " doesnt exist");
            return null;
        }

        int classCapacity = maxLimit;
        int gymCapacity = gym.getMaxCapacity();


        HashMap<Integer, GymClass> existingClassesMap = gym.getClasses();
        List<GymClass> existingClasses = existingClassesMap.values().stream().toList();


        List<GymClass> gymClassesWithSameTime = existingClasses.stream()
                                                    .filter(c ->  c.getStartHour().equals(start) || c.getEndHour().equals(end))
                                                    .collect(Collectors.toList());

        Integer occupiedCapacity = 0;
        for(GymClass cl : gymClassesWithSameTime){
            occupiedCapacity += cl.getMaxLimit();
        }

        if(occupiedCapacity + classCapacity > gymCapacity){
            System.out.println("Class cannot be added as it exceeds gym capcity");
            return null;
        }

        System.out.println("Class added as  to gym = " + gymId);

        existingClassesMap.put(gymClass.getId(), gymClass);
        gym.setClasses(existingClassesMap);

        classRepository.addClass(gymClass);
        return gymClass;

    }



    public void updateClass(GymClass gymClass){
        classRepository.update(gymClass);
        return;
    }
}
