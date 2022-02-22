package ic.doc;

import ic.doc.catalogues.LibraryCatalogue;

// Builder class to create a more user friendly API for clients who wish to construct BookSeachQuery
// objects
public class BookSearchQueryBuilder {
  private LibraryCatalogue libraryCatalogue;
  private String name1;
  private String name2;
  private String title;
  private Integer date1;
  private Integer date2;

  // A LibraryCatalogue object is required to be passed in as a parameter in this method for
  // constructing new objects of the class
  public static BookSearchQueryBuilder aBookSearchQuery(LibraryCatalogue libraryCatalogue) {
    return new BookSearchQueryBuilder(libraryCatalogue);
  }

  // A LibraryCatalogue object is required to be passed in as a parameter in the constructor
  private BookSearchQueryBuilder(LibraryCatalogue libraryCatalogue) {
    this.libraryCatalogue = libraryCatalogue;
  }

  public BookSearchQuery build() {
    BookSearchQuery bookSearchQuery =
        new BookSearchQuery(libraryCatalogue, name1, name2, title, date1, date2);
    return bookSearchQuery;
  }

  public BookSearchQueryBuilder withFirstName(String name1) {
    this.name1 = name1;
    return this;
  }

  public BookSearchQueryBuilder withLastName(String name2) {
    this.name2 = name2;
    return this;
  }

  public BookSearchQueryBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public BookSearchQueryBuilder publishedAfter(Integer date1) {
    this.date1 = date1;
    return this;
  }

  public BookSearchQueryBuilder publishedBefore(Integer date2) {
    this.date2 = date2;
    return this;
  }
}
