package tel_ran.persons.api;

public class IdAddress {
	int id;
	String city;
	String street;
	int bld;
	
	public IdAddress(int id, String city, String street, int bld) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.bld = bld;
	}

	public IdAddress() {
		
	}

	public int getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getBld() {
		return bld;
	}
	
	
}
