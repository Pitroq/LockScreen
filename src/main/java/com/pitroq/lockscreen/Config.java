package com.pitroq.lockscreen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.HashMap;

public class Config {
    private String dirPath;
    private String filePath;
    private File file;
    HashMap<String, String> config = new HashMap<>();

    public Config(String filename) {
        this.dirPath = System.getenv("appdata") + File.separatorChar + "LockScreen";
        this.filePath = dirPath + File.separatorChar + filename;
        this.file = new File(filePath);
        try {
            checkPathAndFile();
            loadConfig();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadConfig() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("=");
            config.put(split[0].strip(), split[1].strip());
        }
        scanner.close();
    }

    public String get(String key) {
        return config.get(key);
    }

    private void checkPathAndFile() throws IOException {
        Path dirPath = Paths.get(this.dirPath);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        if (!file.exists()) {
            file.createNewFile();
            fillFileWithDefaultFile();
        }
    }

    private void fillFileWithDefaultFile() {
        InputStream defaultConfigFileStream = getClass().getResourceAsStream("data/defaultConfig.cfg");
        try (FileOutputStream destinationConfigFileStream = new FileOutputStream(filePath)) {
            int ch;
            do {
                ch = defaultConfigFileStream.read();
                if (ch != -1) destinationConfigFileStream.write(ch);
            }
            while (ch != -1);
            defaultConfigFileStream.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
