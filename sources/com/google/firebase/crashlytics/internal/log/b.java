package com.google.firebase.crashlytics.internal.log;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.log.QueueFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
/* loaded from: classes10.dex */
public class b implements com.google.firebase.crashlytics.internal.log.a {
    public static final Charset d = Charset.forName("UTF-8");

    /* renamed from: a  reason: collision with root package name */
    public final File f11167a;
    public final int b;
    public QueueFile c;

    /* loaded from: classes10.dex */
    public class a implements QueueFile.ElementReader {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f11168a;
        public final /* synthetic */ int[] b;

        public a(b bVar, byte[] bArr, int[] iArr) {
            this.f11168a = bArr;
            this.b = iArr;
        }

        @Override // com.google.firebase.crashlytics.internal.log.QueueFile.ElementReader
        public void read(InputStream inputStream, int i) throws IOException {
            try {
                inputStream.read(this.f11168a, this.b[0], i);
                int[] iArr = this.b;
                iArr[0] = iArr[0] + i;
            } finally {
                inputStream.close();
            }
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.log.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0538b {

        /* renamed from: a  reason: collision with root package name */
        public final byte[] f11169a;
        public final int b;

        public C0538b(byte[] bArr, int i) {
            this.f11169a = bArr;
            this.b = i;
        }
    }

    public b(File file, int i) {
        this.f11167a = file;
        this.b = i;
    }

    @Override // com.google.firebase.crashlytics.internal.log.a
    public void a() {
        CommonUtils.closeOrLog(this.c, "There was a problem closing the Crashlytics log file.");
        this.c = null;
    }

    @Override // com.google.firebase.crashlytics.internal.log.a
    public String b() {
        byte[] c = c();
        if (c != null) {
            return new String(c, d);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.log.a
    public byte[] c() {
        C0538b g = g();
        if (g == null) {
            return null;
        }
        int i = g.b;
        byte[] bArr = new byte[i];
        System.arraycopy(g.f11169a, 0, bArr, 0, i);
        return bArr;
    }

    @Override // com.google.firebase.crashlytics.internal.log.a
    public void d() {
        a();
        this.f11167a.delete();
    }

    @Override // com.google.firebase.crashlytics.internal.log.a
    public void e(long j, String str) {
        h();
        f(j, str);
    }

    public final void f(long j, String str) {
        int i;
        if (this.c == null) {
            return;
        }
        if (str == null) {
            str = "null";
        }
        try {
            if (str.length() > this.b / 4) {
                str = "..." + str.substring(str.length() - i);
            }
            this.c.e(String.format(Locale.US, "%d %s%n", Long.valueOf(j), str.replaceAll("\r", HexStringBuilder.DEFAULT_SEPARATOR).replaceAll("\n", HexStringBuilder.DEFAULT_SEPARATOR)).getBytes(d));
            while (!this.c.j() && this.c.u() > this.b) {
                this.c.q();
            }
        } catch (IOException e) {
            Logger.getLogger().e("There was a problem writing to the Crashlytics log.", e);
        }
    }

    public final C0538b g() {
        if (this.f11167a.exists()) {
            h();
            QueueFile queueFile = this.c;
            if (queueFile == null) {
                return null;
            }
            int[] iArr = {0};
            byte[] bArr = new byte[queueFile.u()];
            try {
                this.c.h(new a(this, bArr, iArr));
            } catch (IOException e) {
                Logger.getLogger().e("A problem occurred while reading the Crashlytics log file.", e);
            }
            return new C0538b(bArr, iArr[0]);
        }
        return null;
    }

    public final void h() {
        if (this.c == null) {
            try {
                this.c = new QueueFile(this.f11167a);
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                logger.e("Could not open log file: " + this.f11167a, e);
            }
        }
    }
}
