package pl.codeleak.samples.junit5.guice;

public class BasicCalculatorService implements CalculatorService {
    @Override
    public void calculate() {
        System.out.println("BasicCalculatorService.calculate");
    }
}
