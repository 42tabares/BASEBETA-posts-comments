package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.bus;


import co.com.sofka.domain.generic.DomainEvent;
import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.config.RabbitMqConfig;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public RabbitMqEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass().getTypeName(),
                gson.toJson(event)
        );
    }

    @Override
    public void publishPostViewModel(PostViewModel viewModel) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,RabbitMqConfig.PROXY_ROUTING_KEY_POST_CREATED,gson.toJson(viewModel).getBytes());
    }

    @Override
    public void publishCommentViewModel(CommentViewModel viewModel) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,RabbitMqConfig.PROXY_ROUTING_KEY_COMMENT_ADDED,gson.toJson(viewModel).getBytes());
    }

    @Override
    public void publishError(Throwable errorEvent) {

    }
}
