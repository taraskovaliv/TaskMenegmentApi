package com.kovaliv;

import com.kovaliv.config.LiquibaseUtil;
import org.junit.jupiter.api.Test;

public class LiquibaseUtilTest {

    @Test
    void testUpate() {
        LiquibaseUtil.update();
    }
}
