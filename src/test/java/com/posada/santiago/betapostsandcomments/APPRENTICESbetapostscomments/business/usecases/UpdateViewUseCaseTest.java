package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;

import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.bus.RabbitMqEventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.CommentAdded;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.domain.events.PostCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateViewUseCaseTest {

    @Mock
    private DomainViewRepository repository;
    @Mock
    private RabbitMqEventBus bus;

    ViewUpdater viewUpdater;
    UpdateViewUseCase usecaseToUse;

    @BeforeEach
    void init(){
        viewUpdater = new ViewUpdater(repository,bus);
        usecaseToUse = new UpdateViewUseCase(bus,viewUpdater);
    }

    @Test
    void PostAddedUpdateViewUseCase() {

        //Arrange

        var post = new PostViewModel("31416", "Author1", "Title1", "false", new ArrayList<>());
        var postCreatedEvent = new PostCreated("Author1","Title1");
        postCreatedEvent.setAggregateRootId("31416");

        Mockito.when(repository.saveNewPost(Mockito.any(PostViewModel.class))).thenReturn(Mono.just(post));

        usecaseToUse.accept(postCreatedEvent);

        Mockito.verify(bus, BDDMockito.times(1)).publishPostViewModel(Mockito.any(PostViewModel.class));

        Mockito.verify(repository, BDDMockito.times(1)).saveNewPost(Mockito.any(PostViewModel.class));


        //Act

        //Assert

    }

    @Test
    void acceptCommentAdded(){

        var post = new PostViewModel("31416", "Author1", "Title1", "false", new ArrayList<>());
        var comment = new CommentViewModel("21718","31416","Author2","LoremIpsum","false");
        var commentAddedEvent = new CommentAdded("21718", "Author2", "LoremIpsum");
        commentAddedEvent.setAggregateRootId("31416");

        List comments = new ArrayList<>();
        comments.add(comment);
        post.setComments(comments);

        Mockito.when(repository.addCommentToPost(Mockito.any(CommentViewModel.class))).thenReturn(Mono.just(post));

        usecaseToUse.accept(commentAddedEvent);

        Mockito.verify(bus, BDDMockito.times(1)).publishCommentViewModel(Mockito.any(CommentViewModel.class));

        Mockito.verify(repository, BDDMockito.times(1)).addCommentToPost(Mockito.any(CommentViewModel.class));

    }
}