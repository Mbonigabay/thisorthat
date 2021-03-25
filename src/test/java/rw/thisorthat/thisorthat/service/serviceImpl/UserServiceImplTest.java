package rw.thisorthat.thisorthat.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rw.thisorthat.thisorthat.exception.ResourceNotFoundException;
import rw.thisorthat.thisorthat.model.User;
import rw.thisorthat.thisorthat.model.dto.UserDto;
import rw.thisorthat.thisorthat.repository.UserRepository;
import rw.thisorthat.thisorthat.util.Utils;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    
    @Mock
    Utils utils;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        
        user = new User();

        user.setId(1L);
        user.setEmail("mbonigabay@gmail.com");
        user.setFirstName("Yusuf");
        user.setLastName("Mbonigaba");
        user.setPassword("password");
        user.setUserId("VKstSNWgwQkGcN7vyWt7tc7oSwGvaf");
    }

    @Test
    final void testGetUser() throws Exception {

        when(userRepository.findByUserId(anyString())).thenReturn(user);

        UserDto userDto = userServiceImpl.getUserById("VKstSNWgwQkGcN7vyWt7tc7oSwGvaf");

        assertNotNull(userDto);
        assertEquals("Yusuf", user.getFirstName());
    }

    @Test
    final void testStoreUser(){
        when(userRepository.findByUserId(anyString())).thenReturn(null);
        when(utils.generateUserId(anyInt())).thenReturn("VKstSNWgwQkGcN7vyWt7tc7oSwGvaf");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto userDto = new UserDto();
        UserDto storedUserDto = userServiceImpl.createUser(userDto);

        assertNotNull(storedUserDto);
        assertEquals(user.getFirstName(), storedUserDto.getFirstName());
    }

    @Test
    final void testGetUser_Exception() {
        when(userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {userServiceImpl.getUserById("VKstSNWgwQkGcN7vyWt7tc7oSwGvaf");});
    }
}
