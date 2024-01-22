package kotlin.reflect;

import kotlin.Function;
import kotlin.SinceKotlin;
/* loaded from: classes12.dex */
public interface KFunction<R> extends KCallable<R>, Function<R> {

    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isExternal$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInfix$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInline$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOperator$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isSuspend$annotations() {
        }
    }

    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.KCallable
    boolean isSuspend();
}
