package bitcamp.project3;

import bitcamp.project3.library.LibraryManagement;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.util.SignIn;
import bitcamp.project3.util.SignUp;
import bitcamp.project3.util.BookManager;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;

public class App {

    String[] loginMenus = {"로그인", "회원가입", "종료하기"};
    SignUp signUpCommand;
    SignIn signInCommand;
    LibraryManagement libraryManagement;
    List<Book> bookList = new ArrayList<>();
    public App()
    {
        List<User> userList = new ArrayList<>();
        signUpCommand = new SignUp(userList);
        signInCommand = new SignIn(userList);
        libraryManagement = new LibraryManagement(userList , bookList);
    }

    public static void main(String[] args) {
        new App().loading();
    }

    void loading()
    {
        addBook();
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
                        int userKey = signInCommand.signInProcess();
                        if(userKey == -1)
                        {
                            System.out.println("아이디 또는 비밀번호가 없거나 틀립니다 다시 입력해주세요");//로그인 이후 메뉴 출력
                            break;
                        }else {
                            libraryManagement.libraryProcess(userKey);
                            break;
                        }
                    case "회원가입":
                        signUpCommand.signUpProcess();
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

    public void addBook()
    {
        Book[] bookTest = {
                new Book("찬미누나와 함께하는 아크라시아 탐험","아브렐슈드","판타지",true),
                new Book("이것만 하면 너도 버러지다","발탄","생활",true),
                new Book("니나브의 활은 이렇게 쏴라","니나브","운동",true),
                new Book("태어나보니 세계관 최강에 내가 가디언 the First 슬레이어?","카단","판타지",true),
                new Book("샨디의 나이먹어도 어려지는 법","샨디","뷰티",true),
                new Book("웨이에 화나도 참는법 ","웨이","문학",true),
                new Book("자바의 정석","자바","공부",true),
                new Book("리펙토링 하는법","리펙토링","공부",true),
                new Book("떠먹여 줄테니 따라와 엄진영의 코딩 스쿨","엄진영","공부",true),
                new Book("신나게 노는법","쿠크와 세이튼","고전",true),
                new Book("민지누나 개때리고 싶다","이태정","문학",true),
                new Book("밥 뭐먹지?","배고파","음식",true),
                new Book("1","1","1",true),
        };
        for (Object object : bookTest)
        {
            Book book = (Book) object;
            bookList.add(book);
        }

    }
}
