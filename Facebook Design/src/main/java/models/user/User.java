package models.user;


import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Address address;
    private Gender gender;
    private String phoneNumber;
    private String emailId;

    public User(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public User() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

//    public void addFriendRequest(Integer id){
//        friendRequestStatus.put(id, Status.PENDING);
//    }
//
//    public void updateFriendRequestStatus(Integer id, Status status){
//        friendRequestStatus.put(id, status);
//    }
//
//    public Status getFriendRequestStatus(Integer id){
//        return friendRequestStatus.getOrDefault(id, Status.UNKNOWN);
//    }
//
//    public HashMap getAllFriendRequests(){
//        return friendRequestStatus;
//    }
}
