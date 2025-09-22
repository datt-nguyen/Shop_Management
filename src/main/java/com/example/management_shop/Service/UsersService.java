package com.example.management_shop.Service;
import com.example.management_shop.Repositories.UserRepository;
import com.example.management_shop.Entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    public List<Users> getAllusers(){
        return userRepository.findAll();
    }

    public Optional<Users> getUserbyId(Long id){
        return userRepository.findById(id);
    }

    public Users saveUser(Users users){
        return userRepository.save(users);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void changeStatus(Long id, boolean status) {
        userRepository.findById(id).ifPresent(u -> {
            u.setStatus(status);
            userRepository.save(u);
        });
    }
}
