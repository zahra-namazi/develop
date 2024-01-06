package ir.rahgozin.branch.user.service;

import ir.rahgozin.branch.exception.ApplicationException;
import ir.rahgozin.branch.user.api.dto.UserDTO;
import ir.rahgozin.branch.user.domain.User;
import ir.rahgozin.branch.user.enumeration.UserErrorCodeEnum;
import ir.rahgozin.branch.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public Long addNewUser(UserDTO userDTO) {
        validateBeforeSave(userDTO);
        User user = repository.save(map(userDTO));
        return user.getId();
    }

    private void validateBeforeSave(UserDTO userDTO) {
        if(userExists(userDTO.userName())) {
            throw new ApplicationException(UserErrorCodeEnum.USER_ALREADY_EXISTS, userDTO.userName());
        }
    }

    public boolean userExists(String userName) {
        Optional<User> user = repository.findByUserName(userName);
        return user.isPresent();
    }
    private User map(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.userName());
        user.setName(dto.name());
        user.setActive(Boolean.getBoolean(dto.active()));
        user.setDescription(dto.description());

        return user;
    }
}
