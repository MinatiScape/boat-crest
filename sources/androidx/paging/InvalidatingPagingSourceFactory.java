package androidx.paging;

import androidx.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.i;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004B!\u0012\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0096\u0002J\u0006\u0010\b\u001a\u00020\u0007R4\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\t8\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Landroidx/paging/InvalidatingPagingSourceFactory;", "", "Key", "Value", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "invoke", "", "invalidate", "Ljava/util/concurrent/CopyOnWriteArrayList;", "i", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getPagingSources$paging_common", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "getPagingSources$paging_common$annotations", "()V", "pagingSources", "pagingSourceFactory", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class InvalidatingPagingSourceFactory<Key, Value> implements Function0<PagingSource<Key, Value>> {
    @NotNull
    public final Function0<PagingSource<Key, Value>> h;
    @NotNull
    public final CopyOnWriteArrayList<PagingSource<Key, Value>> i;

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<PagingSource<Key, Value>, Boolean> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @NotNull
        public final Boolean invoke(PagingSource<Key, Value> pagingSource) {
            return Boolean.valueOf(pagingSource.getInvalid());
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
            return invoke((PagingSource) ((PagingSource) obj));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public InvalidatingPagingSourceFactory(@NotNull Function0<? extends PagingSource<Key, Value>> pagingSourceFactory) {
        Intrinsics.checkNotNullParameter(pagingSourceFactory, "pagingSourceFactory");
        this.h = pagingSourceFactory;
        this.i = new CopyOnWriteArrayList<>();
    }

    @VisibleForTesting
    public static /* synthetic */ void getPagingSources$paging_common$annotations() {
    }

    @NotNull
    public final CopyOnWriteArrayList<PagingSource<Key, Value>> getPagingSources$paging_common() {
        return this.i;
    }

    public final void invalidate() {
        Iterator<PagingSource<Key, Value>> it = this.i.iterator();
        while (it.hasNext()) {
            PagingSource<Key, Value> next = it.next();
            if (!next.getInvalid()) {
                next.invalidate();
            }
        }
        i.removeAll((List) this.i, (Function1) a.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public PagingSource<Key, Value> invoke() {
        PagingSource<Key, Value> invoke = this.h.invoke();
        getPagingSources$paging_common().add(invoke);
        return invoke;
    }
}
