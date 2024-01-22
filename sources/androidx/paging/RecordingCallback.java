package androidx.paging;

import androidx.paging.PagedList;
import com.google.android.gms.fitness.FitnessActivities;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0001¨\u0006\u000e"}, d2 = {"Landroidx/paging/RecordingCallback;", "Landroidx/paging/PagedList$Callback;", "", DeviceKey.position, "count", "", "onChanged", "onInserted", "onRemoved", FitnessActivities.OTHER, "dispatchRecordingTo", "<init>", "()V", "Companion", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class RecordingCallback extends PagedList.Callback {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<Integer> f1545a = new ArrayList();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0004¨\u0006\t"}, d2 = {"Landroidx/paging/RecordingCallback$Companion;", "", "", "Changed", "I", "Inserted", "Removed", "<init>", "()V", "paging-runtime_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void dispatchRecordingTo(@NotNull PagedList.Callback other) {
        Intrinsics.checkNotNullParameter(other, "other");
        IntProgression step = h.step(h.until(0, this.f1545a.size()), 3);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            while (true) {
                int i = first + step2;
                int intValue = this.f1545a.get(first).intValue();
                if (intValue == 0) {
                    other.onChanged(this.f1545a.get(first + 1).intValue(), this.f1545a.get(first + 2).intValue());
                } else if (intValue == 1) {
                    other.onInserted(this.f1545a.get(first + 1).intValue(), this.f1545a.get(first + 2).intValue());
                } else if (intValue == 2) {
                    other.onRemoved(this.f1545a.get(first + 1).intValue(), this.f1545a.get(first + 2).intValue());
                } else {
                    throw new IllegalStateException("Unexpected recording value");
                }
                if (first == last) {
                    break;
                }
                first = i;
            }
        }
        this.f1545a.clear();
    }

    @Override // androidx.paging.PagedList.Callback
    public void onChanged(int i, int i2) {
        this.f1545a.add(0);
        this.f1545a.add(Integer.valueOf(i));
        this.f1545a.add(Integer.valueOf(i2));
    }

    @Override // androidx.paging.PagedList.Callback
    public void onInserted(int i, int i2) {
        this.f1545a.add(1);
        this.f1545a.add(Integer.valueOf(i));
        this.f1545a.add(Integer.valueOf(i2));
    }

    @Override // androidx.paging.PagedList.Callback
    public void onRemoved(int i, int i2) {
        this.f1545a.add(2);
        this.f1545a.add(Integer.valueOf(i));
        this.f1545a.add(Integer.valueOf(i2));
    }
}
