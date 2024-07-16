package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class EditBook implements Command {
    List<Book> bookList;

    public EditBook(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        int bookNum = Prompt.inputInt("수정하고자 하는 책 번호를 입력해 주세요 : ");
        Book book = bookList.get(bookNum-1);

        book.setTitle(Prompt.input("%s => 새 책 제목 ? ", book.getTitle()));
        book.setWriter(Prompt.input("%s => 새 저자 ?", book.getWriter()));
        book.setGenre(Prompt.input("%s => 새 장르 ? ", book.getGenre()));
        book.setLoan(Boolean.parseBoolean(Prompt.input("%b => 대출상태 ? ", book.getLoan())));

        System.out.println("도서 수정 완료:");
        System.out.println("제목: " + book.getTitle());
        System.out.println("저자: " + book.getWriter());
        System.out.println("장르: " + book.getGenre());
        if (book.getLoan())
        {
            System.out.println("대출상태: " + "\033[0;34m 대출 가능 \033[0m ");
        }else
        {
            System.out.println("대출상태: " + "\033[0;31m 대출 불가 \033[0m");
        }
    }
}
