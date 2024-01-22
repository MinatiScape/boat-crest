package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveScheduleEventRes;
import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ScheduleViewModel$callSaveApi$1 implements CoveApiListener<SaveScheduleEventRes, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ScheduleViewModel f5201a;

    public ScheduleViewModel$callSaveApi$1(ScheduleViewModel scheduleViewModel) {
        this.f5201a = scheduleViewModel;
    }

    public static final void b(BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage, ScheduleViewModel this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        if (this$0.getDialogListener() instanceof Activity) {
            DialogListener dialogListener = this$0.getDialogListener();
            Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
            ((Activity) dialogListener).finish();
        }
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
        this.f5201a.getDialogListener().showErrorDialog();
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@Nullable SaveScheduleEventRes saveScheduleEventRes) {
        this.f5201a.getDialogListener().onDismiss();
        DialogListener dialogListener = this.f5201a.getDialogListener();
        Intrinsics.checkNotNull(dialogListener, "null cannot be cast to non-null type android.app.Activity");
        if (UserDataManager.getInstance((Activity) dialogListener).getDoNotDisturbData() != null) {
            PayUtils payUtils = PayUtils.INSTANCE;
            DialogListener dialogListener2 = this.f5201a.getDialogListener();
            Intrinsics.checkNotNull(dialogListener2, "null cannot be cast to non-null type android.app.Activity");
            if (payUtils.isDNDEnabled(UserDataManager.getInstance((Activity) dialogListener2).getDoNotDisturbData())) {
                DialogListener dialogListener3 = this.f5201a.getDialogListener();
                Intrinsics.checkNotNull(dialogListener3, "null cannot be cast to non-null type android.app.Activity");
                Drawable drawable = this.f5201a.getContext().getResources().getDrawable(R.drawable.info_icon_new);
                Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…R.drawable.info_icon_new)");
                String string = this.f5201a.getContext().getString(R.string.success_message);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.success_message)");
                String string2 = this.f5201a.getContext().getString(R.string.turn_off_dnd_enable_sedentary);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…off_dnd_enable_sedentary)");
                final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage((Activity) dialogListener3, drawable, string, string2, false);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                bottomSheetDialogImageTitleMessage.setCancelableOutside(false);
                String string3 = this.f5201a.getContext().getResources().getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…                        )");
                final ScheduleViewModel scheduleViewModel = this.f5201a;
                bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ScheduleViewModel$callSaveApi$1.b(BottomSheetDialogImageTitleMessage.this, scheduleViewModel, view);
                    }
                });
                bottomSheetDialogImageTitleMessage.show();
                return;
            }
            this.f5201a.getDialogListener().showSuccessDialog();
            return;
        }
        this.f5201a.getDialogListener().showSuccessDialog();
    }
}
