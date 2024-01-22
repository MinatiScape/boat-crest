package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@RequiresApi(26)
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static Method f757a;

    static {
        try {
            Class cls = Integer.TYPE;
            f757a = ImageWriter.class.getMethod("newInstance", Surface.class, cls, cls);
        } catch (NoSuchMethodException e) {
            Log.i("ImageWriterCompatApi26", "Unable to initialize via reflection.", e);
        }
    }

    @NonNull
    public static ImageWriter a(@NonNull Surface surface, @IntRange(from = 1) int i, int i2) {
        Throwable th = null;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                return (ImageWriter) Preconditions.checkNotNull(f757a.invoke(null, surface, Integer.valueOf(i), Integer.valueOf(i2)));
            } catch (IllegalAccessException | InvocationTargetException e) {
                th = e;
            }
        }
        throw new RuntimeException("Unable to invoke newInstance(Surface, int, int) via reflection.", th);
    }
}
