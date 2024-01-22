package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public class BaseActivity extends AppCompatActivity {
    @Nullable
    public LoadingDialog h;
    @Nullable
    public LoadingDialogWithTitle i;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage l;
    @Nullable
    public BottomSheetDialogImageTitleMessage m;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle n;
    @Nullable
    public BottomSheetDialogTwoButtons o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String j = "session_expiry_brodcast";
    @NotNull
    public final SessionExpiryReceiver k = new SessionExpiryReceiver();

    /* loaded from: classes7.dex */
    public final class SessionExpiryReceiver extends BroadcastReceiver {
        public SessionExpiryReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            if (ThemesUtils.INSTANCE.isGuestUser(BaseActivity.this)) {
                return;
            }
            BaseActivity.this.onSessionExpired();
        }
    }

    public static final void j(BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logOutUser();
    }

    public static final void k(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, boolean z, BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        if (z) {
            this$0.finish();
        }
    }

    public static final void l(BatterySaverModeDialogClickCallback batterySaverModeDialogClickCallback, BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (batterySaverModeDialogClickCallback != null) {
            batterySaverModeDialogClickCallback.onPositiveButtonClicked();
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.m;
        if (bottomSheetDialogImageTitleMessage != null) {
            bottomSheetDialogImageTitleMessage.dismiss();
        }
        this$0.m = null;
    }

    public static final void m(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void n(BaseActivity this$0, CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        if (this$0.isFinishing()) {
            return;
        }
        this$0.dismissProgress();
        commonMessageDialog.dismiss();
    }

    public static final void o(BaseActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.n;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
    }

    public static final void p(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static /* synthetic */ void showBandNotConnected$default(BaseActivity baseActivity, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showBandNotConnected");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        baseActivity.showBandNotConnected(z);
    }

    public static /* synthetic */ void showCommonMessageDialog$default(BaseActivity baseActivity, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showCommonMessageDialog");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        baseActivity.showCommonMessageDialog(str, z);
    }

    public static /* synthetic */ void showProgressWithTitle$default(BaseActivity baseActivity, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showProgressWithTitle");
        }
        if ((i & 1) != 0) {
            str = baseActivity.getString(R.string.please_wait);
            Intrinsics.checkNotNullExpressionValue(str, "getString(R.string.please_wait)");
        }
        baseActivity.showProgressWithTitle(str);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(@Nullable Context context) {
        Resources resources;
        Configuration configuration = new Configuration((context == null || (resources = context.getResources()) == null) ? null : resources.getConfiguration());
        configuration.fontScale = 1.0f;
        applyOverrideConfiguration(configuration);
        super.attachBaseContext(context);
        AppUtils.setLocale(SessionManager.getInstance(this).getSelectedLanguage(), this);
    }

    public final void dismissProgress() {
        LoadingDialog loadingDialog;
        if (isFinishing() || isDestroyed() || (loadingDialog = this.h) == null) {
            return;
        }
        Intrinsics.checkNotNull(loadingDialog);
        if (loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.h;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.dismiss();
        }
    }

    public final void dismissSystemProgress() {
        LoadingDialogWithTitle loadingDialogWithTitle;
        if (isFinishing() || isDestroyed() || (loadingDialogWithTitle = this.i) == null) {
            return;
        }
        Intrinsics.checkNotNull(loadingDialogWithTitle);
        if (loadingDialogWithTitle.isShowing()) {
            LoadingDialogWithTitle loadingDialogWithTitle2 = this.i;
            Intrinsics.checkNotNull(loadingDialogWithTitle2);
            loadingDialogWithTitle2.dismiss();
        }
    }

    public final void drawableEnd(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public final void drawableStart(@NotNull TextView textView, @DrawableRes int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
    }

    public final void drawableStartAndEnd(@NotNull TextView textView, @DrawableRes int i, int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(i, 0, i2, 0);
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBatterySaveModeEnabledDialogOneButtonTitleMessage() {
        return this.m;
    }

    @Nullable
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle1() {
        return this.n;
    }

    @Nullable
    public final LoadingDialog getProgressDialog() {
        return this.h;
    }

    @NotNull
    public final String getSESSSION_ESXPIRY_ACTION() {
        return this.j;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getSessionExipiryDialogOneButtonTitleMessage() {
        return this.l;
    }

    @NotNull
    public final SessionExpiryReceiver getSessionExpiryReceiver() {
        return this.k;
    }

    @Nullable
    public final LoadingDialogWithTitle getSystemProgressDialog() {
        return this.i;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getWatchDisconnectedDialog() {
        return this.o;
    }

    public final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public final void goneIF(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public final void hideKeyBoard(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object systemService = getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public final void hideKeyboard(@NotNull View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Object systemService = getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public final void inVisible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public final void logOutUser() {
        Intent intent = new Intent("BOAT_SPLASHf");
        intent.addFlags(32768);
        intent.putExtra("IS_FROM_SESSION_EXPIRY", true);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    public final void logScreenViewEvent(@NotNull String screenName) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseAnalytics.Event.SCREEN_VIEW);
        String lowerCase = screenName.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMapData(kotlin.collections.s.hashMapOf(new Pair(FirebaseAnalytics.Param.SCREEN_NAME, lowerCase)));
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    @NotNull
    public final Toast makeText(@NotNull Context context, @NotNull String message, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast makeText = Toast.makeText(context, message, i);
        Intrinsics.checkNotNullExpressionValue(makeText, "makeText(context, message, time)");
        return makeText;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.h = new LoadingDialog(this);
        this.i = new LoadingDialogWithTitle(this);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        AppUtils.setLocale(SessionManager.getInstance(this).getSelectedLanguage(), this);
    }

    public final void onSessionExpired() {
        if (this.l == null) {
            String string = getString(R.string.session_expired);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.session_expired)");
            String string2 = getString(R.string.session_expired_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.session_expired_message)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.l = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BaseActivity.j(BaseActivity.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.l;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.l;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        try {
            super.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerReciever();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        unRegisterReceiver();
    }

    public final void registerReciever() {
        try {
            registerReceiver(this.k, new IntentFilter(this.j));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setBatterySaveModeEnabledDialogOneButtonTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.m = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetDialogOneButtonOneTitle1(@Nullable BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        this.n = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setProgressDialog(@Nullable LoadingDialog loadingDialog) {
        this.h = loadingDialog;
    }

    public final void setSessionExipiryDialogOneButtonTitleMessage(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.l = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setSystemProgressDialog(@Nullable LoadingDialogWithTitle loadingDialogWithTitle) {
        this.i = loadingDialogWithTitle;
    }

    public final void setWatchDisconnectedDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.o = bottomSheetDialogTwoButtons;
    }

    public final void showBandNotConnected(final boolean z) {
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        String string2 = getString(R.string.please_connect_the_device);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_the_device)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.k(BottomSheetDialogOneButtonTitleMessage.this, z, this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showBatterySaverModeEnabledDialog(@Nullable final BatterySaverModeDialogClickCallback batterySaverModeDialogClickCallback) {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        boolean z = false;
        if (this.m == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
            String string = getResources().getString(R.string.battery_save_mode_enabled);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…attery_save_mode_enabled)");
            String string2 = getResources().getString(R.string.disable_battery_saver_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…sable_battery_saver_mode)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
            this.m = bottomSheetDialogImageTitleMessage2;
            bottomSheetDialogImageTitleMessage2.setCancelable(false);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.m;
            if (bottomSheetDialogImageTitleMessage3 != null) {
                bottomSheetDialogImageTitleMessage3.setCancelableOutside(false);
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.m;
            if (bottomSheetDialogImageTitleMessage4 != null) {
                String string3 = getResources().getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(\n   …R.string.ok\n            )");
                bottomSheetDialogImageTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.f
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseActivity.l(BatterySaverModeDialogClickCallback.this, this, view);
                    }
                });
            }
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.m;
        if (bottomSheetDialogImageTitleMessage5 != null && !bottomSheetDialogImageTitleMessage5.isShowing()) {
            z = true;
        }
        if (!z || (bottomSheetDialogImageTitleMessage = this.m) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessage.show();
    }

    public final void showBluetoothOffDialog() {
        String string = getString(R.string.bluetooth_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
        String string2 = getString(R.string.bluetooth_off_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.m(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showCommonMessageDialog(@NotNull String message, boolean z) {
        Window window;
        Intrinsics.checkNotNullParameter(message, "message");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, message, z, false, 8, null);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.theme.g
            @Override // java.lang.Runnable
            public final void run() {
                BaseActivity.n(BaseActivity.this, commonMessageDialog);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void showInternetOffDialog() {
        String string = getString(R.string.please_check_network);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_check_network)");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        this.n = bottomSheetDialogOneButtonOneTitle;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.theme.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.o(BaseActivity.this, view);
            }
        });
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.n;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            bottomSheetDialogOneButtonOneTitle2.show();
        }
    }

    public final void showNoBluetoothDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = getString(R.string.bluetooth_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
        String string2 = getString(R.string.bluetooth_off_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = context.getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseActivity.p(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showNoInternetMessage() {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, getString(R.string.please_check_network), 1).show();
    }

    public final void showProgress() {
        LoadingDialog loadingDialog;
        if (isFinishing() || (loadingDialog = this.h) == null) {
            return;
        }
        Boolean valueOf = loadingDialog != null ? Boolean.valueOf(loadingDialog.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        LoadingDialog loadingDialog2 = this.h;
        if (loadingDialog2 != null) {
            loadingDialog2.setTitle(getString(R.string.please_wait));
        }
        LoadingDialog loadingDialog3 = this.h;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCancelable(false);
        LoadingDialog loadingDialog4 = this.h;
        Intrinsics.checkNotNull(loadingDialog4);
        loadingDialog4.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog5 = this.h;
        Intrinsics.checkNotNull(loadingDialog5);
        loadingDialog5.show();
    }

    public final void showProgressWithTitle(@NotNull String title) {
        LoadingDialog loadingDialog;
        Intrinsics.checkNotNullParameter(title, "title");
        if (isFinishing() || (loadingDialog = this.h) == null) {
            return;
        }
        Intrinsics.checkNotNull(loadingDialog);
        loadingDialog.setTitle(title);
        LoadingDialog loadingDialog2 = this.h;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.setCancelable(false);
        LoadingDialog loadingDialog3 = this.h;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog4 = this.h;
        Intrinsics.checkNotNull(loadingDialog4);
        loadingDialog4.show();
    }

    public final void showProgresswithMsg(@NotNull String msg) {
        LoadingDialog loadingDialog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isFinishing() || (loadingDialog = this.h) == null) {
            return;
        }
        if (loadingDialog != null) {
            loadingDialog.setTitle(msg);
        }
        LoadingDialog loadingDialog2 = this.h;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.setCancelable(false);
        LoadingDialog loadingDialog3 = this.h;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog4 = this.h;
        Intrinsics.checkNotNull(loadingDialog4);
        loadingDialog4.show();
    }

    public final void showSystemProgresswithMsg(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isFinishing() || this.h == null) {
            return;
        }
        LoadingDialogWithTitle loadingDialogWithTitle = this.i;
        if (loadingDialogWithTitle != null) {
            loadingDialogWithTitle.setTitle(msg);
        }
        LoadingDialogWithTitle loadingDialogWithTitle2 = this.i;
        Intrinsics.checkNotNull(loadingDialogWithTitle2);
        loadingDialogWithTitle2.setCancelable(false);
        LoadingDialogWithTitle loadingDialogWithTitle3 = this.i;
        Intrinsics.checkNotNull(loadingDialogWithTitle3);
        loadingDialogWithTitle3.setCanceledOnTouchOutside(false);
        LoadingDialogWithTitle loadingDialogWithTitle4 = this.i;
        Intrinsics.checkNotNull(loadingDialogWithTitle4);
        loadingDialogWithTitle4.show();
    }

    public final void showWatchDisconnectedDialog(@NotNull final Context context) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(context, "context");
        String string = getString(R.string.your_watch_disconnected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.your_watch_disconnected)");
        String string2 = getString(R.string.watch_disconnected_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_disconnected_msg)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(context, string, string2, false, 8, null);
        this.o = bottomSheetDialogTwoButtons2;
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons2.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.BaseActivity$showWatchDisconnectedDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons watchDisconnectedDialog = BaseActivity.this.getWatchDisconnectedDialog();
                if (watchDisconnectedDialog != null) {
                    watchDisconnectedDialog.dismiss();
                }
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.o;
        if (bottomSheetDialogTwoButtons3 != null) {
            String string4 = getString(R.string.connect_now);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.connect_now)");
            bottomSheetDialogTwoButtons3.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.theme.BaseActivity$showWatchDisconnectedDialog$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    if (AppUtils.isBluetoothEnabled(context)) {
                        BleApiManager.getInstance(context).getBleApi().restartService();
                        BottomSheetDialogTwoButtons watchDisconnectedDialog = this.getWatchDisconnectedDialog();
                        if (watchDisconnectedDialog != null) {
                            watchDisconnectedDialog.dismiss();
                            return;
                        }
                        return;
                    }
                    BottomSheetDialogTwoButtons watchDisconnectedDialog2 = this.getWatchDisconnectedDialog();
                    if (watchDisconnectedDialog2 != null) {
                        watchDisconnectedDialog2.dismiss();
                    }
                    this.showNoBluetoothDialog(context);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.o;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.o) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void unRegisterReceiver() {
        try {
            unregisterReceiver(this.k);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void visible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public final void visibleIf(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 0 : 8);
    }
}
