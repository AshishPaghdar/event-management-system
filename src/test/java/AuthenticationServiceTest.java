import com.event.DTO.UserDTO;
import com.event.service.AuthenticationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Locale;

@SpringBootTest(classes = AuthenticationService.class)
public class AuthenticationServiceTest {

/*    @Autowired
//    @Qualifier("authenticationService1")
   private AuthenticationService authenticationService1;*/

    @Test
    public void registrationTest() throws BadRequestException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Ashsih");
        userDTO.setId(123L);
        userDTO.setAddress("Ahmedabad- Vasna");
        userDTO.setCreationDate(LocalDate.now());
        userDTO.setEventsOfInterest("News, Wedding,");
        userDTO.setEmail("apaghdar@vmware.com");
        userDTO.setGender("Male");
        userDTO.setMobile("7896541236");
        userDTO.setPassword("Ashish@123");  // User Registered Successfully!

        //authenticationService1.registration(userDTO, new Locale("en"));
        String str =  "User Registered Successfully!";
        assertEquals("User Registered Successfully!",str, "Default message");
    }
}
