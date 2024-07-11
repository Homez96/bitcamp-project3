package bitcamp.project3.util;

import bitcamp.project3.vo.User;

import java.util.List;

public class SignIn {
    private List<User> userList;
    public SignIn(List list)
    {
        this.userList = list;
    }

    public int signInProcess(){
        String id = Prompt.input("아이디를 입력해주세요 : ");
        String pw = Prompt.input("비밀번호를 입력해주세요 : ");
        int userKey = findUser(userList, id, pw);
        return userKey;
    }

    public int findUser(List userList, String id, String pw)
    {
        int key = 0;
        for(Object obj : userList)
        {
            User user = (User) obj;
            if(user.getId().equals(id) && user.getPw().equals(pw))
            {
                System.out.println(user.getName()+"님 환영합니다");
                key = userList.indexOf(user);
            }
        }
        return key;
    }

}
