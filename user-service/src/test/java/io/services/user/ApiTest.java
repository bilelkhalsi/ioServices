/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.services.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApiTest {

    @Test
    public void testAppHasAGreeting() {
        assertNotNull("app should have a greeting");
    }
}