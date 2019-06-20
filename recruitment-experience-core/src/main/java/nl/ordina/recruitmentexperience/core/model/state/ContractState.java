package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.CONTRACT;

public class ContractState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new SignedState());
        // Do stuff for this state
        System.out.println(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));
    }

    @Override
    public ApplicationState toEnum() {
        return CONTRACT;
    }
}
