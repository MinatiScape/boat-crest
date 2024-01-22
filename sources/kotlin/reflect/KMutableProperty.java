package kotlin.reflect;

import kotlin.Unit;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface KMutableProperty<V> extends KProperty<V> {

    /* loaded from: classes12.dex */
    public interface Setter<V> extends KProperty.Accessor<V>, KFunction<Unit> {
    }

    @NotNull
    Setter<V> getSetter();
}
