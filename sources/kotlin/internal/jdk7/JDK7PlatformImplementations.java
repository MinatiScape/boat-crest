package kotlin.internal.jdk7;

import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {

    /* loaded from: classes12.dex */
    public static final class a {
        @JvmField
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public static final Integer f14064a;

        /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
        static {
            /*
                kotlin.internal.jdk7.JDK7PlatformImplementations$a r0 = new kotlin.internal.jdk7.JDK7PlatformImplementations$a
                r0.<init>()
                r0 = 0
                java.lang.String r1 = "android.os.Build$VERSION"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L1d
                java.lang.String r2 = "SDK_INT"
                java.lang.reflect.Field r1 = r1.getField(r2)     // Catch: java.lang.Throwable -> L1d
                java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L1d
                boolean r2 = r1 instanceof java.lang.Integer     // Catch: java.lang.Throwable -> L1d
                if (r2 == 0) goto L1d
                java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L1d
                goto L1e
            L1d:
                r1 = r0
            L1e:
                if (r1 == 0) goto L2c
                int r2 = r1.intValue()
                if (r2 <= 0) goto L28
                r2 = 1
                goto L29
            L28:
                r2 = 0
            L29:
                if (r2 == 0) goto L2c
                r0 = r1
            L2c:
                kotlin.internal.jdk7.JDK7PlatformImplementations.a.f14064a = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.jdk7.JDK7PlatformImplementations.a.<clinit>():void");
        }
    }

    public final boolean a(int i) {
        Integer num = a.f14064a;
        return num == null || num.intValue() >= i;
    }

    @Override // kotlin.internal.PlatformImplementations
    public void addSuppressed(@NotNull Throwable cause, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (a(19)) {
            cause.addSuppressed(exception);
        } else {
            super.addSuppressed(cause, exception);
        }
    }

    @Override // kotlin.internal.PlatformImplementations
    @NotNull
    public List<Throwable> getSuppressed(@NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (a(19)) {
            Throwable[] suppressed = exception.getSuppressed();
            Intrinsics.checkNotNullExpressionValue(suppressed, "exception.suppressed");
            return ArraysKt___ArraysJvmKt.asList(suppressed);
        }
        return super.getSuppressed(exception);
    }
}
