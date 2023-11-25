package com.seolandfriends.byeolbyeolcoffee.util;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class FileUploadUtilsTest {

	@TempDir
	Path tempDirectory;

	String uploadDir;
	String fileName;
	MultipartFile multipartFile;

	@BeforeEach
	void setUp() {
		uploadDir = tempDirectory.toString();
		fileName = "testfile";
		multipartFile = new MockMultipartFile(
			fileName,
			fileName + ".txt",
			"text/plain",
			"Test content".getBytes()
		);
	}

	@Test
	void saveFile_ShouldSaveFileCorrectly() throws Exception {
		String savedFileName = FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		Path savedFilePath = Paths.get(uploadDir, savedFileName);

		assertTrue(Files.exists(savedFilePath));
		assertNotEquals(0, Files.size(savedFilePath));
	}

	@Test
	void saveFile_ShouldCreateDirectory_WhenDirectoryDoesNotExist() throws Exception {
		Path nonExistentDirectory = tempDirectory.resolve("nonexistent");
		uploadDir = nonExistentDirectory.toString();

		// 이제 새 디렉토리가 성공적으로 생성되었는지 확인합니다.
		String savedFileName = FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		Path savedFilePath = Paths.get(uploadDir, savedFileName);

		assertTrue(Files.exists(savedFilePath));
		assertTrue(Files.isDirectory(nonExistentDirectory));
	}

	@Test
	void deleteFile_ShouldDeleteFile_WhenFileExists() throws Exception {
		// First, save the file
		String savedFileName = FileUploadUtils.saveFile(uploadDir, fileName, multipartFile);
		Path savedFilePath = Paths.get(uploadDir, savedFileName);

		// Now, attempt to delete it
		FileUploadUtils.deleteFile(uploadDir, savedFileName);

		assertFalse(Files.exists(savedFilePath));
	}

	@Test
	void deleteFile_ShouldNotThrowException_WhenFileDoesNotExist() {
		String nonExistentFileName = "shouldnotexist.txt";

		// This should not throw an exception
		assertDoesNotThrow(() ->
			FileUploadUtils.deleteFile(uploadDir, nonExistentFileName)
		);
	}
}