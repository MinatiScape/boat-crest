package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class RangeDateSelector implements DateSelector<Pair<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new c();
    public String h;
    @Nullable
    public Long i = null;
    @Nullable
    public Long j = null;
    @Nullable
    public Long k = null;
    @Nullable
    public Long l = null;

    /* loaded from: classes10.dex */
    public class a extends com.google.android.material.datepicker.c {
        public final /* synthetic */ TextInputLayout n;
        public final /* synthetic */ TextInputLayout o;
        public final /* synthetic */ OnSelectionChangedListener p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, OnSelectionChangedListener onSelectionChangedListener) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.n = textInputLayout2;
            this.o = textInputLayout3;
            this.p = onSelectionChangedListener;
        }

        @Override // com.google.android.material.datepicker.c
        public void e() {
            RangeDateSelector.this.k = null;
            RangeDateSelector.this.i(this.n, this.o, this.p);
        }

        @Override // com.google.android.material.datepicker.c
        public void f(@Nullable Long l) {
            RangeDateSelector.this.k = l;
            RangeDateSelector.this.i(this.n, this.o, this.p);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends com.google.android.material.datepicker.c {
        public final /* synthetic */ TextInputLayout n;
        public final /* synthetic */ TextInputLayout o;
        public final /* synthetic */ OnSelectionChangedListener p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, OnSelectionChangedListener onSelectionChangedListener) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.n = textInputLayout2;
            this.o = textInputLayout3;
            this.p = onSelectionChangedListener;
        }

        @Override // com.google.android.material.datepicker.c
        public void e() {
            RangeDateSelector.this.l = null;
            RangeDateSelector.this.i(this.n, this.o, this.p);
        }

        @Override // com.google.android.material.datepicker.c
        public void f(@Nullable Long l) {
            RangeDateSelector.this.l = l;
            RangeDateSelector.this.i(this.n, this.o, this.p);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a */
        public RangeDateSelector createFromParcel(@NonNull Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.i = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.j = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b */
        public RangeDateSelector[] newArray(int i) {
            return new RangeDateSelector[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final void f(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.h.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !HexStringBuilder.DEFAULT_SEPARATOR.contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    public final boolean g(long j, long j2) {
        return j <= j2;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(@NonNull Context context) {
        int i;
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(R.dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis)) {
            i = R.attr.materialCalendarTheme;
        } else {
            i = R.attr.materialCalendarFullscreenTheme;
        }
        return MaterialAttributes.resolveOrThrow(context, i, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return R.string.mtrl_picker_range_header_title;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Collection<Long> getSelectedDays() {
        ArrayList arrayList = new ArrayList();
        Long l = this.i;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.j;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Collection<Pair<Long, Long>> getSelectedRanges() {
        if (this.i != null && this.j != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Pair(this.i, this.j));
            return arrayList;
        }
        return new ArrayList();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public String getSelectionDisplayString(@NonNull Context context) {
        Resources resources = context.getResources();
        Long l = this.i;
        if (l == null && this.j == null) {
            return resources.getString(R.string.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.j;
        if (l2 == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_start_selected, d.c(l.longValue()));
        }
        if (l == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_end_selected, d.c(l2.longValue()));
        }
        Pair<String, String> a2 = d.a(l, l2);
        return resources.getString(R.string.mtrl_picker_range_header_selected, a2.first, a2.second);
    }

    public final void h(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.h);
        textInputLayout2.setError(HexStringBuilder.DEFAULT_SEPARATOR);
    }

    public final void i(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull OnSelectionChangedListener<Pair<Long, Long>> onSelectionChangedListener) {
        Long l = this.k;
        if (l != null && this.l != null) {
            if (g(l.longValue(), this.l.longValue())) {
                this.i = this.k;
                this.j = this.l;
                onSelectionChangedListener.onSelectionChanged(getSelection());
                return;
            }
            h(textInputLayout, textInputLayout2);
            onSelectionChangedListener.onIncompleteSelectionChanged();
            return;
        }
        f(textInputLayout, textInputLayout2);
        onSelectionChangedListener.onIncompleteSelectionChanged();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        Long l = this.i;
        return (l == null || this.j == null || !g(l.longValue(), this.j.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle, CalendarConstraints calendarConstraints, @NonNull OnSelectionChangedListener<Pair<Long, Long>> onSelectionChangedListener) {
        View inflate = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date_range, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_start);
        TextInputLayout textInputLayout2 = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.h = inflate.getResources().getString(R.string.mtrl_picker_invalid_range);
        SimpleDateFormat k = j.k();
        Long l = this.i;
        if (l != null) {
            editText.setText(k.format(l));
            this.k = this.i;
        }
        Long l2 = this.j;
        if (l2 != null) {
            editText2.setText(k.format(l2));
            this.l = this.j;
        }
        String l3 = j.l(inflate.getResources(), k);
        textInputLayout.setPlaceholderText(l3);
        textInputLayout2.setPlaceholderText(l3);
        editText.addTextChangedListener(new a(l3, k, textInputLayout, calendarConstraints, textInputLayout, textInputLayout2, onSelectionChangedListener));
        editText2.addTextChangedListener(new b(l3, k, textInputLayout2, calendarConstraints, textInputLayout, textInputLayout2, onSelectionChangedListener));
        ViewUtils.requestFocusAndShowKeyboard(editText);
        return inflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void select(long j) {
        Long l = this.i;
        if (l == null) {
            this.i = Long.valueOf(j);
        } else if (this.j == null && g(l.longValue(), j)) {
            this.j = Long.valueOf(j);
        } else {
            this.j = null;
            this.i = Long.valueOf(j);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeValue(this.i);
        parcel.writeValue(this.j);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Pair<Long, Long> getSelection() {
        return new Pair<>(this.i, this.j);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void setSelection(@NonNull Pair<Long, Long> pair) {
        Long l = pair.first;
        if (l != null && pair.second != null) {
            Preconditions.checkArgument(g(l.longValue(), pair.second.longValue()));
        }
        Long l2 = pair.first;
        this.i = l2 == null ? null : Long.valueOf(j.a(l2.longValue()));
        Long l3 = pair.second;
        this.j = l3 != null ? Long.valueOf(j.a(l3.longValue())) : null;
    }
}
