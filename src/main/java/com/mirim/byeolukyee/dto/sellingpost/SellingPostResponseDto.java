package com.mirim.byeolukyee.dto.sellingpost;

import com.mirim.byeolukyee.constant.SellingPostStatus;
import com.mirim.byeolukyee.dto.post.PostResponseDto;
import com.mirim.byeolukyee.dto.postimage.PostImageResponseDto;
import com.mirim.byeolukyee.dto.user.UserResponseDto;
import com.mirim.byeolukyee.domain.SellingPost;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SellingPostResponseDto extends PostResponseDto {

    private final SellingPostStatus status;
    private final String krStatus;
    private final List<PostImageResponseDto> images;

    @Builder
    public SellingPostResponseDto(Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, UserResponseDto user, String title, String description, Integer price, String location, SellingPostStatus status, List<PostImageResponseDto> images) {
        super(isDeleted, createdAt, updatedAt, id, user, title, description, price, location);
        this.status = status;
        this.krStatus = status.getKrName();
        this.images = images;
    }

    public static SellingPostResponseDto from(SellingPost sellingPost) {

        List<PostImageResponseDto> postImageList = new ArrayList<>();

        if (sellingPost.getPostImageList() != null)
            postImageList = sellingPost.getPostImageList().stream().map(PostImageResponseDto::from).collect(Collectors.toList());


        return SellingPostResponseDto.builder()
                .isDeleted(sellingPost.getIsDeleted())
                .createdAt(sellingPost.getCreatedAt())
                .updatedAt(sellingPost.getUpdatedAt())
                .id(sellingPost.getId())
                .user(UserResponseDto.from(sellingPost.getUser()))
                .title(sellingPost.getTitle())
                .description(sellingPost.getDescription())
                .price(sellingPost.getPrice())
                .location(sellingPost.getLocation())
                .status(sellingPost.getStatus())
                .images(postImageList)
                .build();
    }

}
