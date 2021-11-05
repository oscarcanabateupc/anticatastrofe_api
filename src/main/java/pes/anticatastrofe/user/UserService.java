package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
