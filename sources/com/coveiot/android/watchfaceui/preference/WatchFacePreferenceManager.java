package com.coveiot.android.watchfaceui.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.utils.SingletonHolder;
import com.google.gson.Gson;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFacePreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String LAST_SELECTED_WATCH_FACE = "last_selected_watch_face";
    public static final int PRIVATE_MODE = 0;
    @NotNull
    public static final String SELECTED_BACKGROUND_TYPE_WATCH_FACE = "selected_background_type_watch_face";
    @NotNull
    public static final String SELECTED_CLOUD_TYPE_WATCH_FACE = "selected_cloud_type_watch_face";
    @NotNull
    public static final String SELECTED_CLOUD_WATCH_FACE = "selected_cloud_watch_face";
    @NotNull
    public static final String SELECTED_DEFAULT_TYPE_WATCH_FACE = "selected_default_type_watch_face";
    @NotNull
    public static final String SELECTED_DEFAULT_WATCH_FACE = "selected_default_watch_face";
    @NotNull
    public static final String SELECTED_DIY_WATCH_FACE = "selected_diy_watch_face";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6141a;
    @NotNull
    public SharedPreferences b;
    @NotNull
    public SharedPreferences.Editor c;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<WatchFacePreferenceManager, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, WatchFacePreferenceManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, WatchFacePreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final WatchFacePreferenceManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new WatchFacePreferenceManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WatchFacePreferenceManager(Context context) {
        this.f6141a = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("WATCHFACE_PREF", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…s(prefName, PRIVATE_MODE)");
        this.b = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "pref.edit()");
        this.c = edit;
    }

    public /* synthetic */ WatchFacePreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final SharedPreferences.Editor getEditor() {
        return this.c;
    }

    @Nullable
    public final WatchFaceBean getLastSelectedWatchFace() {
        return (WatchFaceBean) new Gson().fromJson(this.b.getString(LAST_SELECTED_WATCH_FACE, null), (Class<Object>) WatchFaceBean.class);
    }

    @NotNull
    public final SharedPreferences getPref() {
        return this.b;
    }

    @Nullable
    public final WatchFaceBean getSelectedCloudWatchFace() {
        return (WatchFaceBean) new Gson().fromJson(this.b.getString(SELECTED_CLOUD_WATCH_FACE, null), (Class<Object>) WatchFaceBean.class);
    }

    @Nullable
    public final WatchFaceBean getSelectedDefaultWatchFace() {
        return (WatchFaceBean) new Gson().fromJson(this.b.getString(SELECTED_DEFAULT_WATCH_FACE, null), (Class<Object>) WatchFaceBean.class);
    }

    @Nullable
    public final WatchFaceBean getSelectedDiyWatchFace() {
        return (WatchFaceBean) new Gson().fromJson(this.b.getString(SELECTED_DIY_WATCH_FACE, null), (Class<Object>) WatchFaceBean.class);
    }

    public final int getSelectedWatchfaceBackgroundType() {
        return this.b.getInt(SELECTED_BACKGROUND_TYPE_WATCH_FACE, 0);
    }

    public final void saveLastSelectedWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.c.putString(LAST_SELECTED_WATCH_FACE, new Gson().toJson(watchFaceBean));
        this.c.commit();
    }

    public final void saveSelectedCloudWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.c.putString(SELECTED_CLOUD_WATCH_FACE, new Gson().toJson(watchFaceBean));
        this.c.commit();
        if (watchFaceBean != null) {
            saveLastSelectedWatchFace(watchFaceBean);
        }
    }

    public final void saveSelectedDefaultWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.c.putString(SELECTED_DEFAULT_WATCH_FACE, new Gson().toJson(watchFaceBean));
        this.c.commit();
        if (watchFaceBean != null) {
            saveLastSelectedWatchFace(watchFaceBean);
        }
    }

    public final void saveSelectedDiyWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.c.putString(SELECTED_DIY_WATCH_FACE, new Gson().toJson(watchFaceBean));
        this.c.commit();
        if (watchFaceBean != null) {
            saveLastSelectedWatchFace(watchFaceBean);
        }
    }

    public final void saveSelectedWatchfaceBackgroundType(int i) {
        this.c.putInt(SELECTED_BACKGROUND_TYPE_WATCH_FACE, i);
        this.c.commit();
    }

    public final void setEditor(@NotNull SharedPreferences.Editor editor) {
        Intrinsics.checkNotNullParameter(editor, "<set-?>");
        this.c = editor;
    }

    public final void setPref(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.b = sharedPreferences;
    }
}
