package com.project.app.controller;

import com.project.app.service.CloudinaryService;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for the Cloudinary Service
 *
 * @author Francesco Galgani
 */
@RestController
@RequestMapping("/cloud")
public class CloudinaryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CloudinaryService cloudinaryService;

    /**
     * Upload a MultipartFile to Cloudinary.
     *
     * @param authToken
     * @param email
     * @param file
     * @return the publicId assigned to the uploaded file, or null in case of
     * error
     */
    @PostMapping("/addAttachment")
    public @ResponseBody
    String upload( @RequestParam("file") MultipartFile file) {
        return cloudinaryService.uploadFile( file);
    }
    
   
    }
