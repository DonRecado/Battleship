import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String var = reader.readLine().trim();
        reader.close();

        if(var.isEmpty()) {
            System.out.println(0);
        } else {
            String[] strArray = var.split("\\s+");
            System.out.println(strArray.length);
        }
    }
}