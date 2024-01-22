package com.coveiot.android.activitymodes.activity1k;

import android.content.Context;
import com.coveiot.android.activitymodes.SingletonHolder;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SingletonOneKActivity {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2728a;
    @Nullable
    public List<CategoryAndActivityModel> b;
    @Nullable
    public ActivitiesListModel c;
    @Nullable
    public ActivityCategoriesModel d;
    @Nullable
    public CategoryAndActivityModel e;
    public boolean f;
    @Nullable
    public String g;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SingletonOneKActivity, Context> {

        /* loaded from: classes2.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SingletonOneKActivity> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SingletonOneKActivity.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SingletonOneKActivity invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SingletonOneKActivity(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SingletonOneKActivity(Context context) {
        this.f2728a = context;
        this.b = new ArrayList();
    }

    public /* synthetic */ SingletonOneKActivity(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final ActivityCategoriesModel getActivityCategoriesModel() {
        return this.d;
    }

    @Nullable
    public final String getAutoActivityDetectionCode() {
        return this.g;
    }

    @Nullable
    public final List<CategoryAndActivityModel> getCategoryAndActivityList() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f2728a;
    }

    public final boolean getIsSelectedForAutoActivityDetection() {
        return this.f;
    }

    @Nullable
    public final ActivitiesListModel getPhysicalActivity() {
        return this.c;
    }

    @Nullable
    public final CategoryAndActivityModel getSelectedActivity() {
        return this.e;
    }

    public final void setActivityCategoriesModel(@NotNull ActivityCategoriesModel activityCategoriesModel) {
        Intrinsics.checkNotNullParameter(activityCategoriesModel, "activityCategoriesModel");
        this.d = activityCategoriesModel;
    }

    public final void setAutoActivityDetectionCode(@Nullable String str) {
        this.g = str;
    }

    public final void setCategoryAndActivityList(@NotNull List<CategoryAndActivityModel> categoryAndActivityList) {
        Intrinsics.checkNotNullParameter(categoryAndActivityList, "categoryAndActivityList");
        this.b = categoryAndActivityList;
    }

    public final void setIsSelectedForAutoActivityDetection(boolean z) {
        this.f = z;
    }

    public final void setPhysicalActivity(@NotNull ActivitiesListModel physicalActivity) {
        Intrinsics.checkNotNullParameter(physicalActivity, "physicalActivity");
        this.c = physicalActivity;
    }

    public final void setSelectedActivity(@Nullable CategoryAndActivityModel categoryAndActivityModel) {
        this.e = categoryAndActivityModel;
    }
}
