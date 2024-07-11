package bitcamp.project3.util;

import bitcamp.project3.vo.User;

import java.util.List;

public class SignIn {
    private List<User> userList;
    public SignIn(List list)
    {
        this.userList = list;
    }

    public Boolean signInProcess(){
        String id = Prompt.input("아이디를 입력해주세요 : ");
        String pw = Prompt.input("비밀번호를 입력해주세요 : ");
        return checkId(userList, id, pw);
    }

    public Boolean checkId(List userList, String id, String pw)
    {
        for(Object obj : userList)
        {
            User user = (User) obj;
            if(user.getId().equals(id) && user.getPw().equals(pw))
            {
                return true;
            }
        }
        return false;
    }
}
