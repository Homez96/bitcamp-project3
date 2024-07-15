package bitcamp.project3.library;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminManagement {
    private ArrayList<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        AdminManagement admin = new AdminManagement();
        admin.adminProcess();
    }

    public void adminProcess() {
        Scanner scanner = new Scanner(System.in);
        String startTitle = "관리자 모드를 실행합니다";
        String line = "---------------------------";
        String title = "[관리자 시스템]";
        String bookManagement = "1. 도서 관리";
        String membership = "2. 회원 관리";
        String exit = "0. 종료";

        while (true) {
            System.out.println(line);
            System.out.println(startTitle);
            System.out.println(line);
            System.out.println(title);
            System.out.println(bookManagement);
            System.out.println(membership);
            System.out.println(exit);
            System.out.print("원하시는 메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageBooks(scanner, line);
                    break;
                case 2:
                    manageMembers(scanner);
                    break;
                case 0:
                    System.out.println("시스템을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void manageBooks(Scanner scanner, String line) {
        String addBook = "1. 책 추가";
        String viewBooks = "2. 전체 보기";
        String back = "0. 돌아가기";

        while (true) {
            System.out.println("[도서 관리]");
            System.out.println(addBook);
            System.out.println(viewBooks);
            System.out.println(back);
            System.out.print("원하시는 메뉴를 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner, line);
                    break;
                case 2:
                    viewBooks(scanner, line);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void addBook(Scanner scanner, String line) {
        System.out.print("책 제목? ");
        String bookTitle = scanner.nextLine();
        System.out.print("저자? ");
        String writer = scanner.nextLine();
        System.out.print("장르? ");
        String genre = scanner.nextLine();

        bookList.add(new Book(bookTitle, writer, genre));
        System.out.println(line);
        System.out.println("책 추가 완료:");
        System.out.println("제목: " + bookTitle);
        System.out.println("저자: " + writer);
        System.out.println("장르: " + genre);
        System.out.println(line);
    }

    private void viewBooks(Scanner scanner, String line) {
        if (bookList.isEmpty()) {
            System.out.println(line);
            System.out.println("추가된 도서가 없습니다.");
            System.out.println(line);
            return;
        }

        System.out.println(line);
        System.out.println("[추가된 도서 목록]");
        System.out.println(line);
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i + 1) + ". " + bookList.get(i));
        }
        System.out.println(line);

        String bookManagement = "1. 도서 수정/삭제";
        String back = "0. 돌아가기";

        while (true) {
            System.out.println(bookManagement);
            System.out.println(back);
            System.out.print("원하는 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageSpecificBook(scanner, line);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private void manageSpecificBook(Scanner scanner, String line) {
        System.out.print("수정/삭제할 도서 번호를 입력하세요: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine();

        if (bookNumber < 1 || bookNumber > bookList.size()) {
            System.out.println("잘못된 도서 번호입니다.");
            return;
        }

        Book book = bookList.get(bookNumber - 1);
        System.out.println("선택된 도서: " + book);

        String editBook = "1. 도서 수정";
        String deleteBook = "2. 도서 삭제";
        String back = "0. 돌아가기";

        while (true) {
            System.out.println(editBook);
            System.out.println(deleteBook);
            System.out.println(back);
            System.out.print("원하는 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    editBook(scanner, book, line);
                    return;
                case 2:
                    deleteBook(bookNumber - 1);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private void editBook(Scanner scanner, Book book, String line) {
        System.out.println(line);
        System.out.print("새 책 제목? ");
        String bookTitle = scanner.nextLine();
        System.out.print("새 저자? ");
        String author = scanner.nextLine();
        System.out.print("새 장르? ");
        String genre = scanner.nextLine();

        book.setTitle(bookTitle);
        book.setWriter(author);
        book.setGenre(genre);
        System.out.println(line);
        System.out.println("도서 수정 완료:");
        System.out.println("제목: " + bookTitle);
        System.out.println("저자: " + author);
        System.out.println("장르: " + genre);
        System.out.println(line);
    }

    private void deleteBook(int bookIndex) {
        bookList.remove(bookIndex);
        System.out.println("---------------------------");
        System.out.println("도서 삭제 완료.");
        System.out.println("---------------------------");
    }

    private void manageMembers(Scanner scanner) {
        String overdueManagement = "1. 장기 미납자 관리";
        String back = "0. 돌아가기";

        while (true) {
            System.out.println(overdueManagement);
            System.out.println(back);
            System.out.print("원하는 작업을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageOverdueMembers(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }
    }

    private void manageOverdueMembers(Scanner scanner) {
        System.out.println("장기 미납자 관리를 시작합니다.");
    }

    private class Book {
        private String title;
        private String writer;
        private String genre;

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

        @Override
        public String toString() {
            return "제목: " + title + ", 저자: " + writer + ", 장르: " + genre;
        }
    }
}
