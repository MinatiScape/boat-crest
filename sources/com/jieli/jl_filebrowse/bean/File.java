package com.jieli.jl_filebrowse.bean;

import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public abstract class File {
    public FileStruct fileStruct;
    private int level = 0;
    private boolean loadFinished;
    private File parent;

    public void clean() {
    }

    public FileStruct getFileStruct() {
        return this.fileStruct;
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        return this.fileStruct.getName();
    }

    public File getParent() {
        return this.parent;
    }

    public String getPathString() {
        if (this.parent == null) {
            return getName();
        }
        return this.parent.getPathString() + MqttTopic.TOPIC_LEVEL_SEPARATOR + getName();
    }

    public abstract int getStartIndex();

    public boolean isFolder() {
        return !this.fileStruct.isFile();
    }

    public boolean isLoadFinished(boolean z) {
        return this.loadFinished;
    }

    public void setFileStruct(FileStruct fileStruct) {
        this.fileStruct = fileStruct;
    }

    public void setLoadFinished(boolean z) {
        this.loadFinished = z;
    }

    public void setParent(File file) {
        this.parent = file;
        this.level = file.level + 1;
    }
}
