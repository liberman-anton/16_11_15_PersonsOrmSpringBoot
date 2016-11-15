package tel_ran.persons.model.dao;

import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Person;


public class PersonsORM {

//	connect 3 points: spring entity manager, persistance.xml ORM, our code
	@PersistenceContext(unitName = "springHibernate")
	EntityManager em;//build Spring
	
	@Transactional//if method change status of table in database
	public boolean addPerson(Person person){
		if(person == null)
			return false;
		if(em.find(Person.class, person.getId()) != null)
			return false;
		em.persist(person);	//will not throw exceptions	
		return true;
	}
	@Transactional
	public boolean updateAddress(int id, Address newAddress){
		Person person = em.find(Person.class, id);
		if(person == null)
			return false;
		person.setAddress(newAddress);
		return true;
	}
	public Person getPerson(int id){
		return em.find(Person.class, id);
	}
	@Transactional
	public Person removePerson(int id){
		Person res = em.find(Person.class, id);
		if(res != null)
			em.remove(res);
		return res;
	}
	public List<Person> getPersonsByMonths(int month){
		Query query = em.createQuery(String.format("select p from Person p where month(p.birthyear) = %d", month));
		return query.getResultList();
	}
}
