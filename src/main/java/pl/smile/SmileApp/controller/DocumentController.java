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
import pl.smile.SmileApp.entity.Appointment;
import pl.smile.SmileApp.entity.Document;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.repository.AppointmentRepository;
import pl.smile.SmileApp.repository.DocumentRepository;
import pl.smile.SmileApp.service.AppointmentService;
import pl.smile.SmileApp.service.DocumentService;
import pl.smile.SmileApp.service.PatientService;

import java.util.List;

@Controller
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final PatientService patientService;
    private final DocumentRepository documentRepository;
    private final AppointmentService appointmentService;

    @GetMapping("/app/doctor/uploadFiles/{appID}/{patientID}")
    public String prepToAddFiles(@PathVariable Long appID,
                                 @PathVariable Long patientID,
                                 Model model) {
        List<Document> documents = documentRepository.findAllByAppointment_IdAndPatient_Id(appID, patientID);
        model.addAttribute("documents", documents);
        Appointment appointment = appointmentService.getById(appID);
        model.addAttribute("appointment", appointment);
        Patient patient = patientService.getById(patientID);
        model.addAttribute("patient", patient);
        return "/doctor/uploadFile";
    }

    @PostMapping("/app/doctor/uploadFiles/{appID}/{patientID}")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files,
                              @PathVariable Long appID,
                              @PathVariable Long patientID) {
        for (MultipartFile file : files) {
            documentService.saveFile(file, appID, patientID);
        }
        return "redirect:/app/doctor/uploadFiles/" + appID + "/" + patientID;
    }

    @GetMapping("/app/file/download/{fileID}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileID) {
        Document document = documentService.getById(fileID);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }

    @GetMapping("/app/file/remove/{appID}/{documentID}")
    public String prepToRemoveDocument(@PathVariable Long documentID, Model model) {
        Document document = documentService.getById(documentID);
        model.addAttribute("document", document);
        return "/doctor/remove_document";
    }

    @PostMapping("/app/file/remove/{appID}/{documentID}")
    public String removeDocument(@PathVariable Long documentID,
                                 @PathVariable Long appID,
                                 @RequestParam String confirmed) {
        Appointment appointment = appointmentService.getById(appID);
        if ("yes".equals(confirmed)) {
            documentRepository.deleteById(documentID);
        }
        return "redirect:/app/doctor/uploadFiles/" + appID + "/" + appointment.getPatient().getId();
    }

    @GetMapping("/app/file/show/{appID}/{patientID}")
    public String showFiles(@PathVariable Long appID,
                            @PathVariable Long patientID,
                            Model model) {
        List<Document> documents = documentRepository.findAllByAppointment_IdAndPatient_Id(appID, patientID);
        model.addAttribute("documents", documents);
        Appointment appointment = appointmentService.getById(appID);
        model.addAttribute("appointment", appointment);
        return "/patient/showFiles";
    }


}
