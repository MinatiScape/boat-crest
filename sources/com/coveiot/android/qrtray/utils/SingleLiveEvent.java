package com.coveiot.android.qrtray.utils;

import android.util.Log;
import androidx.annotation.MainThread;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SingleLiveEvent<T> extends MutableLiveData<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String m = "SingleLiveEvent";
    @NotNull
    public final AtomicBoolean l = new AtomicBoolean(false);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<T, Unit> {
        public final /* synthetic */ Observer<? super T> $observer;
        public final /* synthetic */ SingleLiveEvent<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(SingleLiveEvent<T> singleLiveEvent, Observer<? super T> observer) {
            super(1);
            this.this$0 = singleLiveEvent;
            this.$observer = observer;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke2((a) obj);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(T t) {
            if (this.this$0.l.compareAndSet(true, false)) {
                this.$observer.onChanged(t);
            }
        }
    }

    public static final void g(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @MainThread
    public final void call() {
        setValue(null);
    }

    @Override // androidx.lifecycle.LiveData
    @MainThread
    public void observe(@NotNull LifecycleOwner owner, @NotNull Observer<? super T> observer) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(observer, "observer");
        if (hasActiveObservers()) {
            Log.w(m, "Multiple observers registered but only one will be notified of changes.");
        }
        final a aVar = new a(this, observer);
        super.observe(owner, new Observer() { // from class: com.coveiot.android.qrtray.utils.a
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SingleLiveEvent.g(Function1.this, obj);
            }
        });
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    @MainThread
    public void setValue(@Nullable T t) {
        this.l.set(true);
        super.setValue(t);
    }
}
