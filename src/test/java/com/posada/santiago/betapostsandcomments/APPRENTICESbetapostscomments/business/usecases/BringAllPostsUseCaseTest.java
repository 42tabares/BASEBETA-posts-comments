package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;

import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.repository.MongoViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BringAllPostsUseCaseTest {

    @Mock
    private DomainViewRepository repository;
    @InjectMocks
    private BringAllPostsUseCase useCaseToUse;

    @Test
    void BringAllPostsUSeCaseTest() {

        //Arrange
        var allPostList = new ArrayList<PostViewModel>();
        allPostList.add(new PostViewModel("31416", "Author1", "Title1", "false", new ArrayList<>()));
        allPostList.add(new PostViewModel("31417", "Author2", "Title2", "false", new ArrayList<>()));
        allPostList.add(new PostViewModel("31418", "Author3", "Title3", "false", new ArrayList<>()));

        var expectedFlux = Flux.fromIterable(allPostList);

        Mockito.when(repository.findAllPosts()).thenReturn(expectedFlux);

        var useCase = useCaseToUse.get();

        //Act & Assert
        StepVerifier.create(useCase).expectNextCount(3).verifyComplete();

    }




}