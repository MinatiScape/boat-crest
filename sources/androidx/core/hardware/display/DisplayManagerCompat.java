package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.firebase.messaging.Constants;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public final class DisplayManagerCompat {
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
    public static final WeakHashMap<Context, DisplayManagerCompat> b = new WeakHashMap<>();

    /* renamed from: a  reason: collision with root package name */
    public final Context f1054a;

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static Display a(DisplayManager displayManager, int i) {
            return displayManager.getDisplay(i);
        }

        @DoNotInline
        public static Display[] b(DisplayManager displayManager) {
            return displayManager.getDisplays();
        }
    }

    public DisplayManagerCompat(Context context) {
        this.f1054a = context;
    }

    @NonNull
    public static DisplayManagerCompat getInstance(@NonNull Context context) {
        DisplayManagerCompat displayManagerCompat;
        WeakHashMap<Context, DisplayManagerCompat> weakHashMap = b;
        synchronized (weakHashMap) {
            displayManagerCompat = weakHashMap.get(context);
            if (displayManagerCompat == null) {
                displayManagerCompat = new DisplayManagerCompat(context);
                weakHashMap.put(context, displayManagerCompat);
            }
        }
        return displayManagerCompat;
    }

    @Nullable
    public Display getDisplay(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            return a.a((DisplayManager) this.f1054a.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION), i);
        }
        Display defaultDisplay = ((WindowManager) this.f1054a.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getDisplayId() == i) {
            return defaultDisplay;
        }
        return null;
    }

    @NonNull
    public Display[] getDisplays() {
        return Build.VERSION.SDK_INT >= 17 ? a.b((DisplayManager) this.f1054a.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION)) : new Display[]{((WindowManager) this.f1054a.getSystemService("window")).getDefaultDisplay()};
    }

    @NonNull
    public Display[] getDisplays(@Nullable String str) {
        if (Build.VERSION.SDK_INT >= 17) {
            return a.b((DisplayManager) this.f1054a.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION));
        }
        return str == null ? new Display[0] : new Display[]{((WindowManager) this.f1054a.getSystemService("window")).getDefaultDisplay()};
    }
}
