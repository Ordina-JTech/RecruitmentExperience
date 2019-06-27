package nl.ordina.recruitmentexperience.core.email;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.model.Application;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EmailClientSmtpImpl implements EmailClient {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    private final Locale dutchLocale = new Locale("nl", "NL");

    public void sendMail(final Application application) {
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("recruitment@ordina.nl");
            messageHelper.setTo(application.getApplicant().getEmail());
            messageHelper.setSubject(String.format("Uitnodiging voor je gesprek bij Ordina op %s", application.getFirstInterviewDateTime().format(DateTimeFormatter.ofPattern("EEEE d MMMM", dutchLocale))));
            final String content = buildContent(application);
            messageHelper.setText(content, true);
        };

        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    private String buildContent(final Application application) {
        final Context context = new Context();
        context.setVariable("applicantName", application.getApplicant().getFirstName());
        context.setVariable("interviewDate", application.getFirstInterviewDateTime().format(DateTimeFormatter.ofPattern("EEEE d MMMM", dutchLocale)));
        context.setVariable("interviewTime", application.getFirstInterviewDateTime().format(DateTimeFormatter.ofPattern("H:mm", dutchLocale)));
        return templateEngine.process("mailTemplateFirstInterview", context);
    }
}
