package pl.smile.SmileApp.service.impl;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.smile.SmileApp.entity.Doctor;
import pl.smile.SmileApp.exception.ResourceNotFound;
import pl.smile.SmileApp.repository.DoctorRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static pl.smile.SmileApp.service.impl.ServiceHelper.createDoctor;

@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {
    public static final long NON_EXISTING_DOCTOR_ID = 999L;
    public static final long DOCTOR_ID = 0L;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private Principal principal;
    @InjectMocks
    private DoctorServiceImpl doctorService;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = createDoctor();
    }

    @Test
    public void givenDoctorObject_whenSave_thenReturnDoctorObject() {
        // given
        given(doctorRepository.save(doctor)).willReturn(doctor);
        // when
        Doctor savedDoctor = doctorService.save(doctor);
        // then
        assertThat(savedDoctor).isNotNull();
        assertThat(savedDoctor.getId()).isEqualTo(DOCTOR_ID);
    }

    @Test
    public void givenDoctorObject_whenUpdate_thenReturnUpdatedDoctorObject() {
        // given
        Doctor updatedDoctor = Doctor.builder()
                .firstName("JanEDITED")
                .lastName("KowalskiEDITED")
                .password("test")
                .repassword("test")
                .phoneNumber("789024803")
                .build();

        given(doctorRepository.findById(DOCTOR_ID)).willReturn(Optional.of(doctor));
        given(doctorRepository.save(doctor)).willReturn(updatedDoctor);
        // when
        Doctor updatedDoc = doctorService.update(doctor);
        // then
        assertThat(updatedDoc).isNotNull();
        assertThat(updatedDoc.getFirstName()).isEqualTo(updatedDoctor.getFirstName());
        assertThat(updatedDoc.getLastName()).isEqualTo(updatedDoctor.getLastName());
    }

    @Test
    public void givenNonExistingDoctor_whenUpdated_thenReturnException() {
        // given
        doctor.setId(NON_EXISTING_DOCTOR_ID);
        // when
        assertThrows(ResourceNotFound.class, () -> {
            doctorService.update(doctor);
        });
        // then
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    public void given_whenGetAllDoctors_thenReturnListOfDoctors() {
        // given
        Doctor doctor1 = Doctor.builder()
                .firstName("JanEDITED")
                .lastName("KowalskiEDITED")
                .password("test")
                .repassword("test")
                .phoneNumber("789024803")
                .build();
        given(doctorRepository.findAll()).willReturn(Arrays.asList(doctor, doctor1));
        // when
        List<Doctor> listOfDoctors = doctorService.getAllDoctors();
        // then
        assertThat(listOfDoctors).isNotNull();
        assertThat(listOfDoctors.size()).isEqualTo(2);
    }

    @Test
    public void givenPrincipal_whenGetDoctor_thenReturnDoctorObject() {
        // given
        given(doctorRepository.getByEmail(anyString())).willReturn(doctor);
        given(principal.getName()).willReturn("kowalski@gmail.com");
        // when
        Doctor doctorByPrincipal = doctorService.getDoctor(principal);
        // then
        assertThat(doctorByPrincipal.getId()).isEqualTo(DOCTOR_ID);
    }

    @Test
    public void givenCorrectPasswords_whenComparePassword_thenReturnNull() {
        // given
        // when
        String result = doctorService.comparePasswords(doctor, bindingResult);
        // then
        assertThat(result).isNull();
    }

    @Test
    public void givenIncorrectDoctorPasswords_whenComparePassword_thenReturnToView() {
        // given
        doctor.setPassword("test");
        doctor.setRepassword("test2");
        // when
        String result = doctorService.comparePasswords(doctor, bindingResult);
        // then
        assertThat(result).isEqualTo("/admin/add");
    }





}