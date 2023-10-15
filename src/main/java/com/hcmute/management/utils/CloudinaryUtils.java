package com.hcmute.management.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.hcmute.management.constant.CloudinaryConstant.*;

@Component
@RequiredArgsConstructor
public class CloudinaryUtils {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of(
                                PUBLIC_ID, UUID.randomUUID().toString(),
                                UPLOAD_PRESET, PRODUCT_PATH
                        ))
                .get(URL_PROPERTY)
                .toString();
    }
    public HashMap<String, String> uploadFileToFolder(String pathName, String fileName, MultipartFile multipartFile) throws IOException {
        var file = cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of(
                                PUBLIC_ID, fileName,
                                UPLOAD_PRESET, pathName,
                                OVERWRITE, true
                        ));

        return (HashMap<String, String>) file;
    }

    public void deleteImage(String publicId) throws IOException {
        // Xóa hình ảnh từ Cloudinary bằng cách sử dụng public ID
        Map<String, String> options = ObjectUtils.asMap("invalidate", true);
        cloudinary.uploader().destroy(publicId, options);
    }
}
