package com.github.tgiachi.jhamstudio.services;

import com.tgiachi.jhamstudio.api.impl.services.AbstractHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.services.IFileSystemService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSystemService extends AbstractHamStudioService implements IFileSystemService {
    private static final String rootDirectory = "hamcube";
    private static final String userDirectory = System.getProperty("user.home");
    private static final String appDirectory = Paths.get(userDirectory, rootDirectory).toString();

    private final Map<String, List<String>> startupScannedDirectory = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start() {
        super.start();
        checkDirectory();
    }

    private void checkDirectory() {
        logger.info("Application directory is {}", appDirectory);
        createDirectory("");
    }

    @Override
    public void createDirectory(String directory) {
        var dir = Paths.get(appDirectory, directory).toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void initialScanDirectory(String directory) {
        logger.info("Scanning directory {}", directory);
        startupScannedDirectory.put(directory, listFiles(directory));
    }

    @Override
    public List<String> getInitialScanDirectoryFiles(String directory) {
        if (startupScannedDirectory.containsKey(directory)) {
            return startupScannedDirectory.get(directory);
        }

        return new ArrayList<>();
    }

    @Override
    public List<String> listFiles(String directory, String... exts) {
        if (exts.length == 0)
            exts = null;
        return FileUtils.listFiles(Paths.get(appDirectory, directory).toFile(), exts, true).stream().map(File::getAbsolutePath).collect(Collectors.toList());
    }

    @Override
    public String buildPath(String... directory) {
        return Paths.get(appDirectory, directory).toString();
    }

    @Override
    public boolean writeToFileJson(String filename, Object obj) throws Exception {
        FileUtils.writeStringToFile(new File(buildPath(filename)), DefaultSerializers.toJson(obj), Charset.defaultCharset());
        return true;
    }

    @Override
    public <T> T readFileFromJson(String filename, Class<T> classz) throws Exception {
        var strFile = FileUtils.readFileToString(new File(buildPath(filename)), Charset.defaultCharset());
        return DefaultSerializers.fromJson(strFile, classz);
    }

    @Override
    public boolean writeFile(String filename, String content) throws Exception {
        FileUtils.writeStringToFile(new File(buildPath(filename)), content, Charset.defaultCharset());

        return true;
    }

    @Override
    public String readFile(String filename) throws Exception {
        return FileUtils.readFileToString(new File(buildPath(filename)), Charset.defaultCharset());
    }
}
