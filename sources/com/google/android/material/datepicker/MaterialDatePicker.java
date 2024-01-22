package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.util.Pair;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
/* loaded from: classes10.dex */
public final class MaterialDatePicker<S> extends DialogFragment {
    public static final Object D = "CONFIRM_BUTTON_TAG";
    public static final Object E = "CANCEL_BUTTON_TAG";
    public static final Object F = "TOGGLE_BUTTON_TAG";
    public static final int INPUT_MODE_CALENDAR = 0;
    public static final int INPUT_MODE_TEXT = 1;
    @Nullable
    public MaterialShapeDrawable A;
    public Button B;
    public boolean C;
    public final LinkedHashSet<MaterialPickerOnPositiveButtonClickListener<? super S>> h = new LinkedHashSet<>();
    public final LinkedHashSet<View.OnClickListener> i = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnCancelListener> j = new LinkedHashSet<>();
    public final LinkedHashSet<DialogInterface.OnDismissListener> k = new LinkedHashSet<>();
    @StyleRes
    public int l;
    @Nullable
    public DateSelector<S> m;
    public g<S> n;
    @Nullable
    public CalendarConstraints o;
    public MaterialCalendar<S> p;
    @StringRes
    public int q;
    public CharSequence r;
    public boolean s;
    public int t;
    @StringRes
    public int u;
    public CharSequence v;
    @StringRes
    public int w;
    public CharSequence x;
    public TextView y;
    public CheckableImageButton z;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface InputMode {
    }

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialDatePicker.this.h.iterator();
            while (it.hasNext()) {
                ((MaterialPickerOnPositiveButtonClickListener) it.next()).onPositiveButtonClick(MaterialDatePicker.this.getSelection());
            }
            MaterialDatePicker.this.dismiss();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialDatePicker.this.i.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            MaterialDatePicker.this.dismiss();
        }
    }

    /* loaded from: classes10.dex */
    public class c implements OnApplyWindowInsetsListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f10280a;
        public final /* synthetic */ View b;
        public final /* synthetic */ int c;

        public c(MaterialDatePicker materialDatePicker, int i, View view, int i2) {
            this.f10280a = i;
            this.b = view;
            this.c = i2;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            int i = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).top;
            if (this.f10280a >= 0) {
                this.b.getLayoutParams().height = this.f10280a + i;
                View view2 = this.b;
                view2.setLayoutParams(view2.getLayoutParams());
            }
            View view3 = this.b;
            view3.setPadding(view3.getPaddingLeft(), this.c + i, this.b.getPaddingRight(), this.b.getPaddingBottom());
            return windowInsetsCompat;
        }
    }

    /* loaded from: classes10.dex */
    public class d extends OnSelectionChangedListener<S> {
        public d() {
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onIncompleteSelectionChanged() {
            MaterialDatePicker.this.B.setEnabled(false);
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onSelectionChanged(S s) {
            MaterialDatePicker.this.s();
            MaterialDatePicker.this.B.setEnabled(MaterialDatePicker.this.getDateSelector().isSelectionComplete());
        }
    }

    /* loaded from: classes10.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialDatePicker.this.B.setEnabled(MaterialDatePicker.this.getDateSelector().isSelectionComplete());
            MaterialDatePicker.this.z.toggle();
            MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
            materialDatePicker.t(materialDatePicker.z);
            MaterialDatePicker.this.r();
        }
    }

    @NonNull
    public static Drawable i(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, AppCompatResources.getDrawable(context, R.drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(context, R.drawable.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    public static int k(@NonNull Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        int i = Month.d().k;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width) * i) + ((i - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding));
    }

    public static boolean n(@NonNull Context context) {
        return q(context, 16843277);
    }

    public static boolean o(@NonNull Context context) {
        return q(context, R.attr.nestedScrollable);
    }

    @NonNull
    public static <S> MaterialDatePicker<S> p(@NonNull Builder<S> builder) {
        MaterialDatePicker<S> materialDatePicker = new MaterialDatePicker<>();
        Bundle bundle = new Bundle();
        bundle.putInt("OVERRIDE_THEME_RES_ID", builder.b);
        bundle.putParcelable("DATE_SELECTOR_KEY", builder.f10279a);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", builder.c);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", builder.d);
        bundle.putCharSequence("TITLE_TEXT_KEY", builder.e);
        bundle.putInt("INPUT_MODE_KEY", builder.k);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", builder.f);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", builder.g);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", builder.h);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", builder.i);
        materialDatePicker.setArguments(bundle);
        return materialDatePicker;
    }

    public static boolean q(@NonNull Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{i});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public static long thisMonthInUtcMilliseconds() {
        return Month.d().m;
    }

    public static long todayInUtcMilliseconds() {
        return j.o().getTimeInMillis();
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        return this.j.add(onCancelListener);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        return this.k.add(onDismissListener);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener onClickListener) {
        return this.i.add(onClickListener);
    }

    public boolean addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> materialPickerOnPositiveButtonClickListener) {
        return this.h.add(materialPickerOnPositiveButtonClickListener);
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

    public final DateSelector<S> getDateSelector() {
        if (this.m == null) {
            this.m = (DateSelector) getArguments().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.m;
    }

    public String getHeaderText() {
        return getDateSelector().getSelectionDisplayString(getContext());
    }

    @Nullable
    public final S getSelection() {
        return getDateSelector().getSelection();
    }

    public final void j(Window window) {
        if (this.C) {
            return;
        }
        View findViewById = requireView().findViewById(R.id.fullscreen_header);
        EdgeToEdgeUtils.applyEdgeToEdge(window, true, ViewUtils.getBackgroundColor(findViewById), null);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById, new c(this, findViewById.getLayoutParams().height, findViewById, findViewById.getPaddingTop()));
        this.C = true;
    }

    public final int l(Context context) {
        int i = this.l;
        return i != 0 ? i : getDateSelector().getDefaultThemeResId(context);
    }

    public final void m(Context context) {
        this.z.setTag(F);
        this.z.setImageDrawable(i(context));
        this.z.setChecked(this.t != 0);
        ViewCompat.setAccessibilityDelegate(this.z, null);
        t(this.z);
        this.z.setOnClickListener(new e());
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(@NonNull DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.j.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.l = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.m = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.o = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.q = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.r = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.t = bundle.getInt("INPUT_MODE_KEY");
        this.u = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.v = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.w = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.x = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), l(requireContext()));
        Context context = dialog.getContext();
        this.s = n(context);
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(context, R.attr.colorSurface, MaterialDatePicker.class.getCanonicalName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        this.A = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(context);
        this.A.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        this.A.setElevation(ViewCompat.getElevation(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(this.s ? R.layout.mtrl_picker_fullscreen : R.layout.mtrl_picker_dialog, viewGroup);
        Context context = inflate.getContext();
        if (this.s) {
            inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(k(context), -2));
        } else {
            inflate.findViewById(R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(k(context), -1));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text);
        this.y = textView;
        ViewCompat.setAccessibilityLiveRegion(textView, 1);
        this.z = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
        CharSequence charSequence = this.r;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.q);
        }
        m(context);
        this.B = (Button) inflate.findViewById(R.id.confirm_button);
        if (getDateSelector().isSelectionComplete()) {
            this.B.setEnabled(true);
        } else {
            this.B.setEnabled(false);
        }
        this.B.setTag(D);
        CharSequence charSequence2 = this.v;
        if (charSequence2 != null) {
            this.B.setText(charSequence2);
        } else {
            int i = this.u;
            if (i != 0) {
                this.B.setText(i);
            }
        }
        this.B.setOnClickListener(new a());
        Button button = (Button) inflate.findViewById(R.id.cancel_button);
        button.setTag(E);
        CharSequence charSequence3 = this.x;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i2 = this.w;
            if (i2 != 0) {
                button.setText(i2);
            }
        }
        button.setOnClickListener(new b());
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.l);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.m);
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder(this.o);
        if (this.p.n() != null) {
            builder.setOpenAt(this.p.n().m);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", builder.build());
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.q);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.r);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.u);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.v);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.w);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.x);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.s) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.A);
            j(window);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.A, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(requireDialog(), rect));
        }
        r();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.n.b();
        super.onStop();
    }

    public final void r() {
        g<S> gVar;
        int l = l(requireContext());
        this.p = MaterialCalendar.newInstance(getDateSelector(), l, this.o);
        if (this.z.isChecked()) {
            gVar = MaterialTextInputPicker.c(getDateSelector(), l, this.o);
        } else {
            gVar = this.p;
        }
        this.n = gVar;
        s();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.mtrl_calendar_frame, this.n);
        beginTransaction.commitNow();
        this.n.addOnSelectionChangedListener(new d());
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        return this.j.remove(onCancelListener);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        return this.k.remove(onDismissListener);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener onClickListener) {
        return this.i.remove(onClickListener);
    }

    public boolean removeOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> materialPickerOnPositiveButtonClickListener) {
        return this.h.remove(materialPickerOnPositiveButtonClickListener);
    }

    public final void s() {
        String headerText = getHeaderText();
        this.y.setContentDescription(String.format(getString(R.string.mtrl_picker_announce_current_selection), headerText));
        this.y.setText(headerText);
    }

    public final void t(@NonNull CheckableImageButton checkableImageButton) {
        String string;
        if (this.z.isChecked()) {
            string = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode);
        } else {
            string = checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode);
        }
        this.z.setContentDescription(string);
    }

    /* loaded from: classes10.dex */
    public static final class Builder<S> {

        /* renamed from: a  reason: collision with root package name */
        public final DateSelector<S> f10279a;
        public CalendarConstraints c;
        public int b = 0;
        public int d = 0;
        public CharSequence e = null;
        public int f = 0;
        public CharSequence g = null;
        public int h = 0;
        public CharSequence i = null;
        @Nullable
        public S j = null;
        public int k = 0;

        public Builder(DateSelector<S> dateSelector) {
            this.f10279a = dateSelector;
        }

        public static boolean b(Month month, CalendarConstraints calendarConstraints) {
            return month.compareTo(calendarConstraints.i()) >= 0 && month.compareTo(calendarConstraints.f()) <= 0;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static <S> Builder<S> customDatePicker(@NonNull DateSelector<S> dateSelector) {
            return new Builder<>(dateSelector);
        }

        @NonNull
        public static Builder<Long> datePicker() {
            return new Builder<>(new SingleDateSelector());
        }

        @NonNull
        public static Builder<Pair<Long, Long>> dateRangePicker() {
            return new Builder<>(new RangeDateSelector());
        }

        public final Month a() {
            if (!this.f10279a.getSelectedDays().isEmpty()) {
                Month c = Month.c(this.f10279a.getSelectedDays().iterator().next().longValue());
                if (b(c, this.c)) {
                    return c;
                }
            }
            Month d = Month.d();
            return b(d, this.c) ? d : this.c.i();
        }

        @NonNull
        public MaterialDatePicker<S> build() {
            if (this.c == null) {
                this.c = new CalendarConstraints.Builder().build();
            }
            if (this.d == 0) {
                this.d = this.f10279a.getDefaultTitleResId();
            }
            S s = this.j;
            if (s != null) {
                this.f10279a.setSelection(s);
            }
            if (this.c.h() == null) {
                this.c.l(a());
            }
            return MaterialDatePicker.p(this);
        }

        @NonNull
        public Builder<S> setCalendarConstraints(CalendarConstraints calendarConstraints) {
            this.c = calendarConstraints;
            return this;
        }

        @NonNull
        public Builder<S> setInputMode(int i) {
            this.k = i;
            return this;
        }

        @NonNull
        public Builder<S> setNegativeButtonText(@StringRes int i) {
            this.h = i;
            this.i = null;
            return this;
        }

        @NonNull
        public Builder<S> setPositiveButtonText(@StringRes int i) {
            this.f = i;
            this.g = null;
            return this;
        }

        @NonNull
        public Builder<S> setSelection(S s) {
            this.j = s;
            return this;
        }

        @NonNull
        public Builder<S> setTheme(@StyleRes int i) {
            this.b = i;
            return this;
        }

        @NonNull
        public Builder<S> setTitleText(@StringRes int i) {
            this.d = i;
            this.e = null;
            return this;
        }

        @NonNull
        public Builder<S> setNegativeButtonText(@Nullable CharSequence charSequence) {
            this.i = charSequence;
            this.h = 0;
            return this;
        }

        @NonNull
        public Builder<S> setPositiveButtonText(@Nullable CharSequence charSequence) {
            this.g = charSequence;
            this.f = 0;
            return this;
        }

        @NonNull
        public Builder<S> setTitleText(@Nullable CharSequence charSequence) {
            this.e = charSequence;
            this.d = 0;
            return this;
        }
    }
}
