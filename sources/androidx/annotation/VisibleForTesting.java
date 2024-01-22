package androidx.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import org.jetbrains.annotations.NotNull;
@Retention(RetentionPolicy.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
/* loaded from: classes.dex */
public @interface VisibleForTesting {
    @NotNull
    public static final Companion Companion = Companion.f368a;
    public static final int NONE = 5;
    public static final int PACKAGE_PRIVATE = 3;
    public static final int PRIVATE = 2;
    public static final int PROTECTED = 4;

    /* loaded from: classes.dex */
    public static final class Companion {
        public static final int NONE = 5;
        public static final int PACKAGE_PRIVATE = 3;
        public static final int PRIVATE = 2;
        public static final int PROTECTED = 4;

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f368a = new Companion();
    }

    int otherwise() default 2;
}
