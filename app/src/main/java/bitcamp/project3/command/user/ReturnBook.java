package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class ReturnBook implements Command {
    private List<Book> bookList;
    private List<User> userList;
    private int key;

    public ReturnBook(int key, List<User> userList, List<Book> bookList) {
        this.bookList = bookList;
        this.userList = userList;
        this.key = key;
    }

    @Override
    public void execute(String menuName) {
        int bookNum;
        boolean check = false;
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
            bookNum = Prompt.inputInt("반납하실 책 번호를 입력해주세요 (0)뒤로가기):");
            if (isValidate(bookNum) && user.getBooks(bookNum - 1) != null) {
                break;
            } else if(bookNum == 0){
                System.out.println("뒤로갑니다");
                check = true;
                break;
            }else
            {
                System.out.println("없는 번호이거나 해당 번호에 책이 없습니다");
            }
        }

        if (check)
        {
            return;
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
