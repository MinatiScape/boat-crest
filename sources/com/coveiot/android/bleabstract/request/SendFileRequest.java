package com.coveiot.android.bleabstract.request;

import java.io.File;
/* loaded from: classes2.dex */
public class SendFileRequest extends BleBaseRequest {
    public int f;
    public File g;

    public SendFileRequest(int i, File file) {
        this.f = i;
        this.g = file;
    }

    public File getFile() {
        return this.g;
    }

    public int getFileType() {
        return this.f;
    }
}
