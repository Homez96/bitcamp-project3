package bitcamp.project3.command.signin;

import bitcamp.project3.command.Command;
import bitcamp.project3.command.admin.*;
import bitcamp.project3.command.user.BookLoan;
import bitcamp.project3.command.user.ListBook;
import bitcamp.project3.command.user.ReturnBook;
import bitcamp.project3.command.user.SearchBook;
import bitcamp.project3.menu.MenuItem;
import bitcamp.project3.menu.MenuGroup;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class SignIn implements Command {
    private List<User> userList;
    private List<Book> bookList;
    private int key;
    MenuGroup adminPage;
    MenuGroup userPage;


    private void setupMenus() {
        userPage = new MenuGroup("회원");
        userPage.add(new MenuItem("대출하기", new BookLoan(key, userList, bookList)));
        userPage.add(new MenuItem("반납하기", new ReturnBook(key, userList, bookList)));
        userPage.add(new MenuItem("도서검색", new SearchBook(bookList)));
        userPage.add(new MenuItem("전체확인", new ListBook(bookList)));
        userPage.setExitMenuTitle("로그아웃");

        adminPage = new MenuGroup("관리자");
        adminPage.add(new MenuItem("도서추가", new AddBook(bookList)));
        adminPage.add(new MenuItem("도서 전체보기", new ListBook(bookList)));
        adminPage.add(new MenuItem("도서수정", new EditBook(bookList)));
        adminPage.add(new MenuItem("도서삭제", new DeleteBook(bookList)));
        adminPage.add(new MenuItem("회원정보 보기", new ListUser(userList)));
        adminPage.add(new MenuItem("관리자 관한부여", new UpdateUser(userList)));
        adminPage.add(new MenuItem("회원삭제", new DeleteUser(userList)));
        adminPage.setExitMenuTitle("로그아웃");
        // 관리자 메뉴 항목 추가
    }
    public SignIn(List userList, List bookList)
    {
        this.userList = userList;
        this.bookList = bookList;
    }

    @Override
    public void execute(String menuName) {
        String id = Prompt.input("아이디를 입력해주세요 : ");
        String pw = Prompt.input("비밀번호를 입력해주세요 : ");
        key = findUser(userList, id, pw);
        if (key == -1)
        {
            System.out.println("아이디 및 비밀번호가 잘못 입력되었나 없는 계정입니다.");
        }else
        {
            User user= userList.get(key);
            setupMenus();
            System.out.println(user.getName()+"님 환영합니다");
            if(user.getAdmin())
            {
                adminPage.execute();
            }else
            {
                status(key);
                userPage.execute();
            }
        }
    }

    void status(int key)
    {
        String[] book = new String[3];
        String blank  = "";
        int counter = 0;
        User user = userList.get(key);
        for (int i = 0; i <= 2; i++)
        {
            if (user.getBooks(i) == null)
            {
                book[i] = blank;
                ++counter;
            }else
            {
                book[i] = user.getBooks(i);
            }
        }
        if (counter == 3) {
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.printf("%s, 님의 현재 대출현황 | 대여한 책이 없습니다\n",user.getName());
            System.out.println("------------------------------------------------------------------------------------------------");

        }else
        {
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.printf("%s, 님의 현재 대출현황입니다    %s,   %s,   %s \n",user.getName(), book[0], book[1], book[2]);
            System.out.println("------------------------------------------------------------------------------------------------");

        }
    }

    public int findUser(List<User> userList, String id, String pw) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getId().equals(id) && user.getPw().equals(pw)) {
                return i;
            }
        }
        return -1;
    }
}
