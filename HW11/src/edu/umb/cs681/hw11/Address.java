package edu.umb.cs681.hw11;

public final class Address {
	private final String street, city, state;
	private final int zipcode;
	
	public Address(String street, String city, String state, int zipcode){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	public Address change(String street, String city, String state, int zipcode) {
		return new Address(street, city, state, zipcode);
	}
	
	public String toString() {
		return street + " " + city + " " + state + " "+zipcode;
	}
	
	public Boolean equals( Address address) {
		if(this.toString().equals(address.toString())) {
			return true;
		}
		else {
			return false;
		}
	}
}
