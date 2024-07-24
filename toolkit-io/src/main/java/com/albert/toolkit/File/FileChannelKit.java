package com.albert.toolkit.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * FileChanne用法
 *
 * @author hoyo
 * @since 2024-07-20
 */
public class FileChannelKit {

    /**
     * 使用FileChannel复制文件
     *
     * @param srcFile    源文件绝对路径
     * @param targetFile 目标文件绝对路径
     * @throws IOException
     */
    public void testTransferFrom(String srcFile, String targetFile) throws IOException {
        long position = 0;
        // 一次传8k
        long buffSize = 8192;
        try (FileInputStream fis = new FileInputStream(srcFile);
             FileChannel srcFileChannel = fis.getChannel();
             FileOutputStream fos = new FileOutputStream(targetFile);
             FileChannel targetFileChannel = fos.getChannel()) {
            while (position < srcFileChannel.size()) {
                long transferedBytes = srcFileChannel.transferTo(position, buffSize, targetFileChannel);
                position += transferedBytes;
            }
        }
    }
}
