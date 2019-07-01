package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.state.AssessmentState;
import nl.ordina.recruitmentexperience.core.model.state.ContractState;
import nl.ordina.recruitmentexperience.core.model.state.FirstInterviewState;
import nl.ordina.recruitmentexperience.core.model.state.InvitedState;
import nl.ordina.recruitmentexperience.core.model.state.NewState;
import nl.ordina.recruitmentexperience.core.model.state.OutlineState;
import nl.ordina.recruitmentexperience.core.model.state.SecondInterviewState;
import nl.ordina.recruitmentexperience.core.model.state.SignedState;
import nl.ordina.recruitmentexperience.core.model.state.State;
import org.springframework.stereotype.Component;

@Component
public class FromApplicationStateModelMapper implements Mapper<ApplicationStateModel, State> {

    @Override
    public State map(ApplicationStateModel input) {
        switch (input) {
            case NEW:
                return new NewState();
            case INVITED:
                return new InvitedState();
            case FIRST_INTERVIEW:
                return new FirstInterviewState();
            case SECOND_INTERVIEW:
                return new SecondInterviewState();
            case ASSESSMENT:
                return new AssessmentState();
            case OUTLINE:
                return new OutlineState();
            case CONTRACT:
                return new ContractState();
            case SIGNED:
                return new SignedState();
        }
        throw new IllegalStateException("Unknown state");
    }
}