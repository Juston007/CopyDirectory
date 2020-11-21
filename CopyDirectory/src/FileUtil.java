import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

    //复制文件到目的路径
    public static void copyFile(String srcFilePath, String destFilePath) throws Exception {
        File srcFile = new File(srcFilePath);
        File destFile = new File(destFilePath);

        //判断文件是否存在
        if (!srcFile.isFile() || !srcFile.exists()) {
            throw new Exception("srcFile不存在，或者不是一个文件");
        }

        if (!destFile.exists()) {
            boolean createRes = destFile.createNewFile();
            //System.out.println("创建结果" + destFilePath + " copyFile " + createRes);
        }

        if (!destFile.isFile()) {
            throw new Exception("destFile不是一个文件");
        }

        //将源文件以每次1024b（1k）读取到缓冲区并且写入到目的文件
        FileInputStream inputStream = new FileInputStream(srcFile);
        FileOutputStream outputStream = new FileOutputStream(destFile);

        byte[] buffer = new byte[1024];
        int i;

        while ((i = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, i);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }


    //复制目录（连带文件中的目录）到目的目录
    public static void copyDirectory(String srcDirPath, String destDirPath) throws Exception {
        File srcDir = new File(srcDirPath);
        File destDir = new File(destDirPath);

        if (!srcDir.exists() || !srcDir.isDirectory()) {
            throw new Exception("srcDir不存在，或者不是一个目录");
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        if (!destDir.isDirectory()) {
            throw new Exception("destDir不是一个目录");
        }

        File[] files = srcDir.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(String.format("%s    文件复制到    %s", file.getPath(), destDirPath + "//" + file.getName()));
                copyFile(file.getPath(), destDirPath + "//" + file.getName());
            } else {
                System.out.println(String.format("%s    目录复制到    %s", file.getPath(), destDirPath + "//" + file.getName()));
                copyDirectory(file.getPath(), destDirPath + "//" + file.getName());
            }
        }

    }

    //遍历当前目录
    public static File[] travelDirector(String dirPath) throws Exception {
        File dir = new File(dirPath);

        if (!dir.isDirectory() || !dir.exists()) {
            throw new Exception("dirPath不存在，或者不是一个目录");
        }

        return dir.listFiles();
    }

}
