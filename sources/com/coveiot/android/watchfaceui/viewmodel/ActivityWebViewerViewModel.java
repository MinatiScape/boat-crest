package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import android.net.Uri;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModel;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public final class ActivityWebViewerViewModel extends ViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static ActivityWebViewerViewModel m;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6148a;
    public int b;
    @Nullable
    public String[] c;
    @Nullable
    public Uri d;
    @Nullable
    public ValueCallback<Uri[]> e;
    public boolean f;
    public boolean g;
    @Nullable
    public PermissionRequest h;
    @Nullable
    public String i;
    @Nullable
    public JSONObject j;
    @Nullable
    public JSONObject k;
    @Nullable
    public JSONObject l;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @MainThread
        @NotNull
        public final ActivityWebViewerViewModel getInstance(@NotNull Context context) {
            ActivityWebViewerViewModel activityWebViewerViewModel;
            Intrinsics.checkNotNullParameter(context, "context");
            if (ActivityWebViewerViewModel.m != null) {
                activityWebViewerViewModel = ActivityWebViewerViewModel.m;
                if (activityWebViewerViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("instance");
                    activityWebViewerViewModel = null;
                }
            } else {
                activityWebViewerViewModel = new ActivityWebViewerViewModel(context);
            }
            ActivityWebViewerViewModel.m = activityWebViewerViewModel;
            ActivityWebViewerViewModel activityWebViewerViewModel2 = ActivityWebViewerViewModel.m;
            if (activityWebViewerViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
                return null;
            }
            return activityWebViewerViewModel2;
        }
    }

    public ActivityWebViewerViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6148a = context;
        this.f = true;
    }

    @Nullable
    public final JSONObject getBackObject() {
        return this.l;
    }

    @Nullable
    public final String getBase64Image() {
        return this.i;
    }

    @Nullable
    public final JSONObject getCameraObject() {
        return this.j;
    }

    @NotNull
    public final Context getContext() {
        return this.f6148a;
    }

    public final boolean getLoadingFinished() {
        return this.f;
    }

    @Nullable
    public final PermissionRequest getPermissionRequest() {
        return this.h;
    }

    @Nullable
    public final String[] getPermission_array() {
        return this.c;
    }

    @Nullable
    public final JSONObject getPhotoObject() {
        return this.k;
    }

    @Nullable
    public final Uri getPhotoURI() {
        return this.d;
    }

    public final boolean getRedirect() {
        return this.g;
    }

    public final int getReq() {
        return this.b;
    }

    @Nullable
    public final ValueCallback<Uri[]> getUploadMessage() {
        return this.e;
    }

    public final void setBackObject(@Nullable JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public final void setBase64Image(@Nullable String str) {
        this.i = str;
    }

    public final void setCameraObject(@Nullable JSONObject jSONObject) {
        this.j = jSONObject;
    }

    public final void setLoadingFinished(boolean z) {
        this.f = z;
    }

    public final void setPermissionRequest(@Nullable PermissionRequest permissionRequest) {
        this.h = permissionRequest;
    }

    public final void setPermission_array(@Nullable String[] strArr) {
        this.c = strArr;
    }

    public final void setPhotoObject(@Nullable JSONObject jSONObject) {
        this.k = jSONObject;
    }

    public final void setPhotoURI(@Nullable Uri uri) {
        this.d = uri;
    }

    public final void setRedirect(boolean z) {
        this.g = z;
    }

    public final void setReq(int i) {
        this.b = i;
    }

    public final void setUploadMessage(@Nullable ValueCallback<Uri[]> valueCallback) {
        this.e = valueCallback;
    }
}
