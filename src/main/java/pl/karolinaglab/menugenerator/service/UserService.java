package pl.karolinaglab.menugenerator.service;

import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.User;
import pl.karolinaglab.menugenerator.payload.UserResponse;
import pl.karolinaglab.menugenerator.repository.UserRepository;
import pl.karolinaglab.menugenerator.security.UserPrincipal;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String heightString = body.get("height");
        String bodyWeightString = body.get("bodyWeight");
        String ageString = body.get("age");
        String activityString = body.get("activity");
        String sexString = body.get("sex");

        double height = Double.parseDouble(heightString);
        double bodyWeight = Double.parseDouble(bodyWeightString);
        int age = Integer.parseInt(ageString);
        Activity activity = Activity.valueOf(activityString);
        Sex sex = Sex.valueOf(sexString);

        User newUser = new User(username, password, email, bodyWeight, height, age, activity, sex);

        return userRepository.save(newUser);
    }

    public UserResponse getUser(UserPrincipal currentUser) throws ResourceNotFoundException {
        int userId = currentUser.getId();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserResponse(user.get());
        } else throw new ResourceNotFoundException("User not found on " + userId);
    }

    public User updateUser(UserPrincipal currentUser, Map<String,String> body) throws ResourceNotFoundException {
        String heightString = body.get("height");
        String bodyWeightString = body.get("bodyWeight");
        String ageString = body.get("age");
        String activityString = body.get("activity");
        String sexString = body.get("sex");

        double height = Double.parseDouble(heightString);
        double bodyWeight = Double.parseDouble(bodyWeightString);
        int age = Integer.parseInt(ageString);
        Activity activity = Activity.valueOf(activityString);
        Sex sex = Sex.valueOf(sexString);
        Optional<User> user = userRepository.findById(currentUser.getId());
        if(user.isPresent()) {
            User userToUpdate = user.get();
            userToUpdate.setHeight(height);
            userToUpdate.setBodyWeight(bodyWeight);
            userToUpdate.setAge(age);
            userToUpdate.setActivity(activity);
            userToUpdate.setSex(sex);
            return userRepository.save(userToUpdate);
        } else throw new ResourceNotFoundException("User not found on" + currentUser.getId());
    }
}
