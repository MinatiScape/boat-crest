package com.goodix.ble.libcomx.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes5.dex */
public class FileAsyncWriter extends StreamAsyncWriter {
    public File j;
    public boolean k = true;
    public boolean l = true;
    public SimpleDateFormat m;
    public String n;
    public FileOutputStream o;

    public FileAsyncWriter(File file) {
        this.j = file;
    }

    public File getOutFile() {
        return this.j;
    }

    @Override // com.goodix.ble.libcomx.file.StreamAsyncWriter
    public void onCloseOutputStream(OutputStream outputStream) {
        if (this.o == outputStream) {
            this.o = null;
        }
    }

    @Override // com.goodix.ble.libcomx.file.StreamAsyncWriter
    public OutputStream onPrepareOutputStream() throws Exception {
        FileOutputStream fileOutputStream = this.o;
        if (fileOutputStream != null) {
            return fileOutputStream;
        }
        if (this.j == null) {
            String str = this.n;
            if (str == null || str.trim().length() <= 0) {
                return null;
            }
            this.j = new File(this.n.replace("${date}", this.m.format(new Date())));
        }
        if (!this.j.exists()) {
            File parentFile = this.j.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                throw new Exception("Failed to make parent directory: " + parentFile);
            } else if (this.j.createNewFile()) {
                this.o = new FileOutputStream(this.j);
            } else {
                throw new Exception("Failed to create file: " + this.j.getAbsolutePath());
            }
        } else {
            this.o = new FileOutputStream(this.j, !this.l || this.k);
        }
        this.l = false;
        return this.o;
    }

    public void setDateFormatter(String str) {
        SimpleDateFormat simpleDateFormat = this.m;
        if (simpleDateFormat != null) {
            simpleDateFormat.applyPattern(str);
        } else {
            this.m = new SimpleDateFormat(str, Locale.US);
        }
    }

    public FileAsyncWriter(String str) {
        this.n = str;
        setDateFormatter("yyyyMMdd-HHmmss-SSS");
    }
}
