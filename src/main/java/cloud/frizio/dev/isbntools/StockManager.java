package cloud.frizio.dev.isbntools;

public class StockManager {

  private ExternalISBNDataService service;

  public void setService(ExternalISBNDataService service) {
    this.service = service;
  }

  /*
   Business Logic Requirement: Creation of locator code
   The implementation has a dependency of some external service:
   the test doesn't require the external service, but the MOCKUP
  */

	public String getLocatorCode(String isbn) {
    Book book = service.lookup(isbn);
    StringBuilder locator = new StringBuilder();
    locator.append(isbn.substring(isbn.length()-4));
    locator.append(book.getAuthor().substring(0, 1));
    locator.append(book.getTitle().split(" ").length);
    return locator.toString();
	}

}
