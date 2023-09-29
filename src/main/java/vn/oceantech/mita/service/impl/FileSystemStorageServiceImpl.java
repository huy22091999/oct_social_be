package vn.oceantech.mita.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.oceantech.mita.config.StorageProperties;
import vn.oceantech.mita.service.StorageService;
import vn.oceantech.mita.utils.exception.StorageException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements StorageService {
    private final Path rootLocation;

    //	@Override

    public FileSystemStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file, String storeFilename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException(" cannot store file outside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                throw new StorageException("failed to store file");
            }

        } catch (Exception e) {

        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else
                throw new StorageException("could not read file" + fileName);
        } catch (Exception e) {
            throw new StorageException("could not read file" + fileName);
        }


    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void delete(String storeFileName) throws IOException {
        Path destinationFile = rootLocation.resolve(Paths.get(storeFileName)).normalize().toAbsolutePath();
        if (destinationFile != null)
            Files.delete(destinationFile);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);

        } catch (Exception e) {
            throw new StorageException("could not initalize storage");
        }
    }


}
