package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.smile.SmileApp.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByAppointment_IdAndPatient_Id(Long appointmentID, Long patientID);
}
