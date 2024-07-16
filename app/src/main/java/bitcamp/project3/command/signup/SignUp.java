package bitcamp.project3.command.signup;

import bitcamp.project3.command.Command;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.User;

import java.util.List;

public class SignUp implements Command {
    private List<User> userList;

    public SignUp(List list)
    {
        this.userList = list;
    }

    @Override
    public void execute(String menuName) {
        System.out.println();
        System.out.println("-----------------------");
        User user = new User();
        user.setName(Prompt.input("이름을 입력해주세요 : "));
        String id;
        while (true)
        {

            id = Prompt.input("아이디를 입력해주세요 : ");
            if (!duplicateId(id)) {
                break;
            }else
            {
                System.out.println("이미 있는 아이디 입니다");
            }
        }
        user.setId(id);
        user.setPw(Prompt.input("비밀번호를 입력해주세요 : "));
        user.setAdmin(false);
        userList.add(user);
        System.out.println("회원가입이 완료되었습니다");
        System.out.println("-----------------------");
    }

    Boolean duplicateId(String id)
    {
        for(User user : userList)
        {
            if(user.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }
}