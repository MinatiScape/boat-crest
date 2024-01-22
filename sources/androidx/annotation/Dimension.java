package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import org.jetbrains.annotations.NotNull;
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@MustBeDocumented
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.ANNOTATION_CLASS})
@Documented
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
/* loaded from: classes.dex */
public @interface Dimension {
    @NotNull
    public static final Companion Companion = Companion.f367a;
    public static final int DP = 0;
    public static final int PX = 1;
    public static final int SP = 2;

    /* loaded from: classes.dex */
    public static final class Companion {
        public static final int DP = 0;
        public static final int PX = 1;
        public static final int SP = 2;

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f367a = new Companion();
    }

    int unit() default 1;
}
