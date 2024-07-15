package bitcamp.project3.util;

import bitcamp.project3.library.AdminManagement;
import bitcamp.project3.library.LibraryManagement;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.List;

public class SignIn {
    private List<User> userList;
    private List<Book> bookList;

    public SignIn(List userList, List bookList)
    {
        this.userList = userList;
        this.bookList = bookList;
    }

    public void signInProcess(){
        String id = Prompt.input("아이디를 입력해주세요 : ");
        String pw = Prompt.input("비밀번호를 입력해주세요 : ");
        int userKey = findUser(userList, id, pw);
        if (userKey == -1)
        {
            System.out.println("아이디 및 비밀번호가 잘못 입력되었나 없는 계정입니다.");
        }else
        {
            User user= userList.get(userKey);
            if(user.getAdmin())
            {
                System.out.println(user.getName()+"님 환영합니다");
                AdminManagement adminManagement = new AdminManagement(userList , bookList);
                adminManagement.adminProcess();
            }else
            {
                System.out.println(user.getName()+"님 환영합니다");
                LibraryManagement libraryManagement = new LibraryManagement(userList, bookList);
                libraryManagement.libraryProcess(userKey);
            }
        }
    }

    public int findUser(List userList, String id, String pw)
    {
        int key = 0;
        for(Object obj : userList)
        {
            User user = (User) obj;
            if(user.getId().equals(id) && user.getPw().equals(pw))
            {
                key = userList.indexOf(user);
                break;
            }else
            {
                key = -1;
                break;
            }
        }
        return key;
    }
}
