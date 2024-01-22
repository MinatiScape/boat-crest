package kotlin;

import com.goodix.ble.gr.libdfu.BuildConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.experimental.ExperimentalTypeInference;
@Target({ElementType.METHOD})
@SinceKotlin(version = BuildConfig.VERSION_NAME)
@ExperimentalTypeInference
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION})
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
/* loaded from: classes12.dex */
public @interface OverloadResolutionByLambdaReturnType {
}
