package androidx.paging;

import androidx.exifinterface.media.ExifInterface;
import androidx.paging.LoadState;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.e;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0003\u0010\u0011\u0012B\t\b\u0004¢\u0006\u0004\b\u000e\u0010\u000fJG\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0003*\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJM\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000\"\b\b\u0001\u0010\u0003*\u00020\u00012(\u0010\u0006\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\bJ=\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\b\u0082\u0001\u0003\u0013\u0014\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/paging/PageEvent;", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "transform", "map", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "flatMap", "", "predicate", "filter", "<init>", "()V", "Drop", "Insert", "LoadStateUpdate", "Landroidx/paging/PageEvent$Insert;", "Landroidx/paging/PageEvent$Drop;", "Landroidx/paging/PageEvent$LoadStateUpdate;", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PageEvent<T> {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003B'\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0004\b#\u0010$J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\b\u001a\u00020\u0006HÆ\u0003J\t\u0010\t\u001a\u00020\u0006HÆ\u0003J7\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006HÆ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\f\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0019\u0010\r\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001cR\u0013\u0010\"\u001a\u00020\u00068F@\u0006¢\u0006\u0006\u001a\u0004\b!\u0010\u001c¨\u0006%"}, d2 = {"Landroidx/paging/PageEvent$Drop;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PageEvent;", "Landroidx/paging/LoadType;", "component1", "", "component2", "component3", "component4", "loadType", "minPageOffset", "maxPageOffset", "placeholdersRemaining", Constants.COPY_TYPE, "", "toString", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Landroidx/paging/LoadType;", "getLoadType", "()Landroidx/paging/LoadType;", "b", "I", "getMinPageOffset", "()I", com.google.android.material.color.c.f10260a, "getMaxPageOffset", "d", "getPlaceholdersRemaining", "getPageCount", "pageCount", "<init>", "(Landroidx/paging/LoadType;III)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Drop<T> extends PageEvent<T> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LoadType f1511a;
        public final int b;
        public final int c;
        public final int d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Drop(@NotNull LoadType loadType, int i, int i2, int i3) {
            super(null);
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            this.f1511a = loadType;
            this.b = i;
            this.c = i2;
            this.d = i3;
            if (loadType != LoadType.REFRESH) {
                if (!(getPageCount() > 0)) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Drop count must be > 0, but was ", Integer.valueOf(getPageCount())).toString());
                }
                if (!(i3 >= 0)) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Invalid placeholdersRemaining ", Integer.valueOf(getPlaceholdersRemaining())).toString());
                }
                return;
            }
            throw new IllegalArgumentException("Drop load type must be PREPEND or APPEND".toString());
        }

        public static /* synthetic */ Drop copy$default(Drop drop, LoadType loadType, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                loadType = drop.f1511a;
            }
            if ((i4 & 2) != 0) {
                i = drop.b;
            }
            if ((i4 & 4) != 0) {
                i2 = drop.c;
            }
            if ((i4 & 8) != 0) {
                i3 = drop.d;
            }
            return drop.copy(loadType, i, i2, i3);
        }

        @NotNull
        public final LoadType component1() {
            return this.f1511a;
        }

        public final int component2() {
            return this.b;
        }

        public final int component3() {
            return this.c;
        }

        public final int component4() {
            return this.d;
        }

        @NotNull
        public final Drop<T> copy(@NotNull LoadType loadType, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            return new Drop<>(loadType, i, i2, i3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Drop) {
                Drop drop = (Drop) obj;
                return this.f1511a == drop.f1511a && this.b == drop.b && this.c == drop.c && this.d == drop.d;
            }
            return false;
        }

        @NotNull
        public final LoadType getLoadType() {
            return this.f1511a;
        }

        public final int getMaxPageOffset() {
            return this.c;
        }

        public final int getMinPageOffset() {
            return this.b;
        }

        public final int getPageCount() {
            return (this.c - this.b) + 1;
        }

        public final int getPlaceholdersRemaining() {
            return this.d;
        }

        public int hashCode() {
            return (((((this.f1511a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
        }

        @NotNull
        public String toString() {
            return "Drop(loadType=" + this.f1511a + ", minPageOffset=" + this.b + ", maxPageOffset=" + this.c + ", placeholdersRemaining=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u0086\b\u0018\u0000 @*\b\b\u0001\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003:\u0001@BI\b\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0015\u0012\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0006\u0010 \u001a\u00020\u0018\u0012\u0006\u0010!\u001a\u00020\u0018\u0012\u0006\u0010\"\u001a\u00020\u001b\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b>\u0010?JO\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00012*\u0010\b\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00070\u00060\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJG\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003\"\b\b\u0002\u0010\u0004*\u00020\u00012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0096@ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJM\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00020\u0003\"\b\b\u0002\u0010\u0004*\u00020\u00012(\u0010\b\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00100\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0096@ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u000fJ=\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\"\u0010\u0013\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0096@ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u000fJ\t\u0010\u0016\u001a\u00020\u0015HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0018HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0018HÆ\u0003J\t\u0010\u001c\u001a\u00020\u001bHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u001bHÆ\u0003JY\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u00152\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u00062\b\b\u0002\u0010 \u001a\u00020\u00182\b\b\u0002\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u001b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001bHÆ\u0001J\t\u0010&\u001a\u00020%HÖ\u0001J\t\u0010'\u001a\u00020\u0018HÖ\u0001J\u0013\u0010)\u001a\u00020\u00122\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u001e\u001a\u00020\u00158\u0006@\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R%\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u00068\u0006@\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0019\u0010 \u001a\u00020\u00188\u0006@\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R\u0019\u0010!\u001a\u00020\u00188\u0006@\u0006¢\u0006\f\n\u0004\b6\u00103\u001a\u0004\b7\u00105R\u0019\u0010\"\u001a\u00020\u001b8\u0006@\u0006¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001b\u0010#\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006¢\u0006\f\n\u0004\b<\u00109\u001a\u0004\b=\u0010;\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006A"}, d2 = {"Landroidx/paging/PageEvent$Insert;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PageEvent;", "R", "Lkotlin/Function1;", "", "Landroidx/paging/TransformablePage;", "transform", "transformPages$paging_common", "(Lkotlin/jvm/functions/Function1;)Landroidx/paging/PageEvent$Insert;", "transformPages", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "map", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "flatMap", "", "predicate", "filter", "Landroidx/paging/LoadType;", "component1", "component2", "", "component3", "component4", "Landroidx/paging/LoadStates;", "component5", "component6", "loadType", "pages", "placeholdersBefore", "placeholdersAfter", "sourceLoadStates", "mediatorLoadStates", Constants.COPY_TYPE, "", "toString", "hashCode", FitnessActivities.OTHER, "equals", "a", "Landroidx/paging/LoadType;", "getLoadType", "()Landroidx/paging/LoadType;", "b", "Ljava/util/List;", "getPages", "()Ljava/util/List;", com.google.android.material.color.c.f10260a, "I", "getPlaceholdersBefore", "()I", "d", "getPlaceholdersAfter", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Landroidx/paging/LoadStates;", "getSourceLoadStates", "()Landroidx/paging/LoadStates;", "f", "getMediatorLoadStates", "<init>", "(Landroidx/paging/LoadType;Ljava/util/List;IILandroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Insert<T> extends PageEvent<T> {
        @NotNull
        public static final Companion Companion;
        @NotNull
        public static final Insert<Object> g;
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LoadType f1512a;
        @NotNull
        public final List<TransformablePage<T>> b;
        public final int c;
        public final int d;
        @NotNull
        public final LoadStates e;
        @Nullable
        public final LoadStates f;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015JN\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00020\f\"\b\b\u0002\u0010\u0002*\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tJF\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\f\"\b\b\u0002\u0010\u0002*\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tJF\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00020\f\"\b\b\u0002\u0010\u0002*\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\tR\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/paging/PageEvent$Insert$Companion;", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroidx/paging/TransformablePage;", "pages", "", "placeholdersBefore", "placeholdersAfter", "Landroidx/paging/LoadStates;", "sourceLoadStates", "mediatorLoadStates", "Landroidx/paging/PageEvent$Insert;", HttpHeaders.REFRESH, "Prepend", "Append", "EMPTY_REFRESH_LOCAL", "Landroidx/paging/PageEvent$Insert;", "getEMPTY_REFRESH_LOCAL", "()Landroidx/paging/PageEvent$Insert;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public static /* synthetic */ Insert Append$default(Companion companion, List list, int i, LoadStates loadStates, LoadStates loadStates2, int i2, Object obj) {
                if ((i2 & 8) != 0) {
                    loadStates2 = null;
                }
                return companion.Append(list, i, loadStates, loadStates2);
            }

            public static /* synthetic */ Insert Prepend$default(Companion companion, List list, int i, LoadStates loadStates, LoadStates loadStates2, int i2, Object obj) {
                if ((i2 & 8) != 0) {
                    loadStates2 = null;
                }
                return companion.Prepend(list, i, loadStates, loadStates2);
            }

            public static /* synthetic */ Insert Refresh$default(Companion companion, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, int i3, Object obj) {
                if ((i3 & 16) != 0) {
                    loadStates2 = null;
                }
                return companion.Refresh(list, i, i2, loadStates, loadStates2);
            }

            @NotNull
            public final <T> Insert<T> Append(@NotNull List<TransformablePage<T>> pages, int i, @NotNull LoadStates sourceLoadStates, @Nullable LoadStates loadStates) {
                Intrinsics.checkNotNullParameter(pages, "pages");
                Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
                return new Insert<>(LoadType.APPEND, pages, -1, i, sourceLoadStates, loadStates, null);
            }

            @NotNull
            public final <T> Insert<T> Prepend(@NotNull List<TransformablePage<T>> pages, int i, @NotNull LoadStates sourceLoadStates, @Nullable LoadStates loadStates) {
                Intrinsics.checkNotNullParameter(pages, "pages");
                Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
                return new Insert<>(LoadType.PREPEND, pages, i, -1, sourceLoadStates, loadStates, null);
            }

            @NotNull
            public final <T> Insert<T> Refresh(@NotNull List<TransformablePage<T>> pages, int i, int i2, @NotNull LoadStates sourceLoadStates, @Nullable LoadStates loadStates) {
                Intrinsics.checkNotNullParameter(pages, "pages");
                Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
                return new Insert<>(LoadType.REFRESH, pages, i, i2, sourceLoadStates, loadStates, null);
            }

            @NotNull
            public final Insert<Object> getEMPTY_REFRESH_LOCAL() {
                return Insert.g;
            }
        }

        @DebugMetadata(c = "androidx.paging.PageEvent$Insert", f = "PageEvent.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {104}, m = "filter", n = {"predicate", "this_$iv$iv", "destination$iv$iv$iv", "it", "originalIndices", "data", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "index$iv", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "L$1", "L$3", "L$5", "L$6", "L$7", "L$9", "I$0", "I$1"})
        /* loaded from: classes.dex */
        public static final class a extends ContinuationImpl {
            public int I$0;
            public int I$1;
            public Object L$0;
            public Object L$1;
            public Object L$10;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public Object L$7;
            public Object L$8;
            public Object L$9;
            public int label;
            public /* synthetic */ Object result;
            public final /* synthetic */ Insert<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Insert<T> insert, Continuation<? super a> continuation) {
                super(continuation);
                this.this$0 = insert;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.filter(null, this);
            }
        }

        @DebugMetadata(c = "androidx.paging.PageEvent$Insert", f = "PageEvent.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {86}, m = "flatMap", n = {"transform", "this_$iv$iv", "destination$iv$iv$iv", "it", "originalIndices", "data", "index$iv", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "L$1", "L$3", "L$5", "L$6", "L$7", "I$0", "I$1"})
        /* loaded from: classes.dex */
        public static final class b<R> extends ContinuationImpl {
            public int I$0;
            public int I$1;
            public Object L$0;
            public Object L$1;
            public Object L$10;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public Object L$7;
            public Object L$8;
            public Object L$9;
            public int label;
            public /* synthetic */ Object result;
            public final /* synthetic */ Insert<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(Insert<T> insert, Continuation<? super b> continuation) {
                super(continuation);
                this.this$0 = insert;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.flatMap(null, this);
            }
        }

        @DebugMetadata(c = "androidx.paging.PageEvent$Insert", f = "PageEvent.kt", i = {0, 0, 0, 0, 0}, l = {74}, m = "map", n = {"transform", "this_$iv$iv", "destination$iv$iv$iv", "it", "destination$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$5", "L$7"})
        /* loaded from: classes.dex */
        public static final class c<R> extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public Object L$10;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public Object L$7;
            public Object L$8;
            public Object L$9;
            public int label;
            public /* synthetic */ Object result;
            public final /* synthetic */ Insert<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(Insert<T> insert, Continuation<? super c> continuation) {
                super(continuation);
                this.this$0 = insert;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.map(null, this);
            }
        }

        static {
            Companion companion = new Companion(null);
            Companion = companion;
            List listOf = e.listOf(TransformablePage.Companion.getEMPTY_INITIAL_PAGE());
            LoadState.NotLoading.Companion companion2 = LoadState.NotLoading.Companion;
            g = Companion.Refresh$default(companion, listOf, 0, 0, new LoadStates(companion2.getIncomplete$paging_common(), companion2.getComplete$paging_common(), companion2.getComplete$paging_common()), null, 16, null);
        }

        public Insert(LoadType loadType, List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates, LoadStates loadStates2) {
            super(null);
            this.f1512a = loadType;
            this.b = list;
            this.c = i;
            this.d = i2;
            this.e = loadStates;
            this.f = loadStates2;
            boolean z = false;
            if (loadType == LoadType.APPEND || i >= 0) {
                if (loadType == LoadType.PREPEND || i2 >= 0) {
                    if (!((loadType != LoadType.REFRESH || (list.isEmpty() ^ true)) ? true : z)) {
                        throw new IllegalArgumentException("Cannot create a REFRESH Insert event with no TransformablePages as this could permanently stall pagination. Note that this check does not prevent empty LoadResults and is instead usually an indication of an internal error in Paging itself.".toString());
                    }
                    return;
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("Append insert defining placeholdersAfter must be > 0, but was ", Integer.valueOf(getPlaceholdersAfter())).toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Prepend insert defining placeholdersBefore must be > 0, but was ", Integer.valueOf(getPlaceholdersBefore())).toString());
        }

        public /* synthetic */ Insert(LoadType loadType, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadType, list, i, i2, loadStates, loadStates2);
        }

        public static /* synthetic */ Insert copy$default(Insert insert, LoadType loadType, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                loadType = insert.f1512a;
            }
            List<TransformablePage<T>> list2 = list;
            if ((i3 & 2) != 0) {
                list2 = insert.b;
            }
            List list3 = list2;
            if ((i3 & 4) != 0) {
                i = insert.c;
            }
            int i4 = i;
            if ((i3 & 8) != 0) {
                i2 = insert.d;
            }
            int i5 = i2;
            if ((i3 & 16) != 0) {
                loadStates = insert.e;
            }
            LoadStates loadStates3 = loadStates;
            if ((i3 & 32) != 0) {
                loadStates2 = insert.f;
            }
            return insert.copy(loadType, list3, i4, i5, loadStates3, loadStates2);
        }

        @NotNull
        public final LoadType component1() {
            return this.f1512a;
        }

        @NotNull
        public final List<TransformablePage<T>> component2() {
            return this.b;
        }

        public final int component3() {
            return this.c;
        }

        public final int component4() {
            return this.d;
        }

        @NotNull
        public final LoadStates component5() {
            return this.e;
        }

        @Nullable
        public final LoadStates component6() {
            return this.f;
        }

        @NotNull
        public final Insert<T> copy(@NotNull LoadType loadType, @NotNull List<TransformablePage<T>> pages, int i, int i2, @NotNull LoadStates sourceLoadStates, @Nullable LoadStates loadStates) {
            Intrinsics.checkNotNullParameter(loadType, "loadType");
            Intrinsics.checkNotNullParameter(pages, "pages");
            Intrinsics.checkNotNullParameter(sourceLoadStates, "sourceLoadStates");
            return new Insert<>(loadType, pages, i, i2, sourceLoadStates, loadStates);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Insert) {
                Insert insert = (Insert) obj;
                return this.f1512a == insert.f1512a && Intrinsics.areEqual(this.b, insert.b) && this.c == insert.c && this.d == insert.d && Intrinsics.areEqual(this.e, insert.e) && Intrinsics.areEqual(this.f, insert.f);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00c6  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x010c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0148  */
        /* JADX WARN: Type inference failed for: r10v8, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r11v7, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r14v3, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r7v8, types: [java.util.Collection] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x009e -> B:18:0x00c0). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00f5 -> B:27:0x0104). Please submit an issue!!! */
        @Override // androidx.paging.PageEvent
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object filter(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<T>> r20) {
            /*
                Method dump skipped, instructions count: 356
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.filter(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0076  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00c5  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0125 A[LOOP:0: B:34:0x011b->B:36:0x0125, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x014e  */
        /* JADX WARN: Type inference failed for: r10v8, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r11v7, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r14v3, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r7v8, types: [java.util.Collection] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x009e -> B:18:0x00bf). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00f4 -> B:27:0x0102). Please submit an issue!!! */
        @Override // androidx.paging.PageEvent
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public <R> java.lang.Object flatMap(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Iterable<? extends R>>, ? extends java.lang.Object> r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r20) {
            /*
                Method dump skipped, instructions count: 362
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.flatMap(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @NotNull
        public final LoadType getLoadType() {
            return this.f1512a;
        }

        @Nullable
        public final LoadStates getMediatorLoadStates() {
            return this.f;
        }

        @NotNull
        public final List<TransformablePage<T>> getPages() {
            return this.b;
        }

        public final int getPlaceholdersAfter() {
            return this.d;
        }

        public final int getPlaceholdersBefore() {
            return this.c;
        }

        @NotNull
        public final LoadStates getSourceLoadStates() {
            return this.e;
        }

        public int hashCode() {
            int hashCode = ((((((((this.f1512a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + this.e.hashCode()) * 31;
            LoadStates loadStates = this.f;
            return hashCode + (loadStates == null ? 0 : loadStates.hashCode());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0104  */
        /* JADX WARN: Type inference failed for: r13v9, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r4v9, types: [java.util.Collection] */
        /* JADX WARN: Type inference failed for: r7v10, types: [java.util.Collection] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x008f -> B:18:0x00b0). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00da -> B:24:0x00e2). Please submit an issue!!! */
        @Override // androidx.paging.PageEvent
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public <R> java.lang.Object map(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r19) {
            /*
                Method dump skipped, instructions count: 288
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.map(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @NotNull
        public String toString() {
            return "Insert(loadType=" + this.f1512a + ", pages=" + this.b + ", placeholdersBefore=" + this.c + ", placeholdersAfter=" + this.d + ", sourceLoadStates=" + this.e + ", mediatorLoadStates=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
        }

        @NotNull
        public final <R> Insert<R> transformPages$paging_common(@NotNull Function1<? super List<TransformablePage<T>>, ? extends List<TransformablePage<R>>> transform) {
            Intrinsics.checkNotNullParameter(transform, "transform");
            return new Insert<>(getLoadType(), transform.invoke(getPages()), getPlaceholdersBefore(), getPlaceholdersAfter(), getSourceLoadStates(), getMediatorLoadStates(), null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000*\b\b\u0001\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00010\u0003B\u001b\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0003J%\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\u000b\u001a\u00020\nHÖ\u0001J\t\u0010\r\u001a\u00020\fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0007\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u0019"}, d2 = {"Landroidx/paging/PageEvent$LoadStateUpdate;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/PageEvent;", "Landroidx/paging/LoadStates;", "component1", "component2", "source", "mediator", Constants.COPY_TYPE, "", "toString", "", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Landroidx/paging/LoadStates;", "getSource", "()Landroidx/paging/LoadStates;", "b", "getMediator", "<init>", "(Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class LoadStateUpdate<T> extends PageEvent<T> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LoadStates f1513a;
        @Nullable
        public final LoadStates b;

        public /* synthetic */ LoadStateUpdate(LoadStates loadStates, LoadStates loadStates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadStates, (i & 2) != 0 ? null : loadStates2);
        }

        public static /* synthetic */ LoadStateUpdate copy$default(LoadStateUpdate loadStateUpdate, LoadStates loadStates, LoadStates loadStates2, int i, Object obj) {
            if ((i & 1) != 0) {
                loadStates = loadStateUpdate.f1513a;
            }
            if ((i & 2) != 0) {
                loadStates2 = loadStateUpdate.b;
            }
            return loadStateUpdate.copy(loadStates, loadStates2);
        }

        @NotNull
        public final LoadStates component1() {
            return this.f1513a;
        }

        @Nullable
        public final LoadStates component2() {
            return this.b;
        }

        @NotNull
        public final LoadStateUpdate<T> copy(@NotNull LoadStates source, @Nullable LoadStates loadStates) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new LoadStateUpdate<>(source, loadStates);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LoadStateUpdate) {
                LoadStateUpdate loadStateUpdate = (LoadStateUpdate) obj;
                return Intrinsics.areEqual(this.f1513a, loadStateUpdate.f1513a) && Intrinsics.areEqual(this.b, loadStateUpdate.b);
            }
            return false;
        }

        @Nullable
        public final LoadStates getMediator() {
            return this.b;
        }

        @NotNull
        public final LoadStates getSource() {
            return this.f1513a;
        }

        public int hashCode() {
            int hashCode = this.f1513a.hashCode() * 31;
            LoadStates loadStates = this.b;
            return hashCode + (loadStates == null ? 0 : loadStates.hashCode());
        }

        @NotNull
        public String toString() {
            return "LoadStateUpdate(source=" + this.f1513a + ", mediator=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LoadStateUpdate(@NotNull LoadStates source, @Nullable LoadStates loadStates) {
            super(null);
            Intrinsics.checkNotNullParameter(source, "source");
            this.f1513a = source;
            this.b = loadStates;
        }
    }

    public PageEvent() {
    }

    public /* synthetic */ PageEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ Object a(PageEvent pageEvent, Function2 function2, Continuation continuation) {
        return pageEvent;
    }

    public static /* synthetic */ Object b(PageEvent pageEvent, Function2 function2, Continuation continuation) {
        return pageEvent;
    }

    public static /* synthetic */ Object c(PageEvent pageEvent, Function2 function2, Continuation continuation) {
        return pageEvent;
    }

    @Nullable
    public Object filter(@NotNull Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, @NotNull Continuation<? super PageEvent<T>> continuation) {
        return a(this, function2, continuation);
    }

    @Nullable
    public <R> Object flatMap(@NotNull Function2<? super T, ? super Continuation<? super Iterable<? extends R>>, ? extends Object> function2, @NotNull Continuation<? super PageEvent<R>> continuation) {
        return b(this, function2, continuation);
    }

    @Nullable
    public <R> Object map(@NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super PageEvent<R>> continuation) {
        return c(this, function2, continuation);
    }
}
