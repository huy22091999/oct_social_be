package vn.oceantech.mita.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;


public interface StorageService {

	void init();

	void delete(String storeFileName) throws IOException;

	Path load(String filename);

	Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storeFilename);

	//String getStorageFileName(MultipartFile file, String id);
	
}
