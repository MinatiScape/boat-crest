package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;
/* loaded from: classes13.dex */
public class MqttDefaultFilePersistence implements MqttClientPersistence {
    public static FilenameFilter k;
    public File h;
    public File i;
    public FileLock j;

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    public static FilenameFilter b() {
        if (k == null) {
            k = new PersistanceFileNameFilter(".msg");
        }
        return k;
    }

    public final void a() throws MqttPersistenceException {
        if (this.i == null) {
            throw new MqttPersistenceException();
        }
    }

    public final File[] c() throws MqttPersistenceException {
        a();
        File[] listFiles = this.i.listFiles(b());
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() throws MqttPersistenceException {
        a();
        for (File file : c()) {
            file.delete();
        }
        this.i.delete();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence, java.lang.AutoCloseable
    public void close() throws MqttPersistenceException {
        synchronized (this) {
            FileLock fileLock = this.j;
            if (fileLock != null) {
                fileLock.release();
            }
            if (c().length == 0) {
                this.i.delete();
            }
            this.i = null;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) throws MqttPersistenceException {
        a();
        File file = this.i;
        return new File(file, String.valueOf(str) + ".msg").exists();
    }

    public final boolean d(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    public final void e(File file) throws MqttPersistenceException {
        File[] listFiles = file.listFiles(new PersistanceFileFilter(".bup"));
        if (listFiles != null) {
            for (File file2 : listFiles) {
                File file3 = new File(file, file2.getName().substring(0, file2.getName().length() - 4));
                if (!file2.renameTo(file3)) {
                    file3.delete();
                    file2.renameTo(file3);
                }
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) throws MqttPersistenceException {
        a();
        try {
            File file = this.i;
            FileInputStream fileInputStream = new FileInputStream(new File(file, String.valueOf(str) + ".msg"));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, available, null, 0, 0);
        } catch (IOException e) {
            throw new MqttPersistenceException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration<String> keys() throws MqttPersistenceException {
        String name;
        a();
        File[] c = c();
        Vector vector = new Vector(c.length);
        for (File file : c) {
            vector.addElement(file.getName().substring(0, name.length() - 4));
        }
        return vector.elements();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) throws MqttPersistenceException {
        if (this.h.exists() && !this.h.isDirectory()) {
            throw new MqttPersistenceException();
        }
        if (!this.h.exists() && !this.h.mkdirs()) {
            throw new MqttPersistenceException();
        }
        if (this.h.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (d(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append("-");
            for (int i2 = 0; i2 < str2.length(); i2++) {
                char charAt2 = str2.charAt(i2);
                if (d(charAt2)) {
                    stringBuffer.append(charAt2);
                }
            }
            synchronized (this) {
                if (this.i == null) {
                    File file = new File(this.h, stringBuffer.toString());
                    this.i = file;
                    if (!file.exists()) {
                        this.i.mkdir();
                    }
                }
                try {
                    FileLock fileLock = this.j;
                    if (fileLock != null) {
                        fileLock.release();
                    }
                    this.j = new FileLock(this.i, ".lck");
                } catch (Exception unused) {
                }
                e(this.i);
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        a();
        File file = this.i;
        File file2 = new File(file, String.valueOf(str) + ".msg");
        File file3 = this.i;
        File file4 = new File(file3, String.valueOf(str) + ".msg.bup");
        if (file2.exists() && !file2.renameTo(file4)) {
            file4.delete();
            file2.renameTo(file4);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
                if (mqttPersistable.getPayloadBytes() != null) {
                    fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                if (file4.exists()) {
                    file4.delete();
                }
            } catch (IOException e) {
                throw new MqttPersistenceException(e);
            }
        } finally {
            if (file4.exists() && !file4.renameTo(file2)) {
                file2.delete();
                file4.renameTo(file2);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) throws MqttPersistenceException {
        a();
        File file = this.i;
        File file2 = new File(file, String.valueOf(str) + ".msg");
        if (file2.exists()) {
            file2.delete();
        }
    }

    public MqttDefaultFilePersistence(String str) {
        this.i = null;
        this.j = null;
        this.h = new File(str);
    }
}
