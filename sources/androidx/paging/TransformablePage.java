package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.ViewportHint;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0080\b\u0018\u0000 .*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0001.B5\u0012\u0006\u0010\u0015\u001a\u00020\u000f\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011¢\u0006\u0004\b*\u0010+B\u001f\b\u0016\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0004\b*\u0010-J.\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\t\u0010\u0010\u001a\u00020\u000fHÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003JE\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00032\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0001J\t\u0010\u001b\u001a\u00020\u001aHÖ\u0001R\u0019\u0010\u0015\u001a\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00118\u0006@\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\u0017\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R!\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00118\u0006@\u0006¢\u0006\f\n\u0004\b(\u0010!\u001a\u0004\b)\u0010#¨\u0006/"}, d2 = {"Landroidx/paging/TransformablePage;", "", ExifInterface.GPS_DIRECTION_TRUE, "", FirebaseAnalytics.Param.INDEX, "presentedItemsBefore", "presentedItemsAfter", "originalPageOffsetFirst", "originalPageOffsetLast", "Landroidx/paging/ViewportHint$Access;", "viewportHintFor", FitnessActivities.OTHER, "", "equals", "hashCode", "", "component1", "", "component2", "component3", "component4", "originalPageOffsets", "data", "hintOriginalPageOffset", "hintOriginalIndices", Constants.COPY_TYPE, "", "toString", "a", "[I", "getOriginalPageOffsets", "()[I", "b", "Ljava/util/List;", "getData", "()Ljava/util/List;", com.google.android.material.color.c.f10260a, "I", "getHintOriginalPageOffset", "()I", "d", "getHintOriginalIndices", "<init>", "([ILjava/util/List;ILjava/util/List;)V", "originalPageOffset", "(ILjava/util/List;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public final class TransformablePage<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final TransformablePage<Object> e = new TransformablePage<>(0, CollectionsKt__CollectionsKt.emptyList());
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final int[] f1552a;
    @NotNull
    public final List<T> b;
    public final int c;
    @Nullable
    public final List<Integer> d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0001R\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/paging/TransformablePage$Companion;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/TransformablePage;", "empty", "EMPTY_INITIAL_PAGE", "Landroidx/paging/TransformablePage;", "getEMPTY_INITIAL_PAGE", "()Landroidx/paging/TransformablePage;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final <T> TransformablePage<T> empty() {
            return (TransformablePage<T>) getEMPTY_INITIAL_PAGE();
        }

        @NotNull
        public final TransformablePage<Object> getEMPTY_INITIAL_PAGE() {
            return TransformablePage.e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TransformablePage(@NotNull int[] originalPageOffsets, @NotNull List<? extends T> data, int i, @Nullable List<Integer> list) {
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        Intrinsics.checkNotNullParameter(data, "data");
        this.f1552a = originalPageOffsets;
        this.b = data;
        this.c = i;
        this.d = list;
        boolean z = false;
        if (!(originalPageOffsets.length == 0)) {
            if ((list == null || list.size() == data.size()) ? true : z) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("If originalIndices (size = ");
            List<Integer> hintOriginalIndices = getHintOriginalIndices();
            Intrinsics.checkNotNull(hintOriginalIndices);
            sb.append(hintOriginalIndices.size());
            sb.append(") is provided, it must be same length as data (size = ");
            sb.append(getData().size());
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        throw new IllegalArgumentException("originalPageOffsets cannot be empty when constructing TransformablePage".toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TransformablePage copy$default(TransformablePage transformablePage, int[] iArr, List list, int i, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            iArr = transformablePage.f1552a;
        }
        if ((i2 & 2) != 0) {
            list = transformablePage.b;
        }
        if ((i2 & 4) != 0) {
            i = transformablePage.c;
        }
        if ((i2 & 8) != 0) {
            list2 = transformablePage.d;
        }
        return transformablePage.copy(iArr, list, i, list2);
    }

    @NotNull
    public final int[] component1() {
        return this.f1552a;
    }

    @NotNull
    public final List<T> component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @Nullable
    public final List<Integer> component4() {
        return this.d;
    }

    @NotNull
    public final TransformablePage<T> copy(@NotNull int[] originalPageOffsets, @NotNull List<? extends T> data, int i, @Nullable List<Integer> list) {
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        Intrinsics.checkNotNullParameter(data, "data");
        return new TransformablePage<>(originalPageOffsets, data, i, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(TransformablePage.class, obj == null ? null : obj.getClass())) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type androidx.paging.TransformablePage<*>");
            TransformablePage transformablePage = (TransformablePage) obj;
            return Arrays.equals(this.f1552a, transformablePage.f1552a) && Intrinsics.areEqual(this.b, transformablePage.b) && this.c == transformablePage.c && Intrinsics.areEqual(this.d, transformablePage.d);
        }
        return false;
    }

    @NotNull
    public final List<T> getData() {
        return this.b;
    }

    @Nullable
    public final List<Integer> getHintOriginalIndices() {
        return this.d;
    }

    public final int getHintOriginalPageOffset() {
        return this.c;
    }

    @NotNull
    public final int[] getOriginalPageOffsets() {
        return this.f1552a;
    }

    public int hashCode() {
        int hashCode = ((((Arrays.hashCode(this.f1552a) * 31) + this.b.hashCode()) * 31) + this.c) * 31;
        List<Integer> list = this.d;
        return hashCode + (list == null ? 0 : list.hashCode());
    }

    @NotNull
    public String toString() {
        return "TransformablePage(originalPageOffsets=" + Arrays.toString(this.f1552a) + ", data=" + this.b + ", hintOriginalPageOffset=" + this.c + ", hintOriginalIndices=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    @NotNull
    public final ViewportHint.Access viewportHintFor(int i, int i2, int i3, int i4, int i5) {
        IntRange indices;
        int i6 = this.c;
        List<Integer> list = this.d;
        if ((list == null || (indices = CollectionsKt__CollectionsKt.getIndices(list)) == null || !indices.contains(i)) ? false : true) {
            i = this.d.get(i).intValue();
        }
        return new ViewportHint.Access(i6, i, i2, i3, i4, i5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TransformablePage(int i, @NotNull List<? extends T> data) {
        this(new int[]{i}, data, i, null);
        Intrinsics.checkNotNullParameter(data, "data");
    }
}
