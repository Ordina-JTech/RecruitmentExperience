package nl.ordina.recruitmentexperience.core.model.state;

import nl.ordina.recruitmentexperience.core.SpringContext;
import nl.ordina.recruitmentexperience.core.email.EmailClient;
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
        System.out.println(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));


        final EmailClient emailClient = SpringContext.getBean(EmailClient.class);
        emailClient.sendMail(application);
    }

    @Override
    public ApplicationState toEnum() {
        return FIRST_INTERVIEW;
    }
}
