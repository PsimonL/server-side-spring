package com.example.serversidespring.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ZapScanIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void testZapScan() throws ClientApiException, InterruptedException, IOException {
        // Requires running OWASP ZAP proxy: https://www.zaproxy.org/download/

        String zapAddress = "localhost";
        int zapPort = 8080;
        String zapApiKey = "";  // API Key

        ClientApi api = new ClientApi(zapAddress, zapPort, zapApiKey);

        // Access the application to make sure ZAP can intercept the traffic
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.getForEntity(baseUrl + "/auth/login", String.class);

        // Starting new spider scan
        ApiResponse response = api.spider.scan(baseUrl, null, null, null, null);

        // Wait for the spider scan to complete
        String scanId = ((ApiResponseElement) response).getValue();
        int progress;

        while (true) {
            progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanId)).getValue());
            if (progress >= 100) {
                break;
            }
            Thread.sleep(1000);
        }

        // Perform an active scan
        response = api.ascan.scan(baseUrl, "True", "False", null, null, null);

        // Wait for the active scan to complete
        scanId = ((ApiResponseElement) response).getValue();
        while (true) {
            progress = Integer.parseInt(((ApiResponseElement) api.ascan.status(scanId)).getValue());
            if (progress >= 100) {
                break;
            }
            Thread.sleep(1000);
        }

        // Get the scan results
        ApiResponse alerts = api.core.alerts(baseUrl, null, null);

        // Check if there are any high-risk alerts
        assertThat(alerts.toString()).doesNotContain("High");
    }
}
