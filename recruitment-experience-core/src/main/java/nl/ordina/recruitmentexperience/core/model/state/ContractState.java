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
        throw new UnsupportedOperationException("Contract is the final state; No next state available");
    }

    @Override
    public ApplicationState toEnum() {
        return CONTRACT;
    }
}
