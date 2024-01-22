package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.navigation.NavigationConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class ExcludedSupportedSizesQuirk implements Quirk {
    public static boolean c() {
        return "OnePlus".equalsIgnoreCase(Build.BRAND) && "OnePlus6".equalsIgnoreCase(Build.DEVICE);
    }

    public static boolean d() {
        return "OnePlus".equalsIgnoreCase(Build.BRAND) && "OnePlus6T".equalsIgnoreCase(Build.DEVICE);
    }

    public static boolean e() {
        return c() || d();
    }

    @NonNull
    public final List<Size> a(@NonNull String str, int i) {
        ArrayList arrayList = new ArrayList();
        if (str.equals(BleConst.GetDeviceTime) && i == 256) {
            arrayList.add(new Size(4160, 3120));
            arrayList.add(new Size(NavigationConstants.UI_HANDLER_MAP_CONTROLS, 3000));
        }
        return arrayList;
    }

    @NonNull
    public final List<Size> b(@NonNull String str, int i) {
        ArrayList arrayList = new ArrayList();
        if (str.equals(BleConst.GetDeviceTime) && i == 256) {
            arrayList.add(new Size(4160, 3120));
            arrayList.add(new Size(NavigationConstants.UI_HANDLER_MAP_CONTROLS, 3000));
        }
        return arrayList;
    }

    @NonNull
    public List<Size> getExcludedSizes(@NonNull String str, int i) {
        if (c()) {
            return a(str, i);
        }
        if (d()) {
            return b(str, i);
        }
        Logger.w("ExcludedSupportedSizesQuirk", "Cannot retrieve list of supported sizes to exclude on this device.");
        return Collections.emptyList();
    }
}
