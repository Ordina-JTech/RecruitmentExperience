package nl.ordina.recruitmentexperience.core.model.state;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.recruitmentexperience.core.SpringContext;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.video.VideoRenderingService;

import static nl.ordina.recruitmentexperience.core.model.state.ApplicationState.NEW;

@Slf4j
public class NewState implements State {

    @Override
    public State getCurrentState() {
        return this;
    }

    @Override
    public void toNextState(Application application) {
        application.setState(new InvitedState());
        log.info(String.format("Application %d is now in state %s", application.getId(), application.getState().toEnum().name()));

        final VideoRenderingService videoRenderingService = SpringContext.getBean(VideoRenderingService.class);
        new Thread(() -> videoRenderingService.renderVideo(application.getApplicant().getResumeLink())).start();
    }

    @Override
    public ApplicationState toEnum() {
        return NEW;
    }
}
