package squareonex.evetracker.converters;

import org.junit.jupiter.api.Test;

public abstract class ConverterTestTemplate {
    @Test
    protected abstract void convertingNullShouldReturnNull();
    @Test
    protected abstract void convertingEmptyObjectShouldReturnEmptyObject();
    @Test
    protected abstract void convertShouldReturnValidTarget();
}
