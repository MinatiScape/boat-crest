package rx;

import rx.exceptions.MissingBackpressureException;
/* loaded from: classes13.dex */
public final class BackpressureOverflow {
    public static final Strategy ON_OVERFLOW_DEFAULT;
    public static final Strategy ON_OVERFLOW_DROP_LATEST;
    public static final Strategy ON_OVERFLOW_DROP_OLDEST;
    public static final Strategy ON_OVERFLOW_ERROR;

    /* loaded from: classes13.dex */
    public interface Strategy {
        boolean mayAttemptDrop() throws MissingBackpressureException;
    }

    /* loaded from: classes13.dex */
    public static final class a implements Strategy {

        /* renamed from: a  reason: collision with root package name */
        public static final a f15645a = new a();

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return false;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b implements Strategy {

        /* renamed from: a  reason: collision with root package name */
        public static final b f15646a = new b();

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return true;
        }
    }

    /* loaded from: classes13.dex */
    public static final class c implements Strategy {

        /* renamed from: a  reason: collision with root package name */
        public static final c f15647a = new c();

        @Override // rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }

    static {
        c cVar = c.f15647a;
        ON_OVERFLOW_ERROR = cVar;
        ON_OVERFLOW_DEFAULT = cVar;
        ON_OVERFLOW_DROP_OLDEST = b.f15646a;
        ON_OVERFLOW_DROP_LATEST = a.f15645a;
    }

    public BackpressureOverflow() {
        throw new IllegalStateException("No instances!");
    }
}
