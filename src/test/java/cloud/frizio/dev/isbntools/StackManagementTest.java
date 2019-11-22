package cloud.frizio.dev.isbntools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StackManagementTest {

  @Test
  public void canGetAcorrectLocatorCode() {

    // STUB of the external WEB service
    ExternalISBNDataService webService = new ExternalISBNDataService() {
      @Override
      public Book lookup(String isbn) {
        return new Book(isbn, "Of Mice and Man", "J. Steinbeck");
      }
    };

    // STUB of the external DATABASE service
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

  @Test
  public void databaseIsUsedIfDataIsPresent() {
    
    ExternalISBNDataService databaseService = Mockito.mock(ExternalISBNDataService.class);
    ExternalISBNDataService webService = Mockito.mock(ExternalISBNDataService.class);
    
    Mockito.when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc")); 

    StockManager stockManager = new StockManager();
    stockManager.setWebService(webService);
    stockManager.setDatabaseService(databaseService);

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);
    
    Mockito.verify(databaseService, Mockito.times(1)).lookup("0140177396");
    Mockito.verify(webService, Mockito.never()).lookup(Mockito.anyString());

  }

  @Test
  public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
    
    ExternalISBNDataService databaseService = Mockito.mock(ExternalISBNDataService.class);
    ExternalISBNDataService webService = Mockito.mock(ExternalISBNDataService.class);
    
    Mockito.when(databaseService.lookup("0140177396")).thenReturn(null);
    Mockito.when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

    StockManager stockManager = new StockManager();
    stockManager.setWebService(webService);
    stockManager.setDatabaseService(databaseService);

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);
    
    Mockito.verify(databaseService).lookup("0140177396");
    Mockito.verify(webService).lookup("0140177396");

  }

}
