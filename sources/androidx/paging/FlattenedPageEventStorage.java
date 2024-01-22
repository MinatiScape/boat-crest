package androidx.paging;

import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003J\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0007¨\u0006\u000b"}, d2 = {"Landroidx/paging/FlattenedPageEventStorage;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PageEvent;", "event", "", ProductAction.ACTION_ADD, "", "getAsEvents", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
@VisibleForTesting(otherwise = 2)
/* loaded from: classes.dex */
public final class FlattenedPageEventStorage<T> {

    /* renamed from: a  reason: collision with root package name */
    public int f1488a;
    public int b;
    @NotNull
    public final ArrayDeque<TransformablePage<T>> c = new ArrayDeque<>();
    @NotNull
    public final MutableLoadStateCollection d = new MutableLoadStateCollection();
    @Nullable
    public LoadStates e;
    public boolean f;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
            iArr[LoadType.REFRESH.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void a(PageEvent.Insert<T> insert) {
        this.d.set(insert.getSourceLoadStates());
        this.e = insert.getMediatorLoadStates();
        int i = WhenMappings.$EnumSwitchMapping$0[insert.getLoadType().ordinal()];
        if (i == 1) {
            this.f1488a = insert.getPlaceholdersBefore();
            Iterator<Integer> it = h.downTo(insert.getPages().size() - 1, 0).iterator();
            while (it.hasNext()) {
                this.c.addFirst(insert.getPages().get(((IntIterator) it).nextInt()));
            }
        } else if (i == 2) {
            this.b = insert.getPlaceholdersAfter();
            this.c.addAll(insert.getPages());
        } else if (i != 3) {
        } else {
            this.c.clear();
            this.b = insert.getPlaceholdersAfter();
            this.f1488a = insert.getPlaceholdersBefore();
            this.c.addAll(insert.getPages());
        }
    }

    public final void add(@NotNull PageEvent<T> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.f = true;
        if (event instanceof PageEvent.Insert) {
            a((PageEvent.Insert) event);
        } else if (event instanceof PageEvent.Drop) {
            c((PageEvent.Drop) event);
        } else if (event instanceof PageEvent.LoadStateUpdate) {
            b((PageEvent.LoadStateUpdate) event);
        }
    }

    public final void b(PageEvent.LoadStateUpdate<T> loadStateUpdate) {
        this.d.set(loadStateUpdate.getSource());
        this.e = loadStateUpdate.getMediator();
    }

    public final void c(PageEvent.Drop<T> drop) {
        this.d.set(drop.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
        int i = WhenMappings.$EnumSwitchMapping$0[drop.getLoadType().ordinal()];
        int i2 = 0;
        if (i == 1) {
            this.f1488a = drop.getPlaceholdersRemaining();
            int pageCount = drop.getPageCount();
            while (i2 < pageCount) {
                this.c.removeFirst();
                i2++;
            }
        } else if (i == 2) {
            this.b = drop.getPlaceholdersRemaining();
            int pageCount2 = drop.getPageCount();
            while (i2 < pageCount2) {
                this.c.removeLast();
                i2++;
            }
        } else {
            throw new IllegalArgumentException("Page drop type must be prepend or append");
        }
    }

    @NotNull
    public final List<PageEvent<T>> getAsEvents() {
        if (!this.f) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        LoadStates snapshot = this.d.snapshot();
        if (!this.c.isEmpty()) {
            arrayList.add(PageEvent.Insert.Companion.Refresh(CollectionsKt___CollectionsKt.toList(this.c), this.f1488a, this.b, snapshot, this.e));
        } else {
            arrayList.add(new PageEvent.LoadStateUpdate(snapshot, this.e));
        }
        return arrayList;
    }
}
