package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import ic.doc.catalogues.LibraryCatalogue;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import static ic.doc.BookSearchQueryBuilder.aBookSearchQuery;

public class BookSearchQueryTest {

  // Create rule to manage JMock expectations
  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  // Create mock object of LibraryCatalogue
  LibraryCatalogue libraryCatalogue = context.mock(LibraryCatalogue.class);

  private void checkExpectations(String queryString) {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(libraryCatalogue).searchFor(queryString);
          }
        });
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueByAuthorSurname() {

    // Check that this query is passed as a parameter when searchFor is called under
    // LibraryCatalogue
    String queryString = "LASTNAME='dickens' ";
    checkExpectations(queryString);
    List<Book> books = aBookSearchQuery(libraryCatalogue).withLastName("dickens").build().execute();
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueByAuthorFirstname() {

    String queryString = "FIRSTNAME='Jane' ";
    checkExpectations(queryString);
    List<Book> books = aBookSearchQuery(libraryCatalogue).withFirstName("Jane").build().execute();
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueByTitle() {

    String queryString = "TITLECONTAINS(Two Cities) ";
    checkExpectations(queryString);
    List<Book> books = aBookSearchQuery(libraryCatalogue).withTitle("Two Cities").build().execute();
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    String queryString = "PUBLISHEDBEFORE(1700) ";
    checkExpectations(queryString);
    List<Book> books = aBookSearchQuery(libraryCatalogue).publishedBefore(1700).build().execute();
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    String queryString = "PUBLISHEDAFTER(1950) ";
    checkExpectations(queryString);
    List<Book> books = aBookSearchQuery(libraryCatalogue).publishedAfter(1950).build().execute();
  }

  @Test
  public void checkQueryStringWhenSearchingForBooksInLibraryCatalogueWithCombinationOfParameters() {

    String queryString = "LASTNAME='dickens' PUBLISHEDBEFORE(1840) ";
    checkExpectations(queryString);
    List<Book> books =
        aBookSearchQuery(libraryCatalogue)
            .withLastName("dickens")
            .publishedBefore(1840)
            .build()
            .execute();
  }

  @Test
  public void
      checkQueryStringWhenSearchingForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    String queryString = "TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ";
    checkExpectations(queryString);
    List<Book> books =
        aBookSearchQuery(libraryCatalogue)
            .withTitle("of")
            .publishedAfter(1800)
            .publishedBefore(2000)
            .build()
            .execute();
  }
}
