package cloud.frizio.dev.isbntools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StackManagementTest {

  ExternalISBNDataService webService;
  ExternalISBNDataService databaseService;
  StockManager stockManager;

  @BeforeEach
  public void setup() {
    System.out.println("Setup test");
    this.webService = Mockito.mock(ExternalISBNDataService.class);
    this.databaseService = Mockito.mock(ExternalISBNDataService.class);
    stockManager = new StockManager();
    stockManager.setWebService(webService);
    stockManager.setDatabaseService(databaseService);
  }

  @Test
  public void canGetAcorrectLocatorCode() {
    
    Mockito.when(webService.lookup(Mockito.anyString())).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Steinbeck"));
    Mockito.when(databaseService.lookup(Mockito.anyString())).thenReturn(null);

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);

    assertEquals("7396J4", locatorCode);
  }

  @Test
  public void databaseIsUsedIfDataIsPresent() {
    Mockito.when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc")); 

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);

    Mockito.verify(databaseService, Mockito.times(1)).lookup("0140177396");
    Mockito.verify(webService, Mockito.never()).lookup(Mockito.anyString());
  }

  @Test
  public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
    Mockito.when(databaseService.lookup("0140177396")).thenReturn(null);
    Mockito.when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

    String isbn = "0140177396";
    String locatorCode = stockManager.getLocatorCode(isbn);

    Mockito.verify(databaseService).lookup("0140177396");
    Mockito.verify(webService).lookup("0140177396");
  }

  @AfterEach
  public void complete() {
    System.out.println("Test completed");
  }

}
