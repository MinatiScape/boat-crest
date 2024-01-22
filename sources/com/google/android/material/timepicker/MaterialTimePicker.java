package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.timepicker.TimePickerView;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class MaterialTimePicker extends DialogFragment implements TimePickerView.e {
    public static final int INPUT_MODE_CLOCK = 0;
    public static final int INPUT_MODE_KEYBOARD = 1;
    public TimeModel B;
    public TimePickerView l;
    public ViewStub m;
    @Nullable
    public d n;
    @Nullable
    public g o;
    @Nullable
    public e p;
    @DrawableRes
    public int q;
    @DrawableRes
    public int r;
    public CharSequence t;
    public CharSequence v;
    public CharSequence x;
    public MaterialButton y;
    public Button z;
    public final Set<View.OnClickListener> h = new LinkedHashSet();
    public final Set<View.OnClickListener> i = new LinkedHashSet();
    public final Set<DialogInterface.OnCancelListener> j = new LinkedHashSet();
    public final Set<DialogInterface.OnDismissListener> k = new LinkedHashSet();
    @StringRes
    public int s = 0;
    @StringRes
    public int u = 0;
    @StringRes
    public int w = 0;
    public int A = 0;
    public int C = 0;

    /* loaded from: classes10.dex */
    public static final class Builder {
        public int b;
        public CharSequence d;
        public CharSequence f;
        public CharSequence h;

        /* renamed from: a  reason: collision with root package name */
        public TimeModel f10395a = new TimeModel();
        @StringRes
        public int c = 0;
        @StringRes
        public int e = 0;
        @StringRes
        public int g = 0;
        public int i = 0;

        @NonNull
        public MaterialTimePicker build() {
            return MaterialTimePicker.k(this);
        }

        @NonNull
        public Builder setHour(@IntRange(from = 0, to = 23) int i) {
            this.f10395a.h(i);
            return this;
        }

        @NonNull
        public Builder setInputMode(int i) {
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setMinute(@IntRange(from = 0, to = 59) int i) {
            this.f10395a.i(i);
            return this;
        }

        @NonNull
        public Builder setNegativeButtonText(@StringRes int i) {
            this.g = i;
            return this;
        }

        @NonNull
        public Builder setPositiveButtonText(@StringRes int i) {
            this.e = i;
            return this;
        }

        @NonNull
        public Builder setTheme(@StyleRes int i) {
            this.i = i;
            return this;
        }

        @NonNull
        public Builder setTimeFormat(int i) {
            TimeModel timeModel = this.f10395a;
            int i2 = timeModel.k;
            int i3 = timeModel.l;
            TimeModel timeModel2 = new TimeModel(i);
            this.f10395a = timeModel2;
            timeModel2.i(i3);
            this.f10395a.h(i2);
            return this;
        }

        @NonNull
        public Builder setTitleText(@StringRes int i) {
            this.c = i;
            return this;
        }

        @NonNull
        public Builder setNegativeButtonText(@Nullable CharSequence charSequence) {
            this.h = charSequence;
            return this;
        }

        @NonNull
        public Builder setPositiveButtonText(@Nullable CharSequence charSequence) {
            this.f = charSequence;
            return this;
        }

        @NonNull
        public Builder setTitleText(@Nullable CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (View.OnClickListener onClickListener : MaterialTimePicker.this.h) {
                onClickListener.onClick(view);
            }
            MaterialTimePicker.this.dismiss();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (View.OnClickListener onClickListener : MaterialTimePicker.this.i) {
                onClickListener.onClick(view);
            }
            MaterialTimePicker.this.dismiss();
        }
    }

    /* loaded from: classes10.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
            materialTimePicker.A = materialTimePicker.A == 0 ? 1 : 0;
            MaterialTimePicker materialTimePicker2 = MaterialTimePicker.this;
            materialTimePicker2.n(materialTimePicker2.y);
        }
    }

    @NonNull
    public static MaterialTimePicker k(@NonNull Builder builder) {
        MaterialTimePicker materialTimePicker = new MaterialTimePicker();
        Bundle bundle = new Bundle();
        bundle.putParcelable("TIME_PICKER_TIME_MODEL", builder.f10395a);
        bundle.putInt("TIME_PICKER_INPUT_MODE", builder.b);
        bundle.putInt("TIME_PICKER_TITLE_RES", builder.c);
        if (builder.d != null) {
            bundle.putCharSequence("TIME_PICKER_TITLE_TEXT", builder.d);
        }
        bundle.putInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", builder.e);
        if (builder.f != null) {
            bundle.putCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT", builder.f);
        }
        bundle.putInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", builder.g);
        if (builder.h != null) {
            bundle.putCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT", builder.h);
        }
        bundle.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", builder.i);
        materialTimePicker.setArguments(bundle);
        return materialTimePicker;
    }

    public boolean addOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        return this.j.add(onCancelListener);
    }

    public boolean addOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        return this.k.add(onDismissListener);
    }

    public boolean addOnNegativeButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.i.add(onClickListener);
    }

    public boolean addOnPositiveButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.h.add(onClickListener);
    }

    public void clearOnCancelListeners() {
        this.j.clear();
    }

    public void clearOnDismissListeners() {
        this.k.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.i.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.h.clear();
    }

    @IntRange(from = 0, to = 23)
    public int getHour() {
        return this.B.k % 24;
    }

    public int getInputMode() {
        return this.A;
    }

    @IntRange(from = 0, to = 59)
    public int getMinute() {
        return this.B.l;
    }

    public final Pair<Integer, Integer> h(int i) {
        if (i != 0) {
            if (i == 1) {
                return new Pair<>(Integer.valueOf(this.r), Integer.valueOf(R.string.material_timepicker_clock_mode_description));
            }
            throw new IllegalArgumentException("no icon for mode: " + i);
        }
        return new Pair<>(Integer.valueOf(this.q), Integer.valueOf(R.string.material_timepicker_text_input_mode_description));
    }

    public final int i() {
        int i = this.C;
        if (i != 0) {
            return i;
        }
        TypedValue resolve = MaterialAttributes.resolve(requireContext(), R.attr.materialTimePickerTheme);
        if (resolve == null) {
            return 0;
        }
        return resolve.data;
    }

    public final e j(int i, @NonNull TimePickerView timePickerView, @NonNull ViewStub viewStub) {
        if (i == 0) {
            d dVar = this.n;
            if (dVar == null) {
                dVar = new d(timePickerView, this.B);
            }
            this.n = dVar;
            return dVar;
        }
        if (this.o == null) {
            this.o = new g((LinearLayout) viewStub.inflate(), this.B);
        }
        this.o.d();
        return this.o;
    }

    public final void l(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        TimeModel timeModel = (TimeModel) bundle.getParcelable("TIME_PICKER_TIME_MODEL");
        this.B = timeModel;
        if (timeModel == null) {
            this.B = new TimeModel();
        }
        this.A = bundle.getInt("TIME_PICKER_INPUT_MODE", 0);
        this.s = bundle.getInt("TIME_PICKER_TITLE_RES", 0);
        this.t = bundle.getCharSequence("TIME_PICKER_TITLE_TEXT");
        this.u = bundle.getInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", 0);
        this.v = bundle.getCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT");
        this.w = bundle.getInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", 0);
        this.x = bundle.getCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT");
        this.C = bundle.getInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", 0);
    }

    public final void m() {
        Button button = this.z;
        if (button != null) {
            button.setVisibility(isCancelable() ? 0 : 8);
        }
    }

    public final void n(MaterialButton materialButton) {
        if (materialButton == null || this.l == null || this.m == null) {
            return;
        }
        e eVar = this.p;
        if (eVar != null) {
            eVar.hide();
        }
        e j = j(this.A, this.l, this.m);
        this.p = j;
        j.show();
        this.p.invalidate();
        Pair<Integer, Integer> h = h(this.A);
        materialButton.setIconResource(((Integer) h.first).intValue());
        materialButton.setContentDescription(getResources().getString(((Integer) h.second).intValue()));
        materialButton.sendAccessibilityEvent(4);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(@NonNull DialogInterface dialogInterface) {
        for (DialogInterface.OnCancelListener onCancelListener : this.j) {
            onCancelListener.onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        l(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), i());
        Context context = dialog.getContext();
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(context, R.attr.colorSurface, MaterialTimePicker.class.getCanonicalName());
        int i = R.attr.materialTimePickerStyle;
        int i2 = R.style.Widget_MaterialComponents_TimePicker;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.MaterialTimePicker, i, i2);
        this.r = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_clockIcon, 0);
        this.q = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_keyboardIcon, 0);
        obtainStyledAttributes.recycle();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(materialShapeDrawable);
        window.requestFeature(1);
        window.setLayout(-2, -2);
        materialShapeDrawable.setElevation(ViewCompat.getElevation(window.getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.material_timepicker_dialog, viewGroup);
        TimePickerView timePickerView = (TimePickerView) viewGroup2.findViewById(R.id.material_timepicker_view);
        this.l = timePickerView;
        timePickerView.k(this);
        this.m = (ViewStub) viewGroup2.findViewById(R.id.material_textinput_timepicker);
        this.y = (MaterialButton) viewGroup2.findViewById(R.id.material_timepicker_mode_button);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.header_title);
        int i = this.s;
        if (i != 0) {
            textView.setText(i);
        } else if (!TextUtils.isEmpty(this.t)) {
            textView.setText(this.t);
        }
        n(this.y);
        Button button = (Button) viewGroup2.findViewById(R.id.material_timepicker_ok_button);
        button.setOnClickListener(new a());
        int i2 = this.u;
        if (i2 != 0) {
            button.setText(i2);
        } else if (!TextUtils.isEmpty(this.v)) {
            button.setText(this.v);
        }
        Button button2 = (Button) viewGroup2.findViewById(R.id.material_timepicker_cancel_button);
        this.z = button2;
        button2.setOnClickListener(new b());
        int i3 = this.w;
        if (i3 != 0) {
            this.z.setText(i3);
        } else if (!TextUtils.isEmpty(this.x)) {
            this.z.setText(this.x);
        }
        m();
        this.y.setOnClickListener(new c());
        return viewGroup2;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.p = null;
        this.n = null;
        this.o = null;
        TimePickerView timePickerView = this.l;
        if (timePickerView != null) {
            timePickerView.k(null);
            this.l = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        for (DialogInterface.OnDismissListener onDismissListener : this.k) {
            onDismissListener.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.e
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onDoubleTap() {
        this.A = 1;
        n(this.y);
        this.o.g();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("TIME_PICKER_TIME_MODEL", this.B);
        bundle.putInt("TIME_PICKER_INPUT_MODE", this.A);
        bundle.putInt("TIME_PICKER_TITLE_RES", this.s);
        bundle.putCharSequence("TIME_PICKER_TITLE_TEXT", this.t);
        bundle.putInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", this.u);
        bundle.putCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT", this.v);
        bundle.putInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", this.w);
        bundle.putCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT", this.x);
        bundle.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", this.C);
    }

    public boolean removeOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        return this.j.remove(onCancelListener);
    }

    public boolean removeOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        return this.k.remove(onDismissListener);
    }

    public boolean removeOnNegativeButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.i.remove(onClickListener);
    }

    public boolean removeOnPositiveButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.h.remove(onClickListener);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setCancelable(boolean z) {
        super.setCancelable(z);
        m();
    }

    public void setHour(@IntRange(from = 0, to = 23) int i) {
        this.B.g(i);
        e eVar = this.p;
        if (eVar != null) {
            eVar.invalidate();
        }
    }

    public void setMinute(@IntRange(from = 0, to = 59) int i) {
        this.B.i(i);
        e eVar = this.p;
        if (eVar != null) {
            eVar.invalidate();
        }
    }
}
