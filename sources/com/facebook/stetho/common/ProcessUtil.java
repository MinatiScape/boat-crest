package com.facebook.stetho.common;

import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;
/* loaded from: classes9.dex */
public class ProcessUtil {
    private static final int CMDLINE_BUFFER_SIZE = 64;
    private static String sProcessName;
    private static boolean sProcessNameRead;

    @Nullable
    public static synchronized String getProcessName() {
        String str;
        synchronized (ProcessUtil.class) {
            if (!sProcessNameRead) {
                sProcessNameRead = true;
                try {
                    sProcessName = readProcessName();
                } catch (IOException unused) {
                }
            }
            str = sProcessName;
        }
        return str;
    }

    private static int indexOf(byte[] bArr, int i, int i2, byte b) {
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] == b) {
                return i3;
            }
        }
        return -1;
    }

    private static String readProcessName() throws IOException {
        byte[] bArr = new byte[64];
        FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
        boolean z = false;
        try {
            int read = fileInputStream.read(bArr);
            try {
                int indexOf = indexOf(bArr, 0, read, (byte) 0);
                if (indexOf > 0) {
                    read = indexOf;
                }
                String str = new String(bArr, 0, read);
                Util.close(fileInputStream, false);
                return str;
            } catch (Throwable th) {
                th = th;
                z = true;
                Util.close(fileInputStream, true ^ z);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
