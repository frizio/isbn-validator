package cloud.frizio.dev.isbntools;

/*
import org.junit.Test;
import static org.junit.Assert.assertTrue;
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ISBNValidatorTest {

    @Test
    public void checkValid10DigitISBN() {
      ISBNValidator validator = new ISBNValidator();
      boolean result = validator.checkISBN("8807883686");
      assertTrue(result, "First value");
      // Test another value
      result = validator.checkISBN("8807886871");
      assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalid10DigitISBN() {
      ISBNValidator validator = new ISBNValidator();
      boolean result = validator.checkISBN("8807883687");
      assertFalse(result);
    }

    @Test
    public void nineDigitISBNsAreNotAllowed() {
      ISBNValidator validator = new ISBNValidator();
      assertThrows(
        NumberFormatException.class, 
        () -> { 
          validator.checkISBN("123456789");
        } 
      );
    }

    @Test
    public void nonNumericISBNAreNotAllowed() {
      ISBNValidator validator = new ISBNValidator();
      assertThrows(
        NumberFormatException.class, 
        () -> { 
          validator.checkISBN("helloworld");
        } 
      );
    }

    @Test
    public void TenDigitISBNnumberEndingWithXareValid() {
      ISBNValidator validator = new ISBNValidator();
      boolean result = validator.checkISBN("012000030X");
      assertTrue(result);
    }

    // 13 Digits ISBN number
    @Test
    public void checkValid13DigitsISBN() {
      ISBNValidator validator = new ISBNValidator();
      boolean result = validator.checkISBN("9781853260087");
      assertTrue(result, "First value");
      // Test another value
      result = validator.checkISBN("9781853267338");
      assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalid13DigitISBN() {
      ISBNValidator validator = new ISBNValidator();
      boolean result = validator.checkISBN("9781853260088");
      assertFalse(result);
    }

}
