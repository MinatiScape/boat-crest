package com.ido.ble.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;
/* loaded from: classes11.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static double f12151a = 1024.0d;
    private static double b;
    private static double c;
    private static double d;

    static {
        double d2 = f12151a * 1024.0d;
        b = d2;
        double d3 = d2 * 1024.0d;
        c = d3;
        d = d3 * 1024.0d;
    }

    public static File a(File file, String... strArr) {
        Objects.requireNonNull(file, "directorydirectory must not be null");
        Objects.requireNonNull(strArr, "names must not be null");
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            i++;
            file = new File(file, strArr[i]);
        }
        return file;
    }

    public static File a(String... strArr) {
        Objects.requireNonNull(strArr, "names must not be null");
        File file = null;
        for (String str : strArr) {
            file = file == null ? new File(str) : new File(file, str);
        }
        return file;
    }

    public static FileOutputStream a(File file, boolean z) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file, z);
    }

    private static void a(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (file.isDirectory()) {
        } else {
            throw new IllegalArgumentException(file + " is not a directory");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r13, java.io.File r14) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L46
            r1.<init>(r13)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L46
            java.nio.channels.FileChannel r13 = r1.getChannel()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L46
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            r1.<init>(r14)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            r14 = 67076096(0x3ff8000, float:1.501694E-36)
            long r8 = r13.size()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            r1 = 0
            r10 = r1
        L1d:
            int r1 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r1 >= 0) goto L2b
            long r5 = (long) r14     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            r2 = r13
            r3 = r10
            r7 = r0
            long r1 = r2.transferTo(r3, r5, r7)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3e
            long r10 = r10 + r1
            goto L1d
        L2b:
            r13.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r13 = move-exception
            r13.printStackTrace()
        L33:
            if (r0 == 0) goto L5f
            r0.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L39:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L61
        L3e:
            r14 = move-exception
            r12 = r0
            r0 = r13
            r13 = r12
            goto L48
        L43:
            r14 = move-exception
            r13 = r0
            goto L61
        L46:
            r14 = move-exception
            r13 = r0
        L48:
            r14.printStackTrace()     // Catch: java.lang.Throwable -> L60
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r14 = move-exception
            r14.printStackTrace()
        L55:
            if (r13 == 0) goto L5f
            r13.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r13 = move-exception
            r13.printStackTrace()
        L5f:
            return
        L60:
            r14 = move-exception
        L61:
            if (r0 == 0) goto L6b
            r0.close()     // Catch: java.io.IOException -> L67
            goto L6b
        L67:
            r0 = move-exception
            r0.printStackTrace()
        L6b:
            if (r13 == 0) goto L75
            r13.close()     // Catch: java.io.IOException -> L71
            goto L75
        L71:
            r13 = move-exception
            r13.printStackTrace()
        L75:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.common.j.a(java.io.File, java.io.File):void");
    }

    public static void a(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Reader) {
                ((Reader) obj).close();
            } else if (obj instanceof Writer) {
                ((Writer) obj).close();
            } else if (obj instanceof InputStream) {
                ((InputStream) obj).close();
            } else if (obj instanceof OutputStream) {
                ((OutputStream) obj).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean a(String str, Object obj) {
        boolean z;
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (file.exists()) {
            z = true;
        } else {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                z = false;
            }
        }
        if (z) {
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e2) {
                e = e2;
            }
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
                a(fileOutputStream);
            } catch (IOException e3) {
                e = e3;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                a(fileOutputStream2);
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                a(fileOutputStream2);
                throw th;
            }
        }
        return z;
    }

    public static boolean a(String str, String str2) {
        boolean z;
        File file = new File(str);
        if (file.exists()) {
            z = true;
        } else {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                z = false;
            }
        }
        if (z) {
            try {
                FileWriter fileWriter = new FileWriter(str);
                fileWriter.write(str2);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return z;
    }

    public static void b(File file) {
        if (!file.exists()) {
            file.toString();
        } else if (!file.isDirectory()) {
            file.toString();
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("Failed to list contents of " + file);
            }
            IOException e = null;
            for (File file2 : listFiles) {
                try {
                    e(file2);
                } catch (IOException e2) {
                    e = e2;
                }
            }
            if (e != null) {
                throw e;
            }
        }
    }

    public static boolean b(String str) {
        return new File(str).exists();
    }

    public static Object c(String str) {
        Exception exc;
        Object obj;
        FileInputStream fileInputStream;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            exc = e;
            obj = null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fileInputStream2 = objectInputStream.readObject();
            objectInputStream.close();
            a(fileInputStream);
            return fileInputStream2;
        } catch (Exception e2) {
            obj = fileInputStream2;
            fileInputStream2 = fileInputStream;
            exc = e2;
            exc.printStackTrace();
            a(fileInputStream2);
            fileInputStream2 = obj;
            return fileInputStream2;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            a(fileInputStream2);
            throw th;
        }
    }

    public static void c(File file) {
        if (file.exists()) {
            b(file);
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    public static String d(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean d(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                b(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static void e(File file) {
        if (file.isDirectory()) {
            c(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (exists) {
            throw new IOException("Unable to delete file: " + file);
        }
        throw new FileNotFoundException("File does not exist: " + file);
    }

    public static void f(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " exists and is not a directory. Unable to create directory.");
        } else if (file.mkdirs() || file.isDirectory()) {
        } else {
            throw new IOException("Unable to create directory " + file);
        }
    }

    public static FileInputStream g(File file) {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    public static FileOutputStream h(File file) {
        return a(file, false);
    }

    public static long i(File file) {
        if (file.exists()) {
            return file.isDirectory() ? j(file) : file.length();
        }
        throw new IllegalArgumentException(file + " does not exist");
    }

    public static long j(File file) {
        a(file);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        long j = 0;
        for (File file2 : listFiles) {
            j += i(file2);
            if (j < 0) {
                break;
            }
        }
        return j;
    }
}
