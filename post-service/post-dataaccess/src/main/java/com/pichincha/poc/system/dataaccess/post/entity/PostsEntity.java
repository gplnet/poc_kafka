package com.pichincha.poc.system.dataaccess.post.entity;


import com.pichincha.poc.system.domain.valueobject.PostsStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class PostsEntity {
    @Id
    private Integer id;
    private String  title;
    private String  body;
    private PostsStatus postsStatus;
    private List<String> failureMensagges;
}
