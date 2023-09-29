package vn.oceantech.mita.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.oceantech.mita.domain.Document;
import vn.oceantech.mita.dto.DocumentDto;
import vn.oceantech.mita.repository.DocumentRepository;
import vn.oceantech.mita.service.StorageService;

import java.io.FileOutputStream;

@Controller
@RequestMapping("/public")
public class ImageRestController {
    @Autowired
    private StorageService storageService;
    @Value("${storage.location}")
    private String folderPath;
    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/images/{name}")
    public ResponseEntity<Resource> getImageFile(@PathVariable String name) {
        Resource file = storageService.loadAsResource(name);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DocumentDto> uploadAttachment(@RequestParam("uploadfile") MultipartFile uploadfile) {
        DocumentDto result = null;
        try {
            String filePath = uploadfile.getOriginalFilename();
            filePath = folderPath +"/" +filePath;
            FileOutputStream stream = new FileOutputStream(filePath);
            try {
                stream.write(uploadfile.getBytes());
            } finally {
                stream.close();
            }

            Document file = new Document();
            file.setId(0L);
            file.setContentSize(uploadfile.getSize());
            file.setContentType(uploadfile.getContentType());
            file.setName(uploadfile.getOriginalFilename());
            file.setFilePath(filePath);
            file = documentRepository.save(file);

            result = new DocumentDto(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<DocumentDto>(result, HttpStatus.OK);
    }


}
