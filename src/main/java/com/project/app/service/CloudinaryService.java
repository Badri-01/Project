package com.project.app.service;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Documentation: https://cloudinary.com/documentation/java_integration -
 * https://cloudinary.com/documentation/image_transformations
 *
 * @author Francesco Galgani
 */
@Service
public class CloudinaryService {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Cloudinary cloudinary;

	public String uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            HashMap<Object, Object> optionsMap = new HashMap<>();
            optionsMap.put("public_id", "CloudTest/defects");
            optionsMap.put("resource_type", "raw");          
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, optionsMap);            
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
