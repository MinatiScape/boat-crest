package com.ido.ble.file.transfer.spp;

import com.ido.ble.file.transfer.IFileTransferListener;
/* loaded from: classes11.dex */
public class SPPFileTransferConfig {
    public static final int ZIP_TYPE_FAST_LZ = 2;
    public static final int ZIP_TYPE_NULL = 0;
    public static final int ZIP_TYPE_ZLIB = 1;
    public String filePath;
    public IFileTransferListener iFileTransferListener;
    public String firmwareSpecName = "flash_bin";
    public int PRN = 50;
    public int zipType = 2;
    public int dataType = 255;
    public int maxRetryTimes = 3;

    public static SPPFileTransferConfig getDefaultMusicFileConfig(String str, IFileTransferListener iFileTransferListener) {
        SPPFileTransferConfig sPPFileTransferConfig = new SPPFileTransferConfig();
        sPPFileTransferConfig.filePath = str;
        sPPFileTransferConfig.firmwareSpecName = ".music";
        sPPFileTransferConfig.PRN = 50;
        sPPFileTransferConfig.zipType = 2;
        sPPFileTransferConfig.dataType = 255;
        sPPFileTransferConfig.iFileTransferListener = iFileTransferListener;
        sPPFileTransferConfig.maxRetryTimes = 0;
        return sPPFileTransferConfig;
    }

    public String toString() {
        return "SPPFileTransferConfig{filePath='" + this.filePath + "', firmwareSpecName='" + this.firmwareSpecName + "', iFileTransferListener=" + this.iFileTransferListener + ", PRN=" + this.PRN + ", zipType=" + this.zipType + ", dataType=" + this.dataType + ", maxRetryTimes=" + this.maxRetryTimes + '}';
    }
}
