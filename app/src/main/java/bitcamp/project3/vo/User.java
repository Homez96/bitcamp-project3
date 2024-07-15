package bitcamp.project3.vo;

public class User {
    String name;
    String id;
    String pw;
    String[] books = new String[3];
    boolean admin;

    public User(){};

    public User(String name, String id, String pw, String[] books, boolean admin) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.books = books;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getBooks(int i) {
        return books[i];
    }

    public void setBooks(String books, int i) {
        this.books[i] = books;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

