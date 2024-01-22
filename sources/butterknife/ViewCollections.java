package butterknife;

import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import java.util.List;
/* loaded from: classes.dex */
public final class ViewCollections {
    private ViewCollections() {
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull List<T> list, @NonNull Action<? super T>... actionArr) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (Action<? super T> action : actionArr) {
                action.apply(list.get(i), i);
            }
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull List<T> list, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            setter.set(list.get(i), v, i);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T[] tArr, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            setter.set(tArr[i], v, i);
        }
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull T[] tArr, @NonNull Action<? super T>... actionArr) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            for (Action<? super T> action : actionArr) {
                action.apply(tArr[i], i);
            }
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T t, @NonNull Setter<? super T, V> setter, @Nullable V v) {
        setter.set(t, v, 0);
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull List<T> list, @NonNull Property<? super T, V> property, @Nullable V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            property.set(list.get(i), v);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull List<T> list, @NonNull Action<? super T> action) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            action.apply(list.get(i), i);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T[] tArr, @NonNull Property<? super T, V> property, @Nullable V v) {
        for (T t : tArr) {
            property.set(t, v);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull T[] tArr, @NonNull Action<? super T> action) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            action.apply(tArr[i], i);
        }
    }

    @UiThread
    public static <T extends View, V> void set(@NonNull T t, @NonNull Property<? super T, V> property, @Nullable V v) {
        property.set(t, v);
    }

    @SafeVarargs
    @UiThread
    public static <T extends View> void run(@NonNull T t, @NonNull Action<? super T>... actionArr) {
        for (Action<? super T> action : actionArr) {
            action.apply(t, 0);
        }
    }

    @UiThread
    public static <T extends View> void run(@NonNull T t, @NonNull Action<? super T> action) {
        action.apply(t, 0);
    }
}
