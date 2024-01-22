package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/*  JADX ERROR: JadxRuntimeException in pass: ClassModifier
    jadx.core.utils.exceptions.JadxRuntimeException: Not class type: P
    	at jadx.core.dex.info.ClassInfo.checkClassType(ClassInfo.java:53)
    	at jadx.core.dex.info.ClassInfo.fromType(ClassInfo.java:31)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:83)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:61)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:55)
    */
@PublishedApi
/* loaded from: classes12.dex */
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {
    @NotNull
    public final SelectBuilderImpl<R> h;
    @NotNull
    public final ArrayList<Function0<Unit>> i = new ArrayList<>();

    /* loaded from: classes12.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        public final /* synthetic */ SelectClause0 $this_invoke;
        public final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(SelectClause0 selectClause0, UnbiasedSelectBuilderImpl<? super R> unbiasedSelectBuilderImpl, Function1<? super Continuation<? super R>, ? extends Object> function1) {
            super(0);
            this.$this_invoke = selectClause0;
            this.this$0 = unbiasedSelectBuilderImpl;
            this.$block = function1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.$this_invoke.registerSelectClause0(this.this$0.getInstance(), this.$block);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Function2<Q, Continuation<? super R>, Object> $block;
        public final /* synthetic */ SelectClause1<Q> $this_invoke;
        public final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public b(SelectClause1<? extends Q> selectClause1, UnbiasedSelectBuilderImpl<? super R> unbiasedSelectBuilderImpl, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
            super(0);
            this.$this_invoke = selectClause1;
            this.this$0 = unbiasedSelectBuilderImpl;
            this.$block = function2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.$this_invoke.registerSelectClause1(this.this$0.getInstance(), this.$block);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Function2<Q, Continuation<? super R>, Object> $block;
        public final /* synthetic */ P $param;
        public final /* synthetic */ SelectClause2<P, Q> $this_invoke;
        public final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(SelectClause2<? super P, ? extends Q> selectClause2, UnbiasedSelectBuilderImpl<? super R> unbiasedSelectBuilderImpl, P p, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
            super(0);
            this.$this_invoke = selectClause2;
            this.this$0 = unbiasedSelectBuilderImpl;
            this.$param = p;
            this.$block = function2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.$this_invoke.registerSelectClause2(this.this$0.getInstance(), this.$param, this.$block);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d extends Lambda implements Function0<Unit> {
        public final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
        public final /* synthetic */ long $timeMillis;
        public final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public d(UnbiasedSelectBuilderImpl<? super R> unbiasedSelectBuilderImpl, long j, Function1<? super Continuation<? super R>, ? extends Object> function1) {
            super(0);
            this.this$0 = unbiasedSelectBuilderImpl;
            this.$timeMillis = j;
            this.$block = function1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.getInstance().onTimeout(this.$timeMillis, this.$block);
        }
    }

    public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.h = new SelectBuilderImpl<>(continuation);
    }

    @NotNull
    public final ArrayList<Function0<Unit>> getClauses() {
        return this.i;
    }

    @NotNull
    public final SelectBuilderImpl<R> getInstance() {
        return this.h;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) {
        this.h.handleBuilderException(th);
    }

    @PublishedApi
    @Nullable
    public final Object initSelectResult() {
        if (!this.h.isSelected()) {
            try {
                Collections.shuffle(this.i);
                Iterator<T> it = this.i.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).invoke();
                }
            } catch (Throwable th) {
                this.h.handleBuilderException(th);
            }
        }
        return this.h.getResult();
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long j, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.i.add(new d(this, j, function1));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.i.add(new a(selectClause0, this, function1));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.i.add(new b(selectClause1, this, function2));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.i.add(new c(selectClause2, this, p, function2));
    }
}
