package ir.rahgozin.branch.user.api;


import ir.rahgozin.branch.user.api.dto.RequestUserDTO;
import ir.rahgozin.branch.user.api.dto.ResponseUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/v1/panel/users")
public interface UserController {

    @PostMapping(value = "/create-action")
    ResponseEntity<ResponseUserDTO> addUser(@Valid @RequestBody RequestUserDTO requestUserDTO);
}
