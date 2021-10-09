package racinggame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import nextstep.utils.Randoms;

public class CarTest {

	private static final int START_RANDOM_NUMBER = 0;
	private static final int END_RANDOM_NUMBER = 9;

	@Test
	void 랜덤_숫자가_3이하일때_정지() {
		try (MockedStatic<Randoms> mockRandom = Mockito.mockStatic(Randoms.class)) {
			mockRandom.when(() -> Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER))
				.thenReturn(3, 2, 1);

			Car car = new Car("test");
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(0);
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(0);
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(0);
		}
	}

	@Test
	void 랜덤_숫자가_4이상일때_앞으로_이동() {
		try (MockedStatic<Randoms> mockRandom = Mockito.mockStatic(Randoms.class)) {
			mockRandom.when(() -> Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER))
				.thenReturn(4, 2, 5);

			Car car = new Car("test");
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(1);
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(1);
			car.tryMove();
			assertThat(car.getDistance()).isEqualTo(2);
		}
	}
}
