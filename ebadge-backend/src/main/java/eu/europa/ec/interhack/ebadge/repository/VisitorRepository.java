package eu.europa.ec.interhack.ebadge.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import eu.europa.ec.interhack.ebadge.model.Visitor;

/**
 * Created by rromero on 10/10/16.
 */
@RepositoryRestResource(path = "visitors")
public interface VisitorRepository extends MongoRepository<Visitor, Long> {
	
	List<Visitor> findByStatus(String status);
	
	List<Visitor> findByVisitorId(String visitorId);
}
