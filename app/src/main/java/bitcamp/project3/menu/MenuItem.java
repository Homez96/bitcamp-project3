package bitcamp.project3.menu;

import bitcamp.project3.command.Command;

public class MenuItem extends AbstractMenu {

    Command command;
    MenuGroup menuGroup;
    public MenuItem(String title, Command command) {
        super(title);
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        if (command != null)
        {
            command.execute(title);
        }else
        {
            System.out.println(title);
        }
    }
}
