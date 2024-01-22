package rx.functions;

import rx.exceptions.OnErrorNotImplementedException;
/* loaded from: classes13.dex */
public final class Actions {

    /* renamed from: a  reason: collision with root package name */
    public static final m f15654a = new m();

    /* JADX INFO: Add missing generic type declarations: [T4, T5, T6, R, T7, T8, T9, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class a<R, T1, T2, T3, T4, T5, T6, T7, T8, T9> implements Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {
        public final /* synthetic */ Action9 h;
        public final /* synthetic */ Object i;

        public a(Action9 action9, Object obj) {
            this.h = action9;
            this.i = obj;
        }

        @Override // rx.functions.Func9
        public R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
            this.h.call(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class b<R> implements FuncN<R> {
        public final /* synthetic */ ActionN h;
        public final /* synthetic */ Object i;

        public b(ActionN actionN, Object obj) {
            this.h = actionN;
            this.i = obj;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            this.h.call(objArr);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class c<R> implements Func0<R> {
        public final /* synthetic */ Action0 h;
        public final /* synthetic */ Object i;

        public c(Action0 action0, Object obj) {
            this.h = action0;
            this.i = obj;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public R call() {
            this.h.call();
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R, T1] */
    /* loaded from: classes13.dex */
    public static class d<R, T1> implements Func1<T1, R> {
        public final /* synthetic */ Action1 h;
        public final /* synthetic */ Object i;

        public d(Action1 action1, Object obj) {
            this.h = action1;
            this.i = obj;
        }

        @Override // rx.functions.Func1
        public R call(T1 t1) {
            this.h.call(t1);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R, T1, T2] */
    /* loaded from: classes13.dex */
    public static class e<R, T1, T2> implements Func2<T1, T2, R> {
        public final /* synthetic */ Action2 h;
        public final /* synthetic */ Object i;

        public e(Action2 action2, Object obj) {
            this.h = action2;
            this.i = obj;
        }

        @Override // rx.functions.Func2
        public R call(T1 t1, T2 t2) {
            this.h.call(t1, t2);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class f<R, T1, T2, T3> implements Func3<T1, T2, T3, R> {
        public final /* synthetic */ Action3 h;
        public final /* synthetic */ Object i;

        public f(Action3 action3, Object obj) {
            this.h = action3;
            this.i = obj;
        }

        @Override // rx.functions.Func3
        public R call(T1 t1, T2 t2, T3 t3) {
            this.h.call(t1, t2, t3);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T4, R, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class g<R, T1, T2, T3, T4> implements Func4<T1, T2, T3, T4, R> {
        public final /* synthetic */ Action4 h;
        public final /* synthetic */ Object i;

        public g(Action4 action4, Object obj) {
            this.h = action4;
            this.i = obj;
        }

        @Override // rx.functions.Func4
        public R call(T1 t1, T2 t2, T3 t3, T4 t4) {
            this.h.call(t1, t2, t3, t4);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T4, T5, R, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class h<R, T1, T2, T3, T4, T5> implements Func5<T1, T2, T3, T4, T5, R> {
        public final /* synthetic */ Action5 h;
        public final /* synthetic */ Object i;

        public h(Action5 action5, Object obj) {
            this.h = action5;
            this.i = obj;
        }

        @Override // rx.functions.Func5
        public R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
            this.h.call(t1, t2, t3, t4, t5);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T4, T5, T6, R, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class i<R, T1, T2, T3, T4, T5, T6> implements Func6<T1, T2, T3, T4, T5, T6, R> {
        public final /* synthetic */ Action6 h;
        public final /* synthetic */ Object i;

        public i(Action6 action6, Object obj) {
            this.h = action6;
            this.i = obj;
        }

        @Override // rx.functions.Func6
        public R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
            this.h.call(t1, t2, t3, t4, t5, t6);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T4, T5, T6, R, T7, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class j<R, T1, T2, T3, T4, T5, T6, T7> implements Func7<T1, T2, T3, T4, T5, T6, T7, R> {
        public final /* synthetic */ Action7 h;
        public final /* synthetic */ Object i;

        public j(Action7 action7, Object obj) {
            this.h = action7;
            this.i = obj;
        }

        @Override // rx.functions.Func7
        public R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
            this.h.call(t1, t2, t3, t4, t5, t6, t7);
            return (R) this.i;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T4, T5, T6, R, T7, T8, T1, T2, T3] */
    /* loaded from: classes13.dex */
    public static class k<R, T1, T2, T3, T4, T5, T6, T7, T8> implements Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> {
        public final /* synthetic */ Action8 h;
        public final /* synthetic */ Object i;

        public k(Action8 action8, Object obj) {
            this.h = action8;
            this.i = obj;
        }

        @Override // rx.functions.Func8
        public R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
            this.h.call(t1, t2, t3, t4, t5, t6, t7, t8);
            return (R) this.i;
        }
    }

    /* loaded from: classes13.dex */
    public static final class l<T> implements Action1<T> {
        public final Action0 h;

        public l(Action0 action0) {
            this.h = action0;
        }

        @Override // rx.functions.Action1
        public void call(T t) {
            this.h.call();
        }
    }

    /* loaded from: classes13.dex */
    public static final class m<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements Action0, Action1<T0>, Action2<T0, T1>, Action3<T0, T1, T2>, Action4<T0, T1, T2, T3>, Action5<T0, T1, T2, T3, T4>, Action6<T0, T1, T2, T3, T4, T5>, Action7<T0, T1, T2, T3, T4, T5, T6>, Action8<T0, T1, T2, T3, T4, T5, T6, T7>, Action9<T0, T1, T2, T3, T4, T5, T6, T7, T8>, ActionN {
        @Override // rx.functions.Action0
        public void call() {
        }

        @Override // rx.functions.Action1
        public void call(T0 t0) {
        }

        @Override // rx.functions.Action2
        public void call(T0 t0, T1 t1) {
        }

        @Override // rx.functions.Action3
        public void call(T0 t0, T1 t1, T2 t2) {
        }

        @Override // rx.functions.Action4
        public void call(T0 t0, T1 t1, T2 t2, T3 t3) {
        }

        @Override // rx.functions.Action5
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
        }

        @Override // rx.functions.Action6
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        }

        @Override // rx.functions.Action7
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        }

        @Override // rx.functions.Action8
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
        }

        @Override // rx.functions.Action9
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
        }

        @Override // rx.functions.ActionN
        public void call(Object... objArr) {
        }
    }

    /* loaded from: classes13.dex */
    public enum n implements Action1<Throwable> {
        INSTANCE;

        @Override // rx.functions.Action1
        public void call(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    }

    public Actions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> m<T0, T1, T2, T3, T4, T5, T6, T7, T8> empty() {
        return f15654a;
    }

    public static Action1<Throwable> errorNotImplemented() {
        return n.INSTANCE;
    }

    public static <T> Action1<T> toAction1(Action0 action0) {
        return new l(action0);
    }

    public static Func0<Void> toFunc(Action0 action0) {
        return toFunc(action0, (Object) null);
    }

    public static <T1> Func1<T1, Void> toFunc(Action1<T1> action1) {
        return toFunc(action1, (Object) null);
    }

    public static <T1, T2> Func2<T1, T2, Void> toFunc(Action2<T1, T2> action2) {
        return toFunc(action2, (Object) null);
    }

    public static <T1, T2, T3> Func3<T1, T2, T3, Void> toFunc(Action3<T1, T2, T3> action3) {
        return toFunc(action3, (Object) null);
    }

    public static <T1, T2, T3, T4> Func4<T1, T2, T3, T4, Void> toFunc(Action4<T1, T2, T3, T4> action4) {
        return toFunc(action4, (Object) null);
    }

    public static <T1, T2, T3, T4, T5> Func5<T1, T2, T3, T4, T5, Void> toFunc(Action5<T1, T2, T3, T4, T5> action5) {
        return toFunc(action5, (Object) null);
    }

    public static <T1, T2, T3, T4, T5, T6> Func6<T1, T2, T3, T4, T5, T6, Void> toFunc(Action6<T1, T2, T3, T4, T5, T6> action6) {
        return toFunc(action6, (Object) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Func7<T1, T2, T3, T4, T5, T6, T7, Void> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> action7) {
        return toFunc(action7, (Object) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Func8<T1, T2, T3, T4, T5, T6, T7, T8, Void> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> action8) {
        return toFunc(action8, (Object) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action9) {
        return toFunc(action9, (Object) null);
    }

    public static FuncN<Void> toFunc(ActionN actionN) {
        return toFunc(actionN, (Object) null);
    }

    public static <R> Func0<R> toFunc(Action0 action0, R r) {
        return new c(action0, r);
    }

    public static <T1, R> Func1<T1, R> toFunc(Action1<T1> action1, R r) {
        return new d(action1, r);
    }

    public static <T1, T2, R> Func2<T1, T2, R> toFunc(Action2<T1, T2> action2, R r) {
        return new e(action2, r);
    }

    public static <T1, T2, T3, R> Func3<T1, T2, T3, R> toFunc(Action3<T1, T2, T3> action3, R r) {
        return new f(action3, r);
    }

    public static <T1, T2, T3, T4, R> Func4<T1, T2, T3, T4, R> toFunc(Action4<T1, T2, T3, T4> action4, R r) {
        return new g(action4, r);
    }

    public static <T1, T2, T3, T4, T5, R> Func5<T1, T2, T3, T4, T5, R> toFunc(Action5<T1, T2, T3, T4, T5> action5, R r) {
        return new h(action5, r);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Func6<T1, T2, T3, T4, T5, T6, R> toFunc(Action6<T1, T2, T3, T4, T5, T6> action6, R r) {
        return new i(action6, r);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Func7<T1, T2, T3, T4, T5, T6, T7, R> toFunc(Action7<T1, T2, T3, T4, T5, T6, T7> action7, R r) {
        return new j(action7, r);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> toFunc(Action8<T1, T2, T3, T4, T5, T6, T7, T8> action8, R r) {
        return new k(action8, r);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> toFunc(Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action9, R r) {
        return new a(action9, r);
    }

    public static <R> FuncN<R> toFunc(ActionN actionN, R r) {
        return new b(actionN, r);
    }
}
