package com.seolandfriends.byeolbyeolcoffee.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUploadUtils {

	public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getResource().getFilename());

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(replaceFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new IOException("Could not save file: " + fileName, ex);
		}

		return replaceFileName;
	}

	public static void deleteFile(String uploadDir, String fileName) {
		Path uploadPath = Paths.get(uploadDir);

		try {
			Path filePath = uploadPath.resolve(fileName);
			Files.delete(filePath);
		} catch (IOException ex) {
			log.info("Could not delete file: " + fileName, ex);
		}
	}
}