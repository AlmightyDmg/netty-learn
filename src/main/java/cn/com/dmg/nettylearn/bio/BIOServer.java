package cn.com.dmg.nettylearn.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: BIOServer
 * @Description: TODO
 *  实例说明：
 *  1.使用BIO模型编写一个服务器端，监听6666端口，当有客户端连接时，就启动一个线程与之通讯。
 *  2.要求使用线程池机制改善，可以连接多个客户端.
 *  3.服务器端可以接收客户端发送的数据(telnet 方式即可)。
 *  telnet 127.0.0.1 6666
    Escape 字符为 'CTRL+]'


    Microsoft Telnet> 111
    无效指令。需要帮助，请键入 ?/help
    Microsoft Telnet> 111
    无效指令。需要帮助，请键入 ?/help
    Microsoft Telnet> send 100
    发送字符串 100
    Microsoft Telnet> send 200
    发送字符串 200
    Microsoft Telnet>
 * @author zhum
 * @date 2021/3/17 13:27
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {

        //线程池机制

        //思路
        //1. 创建一个线程池
        //2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {

            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接....");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //就创建一个线程，与之通讯(单独写一个方法)
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() { //我们重写
                    //可以和客户端通讯
                    handler(socket);
                }
            });

        }


    }

    //编写一个handler方法，和客户端通讯
    public static void handler(Socket socket) {

        try {
            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket 获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            while (true) {

                System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());

                System.out.println("read....");
               int read =  inputStream.read(bytes);
               if(read != -1) {
                   //输出客户端发送的数据
                   System.out.println(new String(bytes, 0, read));
               } else {
                   break;
               }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
