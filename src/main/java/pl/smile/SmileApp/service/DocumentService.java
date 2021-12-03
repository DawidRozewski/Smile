package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.repository.DocumentRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Document saveFile(MultipartFile file) {
        String documentName = file.getOriginalFilename();
        try{
            Document document = new Document(documentName, file.getContentType(), file.getBytes());
            return documentRepository.save(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Document getFile(Long fileId) {
        return documentRepository.getById(fileId);
    }





}
