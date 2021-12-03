package pl.smile.SmileApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.DocumentRepository;
import pl.smile.SmileApp.repository.PatientRepository;
import pl.smile.SmileApp.service.DocumentServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class DocumentController {

    private final DocumentServiceImpl documentService;
    private final DocumentRepository documentRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @GetMapping("/app/doctor/uploadFiles/{appID}/{patientID}")
    public String get(@PathVariable Long appID,
                      @PathVariable Long patientID,
                      Model model) {
        List<Document> documents = documentRepository.findAllByAppointment_IdAndPatient_Id(appID, patientID);
        model.addAttribute("documents", documents);
        model.addAttribute("appointment", appointmentRepository.getById(appID));
        model.addAttribute("patient", patientRepository.getById(patientID));
        return "/doctor/uploadFile";
    }

    @PostMapping("/app/doctor/uploadFiles/{appID}/{patientID}")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files,
                              @PathVariable Long appID,
                              @PathVariable Long patientID) {
        for (MultipartFile file : files) {
            documentService.saveFile(file, appID, patientID);
        }
        return "redirect:/app/doctor/patient/" + patientID;
    }

    @GetMapping("/app/file/download/{fileID}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileID) {
        Document document = documentService.getFile(fileID);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }

    @GetMapping("/app/patient/show-files/{appID}")
    public String showFiles(@PathVariable Long appID,
                            Model model,
                            Principal principal) {
        String patientEmail = principal.getName();
        Patient patient = patientRepository.getByEmail(patientEmail);
        List<Document> documents = documentRepository.findAllByAppointment_IdAndPatient_Id(appID, patient.getId());
        model.addAttribute("documents", documents);
        model.addAttribute("appointment", appointmentRepository.getById(appID));
        return "/patient/showFiles";
    }


}
