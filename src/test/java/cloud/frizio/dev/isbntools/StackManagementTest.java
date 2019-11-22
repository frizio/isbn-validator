package cloud.frizio.dev.isbntools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StackManagementTest {

  @Test
  public void canGetAcorrectLocatorCode() {
    String isbn = "8807883686";
    StockManager stockManager = new StockManager();
    String locatorCode = stockManager.getLocatorCode(isbn);
    assertEquals("7693J4", locatorCode);
  }

  
}