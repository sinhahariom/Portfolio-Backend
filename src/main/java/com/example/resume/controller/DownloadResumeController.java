package com.example.resume.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.resume.dto.DownloadResume;
import com.example.resume.helper.Constants;
import com.example.resume.service.DownloadResumeService;

@RestController
@CrossOrigin(origins = "*")
public class DownloadResumeController {

	public static Logger logger = LoggerFactory.getLogger(DownloadResumeController.class);
	
	@Autowired
	DownloadResumeService downloadResumeService;

	@RequestMapping(value="/testDownloadResume", method=RequestMethod.GET)
	public String testDownloadResume() {
		return "Resume Controller Works !!";
	}

	@RequestMapping(value="/downloadResume", method=RequestMethod.GET, produces = "application/octet-stream")
	public ResponseEntity<Resource> downloadResume(){
		
		logger.info("downloadResume request received");
		Path path = Paths.get(Constants.filePath + Constants.fileName);
		Resource resource = null;
		try {
			resource = (Resource) new UrlResource(path.toUri());
			logger.info("downloadResume request success");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.info("downloadResume request failed");
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ((UrlResource) resource).getFilename() + "\"")
				.body(resource);


	}

}
