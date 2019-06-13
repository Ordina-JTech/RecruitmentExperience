package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Applicant;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import org.springframework.stereotype.Component;

@Component
public class ToApplicantEntityMapper implements Mapper<Applicant, ApplicantEntity> {

    @Override
    public ApplicantEntity map(Applicant input) {
        return ApplicantEntity.builder()
                .id(input.getId())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .email(input.getEmail())
                .resumeLink(input.getResumeLink())
                .build();
    }
}
