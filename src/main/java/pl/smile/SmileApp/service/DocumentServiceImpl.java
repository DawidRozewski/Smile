package pl.smile.SmileApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.DoctorRepository;
import pl.smile.SmileApp.repository.DocumentRepository;
import pl.smile.SmileApp.repository.PatientRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public Document saveFile(MultipartFile file, Long appointmentID, Long patientID) {
        String documentName = file.getOriginalFilename();
        try{
            Document document = new Document(documentName, file.getContentType(), file.getBytes());
                document.setAppointment(appointmentRepository.getById(appointmentID));
                document.setPatient(patientRepository.getById(patientID));
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
