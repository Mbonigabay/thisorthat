package rw.thisorthat.thisorthat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import rw.thisorthat.thisorthat.model.dto.UserDto;
import rw.thisorthat.thisorthat.model.response.ApiResponse;
import rw.thisorthat.thisorthat.model.response.UserResponse;
import rw.thisorthat.thisorthat.service.serviceImpl.UserServiceImpl;

public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    UserDto user;

    final String USERID = "VKstSNWgwQkGcN7vyWt7tc7oSwGvaf";

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this).close();
        
        user = new UserDto();

        // user.setId(1L);
        user.setEmail("mbonigabay@gmail.com");
        user.setFirstName("Yusuf");
        user.setLastName("Mbonigaba");
        user.setPassword("password");
        user.setUserId(USERID);
    }

    @Test
    final void getUserTest() throws Exception{
        when(userService.getUserById(anyString())).thenReturn(user);

        ResponseEntity<ApiResponse<UserResponse>> userResponse = userController.show(USERID);

        assertNotNull(userResponse);
        assertEquals(USERID, userResponse.getBody().getPayload().getUserId());
    }
}
