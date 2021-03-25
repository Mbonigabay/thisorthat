package rw.thisorthat.thisorthat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import rw.thisorthat.thisorthat.model.dto.UserDto;
import rw.thisorthat.thisorthat.model.request.UserRequest;
import rw.thisorthat.thisorthat.model.response.ApiResponse;
import rw.thisorthat.thisorthat.model.response.UserResponse;
import rw.thisorthat.thisorthat.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserResponse>>> index(@RequestParam(value="page", defaultValue="1") int page,@RequestParam(value="limit", defaultValue="25") int limit){
        List<UserResponse> responses = new ArrayList<>();
        List<UserDto> userDtos = userService.getUsers(page, limit);
        for(UserDto user: userDtos){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            responses.add(userResponse);
        }

        final String message = "Users retrieved successfully..";
        ApiResponse<List<UserResponse>> body = new ApiResponse<>(HttpStatus.CREATED, message,
                responses);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> show(@PathVariable String id) throws Exception{
        UserResponse response = new UserResponse();

        UserDto userDto = userService.getUserById(id);
        BeanUtils.copyProperties(userDto, response);
        log.info("{}", userDto);
        
        final String message = "User created successfully..";
        ApiResponse<UserResponse> body = new ApiResponse<>(HttpStatus.CREATED, message,
                response);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> store(@RequestBody UserRequest userDetails){
        UserResponse response = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, response);

        final String message = "User created successfully..";
        ApiResponse<UserResponse> body = new ApiResponse<>(HttpStatus.CREATED, message,
                response);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/id")
    public String update(){
        return "update";
    }

    @DeleteMapping("/id")
    public String delete(){
        return "delete";
    }
}
