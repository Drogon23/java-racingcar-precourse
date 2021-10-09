package racinggame;

import nextstep.utils.Randoms;

public class Car {
	private static final int INIT_DISTANCE = 0;
	private static final int FORWARD = 1;
	private static final int START_RANDOM_NUMBER = 0;
	private static final int END_RANDOM_NUMBER = 9;
	private static final int MOVING_FORWARD = 4;
	private static final String DASH = "-";
	private static final String COLON = " : ";

	private final String name;
	private int distance;

	public Car(String name) {
		this.name = name;
		this.distance = INIT_DISTANCE;
	}

	public String getName() {
		return name;
	}

	public int getDistance() {
		return distance;
	}

	public void tryMove() {
		int randomNumber = Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER);
		if (randomNumber >= MOVING_FORWARD) {
			this.distance += FORWARD;
		}
		System.out.println(this.name + COLON + printDistance());
	}

	private String printDistance() {
		int count = INIT_DISTANCE;
		StringBuilder builder = new StringBuilder();
		while (count < this.distance) {
			builder.append(DASH);
			count++;
		}
		return builder.toString();
	}
}
