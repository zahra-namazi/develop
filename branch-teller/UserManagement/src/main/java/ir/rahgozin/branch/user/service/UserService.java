package ir.rahgozin.branch.user.service;

import ir.rahgozin.branch.exception.ApplicationException;
import ir.rahgozin.branch.user.api.dto.RequestUserDTO;
import ir.rahgozin.branch.user.api.dto.ResponseUserDTO;
import ir.rahgozin.branch.user.domain.User;
import ir.rahgozin.branch.user.enumeration.UserErrorCodeEnum;
import ir.rahgozin.branch.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public ResponseUserDTO addNewUser(RequestUserDTO requestUserDTO) {
        validateBeforeSave(requestUserDTO);
        User user = repository.save(map(requestUserDTO));
        return map(user);
    }

    private void validateBeforeSave(RequestUserDTO requestUserDTO) {
        if (userExists(requestUserDTO.userName())) {
            throw new ApplicationException(UserErrorCodeEnum.USER_ALREADY_EXISTS, requestUserDTO.userName());
        }
    }

    public boolean userExists(String userName) {
        Optional<User> user = repository.findByUserName(userName);
        return user.isPresent();
    }

    private User map(RequestUserDTO dto) {
        User user = new User();
        user.setUserName(dto.userName());
        user.setName(dto.name());
        user.setBranchSortCode(dto.branchSortCode());
        user.setActive(Boolean.getBoolean(dto.active()));
        user.setDescription(dto.description());

        return user;
    }

    private ResponseUserDTO map(User user) {
        return ResponseUserDTO
                .builder()
                .id(user.getId())
                .userName(user.getUserName())
                .name(user.getName())
                .branchSortCode(user.getBranchSortCode())
                .active(user.isActive() + "")
                .description(user.getDescription()).build();

    }
}
