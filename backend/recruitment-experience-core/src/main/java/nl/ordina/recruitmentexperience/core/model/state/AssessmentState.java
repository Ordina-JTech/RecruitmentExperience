package nl.ordina.recruitmentexperience.core.model.state;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.ASSESSMENT;

@Slf4j
public class AssessmentState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new OutlineState());
        // Do stuff for this state
        log.info(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));
    }

    @Override
    public ApplicationState toEnum() {
        return ASSESSMENT;
    }
}
