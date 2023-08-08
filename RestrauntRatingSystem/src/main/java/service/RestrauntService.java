package service;

import models.ParentRestraunt;
import models.Restraunt;
import repository.RestrauntChainRepository;
import repository.RestrauntRepository;

import java.util.List;

public class RestrauntService {

    private RestrauntRepository repository;
    private RestrauntChainRepository parentChainRepo;

    private static RestrauntService restrauntService = null;

    public static RestrauntService getInstance(){
        if(restrauntService == null){
            restrauntService = new RestrauntService();
        }
        return restrauntService;
    }

    public RestrauntService() {
        this.parentChainRepo = RestrauntChainRepository.getInstance();
        this.repository = RestrauntRepository.getInstance();
    }

    public ParentRestraunt addParentRestraunt(String name, Integer foodId){
        ParentRestraunt parentRestraunt = new ParentRestraunt(name, foodId);
        parentChainRepo.addParentChain(parentRestraunt);
        return parentRestraunt;
    }

    public Restraunt AddResBranch(Integer parentId, Integer pincode){
        Integer foodId = parentChainRepo.getParentChain(parentId).getFoodId();
        Restraunt restraunt = new Restraunt(parentId, pincode, foodId);
        repository.addRestrauntBrnahc(restraunt);
        return restraunt;
    }

    public ParentRestraunt getParentRestraunt(Integer parentId){
        return parentChainRepo.getParentChain(parentId);
    }

    public Restraunt getRetraunat(Integer id){
        return repository.getRestrauntBranch(id);
    }

    public List<Restraunt> getAllBranhces(Integer parentId){
        return repository.getAllBranches(parentId);
    }

    public List<Restraunt> getAllRestraunts(){
        return repository.getAllRestraunts();
    }

}
