package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes10.dex */
public class k implements n {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final File f11159a;
    @NonNull
    public final String b;
    @NonNull
    public final String c;

    public k(@NonNull String str, @NonNull String str2, @NonNull File file) {
        this.b = str;
        this.c = str2;
        this.f11159a = file;
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @Nullable
    public CrashlyticsReport.FilesPayload.File a() {
        byte[] d = d();
        if (d != null) {
            return CrashlyticsReport.FilesPayload.File.builder().setContents(d).setFilename(this.b).build();
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @NonNull
    public String b() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @Nullable
    public InputStream c() {
        if (this.f11159a.exists() && this.f11159a.isFile()) {
            try {
                return new FileInputStream(this.f11159a);
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    @Nullable
    public final byte[] d() {
        byte[] bArr = new byte[8192];
        try {
            InputStream c = c();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            if (c == null) {
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                if (c != null) {
                    c.close();
                }
                return null;
            }
            while (true) {
                try {
                    int read = c.read(bArr);
                    if (read > 0) {
                        gZIPOutputStream.write(bArr, 0, read);
                    } else {
                        gZIPOutputStream.finish();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        gZIPOutputStream.close();
                        byteArrayOutputStream.close();
                        c.close();
                        return byteArray;
                    }
                } catch (Throwable th) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
