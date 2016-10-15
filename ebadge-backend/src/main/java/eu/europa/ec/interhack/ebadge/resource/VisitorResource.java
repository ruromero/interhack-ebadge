package eu.europa.ec.interhack.ebadge.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
public class VisitorResource {

	@Autowired
	private VisitorRepository repo;

	@CrossOrigin(value = "*")
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public String register(@RequestBody Visitor visitor) {

		// TODO placeholder for validation

		// blindly save it to the db for the moment
		repo.insert(visitor);

		return "registered!";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String checkVisitor(@RequestParam(value = "visitor") Visitor visitor) {

		// TODO here check the visitor

		return "approved!";
	}
}
