package com.mirim.byeolukyee.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirim.byeolukyee.global.constant.status.BuyingPostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BUYING_POST")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuyingPost extends Post {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BuyingPostStatus status = BuyingPostStatus.IN_PROGRESS;

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "referenceItem", cascade = CascadeType.ALL)
    private List<SellingComment> referencingItems = new ArrayList<>();

    public void updatePost(String title, String description, Integer price, String location, BuyingPostStatus status) {
        super.updatePost(title, description, price, location);
        this.status = status;
    }

    public void setStatus(BuyingPostStatus status) {
        this.status = status;
    }
}
