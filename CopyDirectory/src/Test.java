import java.io.File;

public class Test {

    //ByJuston
    public static void main(String[] args) {
        System.out.println("***********************************************");
        System.out.println("列出该目录下所有目录");

        try {
            File[] files = FileUtil.travelDirector("E:\\测试目录");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        System.out.println("***********************************************");
        System.out.println("复制文件");

        try {
            FileUtil.copyFile("E:\\测试目录\\ceshi2\\ceshi2-2\\readme.txt","E:\\测试目录\\ceshi2\\readme.txt");
            System.out.println("Copy file success!");
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        System.out.println("***********************************************");
        System.out.println("复制整个目录");

        try {
            FileUtil.copyDirectory("E:\\测试目录","E:\\hh");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
