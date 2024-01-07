package ir.rahgozin.branch.user;

import ir.rahgozin.branch.BaseTest;
import ir.rahgozin.branch.SequenceCounter;
import ir.rahgozin.branch.exception.ErrorRecord;
import ir.rahgozin.branch.user.api.dto.RequestUserDTO;
import ir.rahgozin.branch.config.TestConfig;
import ir.rahgozin.branch.tokenhelper.TokenHelper;
import ir.rahgozin.branch.user.api.dto.ResponseUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class TestUserPanel extends BaseTest {

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
    void createUser_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = "user" + seq;
        String name = "user" + seq;
        String branchSortCode = "branch" + seq;
        String active = "true";
        String description = "user description";
        RequestUserDTO dto = RequestUserDTO.builder()
                .userName(userName)
                .name(name)
                .branchSortCode(branchSortCode)
                .active(active)
                .description(description)
                .build();

        ResponseUserDTO response = postFor200OK(rest.getRestTemplate(), createUserURL, token, dto, ResponseUserDTO.class);

        Assertions.assertTrue(response != null && response.id() > 0L);
    }

    @Test
    void nullInputUserName_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = null;
        String name = "user" + seq;
        String branchSortCode = "branch" + seq;
        String active = "true";
        String description = "user description";
        RequestUserDTO dto = RequestUserDTO
                .builder()
                .userName(userName)
                .name(name)
                .branchSortCode(branchSortCode)
                .active(active)
                .description(description)
                .build();

        ErrorRecord error = postForErrorResponse(rest.getRestTemplate(), createUserURL, token, dto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), error.httpStatus());
        Assertions.assertEquals("userName required", error.errorEnumString());

    }

    @Test
    void nullInputName_test() {
        int seq = SequenceCounter.getNextNumber();
        String userName = "userName" + seq;
        String name = null;
        String branchSortCode = "branch" + seq;
        String active = "true";
        String description = "user description";
        RequestUserDTO dto = RequestUserDTO
                .builder()
                .userName(userName)
                .name(name)
                .branchSortCode(branchSortCode)
                .active(active)
                .description(description)
                .build();

        ErrorRecord error = postForErrorResponse(rest.getRestTemplate(), createUserURL, token, dto);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), error.httpStatus());
        Assertions.assertEquals("name required", error.errorEnumString());

    }
}
