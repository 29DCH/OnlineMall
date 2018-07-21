package priv.jesse.mall.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

public class FileUtil {

    /**
     * 保存上传的文件
     *
     * @param file
     * @return 文件下载的url
     * @throws Exception
     */
    public static String saveFile(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty())
            return "";
        File target = new File("file");
        if (!target.isDirectory()) {
            target.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(file.getBytes());
        String fileName = (Helper.bytesToHex(md.digest(),0,md.digest().length-1)) + "." + getPostfix(originalFilename);
        File file1 = new File(target.getPath() + "/" + fileName);
        Files.write(Paths.get(file1.toURI()), file.getBytes(), StandardOpenOption.CREATE_NEW);
        return "/mall/admin/product/img/" + fileName;
    }

    /**
     * 获得文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getPostfix(String fileName) {
        if (fileName == null || "".equals(fileName.trim())) {
            return "";
        }
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        }
        return "";
    }

}
