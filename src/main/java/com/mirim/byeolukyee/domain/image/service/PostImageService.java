package com.mirim.byeolukyee.domain.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mirim.byeolukyee.domain.post.entity.Post;
import com.mirim.byeolukyee.domain.image.entity.PostImage;
import com.mirim.byeolukyee.domain.image.dto.ImageResponse;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.domain.post.repository.SellingPostRepository;
import com.mirim.byeolukyee.domain.image.repository.PostImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final SellingPostRepository sellingPostRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    @Transactional
    public List<ImageResponse> uploadPostImage(Long id, List<MultipartFile> images) throws IOException {

        List<ImageResponse> postImageResponseDtos = new ArrayList<>();

        for (MultipartFile image : images) {
            // 파일 이름 (+확장자)
            String originalFileName = image.getOriginalFilename();
            int dotIndex = originalFileName.lastIndexOf('.');

            // 파일 이름과 확장자 분리
            String fileName = originalFileName.substring(0, dotIndex);
            String extension = originalFileName.substring(dotIndex + 1);

            // 새로운 파일 이름 생성
            long unixTimeMillis = System.currentTimeMillis();
            String uploadedFileName = fileName + "_" + unixTimeMillis + "." + extension;

            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(image.getSize());

            amazonS3Client.putObject(bucket, uploadedFileName, image.getInputStream(), metadata);
            String filePath = "https://" + bucket + "/test" + uploadedFileName;


            // 엔티티 저장
            Post post = sellingPostRepository.findById(id)
                    .orElseThrow(() -> PostNotFoundException.EXCEPTION);

            PostImage postImage = PostImage.builder()
                    .post(post)
                    .originalFilename(originalFileName)
                    .uploadedFilename(uploadedFileName)
                    .filePath(filePath)
                    .build();

            postImageResponseDtos.add(ImageResponse.from(postImageRepository.save(postImage)));
        }


        return postImageResponseDtos;
    }

}
