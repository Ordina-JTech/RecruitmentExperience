package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.ApplicantModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ToApplicantModelMapper implements Mapper<Applicant, ApplicantModel> {

    @Override
    public ApplicantModel map(Applicant input) {
        final ApplicantModel applicantModel = new ApplicantModel();
        applicantModel.setId(input.getId());
        applicantModel.setEmail(input.getEmail());
        applicantModel.setFirstName(input.getFirstName());
        applicantModel.setPrefix(input.getPrefix());
        applicantModel.setLastName(input.getLastName());
        applicantModel.setPhoneNumber(input.getPhoneNumber());
        applicantModel.setResumeLink(input.getResumeLink());
        return applicantModel;
    }
}
