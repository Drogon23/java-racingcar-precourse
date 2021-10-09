package racinggame;

import nextstep.utils.Console;

public class Application {

	public static final String GAME_START_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	public static final String RACING_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

	public static void main(String[] args) {
		System.out.println(GAME_START_MESSAGE);
		RacingCars racingCars = RacingCars.createRacingCars();
		System.out.println(RACING_COUNT_MESSAGE);
		int racingCount = getRacingCount();
		System.out.println(racingCount);
	}

	private static int getRacingCount() {
		int racingCount = 0;
		while (racingCount < 1) {
			racingCount = tryReadRacingCount();
		}
		return racingCount;
	}

	private static int tryReadRacingCount() {
		int racingCount = 0;
		try {
			racingCount = readRacingCount();
		} catch (IllegalArgumentException exception) {
			System.out.println(ErrorType.WRONG_RACING_COUNT);
		}
		return racingCount;
	}

	private static int readRacingCount() {
		String inputLine = Console.readLine();
		int racingCount = Integer.parseInt(inputLine);
		if (racingCount < 1) {
			throw new IllegalArgumentException(ErrorType.WRONG_RACING_COUNT);
		}
		return racingCount;
	}
}
