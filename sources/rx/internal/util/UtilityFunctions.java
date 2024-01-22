package rx.internal.util;

import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class UtilityFunctions {

    /* loaded from: classes13.dex */
    public enum a implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object obj) {
            return Boolean.FALSE;
        }
    }

    /* loaded from: classes13.dex */
    public enum b implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object obj) {
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes13.dex */
    public enum c implements Func1<Object, Object> {
        INSTANCE;

        @Override // rx.functions.Func1
        public Object call(Object obj) {
            return obj;
        }
    }

    public UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return a.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return b.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return c.INSTANCE;
    }
}
