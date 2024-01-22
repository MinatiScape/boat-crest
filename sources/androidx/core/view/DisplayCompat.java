package androidx.core.view;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public final class DisplayCompat {

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class a {
        public static void a(Display display, Point point) {
            display.getRealSize(point);
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class b {
        @NonNull
        public static ModeCompat a(@NonNull Context context, @NonNull Display display) {
            Display.Mode mode = display.getMode();
            Point a2 = DisplayCompat.a(context, display);
            if (a2 != null && !d(mode, a2)) {
                return new ModeCompat(mode, a2);
            }
            return new ModeCompat(mode, true);
        }

        @NonNull
        @SuppressLint({"ArrayReturn"})
        public static ModeCompat[] b(@NonNull Context context, @NonNull Display display) {
            ModeCompat modeCompat;
            Display.Mode[] supportedModes = display.getSupportedModes();
            ModeCompat[] modeCompatArr = new ModeCompat[supportedModes.length];
            Display.Mode mode = display.getMode();
            Point a2 = DisplayCompat.a(context, display);
            if (a2 != null && !d(mode, a2)) {
                for (int i = 0; i < supportedModes.length; i++) {
                    if (e(supportedModes[i], mode)) {
                        modeCompat = new ModeCompat(supportedModes[i], a2);
                    } else {
                        modeCompat = new ModeCompat(supportedModes[i], false);
                    }
                    modeCompatArr[i] = modeCompat;
                }
            } else {
                for (int i2 = 0; i2 < supportedModes.length; i2++) {
                    modeCompatArr[i2] = new ModeCompat(supportedModes[i2], e(supportedModes[i2], mode));
                }
            }
            return modeCompatArr;
        }

        public static boolean c(@NonNull Display display) {
            Display.Mode[] supportedModes;
            Display.Mode mode = display.getMode();
            for (Display.Mode mode2 : display.getSupportedModes()) {
                if (mode.getPhysicalHeight() < mode2.getPhysicalHeight() || mode.getPhysicalWidth() < mode2.getPhysicalWidth()) {
                    return false;
                }
            }
            return true;
        }

        public static boolean d(Display.Mode mode, Point point) {
            return (mode.getPhysicalWidth() == point.x && mode.getPhysicalHeight() == point.y) || (mode.getPhysicalWidth() == point.y && mode.getPhysicalHeight() == point.x);
        }

        public static boolean e(Display.Mode mode, Display.Mode mode2) {
            return mode.getPhysicalWidth() == mode2.getPhysicalWidth() && mode.getPhysicalHeight() == mode2.getPhysicalHeight();
        }
    }

    public static Point a(@NonNull Context context, @NonNull Display display) {
        Point h;
        if (Build.VERSION.SDK_INT < 28) {
            h = h("sys.display-size", display);
        } else {
            h = h("vendor.display-size", display);
        }
        if (h != null) {
            return h;
        }
        if (e(context) && d(display)) {
            return new Point(3840, 2160);
        }
        return null;
    }

    @NonNull
    public static Point b(@NonNull Context context, @NonNull Display display) {
        Point a2 = a(context, display);
        if (a2 != null) {
            return a2;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            a.a(display, point);
        } else {
            display.getSize(point);
        }
        return point;
    }

    @Nullable
    public static String c(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean d(@NonNull Display display) {
        if (Build.VERSION.SDK_INT >= 23) {
            return b.c(display);
        }
        return true;
    }

    public static boolean e(@NonNull Context context) {
        return f(context) && "Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd");
    }

    public static boolean f(@NonNull Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getCurrentModeType() == 4;
    }

    public static Point g(@NonNull String str) throws NumberFormatException {
        String[] split = str.trim().split("x", -1);
        if (split.length == 2) {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            if (parseInt > 0 && parseInt2 > 0) {
                return new Point(parseInt, parseInt2);
            }
        }
        throw new NumberFormatException();
    }

    @NonNull
    public static ModeCompat getMode(@NonNull Context context, @NonNull Display display) {
        if (Build.VERSION.SDK_INT >= 23) {
            return b.a(context, display);
        }
        return new ModeCompat(b(context, display));
    }

    @NonNull
    @SuppressLint({"ArrayReturn"})
    public static ModeCompat[] getSupportedModes(@NonNull Context context, @NonNull Display display) {
        return Build.VERSION.SDK_INT >= 23 ? b.b(context, display) : new ModeCompat[]{getMode(context, display)};
    }

    @Nullable
    public static Point h(@NonNull String str, @NonNull Display display) {
        if (display.getDisplayId() != 0) {
            return null;
        }
        String c = c(str);
        if (!TextUtils.isEmpty(c) && c != null) {
            try {
                return g(c);
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static final class ModeCompat {

        /* renamed from: a  reason: collision with root package name */
        public final Display.Mode f1131a;
        public final Point b;
        public final boolean c;

        @RequiresApi(23)
        /* loaded from: classes.dex */
        public static class a {
            @DoNotInline
            public static int a(Display.Mode mode) {
                return mode.getPhysicalHeight();
            }

            @DoNotInline
            public static int b(Display.Mode mode) {
                return mode.getPhysicalWidth();
            }
        }

        public ModeCompat(@NonNull Point point) {
            Preconditions.checkNotNull(point, "physicalSize == null");
            this.b = point;
            this.f1131a = null;
            this.c = true;
        }

        public int getPhysicalHeight() {
            return this.b.y;
        }

        public int getPhysicalWidth() {
            return this.b.x;
        }

        @Deprecated
        public boolean isNative() {
            return this.c;
        }

        @Nullable
        @RequiresApi(23)
        public Display.Mode toMode() {
            return this.f1131a;
        }

        @RequiresApi(23)
        public ModeCompat(@NonNull Display.Mode mode, boolean z) {
            Preconditions.checkNotNull(mode, "mode == null, can't wrap a null reference");
            this.b = new Point(a.b(mode), a.a(mode));
            this.f1131a = mode;
            this.c = z;
        }

        @RequiresApi(23)
        public ModeCompat(@NonNull Display.Mode mode, @NonNull Point point) {
            Preconditions.checkNotNull(mode, "mode == null, can't wrap a null reference");
            Preconditions.checkNotNull(point, "physicalSize == null");
            this.b = point;
            this.f1131a = mode;
            this.c = true;
        }
    }
}
