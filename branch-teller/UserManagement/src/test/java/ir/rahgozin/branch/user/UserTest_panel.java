package ir.rahgozin.branch.user;

import ir.rahgozin.branch.BaseTest;
import ir.rahgozin.branch.SequenceCounter;
import ir.rahgozin.branch.exception.ErrorRecord;
import ir.rahgozin.branch.user.api.dto.UserDTO;
import ir.rahgozin.branch.config.TestConfig;
import ir.rahgozin.branch.tokenhelper.TokenHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest_panel extends BaseTest {

    private static final String CREATE_USER_URI = "/api/v1/panel/users/create-action";
    @LocalServerPort
    private int portNumber;

    @Autowired
    private TestRestTemplate rest;

    private String token;
    private String createUserURL;

    @BeforeAll
    public void getBackOfficeUserToken() {
        token = TokenHelper.getTokenForBackOfficeClerkUser(rest);
        createUserURL = BaseTest.LOCAL_HOST_URI + portNumber + CREATE_USER_URI;
    }

    @Test
    public void createUser_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = "user" + seq;
        String name = "user" + seq;
        String active = "true";
        String description = "user description";
        UserDTO dto = UserDTO.builder().name(name).userName(userName).active(active).description(description).build();

        Long userId = postFor_200_OK(rest.getRestTemplate(), createUserURL, token, dto, Long.class);

        Assertions.assertTrue(userId != null && userId > 0L);
    }

    @Test
    void nullInputUserName_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = null;
        String name = "user" + seq;
        String active = "true";
        String description = "user description";
        UserDTO dto = UserDTO.builder().name(name).userName(userName).active(active).description(description).build();

        ErrorRecord error = postFor_ErrorResponse(rest.getRestTemplate(), createUserURL, token, dto, ErrorRecord.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), error.httpStatus());
        Assertions.assertEquals("userName required", error.errorEnumString());

    }

    @Test
    void nullInputName_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = "userName" + seq;
        String name = null;
        String active = "true";
        String description = "user description";
        UserDTO dto = UserDTO.builder().name(name).userName(userName).active(active).description(description).build();

        ErrorRecord error = postFor_ErrorResponse(rest.getRestTemplate(), createUserURL, token, dto, ErrorRecord.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), error.httpStatus());
        Assertions.assertEquals("name required", error.errorEnumString());

    }
}
