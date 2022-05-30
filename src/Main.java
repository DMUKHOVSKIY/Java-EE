import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(8080); //сервер
//        Socket accept = serverSocket.accept(); //создаем токен для сервера
//        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));//в символьный поток принимаем информацию
//        br.lines().forEach(System.out::println);//достаем и BufferedReader поток и выводим в консоль
        HttpServer httpServer =
                HttpServer.create(new InetSocketAddress("localhost", 8080), 0);//создали HttpServer(как ServerSocket)
        HttpContext context = httpServer.createContext("/test", new TestHandler());
        //backlog - это максимальное кол-во входящих подключений
        httpServer.start();
    }

    private static class TestHandler implements HttpHandler { //обработчик

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody));
            bufferedReader.lines().forEach(System.out::println);
//            System.out.println(bufferedReader.readLine());
           // exchange.close();
        }
    }
}
