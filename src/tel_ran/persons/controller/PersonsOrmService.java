package tel_ran.persons.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tel_ran.persons.api.IdAddress;
import tel_ran.persons.model.dao.PersonsORM;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Person;

import static tel_ran.persons.api.PersonsConstants.*;


@SpringBootApplication
@ImportResource("classpath:beans.xml")
@RestController
public class PersonsOrmService {
	
	private static final String PACKAGE_PERSON = "tel_ran.persons.model.entities.";
	
	@Autowired
	PersonsORM personsOrm;
	
	@RequestMapping(value = ADD_PERSON, method = RequestMethod.POST)
	public String addPerson(@RequestBody Map<String,Object> data){
		String type = (String) data.get(TYPE);
		if(type == null)
			return "Type missing";
		try {
			Person person = (Person) Class.forName(PACKAGE_PERSON + type).newInstance();
			try {
				person.setData(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			boolean res = personsOrm.addPerson(person);
			if(res == false)
				return "Adding existing person";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			return "Wrong Type of Persons " + type;
		}
		return "Success";
	}
	
	@RequestMapping(value = UPDATE_ADDRESS, method = RequestMethod.POST)
	public String updateAddress(@RequestBody IdAddress idAddress){
		boolean res = personsOrm.updateAddress(idAddress.getId(),
				new Address(idAddress.getCity(), idAddress.getStreet(), idAddress.getBld()));
		return res ? "Update success" : "person not found with id: " + idAddress.getId();
	}
	
	@RequestMapping(value = GET_PERSON)
	public Map<String,Object> getPerson(int id){
		Map<String,Object> res = new LinkedHashMap<>();
		Person person = personsOrm.getPerson(id);
		if(person == null){
			res.put(STATUS, "error");
			res.put(DATA, "person not found " + id);
		}
		else {
			res.put(STATUS, "success");
			res.put(DATA, person);
		}
		return res;
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(PersonsOrmService.class, args);
	}
}
