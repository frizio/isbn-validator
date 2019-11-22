package cloud.frizio.dev.isbntools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StackManagementTest {

  @Test
  public void canGetAcorrectLocatorCode() {

    // MOCKUP of the external WEB service
    ExternalISBNDataService webService = new ExternalISBNDataService() {
      @Override
      public Book lookup(String isbn) {
        // This is a test STUB: dependency required bu the testing method
        return new Book(isbn, "Of Mice and Man", "J. Steinbeck");
      }
    };

    // MOCKUP of the external DATABASE service
    ExternalISBNDataService databaseService = new ExternalISBNDataService() {
      @Override
      public Book lookup(String isbn) {
        return null;
      }
    };

    StockManager stockManager = new StockManager();
    stockManager.setWebService(webService);
    stockManager.setDatabaseService(databaseService);

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);
    assertEquals("7396J4", locatorCode);
  }


}