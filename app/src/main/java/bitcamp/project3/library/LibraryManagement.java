package bitcamp.project3.library;

import bitcamp.project3.util.BookManager;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.User;

import java.util.List;

public class LibraryManagement {
    BookManager bookManager = new BookManager();
    String[] libraryMenus = {"책 추가",  "책 목록 보기", "책 검색", "로그아웃"};
    private List<User> userList;
    public LibraryManagement(List list)
    {
        this.userList = list;
    }

   public void libraryProcess(int key)
    {
        status(key);
        printMenu();
        while (true)
        {
            try {
                String command = Prompt.input("도서관리 시스템 > ");
                if (command.equals("menu"))
                {
                    printMenu();
                    continue;
                }
                int num = Integer.parseInt(command);
                String menuTitle = getTitle(libraryMenus, num);
                if(menuTitle.equals("로그아웃"))
                {
                    break;
                }
                switch (menuTitle)
                {
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
    
    void status(int key)
    {
        int counter = 0;
        User user = userList.get(key);
        for (int i = 0; i <= 2; i++)
        {
            if (user.getBooks(i) == null)
            {
                ++counter;
            }
        }
        if (counter == 3) {
            System.out.println("----------------------------------");
            System.out.printf("%s, 님의 현재 대출현황  대여한 책이 없습니다\n",user.getName());
        }else
        {
            System.out.println("----------------------------------");
            System.out.printf("%s, 님의 현재 대출현황  %s, %s, %s 입니다\n",user.getName(),user.getBooks(0),user.getBooks(1),user.getBooks(2));
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
        for (int i = 0; i < libraryMenus.length; i++) {
            if (i + 1 == libraryMenus.length) {
                System.out.printf("%d. %s \n", i + 1, libraryMenus[i]);
            } else {
                System.out.printf("%d. %s \t", i + 1, libraryMenus[i]);
            }
        }
        System.out.println("----------------------------------");
    }
}
