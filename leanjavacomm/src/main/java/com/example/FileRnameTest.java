package com.example;

import java.io.File;

/**
 * Created by UX30 on 2016-12-26.
 */
//һ���ݹ�ʵ���ļ��и����ĳ���
// ���������硰5.2���������ļ��и���ΪL5_2; totalchanged ��Ÿ��ĵ��ļ�����
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
        System.out.println(FileRname.totalchanged + "���ļ�������ΪL _ ��ʽ");
    }
}