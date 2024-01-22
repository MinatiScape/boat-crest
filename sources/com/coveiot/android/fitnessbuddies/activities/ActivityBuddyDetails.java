package com.coveiot.android.fitnessbuddies.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels;
import com.coveiot.android.fitnessbuddies.databinding.ActivityBuddyDetailsBinding;
import com.coveiot.android.fitnessbuddies.dialogs.FitnessBuddyLabelDialog;
import com.coveiot.android.fitnessbuddies.dialogs.RemoveBuddyDialog;
import com.coveiot.android.fitnessbuddies.fragments.NotificationFragment;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor;
import com.coveiot.android.fitnessbuddies.models.BuddyReminderModel;
import com.coveiot.android.fitnessbuddies.models.FitnessBuddiesWalkData;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.FitnessBuddiesConstants;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyGoalsDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyWalkDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityBuddyDetails extends BaseActivity implements LabelBuddiesContractor {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityBuddyDetailsBinding p;
    @Nullable
    public BuddyDetailsViewModel q;
    @Nullable
    public GetBuddyItems r;
    public int s;
    public int t;
    @Nullable
    public FitnessBuddiesLabels u;
    public boolean v;
    public int w;
    public long x;

    public static final void C(RemoveBuddyDialog removeBuddyDialog, ActivityBuddyDetails this$0, GetBuddyItems buddyItems, View view) {
        Intrinsics.checkNotNullParameter(removeBuddyDialog, "$removeBuddyDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(buddyItems, "$buddyItems");
        removeBuddyDialog.dismiss();
        this$0.showProgresswithMsg("");
        BuddyDetailsViewModel buddyDetailsViewModel = this$0.q;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        Integer num = buddyItems.getBuddyDetails().userId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyItems.buddyDetails.userId");
        buddyDetailsViewModel.unFriendBuddy(num.intValue());
    }

    public static final void D(ActivityBuddyDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void G() {
    }

    public static final void I(ActivityBuddyDetails this$0, FitnessBuddyLabelDialog fitnessBuddyLabelDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fitnessBuddyLabelDialog, "$fitnessBuddyLabelDialog");
        FitnessBuddiesLabels fitnessBuddiesLabels = this$0.u;
        Intrinsics.checkNotNull(fitnessBuddiesLabels);
        if (fitnessBuddiesLabels == FitnessBuddiesLabels.CHEER) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.CHEER_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else {
            FitnessBuddiesLabels fitnessBuddiesLabels2 = this$0.u;
            Intrinsics.checkNotNull(fitnessBuddiesLabels2);
            if (fitnessBuddiesLabels2 == FitnessBuddiesLabels.APPLAUD) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.APPLAUD_POPUP.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            } else {
                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.NOTIFICATION_SCREEN.getValue());
                analyticsLog3.setDescription(FirebaseEventParams.Description.NUDGE_POPUP.getValue());
                analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            }
        }
        int i = 0;
        GetBuddyItems getBuddyItems = this$0.r;
        if (getBuddyItems != null) {
            Intrinsics.checkNotNull(getBuddyItems);
            if (getBuddyItems.getGoalsList() != null) {
                GetBuddyItems getBuddyItems2 = this$0.r;
                Intrinsics.checkNotNull(getBuddyItems2);
                Iterator<BuddyGoalsDetails> it = getBuddyItems2.getGoalsList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BuddyGoalsDetails next = it.next();
                    if (kotlin.text.m.equals(next.activityType, "WALK", true)) {
                        String str = next.goalId;
                        Intrinsics.checkNotNullExpressionValue(str, "buddyItemGoals.goalId");
                        i = Integer.parseInt(str);
                        break;
                    }
                }
            }
        }
        BuddyDetailsViewModel buddyDetailsViewModel = this$0.q;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        FitnessBuddiesLabels fitnessBuddiesLabels3 = this$0.u;
        Intrinsics.checkNotNull(fitnessBuddiesLabels3);
        String obj = fitnessBuddyLabelDialog.getUpdateMsgEditText().getText().toString();
        GetBuddyItems getBuddyItems3 = this$0.r;
        Intrinsics.checkNotNull(getBuddyItems3);
        buddyDetailsViewModel.sendBuddyReaction(i, fitnessBuddiesLabels3, obj, getBuddyItems3);
        fitnessBuddyLabelDialog.dismiss();
    }

    public static final void x(ActivityBuddyDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(ActivityBuddyDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GetBuddyItems getBuddyItems = this$0.r;
        if (getBuddyItems != null) {
            Intrinsics.checkNotNull(getBuddyItems);
            this$0.B(getBuddyItems);
        }
    }

    public static final void z(ActivityBuddyDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.v) {
            this$0.H();
            return;
        }
        FitnessBuddiesLabels fitnessBuddiesLabels = this$0.u;
        Intrinsics.checkNotNull(fitnessBuddiesLabels);
        if (fitnessBuddiesLabels == FitnessBuddiesLabels.APPLAUD) {
            String string = this$0.getString(R.string.you_applauded_buddy);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.you_applauded_buddy)");
            BaseActivity.showCommonMessageDialog$default(this$0, string, false, 2, null);
            return;
        }
        String string2 = this$0.getString(R.string.you_reminded_buddy);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.you_reminded_buddy)");
        BaseActivity.showCommonMessageDialog$default(this$0, string2, false, 2, null);
    }

    public final void A(int i) {
        BuddyDetails buddyDetails;
        Integer num;
        List<BuddyReminderModel> fitnessBuddiesReminder = PreferenceManager.Companion.getFitnessBuddiesReminder(this);
        if (fitnessBuddiesReminder != null) {
            Iterator<BuddyReminderModel> it = fitnessBuddiesReminder.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BuddyReminderModel next = it.next();
                GetBuddyItems getBuddyItems = this.r;
                if (((getBuddyItems == null || (buddyDetails = getBuddyItems.getBuddyDetails()) == null || (num = buddyDetails.userId) == null || !num.equals(Integer.valueOf(next.getBuddyUserId()))) ? false : true) && next.getHasRemindedBuddy() && next.isRemindDataToday()) {
                    this.v = true;
                    ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    this.x = Math.abs(themesUtils.getMinutesTimeDifference(calendar, next.getRemindDate()));
                    break;
                }
            }
        }
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding = null;
        if (i >= 0 && i < 51) {
            this.u = FitnessBuddiesLabels.NUDGE;
            if (this.v && this.x < 30) {
                this.v = true;
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding2 = this.p;
                if (activityBuddyDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding2 = null;
                }
                activityBuddyDetailsBinding2.btnLabel.setText(getString(R.string.reminder_sent));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding3 = this.p;
                if (activityBuddyDetailsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding3 = null;
                }
                activityBuddyDetailsBinding3.clLable.setBackground(getDrawable(R.drawable.disable_button_background_new));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding4 = this.p;
                if (activityBuddyDetailsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding4 = null;
                }
                activityBuddyDetailsBinding4.btnLabel.setTextColor(getColor(R.color.color_039268));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding5 = this.p;
                if (activityBuddyDetailsBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBuddyDetailsBinding = activityBuddyDetailsBinding5;
                }
                activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circular_green_tick, 0, 0, 0);
                return;
            }
            this.v = false;
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding6 = this.p;
            if (activityBuddyDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding6 = null;
            }
            activityBuddyDetailsBinding6.btnLabel.setText(getString(R.string.remind_buddy));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding7 = this.p;
            if (activityBuddyDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding7 = null;
            }
            activityBuddyDetailsBinding7.btnLabel.setTextColor(getColor(R.color.main_text_color));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding8 = this.p;
            if (activityBuddyDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding8 = null;
            }
            activityBuddyDetailsBinding8.clLable.setBackground(getDrawable(R.drawable.enable_button_background));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding9 = this.p;
            if (activityBuddyDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding = activityBuddyDetailsBinding9;
            }
            activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return;
        }
        if (51 <= i && i < 100) {
            if (this.v && this.x < 30) {
                this.v = true;
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding10 = this.p;
                if (activityBuddyDetailsBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding10 = null;
                }
                activityBuddyDetailsBinding10.btnLabel.setText(getString(R.string.cheered_buddy));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding11 = this.p;
                if (activityBuddyDetailsBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding11 = null;
                }
                activityBuddyDetailsBinding11.clLable.setBackground(getDrawable(R.drawable.disable_button_background_new));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding12 = this.p;
                if (activityBuddyDetailsBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding12 = null;
                }
                activityBuddyDetailsBinding12.btnLabel.setTextColor(getColor(R.color.color_039268));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding13 = this.p;
                if (activityBuddyDetailsBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBuddyDetailsBinding = activityBuddyDetailsBinding13;
                }
                activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circular_green_tick, 0, 0, 0);
            } else {
                this.v = false;
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding14 = this.p;
                if (activityBuddyDetailsBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding14 = null;
                }
                activityBuddyDetailsBinding14.btnLabel.setText(getString(R.string.cheer_up_buddy));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding15 = this.p;
                if (activityBuddyDetailsBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding15 = null;
                }
                activityBuddyDetailsBinding15.clLable.setBackground(getDrawable(R.drawable.enable_button_background));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding16 = this.p;
                if (activityBuddyDetailsBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBuddyDetailsBinding16 = null;
                }
                activityBuddyDetailsBinding16.btnLabel.setTextColor(getColor(R.color.main_text_color));
                ActivityBuddyDetailsBinding activityBuddyDetailsBinding17 = this.p;
                if (activityBuddyDetailsBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityBuddyDetailsBinding = activityBuddyDetailsBinding17;
                }
                activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            this.u = FitnessBuddiesLabels.CHEER;
            return;
        }
        if (this.v) {
            this.v = true;
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding18 = this.p;
            if (activityBuddyDetailsBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding18 = null;
            }
            activityBuddyDetailsBinding18.btnLabel.setText(getString(R.string.applauded_buddy));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding19 = this.p;
            if (activityBuddyDetailsBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding19 = null;
            }
            activityBuddyDetailsBinding19.btnLabel.setTextColor(getColor(R.color.color_039268));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding20 = this.p;
            if (activityBuddyDetailsBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding20 = null;
            }
            activityBuddyDetailsBinding20.clLable.setBackground(getDrawable(R.drawable.disable_button_background_new));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding21 = this.p;
            if (activityBuddyDetailsBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding = activityBuddyDetailsBinding21;
            }
            activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circular_green_tick, 0, 0, 0);
        } else {
            this.v = false;
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding22 = this.p;
            if (activityBuddyDetailsBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding22 = null;
            }
            activityBuddyDetailsBinding22.btnLabel.setText(getString(R.string.applaud_buddy));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding23 = this.p;
            if (activityBuddyDetailsBinding23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding23 = null;
            }
            activityBuddyDetailsBinding23.btnLabel.setTextColor(getColor(R.color.main_text_color));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding24 = this.p;
            if (activityBuddyDetailsBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding24 = null;
            }
            activityBuddyDetailsBinding24.clLable.setBackground(getDrawable(R.drawable.enable_button_background));
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding25 = this.p;
            if (activityBuddyDetailsBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding = activityBuddyDetailsBinding25;
            }
            activityBuddyDetailsBinding.btnLabel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        this.u = FitnessBuddiesLabels.APPLAUD;
    }

    public final void B(final GetBuddyItems getBuddyItems) {
        final RemoveBuddyDialog removeBuddyDialog = new RemoveBuddyDialog(this, getBuddyItems);
        removeBuddyDialog.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBuddyDetails.C(RemoveBuddyDialog.this, this, getBuddyItems, view);
            }
        });
        removeBuddyDialog.show();
    }

    public final void E() {
        BuddyDetails buddyDetails;
        BuddyDetails buddyDetails2;
        int abs = Math.abs(this.s - this.w);
        int i = this.s;
        int i2 = this.w;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding = null;
        r10 = null;
        String str = null;
        r10 = null;
        String str2 = null;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding2 = null;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding3 = null;
        if (i > i2) {
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding4 = this.p;
            if (activityBuddyDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding4 = null;
            }
            TextView textView = activityBuddyDetailsBinding4.tvInsightsDesc;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.insights_desc);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.insights_desc)");
            Object[] objArr = new Object[3];
            GetBuddyItems getBuddyItems = this.r;
            if (getBuddyItems != null && (buddyDetails2 = getBuddyItems.getBuddyDetails()) != null) {
                str = buddyDetails2.name;
            }
            objArr[0] = str;
            objArr[1] = String.valueOf(abs);
            objArr[2] = getString(R.string.ahead_of);
            String format = String.format(locale, string, Arrays.copyOf(objArr, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            textView.setText(format);
        } else if (i < i2) {
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding5 = this.p;
            if (activityBuddyDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBuddyDetailsBinding5 = null;
            }
            TextView textView2 = activityBuddyDetailsBinding5.tvInsightsDesc;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.ENGLISH;
            String string2 = getString(R.string.insights_desc);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.insights_desc)");
            Object[] objArr2 = new Object[3];
            GetBuddyItems getBuddyItems2 = this.r;
            if (getBuddyItems2 != null && (buddyDetails = getBuddyItems2.getBuddyDetails()) != null) {
                str2 = buddyDetails.name;
            }
            objArr2[0] = str2;
            objArr2[1] = String.valueOf(abs);
            objArr2[2] = getString(R.string.behind);
            String format2 = String.format(locale2, string2, Arrays.copyOf(objArr2, 3));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            textView2.setText(format2);
        } else if (i != 0 && i == i2) {
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding6 = this.p;
            if (activityBuddyDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding2 = activityBuddyDetailsBinding6;
            }
            activityBuddyDetailsBinding2.tvInsightsDesc.setText(getString(R.string.insights_equal_desc));
        } else if (i == 0 && i2 == 0) {
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding7 = this.p;
            if (activityBuddyDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding3 = activityBuddyDetailsBinding7;
            }
            activityBuddyDetailsBinding3.tvInsightsDesc.setText(getString(R.string.insights_zero_desc));
        } else {
            ActivityBuddyDetailsBinding activityBuddyDetailsBinding8 = this.p;
            if (activityBuddyDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBuddyDetailsBinding = activityBuddyDetailsBinding8;
            }
            activityBuddyDetailsBinding.tvInsightsDesc.setText("--");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x020c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems r13) {
        /*
            Method dump skipped, instructions count: 952
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnessbuddies.activities.ActivityBuddyDetails.F(com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems):void");
    }

    public final void H() {
        GetBuddyItems getBuddyItems = this.r;
        Intrinsics.checkNotNull(getBuddyItems);
        FitnessBuddiesLabels fitnessBuddiesLabels = this.u;
        Intrinsics.checkNotNull(fitnessBuddiesLabels);
        final FitnessBuddyLabelDialog fitnessBuddyLabelDialog = new FitnessBuddyLabelDialog(this, getBuddyItems, fitnessBuddiesLabels);
        fitnessBuddyLabelDialog.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBuddyDetails.I(ActivityBuddyDetails.this, fitnessBuddyLabelDialog, view);
            }
        });
        fitnessBuddyLabelDialog.show();
    }

    public final void J() {
        List<DailyWalkData> dailyWalkDataBetweenDates = WalkDBRead.getInstance(this).getDailyWalkDataBetweenDates(RepositoryUtils.formatDate(CoveUtils.INSTANCE.getLastMondayDate().getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"), SessionManager.getInstance(this).getConnectedDeviceMacAddress());
        if (dailyWalkDataBetweenDates == null || dailyWalkDataBetweenDates.size() <= 0) {
            return;
        }
        int size = dailyWalkDataBetweenDates.size();
        for (int i = 0; i < size; i++) {
            this.w += dailyWalkDataBetweenDates.get(i).getValue();
        }
        this.w /= dailyWalkDataBetweenDates.size();
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

    public final void initToolbar() {
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding = this.p;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding2 = null;
        if (activityBuddyDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding = null;
        }
        TextView textView = (TextView) activityBuddyDetailsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding3 = this.p;
        if (activityBuddyDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBuddyDetailsBinding2 = activityBuddyDetailsBinding3;
        }
        textView.setText(getString(R.string.buddy_details));
        ((TextView) activityBuddyDetailsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBuddyDetails.x(ActivityBuddyDetails.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityBuddyDetailsBinding inflate = ActivityBuddyDetailsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        BuddyDetailsViewModel buddyDetailsViewModel = new BuddyDetailsViewModel(this);
        this.q = buddyDetailsViewModel;
        Intrinsics.checkNotNull(buddyDetailsViewModel);
        buddyDetailsViewModel.setMLabelBuddiesContractor(this);
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getSerializable(NotificationFragment.BUDDY_ITEM) != null) {
                Bundle extras2 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras2);
                this.r = (GetBuddyItems) extras2.getSerializable(NotificationFragment.BUDDY_ITEM);
            }
        }
        J();
        GetBuddyItems getBuddyItems = this.r;
        if (getBuddyItems != null) {
            F(getBuddyItems);
        }
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding2 = this.p;
        if (activityBuddyDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding2 = null;
        }
        activityBuddyDetailsBinding2.tvRemoveBuddy.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBuddyDetails.y(ActivityBuddyDetails.this, view);
            }
        });
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding3 = this.p;
        if (activityBuddyDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBuddyDetailsBinding = activityBuddyDetailsBinding3;
        }
        activityBuddyDetailsBinding.clLable.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBuddyDetails.z(ActivityBuddyDetails.this, view);
            }
        });
        E();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void onError(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BaseActivity.showCommonMessageDialog$default(this, message, false, 2, null);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void removeBuddySuccess() {
        BuddyDetails buddyDetails;
        BuddyDetailsViewModel buddyDetailsViewModel;
        dismissProgress();
        GetBuddyItems getBuddyItems = this.r;
        if (getBuddyItems != null && (buddyDetailsViewModel = this.q) != null) {
            buddyDetailsViewModel.removeBuddyReminderModel(getBuddyItems);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.contact_removed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.contact_removed)");
        Object[] objArr = new Object[1];
        GetBuddyItems getBuddyItems2 = this.r;
        objArr[0] = (getBuddyItems2 == null || (buddyDetails = getBuddyItems2.getBuddyDetails()) == null) ? null : buddyDetails.name;
        String format = String.format(locale, string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        BaseActivity.showCommonMessageDialog$default(this, format, false, 2, null);
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.fitnessbuddies.activities.g
            @Override // java.lang.Runnable
            public final void run() {
                ActivityBuddyDetails.D(ActivityBuddyDetails.this);
            }
        }, 2500L);
    }

    public final void setGraphValues(@NotNull ArrayList<BuddyWalkDetails> walkSteps) {
        int maximumYValue;
        Intrinsics.checkNotNullParameter(walkSteps, "walkSteps");
        Collections.sort(walkSteps, new CoveUtils.BuddyWalkDataComparator());
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                String formatDate = RepositoryUtils.formatDate(CoveUtils.INSTANCE.getLastMondayDate().getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(CoveUtils.get…ate().time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate, 0, 0, 0, "Mon"));
                arrayList2.add("Mon");
            }
            if (i == 1) {
                String formatDate2 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 1).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(ThemesUtils.g…), 1).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate2, 0, 0, 0, "Tue"));
                arrayList2.add("Tue");
            }
            if (i == 2) {
                String formatDate3 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 2).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate3, "formatDate(ThemesUtils.g…), 2).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate3, 0, 0, 0, "Wed"));
                arrayList2.add("Wed");
            }
            if (i == 3) {
                String formatDate4 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 3).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate4, "formatDate(ThemesUtils.g…), 3).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate4, 0, 0, 0, "Thu"));
                arrayList2.add("Thu");
            }
            if (i == 4) {
                String formatDate5 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 4).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate5, "formatDate(ThemesUtils.g…), 4).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate5, 0, 0, 0, "Fri"));
                arrayList2.add("Fri");
            }
            if (i == 5) {
                String formatDate6 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 5).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate6, "formatDate(ThemesUtils.g…), 5).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate6, 0, 0, 0, "Sat"));
                arrayList2.add("Sat");
            }
            if (i == 6) {
                String formatDate7 = RepositoryUtils.formatDate(ThemesUtils.INSTANCE.getPreviousDate(CoveUtils.INSTANCE.getLastMondayDate(), 6).getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate7, "formatDate(ThemesUtils.g…), 6).time, \"yyyy-MM-dd\")");
                arrayList.add(new FitnessBuddiesWalkData(formatDate7, 0, 0, 0, "Sun"));
                arrayList2.add("Sun");
            }
        }
        Iterator<BuddyWalkDetails> it = walkSteps.iterator();
        while (it.hasNext()) {
            BuddyWalkDetails next = it.next();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                FitnessBuddiesWalkData fitnessBuddiesWalkData = (FitnessBuddiesWalkData) it2.next();
                if (next.date.equals(fitnessBuddiesWalkData.getDate())) {
                    Integer num = next.steps;
                    fitnessBuddiesWalkData.setSteps(num == null ? 0 : num.intValue());
                    Integer num2 = next.calories;
                    fitnessBuddiesWalkData.setCalories(num2 == null ? 0 : num2.intValue());
                    int i2 = next.meters;
                    if (i2 == null) {
                        i2 = 0;
                    }
                    next.meters = i2;
                }
            }
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (IndexedValue indexedValue : CollectionsKt___CollectionsKt.withIndex(arrayList)) {
            arrayList3.add(new BarEntry(indexedValue.getIndex(), ((FitnessBuddiesWalkData) indexedValue.getValue()).getSteps()));
            arrayList4.add(String.valueOf(ThemesUtils.INSTANCE.formattedDate(((FitnessBuddiesWalkData) indexedValue.getValue()).getDate(), "dd MMM, yyyy")));
        }
        if (arrayList4.size() != 7) {
            for (int size = arrayList4.size() - 1; size < 7; size++) {
                arrayList4.add("--");
            }
        }
        BarDataSet barDataSet = new BarDataSet(arrayList3, "");
        barDataSet.setDrawValues(false);
        Resources resources = getResources();
        int i3 = R.color.steps_graph_color;
        barDataSet.setBarShadowColor(resources.getColor(i3));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(i3));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i4 = 0; i4 < entryCount; i4++) {
                iArr[i4] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding = this.p;
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding2 = null;
        if (activityBuddyDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding = null;
        }
        activityBuddyDetailsBinding.stepsGraph.setData(barData);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding3 = this.p;
        if (activityBuddyDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding3 = null;
        }
        activityBuddyDetailsBinding3.stepsGraph.setFitBars(true);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding4 = this.p;
        if (activityBuddyDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding4 = null;
        }
        activityBuddyDetailsBinding4.stepsGraph.setDrawValueAboveBar(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding5 = this.p;
        if (activityBuddyDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding5 = null;
        }
        activityBuddyDetailsBinding5.stepsGraph.setDrawGridBackground(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding6 = this.p;
        if (activityBuddyDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding6 = null;
        }
        activityBuddyDetailsBinding6.stepsGraph.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding7 = this.p;
        if (activityBuddyDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding7 = null;
        }
        activityBuddyDetailsBinding7.stepsGraph.setDescription(description);
        Resources resources2 = getResources();
        int i5 = R.color.steps_graph_color;
        barData.setValueTextColor(resources2.getColor(i5));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding8 = this.p;
        if (activityBuddyDetailsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding8 = null;
        }
        activityBuddyDetailsBinding8.stepsGraph.getPaint(7).setColor(getResources().getColor(i5));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding9 = this.p;
        if (activityBuddyDetailsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding9 = null;
        }
        activityBuddyDetailsBinding9.stepsGraph.setDrawBorders(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding10 = this.p;
        if (activityBuddyDetailsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding10 = null;
        }
        activityBuddyDetailsBinding10.stepsGraph.setAutoScaleMinMaxEnabled(false);
        int i6 = R.layout.custom_marker_view_steps_hr;
        String BUDDY_DETAILS = FitnessBuddiesConstants.BUDDY_DETAILS;
        Intrinsics.checkNotNullExpressionValue(BUDDY_DETAILS, "BUDDY_DETAILS");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(this, i6, BUDDY_DETAILS, 7, arrayList4);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding11 = this.p;
        if (activityBuddyDetailsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding11 = null;
        }
        customMarkerViewVitals.setChartView(activityBuddyDetailsBinding11.stepsGraph);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding12 = this.p;
        if (activityBuddyDetailsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding12 = null;
        }
        activityBuddyDetailsBinding12.stepsGraph.setMarker(customMarkerViewVitals);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding13 = this.p;
        if (activityBuddyDetailsBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding13 = null;
        }
        YAxis axisLeft = activityBuddyDetailsBinding13.stepsGraph.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setYOffset(10.0f);
        axisLeft.setXOffset(15.0f);
        if (ThemesUtils.INSTANCE.getMaximumYValue(arrayList3) == 0) {
            axisLeft.mAxisMaximum = 1000.0f;
        } else {
            axisLeft.mAxisMaximum = (maximumYValue * 150) / 100.0f;
        }
        Resources resources3 = getResources();
        int i7 = R.color.color_757575;
        axisLeft.setAxisLineColor(resources3.getColor(i7));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding14 = this.p;
        if (activityBuddyDetailsBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding14 = null;
        }
        activityBuddyDetailsBinding14.stepsGraph.getAxisRight().setEnabled(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding15 = this.p;
        if (activityBuddyDetailsBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding15 = null;
        }
        XAxis xAxis = activityBuddyDetailsBinding15.stepsGraph.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.fitnessbuddies.activities.ActivityBuddyDetails$setGraphValues$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                int roundToInt;
                if (f >= 0.0f && (roundToInt = kotlin.math.c.roundToInt(f)) >= 0 && roundToInt < arrayList2.size() && roundToInt == ((int) f)) {
                    String str = arrayList2.get(roundToInt);
                    Intrinsics.checkNotNullExpressionValue(str, "lables[index]");
                    return str;
                }
                return "";
            }
        });
        xAxis.setAxisLineColor(getResources().getColor(i7));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding16 = this.p;
        if (activityBuddyDetailsBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding16 = null;
        }
        activityBuddyDetailsBinding16.stepsGraph.getAxisLeft().setStartAtZero(true);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding17 = this.p;
        if (activityBuddyDetailsBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding17 = null;
        }
        activityBuddyDetailsBinding17.stepsGraph.getAxisRight().setStartAtZero(true);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding18 = this.p;
        if (activityBuddyDetailsBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding18 = null;
        }
        activityBuddyDetailsBinding18.stepsGraph.setVisibleXRangeMaximum(24.0f);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding19 = this.p;
        if (activityBuddyDetailsBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding19 = null;
        }
        activityBuddyDetailsBinding19.stepsGraph.setVisibleXRangeMinimum(7.0f);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding20 = this.p;
        if (activityBuddyDetailsBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding20 = null;
        }
        activityBuddyDetailsBinding20.stepsGraph.getAxisLeft().setTextColor(getResources().getColor(i7));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding21 = this.p;
        if (activityBuddyDetailsBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding21 = null;
        }
        activityBuddyDetailsBinding21.stepsGraph.getXAxis().setTextColor(getResources().getColor(i7));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding22 = this.p;
        if (activityBuddyDetailsBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding22 = null;
        }
        activityBuddyDetailsBinding22.stepsGraph.getLegend().setTextColor(getResources().getColor(i7));
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding23 = this.p;
        if (activityBuddyDetailsBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBuddyDetailsBinding23 = null;
        }
        activityBuddyDetailsBinding23.stepsGraph.getLegend().setEnabled(false);
        ActivityBuddyDetailsBinding activityBuddyDetailsBinding24 = this.p;
        if (activityBuddyDetailsBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBuddyDetailsBinding2 = activityBuddyDetailsBinding24;
        }
        activityBuddyDetailsBinding2.stepsGraph.invalidate();
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor
    public void showLabelSuccessMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        BaseActivity.showCommonMessageDialog$default(this, message, false, 2, null);
        A(this.t);
    }
}
