package com.goodix.ble.libcomx.file;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes5.dex */
public class DelayedFileWriter extends DelayedStreamWriter {
    public File h;
    public boolean i = true;
    public long j;
    public int k;
    public SimpleDateFormat l;
    public String m;

    public DelayedFileWriter(File file) {
        this.h = file;
    }

    public File getOutFileOrDir() {
        return this.h;
    }

    @Override // com.goodix.ble.libcomx.file.DelayedStreamWriter
    public void onStartThread() throws Exception {
        String str = this.m;
        if (str != null && str.trim().length() > 0) {
            if (this.j == 0) {
                this.j = System.currentTimeMillis();
            }
            this.h = new File(this.m.replace("@{date}", this.l.format(new Date())).replace("@{idx}", String.valueOf(this.k)));
            if (this.outputStream != null) {
                this.k++;
            }
        }
        if (!this.h.exists()) {
            File parentFile = this.h.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                throw new Exception("Failed to make parent directory: " + parentFile);
            } else if (this.h.createNewFile()) {
                this.outputStream = new FileOutputStream(this.h);
                return;
            } else {
                throw new Exception("Failed to create file: " + this.h.getAbsolutePath());
            }
        }
        this.outputStream = new FileOutputStream(this.h, this.i);
    }

    public void setDateFormatter(String str) {
        SimpleDateFormat simpleDateFormat = this.l;
        if (simpleDateFormat != null) {
            simpleDateFormat.applyPattern(str);
        } else {
            this.l = new SimpleDateFormat(str, Locale.US);
        }
    }

    public DelayedFileWriter(String str) {
        this.m = str;
        setDateFormatter("yyyyMMdd-HHmmss-SSS");
    }
}
