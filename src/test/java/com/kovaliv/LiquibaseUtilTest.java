package com.kovaliv;

import com.kovaliv.config.LiquibaseUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LiquibaseUtilTest {

    @Test
    @Disabled
    void testUpate() {
        LiquibaseUtil.update();
    }
}
