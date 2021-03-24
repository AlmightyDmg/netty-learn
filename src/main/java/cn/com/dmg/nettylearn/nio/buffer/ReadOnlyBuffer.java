package cn.com.dmg.nettylearn.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: ReadOnlyBuffer 只读 buffer
 * @Description: TODO
 * @author zhum
 * @date 2021/3/17 15:49
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {

        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for(int i = 0; i < 64; i++) {
            buffer.put((byte)i);
        }

        //读取
        buffer.flip();

        //得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte)100); //ReadOnlyBufferException
    }
}
