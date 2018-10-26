package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

class _2_BuiltInAssertions {

    @Test
    void basicAssertions() {
        // act
        List<String> owners = Arrays.asList("Betty Davis", "Eduardo Rodriquez");

        // assert
        Assertions.assertNotNull(owners);
        Assertions.assertSame(owners, owners);
        Assertions.assertTrue(!owners.isEmpty());
        Assertions.assertFalse(owners::isEmpty);
        Assertions.assertEquals(2, owners.size(), "Found owner names size is incorrect");
        Assertions.assertLinesMatch(Arrays.asList("Betty Davis", "Eduardo Rodriquez"), owners);
        Assertions.assertArrayEquals(new String[]{"Betty Davis", "Eduardo Rodriquez"}, owners.toArray(new String[0]));
    }
}
