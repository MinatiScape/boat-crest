package com.coveiot.android.dashboard2;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.ServerDataPullConfigModel;
import com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import com.google.gson.Gson;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class Dashboard2PreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4192a;
    @NotNull
    public final String b;
    public int c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;
    @NotNull
    public final String j;
    @NotNull
    public final String k;
    @NotNull
    public final String l;
    @NotNull
    public final String m;
    @NotNull
    public final SharedPreferences n;

    /* loaded from: classes4.dex */
    public static final class Companion extends SingletonHolder<Dashboard2PreferenceManager, Context> {

        /* loaded from: classes4.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, Dashboard2PreferenceManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, Dashboard2PreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Dashboard2PreferenceManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new Dashboard2PreferenceManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Dashboard2PreferenceManager(Context context) {
        this.f4192a = context;
        this.b = "dashboard2_preference";
        this.d = "last_sync_triggered_timestamp";
        this.e = "last_complete_date_sync_timestamp";
        this.f = "isUserCheckedWatchfaceStudio";
        this.g = "isUserCheckedWatchSettings";
        this.h = "isUserCheckedWatchface";
        this.i = "selectedFitnessVitals";
        this.j = "doMoreWithYourWatch";
        this.k = "isUserTappedWatchfaceStudioInDashboard";
        this.l = "lastVitalApiCallTimestamp";
        this.m = "serverSyncConfigModel";
        SharedPreferences sharedPreferences = context.getSharedPreferences("dashboard2_preference", this.c);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…dPrefsFile, PRIVATE_MODE)");
        this.n = sharedPreferences;
    }

    public /* synthetic */ Dashboard2PreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearPreferences() {
        for (String str : this.n.getAll().keySet()) {
            remove(str);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4192a;
    }

    @NotNull
    public final List<DoMoreWithYourWatchDataModel> getDoMoreWithYourWatchData() {
        return CollectionsKt___CollectionsKt.toMutableList((Collection) DoMoreWithYourWatchHelper.INSTANCE.getDefaultDoMoreWithYourWatch(this.f4192a));
    }

    public final long getLastCompleteDataSyncTimestamp() {
        return this.n.getLong(this.e, -1L);
    }

    public final long getLastSyncTriggeredTimestamp() {
        return this.n.getLong(this.d, -1L);
    }

    public final long getLastVitalSequenceApiCallTimestamp() {
        return this.n.getLong(this.l, -1L);
    }

    @NotNull
    public final SelectedFitnessVitalsDataModel getSelectedFitnessVitals() {
        Gson gson = new Gson();
        Object fromJson = gson.fromJson(this.n.getString(this.i, gson.toJson(FitnessVitalsHelper.INSTANCE.getDefaultSelectedFitnessVitals(this.f4192a))), (Class<Object>) SelectedFitnessVitalsDataModel.class);
        Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …del::class.java\n        )");
        return (SelectedFitnessVitalsDataModel) fromJson;
    }

    @Nullable
    public final ServerDataPullConfigModel getServerSyncConfigModel() {
        return (ServerDataPullConfigModel) new Gson().fromJson(this.n.getString(this.m, null), (Class<Object>) ServerDataPullConfigModel.class);
    }

    public final boolean isUserCheckedWatchSettings() {
        return this.n.getBoolean(this.g, false);
    }

    public final boolean isUserCheckedWatchface() {
        return this.n.getBoolean(this.h, false);
    }

    public final boolean isUserCheckedWatchfaceStudio() {
        return this.n.getBoolean(this.f, false);
    }

    public final boolean isUserTappedWatchfaceStudioInDashboard() {
        return this.n.getBoolean(this.k, false);
    }

    public final void remove(@Nullable String str) {
        if (this.n.contains(str)) {
            SharedPreferences.Editor edit = this.n.edit();
            edit.remove(str);
            edit.commit();
        }
    }

    public final void saveDoMoreWithYourWatchData(@NotNull List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchModels) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchModels, "doMoreWithYourWatchModels");
        this.n.edit().putString(this.j, new Gson().toJson(doMoreWithYourWatchModels)).commit();
    }

    public final void saveIsUserCheckedWatchSettings(boolean z) {
        this.n.edit().putBoolean(this.g, z).commit();
    }

    public final void saveIsUserCheckedWatchface(boolean z) {
        this.n.edit().putBoolean(this.h, z).commit();
    }

    public final void saveIsUserCheckedWatchfaceStudio(boolean z) {
        this.n.edit().putBoolean(this.f, z).commit();
    }

    public final void saveIsUserTappedWatchfaceStudioInDashboard(boolean z) {
        this.n.edit().putBoolean(this.k, z).commit();
    }

    public final void saveLastCompleteDataSyncTimestamp(long j) {
        this.n.edit().putLong(this.e, j).commit();
    }

    public final void saveLastSyncTriggeredTimestamp(long j) {
        this.n.edit().putLong(this.d, j).commit();
    }

    public final void saveLastVitalSequenceApiCallTimestamp(long j) {
        this.n.edit().putLong(this.l, j).commit();
    }

    public final void saveSelectedFitnessVitals(@NotNull SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel) {
        Intrinsics.checkNotNullParameter(selectedFitnessVitalsDataModel, "selectedFitnessVitalsDataModel");
        this.n.edit().putString(this.i, new Gson().toJson(selectedFitnessVitalsDataModel)).commit();
    }

    public final void saveServerSyncConfigModel(@NotNull ServerDataPullConfigModel serverDataPullConfigModel) {
        Intrinsics.checkNotNullParameter(serverDataPullConfigModel, "serverDataPullConfigModel");
        this.n.edit().putString(this.m, new Gson().toJson(serverDataPullConfigModel)).commit();
    }
}
