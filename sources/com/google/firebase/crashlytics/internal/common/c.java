package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes10.dex */
public class c implements n {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11146a;
    @NonNull
    public final String b;
    @NonNull
    public final String c;

    public c(@NonNull String str, @NonNull String str2, @Nullable byte[] bArr) {
        this.b = str;
        this.c = str2;
        this.f11146a = bArr;
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @Nullable
    public CrashlyticsReport.FilesPayload.File a() {
        byte[] d = d();
        if (d == null) {
            return null;
        }
        return CrashlyticsReport.FilesPayload.File.builder().setContents(d).setFilename(this.b).build();
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @NonNull
    public String b() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.common.n
    @Nullable
    public InputStream c() {
        if (e()) {
            return null;
        }
        return new ByteArrayInputStream(this.f11146a);
    }

    @Nullable
    public final byte[] d() {
        if (e()) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(this.f11146a);
                gZIPOutputStream.finish();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    gZIPOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public final boolean e() {
        byte[] bArr = this.f11146a;
        return bArr == null || bArr.length == 0;
    }
}
