package com.mirim.byeolukyee.domain.image.controller;

import com.mirim.byeolukyee.domain.image.dto.ImageResponse;
import com.mirim.byeolukyee.domain.image.service.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/post-images")
@RequiredArgsConstructor
public class PostImageController {
    private final PostImageService postImageService;

    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<ImageResponse>> uploadPostImage(
            @PathVariable("id") Long id,
            @RequestParam("file") List<MultipartFile> files
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postImageService.uploadPostImage(id, files));
    }
}
