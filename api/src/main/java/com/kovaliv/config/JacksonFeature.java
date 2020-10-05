package com.kovaliv.config;

import org.springframework.stereotype.Component;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

@Component
public class JacksonFeature implements Feature {
    @Override
    public boolean configure(FeatureContext featureContext) {
        featureContext.register(DateProvider.class);
        return true;
    }
}
