package cloud.frizio.dev.isbntools;

public class ISBNValidator {

  private static final int SHORT_ISBN_MULTIPLIER = 11;
  private static final int LONG_ISBN_MULTIPLIER = 10;
  private static final int SHORT_ISBN_FORMAT = 10;
  private static final int LONG_ISBN_FORMAT  = 13;

  public boolean checkISBN(String isbn) {
    if ( isbn.length() == LONG_ISBN_FORMAT ) {
      return isThisAValidLongISBN(isbn);
    } else if ( isbn.length() == SHORT_ISBN_FORMAT ) {
      return isThisAValidShortISBN(isbn);
    }
    throw new NumberFormatException("ISBN number must be 10 or 13 digits long");
  }

  private boolean isThisAValidShortISBN(String isbn) {
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
    return total % SHORT_ISBN_MULTIPLIER == 0;
  }

  private boolean isThisAValidLongISBN(String isbn) {
    int total = 0;
    for (int i = 0; i < 13; i++) {
      if ( i % 2 == 0 ) {
        total += Character.getNumericValue(isbn.charAt(i));
      } else {
        total += Character.getNumericValue(isbn.charAt(i)) * 3;
      }
    }
    return total % LONG_ISBN_MULTIPLIER == 0;
  }
  
}
