package kotlin.properties;

import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface ReadOnlyProperty<T, V> {
    V getValue(T t, @NotNull KProperty<?> kProperty);
}
