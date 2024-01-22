package androidx.paging;

import androidx.annotation.IntRange;
import androidx.annotation.VisibleForTesting;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.common.net.HttpHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u0002!\"B\u0007¢\u0006\u0004\b\u001f\u0010 J\u0006\u0010\u0005\u001a\u00020\u0004J\u0014\u0010\b\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006J\u0014\u0010\t\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006J-\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH¦@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH&¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0016\u001a\u00020\u00138A@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u001a\u001a\u00020\u00178V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001c\u001a\u00020\u00178V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\u001e\u001a\u00020\u00178F@\u0006¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Landroidx/paging/PagingSource;", "", "Key", "Value", "", "invalidate", "Lkotlin/Function0;", "onInvalidatedCallback", "registerInvalidatedCallback", "unregisterInvalidatedCallback", "Landroidx/paging/PagingSource$LoadParams;", "params", "Landroidx/paging/PagingSource$LoadResult;", "load", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PagingState;", "state", "getRefreshKey", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "", "getInvalidateCallbackCount$paging_common", "()I", "invalidateCallbackCount", "", "getJumpingSupported", "()Z", "jumpingSupported", "getKeyReuseSupported", "keyReuseSupported", "getInvalid", "invalid", "<init>", "()V", "LoadParams", "LoadResult", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class PagingSource<Key, Value> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final InvalidateCallbackTracker<Function0<Unit>> f1535a = new InvalidateCallbackTracker<>(a.INSTANCE, null, 2, null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0014*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001:\u0004\u0015\u0014\u0016\u0017B\u0019\b\u0004\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\b\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0011\u001a\u0004\u0018\u00018\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0003\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Landroidx/paging/PagingSource$LoadParams;", "", "Key", "", "a", "I", "getLoadSize", "()I", "loadSize", "", "b", "Z", "getPlaceholdersEnabled", "()Z", "placeholdersEnabled", "getKey", "()Ljava/lang/Object;", Constants.KEY_KEY, "<init>", "(IZ)V", "Companion", "Append", "Prepend", HttpHeaders.REFRESH, "Landroidx/paging/PagingSource$LoadParams$Refresh;", "Landroidx/paging/PagingSource$LoadParams$Append;", "Landroidx/paging/PagingSource$LoadParams$Prepend;", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadParams<Key> {
        @NotNull
        public static final Companion Companion = new Companion(null);

        /* renamed from: a  reason: collision with root package name */
        public final int f1536a;
        public final boolean b;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000*\b\b\u0003\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00030\u0003B\u001f\u0012\u0006\u0010\b\u001a\u00028\u0003\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\b\u001a\u00028\u00038\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagingSource$LoadParams$Append;", "", "Key", "Landroidx/paging/PagingSource$LoadParams;", com.google.android.material.color.c.f10260a, "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", Constants.KEY_KEY, "", "loadSize", "", "placeholdersEnabled", "<init>", "(Ljava/lang/Object;IZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Append<Key> extends LoadParams<Key> {
            @NotNull
            public final Key c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Append(@NotNull Key key, int i, boolean z) {
                super(i, z, null);
                Intrinsics.checkNotNullParameter(key, "key");
                this.c = key;
            }

            @Override // androidx.paging.PagingSource.LoadParams
            @NotNull
            public Key getKey() {
                return this.c;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ?\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00030\n\"\b\b\u0003\u0010\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00018\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagingSource$LoadParams$Companion;", "", "Key", "Landroidx/paging/LoadType;", "loadType", Constants.KEY_KEY, "", "loadSize", "", "placeholdersEnabled", "Landroidx/paging/PagingSource$LoadParams;", "create", "(Landroidx/paging/LoadType;Ljava/lang/Object;IZ)Landroidx/paging/PagingSource$LoadParams;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Companion {

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

            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final <Key> LoadParams<Key> create(@NotNull LoadType loadType, @Nullable Key key, int i, boolean z) {
                Intrinsics.checkNotNullParameter(loadType, "loadType");
                int i2 = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (key != null) {
                            return new Prepend(key, i, z);
                        }
                        throw new IllegalArgumentException("key cannot be null for prepend".toString());
                    } else if (i2 == 3) {
                        if (key != null) {
                            return new Append(key, i, z);
                        }
                        throw new IllegalArgumentException("key cannot be null for append".toString());
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                return new Refresh(key, i, z);
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000*\b\b\u0003\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00030\u0003B\u001f\u0012\u0006\u0010\b\u001a\u00028\u0003\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\b\u001a\u00028\u00038\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagingSource$LoadParams$Prepend;", "", "Key", "Landroidx/paging/PagingSource$LoadParams;", com.google.android.material.color.c.f10260a, "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", Constants.KEY_KEY, "", "loadSize", "", "placeholdersEnabled", "<init>", "(Ljava/lang/Object;IZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Prepend<Key> extends LoadParams<Key> {
            @NotNull
            public final Key c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Prepend(@NotNull Key key, int i, boolean z) {
                super(i, z, null);
                Intrinsics.checkNotNullParameter(key, "key");
                this.c = key;
            }

            @Override // androidx.paging.PagingSource.LoadParams
            @NotNull
            public Key getKey() {
                return this.c;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000*\b\b\u0003\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00030\u0003B!\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0003\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eR\u001e\u0010\b\u001a\u0004\u0018\u00018\u00038\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/paging/PagingSource$LoadParams$Refresh;", "", "Key", "Landroidx/paging/PagingSource$LoadParams;", com.google.android.material.color.c.f10260a, "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", Constants.KEY_KEY, "", "loadSize", "", "placeholdersEnabled", "<init>", "(Ljava/lang/Object;IZ)V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Refresh<Key> extends LoadParams<Key> {
            @Nullable
            public final Key c;

            public Refresh(@Nullable Key key, int i, boolean z) {
                super(i, z, null);
                this.c = key;
            }

            @Override // androidx.paging.PagingSource.LoadParams
            @Nullable
            public Key getKey() {
                return this.c;
            }
        }

        public LoadParams(int i, boolean z) {
            this.f1536a = i;
            this.b = z;
        }

        public /* synthetic */ LoadParams(int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, z);
        }

        @Nullable
        public abstract Key getKey();

        public final int getLoadSize() {
            return this.f1536a;
        }

        public final boolean getPlaceholdersEnabled() {
            return this.b;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\b\b\u0003\u0010\u0003*\u00020\u00012\u00020\u0001:\u0003\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Landroidx/paging/PagingSource$LoadResult;", "", "Key", "Value", "<init>", "()V", ErrorConstants.GENERIC_ERROR, "Invalid", "Page", "Landroidx/paging/PagingSource$LoadResult$Error;", "Landroidx/paging/PagingSource$LoadResult$Invalid;", "Landroidx/paging/PagingSource$LoadResult$Page;", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class LoadResult<Key, Value> {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000*\b\b\u0004\u0010\u0002*\u00020\u0001*\b\b\u0005\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0004B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0006\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0007\u001a\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/paging/PagingSource$LoadResult$Error;", "", "Key", "Value", "Landroidx/paging/PagingSource$LoadResult;", "", "component1", "throwable", Constants.COPY_TYPE, "", "toString", "", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/Throwable;", "getThrowable", "()Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Error<Key, Value> extends LoadResult<Key, Value> {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final Throwable f1537a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(@NotNull Throwable throwable) {
                super(null);
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                this.f1537a = throwable;
            }

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = error.f1537a;
                }
                return error.copy(th);
            }

            @NotNull
            public final Throwable component1() {
                return this.f1537a;
            }

            @NotNull
            public final Error<Key, Value> copy(@NotNull Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return new Error<>(throwable);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Error) && Intrinsics.areEqual(this.f1537a, ((Error) obj).f1537a);
            }

            @NotNull
            public final Throwable getThrowable() {
                return this.f1537a;
            }

            public int hashCode() {
                return this.f1537a.hashCode();
            }

            @NotNull
            public String toString() {
                return "Error(throwable=" + this.f1537a + HexStringBuilder.COMMENT_END_CHAR;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0004\u0010\u0002*\u00020\u0001*\b\b\u0005\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/paging/PagingSource$LoadResult$Invalid;", "", "Key", "Value", "Landroidx/paging/PagingSource$LoadResult;", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Invalid<Key, Value> extends LoadResult<Key, Value> {
            public Invalid() {
                super(null);
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\b\u0018\u0000 ,*\b\b\u0004\u0010\u0002*\u00020\u0001*\b\b\u0005\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0004:\u0001,B=\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00050\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00018\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00018\u0004\u0012\b\b\u0003\u0010\u0010\u001a\u00020\n\u0012\b\b\u0003\u0010\u0011\u001a\u00020\n¢\u0006\u0004\b)\u0010*B+\b\u0016\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00050\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00018\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00018\u0004¢\u0006\u0004\b)\u0010+J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00050\u0005HÆ\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0004HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00018\u0004HÆ\u0003¢\u0006\u0004\b\t\u0010\bJ\t\u0010\u000b\u001a\u00020\nHÆ\u0003J\t\u0010\f\u001a\u00020\nHÆ\u0003JX\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00050\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00018\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00018\u00042\b\b\u0003\u0010\u0010\u001a\u00020\n2\b\b\u0003\u0010\u0011\u001a\u00020\nHÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0016\u001a\u00020\nHÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00050\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u000e\u001a\u0004\u0018\u00018\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\bR\u001b\u0010\u000f\u001a\u0004\u0018\u00018\u00048\u0006@\u0006¢\u0006\f\n\u0004\b!\u0010\u001f\u001a\u0004\b\"\u0010\bR\u0019\u0010\u0010\u001a\u00020\n8\u0006@\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u0011\u001a\u00020\n8\u0006@\u0006¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b(\u0010&¨\u0006-"}, d2 = {"Landroidx/paging/PagingSource$LoadResult$Page;", "", "Key", "Value", "Landroidx/paging/PagingSource$LoadResult;", "", "component1", "component2", "()Ljava/lang/Object;", "component3", "", "component4", "component5", "data", "prevKey", "nextKey", "itemsBefore", "itemsAfter", Constants.COPY_TYPE, "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)Landroidx/paging/PagingSource$LoadResult$Page;", "", "toString", "hashCode", FitnessActivities.OTHER, "", "equals", "a", "Ljava/util/List;", "getData", "()Ljava/util/List;", "b", "Ljava/lang/Object;", "getPrevKey", com.google.android.material.color.c.f10260a, "getNextKey", "d", "I", "getItemsBefore", "()I", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getItemsAfter", "<init>", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)V", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Page<Key, Value> extends LoadResult<Key, Value> {
            public static final int COUNT_UNDEFINED = Integer.MIN_VALUE;
            @NotNull
            public static final Companion Companion = new Companion(null);
            @NotNull
            public static final Page f = new Page(CollectionsKt__CollectionsKt.emptyList(), null, null, 0, 0);
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final List<Value> f1538a;
            @Nullable
            public final Key b;
            @Nullable
            public final Key c;
            public final int d;
            public final int e;

            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\rJ/\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u00070\u0004\"\b\b\u0006\u0010\u0002*\u00020\u0001\"\b\b\u0007\u0010\u0003*\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006R.\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00048\u0000@\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\t\u0010\n\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000b\u0010\u0006R\u0016\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/paging/PagingSource$LoadResult$Page$Companion;", "", "Key", "Value", "Landroidx/paging/PagingSource$LoadResult$Page;", "empty$paging_common", "()Landroidx/paging/PagingSource$LoadResult$Page;", "empty", "", "EMPTY", "Landroidx/paging/PagingSource$LoadResult$Page;", "getEMPTY$paging_common", "getEMPTY$paging_common$annotations", "()V", "", "COUNT_UNDEFINED", "I", "<init>", "paging-common"}, k = 1, mv = {1, 5, 1})
            /* loaded from: classes.dex */
            public static final class Companion {
                public Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                public static /* synthetic */ void getEMPTY$paging_common$annotations() {
                }

                @NotNull
                public final <Key, Value> Page<Key, Value> empty$paging_common() {
                    return getEMPTY$paging_common();
                }

                @NotNull
                public final Page getEMPTY$paging_common() {
                    return Page.f;
                }
            }

            public /* synthetic */ Page(List list, Object obj, Object obj2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(list, obj, obj2, (i3 & 8) != 0 ? Integer.MIN_VALUE : i, (i3 & 16) != 0 ? Integer.MIN_VALUE : i2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Page copy$default(Page page, List list, Object obj, Object obj2, int i, int i2, int i3, Object obj3) {
                List<Value> list2 = list;
                if ((i3 & 1) != 0) {
                    list2 = page.f1538a;
                }
                Key key = obj;
                if ((i3 & 2) != 0) {
                    key = page.b;
                }
                Key key2 = key;
                Key key3 = obj2;
                if ((i3 & 4) != 0) {
                    key3 = page.c;
                }
                Key key4 = key3;
                if ((i3 & 8) != 0) {
                    i = page.d;
                }
                int i4 = i;
                if ((i3 & 16) != 0) {
                    i2 = page.e;
                }
                return page.copy(list2, key2, key4, i4, i2);
            }

            @NotNull
            public final List<Value> component1() {
                return this.f1538a;
            }

            @Nullable
            public final Key component2() {
                return this.b;
            }

            @Nullable
            public final Key component3() {
                return this.c;
            }

            public final int component4() {
                return this.d;
            }

            public final int component5() {
                return this.e;
            }

            @NotNull
            public final Page<Key, Value> copy(@NotNull List<? extends Value> data, @Nullable Key key, @Nullable Key key2, @IntRange(from = -2147483648L) int i, @IntRange(from = -2147483648L) int i2) {
                Intrinsics.checkNotNullParameter(data, "data");
                return new Page<>(data, key, key2, i, i2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof Page) {
                    Page page = (Page) obj;
                    return Intrinsics.areEqual(this.f1538a, page.f1538a) && Intrinsics.areEqual(this.b, page.b) && Intrinsics.areEqual(this.c, page.c) && this.d == page.d && this.e == page.e;
                }
                return false;
            }

            @NotNull
            public final List<Value> getData() {
                return this.f1538a;
            }

            public final int getItemsAfter() {
                return this.e;
            }

            public final int getItemsBefore() {
                return this.d;
            }

            @Nullable
            public final Key getNextKey() {
                return this.c;
            }

            @Nullable
            public final Key getPrevKey() {
                return this.b;
            }

            public int hashCode() {
                int hashCode = this.f1538a.hashCode() * 31;
                Key key = this.b;
                int hashCode2 = (hashCode + (key == null ? 0 : key.hashCode())) * 31;
                Key key2 = this.c;
                return ((((hashCode2 + (key2 != null ? key2.hashCode() : 0)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
            }

            @NotNull
            public String toString() {
                return "Page(data=" + this.f1538a + ", prevKey=" + this.b + ", nextKey=" + this.c + ", itemsBefore=" + this.d + ", itemsAfter=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Page(@NotNull List<? extends Value> data, @Nullable Key key, @Nullable Key key2, @IntRange(from = -2147483648L) int i, @IntRange(from = -2147483648L) int i2) {
                super(null);
                Intrinsics.checkNotNullParameter(data, "data");
                this.f1538a = data;
                this.b = key;
                this.c = key2;
                this.d = i;
                this.e = i2;
                boolean z = false;
                if (!(i == Integer.MIN_VALUE || i >= 0)) {
                    throw new IllegalArgumentException("itemsBefore cannot be negative".toString());
                }
                if (!((i2 == Integer.MIN_VALUE || i2 >= 0) ? true : z)) {
                    throw new IllegalArgumentException("itemsAfter cannot be negative".toString());
                }
            }

            /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
            public Page(@NotNull List<? extends Value> data, @Nullable Key key, @Nullable Key key2) {
                this(data, key, key2, Integer.MIN_VALUE, Integer.MIN_VALUE);
                Intrinsics.checkNotNullParameter(data, "data");
            }
        }

        public LoadResult() {
        }

        public /* synthetic */ LoadResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<Function0<? extends Unit>, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0) {
            invoke2((Function0<Unit>) function0);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Function0<Unit> it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.invoke();
        }
    }

    public final boolean getInvalid() {
        return this.f1535a.getInvalid$paging_common();
    }

    @VisibleForTesting
    public final int getInvalidateCallbackCount$paging_common() {
        return this.f1535a.callbackCount$paging_common();
    }

    public boolean getJumpingSupported() {
        return false;
    }

    public boolean getKeyReuseSupported() {
        return false;
    }

    @Nullable
    public abstract Key getRefreshKey(@NotNull PagingState<Key, Value> pagingState);

    public final void invalidate() {
        this.f1535a.invalidate$paging_common();
    }

    @Nullable
    public abstract Object load(@NotNull LoadParams<Key> loadParams, @NotNull Continuation<? super LoadResult<Key, Value>> continuation);

    public final void registerInvalidatedCallback(@NotNull Function0<Unit> onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.f1535a.registerInvalidatedCallback$paging_common(onInvalidatedCallback);
    }

    public final void unregisterInvalidatedCallback(@NotNull Function0<Unit> onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.f1535a.unregisterInvalidatedCallback$paging_common(onInvalidatedCallback);
    }
}
