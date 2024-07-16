package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.Book;

import java.util.List;

public class ListBook implements Command {
    private List<Book> bookList;

    public ListBook(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        int i = 0;
        if (bookList.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
        } else {
            for (Book book : bookList) {
                if (book.getLoan())
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%d    |   %s   |   %s   |   %s   |   \033[0;34m%s\033[0m \n", i+1,book.getTitle(), book.getWriter(), book.getGenre(), "대출 가능");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }else
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%s   |   %s   |   %s   |   %s   |   \033[0;31m%s\033[0m \n",book.getTitle(),i+1, book.getWriter(), book.getGenre(), "대출 불가");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
                ++i;
            }
        }
    }
}
