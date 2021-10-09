package racinggame;

import nextstep.utils.Console;

public class Application {

	private static final String GAME_START_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final String RACING_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
	private static final int ZERO = 0;
	private static final int MIN_RACING_COUNT = 1;
	private static final String RESULT_MESSAGE = "실행 결과";

	public static void main(String[] args) {
		playRacingGame();
	}

	private static void playRacingGame() {
		System.out.println(GAME_START_MESSAGE);
		RacingCars racingCars = RacingCars.createRacingCars();
		System.out.println(RACING_COUNT_MESSAGE);
		int racingCount = getRacingCount();
		System.out.println(RESULT_MESSAGE);
		racingCars.goRacingCars(racingCount);
		racingCars.printWinner();
	}

	private static int getRacingCount() {
		int racingCount = ZERO;
		while (racingCount < MIN_RACING_COUNT) {
			racingCount = tryReadRacingCount();
		}
		return racingCount;
	}

	private static int tryReadRacingCount() {
		int racingCount = ZERO;
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
		if (racingCount < MIN_RACING_COUNT) {
			throw new IllegalArgumentException(ErrorType.WRONG_RACING_COUNT);
		}
		return racingCount;
	}
}
