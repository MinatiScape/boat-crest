package rx.internal.util;
/* loaded from: classes13.dex */
public final class PlatformDependent {
    public static final int ANDROID_API_VERSION_IS_NOT_ANDROID = 0;

    /* renamed from: a  reason: collision with root package name */
    public static final int f15686a;
    public static final boolean b;

    static {
        int a2 = a();
        f15686a = a2;
        b = a2 != 0;
    }

    public PlatformDependent() {
        throw new IllegalStateException("No instances!");
    }

    public static int a() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int getAndroidApiVersion() {
        return f15686a;
    }

    public static boolean isAndroid() {
        return b;
    }
}
