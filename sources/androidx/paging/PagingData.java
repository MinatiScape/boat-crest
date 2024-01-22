package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.e;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u0012*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0001\u0012B%\b\u0000\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003\u0012\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u0010\u0010\u0011R(\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u00038\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u000f\u001a\u00020\n8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Landroidx/paging/PagingData;", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "a", "Lkotlinx/coroutines/flow/Flow;", "getFlow$paging_common", "()Lkotlinx/coroutines/flow/Flow;", "flow", "Landroidx/paging/UiReceiver;", "b", "Landroidx/paging/UiReceiver;", "getReceiver$paging_common", "()Landroidx/paging/UiReceiver;", "receiver", "<init>", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/UiReceiver;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagingData<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final UiReceiver c;
    @NotNull
    public static final PagingData<Object> d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Flow<PageEvent<T>> f1530a;
    @NotNull
    public final UiReceiver b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0001H\u0007J&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0007R\u001c\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR(\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u00038\u0000@\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/paging/PagingData$Companion;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagingData;", "empty", "", "data", "from", "Landroidx/paging/UiReceiver;", "NOOP_RECEIVER", "Landroidx/paging/UiReceiver;", "getNOOP_RECEIVER$paging_common", "()Landroidx/paging/UiReceiver;", "EMPTY", "Landroidx/paging/PagingData;", "getEMPTY$paging_common", "()Landroidx/paging/PagingData;", "getEMPTY$paging_common$annotations", "()V", "<init>", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getEMPTY$paging_common$annotations() {
        }

        @JvmStatic
        @NotNull
        public final <T> PagingData<T> empty() {
            return (PagingData<T>) getEMPTY$paging_common();
        }

        @JvmStatic
        @NotNull
        public final <T> PagingData<T> from(@NotNull List<? extends T> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            PageEvent.Insert.Companion companion = PageEvent.Insert.Companion;
            List listOf = e.listOf(new TransformablePage(0, data));
            LoadState.NotLoading.Companion companion2 = LoadState.NotLoading.Companion;
            return new PagingData<>(FlowKt.flowOf(PageEvent.Insert.Companion.Refresh$default(companion, listOf, 0, 0, new LoadStates(companion2.getIncomplete$paging_common(), companion2.getComplete$paging_common(), companion2.getComplete$paging_common()), null, 16, null)), getNOOP_RECEIVER$paging_common());
        }

        @NotNull
        public final PagingData<Object> getEMPTY$paging_common() {
            return PagingData.d;
        }

        @NotNull
        public final UiReceiver getNOOP_RECEIVER$paging_common() {
            return PagingData.c;
        }
    }

    static {
        UiReceiver uiReceiver = new UiReceiver() { // from class: androidx.paging.PagingData$Companion$NOOP_RECEIVER$1
            @Override // androidx.paging.UiReceiver
            public void accessHint(@NotNull ViewportHint viewportHint) {
                Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
            }

            @Override // androidx.paging.UiReceiver
            public void refresh() {
            }

            @Override // androidx.paging.UiReceiver
            public void retry() {
            }
        };
        c = uiReceiver;
        d = new PagingData<>(FlowKt.flowOf(PageEvent.Insert.Companion.getEMPTY_REFRESH_LOCAL()), uiReceiver);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PagingData(@NotNull Flow<? extends PageEvent<T>> flow, @NotNull UiReceiver receiver) {
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        this.f1530a = flow;
        this.b = receiver;
    }

    @JvmStatic
    @NotNull
    public static final <T> PagingData<T> empty() {
        return Companion.empty();
    }

    @JvmStatic
    @NotNull
    public static final <T> PagingData<T> from(@NotNull List<? extends T> list) {
        return Companion.from(list);
    }

    @NotNull
    public final Flow<PageEvent<T>> getFlow$paging_common() {
        return this.f1530a;
    }

    @NotNull
    public final UiReceiver getReceiver$paging_common() {
        return this.b;
    }
}
