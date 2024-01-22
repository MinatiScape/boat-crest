package com.coveiot.coveaccess.mediauplaod.model;

import java.io.File;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class MediaUploadReq implements Serializable {
    public File file;
    public MediaClassType mediaClassType;

    public MediaUploadReq(MediaClassType mediaClassType, File file) {
        this.mediaClassType = mediaClassType;
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public MediaClassType getMediaClassType() {
        return this.mediaClassType;
    }
}
