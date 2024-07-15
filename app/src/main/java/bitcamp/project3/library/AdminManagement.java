package bitcamp.project3.library;

import bitcamp.project3.util.AdminManager;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminManagement {
    private List<Book> bookList;
    private List<User> userList;
    private AdminManager adminManager;

    private String[] adminMenu = {"책 추가", "책 전체보기", "도서수정", "도서삭제", "회원정보 보기", "회원삭제", "로그아웃"};

    public AdminManagement(List userList, List bookList) {
        this.bookList = bookList;
        this.userList = userList;
        adminManager = new AdminManager(userList, bookList);
    }

    public void adminProcess() {
        System.out.println("관리자 모드를 실행 합니다");
        printMenu();

        while (true) {
                String command = Prompt.input("관리자 메뉴 > ");
                if (command.equals("menu"))
                {
                    printMenu();
                    continue;
                }
                int num = Integer.parseInt(command);
                String menuTitle = getTitle(adminMenu, num);
                if (menuTitle.equals("로그아웃"))
                {
                    break;
                }
                switch (menuTitle) {
                    case "책 추가":
                        adminManager.addBook();
                        break;
                    case "책 전체보기":
                        adminManager.listBooks();
                        break;
                    case "도서수정":
                        adminManager.editBook();
                        break;
                    case "도서삭제":
                        adminManager.deleteBook();
                        break;
                    case "회원정보 보기":
                        adminManager.listUsers();
                        break;
                    case "회원삭제":
                        adminManager.memberDelete();
                        break;
                        default:
                        System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                            break;
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
        for (int i = 0; i < adminMenu.length; i++) {
            if (i + 1 == adminMenu.length) {
                System.out.printf("%d. %s   \n", i + 1, adminMenu[i]);
            } else {
                System.out.printf("%d. %s   ", i + 1, adminMenu[i]);
            }
        }
        System.out.println("----------------------------------");
    }
}
