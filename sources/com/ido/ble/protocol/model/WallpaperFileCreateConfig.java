package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class WallpaperFileCreateConfig {
    private String fileName;
    private int format = 5;
    private String outFilePath;
    private String saveFileName;
    private String sourceFilePath;

    public int getFormat() {
        return this.format;
    }

    public String getOutFilePath() {
        return this.outFilePath;
    }

    public String getSourceFilePath() {
        return this.sourceFilePath;
    }

    public void setFormat(int i) {
        this.format = i;
    }

    public void setOutFilePath(String str) {
        this.outFilePath = str + ".lz";
        this.saveFileName = str;
    }

    public void setSourceFilePath(String str) {
        this.sourceFilePath = str;
        this.fileName = str;
    }

    public String toString() {
        return "WallpaperFileCreateConfig{outFileName='" + this.sourceFilePath + "', outFilePath='" + this.outFilePath + "', format=" + this.format + ", fileName='" + this.fileName + "', saveFileName='" + this.saveFileName + "'}";
    }
}
