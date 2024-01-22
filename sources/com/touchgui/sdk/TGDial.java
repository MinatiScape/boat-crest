package com.touchgui.sdk;

import java.io.File;
/* loaded from: classes12.dex */
public class TGDial {
    private final int dialId;
    private boolean dynamicDial;
    private final String filePath;
    private boolean photoDial;
    private int replacedDialId;

    public TGDial(int i, String str) {
        this(i, str, Boolean.FALSE);
    }

    public int getDialId() {
        return this.dialId;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public int getReplacedDialId() {
        return this.replacedDialId;
    }

    public File getResWatch() {
        return new File(this.filePath);
    }

    public boolean isDynamicDial() {
        return this.dynamicDial;
    }

    public boolean isPhotoDial() {
        return this.photoDial;
    }

    public void setDynamicDial(boolean z) {
        this.dynamicDial = z;
    }

    public void setReplacedDialId(int i) {
        this.replacedDialId = i;
    }

    public TGDial(int i, String str, Boolean bool) {
        this.replacedDialId = 0;
        this.dynamicDial = false;
        this.photoDial = false;
        this.dialId = i;
        this.filePath = str;
        this.photoDial = bool.booleanValue();
    }
}
