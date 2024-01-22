package androidx.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/* loaded from: classes.dex */
public final class a implements Closeable {
    public final File h;
    public final long i;
    public final File j;
    public final RandomAccessFile k;
    public final FileChannel l;
    public final FileLock m;

    /* renamed from: androidx.multidex.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0155a implements FileFilter {
        public C0155a(a aVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return !file.getName().equals("MultiDex.lock");
        }
    }

    /* loaded from: classes.dex */
    public static class b extends File {
        public long crc;

        public b(File file, String str) {
            super(file, str);
            this.crc = -1L;
        }
    }

    public a(File file, File file2) throws IOException {
        Log.i("MultiDex", "MultiDexExtractor(" + file.getPath() + ", " + file2.getPath() + ")");
        this.h = file;
        this.j = file2;
        this.i = f(file);
        File file3 = new File(file2, "MultiDex.lock");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
        this.k = randomAccessFile;
        try {
            FileChannel channel = randomAccessFile.getChannel();
            this.l = channel;
            try {
                Log.i("MultiDex", "Blocking on lock " + file3.getPath());
                this.m = channel.lock();
                Log.i("MultiDex", file3.getPath() + " locked");
            } catch (IOException e) {
                e = e;
                b(this.l);
                throw e;
            } catch (Error e2) {
                e = e2;
                b(this.l);
                throw e;
            } catch (RuntimeException e3) {
                e = e3;
                b(this.l);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e4) {
            b(this.k);
            throw e4;
        }
    }

    public static void b(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("MultiDex", "Failed to close resource", e);
        }
    }

    public static void c(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile("tmp-" + str, OTAManager.OTA_ZIP_SUFFIX, file.getParentFile());
        Log.i("MultiDex", "Extracting " + createTempFile.getPath());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (createTempFile.setReadOnly()) {
                Log.i("MultiDex", "Renaming to " + file.getPath());
                if (createTempFile.renameTo(file)) {
                    return;
                }
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            }
            throw new IOException("Failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
        } finally {
            b(inputStream);
            createTempFile.delete();
        }
    }

