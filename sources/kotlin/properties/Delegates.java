package kotlin.properties;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class Delegates {
    @NotNull
    public static final Delegates INSTANCE = new Delegates();

    @NotNull
    public final <T> ReadWriteProperty<Object, T> notNull() {
        return new a();
    }

    @NotNull
    public final <T> ReadWriteProperty<Object, T> observable(final T t, @NotNull final Function3<? super KProperty<?>, ? super T, ? super T, Unit> onChange) {
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        return new ObservableProperty<T>(t) { // from class: kotlin.properties.Delegates$observable$1
            @Override // kotlin.properties.ObservableProperty
            public void afterChange(@NotNull KProperty<?> property, T t2, T t3) {
                Intrinsics.checkNotNullParameter(property, "property");
                onChange.invoke(property, t2, t3);
            }
        };
    }

    @NotNull
    public final <T> ReadWriteProperty<Object, T> vetoable(final T t, @NotNull final Function3<? super KProperty<?>, ? super T, ? super T, Boolean> onChange) {
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        return new ObservableProperty<T>(t) { // from class: kotlin.properties.Delegates$vetoable$1
            @Override // kotlin.properties.ObservableProperty
            public boolean beforeChange(@NotNull KProperty<?> property, T t2, T t3) {
                Intrinsics.checkNotNullParameter(property, "property");
                return onChange.invoke(property, t2, t3).booleanValue();
            }
        };
    }
}
