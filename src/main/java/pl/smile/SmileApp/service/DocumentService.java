package pl.smile.SmileApp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;

public interface DocumentService  {
    void saveFile(MultipartFile file, Long appointmentID, Long patientID);
    Document getById(long id);

}
