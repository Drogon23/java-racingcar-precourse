package racinggame;

import nextstep.utils.Console;

public class Application {

	public static final String GAME_START_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	public static final String RACING_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

	public static void main(String[] args) {
		System.out.println(GAME_START_MESSAGE);
		RacingCars racingCars = RacingCars.createRacingCars();
		System.out.println(RACING_COUNT_MESSAGE);
		int count = getCount();
		System.out.println(count);
	}

	private static int getCount() {
		int count = 0;
		while (count < 1) {
			count = tryReadCount(count);
		}
		return count;
	}

	private static int tryReadCount(int count) {
		try {
			count = readCount();
		} catch (IllegalArgumentException exception) {
			System.out.println(ErrorType.WRONG_RACING_COUNT);
		}
		return count;
	}

	private static int readCount() {
		String inputLine = Console.readLine();
		int count = Integer.parseInt(inputLine);
		if (count < 1) {
			throw new IllegalArgumentException(ErrorType.WRONG_RACING_COUNT);
		}
		return count;
	}
}
