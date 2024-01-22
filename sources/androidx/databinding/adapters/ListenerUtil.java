package androidx.databinding.adapters;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class ListenerUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final SparseArray<WeakHashMap<View, WeakReference<?>>> f1224a = new SparseArray<>();

    public static <T> T getListener(View view, int i) {
        if (Build.VERSION.SDK_INT >= 14) {
            return (T) view.getTag(i);
        }
        SparseArray<WeakHashMap<View, WeakReference<?>>> sparseArray = f1224a;
        synchronized (sparseArray) {
            WeakHashMap<View, WeakReference<?>> weakHashMap = sparseArray.get(i);
            if (weakHashMap == null) {
                return null;
            }
            WeakReference<?> weakReference = weakHashMap.get(view);
            if (weakReference == null) {
                return null;
            }
            return (T) weakReference.get();
        }
    }

    public static <T> T trackListener(View view, T t, int i) {
        WeakReference<?> put;
        if (Build.VERSION.SDK_INT >= 14) {
            T t2 = (T) view.getTag(i);
            view.setTag(i, t);
            return t2;
        }
        SparseArray<WeakHashMap<View, WeakReference<?>>> sparseArray = f1224a;
        synchronized (sparseArray) {
            WeakHashMap<View, WeakReference<?>> weakHashMap = sparseArray.get(i);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap<>();
                sparseArray.put(i, weakHashMap);
            }
            if (t == null) {
                put = weakHashMap.remove(view);
            } else {
                put = weakHashMap.put(view, new WeakReference<>(t));
            }
            if (put == null) {
                return null;
            }
            return (T) put.get();
        }
    }
}
