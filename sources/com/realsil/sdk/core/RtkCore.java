package com.realsil.sdk.core;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.realsil.sdk.bbpro.core.BuildConfig;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.connection.le.BluetoothGattManager;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Arrays;
/* loaded from: classes12.dex */
public final class RtkCore {
    public static boolean DEBUG = false;
    public static boolean VDBG = false;

    /* renamed from: a  reason: collision with root package name */
    public static Context f13543a;

    public static String getVersion() {
        return "1.2.8";
    }

    public static synchronized void initialize(Context context, @NonNull RtkConfigure rtkConfigure) {
        synchronized (RtkCore.class) {
            if (f13543a == null) {
                f13543a = context.getApplicationContext();
            }
            DEBUG = rtkConfigure.isDebugEnabled();
            ZLogger.initialize(rtkConfigure.getLogTag(), rtkConfigure.isPrintLog(), rtkConfigure.getGlobalLogLevel());
            ZLogger.d(rtkConfigure.toString());
            if (GlobalGatt.getInstance() == null) {
                GlobalGatt.initial(f13543a);
            }
            if (BluetoothGattManager.getInstance() == null) {
                BluetoothGattManager.initial(f13543a);
            }
            BluetoothProfileManager.initial(f13543a);
            ZLogger.v(String.format("%s:%s:%s", BuildConfig.GROUP_ID, "rtk-core", "1.2.8"));
            StringBuilder sb = new StringBuilder();
            sb.append("DeviceInfo{");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SDK_INT: ");
            int i = Build.VERSION.SDK_INT;
            sb2.append(i);
            sb.append(sb2.toString());
            sb.append("\nDevice name: " + Build.DEVICE);
            sb.append("\nAndroid Version: " + Build.VERSION.RELEASE);
            sb.append("\nManufacture: " + Build.MANUFACTURER);
            sb.append("\nModel: " + Build.MODEL);
            if (i >= 21) {
                sb.append("\nsupportedABIS: " + Arrays.toString(Build.SUPPORTED_ABIS));
            } else {
                sb.append("\ncupABI: " + Build.CPU_ABI);
            }
            sb.append("}");
            ZLogger.d(sb.toString());
        }
    }

    public static boolean isBluetoothSupported() {
        return BluetoothGattManager.getInstance().isBluetoothSupported();
    }
}
