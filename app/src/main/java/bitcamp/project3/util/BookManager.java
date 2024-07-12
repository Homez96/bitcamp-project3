package bitcamp.project3.util;

import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> bookList;
    private List<User> userList;

    public BookManager(List<Book> bookList, List<User> userList) {
        this.bookList = bookList;
        this.userList = userList;
    }


    public void bookLoan(int key)
    {
        for (int i = 0; i < bookList.size(); i++)
        {
            Book book = bookList.get(i);
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.printf("%s   |   %s  |   %s  |   %b \n",book.getTitle(), book.getWriter(), book.getGenre(), book.getLoan());
                System.out.println("-------------------------------------------------------------------------------------------------");
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

    public void addBook() {
        String title = Prompt.input("책 제목: ");
        String writer = Prompt.input("저자: ");
        String genre = Prompt.input("장르: ");
       // bookList.add(new Book(title, writer, genre));
        System.out.println("책이 추가되었습니다.");
    }

    public void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
        } else {
            for (Book book : bookList) {
                System.out.println(book);
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
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("검색 결과가 없습니다.");
        }
    }


    public void updateGenre() {
        String title = Prompt.input("장르를 수정할 책 제목을 입력하세요: ");
        for (Book book : bookList) {
            if (book.matchesTitle(title)) {
                String newGenre = Prompt.input("새로운 장르를 입력하세요: ");
                book.setGenre(newGenre);
                System.out.println("장르가 수정되었습니다.");
                return;
            }
        }
        System.out.println("해당 제목의 책을 찾을 수 없습니다.");
    }

    public void returnBook() {
        System.out.println("반납되었습니다");
    }
}
