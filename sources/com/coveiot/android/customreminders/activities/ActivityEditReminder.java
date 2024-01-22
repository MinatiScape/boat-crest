package com.coveiot.android.customreminders.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.fragments.HandWashFragmentNew;
import com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew;
import com.coveiot.android.customreminders.listeners.PostEditReminderListListener;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.customreminders.utils.CustomReminderConstants;
import com.coveiot.android.customreminders.utils.ReminderHelper;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.utils.utility.AppUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityEditReminder extends BaseActivity implements PostEditReminderListListener, MedicineReminderFragmentNew.SaveButtonListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean p;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage q;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage s;

    public static final void r(ActivityEditReminder this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetErrorDialog() {
        return this.s;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getBottomSheetSuccessDialog() {
        return this.q;
    }

    public final boolean getShouldAddToMainList() {
        return this.p;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.edit_reminder));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditReminder.r(ActivityEditReminder.this, view);
            }
        });
    }

    @Override // com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew.SaveButtonListener
    public void isAllDetailsFilled(boolean z) {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int i = R.id.fragment_container;
        if (supportFragmentManager.findFragmentById(i) instanceof HandWashFragmentNew) {
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.coveiot.android.customreminders.fragments.HandWashFragmentNew");
            ((HandWashFragmentNew) findFragmentById).onBackPressed();
        } else if (getSupportFragmentManager().findFragmentById(i) instanceof MedicineReminderFragmentNew) {
            Fragment findFragmentById2 = getSupportFragmentManager().findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById2, "null cannot be cast to non-null type com.coveiot.android.customreminders.fragments.MedicineReminderFragmentNew");
            ((MedicineReminderFragmentNew) findFragmentById2).onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_reminder);
        initToolbar();
        Intent intent = getIntent();
        CustomReminderConstants.Companion companion = CustomReminderConstants.Companion;
        if (intent.hasExtra(companion.getEXTRA_CUSTOM_REMINDER_OBJECT()) && getIntent().hasExtra(companion.getEXTRA_REMINDER_POSITION()) && getIntent().hasExtra(companion.getEXTRA_REMINDER_TYPE())) {
            Serializable serializableExtra = getIntent().getSerializableExtra(companion.getEXTRA_CUSTOM_REMINDER_OBJECT());
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.customreminders.model.CustomReminder");
            CustomReminder customReminder = (CustomReminder) serializableExtra;
            int intExtra = getIntent().getIntExtra(companion.getEXTRA_REMINDER_POSITION(), -1);
            Serializable serializableExtra2 = getIntent().getSerializableExtra(companion.getEXTRA_REMINDER_TYPE());
            Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.coveiot.android.customreminders.ReminderType");
            ReminderType reminderType = (ReminderType) serializableExtra2;
            this.p = getIntent().getBooleanExtra(companion.getEXTRA_SHOULD_ADD_TO_MAIN_LIST(), false);
            if (reminderType != ReminderType.MEDICINE && reminderType != ReminderType.MEETING && reminderType != ReminderType.OTHERS) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, HandWashFragmentNew.Companion.newInstance(reminderType, customReminder, intExtra)).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MedicineReminderFragmentNew.Companion.newInstance(reminderType, customReminder, intExtra)).commit();
            }
        }
    }

    @Override // com.coveiot.android.customreminders.listeners.PostEditReminderListListener
    public void onEditReminder(int i, @NotNull CustomReminder customReminder, @NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(customReminder, "customReminder");
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        if (this.p) {
            if (!AppUtils.isNetConnected(this)) {
                Toast.makeText(this, R.string.noconnection, 0).show();
                return;
            } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
                s();
                return;
            } else {
                List<CustomReminder> reminders = PreferenceManagerCustomReminders.Companion.getReminders(this);
                if (AppUtils.isEmpty(reminders)) {
                    return;
                }
                showProgress();
                Intrinsics.checkNotNull(reminders, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.customreminders.model.CustomReminder>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.customreminders.model.CustomReminder> }");
                ArrayList<CustomReminder> arrayList = (ArrayList) reminders;
                arrayList.set(i, customReminder);
                ReminderHelper.Companion.getInstance(this).sendRemindersToWatch(arrayList, new ResultListener() { // from class: com.coveiot.android.customreminders.activities.ActivityEditReminder$onEditReminder$1
                    @Override // com.coveiot.android.customreminders.listeners.ResultListener
                    public void onError(@Nullable String str) {
                        ActivityEditReminder.this.dismissProgress();
                        ActivityEditReminder.this.showErrorDialog();
                    }

                    @Override // com.coveiot.android.customreminders.listeners.ResultListener
                    public void onSuccess() {
                        ActivityEditReminder.this.dismissProgress();
                        ActivityEditReminder.this.showSuccessDialog();
                    }
                });
                return;
            }
        }
        Intent intent = getIntent();
        intent.putExtra(CustomReminderConstants.Companion.getEXTRA_CUSTOM_REMINDER_OBJECT(), customReminder);
        setResult(-1, intent);
        finish();
    }

    public final void s() {
        if (this.r == null) {
            Drawable drawable = getDrawable(R.drawable.watch_disconnected_icon);
            Intrinsics.checkNotNull(drawable);
            String string = getString(R.string.bluetooth_disconnected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_disconnected)");
            String string2 = getString(R.string.please_connect_your_device);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.please_connect_your_device)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
            this.r = bottomSheetDialogImageTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityEditReminder$showWatchDisconnectedDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = ActivityEditReminder.this.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                    bottomSheetDialogImageTitleMessage2.dismiss();
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
        if (bottomSheetDialogImageTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.r;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
        bottomSheetDialogImageTitleMessage3.show();
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.r = bottomSheetDialogImageTitleMessage;
    }

    public final void setBottomSheetErrorDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.s = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setBottomSheetSuccessDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.q = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setShouldAddToMainList(boolean z) {
        this.p = z;
    }

    public final void showErrorDialog() {
        if (this.s == null) {
            String string = getString(R.string.failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.failed)");
            String string2 = getString(R.string.setting_could_not_be_saved);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_could_not_be_saved)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.s = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityEditReminder$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetErrorDialog = ActivityEditReminder.this.getBottomSheetErrorDialog();
                    Intrinsics.checkNotNull(bottomSheetErrorDialog);
                    bottomSheetErrorDialog.dismiss();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.s;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }

    public final void showSuccessDialog() {
        if (this.q == null) {
            String string = getString(R.string.success);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success)");
            String string2 = getString(R.string.setting_saved_successfully);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_saved_successfully)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.q = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.customreminders.activities.ActivityEditReminder$showSuccessDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonTitleMessage bottomSheetSuccessDialog = ActivityEditReminder.this.getBottomSheetSuccessDialog();
                    Intrinsics.checkNotNull(bottomSheetSuccessDialog);
                    bottomSheetSuccessDialog.dismiss();
                    ActivityEditReminder.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
        if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.q;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
        bottomSheetDialogOneButtonTitleMessage3.show();
    }
}
