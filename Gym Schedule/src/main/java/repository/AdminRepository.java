package repository;

import models.Admin;

import java.util.HashMap;

public class AdminRepository {

    private static AdminRepository adminRepository = null;

    public static AdminRepository getInstance() {
        if (adminRepository == null)
            adminRepository = new AdminRepository();

        return adminRepository;
    }

    private HashMap<Integer, Admin> adminMap;

    public AdminRepository() {
        this.adminMap = new HashMap<>();
    }

    public Admin addAdmin(Admin admin){
        adminMap.put(admin.getId(), admin);
        return admin;
    }

    public Admin getAdmin(Integer id){
        return adminMap.getOrDefault(id, null);
    }
}
