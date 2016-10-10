package eu.europa.ec.interhack.ebadge.repository;

import eu.europa.ec.interhack.ebadge.model.AccessRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by rromero on 10/10/16.
 */
@RepositoryRestResource(path = "requests")
public interface AccessRequestRepository extends MongoRepository<AccessRequest, Long> {
}
