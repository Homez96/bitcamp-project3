package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class BookLoan implements Command
{
    private List<Book> bookList;
    private List<User> userList;
    private int key;

    public BookLoan(int key, List<User> userList,  List<Book> bookList) {
        this.bookList = bookList;
        this.userList = userList;
        this.key = key;
    }

    @Override
    public void execute(String menuName) {
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
        boolean check = false;
        for(Object obj : bookList)
        {
            Book book = (Book) obj;
            if(book.getTitle().equals(bookName)) {
                if(book.getLoan()){
                    User user = userList.get(key);
                    if (user.getBooks(0) == null || user.getBooks(1) == null || user.getBooks(2) == null) {
                        for (int i = 0; i < 3; i++) {
                            if (user.getBooks(i) == null) {
                                user.setBooks(book.getTitle(), i);
                                book.setLoan(false);
                                System.out.println("대출 되었습니다");
                                check = true;
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
        if (!check)
        {
            System.out.println("없는책이거나 잘못된 접근입니다");
        }
    }
}
