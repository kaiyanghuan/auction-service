package features

import com.auction.service.AuctionServiceApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = [AuctionServiceApplication::class])
class CucumberConfiguration {
}