package cloud.frizio.dev.isbnvalidator;

public class ValidateISBN {

  private static final int SHORT_ISBN_MULTIPLIER = 11;
  private static final int LONG_ISBN_MULTIPLIER = 10;
  private static final int SHORT_ISBN_FORMAT = 10;
  private static final int LONG_ISBN_FORMAT  = 13;

  public boolean checkISBN(String isbn) {
    if ( isbn.length() == LONG_ISBN_FORMAT ) {
      return isThisAValid13DigitISBN(isbn);
    } else {
      if ( isbn.length() != SHORT_ISBN_FORMAT ) {
        throw new NumberFormatException("ISBN number must be 10 digits long");
      }
      return isThisAValid10DigitISBN(isbn);
    }
  }

  private boolean isThisAValid10DigitISBN(String isbn) {
    int total = 0;
    for (int i = 0; i < 10; i++) {
      if ( !Character.isDigit(isbn.charAt(i)) ) {
        if ( i == 9 && isbn.charAt(i) == 'X' ) {
          total += 10;
        } else {
          throw new NumberFormatException("ISBN number can only contain numeric digits");
        }
      } else {
        total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
      }
    }
    if (total % SHORT_ISBN_MULTIPLIER == 0) {
      return true;
    } else {
      return false;
    }
  }

  private boolean isThisAValid13DigitISBN(String isbn) {
    int total = 0;
    for (int i = 0; i < 13; i++) {
      if ( i % 2 == 0 ) {
        total += Character.getNumericValue(isbn.charAt(i));
      } else {
        total += Character.getNumericValue(isbn.charAt(i)) * 3;
      }
    }
    if (total % LONG_ISBN_MULTIPLIER == 0) {
      return true;
    } else {
      return false;
    }
  }
  
}
