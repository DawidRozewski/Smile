package pl.smile.SmileApp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.entity.TreatmentPlan;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.TreatmentPlanRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.smile.SmileApp.service.impl.ServiceHelper.*;

@ExtendWith(MockitoExtension.class)
class TreatmentPlanServiceImplTest {
    public static final long TREATMENT_PLAN_ID = 1L;
    public static final long PATIENT_ID = 1L;
    public static final long NON_EXISTING_ID = 99L;
    @Mock
    private TreatmentPlanRepository treatmentPlanRepository;
    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TreatmentPlanServiceImpl treatmentPlanService;

    private TreatmentPlan treatmentPlan;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        treatmentPlan = createTreatmentPlan();
        patient = createPatient();
        doctor = createDoctor();
    }

    @Test
    public void givenTreatmentPlanDayIsSunday_whenIfSundayThrowMessage_thenReturnTrue() {
        // given
        treatmentPlan.setVisitDate(LocalDate.of(2022, 3, 20));
        // when
        boolean result = treatmentPlanService.ifSundayThrowMessage(treatmentPlan, bindingResult);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void givenTreatmentPlanDayIsNotSunday_whenIfSundayThrowMessage_thenReturnFalse() {
        // given
        // when
        boolean result = treatmentPlanService.ifSundayThrowMessage(treatmentPlan, bindingResult);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void givenPatientIdAndDoctorObject_whenGetAllByPatientIdAndDoctor_thenReturnTreatmentPlanList() {
        // given
        TreatmentPlan treatmentPlan1 = TreatmentPlan.builder()
                .id(2L)
                .patient(patient)
                .doctor(doctor)
                .description("description")
                .price(100)
                .visitNumber(1)
                .visitDate(LocalDate.of(2022, 3, 19))
                .build();

        given(treatmentPlanRepository.findAllByPatientIdAndDoctor(PATIENT_ID, doctor)).willReturn(Arrays.asList(treatmentPlan, treatmentPlan1));
        // when
        List<TreatmentPlan> allByPatientIdAndDoctor = treatmentPlanService.getAllByPatientIdAndDoctor(PATIENT_ID, doctor);
        // then
        assertThat(allByPatientIdAndDoctor.size()).isEqualTo(2);
    }

    @Test
    public void givenTreatmentPlan_whenSave_thenReturnTreatmentPlanObject() {
        // given
        given(treatmentPlanRepository.save(treatmentPlan)).willReturn(treatmentPlan);
        // when
        TreatmentPlan savedTreatmentPlan = treatmentPlanService.save(treatmentPlan);
        // then
        assertThat(savedTreatmentPlan).isNotNull();
        assertThat(savedTreatmentPlan.getId()).isEqualTo(TREATMENT_PLAN_ID);
    }

    @Test
    public void givenTreatmentPlanId_whenGetById_thenReturnTreatmentPlanObject() {
        // given
        given(treatmentPlanRepository.findById(TREATMENT_PLAN_ID)).willReturn(Optional.of(treatmentPlan));
        // when
        TreatmentPlan byId = treatmentPlanService.getById(TREATMENT_PLAN_ID);
        // then
        assertThat(byId).isNotNull();
        assertThat(byId.getId()).isEqualTo(TREATMENT_PLAN_ID);
    }

    @Test
    public void givenTreatmentPlanId_whenDelete_thenRemoveTreatmentPlanObject() {
        // given
        given(treatmentPlanRepository.findById(TREATMENT_PLAN_ID)).willReturn(Optional.of(treatmentPlan));
        willDoNothing().given(treatmentPlanRepository).delete(treatmentPlan);
        // when
        treatmentPlanService.delete(TREATMENT_PLAN_ID);
        // then
        verify(treatmentPlanRepository, times(1)).delete(treatmentPlan);
    }

    @Test
    public void givenNonExistingId_whenGetById_thenReturnError() {
        // given
        given(treatmentPlanRepository.findById(NON_EXISTING_ID)).willReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> treatmentPlanService.getById(NON_EXISTING_ID))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessage("Treatment Plan with id: 99 not found.");

    }


}