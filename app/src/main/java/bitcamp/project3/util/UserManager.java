package bitcamp.project3.util;

import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class UserManager {
    private List<Book> bookList;
    private List<User> userList;

    public UserManager(List<Book> bookList, List<User> userList) {
        this.bookList = bookList;
        this.userList = userList;
    }


    public void bookLoan(int key)
    {
        for (int i = 0; i < bookList.size(); i++)
        {
            Book book = bookList.get(i);
            if (book.getLoan())
            {
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%s   |   %s  |   %s  |   \033[0;34m%s\033[0m \n",book.getTitle(), book.getWriter(), book.getGenre(), "대출 가능");
                System.out.println("-------------------------------------------------------------------------------------------------");
            }else
            {
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%s   |   %s  |   %s  |   \033[0;31m%s\033[0m \n",book.getTitle(), book.getWriter(), book.getGenre(), "대출 불가");
                System.out.println("-------------------------------------------------------------------------------------------------");
            }

        }
        String bookName = Prompt.input("대여하고자 하는 책의 이름을 입력해주세요 : ");
        for(Object obj : bookList)
        {
            Book book = (Book) obj;
            if(book.getTitle().trim().equals(bookName)) {
                if(book.getLoan()){
                book.setLoan(false);
                User user = userList.get(key);
                if (user.getBooks(0) == null || user.getBooks(1) == null || user.getBooks(2) == null) {
                    for (int i = 0; i < 3; i++) {
                        if (user.getBooks(i) == null) {
                            user.setBooks(book.getTitle(), i);
                            System.out.println("대출 되었습니다");
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println(" 현재 회원님은 최대로 빌릴 수 있는 3권을 전부 대여중인 상태입니다 반납후에 다시 이용해 주세요");
                }
                }else
                {
                    System.out.println("대여가 불가능한 책입니다");
                    break;
                }
            }
        }
    }

    public void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
        } else {
            for (Book book : bookList) {
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
            }
        }
    }

    public void searchBook() {
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

    public void returnBook(int key) {
        int bookNum;
        User user = userList.get(key);

        if (user.getBooks(0) == null && user.getBooks(1) == null && user.getBooks(2) == null) {
            System.out.println("현재 대출하신 도서가 없습니다");
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (user.getBooks(i) == null) {
                String blank = "";
                if (i == 2) {
                    System.out.printf("%d번 | %s\n", i + 1, blank);
                } else {
                    System.out.printf("%d번 | %s  ", i + 1, blank);
                }
            } else {
                if (i == 2) {
                    System.out.printf("%d번 | %s\n", i + 1, user.getBooks(i));
                } else {
                    System.out.printf("%d번 | %s  ", i + 1, user.getBooks(i));
                }
            }
        }

        while (true) {
            bookNum = Prompt.inputInt("반납하실 책 번호를 입력해주세요 :");
            if (isValidate(bookNum) && user.getBooks(bookNum - 1) != null) {
                break;
            } else {
                System.out.println("없는 번호이거나 해당 번호에 책이 없습니다");
            }
        }

        String bookName = user.getBooks(bookNum - 1);

        for (Object obj : bookList) {
            Book book = (Book) obj;
            if (book.getTitle().equals(bookName)) {
                book.setLoan(true);
                break;
            }
        }

        for (int i = bookNum - 1; i < 2; i++) {
            user.setBooks(user.getBooks(i + 1), i);
        }
        user.setBooks(null, 2);

        System.out.println("도서가 반납되었습니다.");
    }

    Boolean isValidate(int command) {
        return command >= 1 && command <= 3;
    }
}
