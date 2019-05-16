package com.shengsiyuan.netty.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * ASCII(American Standard code for Infomation Interchange, 美国信息交换标准代码)
 * 7 bit 来表示一个字符，2的7次方=128种字符。
 *
 * ISO-8859-1
 * 8 bit 表示一个字符，即用一个字节(byte)(8bit)来表示一个字符，共计可以表示256
 *
 * gb2312:gb表示国标，是中华人民共和国定义的表，gb2312是对所有的汉字进行了对应，是的每一个汉字都有一个数字对应
 * 两个字节表示一个汉字。
 *
 * gbk：是gb2312的一张扩展，因为gb2312表示不完汉字
 *
 * gb18030:最完整的汉字表示形式，是针对中华人民共和国简体字符集
 *
 * big5:繁体中文
 *
 * unicode :因为标准太多了，所有国际出的标准，能表示世界所有的字符
 *      unicode：采用两个字节表示一个字符
 *
 * 但是对欧美国家来说， ISO-8859-1完全够用了，如果欧美国家使用unicode来的话，造成内存的浪费，原来只需要一个字节的，现在需要用两个字节
 *
 * utf :(unicode translation format),表示一个存储方式
 * unicode是一个种编码方式，而UTF则是一种存储方式，utf-8是unicode的实现方式之一
 *
 * utf16-le(little endian),utf16-be(big endian):utf-16也是两个字节表示一个字符
 *
 * zero width no-break space:零宽度，不换行的字符，如果在文件的开头出现0xFEFF(BE),0xFFFE(LE)
 *
 * utf-8，使用变长字节表示形式，根据不同的字符，采用的存储字节不一样
 *
 *
 *
 *
 */
public class CharsetTest {

    public static void main(String[] args) throws Exception {
        String inputFile = "NioTest_in.txt";
        String outFile = "NioTest_out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outFile, "rw");

        long inputLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset charset = Charset.forName("utf-8");
        //decoder:将字节数组转换为字符串,解码
        CharsetDecoder decoder = charset.newDecoder();
        //encoder:将字符串转换为自己数组，编码
        CharsetEncoder encoder = charset.newEncoder();
        //将内存映射的文件解码为CharBuffer
        CharBuffer charBuffer = decoder.decode(inputData);

        ByteBuffer outputData = encoder.encode(charBuffer);

        outFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

        System.out.println("===================================");
        Charset.availableCharsets().forEach((k, v) -> {
            System.out.println(k + " , " + v);
        });

    }
}
