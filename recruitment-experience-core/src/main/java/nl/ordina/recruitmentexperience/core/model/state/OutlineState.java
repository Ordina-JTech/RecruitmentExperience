package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.OUTLINE;

public class OutlineState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new ContractState());
        // Do stuff for this state
    }

    @Override
    public ApplicationState toEnum() {
        return OUTLINE;
    }
}
