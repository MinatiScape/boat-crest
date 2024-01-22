package kotlin.jvm.internal;

import com.goodix.ble.gr.libdfu.BuildConfig;
import java.lang.reflect.Type;
import kotlin.SinceKotlin;
import kotlin.reflect.KType;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
/* loaded from: classes12.dex */
public interface KTypeBase extends KType {
    @Nullable
    Type getJavaType();
}
