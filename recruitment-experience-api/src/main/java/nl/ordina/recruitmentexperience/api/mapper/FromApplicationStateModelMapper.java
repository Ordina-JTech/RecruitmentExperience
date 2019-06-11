package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.core.model.ApplicationState;

import java.util.EnumMap;

public class FromApplicationStateModelMapper extends EnumMap<ApplicationStateModel, ApplicationState> {
    public FromApplicationStateModelMapper(Class<ApplicationStateModel> keyType) {
        super(keyType);
        put(ApplicationStateModel.NEW, ApplicationState.NEW);
        put(ApplicationStateModel.INVITED, ApplicationState.INVITED);
        put(ApplicationStateModel.FIRST_INTERVIEW, ApplicationState.FIRST_INTERVIEW);
        put(ApplicationStateModel.SECOND_INTERVIEW, ApplicationState.SECOND_INTERVIEW);
        put(ApplicationStateModel.ASSESSMENT, ApplicationState.ASSESSMENT);
        put(ApplicationStateModel.OUTLINE, ApplicationState.OUTLINE);
        put(ApplicationStateModel.CONTRACT, ApplicationState.CONTRACT);
    }
}
