package cloud.frizio.dev.isbnvalidator;

/*
import org.junit.Test;
import static org.junit.Assert.assertTrue;
*/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkValidISBN(807883686);
      assertTrue(result);
    }

    @Test
    public void checkInvalidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkValidISBN(807883687);
      assertFalse(result);
    }

}
