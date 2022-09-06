package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;


import co.com.sofka.domain.generic.DomainEvent;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.generic.DomainUpdater;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.PostCreated;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ViewUpdater extends DomainUpdater {

    private final DomainViewRepository repository;


    public ViewUpdater(DomainViewRepository repository){
        this.repository = repository;

        listen((PostCreated event)->{
            PostViewModel post = new PostViewModel(event.aggregateRootId(), event.getAuthor(), event.getTitle(), "false", new ArrayList<>());
            repository.saveNewPost(post);
        });

        listen((CommentAdded event)->{
            CommentViewModel comment = new CommentViewModel(event.getId() , event.aggregateRootId(), event.getAuthor(), event.getContent(), "false");
            repository.addCommentToPost(comment);
        });
    }



}
