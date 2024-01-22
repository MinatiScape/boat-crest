package com.github.siyamed.shapeimageview.path.parser;

import java.io.InputStream;
/* loaded from: classes9.dex */
public class IoUtil {
    public static final void closeQuitely(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable unused) {
            }
        }
    }
}
