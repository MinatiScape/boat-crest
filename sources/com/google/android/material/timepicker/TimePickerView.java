package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;
/* loaded from: classes10.dex */
class TimePickerView extends ConstraintLayout {
    public final Chip h;
    public final Chip i;
    public final ClockHandView j;
    public final ClockFaceView k;
    public final MaterialButtonToggleGroup l;
    public final View.OnClickListener m;
    public f n;
    public g o;
    public e p;

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TimePickerView.this.o != null) {
                TimePickerView.this.o.b(((Integer) view.getTag(R.id.selection_type)).intValue());
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b implements MaterialButtonToggleGroup.OnButtonCheckedListener {
        public b() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
        public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
            int i2 = i == R.id.material_clock_period_pm_button ? 1 : 0;
            if (TimePickerView.this.n == null || !z) {
                return;
            }
            TimePickerView.this.n.a(i2);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends GestureDetector.SimpleOnGestureListener {
        public c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            e eVar = TimePickerView.this.p;
            if (eVar != null) {
                eVar.onDoubleTap();
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public class d implements View.OnTouchListener {
        public final /* synthetic */ GestureDetector h;

        public d(TimePickerView timePickerView, GestureDetector gestureDetector) {
            this.h = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.h.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    /* loaded from: classes10.dex */
    public interface e {
        void onDoubleTap();
    }

    /* loaded from: classes10.dex */
    public interface f {
        void a(int i);
    }

    /* loaded from: classes10.dex */
    public interface g {
        void b(int i);
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void d(ClockHandView.OnRotateListener onRotateListener) {
        this.j.b(onRotateListener);
    }

    public void e(int i) {
        r(this.h, i == 12);
        r(this.i, i == 10);
    }

    public void f(boolean z) {
        this.j.j(z);
    }

    public void g(float f2, boolean z) {
        this.j.m(f2, z);
    }

    public void h(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.h, accessibilityDelegateCompat);
    }

    public void i(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.i, accessibilityDelegateCompat);
    }

    public void j(ClockHandView.OnActionUpListener onActionUpListener) {
        this.j.o(onActionUpListener);
    }

    public void k(@Nullable e eVar) {
        this.p = eVar;
    }

    public void l(f fVar) {
        this.n = fVar;
    }

    public void m(g gVar) {
        this.o = gVar;
    }

    public final void n() {
        Chip chip = this.h;
        int i = R.id.selection_type;
        chip.setTag(i, 12);
        this.i.setTag(i, 10);
        this.h.setOnClickListener(this.m);
        this.i.setOnClickListener(this.m);
        this.h.setAccessibilityClassName("android.view.View");
        this.i.setAccessibilityClassName("android.view.View");
    }

    public void o(String[] strArr, @StringRes int i) {
        this.k.m(strArr, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        t();
    }

    @Override // android.view.View
    public void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            t();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void p() {
        d dVar = new d(this, new GestureDetector(getContext(), new c()));
        this.h.setOnTouchListener(dVar);
        this.i.setOnTouchListener(dVar);
    }

    public void q() {
        this.l.setVisibility(0);
    }

    public final void r(Chip chip, boolean z) {
        chip.setChecked(z);
        ViewCompat.setAccessibilityLiveRegion(chip, z ? 2 : 0);
    }

    @SuppressLint({"DefaultLocale"})
    public void s(int i, int i2, int i3) {
        int i4;
        if (i == 1) {
            i4 = R.id.material_clock_period_pm_button;
        } else {
            i4 = R.id.material_clock_period_am_button;
        }
        this.l.check(i4);
        Locale locale = getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(i3));
        String format2 = String.format(locale, "%02d", Integer.valueOf(i2));
        if (!TextUtils.equals(this.h.getText(), format)) {
            this.h.setText(format);
        }
        if (TextUtils.equals(this.i.getText(), format2)) {
            return;
        }
        this.i.setText(format2);
    }

    public final void t() {
        if (this.l.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.clear(R.id.material_clock_display, ViewCompat.getLayoutDirection(this) == 0 ? 2 : 1);
            constraintSet.applyTo(this);
        }
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new a();
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        this.k = (ClockFaceView) findViewById(R.id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.l = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new b());
        this.h = (Chip) findViewById(R.id.material_minute_tv);
        this.i = (Chip) findViewById(R.id.material_hour_tv);
        this.j = (ClockHandView) findViewById(R.id.material_clock_hand);
        p();
        n();
    }
}
