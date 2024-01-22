package com.goodix.ble.libcomx.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
/* loaded from: classes5.dex */
public class RotateFileOutputStream extends OutputStream {
    public long fileSize;
    public final boolean h;
    public final File[] i;
    public final File j;
    public FileOutputStream k;
    public long l;
    public boolean splitBytes;

    public RotateFileOutputStream(File file, int i, int i2) throws IOException {
        this(file, i, i2, true);
    }

    public final void a() throws IOException {
        FileOutputStream fileOutputStream = this.k;
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        if (this.i[0].exists() && !this.i[0].delete()) {
            PrintStream printStream = System.out;
            printStream.println("RotateFile: Failed to delete file: " + this.i[0].getCanonicalPath());
            return;
        }
        int i = 1;
        while (true) {
            File[] fileArr = this.i;
            if (i < fileArr.length) {
                if (fileArr[i].exists()) {
                    File[] fileArr2 = this.i;
                    if (!fileArr2[i].renameTo(fileArr2[i - 1]) && !this.i[i].delete()) {
                        PrintStream printStream2 = System.out;
                        printStream2.println("RotateFile: Failed to delete file: " + this.i[i].getCanonicalPath());
                    }
                }
                i++;
            } else {
                this.k = new FileOutputStream(this.j, this.h);
                this.l = 0L;
                return;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.k.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.k.flush();
    }

    public void setSplitBytes(boolean z) {
        this.splitBytes = z;
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) throws IOException {
        if (this.l + 1 > this.fileSize) {
            a();
            this.k.write(i);
            this.l++;
        } else {
            this.k.write(i);
            this.l++;
        }
    }

    public void write0(byte[] bArr, int i, int i2) throws IOException {
        if (this.l >= this.fileSize) {
            a();
        }
        if (this.splitBytes) {
            long j = this.l;
            long j2 = this.fileSize;
            if (i2 + j > j2) {
                int i3 = (int) (j2 - j);
                int i4 = i2 - i3;
                this.k.write(bArr, i, i3);
                this.l += i3;
                if (i4 > 0) {
                    write0(bArr, i + i3, i4);
                    return;
                }
                return;
            }
        }
        this.k.write(bArr, i, i2);
        this.l += i2;
    }

    public RotateFileOutputStream(File file, int i, int i2, boolean z) throws IOException {
        this.splitBytes = true;
        this.h = z;
        this.fileSize = i2;
        this.j = file;
        i = i <= 0 ? 4 : i;
        this.i = new File[i + 1];
        String path = file.getPath();
        String str = i < 10 ? "%s.%d" : i < 100 ? "%s.%02d" : "%s.%03d";
        for (int i3 = 0; i3 < i; i3++) {
            this.i[i3] = new File(String.format(str, path, Integer.valueOf(i - i3)));
        }
        this.i[i] = this.j;
        this.k = new FileOutputStream(this.j, z);
        if (z && file.exists()) {
            long length = file.length();
            this.l = length;
            if (length >= this.fileSize) {
                a();
                return;
            }
            return;
        }
        this.k = new FileOutputStream(this.j, z);
        this.l = 0L;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write0(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        write0(bArr, i, i2);
    }
}
