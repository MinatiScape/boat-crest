package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.jvm.functions.Function1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@IgnoreJRERequirement
/* loaded from: classes12.dex */
public final class ClassValueCtorCache extends CtorCache {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final ClassValueCtorCache f14169a = new ClassValueCtorCache();
    @NotNull
    public static final ClassValueCtorCache$cache$1 b = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        public /* bridge */ /* synthetic */ Object computeValue(Class cls) {
            return m764computeValue((Class<?>) cls);
        }

        @NotNull
        /* renamed from: computeValue  reason: collision with other method in class */
        public Function1<Throwable, Throwable> m764computeValue(@Nullable Class<?> cls) {
            Objects.requireNonNull(cls, "null cannot be cast to non-null type java.lang.Class<out kotlin.Throwable>");
            return ExceptionsConstructorKt.access$createConstructor(cls);
        }
    };

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        return (Function1) b.get(cls);
    }
}
