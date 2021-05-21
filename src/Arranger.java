import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Arranger {
    private final String root;
    public static boolean isCorrectInput = true;

    public Arranger(String root) {
        this.root = root;
    }

    void arrange() {
        File[] files = new File(root).listFiles();
        if (files != null) {
            for (File file : files) {
                try {

                    if (!file.isDirectory()) {
                        String fileName = file.getName();
                        String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1);
                        String folderName = folderName(fileFormat.toLowerCase());
                        Files.createDirectories(Paths.get(root + "\\" + folderName));
                        Files.move(Paths.get(root + "\\" + fileName),
                                Paths.get(root + "\\" + folderName + "\\" + fileName));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else isCorrectInput = false;

    }


    void openFolder() {
        try {
            Desktop.getDesktop().open(new File(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveFilesToRoot(String source) {

        File folder = new File(source);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                moveFilesToRoot(file.getAbsolutePath());
                file.delete();
            } else {
                try {
                    Files.move(Paths.get(source + "\\" + file.getName()),
                            Paths.get(root + "\\" + file.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String folderName(String fileFormat) {
        Map<String, String> map = new HashMap<>();
        map.put("jpg", "image");
        map.put("jpeg", "image");
        map.put("gif", "image");
        map.put("tiff", "image");
        map.put("psd", "image");
        map.put("bmp", "image");
        map.put("ico", "image");
        map.put("swg", "image");
        map.put("png", "image");
        map.put("ai", "image");

        map.put("doc", "document");
        map.put("docx", "document");
        map.put("pdf", "document");
        map.put("rtf", "document");
        map.put("xml", "document");
        map.put("json", "document");
        map.put("xls", "document");
        map.put("xlsx", "document");
        map.put("html", "document");
        map.put("txt", "document");

        map.put("mp3", "audio");
        map.put("cda", "audio");
        map.put("mpa", "audio");
        map.put("wav", "audio");
        map.put("wma", "audio");
        map.put("mid", "audio");
        map.put("midi", "audio");

        map.put("3g2", "video");
        map.put("3gp", "video");
        map.put("avi", "video");
        map.put("flv", "video");
        map.put("h264", "video");
        map.put("m4v", "video");
        map.put("mp4", "video");
        map.put("mkv", "video");
        map.put("mov", "video");
        map.put("mpg", "video");
        map.put("mpeg", "video");
        map.put("swf", "video");
        map.put("vob", "video");
        map.put("wmv", "video");

        map.put("exe", "application");
        map.put("apk", "application");
        map.put("jar", "application");
        map.put("msi", "application");

        map.put("7z", "archive");
        map.put("rar", "archive");
        map.put("arj", "archive");
        map.put("zip", "archive");

        return map.getOrDefault(fileFormat, "other");
    }

    void arrangeIncludeSubFolders() {
        File[] files = new File(root).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    moveFilesToRoot(file.getAbsolutePath());
                    file.delete();
                }
            }
        }
        arrange();
    }

}
