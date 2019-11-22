package cloud.frizio.dev.isbntools;


public interface ExternalISBNDataService {

  public Book lookup(String isbn);

}