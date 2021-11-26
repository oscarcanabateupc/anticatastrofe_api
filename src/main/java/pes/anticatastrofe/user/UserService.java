package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository studentRepository) {
        this.userRepository = studentRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    public Optional<User> findByID(String email) {
        return userRepository.findById(email);
    }

    public User updateUserPosition(String email, float last_coordinate_x, float last_coordinate_y) {
        User u = userRepository.findById(email).get();
        u.setLast_coordinate_x(last_coordinate_x);
        u.setLast_coordinate_y(last_coordinate_y);
        u = userRepository.save(u);
        return u;
    }
}
