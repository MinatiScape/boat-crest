package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.timepicker.TimePickerView;
import java.lang.reflect.Field;
import java.util.Locale;
/* loaded from: classes10.dex */
public class g implements TimePickerView.g, com.google.android.material.timepicker.e {
    public final LinearLayout h;
    public final TimeModel i;
    public final TextWatcher j = new a();
    public final TextWatcher k = new b();
    public final ChipTextInputComboView l;
    public final ChipTextInputComboView m;
    public final com.google.android.material.timepicker.f n;
    public final EditText o;
    public final EditText p;
    public MaterialButtonToggleGroup q;

    /* loaded from: classes10.dex */
    public class a extends TextWatcherAdapter {
        public a() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    g.this.i.i(0);
                    return;
                }
                g.this.i.i(Integer.parseInt(editable.toString()));
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b extends TextWatcherAdapter {
        public b() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    g.this.i.g(0);
                    return;
                }
                g.this.i.g(Integer.parseInt(editable.toString()));
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.b(((Integer) view.getTag(R.id.selection_type)).intValue());
        }
    }

    /* loaded from: classes10.dex */
    public class d extends com.google.android.material.timepicker.a {
        public final /* synthetic */ TimeModel e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(g gVar, Context context, int i, TimeModel timeModel) {
            super(context, i);
            this.e = timeModel;
        }

        @Override // com.google.android.material.timepicker.a, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(R.string.material_hour_suffix, String.valueOf(this.e.c())));
        }
    }

    /* loaded from: classes10.dex */
    public class e extends com.google.android.material.timepicker.a {
        public final /* synthetic */ TimeModel e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(g gVar, Context context, int i, TimeModel timeModel) {
            super(context, i);
            this.e = timeModel;
        }

        @Override // com.google.android.material.timepicker.a, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setContentDescription(view.getResources().getString(R.string.material_minute_suffix, String.valueOf(this.e.l)));
        }
    }

    /* loaded from: classes10.dex */
    public class f implements MaterialButtonToggleGroup.OnButtonCheckedListener {
        public f() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
        public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
            g.this.i.j(i == R.id.material_clock_period_pm_button ? 1 : 0);
        }
    }

    public g(LinearLayout linearLayout, TimeModel timeModel) {
        this.h = linearLayout;
        this.i = timeModel;
        Resources resources = linearLayout.getResources();
        ChipTextInputComboView chipTextInputComboView = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_minute_text_input);
        this.l = chipTextInputComboView;
        ChipTextInputComboView chipTextInputComboView2 = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_hour_text_input);
        this.m = chipTextInputComboView2;
        int i = R.id.material_label;
        ((TextView) chipTextInputComboView.findViewById(i)).setText(resources.getString(R.string.material_timepicker_minute));
        ((TextView) chipTextInputComboView2.findViewById(i)).setText(resources.getString(R.string.material_timepicker_hour));
        int i2 = R.id.selection_type;
        chipTextInputComboView.setTag(i2, 12);
        chipTextInputComboView2.setTag(i2, 10);
        if (timeModel.j == 0) {
            j();
        }
        c cVar = new c();
        chipTextInputComboView2.setOnClickListener(cVar);
        chipTextInputComboView.setOnClickListener(cVar);
        chipTextInputComboView2.c(timeModel.d());
        chipTextInputComboView.c(timeModel.e());
        EditText editText = chipTextInputComboView2.e().getEditText();
        this.o = editText;
        EditText editText2 = chipTextInputComboView.e().getEditText();
        this.p = editText2;
        if (Build.VERSION.SDK_INT < 21) {
            int color = MaterialColors.getColor(linearLayout, R.attr.colorPrimary);
            h(editText, color);
            h(editText2, color);
        }
        this.n = new com.google.android.material.timepicker.f(chipTextInputComboView2, chipTextInputComboView, timeModel);
        chipTextInputComboView2.f(new d(this, linearLayout.getContext(), R.string.material_hour_selection, timeModel));
        chipTextInputComboView.f(new e(this, linearLayout.getContext(), R.string.material_minute_selection, timeModel));
        e();
    }

    public static void h(EditText editText, @ColorInt int i) {
        try {
            Context context = editText.getContext();
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i2 = declaredField.getInt(editText);
            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(editText);
            Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
            declaredField3.setAccessible(true);
            Drawable drawable = AppCompatResources.getDrawable(context, i2);
            drawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
            declaredField3.set(obj, new Drawable[]{drawable, drawable});
        } catch (Throwable unused) {
        }
    }

    @Override // com.google.android.material.timepicker.TimePickerView.g
    public void b(int i) {
        this.i.m = i;
        this.l.setChecked(i == 12);
        this.m.setChecked(i == 10);
        k();
    }

    public final void c() {
        this.o.addTextChangedListener(this.k);
        this.p.addTextChangedListener(this.j);
    }

    public void d() {
        this.l.setChecked(false);
        this.m.setChecked(false);
    }

    public void e() {
        c();
        i(this.i);
        this.n.a();
    }

    public final void f() {
        this.o.removeTextChangedListener(this.k);
        this.p.removeTextChangedListener(this.j);
    }

    public void g() {
        this.l.setChecked(this.i.m == 12);
        this.m.setChecked(this.i.m == 10);
    }

    @Override // com.google.android.material.timepicker.e
    public void hide() {
        View focusedChild = this.h.getFocusedChild();
        if (focusedChild == null) {
            this.h.setVisibility(8);
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(this.h.getContext(), InputMethodManager.class);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedChild.getWindowToken(), 0);
        }
        this.h.setVisibility(8);
    }

    public final void i(TimeModel timeModel) {
        f();
        Locale locale = this.h.getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(timeModel.l));
        String format2 = String.format(locale, "%02d", Integer.valueOf(timeModel.c()));
        this.l.g(format);
        this.m.g(format2);
        c();
        k();
    }

    @Override // com.google.android.material.timepicker.e
    public void invalidate() {
        i(this.i);
    }

    public final void j() {
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) this.h.findViewById(R.id.material_clock_period_toggle);
        this.q = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new f());
        this.q.setVisibility(0);
        k();
    }

    public final void k() {
        int i;
        MaterialButtonToggleGroup materialButtonToggleGroup = this.q;
        if (materialButtonToggleGroup == null) {
            return;
        }
        if (this.i.n == 0) {
            i = R.id.material_clock_period_am_button;
        } else {
            i = R.id.material_clock_period_pm_button;
        }
        materialButtonToggleGroup.check(i);
    }

    @Override // com.google.android.material.timepicker.e
    public void show() {
        this.h.setVisibility(0);
    }
}
