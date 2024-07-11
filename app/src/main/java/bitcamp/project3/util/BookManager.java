package bitcamp.project3.util;

import bitcamp.project3.vo.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> bookList = new ArrayList<>();

    public void addBook() {
        String title = Prompt.input("책 제목: ");
        String writer = Prompt.input("저자: ");
        String genre = Prompt.input("장르: ");
        bookList.add(new Book(title, writer, genre));
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
        String searchType = Prompt.input("검색할 타입을 입력하세요 (제목/저자): ");
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
}
