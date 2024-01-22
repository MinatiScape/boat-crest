package com.coveiot.android.navigation.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.navigation.db.RecentSearchHistoryDatabase;
import com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao;
import com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
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
/* loaded from: classes5.dex */
public final class RecentSearchHistoryRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5522a;
    @NotNull
    public RecentSearchHistoryDao b;

    /* loaded from: classes5.dex */
    public static final class Companion extends SingletonHolder<RecentSearchHistoryRepository, Context> {

        /* loaded from: classes5.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, RecentSearchHistoryRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, RecentSearchHistoryRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final RecentSearchHistoryRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new RecentSearchHistoryRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.repository.RecentSearchHistoryRepository$insertSelectedPlace$2", f = "RecentSearchHistoryRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityRecentSearchHistory $entityRecentSearchHistory;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(EntityRecentSearchHistory entityRecentSearchHistory, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$entityRecentSearchHistory = entityRecentSearchHistory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$entityRecentSearchHistory, continuation);
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
                RecentSearchHistoryRepository.this.b.insertSelectedPlace(this.$entityRecentSearchHistory);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public RecentSearchHistoryRepository(Context context) {
        this.f5522a = context;
        RecentSearchHistoryDao recentSearchHistoryDao = RecentSearchHistoryDatabase.getDatabase(context).recentSearchHistoryDao();
        Intrinsics.checkNotNullExpressionValue(recentSearchHistoryDao, "getDatabase(context).recentSearchHistoryDao()");
        this.b = recentSearchHistoryDao;
    }

    public /* synthetic */ RecentSearchHistoryRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearRecentSearchHistory() {
        this.b.clearRecentSearchHistoryTable();
    }

    @NotNull
    public final Context getContext() {
        return this.f5522a;
    }

    @NotNull
    public final LiveData<List<RecentSearchHistoryData>> getRecentHistoryData() {
        return this.b.fetchAllRecentSearchHistoryData();
    }

    @Nullable
    public final Object insertSelectedPlace(@NotNull EntityRecentSearchHistory entityRecentSearchHistory, @NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(entityRecentSearchHistory, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
