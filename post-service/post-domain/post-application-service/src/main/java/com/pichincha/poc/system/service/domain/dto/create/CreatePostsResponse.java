package com.pichincha.poc.system.service.domain.dto.create;

import com.pichincha.poc.system.domain.valueobject.PostsStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
@AllArgsConstructor
public class CreatePostsResponse {
    @NotNull
    private final Integer id;
    @NotNull
    private final PostsStatus postsStatus;
    @NotNull
    private final String message;
}
