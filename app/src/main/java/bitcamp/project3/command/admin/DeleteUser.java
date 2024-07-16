package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.Iterator;
import java.util.List;

public class DeleteUser implements Command {
    List<User> userList;

    public DeleteUser(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void execute(String menuName) {
        if (userList.isEmpty()) {
            System.out.println("등록된 회원이 한명도 없습니다");
            return;
        }

        String userId = Prompt.input("삭제할 회원의 ID를 입력하세요 : ");
        Iterator<User> iterator = userList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                System.out.printf("%s 계정이 삭제 되었습니다 : \n", user.getId());
                iterator.remove();  // 안전하게 요소를 제거합니다.
                found = true;
            }
        }

        if (!found) {
            System.out.println("삭제하고자 하는 회원 ID가 없습니다");
        }
    }
}
