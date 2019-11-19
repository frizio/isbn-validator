package cloud.frizio.dev.isbnvalidator;

/*
import org.junit.Test;
import static org.junit.Assert.assertTrue;
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkValidISBN(807883686);
      assertTrue(result, "First value");
      // Test another value
      result = validator.checkValidISBN(807886871);
      assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkValidISBN(807883687);
      assertFalse(result);
    }

}
