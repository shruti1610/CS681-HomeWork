package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.List;

public class CarTest {

	public static void main(String[] args) {
		List<Car> cars = getCarList();
		System.out.println("Cars");
	cars.forEach(System.out::println);
	
	System.out.println("Counting Cars using Reduce");
	long carCount = cars.stream()
	.map( (Car car)-> car.getModel() )
	.count();
	System.out.println("Car count: "+ carCount);
	
	System.out.println("Counting Cars using 3rd version of Reduce");
	int carModelNum =cars.stream()
			.map( (Car car)-> car.getModel() )
			.reduce(0,
			(result,carModel)-> ++result,
			(finalResult,intermediateResult)->finalResult);
	System.out.println("Car count: "+ carModelNum);

	System.out.println("Counting Cars using Parallel Stream");
	int carModelNumParallel =cars.stream().parallel()
			.map( (Car car)-> car.getModel() )
			.reduce(0,
			(result,carModel)-> ++result,
			(finalResult,intermediateResult)->
				finalResult+intermediateResult
			);
	System.out.println("Car count: "+ carModelNumParallel);
	}
	
	
	
	private static List<Car> getCarList() {
		List<Car> cars = new ArrayList<>();
		Car car1 = new Car("Volkswagen"," Atlas", 4500);
		Car car2 = new Car("Jaguar","F-Type", 13000);
		Car car3 = new Car("Alfa Romeo", "Stelvio", 10000);
		Car car4 = new Car("BMW","7-Series", 15700);

		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);

		return cars;
	}
}
