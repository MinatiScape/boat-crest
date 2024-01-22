package com.google.android.material.timepicker;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.timepicker.ClockHandView;
import com.google.android.material.timepicker.TimePickerView;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes10.dex */
public class d implements ClockHandView.OnRotateListener, TimePickerView.g, TimePickerView.f, ClockHandView.OnActionUpListener, e {
    public static final String[] m = {BleConst.CMD_Reset, "1", "2", "3", BleConst.GetDeviceInfo, BleConst.SetDeviceInfo, BleConst.CMD_Set_Mac, BleConst.GetStepGoal, BleConst.SetStepGoal, BleConst.GetDeviceBatteryLevel, BleConst.GetDeviceMacAddress, BleConst.GetDeviceVersion};
    public static final String[] n = {"00", "2", BleConst.GetDeviceInfo, BleConst.CMD_Set_Mac, BleConst.SetStepGoal, BleConst.GetDeviceMacAddress, BleConst.CMD_Reset, BleConst.SetMotorVibrationWithTimes, BleConst.SetDeviceName, BleConst.SetAutomaticHRMonitoring, BleConst.SetAlarmClockWithAllClock, BleConst.SetSedentaryReminder};
    public static final String[] o = {"00", BleConst.SetDeviceInfo, BleConst.GetDeviceMacAddress, BleConst.GetDeviceName, BleConst.SetAlarmClockWithAllClock, BleConst.GetDetailActivityData, BleConst.EnterActivityMode, BleConst.BackHomeView, BleConst.GPSControlCommand, BleConst.Braceletdial, BleConst.CMD_Get_WorkOutReminder, "55"};
    public final TimePickerView h;
    public final TimeModel i;
    public float j;
    public float k;
    public boolean l = false;

    /* loaded from: classes10.dex */
    public class a extends com.google.android.material.timepicker.a {
        public a(Context context, int i) {
            super(context, i);
        }

        @Override // com.google.android.material.timepicker.a, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(R.string.material_hour_suffix, String.valueOf(d.this.i.c())));
        }
    }

    /* loaded from: classes10.dex */
    public class b extends com.google.android.material.timepicker.a {
        public b(Context context, int i) {
            super(context, i);
        }

        @Override // com.google.android.material.timepicker.a, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(R.string.material_minute_suffix, String.valueOf(d.this.i.l)));
        }
    }

    public d(TimePickerView timePickerView, TimeModel timeModel) {
        this.h = timePickerView;
        this.i = timeModel;
        f();
    }

    @Override // com.google.android.material.timepicker.TimePickerView.f
    public void a(int i) {
        this.i.j(i);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.g
    public void b(int i) {
        h(i, true);
    }

    public final int d() {
        return this.i.j == 1 ? 15 : 30;
    }

    public final String[] e() {
        return this.i.j == 1 ? n : m;
    }

    public void f() {
        if (this.i.j == 0) {
            this.h.q();
        }
        this.h.d(this);
        this.h.m(this);
        this.h.l(this);
        this.h.j(this);
        j();
        invalidate();
    }

    public final void g(int i, int i2) {
        TimeModel timeModel = this.i;
        if (timeModel.l == i2 && timeModel.k == i) {
            return;
        }
        this.h.performHapticFeedback(Build.VERSION.SDK_INT >= 21 ? 4 : 1);
    }

    public void h(int i, boolean z) {
        boolean z2 = i == 12;
        this.h.f(z2);
        this.i.m = i;
        this.h.o(z2 ? o : e(), z2 ? R.string.material_minute_suffix : R.string.material_hour_suffix);
        this.h.g(z2 ? this.j : this.k, z);
        this.h.e(i);
        this.h.i(new a(this.h.getContext(), R.string.material_hour_selection));
        this.h.h(new b(this.h.getContext(), R.string.material_minute_selection));
    }

    @Override // com.google.android.material.timepicker.e
    public void hide() {
        this.h.setVisibility(8);
    }

    public final void i() {
        TimePickerView timePickerView = this.h;
        TimeModel timeModel = this.i;
        timePickerView.s(timeModel.n, timeModel.c(), this.i.l);
    }

    @Override // com.google.android.material.timepicker.e
    public void invalidate() {
        this.k = this.i.c() * d();
        TimeModel timeModel = this.i;
        this.j = timeModel.l * 6;
        h(timeModel.m, false);
        i();
    }

    public final void j() {
        k(m, "%d");
        k(n, "%d");
        k(o, "%02d");
    }

    public final void k(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = TimeModel.b(this.h.getResources(), strArr[i], str);
        }
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnActionUpListener
    public void onActionUp(float f, boolean z) {
        this.l = true;
        TimeModel timeModel = this.i;
        int i = timeModel.l;
        int i2 = timeModel.k;
        if (timeModel.m == 10) {
            this.h.g(this.k, false);
            AccessibilityManager accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(this.h.getContext(), AccessibilityManager.class);
            if (!(accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled())) {
                h(12, true);
            }
        } else {
            int round = Math.round(f);
            if (!z) {
                this.i.i(((round + 15) / 30) * 5);
                this.j = this.i.l * 6;
            }
            this.h.g(this.j, z);
        }
        this.l = false;
        i();
        g(i2, i);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f, boolean z) {
        if (this.l) {
            return;
        }
        TimeModel timeModel = this.i;
        int i = timeModel.k;
        int i2 = timeModel.l;
        int round = Math.round(f);
        TimeModel timeModel2 = this.i;
        if (timeModel2.m == 12) {
            timeModel2.i((round + 3) / 6);
            this.j = (float) Math.floor(this.i.l * 6);
        } else {
            this.i.g((round + (d() / 2)) / d());
            this.k = this.i.c() * d();
        }
        if (z) {
            return;
        }
        i();
        g(i, i2);
    }

    @Override // com.google.android.material.timepicker.e
    public void show() {
        this.h.setVisibility(0);
    }
}
