package com.coveiot.android.activitymodes.activity1k.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.activity1k.db.PhysicalActivityDatabase;
import com.coveiot.android.activitymodes.activity1k.db.dao.PhysicalActivitiesDao;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
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
/* loaded from: classes2.dex */
public final class PhysicalActivityRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2756a;
    @NotNull
    public PhysicalActivitiesDao b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<PhysicalActivityRepository, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, PhysicalActivityRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, PhysicalActivityRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final PhysicalActivityRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new PhysicalActivityRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository$insertActivityCategory$2", f = "PhysicalActivityRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super long[]>, Object> {
        public final /* synthetic */ List<EntityActivityCategory> $entityActivityCategory;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(List<EntityActivityCategory> list, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$entityActivityCategory = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$entityActivityCategory, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super long[]> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PhysicalActivityRepository.this.b.insertActivityCategories(this.$entityActivityCategory);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository$insertPhysicalActivity$2", f = "PhysicalActivityRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super long[]>, Object> {
        public final /* synthetic */ List<EntityPhysicalActivities> $entityPhysicalActivities;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(List<EntityPhysicalActivities> list, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$entityPhysicalActivities = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$entityPhysicalActivities, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super long[]> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PhysicalActivityRepository.this.b.insertPhysicalActivities(this.$entityPhysicalActivities);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public PhysicalActivityRepository(Context context) {
        this.f2756a = context;
        PhysicalActivitiesDao physicalActivityDao = PhysicalActivityDatabase.getDatabase(context).physicalActivityDao();
        Intrinsics.checkNotNull(physicalActivityDao);
        this.b = physicalActivityDao;
    }

    public /* synthetic */ PhysicalActivityRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearActivityTable() {
        this.b.clearActivityTable();
    }

    public final void clearCategoryTable() {
        this.b.clearCategoryTable();
    }

    @NotNull
    public final LiveData<List<ActivityCategoriesModel>> getActivitiesCategories() {
        return this.b.getActivitiesCategories();
    }

    @NotNull
    public final ActivityCategoriesModel getActivitiesCategory(int i) {
        return this.b.getActivitiesCategory(i);
    }

    @NotNull
    public final LiveData<List<CategoryAndActivityModel>> getActivityAndCategories() {
        return this.b.getActivityAndCategories();
    }

    @NotNull
    public final List<CategoryAndActivityModel> getActivityAndCategoriesWithoutLiveData() {
        return this.b.getActivityAndCategoriesWithoutLiveData();
    }

    @NotNull
    public final CategoryAndActivityModel getActivityAndCategoryList(int i, int i2) {
        return this.b.getActivityAndCategoryList(i, i2);
    }

    @Nullable
    public final Integer getActivityId(int i) {
        EntityPhysicalActivities physicalActivity = this.b.getPhysicalActivity(i);
        if (physicalActivity != null) {
            return Integer.valueOf(physicalActivity.getActivityId());
        }
        return Integer.valueOf(i);
    }

    @NotNull
    public final Context getContext() {
        return this.f2756a;
    }

    @Nullable
    public final List<EntityPhysicalActivities> getDefaultActivities() {
        return this.b.getDefaultActivities();
    }

    @NotNull
    public final EntityPhysicalActivities getPhysicalActivity(@Nullable Integer num, @Nullable Integer num2) {
        PhysicalActivitiesDao physicalActivitiesDao = this.b;
        Intrinsics.checkNotNull(num);
        int intValue = num.intValue();
        Intrinsics.checkNotNull(num2);
        return physicalActivitiesDao.getPhysicalActivity(intValue, num2.intValue());
    }

    @NotNull
    public final EntityPhysicalActivities getPhysicalActivityByFwActivityId(@Nullable Integer num, @Nullable Integer num2) {
        PhysicalActivitiesDao physicalActivitiesDao = this.b;
        Intrinsics.checkNotNull(num);
        int intValue = num.intValue();
        Intrinsics.checkNotNull(num2);
        return physicalActivitiesDao.getPhysicalActivityByFWActivityId(intValue, num2.intValue());
    }

    @NotNull
    public final String getPhysicalActivityCategoryName(int i) {
        return this.b.getPhysicalActivityCategoryName(i);
    }

    @NotNull
    public final LiveData<List<ActivitiesListModel>> getPhysicalActivityList() {
        return this.b.getAllPhysicalActivityList();
    }

    @NotNull
    public final EntityPhysicalActivities getPhysicalActivityN(@NotNull String activityCode) {
        Intrinsics.checkNotNullParameter(activityCode, "activityCode");
        return this.b.getPhysicalActivityN(activityCode);
    }

    @NotNull
    public final String getPhysicalActivityName(int i, int i2) {
        return this.b.getPhysicalActivityName(i, i2);
    }

    @Nullable
    public final Object insertActivityCategory(@NotNull List<EntityActivityCategory> list, @NotNull Continuation<? super long[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new a(list, null), continuation);
    }

    @Nullable
    public final Object insertPhysicalActivity(@NotNull List<EntityPhysicalActivities> list, @NotNull Continuation<? super long[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new b(list, null), continuation);
    }

    @NotNull
    public final LiveData<List<ActivitiesListModel>> getPhysicalActivityList(int i) {
        return this.b.getAllPhysicalActivityList(i);
    }
}
