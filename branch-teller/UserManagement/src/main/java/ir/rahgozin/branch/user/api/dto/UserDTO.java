package ir.rahgozin.branch.user.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record UserDTO(

        @NotNull(message = "{user.username.required}")
        @NotBlank(message = "{user.username.required}")
        String userName,

        @NotNull(message = "{user.name.required}")
        @NotBlank(message = "{user.name.required}")
        String name,

        @NotNull(message = "{user.active.required}")
        @NotBlank(message = "{user.active.required}")
        @Pattern(regexp = "true|false", message = "{user.active.should_be_true_or_false}")
        String active,

        String description
        ) {
}
