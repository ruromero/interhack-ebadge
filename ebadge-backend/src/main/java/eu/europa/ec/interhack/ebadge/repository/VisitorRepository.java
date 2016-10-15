package eu.europa.ec.interhack.ebadge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import eu.europa.ec.interhack.ebadge.model.Visitor;

/**
 * Created by rromero on 10/10/16.
 */
@RepositoryRestResource(path = "requests")
public interface VisitorRepository extends MongoRepository<Visitor, Long> {
}