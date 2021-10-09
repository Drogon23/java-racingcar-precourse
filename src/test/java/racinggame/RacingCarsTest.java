package racinggame;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import nextstep.test.NSTest;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class RacingCarsTest extends NSTest {

	private static final int START_RANDOM_NUMBER = 0;
	private static final int END_RANDOM_NUMBER = 9;

	@BeforeEach
	void beforeEach() {
		setUp();
	}

	@Test
	void 차_이름_입력_시_아무_문자도_입력하지_않음() {
		runNoLineFound("\n");
		verify(ErrorType.WRONG_INPUT);
	}

	@Test
	void 차_이름_입력_시_5글자를_초과함() {
		runNoLineFound("asdasd");
		verify(ErrorType.WRONG_INPUT);
	}

	@Test
	void 차_이름_입력_성공() {
		String carName1 = "car1";
		String carName2 = "car2";

		try (MockedStatic<Console> mockConsole = Mockito.mockStatic(Console.class)) {
			mockConsole.when(Console::readLine).thenReturn(carName1 + "," + carName2);

			RacingCars racingCars = RacingCars.createRacingCars();
			assertThat(racingCars.getCarList().get(0).getName()).isEqualTo(carName1);
			assertThat(racingCars.getCarList().get(1).getName()).isEqualTo(carName2);
		}
	}

	@Test
	void 시도_횟수만큼_모든_차_이동() {
		RacingCars racingCars = new RacingCars(new String[] {"pobi", "woni"});
		try (MockedStatic<Randoms> mockRandom = Mockito.mockStatic(Randoms.class)) {
			mockRandom.when(() -> Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER))
				.thenReturn(4, 2, 5, 9);

			racingCars.goRacingCars(2);

			List<Car> carList = racingCars.getCarList();
			assertThat(carList.get(0).getDistance()).isEqualTo(2);
			assertThat(carList.get(1).getDistance()).isEqualTo(1);
		}
	}

	@Test
	void 우승자_한명_선정() {
		RacingCars racingCars = new RacingCars(new String[] {"pobi", "woni"});
		try (MockedStatic<Randoms> mockRandom = Mockito.mockStatic(Randoms.class)) {
			mockRandom.when(() -> Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER))
				.thenReturn(4, 2, 5, 9);

			racingCars.goRacingCars(2);
			racingCars.printWinner();

			verify("pobi");
		}
	}

	@Test
	void 우승자_여러명_선정() {
		RacingCars racingCars = new RacingCars(new String[] {"pobi", "woni", "lani"});
		try (MockedStatic<Randoms> mockRandom = Mockito.mockStatic(Randoms.class)) {
			mockRandom.when(() -> Randoms.pickNumberInRange(START_RANDOM_NUMBER, END_RANDOM_NUMBER))
				.thenReturn(4, 2, 6, 5, 9, 8);

			racingCars.goRacingCars(2);
			racingCars.printWinner();

			verify("최종 우승자는 pobi,lani 입니다.");
		}
	}

	@AfterEach
	void tearDown() {
		outputStandard();
	}

	@Override
	protected void runMain() {
		RacingCars.createRacingCars();
	}
}
