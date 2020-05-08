package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.List;

public class MapReduceTest {

	public static void main(String[] args) {
		List<Car> cars = getCarList();
		System.out.println("Cars List "+ cars);
		System.out.println("Minimum price of the car " + getMinPrice(cars));
		System.out.println("Maximum price of the car " + getMaxPrice(cars));
		System.out.println("Count of cars " + getCountOfCars(cars));
	}

	private static Float getMinPrice(List<Car> cars) {
		return cars.stream()
				.map( (car)-> car.getPrice() )
				.reduce(0F, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice < result) return carPrice;
				else return result;} );	
	}

	private static Float getMaxPrice(List<Car> cars) {
		return cars.stream()
				.map( (car)-> car.getPrice() )
				.reduce(0F, (result, carPrice)->{
				if(result==0) return carPrice;
				else if(carPrice > result) return carPrice;
				else return result;} );
	}
	
	private static long getCountOfCars(List<Car> cars) {
		 return cars.stream()
				.map((Car car)-> car.getModel())
				.count();
		
	}

	private static List<Car> getCarList() {
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car("Volkswagen Atlas", 4500);
		Car car2 = new Car("Jaguar F-Type", 13000);
		Car car3 = new Car("Alfa Romeo Stelvio", 10000);
		Car car4 = new Car("BMW 7-Series", 15700);

		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);

		return cars;
	}
}
