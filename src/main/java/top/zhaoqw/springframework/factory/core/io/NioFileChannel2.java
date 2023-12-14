package top.zhaoqw.springframework.factory.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhaoqw
 * @date 2023/11/17
 */
public class NioFileChannel2 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\logs\\aa.txt");
        FileInputStream fis = new FileInputStream("D:\\logs\\aa.txt");

        FileChannel channel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //从channel读取数据到byteBuffer
        channel.read(byteBuffer);

        //将 byteBuffer 的字节数据转成 String
        System.out.println(new String(byteBuffer.array()));
        fis.close();
    }
}
