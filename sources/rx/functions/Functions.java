package rx.functions;
/* loaded from: classes13.dex */
public final class Functions {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class a<R> implements FuncN<R> {
        public final /* synthetic */ Func9 h;

        public a(Func9 func9) {
            this.h = func9;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 9) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
            throw new IllegalArgumentException("Func9 expecting 9 arguments.");
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements FuncN<Void> {
        public final /* synthetic */ Action0 h;

        public b(Action0 action0) {
            this.h = action0;
        }

        @Override // rx.functions.FuncN
        /* renamed from: a */
        public Void call(Object... objArr) {
            if (objArr.length == 0) {
                this.h.call();
                return null;
            }
            throw new IllegalArgumentException("Action0 expecting 0 arguments.");
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements FuncN<Void> {
        public final /* synthetic */ Action1 h;

        public c(Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.FuncN
        /* renamed from: a */
        public Void call(Object... objArr) {
            if (objArr.length == 1) {
                this.h.call(objArr[0]);
                return null;
            }
            throw new IllegalArgumentException("Action1 expecting 1 argument.");
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements FuncN<Void> {
        public final /* synthetic */ Action2 h;

        public d(Action2 action2) {
            this.h = action2;
        }

        @Override // rx.functions.FuncN
        /* renamed from: a */
        public Void call(Object... objArr) {
            if (objArr.length == 2) {
                this.h.call(objArr[0], objArr[1]);
                return null;
            }
            throw new IllegalArgumentException("Action3 expecting 2 arguments.");
        }
    }

    /* loaded from: classes13.dex */
    public static class e implements FuncN<Void> {
        public final /* synthetic */ Action3 h;

        public e(Action3 action3) {
            this.h = action3;
        }

        @Override // rx.functions.FuncN
        /* renamed from: a */
        public Void call(Object... objArr) {
            if (objArr.length == 3) {
                this.h.call(objArr[0], objArr[1], objArr[2]);
                return null;
            }
            throw new IllegalArgumentException("Action3 expecting 3 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class f<R> implements FuncN<R> {
        public final /* synthetic */ Func0 h;

        public f(Func0 func0) {
            this.h = func0;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 0) {
                return (R) this.h.call();
            }
            throw new IllegalArgumentException("Func0 expecting 0 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class g<R> implements FuncN<R> {
        public final /* synthetic */ Func1 h;

        public g(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 1) {
                return (R) this.h.call(objArr[0]);
            }
            throw new IllegalArgumentException("Func1 expecting 1 argument.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class h<R> implements FuncN<R> {
        public final /* synthetic */ Func2 h;

        public h(Func2 func2) {
            this.h = func2;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 2) {
                return (R) this.h.call(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Func2 expecting 2 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class i<R> implements FuncN<R> {
        public final /* synthetic */ Func3 h;

        public i(Func3 func3) {
            this.h = func3;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 3) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2]);
            }
            throw new IllegalArgumentException("Func3 expecting 3 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class j<R> implements FuncN<R> {
        public final /* synthetic */ Func4 h;

        public j(Func4 func4) {
            this.h = func4;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 4) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
            throw new IllegalArgumentException("Func4 expecting 4 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class k<R> implements FuncN<R> {
        public final /* synthetic */ Func5 h;

        public k(Func5 func5) {
            this.h = func5;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 5) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
            throw new IllegalArgumentException("Func5 expecting 5 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class l<R> implements FuncN<R> {
        public final /* synthetic */ Func6 h;

        public l(Func6 func6) {
            this.h = func6;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 6) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
            throw new IllegalArgumentException("Func6 expecting 6 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class m<R> implements FuncN<R> {
        public final /* synthetic */ Func7 h;

        public m(Func7 func7) {
            this.h = func7;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 7) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
            throw new IllegalArgumentException("Func7 expecting 7 arguments.");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class n<R> implements FuncN<R> {
        public final /* synthetic */ Func8 h;

        public n(Func8 func8) {
            this.h = func8;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            if (objArr.length == 8) {
                return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
            throw new IllegalArgumentException("Func8 expecting 8 arguments.");
        }
    }

    public Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static FuncN<Void> fromAction(Action0 action0) {
        return new b(action0);
    }

    public static <R> FuncN<R> fromFunc(Func0<? extends R> func0) {
        return new f(func0);
    }

    public static <T0> FuncN<Void> fromAction(Action1<? super T0> action1) {
        return new c(action1);
    }

    public static <T0, R> FuncN<R> fromFunc(Func1<? super T0, ? extends R> func1) {
        return new g(func1);
    }

    public static <T0, T1> FuncN<Void> fromAction(Action2<? super T0, ? super T1> action2) {
        return new d(action2);
    }

    public static <T0, T1, R> FuncN<R> fromFunc(Func2<? super T0, ? super T1, ? extends R> func2) {
        return new h(func2);
    }

    public static <T0, T1, T2> FuncN<Void> fromAction(Action3<? super T0, ? super T1, ? super T2> action3) {
        return new e(action3);
    }

    public static <T0, T1, T2, R> FuncN<R> fromFunc(Func3<? super T0, ? super T1, ? super T2, ? extends R> func3) {
        return new i(func3);
    }

    public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> func4) {
        return new j(func4);
    }

    public static <T0, T1, T2, T3, T4, R> FuncN<R> fromFunc(Func5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func5) {
        return new k(func5);
    }

    public static <T0, T1, T2, T3, T4, T5, R> FuncN<R> fromFunc(Func6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func6) {
        return new l(func6);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, R> FuncN<R> fromFunc(Func7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func7) {
        return new m(func7);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, R> FuncN<R> fromFunc(Func8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func8) {
        return new n(func8);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> FuncN<R> fromFunc(Func9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func9) {
        return new a(func9);
    }
}
