package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;

import java.util.EnumMap;

public class ToApplicationStateModelMapper extends EnumMap<ApplicationState, ApplicationStateModel> {

    public ToApplicationStateModelMapper(Class<ApplicationState> keyType) {
        super(keyType);
        put(ApplicationState.NEW, ApplicationStateModel.NEW);
        put(ApplicationState.INVITED, ApplicationStateModel.INVITED);
        put(ApplicationState.FIRST_INTERVIEW, ApplicationStateModel.FIRST_INTERVIEW);
        put(ApplicationState.SECOND_INTERVIEW, ApplicationStateModel.SECOND_INTERVIEW);
        put(ApplicationState.ASSESSMENT, ApplicationStateModel.ASSESSMENT);
        put(ApplicationState.OUTLINE, ApplicationStateModel.OUTLINE);
        put(ApplicationState.CONTRACT, ApplicationStateModel.CONTRACT);
    }
}
