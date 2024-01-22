package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class CopyLock {
    public static final Map<String, Lock> e = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final File f1670a;
    public final Lock b;
    public final boolean c;
    public FileChannel d;

    public CopyLock(@NonNull String str, @NonNull File file, boolean z) {
        File file2 = new File(file, str + ".lck");
        this.f1670a = file2;
        this.b = a(file2.getAbsolutePath());
        this.c = z;
    }

    public static Lock a(String str) {
        Lock lock;
        Map<String, Lock> map = e;
        synchronized (map) {
            lock = map.get(str);
            if (lock == null) {
                lock = new ReentrantLock();
                map.put(str, lock);
            }
        }
        return lock;
    }

    public void lock() {
        this.b.lock();
        if (this.c) {
            try {
                FileChannel channel = new FileOutputStream(this.f1670a).getChannel();
                this.d = channel;
                channel.lock();
            } catch (IOException e2) {
                throw new IllegalStateException("Unable to grab copy lock.", e2);
            }
        }
    }

    public void unlock() {
        FileChannel fileChannel = this.d;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.b.unlock();
    }
}
