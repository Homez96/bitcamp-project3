package bitcamp.project3;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.util.SignIn;
import bitcamp.project3.util.SignUp;
import bitcamp.project3.util.BookManager;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;

public class App {

    String[] loginMenus = {"로그인", "회원가입", "종료하기", "책 추가",  "책 목록 보기", "책 검색", "장르 수정"};
    SignUp signUpCommand;
    SignIn signInCommand;
    BookManager bookManager;

    public App()
    {
        List<User> userList = new ArrayList<>();
        signUpCommand = new SignUp(userList);
        signInCommand = new SignIn(userList);
        bookManager = new BookManager();
    }

    public static void main(String[] args) {
        new App().loading();
    }

    void loading()
    {
        printMenu();
        while (true)
        {
            try {
                String command = Prompt.input("메인 > ");
                if (command.equals("menu"))
                {
                    printMenu();
                    continue;
                }
                int num = Integer.parseInt(command);
                String menuTitle = getTitle(loginMenus, num);
                if(menuTitle.equals("종료하기"))
                {
                    System.out.println("종료하기");
                    break;
                }
                switch (menuTitle)
                {
                    case "로그인":
                        if(signInCommand.signInProcess())
                        {
                            System.out.println("로그인이 되었습니다");
                            break;
                        }else {
                            System.out.println("아이디 또는 비밀번호가 없거나 틀립니다 다시 입력해주세요");
                            break;
                        }
                    case "회원가입":
                        signUpCommand.signUpProcess();
                        break;
                    case "책 추가":
                        bookManager.addBook();
                        break;
                    case "책 목록 보기":
                        bookManager.listBooks();
                        break;
                    case "책 검색":
                        bookManager.searchBook();
                        break;
                    case "장르 수정":
                        bookManager.updateGenre();
                        break;
                    default:
                        System.out.println("없는 메뉴 입니다");
                }

            }catch (NumberFormatException e)
            {
                System.out.println("문자는 menu 말고 안됩니다");
            }

        }
    }

    String getTitle(String[] loginMenus, int command)
    {
        return isValidate(loginMenus, command) ? loginMenus[command-1] : null;
    }

    Boolean isValidate(String[] loginMenus, int command)
    {
        return command >= 1 && command <= loginMenus.length;
    }

    void printMenu()
    {
        System.out.println("----------------------------------");
        for (int i = 0; i < loginMenus.length; i++) {
            if (i + 1 == loginMenus.length) {
                System.out.printf("%d. %s \n", i + 1, loginMenus[i]);
            } else {
                System.out.printf("%d. %s \t", i + 1, loginMenus[i]);
            }
        }
        System.out.println("----------------------------------");
    }
}
