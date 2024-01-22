package androidx.paging;

import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B-\u0012\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0018\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u0006\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\f\u0010\nJ\u000f\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000e\u0010\u000fR$\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Landroidx/paging/InvalidateCallbackTracker;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "callbackCount$paging_common", "()I", "callbackCount", "callback", "", "registerInvalidatedCallback$paging_common", "(Ljava/lang/Object;)V", "registerInvalidatedCallback", "unregisterInvalidatedCallback$paging_common", "unregisterInvalidatedCallback", "invalidate$paging_common", "()V", "invalidate", "", "<set-?>", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Z", "getInvalid$paging_common", "()Z", "invalid", "Lkotlin/Function1;", "callbackInvoker", "Lkotlin/Function0;", "invalidGetter", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class InvalidateCallbackTracker<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function1<T, Unit> f1494a;
    @Nullable
    public final Function0<Boolean> b;
    @NotNull
    public final ReentrantLock c;
    @NotNull
    public final List<T> d;
    public boolean e;

    /* JADX WARN: Multi-variable type inference failed */
    public InvalidateCallbackTracker(@NotNull Function1<? super T, Unit> callbackInvoker, @Nullable Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(callbackInvoker, "callbackInvoker");
        this.f1494a = callbackInvoker;
        this.b = function0;
        this.c = new ReentrantLock();
        this.d = new ArrayList();
    }

    @VisibleForTesting
    public final int callbackCount$paging_common() {
        return this.d.size();
    }

    public final boolean getInvalid$paging_common() {
        return this.e;
    }

    public final void invalidate$paging_common() {
        if (this.e) {
            return;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (getInvalid$paging_common()) {
                return;
            }
            this.e = true;
            List<T> list = CollectionsKt___CollectionsKt.toList(this.d);
            this.d.clear();
            Unit unit = Unit.INSTANCE;
            if (list == null) {
                return;
            }
            Function1<T, Unit> function1 = this.f1494a;
            for (T t : list) {
                function1.invoke(t);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void registerInvalidatedCallback$paging_common(T t) {
        Function0<Boolean> function0 = this.b;
        boolean z = false;
        if (function0 != null && function0.invoke().booleanValue()) {
            invalidate$paging_common();
        }
        if (this.e) {
            this.f1494a.invoke(t);
            return;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (getInvalid$paging_common()) {
                Unit unit = Unit.INSTANCE;
                z = true;
            } else {
                this.d.add(t);
            }
            if (z) {
                this.f1494a.invoke(t);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void unregisterInvalidatedCallback$paging_common(T t) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            this.d.remove(t);
        } finally {
            reentrantLock.unlock();
        }
    }

    public /* synthetic */ InvalidateCallbackTracker(Function1 function1, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, (i & 2) != 0 ? null : function0);
    }
}
