package racinggame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

import nextstep.utils.Console;

public class RacingCars {
	private static final int MAX_CAR_NAME_LENGTH = 5;
	private static final String COMMA = ",";
	private static final int MIN_INPUT_LENGTH = 1;
	private static final int ZERO = 0;
	private static final int FIRST_INDEX = 0;
	private static final String WINNER_MESSAGE = "최종 우승자는 %s 입니다.";

	private final List<Car> carList;

	public RacingCars() {
		carList = Collections.emptyList();
	}

	public RacingCars(String[] carNames) {
		List<Car> carList = new ArrayList<>();
		for (String carName : carNames) {
			validateCarNameLength(carName);
			carList.add(new Car(carName.trim()));
		}
		this.carList = carList;
	}

	private void validateCarNameLength(String carName) {
		if (carName.trim().length() > MAX_CAR_NAME_LENGTH) {
			throw new IllegalArgumentException(ErrorType.WRONG_INPUT);
		}
	}

	public List<Car> getCarList() {
		return carList;
	}

	public static RacingCars createRacingCars() {
		RacingCars racingCars = new RacingCars();
		while (racingCars.getCarList().size() < MIN_INPUT_LENGTH) {
			racingCars = makeRacingCars();
		}
		return racingCars;
	}

	private static RacingCars makeRacingCars() {
		RacingCars racingCars = new RacingCars();
		try {
			String inputLine = getUserInputLine();
			String[] carNames = inputLine.split(COMMA);
			racingCars = new RacingCars(carNames);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
		return racingCars;
	}

	private static String getUserInputLine() {
		String inputLine = Console.readLine();
		if (inputLine.trim().length() < MIN_INPUT_LENGTH) {
			throw new IllegalArgumentException(ErrorType.WRONG_INPUT);
		}
		return inputLine;
	}

	public void goRacingCars(int racingCount) {
		int count = ZERO;
		while (count < racingCount) {
			tryMoveAllRacingCars();
			count++;
		}
	}

	private void tryMoveAllRacingCars() {
		for (Car car : this.carList) {
			car.tryMove();
		}
		System.out.println();
	}

	public void printWinner() {
		carList.sort(Comparator.comparingInt(Car::getDistance).reversed());
		int winnerDistance = carList.get(FIRST_INDEX).getDistance();
		String winnerName = getWinnerName(winnerDistance);
		System.out.printf(WINNER_MESSAGE, winnerName);
	}

	private String getWinnerName(int winnerDistance) {
		StringJoiner stringJoiner = new StringJoiner(COMMA);
		int index = FIRST_INDEX;
		while (winnerDistance == carList.get(index).getDistance()) {
			stringJoiner.add(carList.get(index).getName());
			index++;
		}
		return stringJoiner.toString();
	}
}
