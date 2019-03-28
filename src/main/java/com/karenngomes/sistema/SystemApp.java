package com.karenngomes.sistema;

import com.karenngomes.resources.SecretaryResources;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SystemApp extends Application<SystemConfig> {
	
	public static void main(String[] args) throws Exception {
        new SystemApp().run(args);
    }

    @Override
    public String getName() {
        return "Sistema Academico";
    }

    @Override
    public void initialize(final Bootstrap<SystemConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(final SystemConfig configuration,
                    final Environment environment) {
        // TODO: implement application
    	final SecretaryResources resource = new SecretaryResources();
        environment.jersey().register(resource);
    }
}
