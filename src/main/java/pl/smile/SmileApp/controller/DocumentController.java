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
import pl.smile.SmileApp.repository.DocumentRepository;
import pl.smile.SmileApp.service.DocumentService;

import java.util.List;

@Controller
@AllArgsConstructor
public class DocumentController {

    private DocumentService documentService;
    private DocumentRepository documentRepository;

    @GetMapping("/app/doctor/showFiles")
    public String get(Model model){
        List<Document> documents = documentRepository.findAll();
        model.addAttribute("docs", documents);
        return "doc";
    }

    @PostMapping("/app/doctor/uploadFiles")
    public String upload(@RequestParam("files") MultipartFile[] files) {
        for(MultipartFile file : files) {
            documentService.saveFile(file);
        }
        return "redirect:/app/doctor/showFiles";
    }

    @GetMapping("/app/doctor/downloadFile/{fileID}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileID) {
        Document document = documentService.getFile(fileID);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+document.getName()+"\"")
                .body(new ByteArrayResource(document.getData()));
    }
}
