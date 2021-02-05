package message;


public class Messenger {
    private Messenger(){}

    public static void display(Message communicate) {
        System.out.println(communicate.getText());
    }

    public static void display(Message communicate, String... params) {
        System.out.println(String.format(communicate.getText(), params));
    }

    public static void display(Message communicate, Integer... params) {
        System.out.println(String.format(communicate.getText(), params));
    }
}
