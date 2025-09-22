package com.example.management_shop.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.management_shop.Service.UsersService;
import com.example.management_shop.Entity.Users;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UsersService userService;

    @GetMapping
    public List<Users> getAllUsers() { return userService.getAllusers(); }

    @PostMapping
    public Users createUser(@RequestBody Users user) { return userService.saveUser(user); }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam boolean status) {
        userService.changeStatus(id, status);
        return ResponseEntity.ok("User status updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}

