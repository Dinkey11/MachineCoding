package service;

import models.*;
import repository.AdminRepository;
import repository.CustomerRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;
    private GymService gymService;
    private GymClassService classService;

    private static UserService userService;

    public static UserService getInstance(){
        if(userService == null)
            userService = new UserService();

        return userService;
    }

    public UserService() {
        this.adminRepository = AdminRepository.getInstance();
        this.customerRepository = CustomerRepository.getInstance();
        this.gymService = GymService.getInstance();
        this.classService = GymClassService.getInstance();
    }

    public Customer addCustomer(String name, UserType userType){
        Customer customer = new Customer(name, userType);
        return customerRepository.addCustomer(customer);
    }

//    public Admin addAdmin(String name, Integer gymId){
//        Admin admin = new Admin(name, gymId);
//        Gym gym = gymService.getGym(gymId);
//        if(gym == null){
//            System.out.println("Gym doesnt exists");
//        }
//        gym.setAdminId(admin.getId());
//        return adminRepository.addAdmin(admin);
//    }

    public Boolean bookClass(Integer userId, Integer gymId, Integer classId){
        Gym gym = gymService.getGym(gymId);
        Customer customer = customerRepository.getCustomer(userId);

        HashMap<Integer, GymClass> classMap = gym.getClasses();
        HashMap<Integer, GymClass> booked = customer.getBookedClasses();

        if(booked.getOrDefault(classId, null) == null) {
            GymClass gymClass = classMap.getOrDefault(classId, null);

            if (gymClass == null) {
                System.out.println("Class doesnt exist");
                return false;
            }
            int bookedCapacity = gymClass.getBookedCount();

            if (bookedCapacity < gymClass.getMaxLimit()) {
                gymClass.setBookedCount(++bookedCapacity);
                classMap.put(classId, gymClass);
                booked.put(classId, gymClass);
            }else{
                System.out.println("Class fully booked");
                return false;
            }
        }else{
            System.out.println("Class already booked");
            return false;
        }
        return true;
    }


    public List<GymClass> getAllBookings(Integer userId){
        Customer customer = customerRepository.getCustomer(userId);
        if(customer == null){
            System.out.println("customer doesnt exisrt");
            return Collections.emptyList();
        }
        List<GymClass> classes = customer.getBookedClasses().values().stream().toList();
        return classes;
    }

    public Boolean removeClass(Integer userId, Integer classId){
        Customer customer = customerRepository.getCustomer(userId);
        if(customer == null){
            System.out.println("customer doesnt exisrt");
            return false;
        }
        HashMap<Integer, GymClass> bookedClasses  = customer.getBookedClasses();
        GymClass gymClass = bookedClasses.getOrDefault(classId, null);
        if(gymClass == null){
            System.out.println("Class not booked, cant be removed");
        }
        bookedClasses.remove(classId);
        Integer bookedCount = gymClass.getBookedCount();
        gymClass.setBookedCount(--bookedCount);
        classService.updateClass(gymClass);
        return true;
    }

    public void removeClassFromUsers(Integer classId){
        List<Customer> allCustomers = customerRepository.getAllCustomers();
        allCustomers.stream().forEach(cl -> cl.getBookedClasses().remove(classId));
        return;
    }

    /*
        List<userId> -> from class
        allCustomers.stream().forEach(cl -> cl.getBookedClasses().remove(classId));
     */
}
