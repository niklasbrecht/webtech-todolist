package de.htwberlin.webtech.todolist.service;

import de.htwberlin.webtech.todolist.persistence.UserEntity;
import de.htwberlin.webtech.todolist.persistence.UserRepository;
import de.htwberlin.webtech.todolist.web.api.User;
import de.htwberlin.webtech.todolist.web.api.UserCreateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final String userNotFound = "Nutzer mit der Mail %s wurde nicht gefunden!";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public UserEntity findByIdRaw(Long id){
        var user = userRepository.findById(id);
        return user.orElse(null);
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

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(userNotFound, mail)));
    }

    public void signUpUser(UserEntity user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if(userExists) throw new IllegalStateException("Mail wird bereits für ein Konto verwendet!");
        String encodedPassword = bCryptPasswordEncoder.encode((user.getPassword()));
        user.setPasswort(encodedPassword);
        userRepository.save(user);
    }

    public Long findIdByEmail(String email){
        return userRepository.getIdByMail(email);
    }
}
