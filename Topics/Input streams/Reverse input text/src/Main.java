import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        int x;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine().trim();
        reader.close();

        char[] cArray = new char[str.length()];
        for (int i = 0; i < cArray.length; i++) {
            cArray[i] = str.charAt(i);
        }

        for (int i = cArray.length - 1; i >= 0; i--) {
            System.out.print(cArray[i]);
        }


    }
}