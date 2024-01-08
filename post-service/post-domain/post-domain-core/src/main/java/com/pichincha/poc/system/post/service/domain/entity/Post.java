package com.pichincha.poc.system.post.service.domain.entity;

import com.pichincha.poc.system.domain.valueobject.PostsStatus;
import com.pichincha.poc.system.post.service.domain.exception.PostDomainException;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private Integer id;
    private String  title;
    private String  body;
    private PostsStatus postsStatus;
    private List<String> failureMensagges;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public PostsStatus getPostsStatus() {
        return postsStatus;
    }

    public List<String> getFailureMensagges() {
        return failureMensagges;
    }

    private Post(Builder builder) {
        id = builder.id;
        title = builder.title;
        body = builder.body;
        postsStatus = builder.postsStatus;
        failureMensagges = builder.failureMensagges;
    }


    public void initializePosts(){
        postsStatus = PostsStatus.PENDING;
        failureMensagges =  new ArrayList<>();

    }

    public void review(){
        if(postsStatus != PostsStatus.PENDING){
            throw new PostDomainException("Post is not in correct state for review operation!");
        }
        postsStatus = PostsStatus.IN_REVIEW;
    }

    public void approve(){
        if(postsStatus != PostsStatus.IN_REVIEW){
            throw new PostDomainException("Post is not in correct state for approve operation");
        }
        postsStatus = PostsStatus.APPROVED;
    }

    public void initCancel(List<String> failureMensagges){
        if(postsStatus != PostsStatus.IN_REVIEW){
            throw new PostDomainException("Post is not in correct state for initCancel operation!");
        }
        postsStatus = PostsStatus.CANCELLING;
        updateFailureMessages(failureMensagges);
    }

    public void cancel(List<String> failureMensagges){
        if(!(postsStatus == PostsStatus.CANCELLING || postsStatus == PostsStatus.PENDING) ){
            throw new PostDomainException("Post is not in correct state for cancel operation");
        }
        postsStatus = PostsStatus.CANCELLED;
        updateFailureMessages(failureMensagges);
    }

    private void updateFailureMessages(List<String> failureMessages){
        if(this.failureMensagges != null && failureMessages != null){
            this.failureMensagges.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if(this.failureMensagges==null){
            this.failureMensagges = failureMessages;
        }
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String title;
        private String body;
        private PostsStatus postsStatus;
        private List<String> failureMensagges;

        private Builder() {
        }



        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder body(String val) {
            body = val;
            return this;
        }

        public Builder postsStatus(PostsStatus val) {
            postsStatus = val;
            return this;
        }

        public Builder failureMensagges(List<String> val) {
            failureMensagges = val;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
