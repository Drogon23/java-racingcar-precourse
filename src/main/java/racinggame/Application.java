package racinggame;

public class Application {

	public static final String GAME_START_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";

	public static void main(String[] args) {
		System.out.println(GAME_START_MESSAGE);
		RacingCars racingCars = RacingCars.createRacingCars();

		for (Car car : racingCars.getCarList()) {
			System.out.println(car.getName());
		}

	}
}
