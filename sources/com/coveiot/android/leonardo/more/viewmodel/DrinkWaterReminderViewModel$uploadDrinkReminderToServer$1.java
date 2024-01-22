package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsRes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DrinkWaterReminderViewModel$uploadDrinkReminderToServer$1 implements CoveApiListener<SaveDrinkReminderSettingsRes, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DrinkWaterReminderViewModel f5187a;
    public final /* synthetic */ boolean b;

    public DrinkWaterReminderViewModel$uploadDrinkReminderToServer$1(DrinkWaterReminderViewModel drinkWaterReminderViewModel, boolean z) {
        this.f5187a = drinkWaterReminderViewModel;
        this.b = z;
    }

    public static final void b(BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage, DrinkWaterReminderViewModel this$0, View view) {
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
        this.f5187a.getDialogListener().onDismiss();
        Toast.makeText(this.f5187a.getContext(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, 0).show();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0054, code lost:
        if (com.coveiot.covepreferences.UserDataManager.getInstance((android.app.Activity) r8).getDoNotDisturbData().isSchedule_on_off() != false) goto L9;
     */
    @Override // com.coveiot.coveaccess.CoveApiListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsRes r8) {
        /*
            r7 = this;
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            r8.onDismiss()
            boolean r8 = r7.b
            if (r8 == 0) goto Lfd
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            java.lang.String r0 = "null cannot be cast to non-null type android.app.Activity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r0)
            android.app.Activity r8 = (android.app.Activity) r8
            com.coveiot.covepreferences.UserDataManager r8 = com.coveiot.covepreferences.UserDataManager.getInstance(r8)
            com.coveiot.covepreferences.data.DoNotDisturbData r8 = r8.getDoNotDisturbData()
            if (r8 == 0) goto Lf3
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r0)
            android.app.Activity r8 = (android.app.Activity) r8
            com.coveiot.covepreferences.UserDataManager r8 = com.coveiot.covepreferences.UserDataManager.getInstance(r8)
            com.coveiot.covepreferences.data.DoNotDisturbData r8 = r8.getDoNotDisturbData()
            boolean r8 = r8.isDnd_on_off()
            if (r8 != 0) goto L56
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r0)
            android.app.Activity r8 = (android.app.Activity) r8
            com.coveiot.covepreferences.UserDataManager r8 = com.coveiot.covepreferences.UserDataManager.getInstance(r8)
            com.coveiot.covepreferences.data.DoNotDisturbData r8 = r8.getDoNotDisturbData()
            boolean r8 = r8.isSchedule_on_off()
            if (r8 == 0) goto Lf3
        L56:
            com.coveiot.android.leonardo.utils.PayUtils r8 = com.coveiot.android.leonardo.utils.PayUtils.INSTANCE
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r1 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r1 = r1.getDialogListener()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
            android.app.Activity r1 = (android.app.Activity) r1
            com.coveiot.covepreferences.UserDataManager r1 = com.coveiot.covepreferences.UserDataManager.getInstance(r1)
            com.coveiot.covepreferences.data.DoNotDisturbData r1 = r1.getDoNotDisturbData()
            boolean r8 = r8.isDNDEnabled(r1)
            if (r8 == 0) goto Le9
            com.coveiot.android.theme.BottomSheetDialogImageTitleMessage r8 = new com.coveiot.android.theme.BottomSheetDialogImageTitleMessage
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r1 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r1 = r1.getDialogListener()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
            r2 = r1
            android.app.Activity r2 = (android.app.Activity) r2
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r0 = r7.f5187a
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131232706(0x7f0807c2, float:1.8081529E38)
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r1)
            java.lang.String r0 = "context.resources.getDra…R.drawable.info_icon_new)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r0 = r7.f5187a
            android.content.Context r0 = r0.getContext()
            r1 = 2131955102(0x7f130d9e, float:1.9546722E38)
            java.lang.String r4 = r0.getString(r1)
            java.lang.String r0 = "context.getString(R.string.success_message)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r0 = r7.f5187a
            android.content.Context r0 = r0.getContext()
            r1 = 2131955370(0x7f130eaa, float:1.9547266E38)
            java.lang.String r5 = r0.getString(r1)
            java.lang.String r0 = "context.getString(R.stri…off_dnd_enable_sedentary)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            r6 = 0
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            r0 = 0
            r8.setCancelable(r0)
            r8.setCancelableOutside(r0)
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r0 = r7.f5187a
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131954114(0x7f1309c2, float:1.9544718E38)
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "context.resources.getStr…                        )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r1 = r7.f5187a
            com.coveiot.android.leonardo.more.viewmodel.d r2 = new com.coveiot.android.leonardo.more.viewmodel.d
            r2.<init>()
            r8.setPositiveButton(r0, r2)
            r8.show()
            goto L106
        Le9:
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            r8.showSuccessDialog()
            goto L106
        Lf3:
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            r8.showSuccessDialog()
            goto L106
        Lfd:
            com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel r8 = r7.f5187a
            com.coveiot.android.leonardo.listener.DialogListener r8 = r8.getDialogListener()
            r8.showSuccessDialog()
        L106:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel$uploadDrinkReminderToServer$1.onSuccess(com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsRes):void");
    }
}
