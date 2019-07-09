package nl.ordina.recruitmentexperience.core.email;

import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;

public interface EmailClient {

    void sendMail(final ApplicationEntity application);
}
