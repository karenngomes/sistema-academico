package com.karenngomes.sistema;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SystemApp extends Application<SystemConfig> {
	
	public static void main(String[] args) throws Exception {
        new SystemApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<SystemConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SystemConfig configuration,
                    Environment environment) {
//        configuration.get
    }
}
