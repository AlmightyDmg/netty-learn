package cn.com.dmg.nettylearn.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: NIOFileChannel03
 * 使用 FileChannel(通道) 和 方法  read , write，完成文件的拷贝
 * 拷贝一个文本文件 1.txt  , 放在项目下即可
 * 代码演示
 * @Description: TODO
 * @author zhum
 * @date 2021/3/17 15:35
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) { //循环读取

            //这里有一个重要的操作，一定不要忘了
            /*
             public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }
             */
            byteBuffer.clear(); //清空buffer
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read =" + read);
            if(read == -1) { //表示读完
                break;
            }
            //将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
