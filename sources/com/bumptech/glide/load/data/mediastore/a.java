package com.bumptech.glide.load.data.mediastore;

import java.io.File;
/* loaded from: classes2.dex */
public class a {
    public boolean a(File file) {
        return file.exists();
    }

    public File b(String str) {
        return new File(str);
    }

    public long c(File file) {
        return file.length();
    }
}
