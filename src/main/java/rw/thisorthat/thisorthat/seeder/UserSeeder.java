package rw.thisorthat.thisorthat.seeder;

import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import rw.thisorthat.thisorthat.model.dto.UserDto;
import rw.thisorthat.thisorthat.repository.UserRepository;
import rw.thisorthat.thisorthat.service.UserService;

@Component
@Slf4j
@Data
public class UserSeeder {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public void seedUsersTable() {
        Faker faker = new Faker();
        int numberOfUsers = (int) userRepository.count();
        log.info("number of users: " + numberOfUsers);
        if (numberOfUsers == 0) {
            IntStream.range(0, 100).forEach(i -> {
                UserDto userDto = new UserDto();
                userDto.setFirstName(faker.name().firstName());
                userDto.setLastName(faker.name().lastName());
                userDto.setEmail(faker.internet().emailAddress());
                userDto.setPassword("password");
                userService.createUser(userDto);            
            });
            log.warn("100 users added by seeder");
        } else {
            log.warn("No user added by seeder");
        }
    }

}