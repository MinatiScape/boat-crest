package com.google.android.gms.internal.firebase_ml;

import com.facebook.stetho.inspector.network.DecompressionHelper;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
/* loaded from: classes7.dex */
public final class zzhd {

    /* renamed from: a  reason: collision with root package name */
    public InputStream f8768a;
    public final String b;
    public final String c;
    public final zzgz d;
    public zzhj e;
    public final int f;
    public final String g;
    public final zzhc h;
    public int i;
    public boolean j;
    public boolean k;

    public zzhd(zzhc zzhcVar, zzhj zzhjVar) throws IOException {
        StringBuilder sb;
        this.h = zzhcVar;
        this.i = zzhcVar.zzfy();
        this.j = zzhcVar.zzfz();
        this.e = zzhjVar;
        this.b = zzhjVar.getContentEncoding();
        int statusCode = zzhjVar.getStatusCode();
        boolean z = false;
        statusCode = statusCode < 0 ? 0 : statusCode;
        this.f = statusCode;
        String reasonPhrase = zzhjVar.getReasonPhrase();
        this.g = reasonPhrase;
        Logger logger = zzhh.f8770a;
        if (this.j && logger.isLoggable(Level.CONFIG)) {
            z = true;
        }
        if (z) {
            sb = new StringBuilder();
            sb.append("-------------- RESPONSE --------------");
            String str = zzjt.zzaig;
            sb.append(str);
            String zzgj = zzhjVar.zzgj();
            if (zzgj != null) {
                sb.append(zzgj);
            } else {
                sb.append(statusCode);
                if (reasonPhrase != null) {
                    sb.append(' ');
                    sb.append(reasonPhrase);
                }
            }
            sb.append(str);
        } else {
            sb = null;
        }
        zzhcVar.zzgb().zza(zzhjVar, z ? sb : null);
        String contentType = zzhjVar.getContentType();
        contentType = contentType == null ? zzhcVar.zzgb().getContentType() : contentType;
        this.c = contentType;
        this.d = a(contentType);
        if (z) {
            logger.logp(Level.CONFIG, "com.google.api.client.http.HttpResponse", "<init>", sb.toString());
        }
    }

    public static zzgz a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new zzgz(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final Charset b() {
        zzgz zzgzVar = this.d;
        if (zzgzVar != null && zzgzVar.zzfu() != null) {
            return this.d.zzfu();
        }
        return zziw.ISO_8859_1;
    }

    public final void disconnect() throws IOException {
        ignore();
        this.e.disconnect();
    }

    public final InputStream getContent() throws IOException {
        if (!this.k) {
            InputStream content = this.e.getContent();
            if (content != null) {
                try {
                    String str = this.b;
                    if (str != null && str.contains(DecompressionHelper.GZIP_ENCODING)) {
                        content = new GZIPInputStream(content);
                    }
                    Logger logger = zzhh.f8770a;
                    if (this.j) {
                        Level level = Level.CONFIG;
                        if (logger.isLoggable(level)) {
                            content = new zzji(content, logger, level, this.i);
                        }
                    }
                    this.f8768a = content;
                } catch (EOFException unused) {
                    content.close();
                } catch (Throwable th) {
                    content.close();
                    throw th;
                }
            }
            this.k = true;
        }
        return this.f8768a;
    }

    public final String getContentType() {
        return this.c;
    }

    public final int getStatusCode() {
        return this.f;
    }

    public final String getStatusMessage() {
        return this.g;
    }

    public final void ignore() throws IOException {
        InputStream content = getContent();
        if (content != null) {
            content.close();
        }
    }

    public final <T> T zza(Class<T> cls) throws IOException {
        int i = this.f;
        boolean z = true;
        if (this.h.getRequestMethod().equals("HEAD") || i / 100 == 1 || i == 204 || i == 304) {
            ignore();
            z = false;
        }
        if (z) {
            return (T) this.h.zzgd().zza(getContent(), b(), cls);
        }
        return null;
    }

    public final zzgx zzga() {
        return this.h.zzgb();
    }

    public final boolean zzgg() {
        int i = this.f;
        return i >= 200 && i < 300;
    }

    public final String zzgh() throws IOException {
        InputStream content = getContent();
        if (content == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzml.checkNotNull(content);
            zzml.checkNotNull(byteArrayOutputStream);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = content.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    content.close();
                    return byteArrayOutputStream.toString(b().name());
                }
            }
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }
}
