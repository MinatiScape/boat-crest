package com.coveiot.khjstyledb.gps;

import android.content.Context;
import com.coveiot.khjstyledb.KHJstyleAppDatabase;
import com.coveiot.khjstyledb.SingletonHolder;
import com.coveiot.khjstyledb.gps.model.KHSessionGPSData;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0011\b\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007JB\u0010\u0010\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e\u0018\u00010\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bR\u0019\u0010\u0016\u001a\u00020\u00118\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/coveiot/khjstyledb/gps/KHJstyleGPSRepository;", "", "", "Lcom/coveiot/khjstyledb/gps/KHJstyleSessionGPSData;", "sessionHeartRateDataList", "", "insertSessionGPSData", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "startTime", "endTime", "", "serialNo", "", "Lcom/coveiot/khjstyledb/gps/model/KHSessionGPSData;", "kotlin.jvm.PlatformType", "getGPSDataListBetweenTime", "Landroid/content/Context;", "a", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHJstyleGPSRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7122a;
    @NotNull
    public KHJstyleGPSDataDao b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/khjstyledb/gps/KHJstyleGPSRepository$Companion;", "Lcom/coveiot/khjstyledb/SingletonHolder;", "Lcom/coveiot/khjstyledb/gps/KHJstyleGPSRepository;", "Landroid/content/Context;", "<init>", "()V", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHJstyleGPSRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHJstyleGPSRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHJstyleGPSRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHJstyleGPSRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHJstyleGPSRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.khjstyledb.gps.KHJstyleGPSRepository$insertSessionGPSData$2", f = "KHJstyleGPSRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<KHJstyleSessionGPSData> $sessionHeartRateDataList;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(List<? extends KHJstyleSessionGPSData> list, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$sessionHeartRateDataList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$sessionHeartRateDataList, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                KHJstyleGPSRepository.this.b.insertAll(this.$sessionHeartRateDataList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public KHJstyleGPSRepository(Context context) {
        this.f7122a = context;
        KHJstyleGPSDataDao gpsDataDao = KHJstyleAppDatabase.getAppDatabase(context).gpsDataDao();
        Intrinsics.checkNotNullExpressionValue(gpsDataDao, "getAppDatabase(context).gpsDataDao()");
        this.b = gpsDataDao;
    }

    public /* synthetic */ KHJstyleGPSRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f7122a;
    }

    public final List<KHSessionGPSData> getGPSDataListBetweenTime(long j, long j2, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return this.b.getGPSDataListBetweenTime(Long.valueOf(j), Long.valueOf(j2), serialNo);
    }

    @Nullable
    public final Object insertSessionGPSData(@NotNull List<? extends KHJstyleSessionGPSData> list, @NotNull Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(list, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
