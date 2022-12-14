package com.erecruitment.services;

import com.erecruitment.dtos.requests.ApplicantEditProfileRequest;
import com.erecruitment.dtos.requests.EducationRequest;
import com.erecruitment.dtos.requests.ExperienceRequest;
import com.erecruitment.dtos.requests.SkillApplicantRequest;
import com.erecruitment.dtos.response.ApplicantProfileResponse;
import com.erecruitment.entities.*;
import com.erecruitment.exceptions.DataNotFoundException;
import com.erecruitment.exceptions.ValidationErrorException;
import com.erecruitment.repositories.*;
import com.erecruitment.services.interfaces.IFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicantService.class})
@ExtendWith(SpringExtension.class)
class ApplicantServiceTest {
    @MockBean
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantService applicantService;

    @MockBean
    private EducationRepository educationRepository;

    @MockBean
    private ExperienceRepository experienceRepository;

    @MockBean
    private IFileService iFileService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private SkillRepository skillRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ApplicantService#updateUserDetail(User, ApplicantEditProfileRequest)}
     */
    @Test
    void testUpdateUserDetail() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any())).thenReturn(applicantProfileResponse);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);
        when(userRepository.save(any())).thenReturn(user1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        ApplicantEditProfileRequest applicantEditProfileRequest = new ApplicantEditProfileRequest();
        applicantEditProfileRequest.setAddress("42 Main St");
        applicantEditProfileRequest.setBio("Bio");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantEditProfileRequest.setDob(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        applicantEditProfileRequest.setName("Name");
        applicantEditProfileRequest.setPhoneNumber("4105551212");
        ApplicantProfileResponse actualUpdateUserDetailResult = applicantService.updateUserDetail(user2,
                applicantEditProfileRequest);
        assertSame(applicantProfileResponse, actualUpdateUserDetailResult);
        assertEquals(123L, actualUpdateUserDetailResult.getUserId().longValue());
        assertEquals("4105551212", actualUpdateUserDetailResult.getPhoneNumber());
        assertEquals("Name", actualUpdateUserDetailResult.getName());
        assertTrue(actualUpdateUserDetailResult.getExperiences().isEmpty());
        assertEquals("jane.doe@example.org", actualUpdateUserDetailResult.getEmail());
        assertTrue(actualUpdateUserDetailResult.getEducations().isEmpty());
        assertEquals("https://example.org/example", actualUpdateUserDetailResult.getCvURL());
        assertEquals("https://example.org/example", actualUpdateUserDetailResult.getAvatarURL());
        verify(applicantRepository, atLeast(1)).findByOwnedBy(any());
        verify(educationRepository).findByOwnedBy(any());
        verify(experienceRepository).findByOwnedBy(any());
        verify(iFileService, atLeast(1)).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
        verify(userRepository).save(any());
        assertEquals("4105551212", user2.getPhoneNumber());
        assertEquals("Name", user2.getName());
    }

    /**
     * Method under test: {@link ApplicantService#updateUserDetail(User, ApplicantEditProfileRequest)}
     */
    @Test
    void testUpdateUserDetail2() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(userRepository.save(any())).thenThrow(new ValidationErrorException("An error occurred"));

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        ApplicantEditProfileRequest applicantEditProfileRequest = new ApplicantEditProfileRequest();
        applicantEditProfileRequest.setAddress("42 Main St");
        applicantEditProfileRequest.setBio("Bio");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantEditProfileRequest.setDob(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        applicantEditProfileRequest.setName("Name");
        applicantEditProfileRequest.setPhoneNumber("4105551212");
        assertThrows(ValidationErrorException.class,
                () -> applicantService.updateUserDetail(user1, applicantEditProfileRequest));
        verify(applicantRepository).findByOwnedBy(any());
        verify(userRepository).save(any());
    }

    /**
     * Method under test: {@link ApplicantService#addEducation(User, EducationRequest)}
     */
    @Test
    void testAddEducation() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.save(any())).thenThrow(new ValidationErrorException("An error occurred"));

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Education education = new Education();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        education.setDegree(Degree.HIGH_SCHOOL);
        education.setDeleted(true);
        education.setDescription("The characteristics of someone or something");
        education.setEducationId(123L);
        education.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setEndDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        education.setMajor("Major");
        education.setOwnedBy(user1);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setStartDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        when(modelMapper.map(any(), (Class<Education>) any())).thenReturn(education);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        EducationRequest educationRequest = new EducationRequest();
        educationRequest.setDegree(Degree.HIGH_SCHOOL);
        educationRequest.setDescription("The characteristics of someone or something");
        educationRequest.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setEndDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        educationRequest.setMajor("Major");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setStartDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(ValidationErrorException.class, () -> applicantService.addEducation(user2, educationRequest));
        verify(educationRepository).save(any());
        verify(modelMapper).map(any(), (Class<Education>) any());
    }

    /**
     * Method under test: {@link ApplicantService#updateEducation(Long, EducationRequest, User)}
     */
    @Test
    void testUpdateEducation() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Education education = new Education();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        education.setDegree(Degree.HIGH_SCHOOL);
        education.setDeleted(true);
        education.setDescription("The characteristics of someone or something");
        education.setEducationId(123L);
        education.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setEndDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        education.setMajor("Major");
        education.setOwnedBy(user1);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setStartDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Education> ofResult1 = Optional.of(education);
        when(educationRepository.save(any())).thenThrow(new ValidationErrorException("An error occurred"));
        when(educationRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);

        EducationRequest educationRequest = new EducationRequest();
        educationRequest.setDegree(Degree.HIGH_SCHOOL);
        educationRequest.setDescription("The characteristics of someone or something");
        educationRequest.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setEndDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        educationRequest.setMajor("Major");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setStartDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        assertThrows(ValidationErrorException.class,
                () -> applicantService.updateEducation(123L, educationRequest, user2));
        verify(educationRepository).save(any());
        verify(educationRepository).findByIdaAndOwnedBy(any(), any());
    }

    /**
     * Method under test: {@link ApplicantService#updateEducation(Long, EducationRequest, User)}
     */
    @Test
    void testUpdateEducation2() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Education education = new Education();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        education.setDegree(Degree.HIGH_SCHOOL);
        education.setDeleted(true);
        education.setDescription("The characteristics of someone or something");
        education.setEducationId(123L);
        education.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setEndDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        education.setMajor("Major");
        education.setOwnedBy(user1);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setStartDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Education> ofResult1 = Optional.of(education);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        Education education1 = new Education();
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education1.setCreatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        education1.setDegree(Degree.HIGH_SCHOOL);
        education1.setDeleted(true);
        education1.setDescription("The characteristics of someone or something");
        education1.setEducationId(123L);
        education1.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education1.setEndDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        education1.setMajor("Major");
        education1.setOwnedBy(user2);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education1.setStartDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education1.setUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        when(educationRepository.save(any())).thenReturn(education1);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(educationRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        EducationRequest educationRequest = new EducationRequest();
        educationRequest.setDegree(Degree.HIGH_SCHOOL);
        educationRequest.setDescription("The characteristics of someone or something");
        educationRequest.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setEndDate(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        educationRequest.setMajor("Major");
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        educationRequest.setStartDate(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setJoinedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastLogin(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPhoneNumber("4105551212");
        user3.setRole(RoleName.USER);
        user3.setUserId(123L);
        ApplicantProfileResponse actualUpdateEducationResult = applicantService.updateEducation(123L, educationRequest,
                user3);
        assertSame(applicantProfileResponse, actualUpdateEducationResult);
        assertEquals(123L, actualUpdateEducationResult.getUserId().longValue());
        assertEquals("4105551212", actualUpdateEducationResult.getPhoneNumber());
        assertEquals("Name", actualUpdateEducationResult.getName());
        assertTrue(actualUpdateEducationResult.getExperiences().isEmpty());
        assertEquals("jane.doe@example.org", actualUpdateEducationResult.getEmail());
        assertTrue(actualUpdateEducationResult.getEducations().isEmpty());
        assertEquals("https://example.org/example", actualUpdateEducationResult.getCvURL());
        assertEquals("https://example.org/example", actualUpdateEducationResult.getAvatarURL());
        verify(applicantRepository).findByOwnedBy(any());
        verify(educationRepository).save(any());
        verify(educationRepository).findByIdaAndOwnedBy(any(), any());
        verify(educationRepository).findByOwnedBy(any());
        verify(experienceRepository).findByOwnedBy(any());
        verify(iFileService, atLeast(1)).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#deleteEducation(Long, User)}
     */
    @Test
    void testDeleteEducation() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Education education = new Education();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        education.setDegree(Degree.HIGH_SCHOOL);
        education.setDeleted(true);
        education.setDescription("The characteristics of someone or something");
        education.setEducationId(123L);
        education.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setEndDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        education.setMajor("Major");
        education.setOwnedBy(user1);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setStartDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Education> ofResult1 = Optional.of(education);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        doNothing().when(educationRepository).deleteById(any());
        when(educationRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        ApplicantProfileResponse actualDeleteEducationResult = applicantService.deleteEducation(123L, user2);
        assertSame(applicantProfileResponse, actualDeleteEducationResult);
        assertEquals(123L, actualDeleteEducationResult.getUserId().longValue());
        assertEquals("4105551212", actualDeleteEducationResult.getPhoneNumber());
        assertEquals("Name", actualDeleteEducationResult.getName());
        assertTrue(actualDeleteEducationResult.getExperiences().isEmpty());
        assertEquals("jane.doe@example.org", actualDeleteEducationResult.getEmail());
        assertTrue(actualDeleteEducationResult.getEducations().isEmpty());
        assertEquals("https://example.org/example", actualDeleteEducationResult.getCvURL());
        assertEquals("https://example.org/example", actualDeleteEducationResult.getAvatarURL());
        verify(applicantRepository).findByOwnedBy(any());
        verify(educationRepository).findByIdaAndOwnedBy(any(), any());
        verify(educationRepository).findByOwnedBy(any());
        verify(educationRepository).deleteById(any());
        verify(experienceRepository).findByOwnedBy(any());
        verify(iFileService, atLeast(1)).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#deleteEducation(Long, User)}
     */
    @Test
    void testDeleteEducation2() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Education education = new Education();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        education.setDegree(Degree.HIGH_SCHOOL);
        education.setDeleted(true);
        education.setDescription("The characteristics of someone or something");
        education.setEducationId(123L);
        education.setEducationName("Education Name");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setEndDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        education.setMajor("Major");
        education.setOwnedBy(user1);
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setStartDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        education.setUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Education> ofResult1 = Optional.of(education);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        doNothing().when(educationRepository).deleteById(any());
        when(educationRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenThrow(new ValidationErrorException("An error occurred"));

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        assertThrows(ValidationErrorException.class, () -> applicantService.deleteEducation(123L, user2));
        verify(applicantRepository).findByOwnedBy(any());
        verify(educationRepository).findByIdaAndOwnedBy(any(), any());
        verify(educationRepository).deleteById(any());
        verify(iFileService).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#addExperience(User, ExperienceRequest)}
     */
    @Test
    void testAddExperience() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(experienceRepository.save(any())).thenThrow(new ValidationErrorException("An error occurred"));

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Experience experience = new Experience();
        experience.setCorporateName("Corporate Name");
        experience.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setEndDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        experience.setExperienceId(123L);
        experience.setOwnedBy(user1);
        experience.setPosition("Position");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setStartDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        when(modelMapper.map(any(), (Class<Experience>) any())).thenReturn(experience);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        ExperienceRequest experienceRequest = new ExperienceRequest();
        experienceRequest.setCorporateName("Corporate Name");
        experienceRequest.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experienceRequest.setEndDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        experienceRequest.setPosition("Position");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experienceRequest.setStartDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(ValidationErrorException.class, () -> applicantService.addExperience(user2, experienceRequest));
        verify(experienceRepository).save(any());
        verify(modelMapper).map(any(), (Class<Experience>) any());
    }

    /**
     * Method under test: {@link ApplicantService#updateExperience(Long, ExperienceRequest, User)}
     */
    @Test
    void testUpdateExperience() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Experience experience = new Experience();
        experience.setCorporateName("Corporate Name");
        experience.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setEndDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        experience.setExperienceId(123L);
        experience.setOwnedBy(user1);
        experience.setPosition("Position");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setStartDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Experience> ofResult1 = Optional.of(experience);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        Experience experience1 = new Experience();
        experience1.setCorporateName("Corporate Name");
        experience1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience1.setEndDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        experience1.setExperienceId(123L);
        experience1.setOwnedBy(user2);
        experience1.setPosition("Position");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience1.setStartDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        when(experienceRepository.save(any())).thenReturn(experience1);
        when(experienceRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenThrow(new ValidationErrorException("An error occurred"));

        ExperienceRequest experienceRequest = new ExperienceRequest();
        experienceRequest.setCorporateName("Corporate Name");
        experienceRequest.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experienceRequest.setEndDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        experienceRequest.setPosition("Position");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experienceRequest.setStartDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setJoinedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastLogin(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPhoneNumber("4105551212");
        user3.setRole(RoleName.USER);
        user3.setUserId(123L);
        assertThrows(ValidationErrorException.class,
                () -> applicantService.updateExperience(123L, experienceRequest, user3));
        verify(applicantRepository).findByOwnedBy(any());
        verify(experienceRepository).save(any());
        verify(experienceRepository).findByIdaAndOwnedBy(any(), any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#deleteExperience(Long, User)}
     */
    @Test
    void testDeleteExperience() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Experience experience = new Experience();
        experience.setCorporateName("Corporate Name");
        experience.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setEndDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        experience.setExperienceId(123L);
        experience.setOwnedBy(user1);
        experience.setPosition("Position");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setStartDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Experience> ofResult1 = Optional.of(experience);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        doNothing().when(experienceRepository).deleteById(any());
        when(experienceRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        ApplicantProfileResponse actualDeleteExperienceResult = applicantService.deleteExperience(123L, user2);
        assertSame(applicantProfileResponse, actualDeleteExperienceResult);
        assertEquals(123L, actualDeleteExperienceResult.getUserId().longValue());
        assertEquals("4105551212", actualDeleteExperienceResult.getPhoneNumber());
        assertEquals("Name", actualDeleteExperienceResult.getName());
        assertTrue(actualDeleteExperienceResult.getExperiences().isEmpty());
        assertEquals("jane.doe@example.org", actualDeleteExperienceResult.getEmail());
        assertTrue(actualDeleteExperienceResult.getEducations().isEmpty());
        assertEquals("https://example.org/example", actualDeleteExperienceResult.getCvURL());
        assertEquals("https://example.org/example", actualDeleteExperienceResult.getAvatarURL());
        verify(applicantRepository).findByOwnedBy(any());
        verify(educationRepository).findByOwnedBy(any());
        verify(experienceRepository).findByIdaAndOwnedBy(any(), any());
        verify(experienceRepository).findByOwnedBy(any());
        verify(experienceRepository).deleteById(any());
        verify(iFileService, atLeast(1)).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#deleteExperience(Long, User)}
     */
    @Test
    void testDeleteExperience2() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Experience experience = new Experience();
        experience.setCorporateName("Corporate Name");
        experience.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setEndDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        experience.setExperienceId(123L);
        experience.setOwnedBy(user1);
        experience.setPosition("Position");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setStartDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Experience> ofResult1 = Optional.of(experience);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        doNothing().when(experienceRepository).deleteById(any());
        when(experienceRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(iFileService.generateUrlFile(any())).thenThrow(new ValidationErrorException("An error occurred"));

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        assertThrows(ValidationErrorException.class, () -> applicantService.deleteExperience(123L, user2));
        verify(applicantRepository).findByOwnedBy(any());
        verify(experienceRepository).findByIdaAndOwnedBy(any(), any());
        verify(experienceRepository).deleteById(any());
        verify(iFileService).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
    }

    /**
     * Method under test: {@link ApplicantService#deleteExperience(Long, User)}
     */
    @Test
    void testDeleteExperience3() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);
        Applicant applicant = mock(Applicant.class);
        when(applicant.getAvatar()).thenThrow(new DataNotFoundException("An error occurred"));
        when(applicant.getCv()).thenThrow(new DataNotFoundException("An error occurred"));
        when(applicant.getOwnedBy()).thenReturn(user1);
        doNothing().when(applicant).setAddress(any());
        doNothing().when(applicant).setApplicantId(any());
        doNothing().when(applicant).setAvatar(any());
        doNothing().when(applicant).setBio(any());
        doNothing().when(applicant).setCv(any());
        doNothing().when(applicant).setDob(any());
        doNothing().when(applicant).setOwnedBy(any());
        doNothing().when(applicant).setPortofolioURL(any());
        doNothing().when(applicant).setSkills(any());
        doNothing().when(applicant).setCreatedAt(any());
        doNothing().when(applicant).setDeleted(anyBoolean());
        doNothing().when(applicant).setUpdatedAt(any());
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        Experience experience = new Experience();
        experience.setCorporateName("Corporate Name");
        experience.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setEndDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        experience.setExperienceId(123L);
        experience.setOwnedBy(user2);
        experience.setPosition("Position");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        experience.setStartDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Experience> ofResult1 = Optional.of(experience);
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        doNothing().when(experienceRepository).deleteById(any());
        when(experienceRepository.findByIdaAndOwnedBy(any(), any())).thenReturn(ofResult1);
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setJoinedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setLastLogin(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPhoneNumber("4105551212");
        user3.setRole(RoleName.USER);
        user3.setUserId(123L);
        assertThrows(DataNotFoundException.class, () -> applicantService.deleteExperience(123L, user3));
        verify(applicantRepository).findByOwnedBy(any());
        verify(applicant).getAvatar();
        verify(applicant, atLeast(1)).getOwnedBy();
        verify(applicant).setAddress(any());
        verify(applicant).setApplicantId(any());
        verify(applicant).setAvatar(any());
        verify(applicant).setBio(any());
        verify(applicant).setCv(any());
        verify(applicant).setDob(any());
        verify(applicant).setOwnedBy(any());
        verify(applicant).setPortofolioURL(any());
        verify(applicant).setSkills(any());
        verify(applicant).setCreatedAt(any());
        verify(applicant).setDeleted(anyBoolean());
        verify(applicant).setUpdatedAt(any());
        verify(experienceRepository).findByIdaAndOwnedBy(any(), any());
        verify(experienceRepository).deleteById(any());
        verify(modelMapper).map(any(), any());
    }

    /**
     * Method under test: {@link ApplicantService#addSkill(User, SkillApplicantRequest)}
     */
    @Test
    void testAddSkill() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);

        File file2 = new File();
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file2.setCreatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        file2.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file2.setDeleted(true);
        file2.setDisplayName("Display Name");
        file2.setFileId(123L);
        file2.setType("Type");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file2.setUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));

        File file3 = new File();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file3.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        file3.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file3.setDeleted(true);
        file3.setDisplayName("Display Name");
        file3.setFileId(123L);
        file3.setType("Type");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file3.setUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Applicant applicant1 = new Applicant();
        applicant1.setAddress("42 Main St");
        applicant1.setApplicantId(123L);
        applicant1.setAvatar(file2);
        applicant1.setBio("Bio");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setCreatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        applicant1.setCv(file3);
        applicant1.setDeleted(true);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setDob(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        applicant1.setOwnedBy(user1);
        applicant1.setPortofolioURL("https://example.org/example");
        applicant1.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        when(applicantRepository.save(any())).thenReturn(applicant1);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenThrow(new ValidationErrorException("An error occurred"));

        SkillEntity skillEntity = new SkillEntity();
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setCreatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        skillEntity.setDeleted(true);
        skillEntity.setSkillId(123L);
        skillEntity.setSkillName("Skill Name");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setUpdatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));

        SkillEntity skillEntity1 = new SkillEntity();
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity1.setCreatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        skillEntity1.setDeleted(true);
        skillEntity1.setSkillId(123L);
        skillEntity1.setSkillName("Skill Name");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity1.setUpdatedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<SkillEntity> ofResult1 = Optional.of(skillEntity1);
        when(skillRepository.save(any())).thenReturn(skillEntity);
        when(skillRepository.findBySkillName(any())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);

        SkillApplicantRequest skillApplicantRequest = new SkillApplicantRequest();
        skillApplicantRequest.setSkillName("Skill Name");
        assertThrows(ValidationErrorException.class, () -> applicantService.addSkill(user2, skillApplicantRequest));
        verify(applicantRepository).save(any());
        verify(applicantRepository, atLeast(1)).findByOwnedBy(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
        verify(skillRepository).findBySkillName(any());
    }

    /**
     * Method under test: {@link ApplicantService#removeSkill(Long, User)}
     */
    @Test
    void testRemoveSkill() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.save(any())).thenThrow(new ValidationErrorException("An error occurred"));
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);

        SkillEntity skillEntity = new SkillEntity();
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setCreatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        skillEntity.setDeleted(true);
        skillEntity.setSkillId(123L);
        skillEntity.setSkillName("Skill Name");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<SkillEntity> ofResult1 = Optional.of(skillEntity);
        when(skillRepository.findById(any())).thenReturn(ofResult1);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);
        assertThrows(ValidationErrorException.class, () -> applicantService.removeSkill(123L, user1));
        verify(applicantRepository).save(any());
        verify(applicantRepository).findByOwnedBy(any());
        verify(skillRepository).findById(any());
    }

    /**
     * Method under test: {@link ApplicantService#removeSkill(Long, User)}
     */
    @Test
    void testRemoveSkill2() throws UnsupportedEncodingException {
        File file = new File();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        file.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file.setDeleted(true);
        file.setDisplayName("Display Name");
        file.setFileId(123L);
        file.setType("Type");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        File file1 = new File();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        file1.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file1.setDeleted(true);
        file1.setDisplayName("Display Name");
        file1.setFileId(123L);
        file1.setType("Type");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));

        User user = new User();
        user.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setJoinedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setLastLogin(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber("4105551212");
        user.setRole(RoleName.USER);
        user.setUserId(123L);

        Applicant applicant = new Applicant();
        applicant.setAddress("42 Main St");
        applicant.setApplicantId(123L);
        applicant.setAvatar(file);
        applicant.setBio("Bio");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setCv(file1);
        applicant.setDeleted(true);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setDob(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        applicant.setOwnedBy(user);
        applicant.setPortofolioURL("https://example.org/example");
        applicant.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant.setUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Applicant> ofResult = Optional.of(applicant);

        File file2 = new File();
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file2.setCreatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        file2.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file2.setDeleted(true);
        file2.setDisplayName("Display Name");
        file2.setFileId(123L);
        file2.setType("Type");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file2.setUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));

        File file3 = new File();
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file3.setCreatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        file3.setData("AAAAAAAA".getBytes(StandardCharsets.UTF_8));
        file3.setDeleted(true);
        file3.setDisplayName("Display Name");
        file3.setFileId(123L);
        file3.setType("Type");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        file3.setUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setJoinedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setLastLogin(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNumber("4105551212");
        user1.setRole(RoleName.USER);
        user1.setUserId(123L);

        Applicant applicant1 = new Applicant();
        applicant1.setAddress("42 Main St");
        applicant1.setApplicantId(123L);
        applicant1.setAvatar(file2);
        applicant1.setBio("Bio");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setCreatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        applicant1.setCv(file3);
        applicant1.setDeleted(true);
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setDob(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        applicant1.setOwnedBy(user1);
        applicant1.setPortofolioURL("https://example.org/example");
        applicant1.setSkills(new HashSet<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicant1.setUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        when(applicantRepository.save(any())).thenReturn(applicant1);
        when(applicantRepository.findByOwnedBy(any())).thenReturn(ofResult);
        when(educationRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(experienceRepository.findByOwnedBy(any())).thenReturn(new HashSet<>());
        when(iFileService.generateUrlFile(any())).thenReturn("https://example.org/example");

        ApplicantProfileResponse applicantProfileResponse = new ApplicantProfileResponse();
        applicantProfileResponse.setAddress("42 Main St");
        applicantProfileResponse.setAvatarFileId("42");
        applicantProfileResponse.setAvatarURL("https://example.org/example");
        applicantProfileResponse.setBio("Bio");
        applicantProfileResponse.setCvFileId("42");
        applicantProfileResponse.setCvURL("https://example.org/example");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        applicantProfileResponse.setDob(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        applicantProfileResponse.setEducations(new HashSet<>());
        applicantProfileResponse.setEmail("jane.doe@example.org");
        applicantProfileResponse.setExperiences(new HashSet<>());
        applicantProfileResponse.setName("Name");
        applicantProfileResponse.setPhoneNumber("4105551212");
        applicantProfileResponse.setSkills(new HashSet<>());
        applicantProfileResponse.setUserId(123L);
        when(modelMapper.map(any(), (Class<ApplicantProfileResponse>) any()))
                .thenReturn(applicantProfileResponse);

        SkillEntity skillEntity = new SkillEntity();
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setCreatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        skillEntity.setDeleted(true);
        skillEntity.setSkillId(123L);
        skillEntity.setSkillName("Skill Name");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        skillEntity.setUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<SkillEntity> ofResult1 = Optional.of(skillEntity);
        when(skillRepository.findById(any())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setJoinedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setLastLogin(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber("4105551212");
        user2.setRole(RoleName.USER);
        user2.setUserId(123L);
        ApplicantProfileResponse actualRemoveSkillResult = applicantService.removeSkill(123L, user2);
        assertSame(applicantProfileResponse, actualRemoveSkillResult);
        assertEquals(123L, actualRemoveSkillResult.getUserId().longValue());
        assertEquals("4105551212", actualRemoveSkillResult.getPhoneNumber());
        assertEquals("Name", actualRemoveSkillResult.getName());
        assertTrue(actualRemoveSkillResult.getExperiences().isEmpty());
        assertEquals("jane.doe@example.org", actualRemoveSkillResult.getEmail());
        assertTrue(actualRemoveSkillResult.getEducations().isEmpty());
        assertEquals("https://example.org/example", actualRemoveSkillResult.getCvURL());
        assertEquals("https://example.org/example", actualRemoveSkillResult.getAvatarURL());
        verify(applicantRepository).save(any());
        verify(applicantRepository, atLeast(1)).findByOwnedBy(any());
        verify(educationRepository).findByOwnedBy(any());
        verify(experienceRepository).findByOwnedBy(any());
        verify(iFileService, atLeast(1)).generateUrlFile(any());
        verify(modelMapper).map(any(), (Class<ApplicantProfileResponse>) any());
        verify(skillRepository).findById(any());
    }

}

