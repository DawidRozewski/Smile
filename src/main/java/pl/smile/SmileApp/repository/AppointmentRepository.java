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

    List<Appointment> findAllByPatientId(long id);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID AND a.date >= :now ORDER BY a.date ASC ")
    List<Appointment> getFutureOrPresentAppointments(@Param("patientID") long patientID,
                                            @Param("now") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID AND a.date < :now ORDER BY a.date DESC")
    List<Appointment> getPastAppointments(@Param("patientID") long patientID,
                                          @Param("now") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID AND a.doctor.id = :doctorID AND a.date >= :now ORDER BY a.date ASC")
    List<Appointment> getFutureOrPresentPatientApp(@Param("patientID") long patientID,
                                                   @Param("doctorID") long doctorID,
                                                   @Param("now") LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientID AND a.doctor.id = :doctorID AND a.date < :now ORDER BY a.date ASC")
    List<Appointment> getPatientHistoryApp(@Param("patientID") long patientID,
                                           @Param("doctorID") long doctorID,
                                           @Param("now") LocalDate now);








}