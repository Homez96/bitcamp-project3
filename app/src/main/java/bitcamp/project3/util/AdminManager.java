package bitcamp.project3.util;

import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.User;

import java.util.Iterator;
import java.util.List;

public class AdminManager {

    List<User> userList;
    List<Book> bookList;

    public AdminManager(List userList, List bookList) {
        this.userList = userList;
        this.bookList = bookList;
    }

    public void addBook() {
        Book book = new Book();
        book.setTitle(Prompt.input("책 제목 ? "));
        book.setWriter(Prompt.input("저자 ? "));
        book.setGenre(Prompt.input("장르 ? "));
        book.setLoan(true);
        System.out.println("요기");
        bookList.add(book);
        System.out.println("죠기");
        System.out.println("책 추가 완료:");
        System.out.println("제목: " + book.getTitle());
        System.out.println("저자: " + book.getWriter());
        System.out.println("장르: " + book.getGenre());
    }

    public void listBooks() {
        int i = 0;
        if (bookList.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
        } else {
            for (Book book : bookList) {
                if (book.getLoan())
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%d    |   %s   |   %s  |   %s  |   \033[0;34m%s\033[0m \n", i+1,book.getTitle(), book.getWriter(), book.getGenre(), "대출 가능");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }else
                {
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.printf("%s   |   %s  |   %s  |   \033[0;31m%s\033[0m \n",book.getTitle(),i+1, book.getWriter(), book.getGenre(), "대출 불가");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
                ++i;
            }
        }
    }


    public void editBook() {
        int bookNum = Prompt.inputInt("수정하고자 하는 책 번호를 입력해 주세요 : ");
        Book book = bookList.get(bookNum-1);

        book.setTitle(Prompt.input("%s => 새 책 제목 ? ", book.getTitle()));
        book.setWriter(Prompt.input("%s => 새 저자 ?", book.getWriter()));
        book.setGenre(Prompt.input("%s => 새 장르 ? ", book.getLoan()));
        book.setLoan(Boolean.parseBoolean(Prompt.input("%b => 대출상태 ? ", book.getLoan())));

        System.out.println("도서 수정 완료:");
        System.out.println("제목: " + book.getTitle());
        System.out.println("저자: " + book.getWriter());
        System.out.println("장르: " + book.getGenre());
        if (book.getLoan())
        {
            System.out.println("대출상태: " + "\033[0;34m 대출 가능 \033[0m ");
        }else
        {
            System.out.println("대출상태: " + "\033[0;31m 대출 불가 \033[0m");
        }
    }

    public void deleteBook() {
        int bookNum = Prompt.inputInt("삭제하고자 하는 책의 번호를 입력해주세요 : ");
        Book book = bookList.remove(bookNum-1);
        System.out.println("---------------------------");
        System.out.printf("%s 도서 삭제 완료. \n",book.getTitle());
        System.out.println("---------------------------");
    }

    public void listUsers()
    {
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

    public void updateMember()
    {
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
                user.setAdmin(true);
            }else
            {
                System.out.println("권한박탈을 취소하였습니다 메뉴로 돌아갑니다");
            }
        }

    }

    public void memberDelete() {
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
