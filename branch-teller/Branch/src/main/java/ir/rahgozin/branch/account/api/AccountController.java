package ir.rahgozin.branch.account.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/account")
public class AccountController {

    @GetMapping(value = "test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("ok");
    }
}
