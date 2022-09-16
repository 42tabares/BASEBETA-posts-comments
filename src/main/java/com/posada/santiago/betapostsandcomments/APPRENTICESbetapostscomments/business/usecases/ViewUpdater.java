package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;


import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.config.RabbitMqConfig;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.generic.DomainUpdater;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.PostCreated;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class ViewUpdater extends DomainUpdater {

    private final DomainViewRepository repository;
    private final EventBus bus;

    private final Logger logger = Logger.getLogger(RabbitMqConfig.class.getName());


    public ViewUpdater(DomainViewRepository repository, EventBus bus){
        this.repository = repository;
        this.bus = bus;

        listen((PostCreated event)->{
            logger.info("Event is of type PostCreated, creating new PostView");
            PostViewModel post = new PostViewModel(
                    event.aggregateRootId(),
                    event.getAuthor(),
                    event.getTitle(),
                    "false",
                    new ArrayList<>());
            logger.info("Saving post to Repository");
            this.repository.saveNewPost(post).subscribe();
            logger.info("Post View created, Publishing Via RabbitMQ...");
            this.bus.publishPostViewModel(post);
        });

        listen((CommentAdded event)->{
            logger.info("Event is of type CommentAdded, creating new CommentView");
            CommentViewModel comment = new CommentViewModel(
                    event.getId() ,
                    event.aggregateRootId(),
                    event.getAuthor(),
                    event.getContent(),
                    "false");
            logger.info("Comment View created, Publishing Via RabbitMQ...");
            this.bus.publishCommentViewModel(comment);
            logger.info("Adding comment to post in Repository...");
            this.repository.addCommentToPost(comment).subscribe();
        });
    }
}
