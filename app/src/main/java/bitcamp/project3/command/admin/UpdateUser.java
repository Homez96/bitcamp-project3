package bitcamp.project3.command.admin;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.User;

import java.util.List;

public class UpdateUser implements Command {

    List<User> userList;

    public UpdateUser(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public void execute(String menuName) {
        int userNum = Prompt.inputInt("수정하고자 하는 유저의 번호를 입력해 주세요 : ");
        User user = userList.get(userNum - 1);

        if (!user.getAdmin()) {
            System.out.println("--------------------");
            System.out.printf("아이디 : %s \n", user.getId());
            System.out.printf("이름 : %s \n", user.getName());
            System.out.println("--------------------");
            String check = Prompt.input("해당 계정을 관리자로 설정하시겠습니까? y/n");
            if (check.equals("y") || check.equals("Y") || check.equals("네") || check.equals("예")) {
                user.setAdmin(true);
            } else {
                System.out.println("권한부여를 취소하였습니다");
            }

        }else
        {
            System.out.println("입력하신 번호의 회원은 이미 관리자 입니다.");
            String doubleCheck = Prompt.input("혹시 해당 계정을 일반유저로 강등 시키시겠습니까? y/n");
            if (doubleCheck.equals("y") || doubleCheck.equals("Y") || doubleCheck.equals("네") || doubleCheck.equals("예"))
            {
                user.setAdmin(false);
            }else
            {
                System.out.println("권한박탈을 취소하였습니다 메뉴로 돌아갑니다");
            }
        }
    }
}
