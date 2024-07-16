package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;

import java.util.List;

public class ListUser implements Command {
    List<User> userList;

    public ListUser(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void execute(String menuName) {
        int i = 0;
        if (userList.isEmpty()) {
            System.out.println("등록된 계정이 하나도 없습니다.");
        } else {
            for (User user : userList) {
                String b1 = null;
                String b2 = null;
                String b3 = null;

                if (user.getBooks(0) == null) {b1 = "";}else{b1 = user.getBooks(0);}
                if (user.getBooks(1) == null) {b2 = "";}else{b2 = user.getBooks(1);}
                if (user.getBooks(2) == null) {b3 = "";}else{b3 = user.getBooks(2);}


                if (user.getAdmin())
                {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%d.  |   Id : %s   |   이름 : %s  |   %s\n", i+1, user.getId(), user.getName(), "관리자");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                }else
                {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%d.  |   Id : %s   |  이름 : %s  |   %s  |   %s, %s, %s\n", i+1, user.getId(), user.getName(), "회원",b1,b2,b3);
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                }
                ++i;
            }
        }
    }
}
