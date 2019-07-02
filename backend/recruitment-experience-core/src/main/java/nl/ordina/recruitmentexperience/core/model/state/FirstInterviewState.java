package nl.ordina.recruitmentexperience.core.model.state;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.recruitmentexperience.core.SpringContext;
import nl.ordina.recruitmentexperience.core.email.EmailClient;
import nl.ordina.recruitmentexperience.core.model.Application;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.FIRST_INTERVIEW;

@Slf4j
public class FirstInterviewState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new SecondInterviewState());
        // Do stuff for this state
        log.info(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));

        final EmailClient emailClient = SpringContext.getBean(EmailClient.class);
        emailClient.sendMail(application);
    }

    @Override
    public ApplicationState toEnum() {
        return FIRST_INTERVIEW;
    }
}
