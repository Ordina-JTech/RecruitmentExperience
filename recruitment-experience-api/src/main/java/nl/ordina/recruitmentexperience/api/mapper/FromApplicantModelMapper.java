package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.ApplicantModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Applicant;
import org.springframework.stereotype.Component;

@Component
public class FromApplicantModelMapper implements Mapper<ApplicantModel, Applicant> {

    @Override
    public Applicant map(ApplicantModel input) {
        return Applicant.builder()
                .id(input.getId())
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .resumeLink(input.getResumeLink())
                .phoneNumber(input.getPhoneNumber())
                .build();
    }
}
