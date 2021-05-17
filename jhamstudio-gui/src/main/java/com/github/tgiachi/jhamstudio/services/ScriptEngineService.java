package com.github.tgiachi.jhamstudio.services;

import com.tgiachi.jhamstudio.api.impl.services.AbstractHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.services.IFileSystemService;
import com.tgiachi.jhamstudio.api.interfaces.services.IScriptEngineService;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Service
public class ScriptEngineService extends AbstractHamStudioService implements IScriptEngineService {

    private final IFileSystemService fileSystemService;

    private String scriptsPath;
    private ScriptEngine scriptEngine;

    public ScriptEngineService(IFileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @Override
    public void start() {
        super.start();
        initializeEngine();
    }

    private void initializeEngine(){
        fileSystemService.createDirectory("scripts");
        scriptsPath = fileSystemService.buildPath("scripts");
        logger.info("Initialize LUA script engine");
        var mrg = new ScriptEngineManager();
        scriptEngine  = mrg.getEngineByName("luaj");
    }
}
