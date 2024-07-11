package bitcamp.project3.library;

import bitcamp.project3.util.BookManager;
import bitcamp.project3.util.Prompt;

public class LibraryManagement {
    BookManager bookManager = new BookManager();
    String[] libraryMenus = {"책 추가",  "책 목록 보기", "책 검색", "로그아웃"};
   public void libraryProcess(int key)
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
                String menuTitle = getTitle(libraryMenus, num);
                if(menuTitle.equals("종료하기"))
                {
                    System.out.println("종료하기");
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
