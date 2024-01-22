package androidx.paging;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB)\b\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0007\u0012\u0006\u0010\u0019\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000b\u001a\u00020\u0007H\u0016R\u0019\u0010\u0010\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0013\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u000fR\u0019\u0010\u0016\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0015\u0010\u000fR\u0019\u0010\u0019\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0018\u0010\u000f\u0082\u0001\u0002\u001e\u001f¨\u0006 "}, d2 = {"Landroidx/paging/ViewportHint;", "", FitnessActivities.OTHER, "", "equals", "Landroidx/paging/LoadType;", "loadType", "", "presentedItemsBeyondAnchor$paging_common", "(Landroidx/paging/LoadType;)I", "presentedItemsBeyondAnchor", "hashCode", "a", "I", "getPresentedItemsBefore", "()I", "presentedItemsBefore", "b", "getPresentedItemsAfter", "presentedItemsAfter", com.google.android.material.color.c.f10260a, "getOriginalPageOffsetFirst", "originalPageOffsetFirst", "d", "getOriginalPageOffsetLast", "originalPageOffsetLast", "<init>", "(IIII)V", "Access", "Initial", "Landroidx/paging/ViewportHint$Initial;", "Landroidx/paging/ViewportHint$Access;", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class ViewportHint {

    /* renamed from: a  reason: collision with root package name */
    public final int f1554a;
    public final int b;
    public final int c;
    public final int d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\u0006\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u0019\u0010\u000e\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0011\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\r¨\u0006\u0018"}, d2 = {"Landroidx/paging/ViewportHint$Access;", "Landroidx/paging/ViewportHint;", "", FitnessActivities.OTHER, "", "equals", "", "hashCode", "", "toString", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "I", "getPageOffset", "()I", "pageOffset", "f", "getIndexInPage", "indexInPage", "presentedItemsBefore", "presentedItemsAfter", "originalPageOffsetFirst", "originalPageOffsetLast", "<init>", "(IIIIII)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Access extends ViewportHint {
        public final int e;
        public final int f;

        public Access(int i, int i2, int i3, int i4, int i5, int i6) {
            super(i3, i4, i5, i6, null);
            this.e = i;
            this.f = i2;
        }

        @Override // androidx.paging.ViewportHint
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Access) {
                Access access = (Access) obj;
                return this.e == access.e && this.f == access.f && getPresentedItemsBefore() == access.getPresentedItemsBefore() && getPresentedItemsAfter() == access.getPresentedItemsAfter() && getOriginalPageOffsetFirst() == access.getOriginalPageOffsetFirst() && getOriginalPageOffsetLast() == access.getOriginalPageOffsetLast();
            }
            return false;
        }

        public final int getIndexInPage() {
            return this.f;
        }

        public final int getPageOffset() {
            return this.e;
        }

        @Override // androidx.paging.ViewportHint
        public int hashCode() {
            return super.hashCode() + Integer.hashCode(this.e) + Integer.hashCode(this.f);
        }

        @NotNull
        public String toString() {
            return f.trimMargin$default("ViewportHint.Access(\n            |    pageOffset=" + this.e + ",\n            |    indexInPage=" + this.f + ",\n            |    presentedItemsBefore=" + getPresentedItemsBefore() + ",\n            |    presentedItemsAfter=" + getPresentedItemsAfter() + ",\n            |    originalPageOffsetFirst=" + getOriginalPageOffsetFirst() + ",\n            |    originalPageOffsetLast=" + getOriginalPageOffsetLast() + ",\n            |)", null, 1, null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Landroidx/paging/ViewportHint$Initial;", "Landroidx/paging/ViewportHint;", "", "toString", "", "presentedItemsBefore", "presentedItemsAfter", "originalPageOffsetFirst", "originalPageOffsetLast", "<init>", "(IIII)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Initial extends ViewportHint {
        public Initial(int i, int i2, int i3, int i4) {
            super(i, i2, i3, i4, null);
        }

        @NotNull
        public String toString() {
            return f.trimMargin$default("ViewportHint.Initial(\n            |    presentedItemsBefore=" + getPresentedItemsBefore() + ",\n            |    presentedItemsAfter=" + getPresentedItemsAfter() + ",\n            |    originalPageOffsetFirst=" + getOriginalPageOffsetFirst() + ",\n            |    originalPageOffsetLast=" + getOriginalPageOffsetLast() + ",\n            |)", null, 1, null);
        }
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

    public ViewportHint(int i, int i2, int i3, int i4) {
        this.f1554a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public /* synthetic */ ViewportHint(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ViewportHint) {
            ViewportHint viewportHint = (ViewportHint) obj;
            return this.f1554a == viewportHint.f1554a && this.b == viewportHint.b && this.c == viewportHint.c && this.d == viewportHint.d;
        }
        return false;
    }

    public final int getOriginalPageOffsetFirst() {
        return this.c;
    }

    public final int getOriginalPageOffsetLast() {
        return this.d;
    }

    public final int getPresentedItemsAfter() {
        return this.b;
    }

    public final int getPresentedItemsBefore() {
        return this.f1554a;
    }

    public int hashCode() {
        return Integer.hashCode(this.f1554a) + Integer.hashCode(this.b) + Integer.hashCode(this.c) + Integer.hashCode(this.d);
    }

    public final int presentedItemsBeyondAnchor$paging_common(@NotNull LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return this.b;
                }
                throw new NoWhenBranchMatchedException();
            }
            return this.f1554a;
        }
        throw new IllegalArgumentException("Cannot get presentedItems for loadType: REFRESH");
    }
}
