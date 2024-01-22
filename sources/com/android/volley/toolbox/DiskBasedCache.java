package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class DiskBasedCache implements Cache {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, a> f2155a;
    public long b;
    public final File c;
    public final int d;

    public DiskBasedCache(File file, int i) {
        this.f2155a = new LinkedHashMap(16, 0.75f, true);
        this.b = 0L;
        this.c = file;
        this.d = i;
    }

    public static int f(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    public static List<Header> g(b bVar) throws IOException {
        int h = h(bVar);
        if (h >= 0) {
            List<Header> emptyList = h == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i = 0; i < h; i++) {
                emptyList.add(new Header(j(bVar).intern(), j(bVar).intern()));
            }
            return emptyList;
        }
        throw new IOException("readHeaderList size=" + h);
    }

    public static int h(InputStream inputStream) throws IOException {
        return (f(inputStream) << 24) | (f(inputStream) << 0) | 0 | (f(inputStream) << 8) | (f(inputStream) << 16);
    }

    public static long i(InputStream inputStream) throws IOException {
        return ((f(inputStream) & 255) << 0) | 0 | ((f(inputStream) & 255) << 8) | ((f(inputStream) & 255) << 16) | ((f(inputStream) & 255) << 24) | ((f(inputStream) & 255) << 32) | ((f(inputStream) & 255) << 40) | ((f(inputStream) & 255) << 48) | ((255 & f(inputStream)) << 56);
    }

    public static String j(b bVar) throws IOException {
        return new String(l(bVar, i(bVar)), "UTF-8");
    }

    public static byte[] l(b bVar, long j) throws IOException {
        long a2 = bVar.a();
        if (j >= 0 && j <= a2) {
            int i = (int) j;
            if (i == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(bVar).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + a2);
    }

    public static void m(List<Header> list, OutputStream outputStream) throws IOException {
        if (list != null) {
            n(outputStream, list.size());
            for (Header header : list) {
                p(outputStream, header.getName());
                p(outputStream, header.getValue());
            }
            return;
        }
        n(outputStream, 0);
    }

    public static void n(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    public static void o(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) (j >>> 0));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    public static void p(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        o(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    public InputStream a(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    public OutputStream b(File file) throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    public final String c(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    @Override // com.android.volley.Cache
    public synchronized void clear() {
        File[] listFiles = this.c.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
        this.f2155a.clear();
        this.b = 0L;
        VolleyLog.d("Cache cleared.", new Object[0]);
    }

    public final void d(int i) {
        long j;
        long j2 = i;
        if (this.b + j2 < this.d) {
            return;
        }
        if (VolleyLog.DEBUG) {
            VolleyLog.v("Pruning old cache entries.", new Object[0]);
        }
        long j3 = this.b;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<Map.Entry<String, a>> it = this.f2155a.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (getFileForKey(value.b).delete()) {
                j = j2;
                this.b -= value.f2156a;
            } else {
                j = j2;
                String str = value.b;
                VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, c(str));
            }
            it.remove();
            i2++;
            if (((float) (this.b + j)) < this.d * 0.9f) {
                break;
            }
            j2 = j;
        }
        if (VolleyLog.DEBUG) {
            VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.b - j3), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    public final void e(String str, a aVar) {
        if (!this.f2155a.containsKey(str)) {
            this.b += aVar.f2156a;
        } else {
            this.b += aVar.f2156a - this.f2155a.get(str).f2156a;
        }
        this.f2155a.put(str, aVar);
    }

    @Override // com.android.volley.Cache
    public synchronized Cache.Entry get(String str) {
        a aVar = this.f2155a.get(str);
        if (aVar == null) {
            return null;
        }
        File fileForKey = getFileForKey(str);
        try {
            b bVar = new b(new BufferedInputStream(a(fileForKey)), fileForKey.length());
            try {
                a b2 = a.b(bVar);
                if (!TextUtils.equals(str, b2.b)) {
                    VolleyLog.d("%s: key=%s, found=%s", fileForKey.getAbsolutePath(), str, b2.b);
                    k(str);
                    return null;
                }
                return aVar.c(l(bVar, bVar.a()));
            } finally {
                bVar.close();
            }
        } catch (IOException e) {
            VolleyLog.d("%s: %s", fileForKey.getAbsolutePath(), e.toString());
            remove(str);
            return null;
        }
    }

    public File getFileForKey(String str) {
        return new File(this.c, c(str));
    }

    @Override // com.android.volley.Cache
    public synchronized void initialize() {
        long length;
        b bVar;
        if (!this.c.exists()) {
            if (!this.c.mkdirs()) {
                VolleyLog.e("Unable to create cache dir %s", this.c.getAbsolutePath());
            }
            return;
        }
        File[] listFiles = this.c.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            try {
                length = file.length();
                bVar = new b(new BufferedInputStream(a(file)), length);
            } catch (IOException unused) {
                file.delete();
            }
            try {
                a b2 = a.b(bVar);
                b2.f2156a = length;
                e(b2.b, b2);
                bVar.close();
            } catch (Throwable th) {
                bVar.close();
                throw th;
                break;
            }
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void invalidate(String str, boolean z) {
        Cache.Entry entry = get(str);
        if (entry != null) {
            entry.softTtl = 0L;
            if (z) {
                entry.ttl = 0L;
            }
            put(str, entry);
        }
    }

    public final void k(String str) {
        a remove = this.f2155a.remove(str);
        if (remove != null) {
            this.b -= remove.f2156a;
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void put(String str, Cache.Entry entry) {
        d(entry.data.length);
        File fileForKey = getFileForKey(str);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b(fileForKey));
            a aVar = new a(str, entry);
            if (aVar.d(bufferedOutputStream)) {
                bufferedOutputStream.write(entry.data);
                bufferedOutputStream.close();
                e(str, aVar);
            } else {
                bufferedOutputStream.close();
                VolleyLog.d("Failed to write header for %s", fileForKey.getAbsolutePath());
                throw new IOException();
            }
        } catch (IOException unused) {
            if (fileForKey.delete()) {
                return;
            }
            VolleyLog.d("Could not clean up file %s", fileForKey.getAbsolutePath());
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        k(str);
        if (!delete) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, c(str));
        }
    }

    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class b extends FilterInputStream {
        public final long h;
        public long i;

        public b(InputStream inputStream, long j) {
            super(inputStream);
            this.h = j;
        }

        public long a() {
            return this.h - this.i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.i++;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.i += read;
            }
            return read;
        }
    }

    public DiskBasedCache(File file) {
        this(file, 5242880);
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public long f2156a;
        public final String b;
        public final String c;
        public final long d;
        public final long e;
        public final long f;
        public final long g;
        public final List<Header> h;

        public a(String str, String str2, long j, long j2, long j3, long j4, List<Header> list) {
            this.b = str;
            this.c = "".equals(str2) ? null : str2;
            this.d = j;
            this.e = j2;
            this.f = j3;
            this.g = j4;
            this.h = list;
        }

        public static List<Header> a(Cache.Entry entry) {
            List<Header> list = entry.allResponseHeaders;
            return list != null ? list : HttpHeaderParser.c(entry.responseHeaders);
        }

        public static a b(b bVar) throws IOException {
            if (DiskBasedCache.h(bVar) == 538247942) {
                return new a(DiskBasedCache.j(bVar), DiskBasedCache.j(bVar), DiskBasedCache.i(bVar), DiskBasedCache.i(bVar), DiskBasedCache.i(bVar), DiskBasedCache.i(bVar), DiskBasedCache.g(bVar));
            }
            throw new IOException();
        }

        public Cache.Entry c(byte[] bArr) {
            Cache.Entry entry = new Cache.Entry();
            entry.data = bArr;
            entry.etag = this.c;
            entry.serverDate = this.d;
            entry.lastModified = this.e;
            entry.ttl = this.f;
            entry.softTtl = this.g;
            entry.responseHeaders = HttpHeaderParser.d(this.h);
            entry.allResponseHeaders = Collections.unmodifiableList(this.h);
            return entry;
        }

        public boolean d(OutputStream outputStream) {
            try {
                DiskBasedCache.n(outputStream, 538247942);
                DiskBasedCache.p(outputStream, this.b);
                String str = this.c;
                if (str == null) {
                    str = "";
                }
                DiskBasedCache.p(outputStream, str);
                DiskBasedCache.o(outputStream, this.d);
                DiskBasedCache.o(outputStream, this.e);
                DiskBasedCache.o(outputStream, this.f);
                DiskBasedCache.o(outputStream, this.g);
                DiskBasedCache.m(this.h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.d("%s", e.toString());
                return false;
            }
        }

        public a(String str, Cache.Entry entry) {
            this(str, entry.etag, entry.serverDate, entry.lastModified, entry.ttl, entry.softTtl, a(entry));
            this.f2156a = entry.data.length;
        }
    }
}
