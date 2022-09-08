package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.usecases;


import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.repository.MongoViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
public class BringAllPostsUseCase implements Supplier<Flux<PostViewModel>> {
    public final MongoViewRepository repository;

    public BringAllPostsUseCase(MongoViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<PostViewModel> get() {
        return this.repository.findAllPosts();
    }
}
