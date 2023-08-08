package models;


import java.time.LocalDate;
import java.util.*;
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
    private Set<Integer> followers;
    private HashMap<Integer, Post> posts;
    private Post head;
    private Post tail;

    public User() {
        this.id = ID_GENERATOR.getAndIncrement();
        followers = new HashSet<>();
        posts = new HashMap<>();
        this.head = new Post(-10000);
        this.tail = new Post(10000);
        head.setNext(tail);
        tail.setPrev(head);
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

    public Set<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Integer> followers) {
        this.followers = followers;
    }

    public HashMap<Integer, Post> getPosts() {
        return posts;
    }

    public void setPosts(HashMap<Integer, Post> posts) {
        this.posts = posts;
    }

    public Post getHead() {
        return head;
    }

    public void setHead(Post head) {
        this.head = head;
    }

    public Post getTail() {
        return tail;
    }

    public void setTail(Post tail) {
        this.tail = tail;
    }

    public String printFollowers() {
//        String fol = "";
//        for(Integer id : followers) {
//            fol.concat(id.toString());
//            fol.concat(" and ");
//        }
        String ans = "User - "+ id +" {" +
                "followers=" + followers +
                '}';
        System.out.println(ans);
        return ans;
    }

    public String printPosts() {
        Set<Integer> postIds = posts.keySet();
//        String fol = "";
//        for(Integer id : posts.keySet()) {
//            fol.concat(id.toString());
//            fol.concat(" and ");
//        }
        String ans = "User - "+ id +" {" +
                "posts=" + postIds +
                '}';
        System.out.println(ans);
        return ans;
    }
}
