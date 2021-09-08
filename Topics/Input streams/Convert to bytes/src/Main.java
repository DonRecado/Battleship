import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        String str = "";
        InputStream inputStream = System.in;
//        int x = 0;
//        while (x != -1 && x != 10) {
//            x = inputStream.read();
//            str += x;
//        }
        byte[] allBytes = inputStream.readAllBytes();
        inputStream.close();
        for (byte b:allBytes) {
            System.out.print(b);

        }

    }

}