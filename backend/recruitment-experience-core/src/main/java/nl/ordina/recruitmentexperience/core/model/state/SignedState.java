package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.SIGNED;

public class SignedState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(ApplicationEntity application) {
        throw new IllegalStateException("Signed is the final state; No next state available");
    }

    @Override
    public ApplicationState toEnum() {
        return SIGNED;
    }
}
