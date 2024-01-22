package com.bumptech.glide.disklrucache;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class DiskLruCache implements Closeable {
    public final File h;
    public final File i;
    public final File j;
    public final File k;
    public final int l;
    public long m;
    public final int n;
    public Writer p;
    public int r;
    public long o = 0;
    public final LinkedHashMap<String, c> q = new LinkedHashMap<>(0, 0.75f, true);
    public long s = 0;
    public final ThreadPoolExecutor t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new b(null));
    public final Callable<Void> u = new a();

    /* loaded from: classes.dex */
    public final class Editor {

        /* renamed from: a  reason: collision with root package name */
        public final c f2314a;
        public final boolean[] b;
        public boolean c;

        public /* synthetic */ Editor(DiskLruCache diskLruCache, c cVar, a aVar) {
            this(cVar);
        }

        public void abort() throws IOException {
            DiskLruCache.this.m(this, false);
        }

        public void abortUnlessCommitted() {
            if (this.c) {
                return;
            }
            try {
                abort();
            } catch (IOException unused) {
            }
        }

        public final InputStream c(int i) throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.f2314a.f == this) {
                    if (this.f2314a.e) {
                        try {
                            return new FileInputStream(this.f2314a.j(i));
                        } catch (FileNotFoundException unused) {
                            return null;
                        }
                    }
                    return null;
                }
                throw new IllegalStateException();
            }
        }

        public void commit() throws IOException {
            DiskLruCache.this.m(this, true);
            this.c = true;
        }

        public File getFile(int i) throws IOException {
            File k;
            synchronized (DiskLruCache.this) {
                if (this.f2314a.f == this) {
                    if (!this.f2314a.e) {
                        this.b[i] = true;
                    }
                    k = this.f2314a.k(i);
                    DiskLruCache.this.h.mkdirs();
                } else {
                    throw new IllegalStateException();
                }
            }
            return k;
        }

        public String getString(int i) throws IOException {
            InputStream c = c(i);
            if (c != null) {
                return DiskLruCache.q(c);
            }
            return null;
        }

        public void set(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(getFile(i)), com.bumptech.glide.disklrucache.b.b);
                try {
                    outputStreamWriter2.write(str);
                    com.bumptech.glide.disklrucache.b.a(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    com.bumptech.glide.disklrucache.b.a(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public Editor(c cVar) {
            this.f2314a = cVar;
            this.b = cVar.e ? null : new boolean[DiskLruCache.this.n];
        }
    }

    /* loaded from: classes.dex */
    public final class Value {

        /* renamed from: a  reason: collision with root package name */
        public final String f2315a;
        public final long b;
        public final long[] c;
        public final File[] d;

        public /* synthetic */ Value(DiskLruCache diskLruCache, String str, long j, File[] fileArr, long[] jArr, a aVar) {
            this(str, j, fileArr, jArr);
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.o(this.f2315a, this.b);
        }

        public File getFile(int i) {
            return this.d[i];
        }

        public long getLength(int i) {
            return this.c[i];
        }

        public String getString(int i) throws IOException {
            return DiskLruCache.q(new FileInputStream(this.d[i]));
        }

        public Value(String str, long j, File[] fileArr, long[] jArr) {
            this.f2315a = str;
            this.b = j;
            this.d = fileArr;
            this.c = jArr;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Callable<Void> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.this.p == null) {
                    return null;
                }
                DiskLruCache.this.x();
                if (DiskLruCache.this.r()) {
                    DiskLruCache.this.v();
                    DiskLruCache.this.r = 0;
                }
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements ThreadFactory {
        public b() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-disk-lru-cache-thread");
            thread.setPriority(1);
            return thread;
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public final class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f2316a;
        public final long[] b;
        public File[] c;
        public File[] d;
        public boolean e;
        public Editor f;
        public long g;

        public /* synthetic */ c(DiskLruCache diskLruCache, String str, a aVar) {
            this(str);
        }

        public File j(int i) {
            return this.c[i];
        }

        public File k(int i) {
            return this.d[i];
        }

        public String l() throws IOException {
            long[] jArr;
            StringBuilder sb = new StringBuilder();
            for (long j : this.b) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public final IOException m(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public final void n(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.n) {
                for (int i = 0; i < strArr.length; i++) {
                    try {
                        this.b[i] = Long.parseLong(strArr[i]);
                    } catch (NumberFormatException unused) {
                        throw m(strArr);
                    }
                }
                return;
            }
            throw m(strArr);
        }

        public c(String str) {
            this.f2316a = str;
            this.b = new long[DiskLruCache.this.n];
            this.c = new File[DiskLruCache.this.n];
            this.d = new File[DiskLruCache.this.n];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.n; i++) {
                sb.append(i);
                this.c[i] = new File(DiskLruCache.this.h, sb.toString());
                sb.append(".tmp");
                this.d[i] = new File(DiskLruCache.this.h, sb.toString());
                sb.setLength(length);
            }
        }
    }

    public DiskLruCache(File file, int i, int i2, long j) {
        this.h = file;
        this.l = i;
        this.i = new File(file, "journal");
        this.j = new File(file, "journal.tmp");
        this.k = new File(file, "journal.bkp");
        this.n = i2;
        this.m = j;
    }

    @TargetApi(26)
    public static void l(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.close();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static void n(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j > 0) {
            if (i2 > 0) {
                File file2 = new File(file, "journal.bkp");
                if (file2.exists()) {
                    File file3 = new File(file, "journal");
                    if (file3.exists()) {
                        file2.delete();
                    } else {
                        w(file2, file3, false);
                    }
                }
                DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
                if (diskLruCache.i.exists()) {
                    try {
                        diskLruCache.t();
                        diskLruCache.s();
                        return diskLruCache;
                    } catch (IOException e) {
                        PrintStream printStream = System.out;
                        printStream.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                        diskLruCache.delete();
                    }
                }
                file.mkdirs();
                DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
                diskLruCache2.v();
                return diskLruCache2;
            }
            throw new IllegalArgumentException("valueCount <= 0");
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    @TargetApi(26)
    public static void p(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.flush();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static String q(InputStream inputStream) throws IOException {
        return com.bumptech.glide.disklrucache.b.c(new InputStreamReader(inputStream, com.bumptech.glide.disklrucache.b.b));
    }

    public static void w(File file, File file2, boolean z) throws IOException {
        if (z) {
            n(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.p == null) {
            return;
        }
        Iterator it = new ArrayList(this.q.values()).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.f != null) {
                cVar.f.abort();
            }
        }
        x();
        l(this.p);
        this.p = null;
    }

    public void delete() throws IOException {
        close();
        com.bumptech.glide.disklrucache.b.b(this.h);
    }

    public Editor edit(String str) throws IOException {
        return o(str, -1L);
    }

    public synchronized void flush() throws IOException {
        k();
        x();
        p(this.p);
    }

    public synchronized Value get(String str) throws IOException {
        k();
        c cVar = this.q.get(str);
        if (cVar == null) {
            return null;
        }
        if (cVar.e) {
            for (File file : cVar.c) {
                if (!file.exists()) {
                    return null;
                }
            }
            this.r++;
            this.p.append((CharSequence) "READ");
            this.p.append(' ');
            this.p.append((CharSequence) str);
            this.p.append('\n');
            if (r()) {
                this.t.submit(this.u);
            }
            return new Value(this, str, cVar.g, cVar.c, cVar.b, null);
        }
        return null;
    }

    public File getDirectory() {
        return this.h;
    }

    public synchronized long getMaxSize() {
        return this.m;
    }

    public synchronized boolean isClosed() {
        return this.p == null;
    }

    public final void k() {
        if (this.p == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void m(Editor editor, boolean z) throws IOException {
        c cVar = editor.f2314a;
        if (cVar.f == editor) {
            if (z && !cVar.e) {
                for (int i = 0; i < this.n; i++) {
                    if (editor.b[i]) {
                        if (!cVar.k(i).exists()) {
                            editor.abort();
                            return;
                        }
                    } else {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.n; i2++) {
                File k = cVar.k(i2);
                if (z) {
                    if (k.exists()) {
                        File j = cVar.j(i2);
                        k.renameTo(j);
                        long j2 = cVar.b[i2];
                        long length = j.length();
                        cVar.b[i2] = length;
                        this.o = (this.o - j2) + length;
                    }
                } else {
                    n(k);
                }
            }
            this.r++;
            cVar.f = null;
            if (cVar.e | z) {
                cVar.e = true;
                this.p.append((CharSequence) "CLEAN");
                this.p.append(' ');
                this.p.append((CharSequence) cVar.f2316a);
                this.p.append((CharSequence) cVar.l());
                this.p.append('\n');
                if (z) {
                    long j3 = this.s;
                    this.s = 1 + j3;
                    cVar.g = j3;
                }
            } else {
                this.q.remove(cVar.f2316a);
                this.p.append((CharSequence) "REMOVE");
                this.p.append(' ');
                this.p.append((CharSequence) cVar.f2316a);
                this.p.append('\n');
            }
            p(this.p);
            if (this.o > this.m || r()) {
                this.t.submit(this.u);
            }
            return;
        }
        throw new IllegalStateException();
    }

    public final synchronized Editor o(String str, long j) throws IOException {
        k();
        c cVar = this.q.get(str);
        if (j == -1 || (cVar != null && cVar.g == j)) {
            if (cVar != null) {
                if (cVar.f != null) {
                    return null;
                }
            } else {
                cVar = new c(this, str, null);
                this.q.put(str, cVar);
            }
            Editor editor = new Editor(this, cVar, null);
            cVar.f = editor;
            this.p.append((CharSequence) "DIRTY");
            this.p.append(' ');
            this.p.append((CharSequence) str);
            this.p.append('\n');
            p(this.p);
            return editor;
        }
        return null;
    }

    public final boolean r() {
        int i = this.r;
        return i >= 2000 && i >= this.q.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        k();
        c cVar = this.q.get(str);
        if (cVar != null && cVar.f == null) {
            for (int i = 0; i < this.n; i++) {
                File j = cVar.j(i);
                if (j.exists() && !j.delete()) {
                    throw new IOException("failed to delete " + j);
                }
                this.o -= cVar.b[i];
                cVar.b[i] = 0;
            }
            this.r++;
            this.p.append((CharSequence) "REMOVE");
            this.p.append(' ');
            this.p.append((CharSequence) str);
            this.p.append('\n');
            this.q.remove(str);
            if (r()) {
                this.t.submit(this.u);
            }
            return true;
        }
        return false;
    }

    public final void s() throws IOException {
        n(this.j);
        Iterator<c> it = this.q.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f != null) {
                next.f = null;
                while (i < this.n) {
                    n(next.j(i));
                    n(next.k(i));
                    i++;
                }
                it.remove();
            } else {
                while (i < this.n) {
                    this.o += next.b[i];
                    i++;
                }
            }
        }
    }

    public synchronized void setMaxSize(long j) {
        this.m = j;
        this.t.submit(this.u);
    }

    public synchronized long size() {
        return this.o;
    }

    public final void t() throws IOException {
        com.bumptech.glide.disklrucache.a aVar = new com.bumptech.glide.disklrucache.a(new FileInputStream(this.i), com.bumptech.glide.disklrucache.b.f2317a);
        try {
            String d = aVar.d();
            String d2 = aVar.d();
            String d3 = aVar.d();
            String d4 = aVar.d();
            String d5 = aVar.d();
            if (!"libcore.io.DiskLruCache".equals(d) || !"1".equals(d2) || !Integer.toString(this.l).equals(d3) || !Integer.toString(this.n).equals(d4) || !"".equals(d5)) {
                throw new IOException("unexpected journal header: [" + d + ", " + d2 + ", " + d4 + ", " + d5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    u(aVar.d());
                    i++;
                } catch (EOFException unused) {
                    this.r = i - this.q.size();
                    if (aVar.c()) {
                        v();
                    } else {
                        this.p = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.i, true), com.bumptech.glide.disklrucache.b.f2317a));
                    }
                    com.bumptech.glide.disklrucache.b.a(aVar);
                    return;
                }
            }
        } catch (Throwable th) {
            com.bumptech.glide.disklrucache.b.a(aVar);
            throw th;
        }
    }

    public final void u(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                substring = str.substring(i);
                if (indexOf == 6 && str.startsWith("REMOVE")) {
                    this.q.remove(substring);
                    return;
                }
            } else {
                substring = str.substring(i, indexOf2);
            }
            c cVar = this.q.get(substring);
            if (cVar == null) {
                cVar = new c(this, substring, null);
                this.q.put(substring, cVar);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
                String[] split = str.substring(indexOf2 + 1).split(HexStringBuilder.DEFAULT_SEPARATOR);
                cVar.e = true;
                cVar.f = null;
                cVar.n(split);
                return;
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
                cVar.f = new Editor(this, cVar, null);
                return;
            } else if (indexOf2 == -1 && indexOf == 4 && str.startsWith("READ")) {
                return;
            } else {
                throw new IOException("unexpected journal line: " + str);
            }
        }
        throw new IOException("unexpected journal line: " + str);
    }

    public final synchronized void v() throws IOException {
        Writer writer = this.p;
        if (writer != null) {
            l(writer);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.j), com.bumptech.glide.disklrucache.b.f2317a));
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.l));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.n));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (c cVar : this.q.values()) {
            if (cVar.f != null) {
                bufferedWriter.write("DIRTY " + cVar.f2316a + '\n');
            } else {
                bufferedWriter.write("CLEAN " + cVar.f2316a + cVar.l() + '\n');
            }
        }
        l(bufferedWriter);
        if (this.i.exists()) {
            w(this.i, this.k, true);
        }
        w(this.j, this.i, false);
        this.k.delete();
        this.p = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.i, true), com.bumptech.glide.disklrucache.b.f2317a));
    }

    public final void x() throws IOException {
        while (this.o > this.m) {
            remove(this.q.entrySet().iterator().next().getKey());
        }
    }
}
