package bitcamp.project3.vo;

import java.util.Objects;

public class Book {
    private String title;
    private String writer;
    private String genre;
    private Boolean loan;

    public Book(){}

    public Book(String title, String writer, String genre, boolean loan) {
        this.title = title;
        this.writer = writer;
        this.genre = genre;
        this.loan = loan;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return Objects.equals(title, book.title) && Objects.equals(writer, book.writer) && Objects.equals(genre, book.genre) && Objects.equals(loan, book.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, writer, genre, loan);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean matchesTitle(String title) {
        return this.title.equalsIgnoreCase(title);
    }

    public boolean matchesWriter(String writer) {
        return this.writer.equalsIgnoreCase(writer);
    }

    public Boolean getLoan() {
        return loan;
    }

    public void setLoan(Boolean loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "책 제목: " + title + ", 저자: " + writer + ", 장르: " + genre;
    }
}
