package com.mirim.byeolukyee.domain.post.dto.buyingpost;

import com.mirim.byeolukyee.global.constant.status.BuyingPostStatus;
import com.mirim.byeolukyee.domain.post.dto.PostResponse;
import com.mirim.byeolukyee.domain.user.dto.UserResponse;
import com.mirim.byeolukyee.domain.post.entity.BuyingPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BuyingPostResponse extends PostResponse {
    private final BuyingPostStatus status;
    private final String krStatus;

    @Builder
    public BuyingPostResponse(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponse user, String title, String description, Integer price, String location, BuyingPostStatus status) {
        super(isDeleted, createdAt, updatedAt, id, user, title, description, price, location);
        this.status = status;
        this.krStatus = status.getKrName();
    }
    
    public static BuyingPostResponse from(BuyingPost buyingPost) {
        return BuyingPostResponse.builder()
                .isDeleted(buyingPost.getIsDeleted())
                .createdAt(buyingPost.getCreatedAt())
                .updatedAt(buyingPost.getUpdatedAt())
                .id(buyingPost.getId())
                .user(UserResponse.from(buyingPost.getUser()))
                .title(buyingPost.getTitle())
                .description(buyingPost.getDescription())
                .price(buyingPost.getPrice())
                .location(buyingPost.getLocation())
                .status(buyingPost.getStatus())
                .build();
    }
}
