package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.ViewportHint;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.i;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 **\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0002*+B\u0015\u0012\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&¢\u0006\u0004\b(\u0010)J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0017\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nJ\u0017\u0010\r\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\tJ\u001c\u0010\u0013\u001a\u00020\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0006R$\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00068\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR$\u0010 \u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00068\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u001a\u001a\u0004\b\u001f\u0010\u001cR$\u0010#\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00068\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b\"\u0010\u001cR\u0016\u0010%\u001a\u00020\u00068V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001c¨\u0006,"}, d2 = {"Landroidx/paging/PagePresenter;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/NullPaddedList;", "", "toString", "", FirebaseAnalytics.Param.INDEX, "get", "(I)Ljava/lang/Object;", "Landroidx/paging/ItemSnapshotList;", "snapshot", "localIndex", "getFromStorage", "Landroidx/paging/PageEvent;", "pageEvent", "Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "callback", "", "processEvent", "Landroidx/paging/ViewportHint$Initial;", "initializeHint", "Landroidx/paging/ViewportHint$Access;", "accessHintForPresenterIndex", "<set-?>", "i", "I", "getStorageCount", "()I", "storageCount", "j", "getPlaceholdersBefore", "placeholdersBefore", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getPlaceholdersAfter", "placeholdersAfter", "getSize", "size", "Landroidx/paging/PageEvent$Insert;", "insertEvent", "<init>", "(Landroidx/paging/PageEvent$Insert;)V", "Companion", "ProcessPageEventCallback", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class PagePresenter<T> implements NullPaddedList<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final PagePresenter<Object> l = new PagePresenter<>(PageEvent.Insert.Companion.getEMPTY_REFRESH_LOCAL());
    @NotNull
    public final List<TransformablePage<T>> h;
    public int i;
    public int j;
    public int k;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00038\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/paging/PagePresenter$Companion;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PagePresenter;", "initial$paging_common", "()Landroidx/paging/PagePresenter;", "initial", "INITIAL", "Landroidx/paging/PagePresenter;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final <T> PagePresenter<T> initial$paging_common() {
            return PagePresenter.l;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&J \u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH&J\u001a\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H&¨\u0006\u0013"}, d2 = {"Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "", "", DeviceKey.position, "count", "", "onChanged", "onInserted", "onRemoved", "Landroidx/paging/LoadType;", "loadType", "", "fromMediator", "Landroidx/paging/LoadState;", "loadState", "onStateUpdate", "Landroidx/paging/LoadStates;", "source", "mediator", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public interface ProcessPageEventCallback {
        void onChanged(int i, int i2);

        void onInserted(int i, int i2);

        void onRemoved(int i, int i2);

        void onStateUpdate(@NotNull LoadStates loadStates, @Nullable LoadStates loadStates2);

        void onStateUpdate(@NotNull LoadType loadType, boolean z, @NotNull LoadState loadState);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PagePresenter(@NotNull PageEvent.Insert<T> insertEvent) {
        Intrinsics.checkNotNullParameter(insertEvent, "insertEvent");
        this.h = CollectionsKt___CollectionsKt.toMutableList((Collection) insertEvent.getPages());
        this.i = d(insertEvent.getPages());
        this.j = insertEvent.getPlaceholdersBefore();
        this.k = insertEvent.getPlaceholdersAfter();
    }

    public final void a(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + getSize());
        }
    }

    @NotNull
    public final ViewportHint.Access accessHintForPresenterIndex(int i) {
        int i2 = 0;
        int placeholdersBefore = i - getPlaceholdersBefore();
        while (placeholdersBefore >= this.h.get(i2).getData().size() && i2 < CollectionsKt__CollectionsKt.getLastIndex(this.h)) {
            placeholdersBefore -= this.h.get(i2).getData().size();
            i2++;
        }
        return this.h.get(i2).viewportHintFor(placeholdersBefore, i - getPlaceholdersBefore(), ((getSize() - i) - getPlaceholdersAfter()) - 1, e(), f());
    }

    public final void b(PageEvent.Drop<T> drop, ProcessPageEventCallback processPageEventCallback) {
        int size = getSize();
        LoadType loadType = drop.getLoadType();
        LoadType loadType2 = LoadType.PREPEND;
        if (loadType == loadType2) {
            int placeholdersBefore = getPlaceholdersBefore();
            this.i = getStorageCount() - c(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
            this.j = drop.getPlaceholdersRemaining();
            int size2 = getSize() - size;
            if (size2 > 0) {
                processPageEventCallback.onInserted(0, size2);
            } else if (size2 < 0) {
                processPageEventCallback.onRemoved(0, -size2);
            }
            int max = Math.max(0, placeholdersBefore + size2);
            int placeholdersRemaining = drop.getPlaceholdersRemaining() - max;
            if (placeholdersRemaining > 0) {
                processPageEventCallback.onChanged(max, placeholdersRemaining);
            }
            processPageEventCallback.onStateUpdate(loadType2, false, LoadState.NotLoading.Companion.getIncomplete$paging_common());
            return;
        }
        int placeholdersAfter = getPlaceholdersAfter();
        this.i = getStorageCount() - c(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
        this.k = drop.getPlaceholdersRemaining();
        int size3 = getSize() - size;
        if (size3 > 0) {
            processPageEventCallback.onInserted(size, size3);
        } else if (size3 < 0) {
            processPageEventCallback.onRemoved(size + size3, -size3);
        }
        int placeholdersRemaining2 = drop.getPlaceholdersRemaining() - (placeholdersAfter - (size3 < 0 ? Math.min(placeholdersAfter, -size3) : 0));
        if (placeholdersRemaining2 > 0) {
            processPageEventCallback.onChanged(getSize() - drop.getPlaceholdersRemaining(), placeholdersRemaining2);
        }
        processPageEventCallback.onStateUpdate(LoadType.APPEND, false, LoadState.NotLoading.Companion.getIncomplete$paging_common());
    }

    public final int c(IntRange intRange) {
        boolean z;
        Iterator<TransformablePage<T>> it = this.h.iterator();
        int i = 0;
        while (it.hasNext()) {
            TransformablePage<T> next = it.next();
            int[] originalPageOffsets = next.getOriginalPageOffsets();
            int length = originalPageOffsets.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                } else if (intRange.contains(originalPageOffsets[i2])) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                i += next.getData().size();
                it.remove();
            }
        }
        return i;
    }

    public final int d(List<TransformablePage<T>> list) {
        Iterator<T> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((TransformablePage) it.next()).getData().size();
        }
        return i;
    }

    public final int e() {
        Integer minOrNull = ArraysKt___ArraysKt.minOrNull(((TransformablePage) CollectionsKt___CollectionsKt.first((List<? extends Object>) this.h)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(minOrNull);
        return minOrNull.intValue();
    }

    public final int f() {
        Integer maxOrNull = ArraysKt___ArraysKt.maxOrNull(((TransformablePage) CollectionsKt___CollectionsKt.last((List<? extends Object>) this.h)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(maxOrNull);
        return maxOrNull.intValue();
    }

    public final void g(PageEvent.Insert<T> insert, ProcessPageEventCallback processPageEventCallback) {
        int d = d(insert.getPages());
        int size = getSize();
        int i = WhenMappings.$EnumSwitchMapping$0[insert.getLoadType().ordinal()];
        if (i != 1) {
            if (i == 2) {
                int min = Math.min(getPlaceholdersBefore(), d);
                int i2 = d - min;
                this.h.addAll(0, insert.getPages());
                this.i = getStorageCount() + d;
                this.j = insert.getPlaceholdersBefore();
                processPageEventCallback.onChanged(getPlaceholdersBefore() - min, min);
                processPageEventCallback.onInserted(0, i2);
                int size2 = (getSize() - size) - i2;
                if (size2 > 0) {
                    processPageEventCallback.onInserted(0, size2);
                } else if (size2 < 0) {
                    processPageEventCallback.onRemoved(0, -size2);
                }
            } else if (i == 3) {
                int min2 = Math.min(getPlaceholdersAfter(), d);
                int placeholdersBefore = getPlaceholdersBefore() + getStorageCount();
                int i3 = d - min2;
                List<TransformablePage<T>> list = this.h;
                list.addAll(list.size(), insert.getPages());
                this.i = getStorageCount() + d;
                this.k = insert.getPlaceholdersAfter();
                processPageEventCallback.onChanged(placeholdersBefore, min2);
                processPageEventCallback.onInserted(placeholdersBefore + min2, i3);
                int size3 = (getSize() - size) - i3;
                if (size3 > 0) {
                    processPageEventCallback.onInserted(getSize() - size3, size3);
                } else if (size3 < 0) {
                    processPageEventCallback.onRemoved(getSize(), -size3);
                }
            }
            processPageEventCallback.onStateUpdate(insert.getSourceLoadStates(), insert.getMediatorLoadStates());
            return;
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public final T get(int i) {
        a(i);
        int placeholdersBefore = i - getPlaceholdersBefore();
        if (placeholdersBefore < 0 || placeholdersBefore >= getStorageCount()) {
            return null;
        }
        return getFromStorage(placeholdersBefore);
    }

    @Override // androidx.paging.NullPaddedList
    @NotNull
    public T getFromStorage(int i) {
        int size = this.h.size();
        int i2 = 0;
        while (i2 < size) {
            int size2 = this.h.get(i2).getData().size();
            if (size2 > i) {
                break;
            }
            i -= size2;
            i2++;
        }
        return this.h.get(i2).getData().get(i);
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersAfter() {
        return this.k;
    }

    @Override // androidx.paging.NullPaddedList
    public int getPlaceholdersBefore() {
        return this.j;
    }

    @Override // androidx.paging.NullPaddedList
    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    @Override // androidx.paging.NullPaddedList
    public int getStorageCount() {
        return this.i;
    }

    @NotNull
    public final ViewportHint.Initial initializeHint() {
        int storageCount = getStorageCount() / 2;
        return new ViewportHint.Initial(storageCount, storageCount, e(), f());
    }

    public final void processEvent(@NotNull PageEvent<T> pageEvent, @NotNull ProcessPageEventCallback callback) {
        Intrinsics.checkNotNullParameter(pageEvent, "pageEvent");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (pageEvent instanceof PageEvent.Insert) {
            g((PageEvent.Insert) pageEvent, callback);
        } else if (pageEvent instanceof PageEvent.Drop) {
            b((PageEvent.Drop) pageEvent, callback);
        } else if (pageEvent instanceof PageEvent.LoadStateUpdate) {
            PageEvent.LoadStateUpdate loadStateUpdate = (PageEvent.LoadStateUpdate) pageEvent;
            callback.onStateUpdate(loadStateUpdate.getSource(), loadStateUpdate.getMediator());
        }
    }

    @NotNull
    public final ItemSnapshotList<T> snapshot() {
        int placeholdersBefore = getPlaceholdersBefore();
        int placeholdersAfter = getPlaceholdersAfter();
        List<TransformablePage<T>> list = this.h;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            i.addAll(arrayList, ((TransformablePage) it.next()).getData());
        }
        return new ItemSnapshotList<>(placeholdersBefore, placeholdersAfter, arrayList);
    }

    @NotNull
    public String toString() {
        int storageCount = getStorageCount();
        ArrayList arrayList = new ArrayList(storageCount);
        for (int i = 0; i < storageCount; i++) {
            arrayList.add(getFromStorage(i));
        }
        String joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null);
        return "[(" + getPlaceholdersBefore() + " placeholders), " + joinToString$default + ", (" + getPlaceholdersAfter() + " placeholders)]";
    }
}
