package com.pichincha.poc.system.service.domain.dto.message;


import com.pichincha.poc.system.domain.valueobject.PostsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EditorialResponse {

    private String id;
    private String title;
    private String body;
    private String userId;
    private Instant createdAt;
    private PostsStatus postsStatus;
    private List<String> failureMessages;

}
