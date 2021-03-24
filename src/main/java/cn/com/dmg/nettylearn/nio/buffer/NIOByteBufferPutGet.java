package cn.com.dmg.nettylearn.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName: NIOByteBufferPutGet  放入什么类型就 【按照相应的顺序】取出什么类型 避免出现异常
 * @Description: TODO
 * @author zhum
 * @date 2021/3/17 15:47
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {

        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('尚');
        buffer.putShort((short) 4);

        //取出
        buffer.flip();
        System.out.println();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());

    }
}
