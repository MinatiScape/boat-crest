package com.crrepa.ble.conn.bean;

import java.io.File;
/* loaded from: classes9.dex */
public class CRPCustomizeWatchFaceInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7644a;
    public File b;

    public CRPCustomizeWatchFaceInfo(int i, File file) {
        this.f7644a = i;
        this.b = file;
    }

    public File getFile() {
        return this.b;
    }

    public int getIndex() {
        return this.f7644a;
    }

    public void setFile(File file) {
        this.b = file;
    }

    public void setIndex(int i) {
        this.f7644a = i;
    }
}
