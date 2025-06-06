package com.josue.banksystem.infraestructure.adapters.in.rest;

import com.josue.banksystem.application.in.role.FindByNameRole;
import com.josue.banksystem.application.in.user.CreateUser;
import com.josue.banksystem.application.in.user.FindUserById;
import com.josue.banksystem.application.in.user.GetUsers;
import com.josue.banksystem.application.in.user.UpdateUser;
import com.josue.banksystem.domain.model.Role;
import com.josue.banksystem.domain.model.User;
import com.josue.banksystem.infraestructure.adapters.in.rest.mapper.UserRestMapper;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.user.CreateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.request.user.UpdateUserRequest;
import com.josue.banksystem.infraestructure.adapters.in.rest.response.user.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRestAdapter {

    private GetUsers getUsers;
    private FindUserById findUserById;
    private CreateUser createUser;
    private UpdateUser updateUser;
    private UserRestMapper userMapper;

    private FindByNameRole findByNameRole;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<UserResponse> getAll() {
        return getUsers.getAll()
                .stream()
                .map(user -> userMapper.toUserRespose(user))
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userMapper.toUserRespose(findUserById.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public UserResponse save(@Valid @RequestBody CreateUserRequest request) {
        User user = userMapper.toUser(request);
        return userMapper.toUserRespose(createUser.create(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {

        User user = userMapper.toUser(request);
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        if (request.isAdmin()) {
            // search role admin
            Role admin = findByNameRole.findByName("ROLE_ADMIN");
            if (!user.getRoles().contains(admin)) {
                user.getRoles().add(admin);
            }
        }

        if (request.isEmployee()) {
            // serach employee role
            Role employee = findByNameRole.findByName("ROLE_EMPLOYEE");
            if (!user.getRoles().contains(employee)) {
                user.getRoles().add(employee);
            }
        }

        if (request.isUser()) {
            // search user role
            Role userRole = findByNameRole.findByName("ROLE_USER");
            if (!user.getRoles().contains(userRole)) {
                user.getRoles().add(userRole);
            }
        }

        // id client and the data to update.
        return userMapper.toUserRespose(updateUser.update(id, user));
    }
}
