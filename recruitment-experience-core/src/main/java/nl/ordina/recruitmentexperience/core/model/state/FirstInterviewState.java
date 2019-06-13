package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.FIRST_INTERVIEW;

public class FirstInterviewState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new SecondInterviewState());
        // Do stuff for this state
    }

    @Override
    public ApplicationState toEnum() {
        return FIRST_INTERVIEW;
    }
}
