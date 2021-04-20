package tk.ynvaser.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.ynvaser.quiz.model.users.Role;
import tk.ynvaser.quiz.model.users.User;
import tk.ynvaser.quiz.persistence.entity.UserEntity;
import tk.ynvaser.quiz.persistence.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(User::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public void setRoleForUser(User user, Role role) {
        user.setRole(role);
        UserEntity userEntity = userRepository.getOne(user.getId());
        userEntity.setRole(role);
        userRepository.save(userEntity);
    }
}
