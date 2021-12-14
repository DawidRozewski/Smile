package pl.smile.SmileApp.service;

import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.DocumentRepository;

public interface DocumentService  {

    Document saveFile(MultipartFile file, Long appointmentID, Long patientID);


}
