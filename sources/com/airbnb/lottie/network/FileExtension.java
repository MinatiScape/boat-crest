package com.airbnb.lottie.network;

import androidx.annotation.RestrictTo;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public enum FileExtension {
    JSON(".json"),
    ZIP(OTAManager.OTA_ZIP_SUFFIX);
    
    public final String extension;

    FileExtension(String str) {
        this.extension = str;
    }

    public String tempExtension() {
        return ".temp" + this.extension;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.extension;
    }
}
