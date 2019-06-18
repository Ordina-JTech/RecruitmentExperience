package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.NEW;

public class NewState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new InvitedState());
        System.out.println(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));
    }

    @Override
    public ApplicationState toEnum() {
        return NEW;
    }
}
