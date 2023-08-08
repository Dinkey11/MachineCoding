package repository;

import models.ParentRestraunt;

import java.util.HashMap;

public class RestrauntChainRepository {

    private static RestrauntChainRepository repository=null;

    public static RestrauntChainRepository getInstance(){
        if(repository == null){
            repository = new RestrauntChainRepository();
        }
        return repository;
    }

    private HashMap<Integer, ParentRestraunt> parentRestrauntHashMap;

    public RestrauntChainRepository() {
        this.parentRestrauntHashMap = new HashMap<>();
    }

    public void addParentChain(ParentRestraunt restraunt){
        parentRestrauntHashMap.put(restraunt.getId(), restraunt);
        return;
    }

    public ParentRestraunt getParentChain(Integer id){
        return parentRestrauntHashMap.getOrDefault(id, null);
    }
}
