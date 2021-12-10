package pl.smile.SmileApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.smile.SmileApp.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID " +
            "AND a.date >= :now " +
            "AND a.isFinished = false " +
            "ORDER BY a.date ASC ")
    List<Appointment> getFutureOrPresentAppointments(@Param("patientID") long patientID,
                                                     @Param("now") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID " +
            "AND a.isFinished = true " +
            "ORDER BY a.date DESC")
    List<Appointment> getPastAppointments(@Param("patientID") long patientID);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID " +
            "AND a.doctor.id = :doctorID " +
            "AND a.date >= :now " +
            "AND a.isFinished = false " +
            "ORDER BY a.date ASC")
    List<Appointment> getFutureOrPresentPatientApp(@Param("patientID") long patientID,
                                                   @Param("doctorID") long doctorID,
                                                   @Param("now") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID " +
            "AND a.doctor.id = :doctorID " +
            "AND a.isFinished = true " +
            "ORDER BY a.date DESC")
    List<Appointment> getPatientHistoryApp(@Param("patientID") long patientID,
                                           @Param("doctorID") long doctorID);

    @Query("SELECT a FROM Appointment a WHERE a.date = :date AND a.isFinished = false")
    List<Appointment> findByDateAndFinishedIsFalse(@Param("date") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorID " +
            "AND a.isFinished = false " +
            "ORDER BY a.date, a.time ASC ")
    List<Appointment> findAllActiveAppointments(@Param("doctorID") long doctorID);

}