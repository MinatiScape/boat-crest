package com.elvishew.xlog.printer.file;

import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.flattener.Flattener2;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.printer.file.backup.BackupStrategyWrapper;
import com.elvishew.xlog.internal.printer.file.backup.BackupUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.backup.BackupStrategy;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import com.elvishew.xlog.printer.file.clean.CleanStrategy;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes9.dex */
public class FilePrinter implements Printer {

    /* renamed from: a  reason: collision with root package name */
    public final String f7879a;
    public final FileNameGenerator b;
    public final BackupStrategy2 c;
    public final CleanStrategy d;
    public Flattener2 e;
    public d f = new d();
    public volatile c g = new c();

    /* loaded from: classes9.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f7880a;
        public FileNameGenerator b;
        public BackupStrategy2 c;
        public CleanStrategy d;
        public Flattener2 e;

        /* loaded from: classes9.dex */
        public class a implements Flattener2 {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Flattener f7881a;

            public a(Builder builder, Flattener flattener) {
                this.f7881a = flattener;
            }

            @Override // com.elvishew.xlog.flattener.Flattener2
            public CharSequence flatten(long j, int i, String str, String str2) {
                return this.f7881a.flatten(i, str, str2);
            }
        }

        public Builder(String str) {
            this.f7880a = str;
        }

        public final void a() {
            if (this.b == null) {
                this.b = DefaultsFactory.createFileNameGenerator();
            }
            if (this.c == null) {
                this.c = DefaultsFactory.createBackupStrategy();
            }
            if (this.d == null) {
                this.d = DefaultsFactory.createCleanStrategy();
            }
            if (this.e == null) {
                this.e = DefaultsFactory.createFlattener2();
            }
        }

        public Builder backupStrategy(BackupStrategy backupStrategy) {
            if (!(backupStrategy instanceof BackupStrategy2)) {
                backupStrategy = new BackupStrategyWrapper(backupStrategy);
            }
            BackupStrategy2 backupStrategy2 = (BackupStrategy2) backupStrategy;
            this.c = backupStrategy2;
            BackupUtil.verifyBackupStrategy(backupStrategy2);
            return this;
        }

        public FilePrinter build() {
            a();
            return new FilePrinter(this);
        }

        public Builder cleanStrategy(CleanStrategy cleanStrategy) {
            this.d = cleanStrategy;
            return this;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.b = fileNameGenerator;
            return this;
        }

        public Builder flattener(Flattener2 flattener2) {
            this.e = flattener2;
            return this;
        }

        @Deprecated
        public Builder logFlattener(Flattener flattener) {
            return flattener(new a(this, flattener));
        }
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public long f7882a;
        public int b;
        public String c;
        public String d;

        public b(long j, int i, String str, String str2) {
            this.f7882a = j;
            this.b = i;
            this.c = str;
            this.d = str2;
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public BlockingQueue<b> h;
        public volatile boolean i;

        public c() {
            this.h = new LinkedBlockingQueue();
        }

        public void a(b bVar) {
            try {
                this.h.put(bVar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void b() {
            synchronized (this) {
                if (this.i) {
                    return;
                }
                new Thread(this).start();
                this.i = true;
            }
        }

        public boolean isStarted() {
            boolean z;
            synchronized (this) {
                z = this.i;
            }
            return z;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    b take = this.h.take();
                    if (take == null) {
                        return;
                    }
                    FilePrinter.this.e(take.f7882a, take.b, take.c, take.d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    synchronized (this) {
                        this.i = false;
                        return;
                    }
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d {

        /* renamed from: a  reason: collision with root package name */
        public String f7883a;
        public File b;
        public BufferedWriter c;

        public d() {
        }

        public void a(String str) {
            try {
                this.c.write(str);
                this.c.newLine();
                this.c.flush();
            } catch (IOException unused) {
            }
        }

        public boolean b() {
            BufferedWriter bufferedWriter = this.c;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.c = null;
            this.f7883a = null;
            this.b = null;
            return true;
        }

        public File c() {
            return this.b;
        }

        public String d() {
            return this.f7883a;
        }

        public boolean e() {
            return this.c != null && this.b.exists();
        }

        public boolean f(String str) {
            this.f7883a = str;
            File file = new File(FilePrinter.this.f7879a, str);
            this.b = file;
            if (!file.exists()) {
                try {
                    File parentFile = this.b.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    this.b.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    b();
                    return false;
                }
            }
            try {
                this.c = new BufferedWriter(new FileWriter(this.b, true));
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                b();
                return false;
            }
        }
    }

    public FilePrinter(Builder builder) {
        this.f7879a = builder.f7880a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        c();
    }

    public final void c() {
        File file = new File(this.f7879a);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public final void d() {
        File[] listFiles = new File(this.f7879a).listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if (this.d.shouldClean(file)) {
                file.delete();
            }
        }
    }

    public final void e(long j, int i, String str, String str2) {
        String d2 = this.f.d();
        boolean z = !this.f.e();
        if (d2 == null || z || this.b.isFileNameChangeable()) {
            String generateFileName = this.b.generateFileName(i, System.currentTimeMillis());
            if (generateFileName != null && generateFileName.trim().length() != 0) {
                if (!generateFileName.equals(d2) || z) {
                    this.f.b();
                    d();
                    if (!this.f.f(generateFileName)) {
                        return;
                    }
                    d2 = generateFileName;
                }
            } else {
                Platform.get().error("File name should not be empty, ignore log: " + str2);
                return;
            }
        }
        File c2 = this.f.c();
        if (this.c.shouldBackup(c2)) {
            this.f.b();
            BackupUtil.backup(c2, this.c);
            if (!this.f.f(d2)) {
                return;
            }
        }
        this.f.a(this.e.flatten(j, i, str, str2).toString());
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.g.isStarted()) {
            this.g.b();
        }
        this.g.a(new b(currentTimeMillis, i, str, str2));
    }
}
