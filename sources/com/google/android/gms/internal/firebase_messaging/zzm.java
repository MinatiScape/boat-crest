package com.google.android.gms.internal.firebase_messaging;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
/* loaded from: classes7.dex */
public final class zzm {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f8656a = Logger.getLogger(zzm.class.getName());

    public static void zza(@CheckForNull InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            try {
                f8656a.logp(Level.WARNING, "com.google.common.io.Closeables", Constants.KEY_HIDE_CLOSE, "IOException thrown while closing Closeable.", (Throwable) e);
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }
}
