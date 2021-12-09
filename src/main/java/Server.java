import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) throws IOException {

        System.out.println("Центр тяжелых вычислений startup");

        //  Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = new ServerSocket(23444);
        while (true) {
            //  Ждем подключения клиента и получаем потоки для дальнейшей работы
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    if (number == 0) {
                        out.println(" ряд Фибоначчи = 0");
                    } else if (number == 1) {
                        out.println(" ряд Фибоначчи = 1");
                    } else {
                        int[] arr = new int[number];
                        arr[0] = 0;
                        arr[1] = 1;
                        for (int i = 2; i < arr.length; ++i) {
                            arr[i] = arr[i - 1] + arr[i - 2];
                        }
                        out.println("Ряд Фибоначчи : " + Arrays.toString(arr));
                    }
                    // Выход если от клиента получили end
                    if (line.equals("end")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}