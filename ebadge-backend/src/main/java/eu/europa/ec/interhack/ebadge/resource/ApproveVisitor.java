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
public class ApproveVisitor {

	@Autowired
	private VisitorRepository repo;
	
    @RequestMapping(value = "/checkvisitor", method = RequestMethod.POST)
    public String checkVisitor(@RequestParam(value="visitor") Visitor visitor) {

    	// TODO here check the visitor
    	
        return "approved!";
    }

}
