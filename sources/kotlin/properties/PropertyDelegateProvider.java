package kotlin.properties;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.SinceKotlin;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
/* loaded from: classes12.dex */
public interface PropertyDelegateProvider<T, D> {
    D provideDelegate(T t, @NotNull KProperty<?> kProperty);
}
