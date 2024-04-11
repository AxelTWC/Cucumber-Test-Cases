package seg3102team3.project

import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [UypPmsApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@CucumberContextConfiguration
class UypPmsApplicationTests {
	@Test
	fun contextLoads() {}

}
