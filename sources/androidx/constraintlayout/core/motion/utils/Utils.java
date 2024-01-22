package androidx.constraintlayout.core.motion.utils;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
/* loaded from: classes.dex */
public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static DebugHandle f889a;

    /* loaded from: classes.dex */
    public interface DebugHandle {
        void message(String str);
    }

    public static int a(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static void log(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + " : " + str2);
    }

    public static void logStack(String str, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int min = Math.min(i, stackTrace.length - 1);
        String str2 = HexStringBuilder.DEFAULT_SEPARATOR;
        for (int i2 = 1; i2 <= min; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            str2 = str2 + HexStringBuilder.DEFAULT_SEPARATOR;
            PrintStream printStream = System.out;
            printStream.println(str + str2 + (".(" + stackTrace[i2].getFileName() + ":" + stackTrace[i2].getLineNumber() + ") " + stackTrace[i2].getMethodName()) + str2);
        }
    }

    public static void loge(String str, String str2) {
        PrintStream printStream = System.err;
        printStream.println(str + " : " + str2);
    }

    public static int rgbaTocColor(float f, float f2, float f3, float f4) {
        int a2 = a((int) (f * 255.0f));
        int a3 = a((int) (f2 * 255.0f));
        return (a2 << 16) | (a((int) (f4 * 255.0f)) << 24) | (a3 << 8) | a((int) (f3 * 255.0f));
    }

    public static void setDebugHandle(DebugHandle debugHandle) {
        f889a = debugHandle;
    }

    public static void socketSend(String str) {
        try {
            OutputStream outputStream = new Socket("127.0.0.1", 5327).getOutputStream();
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInterpolatedColor(float[] fArr) {
        int a2 = a((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f));
        int a3 = a((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f));
        return (a((int) (fArr[3] * 255.0f)) << 24) | (a2 << 16) | (a3 << 8) | a((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
    }

    public static void log(String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String substring = (stackTraceElement.getMethodName() + "                  ").substring(0, 17);
        String str2 = ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")" + "    ".substring(Integer.toString(stackTraceElement.getLineNumber()).length()) + substring;
        System.out.println(str2 + HexStringBuilder.DEFAULT_SEPARATOR + str);
        DebugHandle debugHandle = f889a;
        if (debugHandle != null) {
            debugHandle.message(str2 + HexStringBuilder.DEFAULT_SEPARATOR + str);
        }
    }
}
