package com.abupdate.mqtt_libs.mqttv3.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public File f1955a;
    public RandomAccessFile b;
    public Object c;

    public j(File file, String str) throws Exception {
        this.f1955a = new File(file, str);
        if (i.a("java.nio.channels.FileLock")) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.f1955a, "rw");
                this.b = randomAccessFile;
                Object invoke = randomAccessFile.getClass().getMethod("getChannel", new Class[0]).invoke(this.b, new Object[0]);
                this.c = invoke.getClass().getMethod("tryLock", new Class[0]).invoke(invoke, new Object[0]);
            } catch (IllegalAccessException unused) {
                this.c = null;
            } catch (IllegalArgumentException unused2) {
                this.c = null;
            } catch (NoSuchMethodException unused3) {
                this.c = null;
            }
            if (this.c != null) {
                return;
            }
            a();
            throw new Exception("Problem obtaining file lock");
        }
    }

    public void a() {
        try {
            Object obj = this.c;
            if (obj != null) {
                obj.getClass().getMethod("release", new Class[0]).invoke(this.c, new Object[0]);
                this.c = null;
            }
        } catch (Exception unused) {
        }
        RandomAccessFile randomAccessFile = this.b;
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException unused2) {
            }
            this.b = null;
        }
        File file = this.f1955a;
        if (file != null && file.exists()) {
            this.f1955a.delete();
        }
        this.f1955a = null;
    }
}
