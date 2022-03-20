package pl.smile.SmileApp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.DocumentRepository;
import pl.smile.SmileApp.service.AppointmentService;
import pl.smile.SmileApp.service.DocumentService;
import pl.smile.SmileApp.service.PatientService;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    public void saveFile(MultipartFile file, Long appointmentID, Long patientID) {
        String documentName = setDocumentName(file);
        try {
            Document document = createDocument(file, appointmentID, patientID, documentName);
            documentRepository.save(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String setDocumentName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    private Document createDocument(MultipartFile file, Long appointmentID, Long patientID, String documentName) throws IOException {
        Document document = getDocument(file, documentName);
        setAppointmentToDocument(appointmentID, document);
        setPatientToDocument(patientID, document);
        return document;
    }

    private Document getDocument(MultipartFile file, String documentName) throws IOException {
        return new Document(documentName, file.getContentType(), file.getBytes());
    }

    private void setAppointmentToDocument(Long appointmentID, Document document) {
        document.setAppointment(appointmentService.getById(appointmentID));
    }

    private void setPatientToDocument(Long patientID, Document document) {
        document.setPatient(patientService.getById(patientID));
    }


    @Override
    public Document getById(long id) {
        return documentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Document", id));
    }
}
