package com.pichincha.poc.system.post.service.domain.entity;

import java.util.List;

public class Editorial {
    private final List<Post> posts;
    private boolean active;

    private Editorial(Builder builder) {
        posts = builder.posts;
        active = builder.active;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Post> posts;
        private boolean active;

        private Builder() {
        }



        public Builder posts(List<Post> val) {
            posts = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Editorial build() {
            return new Editorial(this);
        }
    }
}
