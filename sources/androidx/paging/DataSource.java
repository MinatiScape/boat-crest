package androidx.paging;

import androidx.annotation.AnyThread;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.util.Function;
import androidx.exifinterface.media.ExifInterface;
import androidx.paging.DataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.f;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\b&\u0018\u0000 1*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u0006213456B\u0011\b\u0000\u0012\u0006\u0010 \u001a\u00020\u001b¢\u0006\u0004\b/\u00100J>\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00012\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00060\u0005H\u0016J>\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00012\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00060\tH\u0016J2\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005H\u0016J2\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0000\"\b\b\u0002\u0010\u0004*\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0017J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0017J\b\u0010\u0010\u001a\u00020\rH\u0017J'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H @ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0001H ¢\u0006\u0004\b\u0018\u0010\u0019R\u001c\u0010 \u001a\u00020\u001b8\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010&\u001a\u00020!8\u0010@\u0010X\u0090D¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001c\u0010)\u001a\u00020!8\u0010@\u0010X\u0090D¢\u0006\f\n\u0004\b'\u0010#\u001a\u0004\b(\u0010%R\u0016\u0010-\u001a\u00020*8A@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020!8W@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010%\u0082\u0002\u0004\n\u0002\b\u0019¨\u00067"}, d2 = {"Landroidx/paging/DataSource;", "", "Key", "Value", "ToValue", "Landroidx/arch/core/util/Function;", "", "function", "mapByPage", "Lkotlin/Function1;", "map", "Landroidx/paging/DataSource$InvalidatedCallback;", "onInvalidatedCallback", "", "addInvalidatedCallback", "removeInvalidatedCallback", "invalidate", "Landroidx/paging/DataSource$Params;", "params", "Landroidx/paging/DataSource$BaseResult;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", Constants.IAP_ITEM_PARAM, "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal", "Landroidx/paging/DataSource$KeyType;", "a", "Landroidx/paging/DataSource$KeyType;", "getType$paging_common", "()Landroidx/paging/DataSource$KeyType;", "type", "", com.google.android.material.color.c.f10260a, "Z", "isContiguous$paging_common", "()Z", "isContiguous", "d", "getSupportsPageDropping$paging_common", "supportsPageDropping", "", "getInvalidateCallbackCount$paging_common", "()I", "invalidateCallbackCount", "isInvalid", "<init>", "(Landroidx/paging/DataSource$KeyType;)V", "Companion", "BaseResult", "Factory", "InvalidatedCallback", "KeyType", "Params", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public abstract class DataSource<Key, Value> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final KeyType f1479a;
    @NotNull
    public final InvalidateCallbackTracker<InvalidatedCallback> b;
    public final boolean c;
    public final boolean d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJO\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00030\u0005\"\u0004\b\u0002\u0010\u0002\"\u0004\b\u0003\u0010\u00032\u001e\u0010\u0006\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Landroidx/paging/DataSource$Companion;", "", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Landroidx/arch/core/util/Function;", "", "function", "source", "convert$paging_common", "(Landroidx/arch/core/util/Function;Ljava/util/List;)Ljava/util/List;", "convert", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final <A, B> List<B> convert$paging_common(@NotNull Function<List<A>, List<B>> function, @NotNull List<? extends A> source) {
            Intrinsics.checkNotNullParameter(function, "function");
            Intrinsics.checkNotNullParameter(source, "source");
            List<B> dest = function.apply(source);
            if (dest.size() == source.size()) {
                Intrinsics.checkNotNullExpressionValue(dest, "dest");
                return dest;
            }
            throw new IllegalStateException("Invalid Function " + function + " changed return size. This is not supported.");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u0001*\b\b\u0003\u0010\u0003*\u00020\u00012\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0004H&J2\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00040\u0000\"\b\b\u0004\u0010\u0006*\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0007H\u0016J2\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00040\u0000\"\b\b\u0004\u0010\u0006*\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\nH\u0016J>\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00040\u0000\"\b\b\u0004\u0010\u0006*\u00020\u00012\u001e\u0010\b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00040\u000b0\u0007H\u0016J>\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00040\u0000\"\b\b\u0004\u0010\u0006*\u00020\u00012\u001e\u0010\b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00040\u000b0\nH\u0016J$\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00100\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¨\u0006\u0014"}, d2 = {"Landroidx/paging/DataSource$Factory;", "", "Key", "Value", "Landroidx/paging/DataSource;", "create", "ToValue", "Landroidx/arch/core/util/Function;", "function", "map", "Lkotlin/Function1;", "", "mapByPage", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "asPagingSourceFactory", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static abstract class Factory<Key, Value> {

        /* loaded from: classes.dex */
        public static final class a extends Lambda implements Function0<PagingSource<Key, Value>> {
            public final /* synthetic */ CoroutineDispatcher $fetchDispatcher;
            public final /* synthetic */ Factory<Key, Value> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(CoroutineDispatcher coroutineDispatcher, Factory<Key, Value> factory) {
                super(0);
                this.$fetchDispatcher = coroutineDispatcher;
                this.this$0 = factory;
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PagingSource<Key, Value> invoke() {
                return new LegacyPagingSource(this.$fetchDispatcher, this.this$0.create());
            }
        }

        /* loaded from: classes.dex */
        public static final class b<I, O> implements Function {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Function<Value, ToValue> f1481a;

            public b(Function<Value, ToValue> function) {
                this.f1481a = function;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.arch.core.util.Function
            /* renamed from: a */
            public final List<ToValue> apply(List<? extends Value> list) {
                Intrinsics.checkNotNullExpressionValue(list, "list");
                Function<Value, ToValue> function = this.f1481a;
                ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(function.apply(it.next()));
                }
                return arrayList;
            }
        }

        /* loaded from: classes.dex */
        public static final class c<I, O> implements Function {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Function1<Value, ToValue> f1482a;

            /* JADX WARN: Multi-variable type inference failed */
            public c(Function1<? super Value, ? extends ToValue> function1) {
                this.f1482a = function1;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.arch.core.util.Function
            /* renamed from: a */
            public final List<ToValue> apply(List<? extends Value> list) {
                Intrinsics.checkNotNullExpressionValue(list, "list");
                Function1<Value, ToValue> function1 = this.f1482a;
                ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(function1.invoke(it.next()));
                }
                return arrayList;
            }
        }

        /* loaded from: classes.dex */
        public static final class d<I, O> implements Function {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Function1<List<? extends Value>, List<ToValue>> f1483a;

            /* JADX WARN: Multi-variable type inference failed */
            public d(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
                this.f1483a = function1;
            }

            @Override // androidx.arch.core.util.Function
            /* renamed from: a */
            public final List<ToValue> apply(List<? extends Value> it) {
                Function1<List<? extends Value>, List<ToValue>> function1 = this.f1483a;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return (List) function1.invoke(it);
            }
        }

        public static /* synthetic */ Function0 asPagingSourceFactory$default(Factory factory, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    coroutineDispatcher = Dispatchers.getIO();
                }
                return factory.asPagingSourceFactory(coroutineDispatcher);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: asPagingSourceFactory");
        }

        @JvmOverloads
        @NotNull
        public final Function0<PagingSource<Key, Value>> asPagingSourceFactory() {
            return asPagingSourceFactory$default(this, null, 1, null);
        }

        @JvmOverloads
        @NotNull
        public final Function0<PagingSource<Key, Value>> asPagingSourceFactory(@NotNull CoroutineDispatcher fetchDispatcher) {
            Intrinsics.checkNotNullParameter(fetchDispatcher, "fetchDispatcher");
            return new SuspendingPagingSourceFactory(fetchDispatcher, new a(fetchDispatcher, this));
        }

        @NotNull
        public abstract DataSource<Key, Value> create();

        @NotNull
        public <ToValue> Factory<Key, ToValue> map(@NotNull Function<Value, ToValue> function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return mapByPage(new b(function));
        }

        @NotNull
        public <ToValue> Factory<Key, ToValue> mapByPage(@NotNull final Function<List<Value>, List<ToValue>> function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return new Factory<Key, ToValue>(this) { // from class: androidx.paging.DataSource$Factory$mapByPage$1

                /* renamed from: a  reason: collision with root package name */
                public final /* synthetic */ DataSource.Factory<Key, Value> f1484a;

                {
                    this.f1484a = this;
                }

                @Override // androidx.paging.DataSource.Factory
                @NotNull
                public DataSource<Key, ToValue> create() {
                    return this.f1484a.create().mapByPage((Function) function);
                }
            };
        }

        public /* synthetic */ Factory map(Function1 function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return mapByPage(new c(function));
        }

        public /* synthetic */ Factory mapByPage(Function1 function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return mapByPage(new d(function));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H'¨\u0006\u0004"}, d2 = {"Landroidx/paging/DataSource$InvalidatedCallback;", "", "", "onInvalidated", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public interface InvalidatedCallback {
        @AnyThread
        void onInvalidated();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/paging/DataSource$KeyType;", "", "<init>", "(Ljava/lang/String;I)V", "POSITIONAL", "PAGE_KEYED", "ITEM_KEYED", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public enum KeyType {
        POSITIONAL,
        PAGE_KEYED,
        ITEM_KEYED
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001B3\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u000e\u0012\u0006\u0010\u0019\u001a\u00020\u0014\u0012\u0006\u0010\u001c\u001a\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u001eR\u001c\u0010\b\u001a\u00020\u00038\u0000@\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\r\u001a\u0004\u0018\u00018\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0013\u001a\u00020\u000e8\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0019\u001a\u00020\u00148\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u001c\u001a\u00020\u000e8\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u0012¨\u0006\u001f"}, d2 = {"Landroidx/paging/DataSource$Params;", "", "K", "Landroidx/paging/LoadType;", "a", "Landroidx/paging/LoadType;", "getType$paging_common", "()Landroidx/paging/LoadType;", "type", "b", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", com.clevertap.android.sdk.Constants.KEY_KEY, "", com.google.android.material.color.c.f10260a, "I", "getInitialLoadSize", "()I", "initialLoadSize", "", "d", "Z", "getPlaceholdersEnabled", "()Z", "placeholdersEnabled", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getPageSize", "pageSize", "<init>", "(Landroidx/paging/LoadType;Ljava/lang/Object;IZI)V", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class Params<K> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LoadType f1485a;
        @Nullable
        public final K b;
        public final int c;
        public final boolean d;
        public final int e;

        public Params(@NotNull LoadType type, @Nullable K k, int i, boolean z, int i2) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.f1485a = type;
            this.b = k;
            this.c = i;
            this.d = z;
            this.e = i2;
            if (type != LoadType.REFRESH && k == null) {
                throw new IllegalArgumentException("Key must be non-null for prepend/append");
            }
        }

        public final int getInitialLoadSize() {
            return this.c;
        }

        @Nullable
        public final K getKey() {
            return this.b;
        }

        public final int getPageSize() {
            return this.e;
        }

        public final boolean getPlaceholdersEnabled() {
            return this.d;
        }

        @NotNull
        public final LoadType getType$paging_common() {
            return this.f1485a;
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends Lambda implements Function1<InvalidatedCallback, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InvalidatedCallback invalidatedCallback) {
            invoke2(invalidatedCallback);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull InvalidatedCallback it) {
            Intrinsics.checkNotNullParameter(it, "it");
            it.onInvalidated();
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends Lambda implements Function0<Boolean> {
        public final /* synthetic */ DataSource<Key, Value> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DataSource<Key, Value> dataSource) {
            super(0);
            this.this$0 = dataSource;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Boolean invoke() {
            return Boolean.valueOf(this.this$0.isInvalid());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [ToValue] */
    /* loaded from: classes.dex */
    public static final class c<ToValue> extends Lambda implements Function1<List<? extends Value>, List<? extends ToValue>> {
        public final /* synthetic */ Function<Value, ToValue> $function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Function<Value, ToValue> function) {
            super(1);
            this.$function = function;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke((List) ((List) obj));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final List<ToValue> invoke(@NotNull List<? extends Value> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            Function<Value, ToValue> function = this.$function;
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(function.apply(it.next()));
            }
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class d<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<Value, ToValue> f1486a;

        /* JADX WARN: Multi-variable type inference failed */
        public d(Function1<? super Value, ? extends ToValue> function1) {
            this.f1486a = function1;
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [ToValue, java.lang.Object] */
        @Override // androidx.arch.core.util.Function
        public final ToValue apply(Value it) {
            Function1<Value, ToValue> function1 = this.f1486a;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            return function1.invoke(it);
        }
    }

    /* loaded from: classes.dex */
    public static final class e<I, O> implements Function {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<List<? extends Value>, List<ToValue>> f1487a;

        /* JADX WARN: Multi-variable type inference failed */
        public e(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
            this.f1487a = function1;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public final List<ToValue> apply(List<? extends Value> it) {
            Function1<List<? extends Value>, List<ToValue>> function1 = this.f1487a;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            return (List) function1.invoke(it);
        }
    }

    public DataSource(@NotNull KeyType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.f1479a = type;
        this.b = new InvalidateCallbackTracker<>(a.INSTANCE, new b(this));
        this.c = true;
        this.d = true;
    }

    @AnyThread
    public void addInvalidatedCallback(@NotNull InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.b.registerInvalidatedCallback$paging_common(onInvalidatedCallback);
    }

    @VisibleForTesting
    public final int getInvalidateCallbackCount$paging_common() {
        return this.b.callbackCount$paging_common();
    }

    @NotNull
    public abstract Key getKeyInternal$paging_common(@NotNull Value value);

    public boolean getSupportsPageDropping$paging_common() {
        return this.d;
    }

    @NotNull
    public final KeyType getType$paging_common() {
        return this.f1479a;
    }

    @AnyThread
    public void invalidate() {
        this.b.invalidate$paging_common();
    }

    public boolean isContiguous$paging_common() {
        return this.c;
    }

    @WorkerThread
    public boolean isInvalid() {
        return this.b.getInvalid$paging_common();
    }

    @Nullable
    public abstract Object load$paging_common(@NotNull Params<Key> params, @NotNull Continuation<? super BaseResult<Value>> continuation);

    @NotNull
    public <ToValue> DataSource<Key, ToValue> map(@NotNull Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage(new c(function));
    }

    @NotNull
    public <ToValue> DataSource<Key, ToValue> mapByPage(@NotNull Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperDataSource(this, function);
    }

    @AnyThread
    public void removeInvalidatedCallback(@NotNull InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.b.unregisterInvalidatedCallback$paging_common(onInvalidatedCallback);
    }

    public /* synthetic */ DataSource map(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return map(new d(function));
    }

    public /* synthetic */ DataSource mapByPage(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage(new e(function));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\b\u0000\u0018\u0000 !*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0001:\u0001!B?\b\u0000\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00020\f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002R\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00020\f8\u0006@\u0007X\u0087\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00018\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0016\u001a\u0004\u0018\u00010\u00018\u0006@\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012R\u0019\u0010\u001b\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001e\u001a\u00020\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001d\u0010\u001a¨\u0006\""}, d2 = {"Landroidx/paging/DataSource$BaseResult;", "", "Value", "", "pageSize", "", "validateForInitialTiling$paging_common", "(I)V", "validateForInitialTiling", FitnessActivities.OTHER, "", "equals", "", "data", "Ljava/util/List;", "a", "Ljava/lang/Object;", "getPrevKey", "()Ljava/lang/Object;", "prevKey", "b", "getNextKey", "nextKey", com.google.android.material.color.c.f10260a, "I", "getItemsBefore", "()I", "itemsBefore", "d", "getItemsAfter", "itemsAfter", "<init>", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)V", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public static final class BaseResult<Value> {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final Object f1480a;
        @Nullable
        public final Object b;
        public final int c;
        public final int d;
        @JvmField
        @NotNull
        public final List<Value> data;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00030\u0003\"\b\b\u0003\u0010\u0002*\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005JW\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00040\u0003\"\b\b\u0003\u0010\u0007*\u00020\u0001\"\b\b\u0004\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00030\u00032\u001e\u0010\f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00040\u000b0\nH\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Landroidx/paging/DataSource$BaseResult$Companion;", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/paging/DataSource$BaseResult;", "empty$paging_common", "()Landroidx/paging/DataSource$BaseResult;", "empty", "ToValue", "Value", "result", "Landroidx/arch/core/util/Function;", "", "function", "convert$paging_common", "(Landroidx/paging/DataSource$BaseResult;Landroidx/arch/core/util/Function;)Landroidx/paging/DataSource$BaseResult;", "convert", "<init>", "()V", "paging-common"}, k = 1, mv = {1, 5, 1})
        /* loaded from: classes.dex */
        public static final class Companion {
            public Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final <ToValue, Value> BaseResult<Value> convert$paging_common(@NotNull BaseResult<ToValue> result, @NotNull Function<List<ToValue>, List<Value>> function) {
                Intrinsics.checkNotNullParameter(result, "result");
                Intrinsics.checkNotNullParameter(function, "function");
                return new BaseResult<>(DataSource.Companion.convert$paging_common(function, result.data), result.getPrevKey(), result.getNextKey(), result.getItemsBefore(), result.getItemsAfter());
            }

            @NotNull
            public final <T> BaseResult<T> empty$paging_common() {
                return new BaseResult<>(CollectionsKt__CollectionsKt.emptyList(), null, null, 0, 0);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BaseResult(@NotNull List<? extends Value> data, @Nullable Object obj, @Nullable Object obj2, int i, int i2) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
            this.f1480a = obj;
            this.b = obj2;
            this.c = i;
            this.d = i2;
            if (i < 0 && i != Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Position must be non-negative");
            }
            if (data.isEmpty() && (i > 0 || i2 > 0)) {
                throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
            }
            if (i2 < 0 && i2 != Integer.MIN_VALUE) {
                throw new IllegalArgumentException("List size + position too large, last item in list beyond totalCount.");
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof BaseResult) {
                BaseResult baseResult = (BaseResult) obj;
                return Intrinsics.areEqual(this.data, baseResult.data) && Intrinsics.areEqual(this.f1480a, baseResult.f1480a) && Intrinsics.areEqual(this.b, baseResult.b) && this.c == baseResult.c && this.d == baseResult.d;
            }
            return false;
        }

        public final int getItemsAfter() {
            return this.d;
        }

        public final int getItemsBefore() {
            return this.c;
        }

        @Nullable
        public final Object getNextKey() {
            return this.b;
        }

        @Nullable
        public final Object getPrevKey() {
            return this.f1480a;
        }

        public final void validateForInitialTiling$paging_common(int i) {
            int i2;
            if (this.c != Integer.MIN_VALUE && (i2 = this.d) != Integer.MIN_VALUE) {
                if (i2 > 0 && this.data.size() % i != 0) {
                    throw new IllegalArgumentException("PositionalDataSource requires initial load size to be a multiple of page size to support internal tiling. loadSize " + this.data.size() + ", position " + this.c + ", totalCount " + (this.c + this.data.size() + this.d) + ", pageSize " + i);
                } else if (this.c % i == 0) {
                    return;
                } else {
                    throw new IllegalArgumentException("Initial load must be pageSize aligned.Position = " + this.c + ", pageSize = " + i);
                }
            }
            throw new IllegalStateException("Placeholders requested, but totalCount not provided. Please call the three-parameter onResult method, or disable placeholders in the PagedList.Config");
        }

        public /* synthetic */ BaseResult(List list, Object obj, Object obj2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, obj, obj2, (i3 & 8) != 0 ? Integer.MIN_VALUE : i, (i3 & 16) != 0 ? Integer.MIN_VALUE : i2);
        }
    }
}
