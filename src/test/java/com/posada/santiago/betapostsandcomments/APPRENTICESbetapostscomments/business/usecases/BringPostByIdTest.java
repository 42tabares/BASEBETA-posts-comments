package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;

import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BringPostByIdTest {

    @Mock
    private DomainViewRepository repository;
    @InjectMocks
    private BringPostById useCaseToUse;

    @Test
    void PostByIDUseCaseTest() {

        //Arrange
        var post = new PostViewModel("31416", "Author1", "Title1", "false", new ArrayList<>());
        var resultExpected = Mono.just(post);

        Mockito.when(repository.findByAggregateId(Mockito.any(String.class))).thenReturn(resultExpected);

        var useCase = useCaseToUse.apply("31416");

        //Act
        StepVerifier.create(useCase)
                .expectNext(post)
                .expectComplete()
                .verify();

        //Assert
        Mockito.verify(repository).findByAggregateId("31416");
    }


}