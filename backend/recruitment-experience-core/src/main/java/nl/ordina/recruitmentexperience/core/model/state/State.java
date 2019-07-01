package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.model.Application;

public interface State {

    State getCurrentState();

    void toNextState(final Application application);

    ApplicationState toEnum();
}
