package nl.ordina.recruitmentexperience.api.application.mapper;

import nl.ordina.recruitmentexperience.api.application.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.core.application.model.ApplicationState;

import java.util.EnumMap;

public class ToApplicationStateModelMapper extends EnumMap<ApplicationState, ApplicationStateModel> {

    public ToApplicationStateModelMapper(Class<ApplicationState> keyType) {
        super(keyType);
        put(ApplicationState.RESUME_SENT, ApplicationStateModel.RESUME_SENT);
        put(ApplicationState.INTERVIEW_INVITED, ApplicationStateModel.INTERVIEW_INVITED);
        put(ApplicationState.FIRST_INTERVIEW, ApplicationStateModel.FIRST_INTERVIEW);
        put(ApplicationState.SECOND_INTERVIEW, ApplicationStateModel.SECOND_INTERVIEW);
        put(ApplicationState.ASSESSMENT, ApplicationStateModel.ASSESSMENT);
        put(ApplicationState.OUTLINE, ApplicationStateModel.OUTLINE);
        put(ApplicationState.CONTRACT, ApplicationStateModel.CONTRACT);
    }
}
