package eu.europa.ec.interhack.ebadge.repository;

import eu.europa.ec.interhack.ebadge.model.AccessRequest;
import eu.europa.ec.interhack.ebadge.model.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by rromero on 10/10/16.
 */
@RepositoryRestResource(path = "buildings")
public interface BuildingRepository extends MongoRepository<Building, String> {

}
