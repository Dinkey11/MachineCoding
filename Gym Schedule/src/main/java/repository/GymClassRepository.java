package repository;

import models.GymClass;

import java.util.HashMap;

public class GymClassRepository {

    private static GymClassRepository gymClassRepository = null;

    public static GymClassRepository getInstance(){
        if(gymClassRepository == null)
            gymClassRepository = new GymClassRepository();

        return gymClassRepository;
    }

    private HashMap<Integer, GymClass> classMap;

    public GymClassRepository() {
        this.classMap = new HashMap<>();
    }

    //Add Class
    public GymClass addClass(GymClass classObj){
        classMap.put(classObj.getId(), classObj);
        return classObj;
    }

    public GymClass getClass(Integer id){
        return classMap.getOrDefault(id, null);
    }

    public void update(GymClass gymClass){
        classMap.put(gymClass.getId(), gymClass);
        return;
    }
}
