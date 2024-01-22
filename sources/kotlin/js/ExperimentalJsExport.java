package kotlin.js;

import com.goodix.ble.gr.libdfu.BuildConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.RequiresOptIn;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.MustBeDocumented;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
@MustBeDocumented
@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
@Documented
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
/* loaded from: classes12.dex */
public @interface ExperimentalJsExport {
}
