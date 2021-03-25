package rw.thisorthat.thisorthat.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rw.thisorthat.thisorthat.exception.ResourceAlreadyExistException;
import rw.thisorthat.thisorthat.exception.ResourceNotFoundException;
import rw.thisorthat.thisorthat.model.User;
import rw.thisorthat.thisorthat.model.dto.UserDto;
import rw.thisorthat.thisorthat.repository.UserRepository;
import rw.thisorthat.thisorthat.service.UserService;
import rw.thisorthat.thisorthat.util.Utils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new ResourceAlreadyExistException("User already exists");

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        String userId = utils.generateUserId(30);
        String password = userDto.getPassword();
        user.setUserId(userId);
        user.setPassword(password);

        User storedUser = userRepository.save(user);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();
        if(page>0) page = page-1;
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, limit));
        List<User> users = usersPage.getContent();
        for(User user: users){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            returnValue.add(userDto);
        }
        log.info("page " + page);
        return returnValue;
    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto returnValue = new UserDto();
        User user = userRepository.findByUserId(userId);
        log.info("{}", user);

        if(user == null) throw new ResourceNotFoundException("User not found");
            

        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
    }
    
}
