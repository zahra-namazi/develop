package ir.rahgozin.branch.user.api;


import ir.rahgozin.branch.user.api.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/v1/panel/users")
public interface UserController {

    @PostMapping(value = "/create-action")
    ResponseEntity<Long> addUser(@Valid @RequestBody UserDTO userDTO);
}
