package pl.codzisnaobiad.imhungry

import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import pl.codzisnaobiad.imhungry.stubs.SpoonacularStubs
import spock.lang.Shared
import spock.lang.Specification

@ActiveProfiles("integration")
@ContextConfiguration
@SpringBootTest(
        classes = ImhungryApplication,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
abstract class IntegrationSpec extends Specification implements SpoonacularStubs {

    @LocalServerPort
    int port

    @Shared
    @ClassRule
    public WireMockClassRule wireMockRule = new WireMockClassRule(8089)

    @Autowired
    TestRestTemplate httpClient

    def <T> ResponseEntity<T> get(String path, Class<T> responseType) {
        httpClient.getForEntity(localUrl() + path, responseType)
    }

    protected String localUrl() {
        return "http://localhost:$port"
    }

}