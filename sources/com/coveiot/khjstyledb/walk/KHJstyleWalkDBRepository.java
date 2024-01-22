package com.coveiot.khjstyledb.walk;

import android.content.Context;
import com.coveiot.khjstyledb.KHJstyleAppDatabase;
import com.coveiot.khjstyledb.SingletonHolder;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0011\b\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u001d\u0010\u001eJB\u0010\n\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\t0\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002J!\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0014\u001a\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u001c\u001a\u00020\u00158\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDBRepository;", "", "", "startTime", "endTime", "serialNo", "", "Lcom/coveiot/khjstyledb/walk/KHJstyleHourlyWalkData;", "kotlin.jvm.PlatformType", "", "getHourlyStepsValueBetween", "hourlyWalkDataList", "", "insertHourlyStepsData", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroid/content/Context;", "a", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDataDao;", "b", "Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDataDao;", "getWalkDataDao", "()Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDataDao;", "setWalkDataDao", "(Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDataDao;)V", "walkDataDao", "<init>", "(Landroid/content/Context;)V", "Companion", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHJstyleWalkDBRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7131a;
    @NotNull
    public KHJstyleWalkDataDao b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDBRepository$Companion;", "Lcom/coveiot/khjstyledb/SingletonHolder;", "Lcom/coveiot/khjstyledb/walk/KHJstyleWalkDBRepository;", "Landroid/content/Context;", "<init>", "()V", "khjstyledb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHJstyleWalkDBRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHJstyleWalkDBRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHJstyleWalkDBRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHJstyleWalkDBRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHJstyleWalkDBRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.khjstyledb.walk.KHJstyleWalkDBRepository$insertHourlyStepsData$2", f = "KHJstyleWalkDBRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<KHJstyleHourlyWalkData> $hourlyWalkDataList;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public a(List<? extends KHJstyleHourlyWalkData> list, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$hourlyWalkDataList = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$hourlyWalkDataList, continuation);
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
                KHJstyleWalkDBRepository.this.getWalkDataDao().insertAll(this.$hourlyWalkDataList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public KHJstyleWalkDBRepository(Context context) {
        this.f7131a = context;
        KHJstyleWalkDataDao walkDataDao = KHJstyleAppDatabase.getAppDatabase(context).walkDataDao();
        Intrinsics.checkNotNullExpressionValue(walkDataDao, "getAppDatabase(context).walkDataDao()");
        this.b = walkDataDao;
    }

    public /* synthetic */ KHJstyleWalkDBRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f7131a;
    }

    public final List<KHJstyleHourlyWalkData> getHourlyStepsValueBetween(@NotNull String startTime, @NotNull String endTime, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return this.b.getHourlyStepsValueBetween(startTime, endTime, serialNo);
    }

    @NotNull
    public final KHJstyleWalkDataDao getWalkDataDao() {
        return this.b;
    }

    @Nullable
    public final Object insertHourlyStepsData(@NotNull List<? extends KHJstyleHourlyWalkData> list, @NotNull Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(list, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void setWalkDataDao(@NotNull KHJstyleWalkDataDao kHJstyleWalkDataDao) {
        Intrinsics.checkNotNullParameter(kHJstyleWalkDataDao, "<set-?>");
        this.b = kHJstyleWalkDataDao;
    }
}
