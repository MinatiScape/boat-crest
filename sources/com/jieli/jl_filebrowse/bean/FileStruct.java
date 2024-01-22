package com.jieli.jl_filebrowse.bean;
/* loaded from: classes11.dex */
public class FileStruct {
    private int cluster;
    private byte devIndex;
    private boolean file;
    private short fileNum;
    private String name;
    private boolean unicode;

    public int getCluster() {
        return this.cluster;
    }

    public byte getDevIndex() {
        return this.devIndex;
    }

    public short getFileNum() {
        return this.fileNum;
    }

    public String getName() {
        return this.name;
    }

    public boolean isFile() {
        return this.file;
    }

    public boolean isUnicode() {
        return this.unicode;
    }

    public void setCluster(int i) {
        this.cluster = i;
    }

    public void setDevIndex(byte b) {
        this.devIndex = b;
    }

    public void setFile(boolean z) {
        this.file = z;
    }

    public void setFileNum(short s) {
        this.fileNum = s;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUnicode(boolean z) {
        this.unicode = z;
    }

    public String toString() {
        return "FileStruct{file=" + this.file + ", unicode=" + this.unicode + ", cluster=" + this.cluster + ", fileNum=" + ((int) this.fileNum) + ", devIndex=" + ((int) this.devIndex) + ", name='" + this.name + "'}";
    }
}
