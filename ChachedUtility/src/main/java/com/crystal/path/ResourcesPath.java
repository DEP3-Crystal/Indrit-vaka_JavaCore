package com.crystal.path;

import com.crystal.os.OperationSystem;

import java.util.Objects;

public class ResourcesPath {

    /**
     * @return ex: C:/Users/indri/Desktop/CrystalSystem/General-Java/OnlineMediaStore_4/src/main/resources/
     */
    public static String getResourcePathAsString(Class<?> classResource) {
        //ex: C:/Users/indri/Desktop/CrystalSystem/General-Java/OnlineMediaStore_4/target/classes/media.json
        String moduleRoot = getModuleRootPathAsString(classResource);
        return moduleRoot + "src/main/resources/";
    }

    public static String getModuleRootPathAsString(Class<?> classResource) {
        //ex: C:/Users/indri/Desktop/CrystalSystem/General-Java/OnlineMediaStore_4/target/classes/media.json
        String rootPath = Objects.requireNonNull(classResource.getResource("/")).getPath().split("target")[0];
        if (isSlash(rootPath.charAt(0)) && isRunningOnWindows()) {
            return rootPath.substring(1);
        }
        return rootPath;
    }

    private static boolean isSlash(char c) {
        return c == '\\' || c == '/';
    }
    private static boolean isRunningOnWindows(){
        return OperationSystem.getOperatingSystem().toLowerCase().contains("windows");
    }

}
