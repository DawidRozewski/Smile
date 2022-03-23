package pl.smile.SmileApp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.entity.Patient;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.PatientRepository;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createDoctor;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createPatient;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
    public static final String PATIENT_PESEL = "87021318486";
    public static final long NON_EXISTING_ID = 99L;
    public static final long PATIENT_ID = 0L;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Principal principal;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
        patient = createPatient(doctor);
    }

    @Test
    public void givenPatientObject_whenSave_thenReturnPatientObject() {
        // given
        given(patientRepository.save(patient)).willReturn(patient);
        // when
        Patient savedPatient = patientService.save(patient);
        // then
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getId()).isEqualTo(patient.getId());
    }

    @Test
    public void givenPesel_whenListAll_thenReturnPatientObject() {
        // given
        given(patientRepository.findAllByPesel(PATIENT_PESEL)).willReturn(Collections.singletonList(patient));
        // when
        List<Patient> patientByPesel = patientService.listAll(PATIENT_PESEL, doctor);
        // then
        assertThat(patientByPesel).isNotNull();
        assertThat(patientByPesel.size()).isEqualTo(1);
        assertThat(patientByPesel.get(0).getPesel()).isEqualTo(PATIENT_PESEL);
    }

    @Test
    public void givenDoctor_whenListAll_thenReturnListOfPatientsByDoctor() {
        // given
        Patient patient1 = patient = Patient.builder()
                .firstName("Ivan")
                .lastName("Zaporozec")
                .pesel("7891042151")
                .phoneNumber("789024803")
                .email("averoza@o2.pl")
                .password("test")
                .repassword("test")
                .doctor(doctor)
                .processingOfPersonalData(true)
                .build();

        given(patientRepository.findAllByDoctor(doctor)).willReturn(Arrays.asList(patient, patient1));
        // when
        List<Patient> patientsByDoctor = patientService.listAll(null, doctor);
        // then
        assertThat(patientsByDoctor).isNotNull();
        assertThat(patientsByDoctor.size()).isEqualTo(2);
    }

    @Test
    public void givenPatientId_whenGetById_thenReturnPatientObject() {
        // given
        given(patientRepository.findById(PATIENT_ID)).willReturn(Optional.of(patient));
        // when
        Patient patientById = patientService.getById(PATIENT_ID);
        // then
        assertThat(patientById).isNotNull();
        assertThat(patientById.getId()).isEqualTo(PATIENT_ID);
    }

    @Test
    public void givenNonExistingId_whenGetById_thenThrowError() {
        // given
        given(patientRepository.findById(NON_EXISTING_ID)).willReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> patientService.getById(NON_EXISTING_ID))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessage("Patient with id: 99 not found.");
    }

    @Test
    public void givenPatientId_whenUpdate_thenReturnPatientObject() {
        // given
        given(patientRepository.findById(PATIENT_ID)).willReturn(Optional.of(patient));
        patient.setFirstName("UpdatedName");
        patient.setLastName("UpdatedLastName");
        // when
        Patient updatedPatient = patientService.update(patient);
        // then
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getFirstName()).isEqualTo("UpdatedName");
        assertThat(updatedPatient.getLastName()).isEqualTo("UpdatedLastName");
    }

    @Test
    public void givenPatientList_whenGetAllPatients_thenListOfPatients() {
        // given
        Patient patient1 = patient = Patient.builder()
                .firstName("Ivan")
                .lastName("Zaporozec")
                .pesel("7891042151")
                .phoneNumber("789024803")
                .email("averoza@o2.pl")
                .password("test")
                .repassword("test")
                .doctor(doctor)
                .processingOfPersonalData(true)
                .build();

        given(patientRepository.findAll()).willReturn(Arrays.asList(patient, patient1));
        // when
        List<Patient> patientList = patientService.getAllPatients();
        // then
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isEqualTo(2);
    }

    @Test
    public void givenIncorrectPatientPasswords_whenComparePassword_thenReturnToView() {
        // given
        patient.setPassword("test");
        patient.setRepassword("test2");
        // when
        String result = patientService.comparePasswords(patient, bindingResult);
        // then
        assertThat(result).isEqualTo("/form/register");
    }

    @Test
    public void givenCorrectPasswords_whenComparePassword_thenReturnNull() {
        // given
        // when
        String result = patientService.comparePasswords(patient, bindingResult);
        // then
        assertThat(result).isNull();
    }

    @Test
    public void givenPrincipal_whenGetPatient_thenReturnPatientObject() {
        // given
        given(patientRepository.getByEmail(anyString())).willReturn(patient);
        given(principal.getName()).willReturn("averoza@o2.pl");
        // when
        Patient patientByEmail = patientService.getPatient(principal);
        // then
        assertThat(patientByEmail.getId()).isEqualTo(PATIENT_ID);
        assertThat(patientByEmail.getEmail()).isEqualTo(patient.getEmail());
    }


}