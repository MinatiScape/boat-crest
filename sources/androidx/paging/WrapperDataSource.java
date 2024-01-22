package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.IdentityHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u0001*\b\b\u0002\u0010\u0004*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0005B;\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u001e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00100\u001d¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0017\u0010\u000f\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0002H\u0010¢\u0006\u0004\b\r\u0010\u000eJ\"\u0010\u0013\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00020\u0010J'\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00020\u00162\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0090@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001b\u001a\u00020\u001a8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Landroidx/paging/WrapperDataSource;", "", "Key", "ValueFrom", "ValueTo", "Landroidx/paging/DataSource;", "Landroidx/paging/DataSource$InvalidatedCallback;", "onInvalidatedCallback", "", "addInvalidatedCallback", "removeInvalidatedCallback", "invalidate", Constants.IAP_ITEM_PARAM, "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal", "", "source", "dest", "stashKeysIfNeeded", "Landroidx/paging/DataSource$Params;", "params", "Landroidx/paging/DataSource$BaseResult;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "", "isInvalid", "()Z", "Landroidx/arch/core/util/Function;", "listFunction", "<init>", "(Landroidx/paging/DataSource;Landroidx/arch/core/util/Function;)V", "paging-common"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes.dex */
public class WrapperDataSource<Key, ValueFrom, ValueTo> extends DataSource<Key, ValueTo> {
    @NotNull
    public final DataSource<Key, ValueFrom> e;
    @NotNull
    public final Function<List<ValueFrom>, List<ValueTo>> f;
    @Nullable
    public final IdentityHashMap<ValueTo, Key> g;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.KeyType.values().length];
            iArr[DataSource.KeyType.ITEM_KEYED.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "androidx.paging.WrapperDataSource", f = "WrapperDataSource.kt", i = {0}, l = {68}, m = "load$suspendImpl", n = {"this"}, s = {"L$0"})
    /* loaded from: classes.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;
        public final /* synthetic */ WrapperDataSource<Key, ValueFrom, ValueTo> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WrapperDataSource<Key, ValueFrom, ValueTo> wrapperDataSource, Continuation<? super a> continuation) {
            super(continuation);
            this.this$0 = wrapperDataSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WrapperDataSource.a(this.this$0, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrapperDataSource(@NotNull DataSource<Key, ValueFrom> source, @NotNull Function<List<ValueFrom>, List<ValueTo>> listFunction) {
        super(source.getType$paging_common());
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(listFunction, "listFunction");
        this.e = source;
        this.f = listFunction;
        this.g = WhenMappings.$EnumSwitchMapping$0[source.getType$paging_common().ordinal()] == 1 ? new IdentityHashMap<>() : null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object a(androidx.paging.WrapperDataSource r4, androidx.paging.DataSource.Params r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof androidx.paging.WrapperDataSource.a
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.WrapperDataSource$a r0 = (androidx.paging.WrapperDataSource.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.WrapperDataSource$a r0 = new androidx.paging.WrapperDataSource$a
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            androidx.paging.WrapperDataSource r4 = (androidx.paging.WrapperDataSource) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.DataSource<Key, ValueFrom> r6 = r4.e
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.load$paging_common(r5, r0)
            if (r6 != r1) goto L45
            return r1
        L45:
            androidx.paging.DataSource$BaseResult r6 = (androidx.paging.DataSource.BaseResult) r6
            androidx.paging.DataSource$BaseResult$Companion r5 = androidx.paging.DataSource.BaseResult.Companion
            androidx.arch.core.util.Function<java.util.List<ValueFrom>, java.util.List<ValueTo>> r0 = r4.f
            androidx.paging.DataSource$BaseResult r5 = r5.convert$paging_common(r6, r0)
            java.util.List<Value> r6 = r6.data
            java.util.List<Value> r0 = r5.data
            r4.stashKeysIfNeeded(r6, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.WrapperDataSource.a(androidx.paging.WrapperDataSource, androidx.paging.DataSource$Params, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.paging.DataSource
    public void addInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.e.addInvalidatedCallback(onInvalidatedCallback);
    }

    @Override // androidx.paging.DataSource
    @NotNull
    public Key getKeyInternal$paging_common(@NotNull ValueTo item) {
        Key key;
        Intrinsics.checkNotNullParameter(item, "item");
        IdentityHashMap<ValueTo, Key> identityHashMap = this.g;
        if (identityHashMap != null) {
            synchronized (identityHashMap) {
                key = this.g.get(item);
                Intrinsics.checkNotNull(key);
                Intrinsics.checkNotNullExpressionValue(key, "keyMap[item]!!");
            }
            return key;
        }
        throw new IllegalStateException("Cannot get key by item in non-item keyed DataSource");
    }

    @Override // androidx.paging.DataSource
    public void invalidate() {
        this.e.invalidate();
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        return this.e.isInvalid();
    }

    @Override // androidx.paging.DataSource
    @Nullable
    public Object load$paging_common(@NotNull DataSource.Params<Key> params, @NotNull Continuation<? super DataSource.BaseResult<ValueTo>> continuation) {
        return a(this, params, continuation);
    }

    @Override // androidx.paging.DataSource
    public void removeInvalidatedCallback(@NotNull DataSource.InvalidatedCallback onInvalidatedCallback) {
        Intrinsics.checkNotNullParameter(onInvalidatedCallback, "onInvalidatedCallback");
        this.e.removeInvalidatedCallback(onInvalidatedCallback);
    }

    public final void stashKeysIfNeeded(@NotNull List<? extends ValueFrom> source, @NotNull List<? extends ValueTo> dest) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(dest, "dest");
        IdentityHashMap<ValueTo, Key> identityHashMap = this.g;
        if (identityHashMap != null) {
            synchronized (identityHashMap) {
                int i = 0;
                int size = dest.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = i + 1;
                        ((IdentityHashMap<ValueTo, Key>) this.g).put(dest.get(i), ((ItemKeyedDataSource) this.e).getKey(source.get(i)));
                        if (i2 > size) {
                            break;
                        }
                        i = i2;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }
}
