package kotlin.reflect;

import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface KProperty<V> extends KCallable<V> {

    /* loaded from: classes12.dex */
    public interface Accessor<V> {
        @NotNull
        KProperty<V> getProperty();
    }

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isConst$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isLateinit$annotations() {
        }
    }

    /* loaded from: classes12.dex */
    public interface Getter<V> extends Accessor<V>, KFunction<V> {
    }

    @NotNull
    Getter<V> getGetter();

    boolean isConst();

    boolean isLateinit();
}
