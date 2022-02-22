package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;
import static ic.doc.BookSearchQueryBuilder.*;

public class BookSearchQueryTest {

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = aBookSearchQuery().withLastName("dickens").build().execute();
//    List<Book> books = new BookSearchQuery(null, "dickens", null, null, null).execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = aBookSearchQuery().withFirstName("Jane").build().execute();
//    List<Book> books = new BookSearchQuery("Jane", null, null, null, null).execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = aBookSearchQuery().withTitle("Two Cities").build().execute();
//    List<Book> books = new BookSearchQuery(null, null, "Two Cities", null, null).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = aBookSearchQuery().publishedBefore(1700).build().execute();
//    List<Book> books = new BookSearchQuery(null, null, null, null, 1700).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = aBookSearchQuery().publishedAfter(1950).build().execute();
//    List<Book> books = new BookSearchQuery(null, null, null, 1950, null).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = aBookSearchQuery().withLastName("dickens").publishedBefore(1840).build().execute();
//    List<Book> books = new BookSearchQuery(null, "dickens", null, null, 1840).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = aBookSearchQuery().withTitle("of").publishedAfter(1800).publishedBefore(2000).build().execute();
//    List<Book> books = new BookSearchQuery(null, null, "of", 1800, 2000).execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
