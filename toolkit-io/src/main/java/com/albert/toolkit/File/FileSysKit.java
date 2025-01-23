package com.albert.toolkit.File;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author losfoo
 * @since 2025-01-21
 */
public class FileSysKit {
    public static void main(String[] args) {
        String userDirectoryPath = System.getProperty("user.dir");
        // 其实就是执行命令时，终端（命令行）所在的当前目录
        System.out.println("user.dir: " + userDirectoryPath);
        System.out.println("getDefault getPath: " + getCurrentPath());
        System.out.println("getClassPath: " + getClassPath());
    }


    public static String getCurrentPath() {
        // 命令行执行时，命令行所在的当前目录
        Path currentDirectoryPath = FileSystems.getDefault().getPath("");

        return currentDirectoryPath.toAbsolutePath().toString();
    }

    /**
     * 获取当前类所在的路径，该路径是classpath中的某个路径且以/开头
     * @return
     */
    public static String getClassPath() {
        // 当类装载器将类型装入Java虚拟机时，它们将为每个类型指派一个保护域。保护域定义了授予一段特定代码的所有权限。
        // 一个保护域对应策略文件中的一个或多个grant子句。装载入Java虚拟机的每一个类型都属于一个且仅属于—个保护域。
        ProtectionDomain protectionDomain = FileSysKit.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        return codeSource.getLocation().getPath();
    }

}
