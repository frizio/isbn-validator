package cloud.frizio.dev.isbnvalidator;

/*
import org.junit.Test;
import static org.junit.Assert.assertTrue;
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkISBN("8807883686");
      assertTrue(result, "First value");
      // Test another value
      result = validator.checkISBN("8807886871");
      assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalidISBN() {
      ValidateISBN validator = new ValidateISBN();
      boolean result = validator.checkISBN("8807883687");
      assertFalse(result);
    }

    @Test
    public void nineDigitISBNsAreNotAllowed() {
      ValidateISBN validator = new ValidateISBN();
      assertThrows(
        NumberFormatException.class, 
        () -> { 
          validator.checkISBN("123456789");
        } 
      );
    }

    @Test
    public void nonNumericISBNAreNotAllowed() {
      ValidateISBN validator = new ValidateISBN();
      assertThrows(
        NumberFormatException.class, 
        () -> { 
          validator.checkISBN("helloworld");
        } 
      );
    }

}
