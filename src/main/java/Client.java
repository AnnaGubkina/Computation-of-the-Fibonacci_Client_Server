import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
Программа "Получение ряда чисел Фибоначчи"
Версия программы №1 Blocking
В данной задаче можно использовать как Blocking, так и NonBlocking способ взаимодействия. Я решила использовать Blocking,
так как в данной задаче нам достаточно передать только одно значение серверу, чтобы получить конечный результат обратно, никаких
дополнительных данных пользователь в процессе вычисления не передает и других процессов в "клиенте" не совершает.
 */

public class Client {

    public static void main(String[] args) throws IOException {
        // Определяем сокет сервера
        Socket socket = new Socket("127.0.0.1", 23444);

        // Получаем входящий и исходящий потоки информации
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String msg;
            while (true) {
                System.out.println("Введите число для вычисления ряда Фибоначчи: ");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) {
                    System.out.println("Программа завершена");
                    break;
                }
                System.out.println("Ответ от центр тяжелых вычислений: " + in.readLine());
            }
        }
    }
}
