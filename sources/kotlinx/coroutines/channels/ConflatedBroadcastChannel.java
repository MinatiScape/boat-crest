package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@ObsoleteCoroutinesApi
/* loaded from: classes12.dex */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater h;
    public static final /* synthetic */ AtomicIntegerFieldUpdater i;
    public static final /* synthetic */ AtomicReferenceFieldUpdater j;
    @Deprecated
    @NotNull
    public static final a k;
    @Deprecated
    @NotNull
    public static final Symbol l;
    @Deprecated
    @NotNull
    public static final c<Object> m;
    @NotNull
    private volatile /* synthetic */ Object _state;
    @NotNull
    private volatile /* synthetic */ int _updating;
    @NotNull
    private volatile /* synthetic */ Object onCloseHandler;

    /* loaded from: classes12.dex */
    public static final class a {
        @JvmField
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f14147a;

        public a(@Nullable Throwable th) {
            this.f14147a = th;
        }

        @NotNull
        public final Throwable a() {
            Throwable th = this.f14147a;
            return th == null ? new ClosedSendChannelException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }

        @NotNull
        public final Throwable b() {
            Throwable th = this.f14147a;
            return th == null ? new IllegalStateException(ChannelsKt.DEFAULT_CLOSE_MESSAGE) : th;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<E> {
        @JvmField
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Object f14148a;
        @JvmField
        @Nullable
        public final d<E>[] b;

        public c(@Nullable Object obj, @Nullable d<E>[] dVarArr) {
            this.f14148a = obj;
            this.b = dVarArr;
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<E> extends ConflatedChannel<E> implements ReceiveChannel<E> {
        @NotNull
        public final ConflatedBroadcastChannel<E> l;

        public d(@NotNull ConflatedBroadcastChannel<E> conflatedBroadcastChannel) {
            super(null);
            this.l = conflatedBroadcastChannel;
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractSendChannel
        @NotNull
        public Object offerInternal(E e) {
            return super.offerInternal(e);
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractChannel
        public void onCancelIdempotent(boolean z) {
            if (z) {
                this.l.b(this);
            }
        }
    }

    static {
        new b(null);
        k = new a(null);
        Symbol symbol = new Symbol("UNDEFINED");
        l = symbol;
        m = new c<>(symbol, null);
        h = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
        i = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
        j = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "onCloseHandler");
    }

    public ConflatedBroadcastChannel() {
        this._state = m;
        this._updating = 0;
        this.onCloseHandler = null;
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    public final d<E>[] a(d<E>[] dVarArr, d<E> dVar) {
        if (dVarArr == null) {
            d<E>[] dVarArr2 = new d[1];
            for (int i2 = 0; i2 < 1; i2++) {
                dVarArr2[i2] = dVar;
            }
            return dVarArr2;
        }
        return (d[]) ArraysKt___ArraysJvmKt.plus(dVarArr, dVar);
    }

    public final void b(d<E> dVar) {
        Object obj;
        Object obj2;
        d<E>[] dVarArr;
        do {
            obj = this._state;
            if (obj instanceof a) {
                return;
            }
            if (obj instanceof c) {
                c cVar = (c) obj;
                obj2 = cVar.f14148a;
                dVarArr = cVar.b;
                Intrinsics.checkNotNull(dVarArr);
            } else {
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
            }
        } while (!h.compareAndSet(this, obj, new c(obj2, f(dVarArr, dVar))));
    }

    public final void c(Throwable th) {
        Symbol symbol;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (symbol = AbstractChannelKt.HANDLER_INVOKED) || !j.compareAndSet(this, obj, symbol)) {
            return;
        }
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1)).invoke(th);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    /* renamed from: close */
    public boolean cancel(@Nullable Throwable th) {
        Object obj;
        int i2;
        do {
            obj = this._state;
            i2 = 0;
            if (obj instanceof a) {
                return false;
            }
            if (!(obj instanceof c)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
            }
        } while (!h.compareAndSet(this, obj, th == null ? k : new a(th)));
        d<E>[] dVarArr = ((c) obj).b;
        if (dVarArr != null) {
            int length = dVarArr.length;
            while (i2 < length) {
                d<E> dVar = dVarArr[i2];
                i2++;
                dVar.close(th);
            }
        }
        c(th);
        return true;
    }

    public final a d(E e) {
        Object obj;
        if (i.compareAndSet(this, 0, 1)) {
            do {
                try {
                    obj = this._state;
                    if (obj instanceof a) {
                        return (a) obj;
                    }
                    if (obj instanceof c) {
                    } else {
                        throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
                    }
                } finally {
                    this._updating = 0;
                }
            } while (!h.compareAndSet(this, obj, new c(e, ((c) obj).b)));
            d<E>[] dVarArr = ((c) obj).b;
            if (dVarArr != null) {
                int length = dVarArr.length;
                int i2 = 0;
                while (i2 < length) {
                    d<E> dVar = dVarArr[i2];
                    i2++;
                    dVar.offerInternal(e);
                }
            }
            return null;
        }
        return null;
    }

    public final <R> void e(SelectInstance<? super R> selectInstance, E e, Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
        if (selectInstance.trySelect()) {
            a d2 = d(e);
            if (d2 == null) {
                UndispatchedKt.startCoroutineUnintercepted(function2, this, selectInstance.getCompletion());
            } else {
                selectInstance.resumeSelectWithException(d2.a());
            }
        }
    }

    public final d<E>[] f(d<E>[] dVarArr, d<E> dVar) {
        int length = dVarArr.length;
        int indexOf = ArraysKt___ArraysKt.indexOf(dVarArr, dVar);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(indexOf >= 0)) {
                throw new AssertionError();
            }
        }
        if (length == 1) {
            return null;
        }
        d<E>[] dVarArr2 = new d[length - 1];
        ArraysKt___ArraysJvmKt.copyInto$default(dVarArr, dVarArr2, 0, 0, indexOf, 6, (Object) null);
        ArraysKt___ArraysJvmKt.copyInto$default(dVarArr, dVarArr2, indexOf, indexOf + 1, 0, 8, (Object) null);
        return dVarArr2;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return (SelectClause2<E, SendChannel<? super E>>) new SelectClause2<E, SendChannel<? super E>>(this) { // from class: kotlinx.coroutines.channels.ConflatedBroadcastChannel$onSend$1
            public final /* synthetic */ ConflatedBroadcastChannel<E> h;

            {
                this.h = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause2
            public <R> void registerSelectClause2(@NotNull SelectInstance<? super R> selectInstance, E e, @NotNull Function2<? super SendChannel<? super E>, ? super Continuation<? super R>, ? extends Object> function2) {
                this.h.e(selectInstance, e, function2);
            }
        };
    }

    public final E getValue() {
        Object obj = this._state;
        if (!(obj instanceof a)) {
            if (obj instanceof c) {
                E e = (E) ((c) obj).f14148a;
                if (e != l) {
                    return e;
                }
                throw new IllegalStateException("No value");
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
        }
        throw ((a) obj).b();
    }

    @Nullable
    public final E getValueOrNull() {
        Object obj = this._state;
        if (obj instanceof a) {
            return null;
        }
        if (obj instanceof c) {
            Symbol symbol = l;
            E e = (E) ((c) obj).f14148a;
            if (e == symbol) {
                return null;
            }
            return e;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = j;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, null, function1)) {
            Object obj = this.onCloseHandler;
            if (obj == AbstractChannelKt.HANDLER_INVOKED) {
                throw new IllegalStateException("Another handler was already registered and successfully invoked");
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Another handler was already registered: ", obj));
        }
        Object obj2 = this._state;
        if ((obj2 instanceof a) && atomicReferenceFieldUpdater.compareAndSet(this, function1, AbstractChannelKt.HANDLER_INVOKED)) {
            function1.invoke(((a) obj2).f14147a);
        }
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._state instanceof a;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E e) {
        return BroadcastChannel.DefaultImpls.offer(this, e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.BroadcastChannel
    @NotNull
    public ReceiveChannel<E> openSubscription() {
        Object obj;
        c cVar;
        d dVar = new d(this);
        do {
            obj = this._state;
            if (obj instanceof a) {
                dVar.close(((a) obj).f14147a);
                return dVar;
            } else if (obj instanceof c) {
                cVar = (c) obj;
                Object obj2 = cVar.f14148a;
                if (obj2 != l) {
                    dVar.offerInternal(obj2);
                }
            } else {
                throw new IllegalStateException(Intrinsics.stringPlus("Invalid state ", obj).toString());
            }
        } while (!h.compareAndSet(this, obj, new c(cVar.f14148a, a(cVar.b, dVar))));
        return dVar;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(E e, @NotNull Continuation<? super Unit> continuation) {
        a d2 = d(e);
        if (d2 == null) {
            if (kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() == null) {
                return null;
            }
            return Unit.INSTANCE;
        }
        throw d2.a();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    /* renamed from: trySend-JP2dKIU */
    public Object mo12trySendJP2dKIU(E e) {
        a d2 = d(e);
        if (d2 == null) {
            return ChannelResult.Companion.m755successJP2dKIU(Unit.INSTANCE);
        }
        return ChannelResult.Companion.m753closedJP2dKIU(d2.a());
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void cancel(@Nullable CancellationException cancellationException) {
        cancel(cancellationException);
    }

    public ConflatedBroadcastChannel(E e) {
        this();
        h.lazySet(this, new c(e, null));
    }
}
