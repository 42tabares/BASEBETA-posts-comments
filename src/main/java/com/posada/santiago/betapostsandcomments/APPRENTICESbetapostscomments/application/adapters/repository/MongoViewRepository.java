package com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.adapters.repository;


import com.google.gson.Gson;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.application.config.RabbitMqConfig;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.DomainViewRepository;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.EventBus;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.CommentViewModel;
import com.posada.santiago.betapostsandcomments.APPRENTICESbetapostscomments.business.gateways.model.PostViewModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.logging.Logger;

@Repository
public class MongoViewRepository implements DomainViewRepository {
    private final ReactiveMongoTemplate template;
    private final EventBus bus;

    private final Gson gson = new Gson();

    private final Logger logger = Logger.getLogger(MongoViewRepository.class.getName());

    public MongoViewRepository(ReactiveMongoTemplate template, EventBus bus) {
        this.template = template;
        this.bus = bus;
    }

    @Override
    public Mono<PostViewModel> findByAggregateId(String aggregateId) {
        logger.info("Searching post " + aggregateId);
        return template.findOne(Query.query(Criteria.where("aggregateId").is(aggregateId)), PostViewModel.class);
    }

    @Override
    public Flux<PostViewModel> findAllPosts() {
        logger.info("Retrieving posts from repository");
        return template.findAll(PostViewModel.class);
    }

    @Override
    public Mono<PostViewModel> saveNewPost(PostViewModel post) {
        logger.info("Updating/Creating post...");
        return template.save(post);
    }


    @Override
    public Mono<PostViewModel> addCommentToPost(CommentViewModel comment) {
        return findByAggregateId(comment.getPostId()).flatMap(postViewModel -> {
            logger.info("Post found, adding comment");
            var comments = new ArrayList<>(postViewModel.getComments());
            comments.add(comment);
            postViewModel.setComments(comments);
            return template.findAndReplace(Query.query(Criteria.where("aggregateId").is(comment.getPostId())),postViewModel);
        });
    }

}