    public static SharedPreferences d(Context context) {
        return context.getSharedPreferences("multidex.version", Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    public static long e(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    public static long f(File file) throws IOException {
        long c = androidx.multidex.b.c(file);
        return c == -1 ? c - 1 : c;
    }

    public static boolean g(Context context, File file, long j, String str) {
        SharedPreferences d = d(context);
        if (d.getLong(str + "timestamp", -1L) == e(file)) {
            if (d.getLong(str + "crc", -1L) == j) {
                return false;
            }
        }
        return true;
    }

    public static void k(Context context, String str, long j, long j2, List<b> list) {
        SharedPreferences.Editor edit = d(context).edit();
        edit.putLong(str + "timestamp", j);
        edit.putLong(str + "crc", j2);
        edit.putInt(str + "dex.number", list.size() + 1);
        int i = 2;
        for (b bVar : list) {
            edit.putLong(str + "dex.crc." + i, bVar.crc);
            edit.putLong(str + "dex.time." + i, bVar.lastModified());
            i++;
        }
        edit.commit();
    }

    public final void a() {
        File[] listFiles = this.j.listFiles(new C0155a(this));
        if (listFiles == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + this.j.getPath() + ").");
            return;
        }
        for (File file : listFiles) {
            Log.i("MultiDex", "Trying to delete old file " + file.getPath() + " of size " + file.length());
            if (file.delete()) {
                Log.i("MultiDex", "Deleted old file " + file.getPath());
            } else {
                Log.w("MultiDex", "Failed to delete old file " + file.getPath());
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.m.release();
        this.l.close();
        this.k.close();
    }

    public List<? extends File> h(Context context, String str, boolean z) throws IOException {
        List<b> j;
        List<b> list;
        Log.i("MultiDex", "MultiDexExtractor.load(" + this.h.getPath() + ", " + z + ", " + str + ")");
        if (this.m.isValid()) {
            if (!z && !g(context, this.h, this.i, str)) {
                try {
                    list = i(context, str);
                } catch (IOException e) {
                    Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e);
                    j = j();
                    k(context, str, e(this.h), this.i, j);
                }
                Log.i("MultiDex", "load found " + list.size() + " secondary dex files");
                return list;
            }
            if (z) {
                Log.i("MultiDex", "Forced extraction must be performed.");
            } else {
                Log.i("MultiDex", "Detected that extraction must be performed.");
            }
            j = j();
            k(context, str, e(this.h), this.i, j);
            list = j;
            Log.i("MultiDex", "load found " + list.size() + " secondary dex files");
            return list;
        }
        throw new IllegalStateException("MultiDexExtractor was closed");
    }

    public final List<b> i(Context context, String str) throws IOException {
        Log.i("MultiDex", "loading existing secondary dex files");
        String str2 = this.h.getName() + ".classes";
        SharedPreferences d = d(context);
        int i = d.getInt(str + "dex.number", 1);
        ArrayList arrayList = new ArrayList(i + (-1));
        int i2 = 2;
        while (i2 <= i) {
            b bVar = new b(this.j, str2 + i2 + OTAManager.OTA_ZIP_SUFFIX);
            if (bVar.isFile()) {
                bVar.crc = f(bVar);
                long j = d.getLong(str + "dex.crc." + i2, -1L);
                long j2 = d.getLong(str + "dex.time." + i2, -1L);
                long lastModified = bVar.lastModified();
                if (j2 == lastModified) {
                    String str3 = str2;
                    SharedPreferences sharedPreferences = d;
                    if (j == bVar.crc) {
                        arrayList.add(bVar);
                        i2++;
                        d = sharedPreferences;
                        str2 = str3;
                    }
                }
                throw new IOException("Invalid extracted dex: " + bVar + " (key \"" + str + "\"), expected modification time: " + j2 + ", modification time: " + lastModified + ", expected crc: " + j + ", file crc: " + bVar.crc);
            }
            throw new IOException("Missing extracted secondary dex file '" + bVar.getPath() + "'");
        }
        return arrayList;
    }

    public final List<b> j() throws IOException {
        boolean z;
        String str = this.h.getName() + ".classes";
        a();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.h);
        try {
            ZipEntry entry = zipFile.getEntry("classes2.dex");
            int i = 2;
            while (entry != null) {
                b bVar = new b(this.j, str + i + OTAManager.OTA_ZIP_SUFFIX);
                arrayList.add(bVar);
                Log.i("MultiDex", "Extraction is needed for file " + bVar);
                int i2 = 0;
                boolean z2 = false;
                while (i2 < 3 && !z2) {
                    int i3 = i2 + 1;
                    c(zipFile, entry, bVar, str);
                    try {
                        bVar.crc = f(bVar);
                        z = true;
                    } catch (IOException e) {
                        Log.w("MultiDex", "Failed to read crc from " + bVar.getAbsolutePath(), e);
                        z = false;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Extraction ");
                    sb.append(z ? "succeeded" : "failed");
                    sb.append(" '");
                    sb.append(bVar.getAbsolutePath());
                    sb.append("': length ");
                    sb.append(bVar.length());
                    sb.append(" - crc: ");
                    sb.append(bVar.crc);
                    Log.i("MultiDex", sb.toString());
                    if (!z) {
                        bVar.delete();
                        if (bVar.exists()) {
                            Log.w("MultiDex", "Failed to delete corrupted secondary dex '" + bVar.getPath() + "'");
                        }
                    }
                    z2 = z;
                    i2 = i3;
                }
                if (z2) {
                    i++;
                    entry = zipFile.getEntry("classes" + i + ".dex");
                } else {
                    throw new IOException("Could not create zip file " + bVar.getAbsolutePath() + " for secondary dex (" + i + ")");
                }
            }
            try {
                zipFile.close();
            } catch (IOException e2) {
                Log.w("MultiDex", "Failed to close resource", e2);
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e3) {
                Log.w("MultiDex", "Failed to close resource", e3);
            }
            throw th;
        }
    }
}
