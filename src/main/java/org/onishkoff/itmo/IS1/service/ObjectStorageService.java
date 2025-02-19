package org.onishkoff.itmo.IS1.service;


import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class ObjectStorageService {

    private final MinioClient minioClient;
    private final SecurityUtil securityUtil;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Transactional
    public String uploadFile(MultipartFile file, Integer uploadId) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String uploadUrl = securityUtil.getUserFromContext().getId()+ "/" + uploadId + "-" + file.getOriginalFilename();
        String minioAddress = minioUrl + "/" + bucketName;
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .contentType(file.getContentType())
                        .object(uploadUrl)
                        .stream(file.getInputStream(), file.getInputStream().available(), -1)
                        .build()
        );
        return minioAddress + "/" + uploadUrl;
    }
}
