package bitcamp.project3;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.util.SignIn;
import bitcamp.project3.util.SignUp;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;

public class App {

    String[] loginMenus = {"로그인", "회원가입", "종료하기"};
    SignUp signUpCommand;
    SignIn signInCommand;
    List<Book> bookList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    public App()
    {
        signUpCommand = new SignUp(userList);
        signInCommand = new SignIn(userList, bookList);
    }

    public static void main(String[] args) {
        new App().loading();
    }

    void loading()
    {
        addBook();
        addUser();
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
                        signInCommand.signInProcess();
                        break;

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
                System.out.printf("%d. %s\n", i + 1, loginMenus[i]);
            } else {
                System.out.printf("%d. %s   ", i + 1, loginMenus[i]);
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
                new Book("밥 뭐먹지?","배고파","음식",true),
                new Book("1","1","1",true),
                new Book("2","2","2",true),
                new Book("3","3","3",true),
        };
        for (Object object : bookTest)
        {
            Book book = (Book) object;
            bookList.add(book);
        }
    }

    public void addUser()
    {
        String[] a = new String[]{null,null,null};
        String[] b = new String[]{"왕좌의 게임1","왕좌의 게임2","왕좌의 게임3"};
        String[] c = new String[]{"돈의속성","돈을 부르는 매너","세이노의 가르침"};
        String[] d = new String[]{"호감의 시작","너에게 들려주는 단단한 말","만일 나에게 한 번의 아침이 남아 있다면"};
        String[] e = new String[]{"나는 도대체 왜 피곤할까","초역 부처의 말","죽이고 싶은 아이2"};

        User[] userTest = {
                new User("admin","admin","1234", a,true),
                new User("테스트1","test1","1234", b,false),
                new User("테스트2","test2","5678", c,false),
                new User("테스트3","test3","9101", d,false),
                new User("테스트4","test4","1213", e,false)
        };
        for (Object object : userTest)
        {
            User user = (User) object;
            userList.add(user);
        }
    }
}

