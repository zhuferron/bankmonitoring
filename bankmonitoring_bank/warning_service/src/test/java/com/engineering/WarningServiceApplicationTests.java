package com.engineering;

import com.engineering.service.MonitorConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WarningServiceApplicationTests {

    @Autowired
    MonitorConsumer monitorConsumer;

    @Test
    void contextLoads() {

    }

}
