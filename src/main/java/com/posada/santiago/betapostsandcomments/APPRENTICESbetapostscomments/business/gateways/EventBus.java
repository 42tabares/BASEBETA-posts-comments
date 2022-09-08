package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;

import java.lang.reflect.Type;

public interface EventBus {
    void publish(DomainEvent event);

    void publishPostViewModel(PostViewModel viewModel);

    void publishCommentViewModel(CommentViewModel viewModel);

    void publishError(Throwable errorEvent);
}
