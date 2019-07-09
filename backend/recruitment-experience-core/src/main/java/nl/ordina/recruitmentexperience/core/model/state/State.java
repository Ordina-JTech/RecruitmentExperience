package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;

public interface State {

    State getCurrentState();

    void toNextState(final ApplicationEntity applicationEntity);

    ApplicationState toEnum();
}
