package com.laudado.talentforgeaibackend.services;

import com.laudado.talentforgeaibackend.config.CloudinaryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final CloudinaryConfig cloudinaryConfig;

    public String uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Map<?, ?> result = cloudinaryConfig.cloudinary()
                    .uploader().upload(bytes, emptyMap());
            return (String) result.get("secure_url");
        }catch(IOException e){
            throw new RuntimeException("Failed to upload file to cloudinary");
        }
    }
}
