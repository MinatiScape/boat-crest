package com.coveiot.android.theme;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.theme.utils.BatterySaverModeDialogClickCallback;
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
public class BaseFragment extends Fragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public BottomSheetDialogImageTitleMessage h;
    @Nullable
    public BottomSheetDialogTwoButtons i;
    @Nullable
    public LoadingDialog j;
    @Nullable
    public QRCodeLoadingDialog k;
    @Nullable
    public ProgressDialog l;

    /* loaded from: classes7.dex */
    public interface ProgressListener {
        void onProgressChanged(int i);
    }

    public static final void f(EditText editText, BaseFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (editText != null) {
            editText.requestFocus();
            Object systemService = this$0.requireActivity().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).showSoftInput(editText, 1);
        }
    }

    public static final void g(BaseFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.h;
        if (bottomSheetDialogImageTitleMessage != null) {
            bottomSheetDialogImageTitleMessage.dismiss();
        }
        this$0.h = null;
    }

    public static final void h(BaseFragment this$0, CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        if (this$0.isAdded()) {
            this$0.dismissProgress();
            commonMessageDialog.dismiss();
        }
    }

    public static final void i(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void j(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static /* synthetic */ void showCommonMessageDialog$default(BaseFragment baseFragment, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showCommonMessageDialog");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        baseFragment.showCommonMessageDialog(str, z);
    }

    public static /* synthetic */ void showProgress$default(BaseFragment baseFragment, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showProgress");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        baseFragment.showProgress(z);
    }

    public static /* synthetic */ ProgressListener showProgressDialogWithTitleAndProgress$default(BaseFragment baseFragment, String str, boolean z, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                z = false;
            }
            if ((i2 & 4) != 0) {
                i = 100;
            }
            return baseFragment.showProgressDialogWithTitleAndProgress(str, z, i);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showProgressDialogWithTitleAndProgress");
    }

    public static /* synthetic */ void showProgressWithTitle$default(BaseFragment baseFragment, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showProgressWithTitle");
        }
        if ((i & 1) != 0) {
            str = baseFragment.getString(R.string.please_wait);
            Intrinsics.checkNotNullExpressionValue(str, "getString(R.string.please_wait)");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        baseFragment.showProgressWithTitle(str, z);
    }

    public static /* synthetic */ void showQRCodeProgress$default(BaseFragment baseFragment, boolean z, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showQRCodeProgress");
        }
        if ((i & 1) != 0) {
            z = false;
        }
        baseFragment.showQRCodeProgress(z, str);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public void dismissProgress() {
        LoadingDialog loadingDialog;
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing() || (loadingDialog = this.j) == null) {
            return;
        }
        Intrinsics.checkNotNull(loadingDialog);
        if (loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.j;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.dismiss();
        }
    }

    public final void dismissProgressDialogWithTitleAndProgress() {
        ProgressDialog progressDialog;
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing() || (progressDialog = this.l) == null) {
            return;
        }
        Intrinsics.checkNotNull(progressDialog);
        if (progressDialog.isShowing()) {
            ProgressDialog progressDialog2 = this.l;
            Intrinsics.checkNotNull(progressDialog2);
            progressDialog2.dismiss();
        }
    }

    public void dismissQRCodeProgress() {
        QRCodeLoadingDialog qRCodeLoadingDialog;
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing() || (qRCodeLoadingDialog = this.k) == null) {
            return;
        }
        Intrinsics.checkNotNull(qRCodeLoadingDialog);
        if (qRCodeLoadingDialog.isShowing()) {
            QRCodeLoadingDialog qRCodeLoadingDialog2 = this.k;
            Intrinsics.checkNotNull(qRCodeLoadingDialog2);
            qRCodeLoadingDialog2.dismiss();
        }
    }

    public final void dismissWatchDisconnectedDialog() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.i;
        if (bottomSheetDialogTwoButtons2 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtons2 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (!valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.i) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.dismiss();
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

    public final void emptyDrawable(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBatterySaveModeEnabledDialogOneButtonTitleMessage() {
        return this.h;
    }

    @Nullable
    public final LoadingDialog getProgressDialog() {
        return this.j;
    }

    @Nullable
    public final ProgressDialog getProgressDialogDefault() {
        return this.l;
    }

    @Nullable
    public final QRCodeLoadingDialog getQrCodeLoadingDialog() {
        return this.k;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getWatchDisconnectedDialog() {
        return this.i;
    }

    public final void gone(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public final void goneIF(@NotNull View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(z ? 8 : 0);
    }

    public final void hideKeyBoard() {
        FragmentActivity activity = getActivity();
        View currentFocus = activity != null ? activity.getCurrentFocus() : null;
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        FragmentActivity activity2 = getActivity();
        InputMethodManager inputMethodManager = (InputMethodManager) (activity2 != null ? activity2.getSystemService("input_method") : null);
        Intrinsics.checkNotNull(inputMethodManager);
        inputMethodManager.hideSoftInputFromWindow(currentFocus != null ? currentFocus.getWindowToken() : null, 0);
    }

    public final void hideKeyboard() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        Object systemService = requireActivity().getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        View currentFocus = requireActivity().getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(getActivity());
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public final void inVisible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
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

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AppUtils.setLocale(SessionManager.getInstance(getContext()).getSelectedLanguage(), getContext());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.j = new LoadingDialog(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.k = new QRCodeLoadingDialog(requireContext2);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void openKeyBoard(@Nullable final EditText editText) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.coveiot.android.theme.k
            @Override // java.lang.Runnable
            public final void run() {
                BaseFragment.f(editText, this);
            }
        });
    }

    public final void setBatterySaveModeEnabledDialogOneButtonTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.h = bottomSheetDialogImageTitleMessage;
    }

    public final void setProgressDialog(@Nullable LoadingDialog loadingDialog) {
        this.j = loadingDialog;
    }

    public final void setProgressDialogDefault(@Nullable ProgressDialog progressDialog) {
        this.l = progressDialog;
    }

    public final void setQrCodeLoadingDialog(@Nullable QRCodeLoadingDialog qRCodeLoadingDialog) {
        this.k = qRCodeLoadingDialog;
    }

    public final void setWatchDisconnectedDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.i = bottomSheetDialogTwoButtons;
    }

    public final void showBatterySaverModeEnabledDialog(@Nullable BatterySaverModeDialogClickCallback batterySaverModeDialogClickCallback) {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        boolean z = false;
        if (this.h == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
            String string = getResources().getString(R.string.battery_save_mode_enabled);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…attery_save_mode_enabled)");
            String string2 = getResources().getString(R.string.disable_battery_saver_mode);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…sable_battery_saver_mode)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = new BottomSheetDialogImageTitleMessage(requireActivity, drawable, string, string2, false);
            this.h = bottomSheetDialogImageTitleMessage2;
            bottomSheetDialogImageTitleMessage2.setCancelable(false);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.h;
            if (bottomSheetDialogImageTitleMessage3 != null) {
                bottomSheetDialogImageTitleMessage3.setCancelableOutside(false);
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.h;
            if (bottomSheetDialogImageTitleMessage4 != null) {
                String string3 = getResources().getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(\n   …R.string.ok\n            )");
                bottomSheetDialogImageTitleMessage4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.h
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BaseFragment.g(BaseFragment.this, view);
                    }
                });
            }
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.h;
        if (bottomSheetDialogImageTitleMessage5 != null && !bottomSheetDialogImageTitleMessage5.isShowing()) {
            z = true;
        }
        if (!z || (bottomSheetDialogImageTitleMessage = this.h) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessage.show();
    }

    public final void showCommonMessageDialog(@NotNull String message, boolean z) {
        Window window;
        Intrinsics.checkNotNullParameter(message, "message");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(requireContext, message, z, false, 8, null);
        commonMessageDialog.show(getChildFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.theme.l
            @Override // java.lang.Runnable
            public final void run() {
                BaseFragment.h(BaseFragment.this, commonMessageDialog);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void showNoBluetoothDialog() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.bluetooth_off);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
        String string2 = getString(R.string.bluetooth_off_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = requireActivity().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "requireActivity().getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseFragment.i(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showNoInternetDialog() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_connection)");
        String string2 = getString(R.string.please_check_network);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_check_network)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = requireActivity().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "requireActivity().getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseFragment.j(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void showNoInternetMessage() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        Toast.makeText(requireContext(), getString(R.string.please_check_network), 1).show();
    }

    public final void showProgress(boolean z) {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        LoadingDialog loadingDialog = this.j;
        if (loadingDialog != null) {
            loadingDialog.setTitle(getString(R.string.please_wait));
        }
        LoadingDialog loadingDialog2 = this.j;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.setCancelable(z);
        LoadingDialog loadingDialog3 = this.j;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCanceledOnTouchOutside(z);
        LoadingDialog loadingDialog4 = this.j;
        Intrinsics.checkNotNull(loadingDialog4);
        if (loadingDialog4.isShowing()) {
            return;
        }
        LoadingDialog loadingDialog5 = this.j;
        Intrinsics.checkNotNull(loadingDialog5);
        loadingDialog5.show();
    }

    @NotNull
    public final ProgressListener showProgressDialogWithTitleAndProgress(@NotNull String title, boolean z, int i) {
        Intrinsics.checkNotNullParameter(title, "title");
        ProgressListener progressListener = new ProgressListener() { // from class: com.coveiot.android.theme.BaseFragment$showProgressDialogWithTitleAndProgress$listner$1
            @Override // com.coveiot.android.theme.BaseFragment.ProgressListener
            public void onProgressChanged(int i2) {
                if (BaseFragment.this.getProgressDialogDefault() != null) {
                    ProgressDialog progressDialogDefault = BaseFragment.this.getProgressDialogDefault();
                    Intrinsics.checkNotNull(progressDialogDefault);
                    progressDialogDefault.setProgress(i2);
                }
            }
        };
        if (isAdded() && getView() != null && requireActivity() != null && !requireActivity().isFinishing()) {
            if (this.l == null) {
                ProgressDialog progressDialog = new ProgressDialog(requireContext(), R.style.DataWithProgressDialogTheme);
                this.l = progressDialog;
                Intrinsics.checkNotNull(progressDialog);
                progressDialog.setProgressStyle(1);
                ProgressDialog progressDialog2 = this.l;
                Intrinsics.checkNotNull(progressDialog2);
                progressDialog2.setCanceledOnTouchOutside(false);
                ProgressDialog progressDialog3 = this.l;
                Intrinsics.checkNotNull(progressDialog3);
                progressDialog3.setCancelable(false);
                ProgressDialog progressDialog4 = this.l;
                Intrinsics.checkNotNull(progressDialog4);
                progressDialog4.setMax(i);
            }
            ProgressDialog progressDialog5 = this.l;
            Intrinsics.checkNotNull(progressDialog5);
            progressDialog5.setProgress(0);
            ProgressDialog progressDialog6 = this.l;
            Intrinsics.checkNotNull(progressDialog6);
            progressDialog6.setTitle(title);
            ProgressDialog progressDialog7 = this.l;
            Intrinsics.checkNotNull(progressDialog7);
            if (!progressDialog7.isShowing()) {
                ProgressDialog progressDialog8 = this.l;
                Intrinsics.checkNotNull(progressDialog8);
                progressDialog8.show();
            }
        }
        return progressListener;
    }

    public final void showProgressWithTitle(@NotNull String title, boolean z) {
        LoadingDialog loadingDialog;
        Intrinsics.checkNotNullParameter(title, "title");
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing() || (loadingDialog = this.j) == null) {
            return;
        }
        Intrinsics.checkNotNull(loadingDialog);
        loadingDialog.setTitle(title);
        LoadingDialog loadingDialog2 = this.j;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.setCancelable(z);
        LoadingDialog loadingDialog3 = this.j;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCanceledOnTouchOutside(z);
        LoadingDialog loadingDialog4 = this.j;
        Intrinsics.checkNotNull(loadingDialog4);
        loadingDialog4.show();
    }

    public final void showProgresswithMsg(@NotNull String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        LoadingDialog loadingDialog = this.j;
        if (loadingDialog != null) {
            loadingDialog.setTitle(msg);
        }
        LoadingDialog loadingDialog2 = this.j;
        Intrinsics.checkNotNull(loadingDialog2);
        loadingDialog2.setCancelable(false);
        LoadingDialog loadingDialog3 = this.j;
        Intrinsics.checkNotNull(loadingDialog3);
        loadingDialog3.setCanceledOnTouchOutside(false);
        LoadingDialog loadingDialog4 = this.j;
        Intrinsics.checkNotNull(loadingDialog4);
        if (loadingDialog4.isShowing()) {
            return;
        }
        LoadingDialog loadingDialog5 = this.j;
        Intrinsics.checkNotNull(loadingDialog5);
        loadingDialog5.show();
    }

    public final void showQRCodeProgress(boolean z, @NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        QRCodeLoadingDialog qRCodeLoadingDialog = this.k;
        if (qRCodeLoadingDialog != null) {
            qRCodeLoadingDialog.setTitle(getString(R.string.please_wait));
        }
        QRCodeLoadingDialog qRCodeLoadingDialog2 = this.k;
        Intrinsics.checkNotNull(qRCodeLoadingDialog2);
        qRCodeLoadingDialog2.setCancelable(z);
        QRCodeLoadingDialog qRCodeLoadingDialog3 = this.k;
        Intrinsics.checkNotNull(qRCodeLoadingDialog3);
        qRCodeLoadingDialog3.setCanceledOnTouchOutside(z);
        QRCodeLoadingDialog qRCodeLoadingDialog4 = this.k;
        if (qRCodeLoadingDialog4 != null) {
            qRCodeLoadingDialog4.setText(message);
        }
        QRCodeLoadingDialog qRCodeLoadingDialog5 = this.k;
        Intrinsics.checkNotNull(qRCodeLoadingDialog5);
        if (qRCodeLoadingDialog5.isShowing()) {
            return;
        }
        QRCodeLoadingDialog qRCodeLoadingDialog6 = this.k;
        Intrinsics.checkNotNull(qRCodeLoadingDialog6);
        qRCodeLoadingDialog6.show();
    }

    public final void showWatchDisconnectedDialog() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.your_watch_disconnected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.your_watch_disconnected)");
        String string2 = getString(R.string.watch_disconnected_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_disconnected_msg)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        this.i = bottomSheetDialogTwoButtons2;
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons2.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.theme.BaseFragment$showWatchDisconnectedDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons watchDisconnectedDialog = BaseFragment.this.getWatchDisconnectedDialog();
                if (watchDisconnectedDialog != null) {
                    watchDisconnectedDialog.dismiss();
                }
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.i;
        if (bottomSheetDialogTwoButtons3 != null) {
            String string4 = getString(R.string.connect_now);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.connect_now)");
            bottomSheetDialogTwoButtons3.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.theme.BaseFragment$showWatchDisconnectedDialog$2
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    if (AppUtils.isBluetoothEnabled(BaseFragment.this.requireContext())) {
                        BleApiManager.getInstance(BaseFragment.this.requireContext()).getBleApi().restartService();
                        BottomSheetDialogTwoButtons watchDisconnectedDialog = BaseFragment.this.getWatchDisconnectedDialog();
                        if (watchDisconnectedDialog != null) {
                            watchDisconnectedDialog.dismiss();
                            return;
                        }
                        return;
                    }
                    BottomSheetDialogTwoButtons watchDisconnectedDialog2 = BaseFragment.this.getWatchDisconnectedDialog();
                    if (watchDisconnectedDialog2 != null) {
                        watchDisconnectedDialog2.dismiss();
                    }
                    BaseFragment.this.showNoBluetoothDialog();
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.i;
        Boolean valueOf = bottomSheetDialogTwoButtons4 != null ? Boolean.valueOf(bottomSheetDialogTwoButtons4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue() || (bottomSheetDialogTwoButtons = this.i) == null) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void toast(@NotNull Context context, @NotNull CharSequence message) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(context, message, 1).show();
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
