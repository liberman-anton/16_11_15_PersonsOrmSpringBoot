package tel_ran.persons.controller;

import java.time.LocalDate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import tel_ran.persons.model.dao.PersonsORM;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Employee;
import tel_ran.persons.model.entities.Person;

@Component
public class PersonsOrmTestCreation implements CommandLineRunner{
	
	@Autowired
	PersonsORM personsOrm;

	public static void createPersons(Person[] persons, PersonsORM personsOrm) {
		for(Person person : persons)
			personsOrm.addPerson(person);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Person[] persons = {
				new Child(123, "Moshe", LocalDate.of(2011, 11, 3), new Address("Rehovot", "Plaut", 10), "tel-ran"),
				new Employee(124, "Vasya", LocalDate.of(1990, 1, 30), new Address("Rehovot", "Plaut", 10), "Tel-ran", 15000),
				new Child(125, "Sara", LocalDate.of(2015, 3, 13), new Address("Rehovot", "Plaut", 10), "none"),
				new Child(126, "Olya", LocalDate.of(2010, 5, 12), new Address("Beersheva", "Yalim", 3), "klita"),
				new Child(127, "Sasha", LocalDate.of(2012, 11, 3), new Address("Beersheva", "Yalim", 3), "klita"),
				new Employee(128, "David", LocalDate.of(1970, 1, 3), new Address("Beersheva", "Yalim", 3), "Motorola", 20000),
				new Child(129, "Tolya", LocalDate.of(2010, 5, 3), new Address("Rehovot", "Plaut", 10), "Salut"),
				new Employee(130, "Serg", LocalDate.of(1975, 4, 12), new Address("Beersheva", "Yalim", 3), "Motorola", 18000)
		};
			createPersons(persons, personsOrm);
			personsOrm.updateAddress(123, new Address("RishonLezion", "Zibotinsky", 21));		
	}
	

}
