package com.abupdate.mqtt_libs.mqttv3.b;

import com.abupdate.mqtt_libs.mqttv3.a.j;
import com.abupdate.mqtt_libs.mqttv3.a.m;
import com.abupdate.mqtt_libs.mqttv3.g;
import com.abupdate.mqtt_libs.mqttv3.k;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
/* loaded from: classes.dex */
public class b implements g {
    public static FilenameFilter d;

    /* renamed from: a  reason: collision with root package name */
    public File f1962a;
    public File b;
    public j c;

    public b() {
        this(System.getProperty("user.dir"));
    }

    public static FilenameFilter d() {
        if (d == null) {
            d = new d(".msg");
        }
        return d;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a(String str, String str2) throws k {
        if (this.f1962a.exists() && !this.f1962a.isDirectory()) {
            throw new k();
        }
        if (!this.f1962a.exists() && !this.f1962a.mkdirs()) {
            throw new k();
        }
        if (this.f1962a.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (c(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append("-");
            for (int i2 = 0; i2 < str2.length(); i2++) {
                char charAt2 = str2.charAt(i2);
                if (c(charAt2)) {
                    stringBuffer.append(charAt2);
                }
            }
            synchronized (this) {
                if (this.b == null) {
                    File file = new File(this.f1962a, stringBuffer.toString());
                    this.b = file;
                    if (!file.exists()) {
                        this.b.mkdir();
                    }
                }
                try {
                    this.c = new j(this.b, ".lck");
                } catch (Exception unused) {
                }
                b(this.b);
            }
            return;
        }
        throw new k();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void b(String str) throws k {
        e();
        File file = this.b;
        File file2 = new File(file, str + ".msg");
        if (file2.exists()) {
            file2.delete();
        }
    }

    public final boolean c(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    public final void e() throws k {
        if (this.b == null) {
            throw new k();
        }
    }

    public final File[] f() throws k {
        e();
        File[] listFiles = this.b.listFiles(d());
        if (listFiles != null) {
            return listFiles;
        }
        throw new k();
    }

    public b(String str) {
        this.b = null;
        this.c = null;
        this.f1962a = new File(str);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public boolean c(String str) throws k {
        e();
        File file = this.b;
        return new File(file, str + ".msg").exists();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public Enumeration b() throws k {
        String name;
        e();
        File[] f = f();
        Vector vector = new Vector(f.length);
        for (File file : f) {
            vector.addElement(file.getName().substring(0, name.length() - 4));
        }
        return vector.elements();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void c() throws k {
        e();
        for (File file : f()) {
            file.delete();
        }
        this.b.delete();
    }

    public final void b(File file) throws k {
        File[] listFiles = file.listFiles(new c(".bup"));
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                File file2 = new File(file, listFiles[i].getName().substring(0, listFiles[i].getName().length() - 4));
                if (!listFiles[i].renameTo(file2)) {
                    file2.delete();
                    listFiles[i].renameTo(file2);
                }
            }
            return;
        }
        throw new k();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a() throws k {
        synchronized (this) {
            j jVar = this.c;
            if (jVar != null) {
                jVar.a();
            }
            if (f().length == 0) {
                this.b.delete();
            }
            this.b = null;
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public void a(String str, com.abupdate.mqtt_libs.mqttv3.j jVar) throws k {
        e();
        File file = this.b;
        File file2 = new File(file, str + ".msg");
        File file3 = this.b;
        File file4 = new File(file3, str + ".msg.bup");
        if (file2.exists() && !file2.renameTo(file4)) {
            file4.delete();
            file2.renameTo(file4);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(jVar.a(), jVar.c(), jVar.b());
                if (jVar.d() != null) {
                    fileOutputStream.write(jVar.d(), jVar.f(), jVar.b_());
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                if (file4.exists()) {
                    file4.delete();
                }
            } catch (IOException e) {
                throw new k(e);
            }
        } finally {
            if (file4.exists() && !file4.renameTo(file2)) {
                file2.delete();
                file4.renameTo(file2);
            }
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.g
    public com.abupdate.mqtt_libs.mqttv3.j a(String str) throws k {
        e();
        try {
            File file = this.b;
            FileInputStream fileInputStream = new FileInputStream(new File(file, str + ".msg"));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new m(str, bArr, 0, available, null, 0, 0);
        } catch (IOException e) {
            throw new k(e);
        }
    }
}
