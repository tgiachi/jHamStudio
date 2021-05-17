package com.tgiachi.jhamstudio.api.interfaces.services;

import com.tgiachi.jhamstudio.api.interfaces.IHamStudioService;

import java.util.List;

public interface IFileSystemService extends IHamStudioService {

    void initialScanDirectory(String directory);

    List<String> getInitialScanDirectoryFiles(String directory);

    String buildPath(String... directory);

    boolean writeToFileJson(String filename, Object obj) throws Exception;

    <T> T readFileFromJson(String filename, Class<T> classz) throws Exception;

    boolean writeFile(String filename, String content) throws Exception;

    String readFile(String filename) throws Exception;

    void createDirectory(String directory);

    List<String> listFiles(String directory, String... exts);

}
