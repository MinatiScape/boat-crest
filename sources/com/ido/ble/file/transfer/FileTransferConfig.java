package com.ido.ble.file.transfer;

import android.os.Build;
/* loaded from: classes11.dex */
public class FileTransferConfig {
    public static final int ZIP_TYPE_FAST_LZ = 2;
    public static final int ZIP_TYPE_NULL = 0;
    public static final int ZIP_TYPE_ZLIB = 1;
    public String filePath;
    public IFileTransferListener iFileTransferListener;
    public String firmwareSpecName = "flash_bin";
    public int PRN = 10;
    public int zipType = 2;
    public int dataType = 255;
    public int maxRetryTimes = 3;
    public int oriSize = 0;
    public boolean isNeedChangeSpeedMode = true;

    public static FileTransferConfig getDefaultApolloOTAConfig(String str, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.filePath = str;
        fileTransferConfig.firmwareSpecName = ".fw";
        fileTransferConfig.PRN = 10;
        fileTransferConfig.zipType = 2;
        fileTransferConfig.dataType = 255;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.maxRetryTimes = 3;
        fileTransferConfig.isNeedChangeSpeedMode = false;
        return fileTransferConfig;
    }

    public static FileTransferConfig getDefaultMusicFileConfig(String str, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.filePath = str;
        fileTransferConfig.firmwareSpecName = ".music";
        fileTransferConfig.PRN = 10;
        fileTransferConfig.zipType = 2;
        fileTransferConfig.dataType = 255;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.maxRetryTimes = 3;
        fileTransferConfig.isNeedChangeSpeedMode = false;
        return fileTransferConfig;
    }

    public static FileTransferConfig getDefaultTransLangFileConfig(String str, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.filePath = str;
        fileTransferConfig.firmwareSpecName = ".fzbin";
        fileTransferConfig.PRN = 10;
        fileTransferConfig.zipType = 0;
        fileTransferConfig.dataType = 255;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.maxRetryTimes = 3;
        fileTransferConfig.isNeedChangeSpeedMode = true;
        return fileTransferConfig;
    }

    public static FileTransferConfig getDefaultTransPictureConfig(String str, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.filePath = str;
        fileTransferConfig.firmwareSpecName = "wallpaper.z";
        fileTransferConfig.PRN = 10;
        fileTransferConfig.zipType = 2;
        fileTransferConfig.dataType = 2;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.maxRetryTimes = 3;
        fileTransferConfig.isNeedChangeSpeedMode = true;
        return fileTransferConfig;
    }

    public static FileTransferConfig getDefaultUbloxAGpsFileConfig(String str, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.maxRetryTimes = 2;
        fileTransferConfig.isNeedChangeSpeedMode = true;
        fileTransferConfig.filePath = str;
        fileTransferConfig.dataType = 255;
        fileTransferConfig.zipType = 0;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.firmwareSpecName = "";
        if (Build.VERSION.SDK_INT <= 24) {
            fileTransferConfig.PRN = 2;
        } else {
            fileTransferConfig.PRN = 10;
        }
        return fileTransferConfig;
    }

    public static FileTransferConfig getDefaultWatchPlateFileConfig(String str, String str2, IFileTransferListener iFileTransferListener) {
        FileTransferConfig fileTransferConfig = new FileTransferConfig();
        fileTransferConfig.maxRetryTimes = 2;
        fileTransferConfig.isNeedChangeSpeedMode = true;
        fileTransferConfig.filePath = str2;
        fileTransferConfig.dataType = 255;
        fileTransferConfig.zipType = 2;
        fileTransferConfig.iFileTransferListener = iFileTransferListener;
        fileTransferConfig.firmwareSpecName = str;
        if (Build.VERSION.SDK_INT <= 24) {
            fileTransferConfig.PRN = 2;
        } else {
            fileTransferConfig.PRN = 10;
        }
        return fileTransferConfig;
    }

    public String toString() {
        return "FileTransferConfig{filePath='" + this.filePath + "', firmwareSpecName='" + this.firmwareSpecName + "', iFileTransferListener=" + this.iFileTransferListener + ", PRN=" + this.PRN + ", zipType=" + this.zipType + ", dataType=" + this.dataType + ", maxRetryTimes=" + this.maxRetryTimes + ", oriSize=" + this.oriSize + ", isNeedChangeSpeedMode=" + this.isNeedChangeSpeedMode + '}';
    }
}
