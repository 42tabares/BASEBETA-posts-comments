package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;



import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.repository.MongoViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class BringPostById implements Function<String,Mono<PostViewModel>>{

    public final MongoViewRepository repository;

    public BringPostById(MongoViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<PostViewModel> apply(String postId) {
        return this.repository.findByAggregateId(postId);
    }
}
