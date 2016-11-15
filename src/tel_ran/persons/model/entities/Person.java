package tel_ran.persons.model.entities;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;
import static tel_ran.persons.api.PersonsConstants.*;

@Entity // default table name = class name (person)//not enherit// must be constructor default
@Table(name = "bsh_persons")
public abstract class Person {

	private static final Object BIRH_DATA = null;
	@Id
	int id;
	String name;
//	Hibernate 5 and upper know LocalDate
	LocalDate birthdate;// sql don't know camel case
	@Embedded
	Address address;
	
	abstract public String toString();
	
	public Person(){}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())//два объекта принадлежат одному классу, сравниваем ссылки. сингл тон у каждого класса только один обьект
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Person(int id, String name, LocalDate birthyear, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthyear;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public void setData(Map<String,Object> data) throws IllegalArgumentException{
		if(data == null)
			new IllegalArgumentException("data is null");
		try {
			if(id == 0){
				Integer Id = (Integer) data.get(ID);
				if(Id != null)
					id = Id;
			}
			name = (String) data.get(NAME);
			birthdate = (LocalDate) data.get(BIRH_DATA);
			String street = (String) data.get(STREET);
			String city = (String) data.get(CITY);
			Integer bld = (Integer) data.get(BUILDING);
			address = new Address(city, street, bld == null ? 0 : bld);
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
	}
}
