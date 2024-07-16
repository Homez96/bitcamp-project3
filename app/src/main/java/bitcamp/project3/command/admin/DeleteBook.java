package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;

import java.util.List;

public class DeleteBook implements Command {
    List<Book> bookList;

    public DeleteBook(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        int bookNum = Prompt.inputInt("삭제하고자 하는 책의 번호를 입력해주세요 : ");
        Book book = bookList.remove(bookNum-1);
        System.out.println("---------------------------");
        System.out.printf("%s 도서 삭제 완료. \n",book.getTitle());
        System.out.println("---------------------------");
    }
}
