package ui;

import com.george.taf.utils.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    public static final Logger logger = LogManager.getLogger();
    @BeforeEach
    public void openDriver() {
        logger.info("Open Chrome");
        Driver.getDriver();
    }

    @AfterEach
    public void closeDriver() {
        logger.info("Close Chrome\n");
        Driver.quitDriver();
    }
}
