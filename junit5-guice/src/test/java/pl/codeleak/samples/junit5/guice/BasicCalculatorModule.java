package pl.codeleak.samples.junit5.guice;

import com.google.inject.AbstractModule;

public class BasicCalculatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CalculatorService.class).to(BasicCalculatorService.class);
    }
}
