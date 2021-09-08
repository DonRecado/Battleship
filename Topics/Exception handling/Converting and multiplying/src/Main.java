import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = "";

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            } else {
                str = str + " " + input;
            }
        }

        String[] strArray = str.trim().split(" ");

        for (String s : strArray) {
            try {
                int num = Integer.parseInt(s);
                num *= 10;
                System.out.println(num);
            } catch (Exception e) {
                System.out.println("Invalid user input: " + s);
            }
        }
    }
}