package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.UserEntity;
import de.htwberlin.webtech.todolist.persistence.UserRepository;
import de.htwberlin.webtech.todolist.web.api.User;
import de.htwberlin.webtech.todolist.web.api.UserCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        List<UserEntity> user = userRepository.findAll();
        return user.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public User create(UserCreateRequest req){
        var userEntity = new UserEntity(req.getVorname(), req.getNachname(), req.getEmail(), req.getPasswort());
        userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public User findById(Long id){
        var userEntity = userRepository.findById(id);
        return userEntity.map(this::transformEntity).orElse(null);
    }

    public User update(Long id, UserCreateRequest req){
        var userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()) return null;
        var userEntity = userEntityOptional.get();
        userEntity.setVorname(req.getVorname());
        userEntity.setNachname(req.getNachname());
        userEntity.setEmail(req.getEmail());
        userEntity.setPasswort(req.getPasswort());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public boolean deleteById(Long id){
        if(!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    private User transformEntity(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getVorname(),
                userEntity.getNachname(),
                userEntity.getEmail(),
                userEntity.getPasswort()
        );
    }
}
