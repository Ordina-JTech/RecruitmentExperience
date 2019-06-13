package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.ASSESSMENT;

public class AssessmentState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new OutlineState());
        // Do stuff for this state
    }

    @Override
    public ApplicationState toEnum() {
        return ASSESSMENT;
    }
}
