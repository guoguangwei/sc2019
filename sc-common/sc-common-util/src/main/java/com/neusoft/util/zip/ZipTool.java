package com.neusoft.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ZipTool {
    public static void zip(String strSrcPath, String strDstPath) {
        String srcPath, dstPath;
        srcPath = strSrcPath.trim();
        dstPath = strDstPath.trim();
        if (srcPath.charAt(srcPath.length() - 1) == File.separatorChar) {
            srcPath = srcPath.substring(0, srcPath.length() - 1);
        }
        if (dstPath.charAt(dstPath.length() - 1) == File.separatorChar) {
            dstPath = dstPath.substring(0, dstPath.length() - 1);
        }
        if ("".equals(srcPath) || "".equals(dstPath) || srcPath.equals(dstPath)) {
            System.out.println("Path is error(FileOperate.zip())");
            return;
        }
        File srcFile = new File(srcPath);
        try {
            if (srcFile.exists()) {
                ZipOutputStream zipOut;
                if (dstPath.endsWith(".zip") || dstPath.endsWith(".ZIP")) {
                    zipOut = new ZipOutputStream(new BufferedOutputStream(new
                            FileOutputStream(dstPath)));
                } else {
                    File file = new File(dstPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    zipOut = new ZipOutputStream(new FileOutputStream(new File(
                            file, srcFile.getName() + ".zip")));
                }
                zip(srcFile, zipOut, "");
                zipOut.close();
            } else {
                System.out.println(
                        "file or dir isn't existent(FileOperate.zip())");
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void zip(File file, ZipOutputStream zip, String entryName) {
        try {
            if (file.isDirectory()) {
                ZipEntry ze = new ZipEntry(entryName + file.getName() + "/");
                ze.setTime(file.lastModified());
                zip.putNextEntry(ze);
                zip.closeEntry();
                String[] names = file.list();
                for (int i = 0; i < names.length; i++) {
                    zip(new File(file, names[i]), zip,
                        entryName + file.getName() + "/");
                }
            } else {
                int len;
                byte[] buf = new byte[1024];

                BufferedInputStream in = new BufferedInputStream(new
                        FileInputStream(file), 1024);
                ZipEntry ze = new ZipEntry(entryName + file.getName());
                ze.setTime(file.lastModified());
                zip.putNextEntry(ze);
                while ((len = in.read(buf)) != -1) {
                    zip.write(buf, 0, len);
                }
                zip.flush();
                in.close();
                zip.closeEntry();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void createDirectory(String directory, String subDirectory) {
        String[] dir;
        File fl = new File(directory);
        try {
            if (subDirectory == "" && fl.exists() != true) {
                fl.mkdir();
            } else if (subDirectory != "") {
                dir = subDirectory.replace('\\', '/').split("/");
                for (int i = 0; i < dir.length; i++) {
                    File subFile = new File(directory + File.separator + dir[i]);
                    if (subFile.exists() == false) {
                        subFile.mkdir();
                    }
                    directory += File.separator + dir[i];
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
      * ��ѹZIP�ļ����Ѵ����������⣩
      * @param zipFileName ׼����ѹ�ĵ��ļ���(����·��)
      * @param outputDirectory ��ѹ���ŵ�Ŀ¼��
      * @return
     */
    public static void unZip(String zipFileName, String outputDirectory) throws
            Exception {
        try {
            ZipFile zipFile = new ZipFile(zipFileName,"gbk");
            java.util.Enumeration e = zipFile.getEntries();
            ZipEntry zipEntry = null;
            createDirectory(outputDirectory, "");
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                System.out.println("unziping " + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    name = name.substring(0, name.length() - 1);
                    File f = new File(outputDirectory + File.separator + name);
                    f.mkdir();
                    System.out.println("����Ŀ¼��" + outputDirectory +File.separator + name);
                } else {
                    String fileName = zipEntry.getName();
                    fileName = fileName.replace('\\', '/');
                    if (fileName.indexOf("/") != -1) {
                        createDirectory(outputDirectory,
                                        fileName.substring(0,
                                fileName.lastIndexOf("/")));
                        fileName = fileName.substring(fileName.lastIndexOf("/") +
                                1, fileName.length());
                    }

                    File f = new File(outputDirectory + File.separator +
                                      zipEntry.getName());

                    f.createNewFile();
                    InputStream in = zipFile.getInputStream(zipEntry);
                    FileOutputStream out = new FileOutputStream(f);

                    byte[] by = new byte[1024];
                    int c;
                    while ((c = in.read(by)) != -1) {
                        out.write(by, 0, c);
                    }
                    out.close();
                    in.close();
                }
            }
            zipFile.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ZipTool.zip("J:\\�°������վ���س���(2007-07-21)", "F:\\");
        try {
            ZipTool.unZip("F:\\�°������վ���س���(2007-07-21).zip", "F:\\");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
