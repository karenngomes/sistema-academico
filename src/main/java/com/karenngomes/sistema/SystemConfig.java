package com.karenngomes.sistema;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Getter
public class SystemConfig extends Configuration {

	@NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";
    
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
}
