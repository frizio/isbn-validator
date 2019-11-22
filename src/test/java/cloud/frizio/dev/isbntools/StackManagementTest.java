package cloud.frizio.dev.isbntools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StackManagementTest {

  @Test
  public void canGetAcorrectLocatorCode() {

    // MOCKUP of the external service
    ExternalISBNDataService testService = new ExternalISBNDataService() {
      @Override
      public Book lookup(String isbn) {
        // This is a test STUB: dependency required bu the testing method
        return new Book(isbn, "Of Mice and Man", "J. Steinbeck");
      }
    };

    StockManager stockManager = new StockManager();
    stockManager.setService(testService);

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);
    assertEquals("7396J4", locatorCode);
  }


}