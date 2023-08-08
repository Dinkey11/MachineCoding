package service;


import models.Restraunt;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class RatingService {

    private static RatingService ratingService = null;

    public static RatingService getInstance(){
        if(ratingService == null)
            ratingService = new RatingService();

        return ratingService;
    }

    private UserService userService;
    private RestrauntService restrauntService;

    public RatingService() {
        this.userService = UserService.getInstance();
        this.restrauntService = RestrauntService.getInstance();
    }

    public void giveRating(Integer userId, Integer branchId, Integer rating){
        User user = userService.getUser(userId);
        if(user == null ) return;

        Restraunt restraunt = restrauntService.getRetraunat(branchId);
        if(restraunt == null) return;

        if(user.getPincode().equals(restraunt.getPincode())) {
            HashMap<Integer, Integer> userRatings = restraunt.getUserRatingMap();
            HashMap<Integer, Integer> givenRatings = user.getGivenRatingsMap();

            userRatings.put(userId, rating);
            givenRatings.put(branchId, rating);

            user.setGivenRatingsMap(givenRatings);
            restraunt.setUserRatingMap(userRatings);
        }
        return;
    }

    public Double getAverageRating(Integer foodId){
        List<Restraunt> allRestraunts = restrauntService.getAllRestraunts();
        List<Restraunt> withFoodItem = allRestraunts.stream().filter(res -> res.getFoodId().equals(foodId)).collect(Collectors.toList());
        List<Double> avgRatingsAcrossRes = withFoodItem.stream().map(res -> getAvgRating(res)).collect(Collectors.toList());
        OptionalDouble finalAvergaeRatingAcrossRes = avgRatingsAcrossRes.stream().mapToDouble(a-> a).average();
        return finalAvergaeRatingAcrossRes.isPresent() ? finalAvergaeRatingAcrossRes.getAsDouble() : Double.parseDouble("0");
    }

    public Double getAverageRatingInARestraunt(Integer branchId){
        Restraunt restraunt = restrauntService.getRetraunat(branchId);
        return getAvgRating(restraunt);
    }

    private Double getAvgRating(Restraunt restraunt){
        List<Integer> allratings = restraunt.getUserRatingMap().values().stream().toList();
        OptionalDouble avgRating = allratings.stream().mapToDouble(a-> a).average();
        if(avgRating.isPresent()) return avgRating.getAsDouble();
        return Double.parseDouble("0");
    }

    public List<Integer> getFoodRatings(Integer id){
        Restraunt restraunt = restrauntService.getRetraunat(id);
        return restraunt.getUserRatingMap().values().stream().toList();
    }

    public List<Integer> getRatingsForArea(Integer pincode){
        List<Restraunt> allRestraunts = restrauntService.getAllRestraunts();
        List<Restraunt> inArea = allRestraunts.stream().filter(res -> res.getPincode().equals(pincode)).collect(Collectors.toList());
        List<Integer> ratings = new ArrayList<>();
        inArea.stream().forEach(res -> ratings.addAll(res.getUserRatingMap().values().stream().toList()));
        return ratings;
    }
}
