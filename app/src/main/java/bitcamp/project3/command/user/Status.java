package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;

import java.util.List;

public class Status implements Command {

    int key;
    List<User> userList;

    public Status(int key, List<User> userList) {
        this.key = key;
        this.userList = userList;
    }

    @Override
    public void execute(String menuName) {
        String[] book = new String[3];
        String blank  = "____";
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
}
