package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Applicant;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import org.springframework.stereotype.Component;

@Component
public class FromApplicantEntityMapper implements Mapper<ApplicantEntity, Applicant> {

    @Override
    public Applicant map(ApplicantEntity input) {
        return Applicant.builder()
                .id(input.getId())
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .resumeLink(input.getResumeLink())
                .build();
    }
}
