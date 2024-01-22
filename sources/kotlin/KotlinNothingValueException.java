package kotlin;

import com.goodix.ble.gr.libdfu.BuildConfig;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
@PublishedApi
/* loaded from: classes12.dex */
public final class KotlinNothingValueException extends RuntimeException {
    public KotlinNothingValueException() {
    }

    public KotlinNothingValueException(@Nullable String str) {
        super(str);
    }

    public KotlinNothingValueException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    public KotlinNothingValueException(@Nullable Throwable th) {
        super(th);
    }
}
