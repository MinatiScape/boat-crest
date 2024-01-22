package com.goodix.ble.libcomx.logger;

import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.annotation.Nullable;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes5.dex */
public class RingLogger implements Runnable, ILogger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int EVT_UPDATE = 248;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static final String w = RingLogger.class.getSimpleName();
    public LogEntry[] i;
    public int j;
    public int k;
    public boolean m;
    public boolean n;
    public Executor q;
    public OutputStream u;
    public byte[] v;
    @Nullable
    public ILogger h = null;
    @Nullable
    public ArrayList<LogEntry> l = null;
    public int o = 200;
    public String p = w;
    @Nullable
    public Event<Void> r = null;
    public boolean s = false;
    public ConcurrentLinkedQueue<LogEntry> t = null;

    public RingLogger(int i) {
        this.i = new LogEntry[i < 1 ? 0 : i];
        a();
    }

    public final synchronized void a() {
        this.k = 0;
        this.j = 0;
    }

    public final synchronized void b() {
        if (!this.m) {
            this.m = true;
            if (this.q == null) {
                this.q = new ThreadPoolExecutor(0, 1, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
            }
            this.q.execute(this);
        }
    }

    public void clear() {
        synchronized (this) {
            this.n = true;
        }
        b();
    }

    public void clearSync() {
        a();
        Event<Void> event = this.r;
        if (event != null) {
            event.postEvent(null);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void d(String str, String str2) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 3, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.d(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 6, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.e(str, str2);
        }
    }

    public Event<Void> evtUpdate() {
        if (this.r == null) {
            synchronized (this) {
                if (this.r == null) {
                    this.r = new Event<>(this, EVT_UPDATE);
                }
            }
        }
        return this.r;
    }

    @Deprecated
    public LogEntry get(int i) {
        int i2 = this.k;
        LogEntry[] logEntryArr = this.i;
        if (i2 < logEntryArr.length) {
            return logEntryArr[i];
        }
        return logEntryArr[(this.j + i) % logEntryArr.length];
    }

    public int getCapability() {
        return this.i.length;
    }

    public List<LogEntry> getLogs(List<LogEntry> list) {
        int size;
        int i;
        LogEntry logEntry;
        synchronized (this) {
            if (list == null) {
                if (this.l == null) {
                    this.l = new ArrayList<>(this.i.length);
                }
                list = this.l;
            }
            size = list.size();
            i = this.k;
            int i2 = 0;
            while (true) {
                int i3 = this.k;
                if (i2 >= i3) {
                    break;
                }
                LogEntry[] logEntryArr = this.i;
                if (i3 < logEntryArr.length) {
                    logEntry = logEntryArr[i2];
                } else {
                    logEntry = logEntryArr[(this.j + i2) % logEntryArr.length];
                }
                if (i2 < size) {
                    list.get(i2).copy(logEntry);
                } else {
                    list.add(new LogEntry(logEntry));
                }
                i2++;
            }
        }
        while (i < size) {
            size--;
            list.remove(size);
        }
        return list;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void i(String str, String str2) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 4, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.i(str, str2);
        }
    }

    public RingLogger log(String str) {
        return log(System.currentTimeMillis(), Thread.currentThread().getId(), 3, this.p, str);
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = this.o;
        if (i > 0) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException unused) {
            }
        }
        boolean z = this.n;
        synchronized (this) {
            this.m = false;
            this.n = false;
        }
        if (z) {
            a();
        }
        OutputStream outputStream = this.s ? this.u : null;
        if (outputStream != null) {
            try {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                StringBuilder sb = new StringBuilder(1024);
                while (!this.t.isEmpty()) {
                    LogEntry poll = this.t.poll();
                    if (poll != null && poll.level != 100100) {
                        sb.delete(0, sb.length());
                        date.setTime(poll.timestamp);
                        sb.append(simpleDateFormat.format(date));
                        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                        sb.append(poll.tid);
                        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                        sb.append(poll.level);
                        sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                        sb.append(poll.tag);
                        sb.append(": ");
                        sb.append(poll.msg);
                        outputStream.write(sb.toString().getBytes());
                        outputStream.write(this.v);
                    }
                }
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
                this.s = false;
                log(100100, w, e.getMessage());
            }
        }
        Event<Void> event = this.r;
        if (event != null) {
            event.postEvent(null);
        }
    }

    public void saveTo(File file) throws IOException {
        if (file == null) {
            return;
        }
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.createNewFile();
        }
        if (file.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                saveTo(fileOutputStream);
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public RingLogger setAutoStoreToFile(OutputStream outputStream) {
        if (outputStream != null) {
            this.u = outputStream;
            this.t = new ConcurrentLinkedQueue<>();
            this.s = true;
            this.v = "\r\n".getBytes();
        } else {
            this.s = false;
        }
        return this;
    }

    public RingLogger setLogger(ILogger iLogger) {
        this.h = iLogger;
        return this;
    }

    public RingLogger setUpdateDelay(int i) {
        if (i < 1) {
            i = 1;
        }
        this.o = i;
        return this;
    }

    public int size() {
        return this.k;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void v(String str, String str2) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 2, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.v(str, str2);
        }
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void w(String str, String str2) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 5, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.w(str, str2);
        }
    }

    public RingLogger log(int i, String str, String str2) {
        return log(System.currentTimeMillis(), Thread.currentThread().getId(), i, str, str2);
    }

    public RingLogger log(long j, long j2, int i, String str, String str2) {
        LogEntry logEntry;
        int length = this.i.length;
        synchronized (this) {
            LogEntry[] logEntryArr = this.i;
            int i2 = this.j;
            logEntry = logEntryArr[i2];
            if (logEntry != null) {
                logEntry.timestamp = j;
                logEntry.tid = j2;
                logEntry.level = i;
                logEntry.tag = str;
                logEntry.msg = str2;
            } else {
                logEntry = new LogEntry(j, j2, i, str, str2);
                logEntryArr[i2] = logEntry;
            }
            int i3 = this.j + 1;
            this.j = i3;
            if (i3 >= length) {
                this.j = i3 - length;
            }
            int i4 = this.k;
            if (i4 < length) {
                this.k = i4 + 1;
            }
        }
        if (this.s) {
            this.t.add(new LogEntry(logEntry));
        }
        b();
        return this;
    }

    @Override // com.goodix.ble.libcomx.ILogger
    public void e(String str, String str2, Throwable th) {
        log(System.currentTimeMillis(), Thread.currentThread().getId(), 6, str, str2);
        ILogger iLogger = this.h;
        if (iLogger != null) {
            iLogger.e(str, str2, th);
        }
    }

    /* loaded from: classes5.dex */
    public static class LogEntry {
        public int level;
        public String msg;
        public String tag;
        public long tid;
        public long timestamp;

        public LogEntry(long j, long j2, int i, String str, String str2) {
            this.timestamp = j;
            this.tid = j2;
            this.level = i;
            this.tag = str;
            this.msg = str2;
        }

        public void copy(LogEntry logEntry) {
            this.timestamp = logEntry.timestamp;
            this.tid = logEntry.tid;
            this.level = logEntry.level;
            this.tag = logEntry.tag;
            this.msg = logEntry.msg;
        }

        public LogEntry(LogEntry logEntry) {
            copy(logEntry);
        }
    }

    public void saveTo(OutputStream outputStream) throws IOException {
        List<LogEntry> logs = getLogs(new ArrayList(getCapability()));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        StringBuilder sb = new StringBuilder(1024);
        if (this.v == null) {
            this.v = "\r\n".getBytes();
        }
        for (LogEntry logEntry : logs) {
            if (logEntry != null) {
                sb.delete(0, sb.length());
                date.setTime(logEntry.timestamp);
                sb.append(simpleDateFormat.format(date));
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(logEntry.tid);
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(logEntry.level);
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(logEntry.tag);
                sb.append(": ");
                sb.append(logEntry.msg);
                outputStream.write(sb.toString().getBytes());
                outputStream.write(this.v);
            }
        }
        outputStream.flush();
    }
}
