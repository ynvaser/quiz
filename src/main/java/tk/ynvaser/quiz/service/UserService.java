package tk.ynvaser.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.ynvaser.quiz.dto.RegistrationDTO;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    public void registerUser(RegistrationDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getName());
        userEntity.setRole(Role.USER);
        userEntity.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);
    }
}
