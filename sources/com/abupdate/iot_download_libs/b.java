package com.abupdate.iot_download_libs;

import android.text.TextUtils;
import com.abupdate.trace.Trace;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final DownEntity f1880a;
    public final long b;
    public long c;
    public final long h;
    public int i;
    public int j;
    public String k;
    public File l;
    public String m;
    public String n;
    public boolean o;
    public boolean p;
    public int q;

    public b(DownEntity downEntity, long j, long j2) {
        this(downEntity, j, j2, -1, "");
    }

    public b(DownEntity downEntity, long j, long j2, int i, String str) {
        this.p = false;
        this.q = 0;
        this.f1880a = downEntity;
        this.h = j;
        this.b = j2;
        this.k = str;
        this.j = i;
        File a2 = d.a(downEntity);
        this.l = a2;
        a2.mkdirs();
        this.m = new File(downEntity.file_path).getName() + "record" + j;
        if (!TextUtils.isEmpty(str) && downEntity.getSegmentDownInfos().size() > 0) {
            this.n = new File(downEntity.file_path).getName() + this.j;
            this.p = true;
            this.i = DownConfig.SEGMENT_DOWNLOAD_RETRY_TIME;
        }
        long a3 = d.a(this.l, this.m);
        this.c = a3;
        if (a3 >= j2) {
            this.o = true;
        }
    }

    public final void a(File file, InputStream inputStream, long j) {
        long j2;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(j);
        byte[] bArr = new byte[102400];
        do {
            try {
                int read = inputStream.read(bArr);
                if (read == -1 || this.f1880a.download_cancel) {
                    break;
                }
                randomAccessFile.write(bArr, 0, read);
                j2 = this.c + read;
                this.c = j2;
            } catch (Throwable th) {
                d.a(this.l, this.m, this.c);
                randomAccessFile.getFD().sync();
                d.a(inputStream, randomAccessFile);
                throw th;
            }
        } while (j2 <= this.b);
        d.a(this.l, this.m, this.c);
        randomAccessFile.getFD().sync();
        d.a(inputStream, randomAccessFile);
    }

    public void a(boolean z) {
        if (!z) {
            this.f1880a.download_status = 0;
            this.o = false;
            return;
        }
        this.c = this.b;
        this.f1880a.download_status = 0;
        this.o = true;
    }

    public boolean a() {
        return this.o || this.f1880a.download_cancel;
    }

    public void b() {
        new File(this.l, this.m).delete();
    }

    public final void b(InputStream inputStream) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.f1880a.file_path, "rw");
        randomAccessFile.seek(this.h);
        byte[] bArr = new byte[102400];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    randomAccessFile.getFD().sync();
                    d.a(inputStream, randomAccessFile);
                    return;
                }
                randomAccessFile.write(bArr, 0, read);
            } catch (Throwable th) {
                randomAccessFile.getFD().sync();
                d.a(inputStream, randomAccessFile);
                throw th;
            }
        }
    }

    public void c() {
        new File(this.l, this.n).delete();
    }

    public final void c(InputStream inputStream, long j) {
        a(new File(this.l, this.n), inputStream, this.c);
        if (this.f1880a.download_cancel) {
            return;
        }
        Trace.i("DownThread", "writeBlockFile() file md5:" + d.a(new File(this.l, this.n)) + ",md5:" + this.k);
        if (!TextUtils.equals(d.a(new File(this.l, this.n)), this.k)) {
            this.i--;
            throw new e(-5);
        }
        b(new FileInputStream(new File(this.l, this.n)));
        c();
    }

    public final boolean d() {
        try {
            e();
            return true;
        } catch (e e) {
            Trace.e("DownThread", "downloadTask() e = ", e);
            if (e.a() == -5) {
                b();
                c();
                if (this.i >= 1) {
                    this.c = 0L;
                    return d();
                }
                this.q = -5;
                return false;
            }
            return false;
        } catch (IOException e2) {
            e = e2;
            Trace.e("DownThread", "downloadTask() e = ", e);
            this.q = -2;
            return false;
        } catch (Exception e3) {
            e = e3;
            Trace.e("DownThread", "downloadTask() e = ", e);
            this.q = -2;
            return false;
        }
    }

    public final void e() {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f1880a.url).openConnection();
        httpURLConnection.setConnectTimeout(DownConfig.CONNECT_TIMEOUT);
        httpURLConnection.setReadTimeout(DownConfig.READ_TIMEOUT);
        SSLSocketFactory sSLSocketFactory = DownConfig.SSL;
        if (sSLSocketFactory != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory);
        }
        long j = this.h;
        long j2 = this.c;
        long j3 = j + j2;
        long j4 = this.b - j2;
        httpURLConnection.setRequestProperty(HttpHeaders.RANGE, "bytes=" + j3 + "-" + ((j4 + j3) - 1));
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200 && responseCode != 206) {
            Trace.e("DownThread", "download() response code error:" + responseCode);
            return;
        }
        boolean z = this.p;
        InputStream inputStream = httpURLConnection.getInputStream();
        if (z) {
            c(inputStream, j3);
        } else {
            a(new File(this.f1880a.file_path), inputStream, j3);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.o || this.f1880a.download_cancel || this.c == this.b || this.f1880a.download_status != 0) {
            this.o = true;
            return;
        }
        int i = DownConfig.RETRY_TIMES_MAX;
        int i2 = 0;
        while (true) {
            if (i2 >= i || d()) {
                break;
            } else if (i2 == i - 1) {
                DownEntity downEntity = this.f1880a;
                if (downEntity.download_status == 0) {
                    downEntity.download_status = this.q;
                }
            } else {
                try {
                    Thread.sleep(DownConfig.RETRY_INTERVAL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i2++;
            }
        }
        this.o = true;
    }
}
