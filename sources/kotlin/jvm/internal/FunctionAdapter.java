package kotlin.jvm.internal;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.Function;
import kotlin.SinceKotlin;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
/* loaded from: classes12.dex */
public interface FunctionAdapter {
    Function<?> getFunctionDelegate();
}
