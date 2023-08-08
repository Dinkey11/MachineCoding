package repository;

import models.Restraunt;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RestrauntRepository {

    private static RestrauntRepository restrauntRepository=null;

    public static RestrauntRepository getInstance(){
        if(restrauntRepository == null){
            restrauntRepository = new RestrauntRepository();
        }
        return restrauntRepository;
    }

    private HashMap<Integer, Restraunt> restrauntHashMap;

    public RestrauntRepository() {
        this.restrauntHashMap = new HashMap<>();
    }

    public void addRestrauntBrnahc(Restraunt restraunt){
        restrauntHashMap.put(restraunt.getId(), restraunt);
        return;
    }

    public Restraunt getRestrauntBranch(Integer id){
        return restrauntHashMap.getOrDefault(id, null);
    }

    public List<Restraunt> getAllBranches(Integer parentId){
        List<Restraunt> allRes = restrauntHashMap.values().stream().toList();
        List<Restraunt> branches = allRes.stream().filter(res -> res.getParentId().equals(parentId)).collect(Collectors.toList());
        return branches;
    }

    public List<Restraunt> getAllRestraunts(){
        List<Restraunt> allRes = restrauntHashMap.values().stream().toList();
        return allRes;
    }
}
