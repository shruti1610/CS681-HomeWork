package edu.umb.cs681.hw19;


import java.util.List;

public class Car {

	String make;
	String model;
	private float price;
	

	public Car(String make, String model, float price) {
		super();
		this.make = make;
		this.model = model;
		this.price = price;
	}



	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", price=" + price +"]";
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

}
