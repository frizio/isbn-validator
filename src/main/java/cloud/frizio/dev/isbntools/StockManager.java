package cloud.frizio.dev.isbntools;

public class StockManager {

  private ExternalISBNDataService webService;
  private ExternalISBNDataService databaseService;

  public void setWebService(ExternalISBNDataService service) {
    this.webService = service;
  }

  public void setDatabaseService(ExternalISBNDataService service) {
    this.databaseService = service;
  }

  /*
   Business Logic Requirement: Creation of locator code
   The implementation has a dependency of some external service:
   the test doesn't require the external service, but the MOCKUP
  */

	public String getLocatorCode(String isbn) {
    Book book = webService.lookup(isbn);
    if (book == null) {
      book = databaseService.lookup(isbn);
    }
    StringBuilder locator = new StringBuilder();
    locator.append(isbn.substring(isbn.length()-4));
    locator.append(book.getAuthor().substring(0, 1));
    locator.append(book.getTitle().split(" ").length);
    return locator.toString();
	}

}
