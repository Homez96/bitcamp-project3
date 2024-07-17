package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;

import java.util.List;

public class AddBook implements Command {
    List<Book> bookList;

    public AddBook(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        Book book = new Book();
        book.setTitle(Prompt.input("책 제목 ? "));
        book.setWriter(Prompt.input("저자 ? "));
        book.setGenre(Prompt.input("장르 ? "));
        book.setLoan(true);
        bookList.add(book);

        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("책 추가 완료:");
        System.out.println("제목: " + book.getTitle());
        System.out.println("저자: " + book.getWriter());
        System.out.println("장르: " + book.getGenre());
        System.out.println("-----------------------");
    }
}
