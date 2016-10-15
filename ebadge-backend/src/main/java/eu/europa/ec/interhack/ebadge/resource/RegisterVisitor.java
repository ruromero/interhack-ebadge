package eu.europa.ec.interhack.ebadge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.europa.ec.interhack.ebadge.model.Visitor;
import eu.europa.ec.interhack.ebadge.repository.VisitorRepository;

/**
 * Created by rromero on 10/10/16.
 */
@RestController
public class RegisterVisitor {

	@Autowired
	private VisitorRepository repo;
	
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value="visitor") Visitor visitor) {
    	
    	// blindly save it to the db for the moment
    	repo.insert(visitor);
    	
        return "registered!";
    }

}
