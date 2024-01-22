package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.ViewUtilsKt;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutJsInterface;
import com.coveiot.android.leonardo.more.activities.ActivityEditProfile;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SActivatePlanResponse;
import com.coveiot.coveaccess.preparationplan.requestmodel.ActivateFitnessPlanReq;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.OnDeviceConnected;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class ActivityWorkoutWebViewer extends BaseActivity implements WorkoutJsInterface.OnJavascriptListener {
    public TextView backButtonIV;
    public WebView mWebView;
    @Nullable
    public WorkoutJsInterface q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityWorkoutWebView";
    public final int r = 11;
    @NotNull
    public final String s = "profileSuccess";
    @NotNull
    public final String t = "profileInfo";

    public static final void A(ActivityWorkoutWebViewer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void B(ActivityWorkoutWebViewer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PreparationPlanRepository.Companion.getInstance(this$0).getCurrentPlanFromServer(new PreparationPlanRepository.PlanDetailsListner() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutWebViewer$updatePlan$1$1
            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
            public void onFailure(@NotNull String mesaage) {
                Intrinsics.checkNotNullParameter(mesaage, "mesaage");
                LogHelper.d("preplan", "Not onPlanFetchedSuccessfully");
            }

            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
            public void onPlanFetchedSuccessfully() {
                LogHelper.d("preplan", "onPlanFetchedSuccessfully");
            }
        });
    }

    public static final void w(final ActivityWorkoutWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        Log.d(str2, "appOutScript: " + str);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(str);
        mWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.activitymodes.activities.p2
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityWorkoutWebViewer.x(ActivityWorkoutWebViewer.this, (String) obj);
            }
        });
    }

    public static final void x(ActivityWorkoutWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        Log.d(str2, "onReceiveValue: " + str);
    }

    public static final void y(boolean z, String str, final ActivityWorkoutWebViewer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final JSONObject jSONObject = new JSONObject();
        jSONObject.put("reqId", "1");
        jSONObject.put("resType", "UPDATE_USR_PROFILE");
        jSONObject.put("reqVer", 1);
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, z ? CoveApiConstants.RESPONSE_STATUS_VALUE_OK : "ER");
        if (!z) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", str);
            jSONObject.put("data", jSONObject2);
        }
        WebView mWebView = this$0.getMWebView();
        mWebView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + jSONObject + HexStringBuilder.COMMENT_END_CHAR, new ValueCallback() { // from class: com.coveiot.android.activitymodes.activities.q2
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityWorkoutWebViewer.z(ActivityWorkoutWebViewer.this, jSONObject, (String) obj);
            }
        });
    }

    public static final void z(ActivityWorkoutWebViewer this$0, JSONObject resObject, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(resObject, "$resObject");
        String str2 = this$0.p;
        LogHelper.d(str2, "evaluateJavascript.appOut: " + resObject);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.s2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutWebViewer.w(ActivityWorkoutWebViewer.this, str);
            }
        });
    }

    @NotNull
    public final TextView getBackButtonIV() {
        TextView textView = this.backButtonIV;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backButtonIV");
        return null;
    }

    @NotNull
    public final WebView getMWebView() {
        WebView webView = this.mWebView;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        return null;
    }

    @Nullable
    public final WorkoutJsInterface getWorkoutJsInterface() {
        return this.q;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != this.r || intent == null) {
            return;
        }
        final boolean booleanExtra = intent.getBooleanExtra(this.s, false);
        final String stringExtra = intent.getStringExtra(this.t);
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.t2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutWebViewer.y(booleanExtra, stringExtra, this);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getMWebView().canGoBack()) {
            getMWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_workout_web_viewer);
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.webkit.WebView");
        setMWebView((WebView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.toolbar_back_arrow)");
        setBackButtonIV((TextView) findViewById2);
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        showProgressWithTitle("Loading...");
        this.q = new WorkoutJsInterface(this, this);
        WebView mWebView = getMWebView();
        WorkoutJsInterface workoutJsInterface = this.q;
        Intrinsics.checkNotNull(workoutJsInterface);
        mWebView.addJavascriptInterface(workoutJsInterface, "CoveJsInterface");
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.o2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWorkoutWebViewer.A(ActivityWorkoutWebViewer.this, view);
            }
        });
        String stringExtra = getIntent().getStringExtra(WorkoutConstants.INTENT_EXTRA_URL);
        String stringExtra2 = getIntent().getStringExtra(WorkoutConstants.INTENT_EXTRA_TITLE);
        Intrinsics.checkNotNull(stringExtra2);
        if (stringExtra2.length() > 0) {
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(stringExtra2);
        }
        if (AppUtils.isEmpty(stringExtra)) {
            return;
        }
        WebSettings settings = getMWebView().getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
        settings.setMixedContentMode(0);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath("/data/data/" + getMWebView().getContext().getPackageName() + "/databases/");
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutWebViewer$onCreate$2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (ActivityWorkoutWebViewer.this.isFinishing()) {
                    return;
                }
                ActivityWorkoutWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                view.loadUrl(url);
                return true;
            }
        });
        WebView mWebView2 = getMWebView();
        Intrinsics.checkNotNull(stringExtra);
        mWebView2.loadUrl(stringExtra);
    }

    @Subscribe
    public final void onDeviceConnected(@NotNull OnDeviceConnected onDeviceConnected) {
        Intrinsics.checkNotNullParameter(onDeviceConnected, "onDeviceConnected");
        new PreferenceManager(this).setDeviceConnected(true);
    }

    @Subscribe
    public final void onDeviceDisconnected(@NotNull OnDeviceDisconnected onDeviceDisconnected) {
        Intrinsics.checkNotNullParameter(onDeviceDisconnected, "onDeviceDisconnected");
        new PreferenceManager(this).setDeviceConnected(false);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void openEditProfile() {
        try {
            startActivityForResult(new Intent(this, ActivityEditProfile.class), this.r);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void openLinkInBrowser(@Nullable String str) {
        if (str != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        }
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void openNoInternetDialog() {
        if (AppUtils.isNetConnected(this)) {
            return;
        }
        String string = getString(R.string.check_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.check_internet)");
        ViewUtilsKt.toast(this, string);
    }

    public final void setBackButtonIV(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backButtonIV = textView;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final void setWorkoutJsInterface(@Nullable WorkoutJsInterface workoutJsInterface) {
        this.q = workoutJsInterface;
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void updatePlan() {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.r2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityWorkoutWebViewer.B(ActivityWorkoutWebViewer.this);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.utils.WorkoutJsInterface.OnJavascriptListener
    public void updateProfile(@NotNull JSONObject profileJsonObj) {
        Intrinsics.checkNotNullParameter(profileJsonObj, "profileJsonObj");
        CovePreparationPlanApi.activateFitnessPlan(new ActivateFitnessPlanReq("5bae48c3360f824f60308eb4"), new CoveApiListener<SActivatePlanResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activities.ActivityWorkoutWebViewer$updateProfile$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                Intrinsics.checkNotNullParameter(object, "object");
                PreparationPlanRepository.Companion.getInstance(ActivityWorkoutWebViewer.this).getCurrentPlanFromServer(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SActivatePlanResponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                PreparationPlanRepository.Companion.getInstance(ActivityWorkoutWebViewer.this).getCurrentPlanFromServer(null);
            }
        });
    }
}
