package tel_ran.persons.model.entities;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;

import static tel_ran.persons.api.PersonsConstants.*;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends Person {
	String company;
	Integer salary;
	
	public Employee(){}
	
	public Employee(int id, String name, LocalDate birthdate, Address address, String company, Integer salary) {
		super(id, name, birthdate, address);
		this.company = company;
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [company=" + company + ", salary=" + salary + ", id=" + id + ", name=" + name + ", birthdate="
				+ birthdate + ", address=" + address + "]";
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public void setData(Map<String,Object> data) throws IllegalArgumentException{
		super.setData(data);
		try {
			company = (String) data.get(COMPANY);
			Integer Salary = (Integer) data.get(SALARY);
			salary = Salary == null ? 0 : Salary;
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong field/s of Employee");
		}
	}
}
