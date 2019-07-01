package nl.ordina.recruitmentexperience.core.email;

import nl.ordina.recruitmentexperience.core.model.Application;

public interface EmailClient {

    void sendMail(final Application application);
}
