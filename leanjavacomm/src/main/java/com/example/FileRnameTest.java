package com.example;

import java.io.File;

/**
 * Created by UX30 on 2016-12-26.
 */
//一个递归实现文件夹更名的程序，
// 把所有形如“5.2”命名的文件夹更改为L5_2; totalchanged 存放更改的文件数量
class FileRname {
    static int totalchanged = 0;
    static void DoFileRname(String inPath) {
        File SrcPathFile = new File(inPath);
        String tmpstr = "";
        File[] SrcPathList = SrcPathFile.listFiles();
        if (SrcPathFile.isDirectory()) {
            for (int i = 0; i < SrcPathList.length; i++) {
                if (SrcPathList[i].isDirectory() && SrcPathList[i].getName().matches("^[\\d]*\\.[\\d]*")) {
                    totalchanged += 1;
                    tmpstr = SrcPathList[i].getParent() + "\\" + "L" + SrcPathList[i].getName().replace(".", "_");
                    SrcPathList[i].renameTo(new File(tmpstr));
                } else {
                    if (SrcPathList[i].isDirectory()) {
                        DoFileRname(SrcPathList[i].toString());
                    }
                }
            }
        } else
            return;
    }
}

public class FileRnameTest {
    public static void main(String[] args) {
        new FileRname().DoFileRname("C:\\Users\\UX30\\Desktop\\codes");
        System.out.println(FileRname.totalchanged + "个文件被更名为L _ 格式");
    }
}
