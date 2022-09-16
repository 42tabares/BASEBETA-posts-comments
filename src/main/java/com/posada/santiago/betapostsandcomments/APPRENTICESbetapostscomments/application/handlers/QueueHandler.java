package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.handlers;

import co.com.sofka.domain.generic.DomainEvent;
import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.bus.Notification;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.config.RabbitMqConfig;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.generic.models.StoredEvent;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases.UpdateViewUseCase;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.logging.Logger;

@Service
public class QueueHandler implements Consumer<String> {
    private final Gson gson = new Gson();
    private final UpdateViewUseCase useCase;
    private final Logger logger = Logger.getLogger(RabbitMqConfig.class.getName());

    public QueueHandler(UpdateViewUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void accept(String received) {

        logger.info("Received new message from Rabbit MQ: " + received);
        Notification notification = gson.fromJson(received, Notification.class);
        logger.info("Searching View Associated to Domain Event...");

        DomainEvent event = null;
        try {
            event = (DomainEvent) gson.fromJson(notification.getBody(),Class.forName(
                    notification.getType().replace("alphapostsandcomments","betapostsandcomments.APPRENTICESbetapostscomments")
            ));
        } catch (ClassNotFoundException e) {
            logger.info("DomainEvent not found, execption incoming");
            throw new RuntimeException(e);
        }
        logger.info("Updating View");
        useCase.accept(event);
    }
}
