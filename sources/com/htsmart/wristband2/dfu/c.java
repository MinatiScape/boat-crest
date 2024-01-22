package com.htsmart.wristband2.dfu;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.dfu.i;
import com.htsmart.wristband2.dfu.l;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class c implements i {

    /* renamed from: a  reason: collision with root package name */
    public i.a f11997a;
    public boolean b;
    public l c;
    public final com.htsmart.wristband2.dfu.a d;
    public boolean e;
    public byte f;
    public l.c g = new a();
    public l.c h = new b();

    /* loaded from: classes11.dex */
    public class a implements l.c {
        public a() {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a() {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(int i) {
            c cVar;
            int i2;
            if (i == 1006) {
                cVar = c.this;
                i2 = Integer.MAX_VALUE;
            } else {
                cVar = c.this;
                i2 = 4;
            }
            cVar.b(i2);
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(@NonNull String str) {
            if (c.this.m()) {
                c.this.d.e(new File(str));
            } else {
                c.this.q(str);
            }
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void b(int i) {
        }
    }

    /* loaded from: classes11.dex */
    public class b implements l.c {
        public b() {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a() {
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(int i) {
            c cVar;
            int i2;
            if (i == 1003) {
                cVar = c.this;
                i2 = 2;
            } else if (i == 1001) {
                cVar = c.this;
                i2 = 3;
            } else {
                cVar = c.this;
                i2 = Integer.MAX_VALUE;
            }
            cVar.b(i2);
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void a(@NonNull String str) {
            if (c.this.m()) {
                try {
                    c.this.l(str);
                } catch (C0566c e) {
                    c.this.c(e.f12000a, e.b);
                    return;
                }
            }
            c.this.q(str);
        }

        @Override // com.htsmart.wristband2.dfu.l.c
        public void b(int i) {
        }
    }

    /* renamed from: com.htsmart.wristband2.dfu.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0566c extends RuntimeException {

        /* renamed from: a  reason: collision with root package name */
        public int f12000a;
        public int b;

        public C0566c(int i, int i2) {
            this.f12000a = i;
            this.b = i2;
        }
    }

    public c(Context context) {
        try {
            l lVar = new l(context);
            this.c = lVar;
            lVar.a(this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.htsmart.wristband2.dfu.a aVar = new com.htsmart.wristband2.dfu.a(context);
        this.d = aVar;
        aVar.d(this.h);
    }

    public static boolean n(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !n(file2)) {
                            return false;
                        }
                    }
                }
                return file.delete();
            }
            return false;
        }
        return true;
    }

    @Override // com.htsmart.wristband2.dfu.i
    public void a() {
        this.b = true;
        this.f11997a = null;
        l lVar = this.c;
        if (lVar != null) {
            lVar.b();
        }
        this.d.g();
    }

    @Override // com.htsmart.wristband2.dfu.i
    public void a(i.a aVar) {
        this.f11997a = aVar;
    }

    @Override // com.htsmart.wristband2.dfu.i
    public void a(String str, boolean z, byte b2) {
        Object[] objArr = new Object[3];
        objArr[0] = str == null ? "null" : str;
        objArr[1] = Boolean.valueOf(z);
        objArr[2] = Byte.valueOf(b2);
        WristbandLog.i("check uri:%s  upgradeFirmware:%b  dialIndexFlag:%d", objArr);
        this.e = z;
        this.f = b2;
        this.b = false;
        if (TextUtils.isEmpty(str)) {
            b(0);
            return;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if ("http".equals(scheme) || "https".equals(scheme)) {
            WristbandLog.i("check uri:download", new Object[0]);
            this.c.b(str);
            return;
        }
        if ("content".equals(scheme)) {
            WristbandLog.i("check uri:content copy", new Object[0]);
        } else if (!"file".equals(scheme)) {
            WristbandLog.i("check uri:path check", new Object[0]);
            File file = new File(str);
            if (m()) {
                this.d.e(file);
                return;
            } else {
                g(file);
                return;
            }
        } else {
            File file2 = TextUtils.isEmpty(parse.getPath()) ? null : new File(parse.getPath());
            if (file2 != null && file2.exists() && file2.canRead()) {
                WristbandLog.i("check uri:file check", new Object[0]);
                if (m()) {
                    this.d.e(file2);
                    return;
                } else {
                    g(file2);
                    return;
                }
            }
            WristbandLog.i("check uri:file copy", new Object[0]);
        }
        this.d.c(parse);
    }

    public final void b(int i) {
        if (this.b) {
            return;
        }
        this.b = true;
        i.a aVar = this.f11997a;
        if (aVar != null) {
            aVar.onError(1, i);
        }
    }

    public final void c(int i, int i2) {
        if (this.b) {
            return;
        }
        this.b = true;
        i.a aVar = this.f11997a;
        if (aVar != null) {
            aVar.onError(i, i2);
        }
    }

    @Override // com.htsmart.wristband2.dfu.i
    public void cancel() {
        this.b = true;
        l lVar = this.c;
        if (lVar != null) {
            lVar.a();
        }
        this.d.b();
    }

    public final void g(File file) {
        boolean z = true;
        if (file == null || !file.exists()) {
            b(1);
        } else if (!file.canRead()) {
            b(2);
        } else {
            if (!file.getName().endsWith(".bin") && !file.getName().endsWith(OTAManager.OTA_ZIP_SUFFIX)) {
                z = false;
            }
            if (z) {
                q(file.getAbsolutePath());
            } else {
                b(3);
            }
        }
    }

    public final void h(@NonNull String str) throws C0566c {
        WristbandLog.i("modify8762C:%s", str);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(str), "rw");
            byte[] bArr = {this.f};
            randomAccessFile.seek(61L);
            randomAccessFile.write(bArr);
            randomAccessFile.seek(516L);
            randomAccessFile.write(bArr);
            randomAccessFile.close();
        } catch (Exception e) {
            WristbandLog.w("modify8762C bin failed", e);
            throw new C0566c(1, Integer.MAX_VALUE);
        }
    }

    public final void l(@NonNull String str) throws C0566c {
        com.htsmart.wristband2.utils.a b2 = com.htsmart.wristband2.utils.a.b();
        String d = b2 != null ? b2.d() : null;
        if (TextUtils.isEmpty(d) || d.length() < 76) {
            throw new C0566c(2, 0);
        }
        byte[] hexStr2Bytes = BytesUtil.hexStr2Bytes(WristbandVersion.get_version_hardware(d));
        if (hexStr2Bytes == null || hexStr2Bytes.length != 4) {
            throw new C0566c(2, 0);
        }
        byte b3 = hexStr2Bytes[2];
        boolean z = (b3 & 1) > 0;
        boolean z2 = (b3 & 128) > 0;
        if (!z2 && !z) {
            throw new C0566c(2, 4);
        }
        if (z2) {
            p(str);
        } else {
            h(str);
        }
    }

    public final boolean m() {
        return (this.e || this.f == 0) ? false : true;
    }

    public final byte o() {
        byte b2 = this.f;
        if (b2 != -95) {
            switch (b2) {
                case -91:
                    return (byte) 18;
                case -90:
                    return (byte) 19;
                case -89:
                    return (byte) 20;
                case -88:
                    return (byte) 21;
                case -87:
                    return (byte) 22;
                case -86:
                    return (byte) 23;
                case -85:
                    return (byte) 24;
                default:
                    return (byte) 0;
            }
        }
        return (byte) 17;
    }

    public final void p(@NonNull String str) throws C0566c {
        WristbandLog.i("modifyNordic:%s", str);
        File file = new File(str);
        if (!file.getName().endsWith(OTAManager.OTA_ZIP_SUFFIX)) {
            throw new C0566c(1, 3);
        }
        String parent = file.getParent();
        File file2 = new File(parent, file.getName() + "TempDir");
        File file3 = null;
        try {
            try {
                try {
                    List<File> a2 = m.a(file, file2);
                    Iterator<File> it = a2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        File next = it.next();
                        if (next.getName().equals("firmware.dat")) {
                            file3 = next;
                            break;
                        }
                    }
                    if (file3 == null) {
                        throw new C0566c(1, 3);
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                    try {
                        byte[] bArr = {o()};
                        randomAccessFile.seek(12L);
                        randomAccessFile.write(bArr);
                        randomAccessFile.close();
                        m.a(a2, file);
                    } finally {
                    }
                } finally {
                    n(file2);
                }
            } catch (Exception e) {
                WristbandLog.w("modifyNordic bin failed", e);
                throw new C0566c(1, Integer.MAX_VALUE);
            }
        } catch (C0566c e2) {
            throw e2;
        }
    }

    public final void q(String str) {
        if (this.b) {
            return;
        }
        this.b = true;
        i.a aVar = this.f11997a;
        if (aVar != null) {
            aVar.a(str);
        }
    }
}
