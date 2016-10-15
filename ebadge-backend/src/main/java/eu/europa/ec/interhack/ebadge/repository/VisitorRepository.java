package eu.europa.ec.interhack.ebadge.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import eu.europa.ec.interhack.ebadge.model.Visitor;

import java.util.Collection;

/**
 * Created by rromero on 10/10/16.
 */
@RepositoryRestResource(path = "visitors")
public interface VisitorRepository extends MongoRepository<Visitor, String> {

    Collection<Visitor> findByStatusIn(@Param("statuses") String... status);

}
