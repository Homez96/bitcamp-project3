package bitcamp.project3.vo;

public class Book {
    private String title;
    private String writer;
    private String genre;
    private Boolean Loan;

    public Book(String title, String writer, String genre) {
        this.title = title;
        this.writer = writer;
        this.genre = genre;
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

    @Override
    public String toString() {
        return "책 제목: " + title + ", 저자: " + writer + ", 장르: " + genre;
    }
}
