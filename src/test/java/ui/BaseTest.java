package ui;

import com.george.taf.utils.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeEach
    public void openDriver() {
        Driver.getDriver();
    }

    @AfterEach
    public void closeDriver() {
        Driver.quitDriver();
    }
}
