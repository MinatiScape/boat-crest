package com.htsmart.wristband2.dfu;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.htsmart.wristband2.dfu.l;
import com.htsmart.wristband2.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public l.c f11993a;
    public c c;
    public Context d;
    public int e = 20;
    public b b = new b(Looper.getMainLooper(), this);

    /* loaded from: classes11.dex */
    public static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<a> f11994a;

        public b(Looper looper, a aVar) {
            super(looper);
            this.f11994a = new WeakReference<>(aVar);
        }

        public final void a() {
            sendEmptyMessage(3);
        }

        public final void b(int i) {
            Message obtainMessage = obtainMessage(2);
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }

        public final void f(String str) {
            Message obtainMessage = obtainMessage(1);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = this.f11994a.get();
            if (aVar == null || aVar.f11993a == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                aVar.f11993a.a((String) message.obj);
            } else if (i == 2) {
                aVar.f11993a.a(message.arg1);
            } else if (i != 3) {
            } else {
                aVar.f11993a.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends Thread {
        public Context h;
        public b i;
        public Uri j;
        public String k;
        public volatile boolean l;

        public c(Context context, b bVar, Uri uri, String str) {
            this.h = context;
            this.i = bVar;
            this.j = uri;
            this.k = str;
        }

        public final void a() {
            this.l = true;
            interrupt();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            File file = new File(this.k + ".tmp");
            try {
                InputStream openInputStream = this.h.getContentResolver().openInputStream(this.j);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = openInputStream.read(bArr);
                    z = false;
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                        if (this.l) {
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    z = file.renameTo(new File(this.k));
                }
                if (z) {
                    this.i.f(this.k);
                } else if (this.l) {
                    this.i.a();
                } else {
                    this.i.b(1006);
                }
                fileOutputStream.close();
                openInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                this.i.b(1003);
            }
        }
    }

    public a(Context context) {
        this.d = context.getApplicationContext();
    }

    public void b() {
        c cVar = this.c;
        if (cVar == null || !cVar.isAlive()) {
            return;
        }
        this.c.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(@androidx.annotation.NonNull android.net.Uri r3) {
        /*
            r2 = this;
            android.content.Context r0 = r2.d     // Catch: java.lang.Exception -> L48
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Exception -> L48
            java.lang.String r0 = r0.getType(r3)     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = "application/zip"
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Exception -> L48
            if (r1 == 0) goto L28
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L48
            r0.<init>()     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = com.htsmart.wristband2.dfu.l.g(r1)     // Catch: java.lang.Exception -> L48
            r0.append(r1)     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = ".zip"
        L24:
            r0.append(r1)     // Catch: java.lang.Exception -> L48
            goto L43
        L28:
            java.lang.String r1 = "application/octet-stream"
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L4c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L48
            r0.<init>()     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = com.htsmart.wristband2.dfu.l.g(r1)     // Catch: java.lang.Exception -> L48
            r0.append(r1)     // Catch: java.lang.Exception -> L48
            java.lang.String r1 = ".bin"
            goto L24
        L43:
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L48
            goto L4d
        L48:
            r0 = move-exception
            r0.printStackTrace()
        L4c:
            r0 = 0
        L4d:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L5b
            com.htsmart.wristband2.dfu.a$b r3 = r2.b
            r0 = 1001(0x3e9, float:1.403E-42)
            com.htsmart.wristband2.dfu.a.b.d(r3, r0)
            return
        L5b:
            r2.f(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.dfu.a.c(android.net.Uri):void");
    }

    public void d(l.c cVar) {
        this.f11993a = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(@androidx.annotation.NonNull java.io.File r6) {
        /*
            r5 = this;
            java.lang.String r0 = ".bin"
            java.lang.String r1 = ".zip"
            boolean r2 = r6.exists()
            r3 = 1001(0x3e9, float:1.403E-42)
            if (r2 == 0) goto L6d
            boolean r2 = r6.canRead()
            if (r2 != 0) goto L13
            goto L6d
        L13:
            r2 = 0
            java.lang.String r4 = r6.getName()     // Catch: java.lang.Exception -> L55
            boolean r4 = r4.endsWith(r1)     // Catch: java.lang.Exception -> L55
            if (r4 == 0) goto L32
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L55
            r0.<init>()     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = r6.getAbsolutePath()     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = com.htsmart.wristband2.dfu.l.g(r4)     // Catch: java.lang.Exception -> L55
            r0.append(r4)     // Catch: java.lang.Exception -> L55
            r0.append(r1)     // Catch: java.lang.Exception -> L55
            goto L50
        L32:
            java.lang.String r1 = r6.getName()     // Catch: java.lang.Exception -> L55
            boolean r1 = r1.endsWith(r0)     // Catch: java.lang.Exception -> L55
            if (r1 == 0) goto L59
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L55
            r1.<init>()     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = r6.getAbsolutePath()     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = com.htsmart.wristband2.dfu.l.g(r4)     // Catch: java.lang.Exception -> L55
            r1.append(r4)     // Catch: java.lang.Exception -> L55
            r1.append(r0)     // Catch: java.lang.Exception -> L55
            r0 = r1
        L50:
            java.lang.String r2 = r0.toString()     // Catch: java.lang.Exception -> L55
            goto L59
        L55:
            r0 = move-exception
            r0.printStackTrace()
        L59:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto L65
            com.htsmart.wristband2.dfu.a$b r6 = r5.b
            com.htsmart.wristband2.dfu.a.b.d(r6, r3)
            return
        L65:
            android.net.Uri r6 = android.net.Uri.fromFile(r6)
            r5.f(r2, r6)
            return
        L6d:
            com.htsmart.wristband2.dfu.a$b r6 = r5.b
            com.htsmart.wristband2.dfu.a.b.d(r6, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.dfu.a.e(java.io.File):void");
    }

    public final void f(String str, Uri uri) {
        File[] externalFilesDirs = Utils.getExternalFilesDirs(this.d, Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDirs == null || externalFilesDirs.length <= 0 || externalFilesDirs[0] == null) {
            this.b.b(1002);
            return;
        }
        File file = externalFilesDirs[0];
        if (!file.exists() && !file.mkdirs()) {
            this.b.b(1003);
        } else if (l.a(file) < this.e) {
            this.b.b(1005);
        } else {
            File file2 = new File(file, str);
            if (file2.exists()) {
                file2.delete();
            }
            b();
            c cVar = new c(this.d, this.b, uri, file2.getAbsolutePath());
            this.c = cVar;
            cVar.start();
        }
    }

    public void g() {
        this.f11993a = null;
        b();
    }
}
