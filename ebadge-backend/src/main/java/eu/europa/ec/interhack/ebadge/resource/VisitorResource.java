package eu.europa.ec.interhack.ebadge.resource;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.europa.ec.interhack.ebadge.dto.VisitorResponse;
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
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public VisitorResponse register(@RequestBody Visitor visitor) {

		// Checking that we have the required params coming in
		ArrayList<String> errors = new ArrayList<String>();
		if (StringUtils.isBlank(visitor.getFirstName())) errors.add("firstName is a required field");
		if (StringUtils.isBlank(visitor.getLastName())) errors.add("lastName is a required field");
		if (StringUtils.isBlank(visitor.getEmail())) errors.add("email is a required field");
		if (StringUtils.isBlank(visitor.getIdDocNumber())) errors.add("idDocNumber is a required field");
		if (StringUtils.isBlank(visitor.getValidityIdDate())) errors.add("validityIdDate is a required field");
		if (StringUtils.isBlank(visitor.getHost())) errors.add("host is a required field");
		
		// If we have missing fields send an error, otherwise push into DB and return a success
		if (errors.size() > 0){
			return new VisitorResponse("ERROR: "+StringUtils.join(errors.toArray(new String[]{}), ", "));
		} else {
			// Save it to the DB
			repo.insert(visitor);

			return new VisitorResponse("OK");
		}
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public VisitorResponse checkVisitor(@RequestParam(value = "visitor") Visitor visitor) {

		// TODO here check an already registered visitor

		return new VisitorResponse("OK");
	}
}
