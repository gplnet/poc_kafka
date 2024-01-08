package com.pichincha.poc.system.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreatePostsCommand {
    @NotNull
    private final Integer id;
    @NotNull
    private final String title;
    @NotNull
    private final String body;
    @NotNull
    private final Integer userId;
}
