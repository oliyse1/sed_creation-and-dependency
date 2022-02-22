package ic.doc.catalogues;

import ic.doc.Book;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class BritishLibraryCatalogueTest {

  BritishLibraryCatalogue britishLibraryCatalogue = BritishLibraryCatalogue.getInstance();

  @Test
  public void searchesForBooksInBritishLibraryCatalogueByAuthorSurname() {

    List<Book> books = britishLibraryCatalogue.searchFor("LASTNAME='dickens' ");

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueByAuthorFirstname() {

    List<Book> books = britishLibraryCatalogue.searchFor("FIRSTNAME='Jane' ");

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueByTitle() {

    List<Book> books = britishLibraryCatalogue.searchFor("TITLECONTAINS(Two Cities) ");

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
    assertTrue(books.get(0).matchesTitle("two cities"));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = britishLibraryCatalogue.searchFor("PUBLISHEDBEFORE(1700) ");

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
    assertTrue(books.get(0).publishedBefore(1700));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = britishLibraryCatalogue.searchFor("PUBLISHEDAFTER(1950) ");

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
    assertTrue(books.get(0).publishedSince(1950));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books =
        britishLibraryCatalogue.searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
    assertTrue(books.get(0).publishedBefore(1840));
  }

  @Test
  public void searchesForBooksInBritishLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books =
        britishLibraryCatalogue.searchFor(
            "TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ");

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
    assertTrue(books.get(0).matchesTitle("of"));
    assertTrue(books.get(0).publishedSince(1850));
    assertTrue(books.get(0).publishedBefore(2000));
  }
}
