package cloud.frizio.dev.isbnvalidator;

/*
import org.junit.Test;
import static org.junit.Assert.assertTrue;
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkValidISBN(807883686);
      assertTrue(result);
    }
}
