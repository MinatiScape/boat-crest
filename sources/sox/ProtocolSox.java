package sox;

import android.util.Log;
import java.io.File;
/* loaded from: classes13.dex */
public class ProtocolSox {

    /* renamed from: a  reason: collision with root package name */
    public static ProtocolSox f15708a;

    static {
        System.loadLibrary("mySox");
        f15708a = null;
    }

    public static native int ExcuateCommand(String str);

    public static synchronized ProtocolSox a() {
        ProtocolSox protocolSox;
        synchronized (ProtocolSox.class) {
            if (f15708a == null) {
                f15708a = new ProtocolSox();
            }
            protocolSox = f15708a;
        }
        return protocolSox;
    }

    public String a(String str) {
        File file = new File(str);
        String replace = file.getAbsolutePath().replace(file.getName(), "");
        String str2 = replace + "temp1.wav";
        a();
        Log.i("ProtocolSox", "to16HZ cmd1:  result = " + ExcuateCommand("sox -t raw -c 1 -e signed-integer -b 16 -r 24000  " + str + "  " + str2));
        String str3 = replace + "temp2.wav";
        a();
        Log.i("ProtocolSox", "to16HZ cmd2:  result = " + ExcuateCommand("sox   " + str2 + "   -r   16000  " + str3));
        String str4 = replace + "temp3.raw";
        a();
        Log.i("ProtocolSox", "to16HZ cmd3:  result = " + ExcuateCommand("sox " + str3 + " --bits 16 --encoding signed-integer --endian little " + str4));
        return str4;
    }
}
