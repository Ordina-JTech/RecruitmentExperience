package nl.ordina.recruitmentexperience.core.model.state;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.OUTLINE;

@Slf4j
public class OutlineState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(ApplicationEntity application) {
        //application.setState(new ContractState());
        // Do stuff for this state
        //log.info(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));
    }

    @Override
    public ApplicationState toEnum() {
        return OUTLINE;
    }
}
