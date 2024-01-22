package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class WomenWellnessViewModel$sendWomenWellnessDataToBand$2 implements SettingsResultListener {
    public final /* synthetic */ WomenWellnessViewModel h;
    public final /* synthetic */ WomenWellnessData i;

    public WomenWellnessViewModel$sendWomenWellnessDataToBand$2(WomenWellnessViewModel womenWellnessViewModel, WomenWellnessData womenWellnessData) {
        this.h = womenWellnessViewModel;
        this.i = womenWellnessData;
    }

    public static final void b(BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage, WomenWellnessViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        if (this$0.getDialogListener() instanceof Activity) {
            DialogListener dialogListener = this$0.getDialogListener();
            Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) dialogListener).finish();
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        WomenWellnessViewModel womenWellnessViewModel = this.h;
        if (womenWellnessViewModel.dialogListener != null) {
            womenWellnessViewModel.getDialogListener().showErrorDialog();
        }
        LogHelper.d(this.h.getTAG(), error.toString());
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        LogHelper.d(this.h.getTAG(), response.toString());
        this.h.n(this.i);
        WomenWellnessViewModel womenWellnessViewModel = this.h;
        if (womenWellnessViewModel.dialogListener != null) {
            DialogListener dialogListener = womenWellnessViewModel.getDialogListener();
            Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
            if (UserDataManager.getInstance((Activity) dialogListener).getDoNotDisturbData() != null) {
                PayUtils payUtils = PayUtils.INSTANCE;
                DialogListener dialogListener2 = this.h.getDialogListener();
                Intrinsics.checkNotNull(dialogListener2, "null cannot be cast to non-null type android.app.Activity");
                if (payUtils.isDNDEnabled(UserDataManager.getInstance((Activity) dialogListener2).getDoNotDisturbData())) {
                    DialogListener dialogListener3 = this.h.getDialogListener();
                    Intrinsics.checkNotNull(dialogListener3, "null cannot be cast to non-null type android.app.Activity");
                    Drawable drawable = this.h.getContext().getResources().getDrawable(R.drawable.info_icon_new);
                    Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…R.drawable.info_icon_new)");
                    String string = this.h.getContext().getString(R.string.success_message);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.success_message)");
                    String string2 = this.h.getContext().getString(R.string.turn_off_dnd_enable_sedentary);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…off_dnd_enable_sedentary)");
                    final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage((Activity) dialogListener3, drawable, string, string2, false);
                    bottomSheetDialogImageTitleMessage.setCancelable(false);
                    bottomSheetDialogImageTitleMessage.setCancelableOutside(false);
                    String string3 = this.h.getContext().getResources().getString(R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…                        )");
                    final WomenWellnessViewModel womenWellnessViewModel2 = this.h;
                    bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.n
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            WomenWellnessViewModel$sendWomenWellnessDataToBand$2.b(BottomSheetDialogImageTitleMessage.this, womenWellnessViewModel2, view);
                        }
                    });
                    bottomSheetDialogImageTitleMessage.show();
                } else {
                    this.h.getDialogListener().showSuccessDialog();
                }
            } else {
                this.h.getDialogListener().showSuccessDialog();
            }
        }
        this.h.saveWomenWellnessSettings(this.i);
    }
}
