package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;

import java.util.List;

public class SearchBook implements Command {
    private List<Book> bookList;

    public SearchBook(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        String searchType;
        while (true) {
            searchType = Prompt.input("검색할 타입을 입력하세요 (제목/저자): ");
            if (searchType.equalsIgnoreCase("제목") || searchType.equalsIgnoreCase("저자")) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        String searchValue = Prompt.input("검색어를 입력하세요: ");

        boolean found = false;
        for (Book book : bookList) {
            if ((searchType.equalsIgnoreCase("제목") && book.matchesTitle(searchValue)) ||
                    (searchType.equalsIgnoreCase("저자") && book.matchesWriter(searchValue))) {
                if (book.getLoan())
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%s   |   %s   |   %s   |   \033[0;34m%s\033[0m \n",book.getTitle(), book.getWriter(), book.getGenre(), "대출 가능");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }else
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%s   |   %s   |   %s   |   \033[0;31m%s\033[0m \n",book.getTitle(), book.getWriter(), book.getGenre(), "대출 불가");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }
}
