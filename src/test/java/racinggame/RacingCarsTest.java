package racinggame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import nextstep.test.NSTest;
import nextstep.utils.Console;

public class RacingCarsTest extends NSTest {

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
		MockedStatic<Console> mockConsole = Mockito.mockStatic(Console.class);
		mockConsole.when(Console::readLine).thenReturn(carName1 + "," + carName2);

		RacingCars racingCars = RacingCars.createRacingCars();
		assertThat(racingCars.getCarList().get(0).getName()).isEqualTo(carName1);
		assertThat(racingCars.getCarList().get(1).getName()).isEqualTo(carName2);
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
