package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.UserDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.exceptions.UsernameNotFoundException;
import com.example.garageshopfinalassignment.models.Role;
import com.example.garageshopfinalassignment.models.User;
import com.example.garageshopfinalassignment.repositories.RoleRepository;
import com.example.garageshopfinalassignment.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }

    // methods
    public UserDto createUser(UserDto dto) {
        User user = toUser(dto);
        userRepos.save(user);

        return toUserDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepos.findAll();
        return userListToUserDtoList(userList);
    }

    public UserDto getUserByUsername(String username) {
        if(userRepos.findById(username).isPresent()) {
            User user = userRepos.findById(username).get();

            return toUserDto(user);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserDto updateUser(String username, UserDto dto) {
        if(userRepos.findById(username).isPresent()) {
            User user = userRepos.findById(username).get();
            User user1 = toUser(dto);
            user1.setUsername(user.getUsername());

            userRepos.save(user1);

            return toUserDto(user);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public String deleteUser(String username) {
        if(userRepos.findById(username).isPresent()) {
            userRepos.deleteById(username);

            return "User deleted";
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private List<UserDto> userListToUserDtoList(List<User> users) {
        List<UserDto> dtoList = new ArrayList<>();

        for(User user : users) {
            UserDto dto = toUserDto(user);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private User toUser(UserDto dto) {
        var user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : dto.roles) {
            if(roleRepos.findById(rolename).isPresent()) {
                Optional<Role> or = roleRepos.findById(rolename);
                userRoles.add(or.get());
            } else {
                throw new RecordNotFoundException("Couldn't find role: " + rolename);
            }
        }
        user.setRoles(userRoles);

        return user;
    }

    private UserDto toUserDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        List<String> userRoles = new ArrayList<>();
        for(Role role : user.getRoles()) {
            userRoles.add(role.getRolename());
        }
        dto.setRoles(userRoles);

        return dto;
    }
}
