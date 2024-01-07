package ir.rahgozin.branch.user.api.dto;

import lombok.Builder;

@Builder
public record ResponseUserDTO(
        Long id,
        String userName,
        String name,
        String branchSortCode,
        String active,
        String description
        ) {
}
