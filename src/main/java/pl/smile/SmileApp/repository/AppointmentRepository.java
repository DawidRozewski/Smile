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

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.date >= :now ORDER BY a.date asc ")
    List<Appointment> getFutureAppointments(@Param("patientId") long patientId,
                                            @Param("now")LocalDate now);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.date < :now ORDER BY a.date DESC")
    List<Appointment> getPastAppointments(@Param("patientId") long patientId,
                                          @Param("now") LocalDate now);

}
