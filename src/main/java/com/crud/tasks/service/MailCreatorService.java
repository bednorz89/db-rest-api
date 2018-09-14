package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message, boolean dailyMail) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("company_info", adminConfig.companyInfo());
        context.setVariable("preview", "New card is created!");
        context.setVariable("preview_daily_mail", "Daily mail!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("daily_mail", dailyMail);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
