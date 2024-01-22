package com.coveiot.android.theme.compundview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.coveiot.android.theme.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt___StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class OTPView extends LinearLayout {
    @Nullable
    public final Typeface A;
    public final int B;
    public final int C;
    @Nullable
    public Drawable D;
    @Nullable
    public final Typeface E;
    @NotNull
    public Function1<? super String, Unit> F;
    @NotNull
    public Function1<? super Boolean, Unit> G;
    @NotNull
    public Function1<? super Boolean, Unit> H;
    @NotNull
    public final List<EditText> I;
    public int J;
    @Nullable
    public Drawable K;
    public boolean L;
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    public final int h;
    public final boolean i;
    public final boolean j;
    @Nullable
    public final Drawable k;
    public final int l;
    public final int m;
    @Nullable
    public final String n;
    public int o;
    public int p;
    public final boolean q;
    public final int r;
    public final boolean s;
    public final int t;
    public final int u;
    @Nullable
    public Drawable v;
    @Nullable
    public final Typeface w;
    public final int x;
    public final int y;
    @Nullable
    public Drawable z;

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<String, Unit> {
        public static final c INSTANCE = new c();

        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(String str) {
            invoke2(str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull String it) {
            Intrinsics.checkNotNullParameter(it, "it");
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public OTPView(@NotNull Context context) {
        this(context, null, 0, 0, 14, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public OTPView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public OTPView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public OTPView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.F = c.INSTANCE;
        this.G = a.INSTANCE;
        this.H = b.INSTANCE;
        this.I = new ArrayList();
        LayoutInflater.from(context).inflate(R.layout.otp_view_layout, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OTPView, 0, 0);
        try {
            this.h = obtainStyledAttributes.getInteger(R.styleable.OTPView_otp_itemCount, 1);
            this.i = obtainStyledAttributes.getBoolean(R.styleable.OTPView_otp_showCursor, false);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.OTPView_otp_underscoreCursor, false);
            this.k = obtainStyledAttributes.getDrawable(R.styleable.OTPView_otp_customCursor);
            this.l = obtainStyledAttributes.getInteger(R.styleable.OTPView_android_inputType, 0);
            this.m = obtainStyledAttributes.getInteger(R.styleable.OTPView_android_importantForAutofill, 0);
            this.n = obtainStyledAttributes.getString(R.styleable.OTPView_android_autofillHints);
            this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_itemWidth, 44);
            this.p = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_itemHeight, 44);
            obtainStyledAttributes.getColor(R.styleable.OTPView_otp_cursorColor, ViewCompat.MEASURED_STATE_MASK);
            this.q = obtainStyledAttributes.getBoolean(R.styleable.OTPView_otp_allcaps, false);
            this.r = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_marginBetween, p(8));
            this.s = obtainStyledAttributes.getBoolean(R.styleable.OTPView_otp_ispassword, false);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_textSize, p(14));
            this.t = dimensionPixelSize;
            int integer = obtainStyledAttributes.getInteger(R.styleable.OTPView_otp_textColor, ViewCompat.MEASURED_STATE_MASK);
            this.u = integer;
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.OTPView_otp_backgroundImage);
            this.v = drawable == null ? o() : drawable;
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.OTPView_otp_errorBackgroundImage);
            this.K = drawable2 == null ? o() : drawable2;
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 26) {
                this.w = obtainStyledAttributes.getFont(R.styleable.OTPView_otp_Font);
            } else {
                this.w = ResourcesCompat.getFont(context, R.font.roboto_regular);
            }
            this.x = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_highlightedTextSize, dimensionPixelSize);
            this.y = obtainStyledAttributes.getInteger(R.styleable.OTPView_otp_highlightedTextColor, integer);
            Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.OTPView_otp_highlightedBackgroundImage);
            this.z = drawable3 == null ? this.v : drawable3;
            if (i3 >= 26) {
                Typeface font = obtainStyledAttributes.getFont(R.styleable.OTPView_otp_highlightedFont);
                this.A = font == null ? this.w : font;
            } else {
                this.A = ResourcesCompat.getFont(context, R.font.roboto_regular);
            }
            this.B = obtainStyledAttributes.getDimensionPixelSize(R.styleable.OTPView_otp_filledTextSize, dimensionPixelSize);
            this.C = obtainStyledAttributes.getInteger(R.styleable.OTPView_otp_filledTextColor, integer);
            Drawable drawable4 = obtainStyledAttributes.getDrawable(R.styleable.OTPView_otp_filledBackgroundImage);
            this.D = drawable4 == null ? this.v : drawable4;
            if (i3 >= 26) {
                Typeface font2 = obtainStyledAttributes.getFont(R.styleable.OTPView_otp_filledFont);
                this.E = font2 == null ? this.w : font2;
            } else {
                this.E = ResourcesCompat.getFont(context, R.font.roboto_regular);
            }
            q();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static final void h(final EditText et, View view, boolean z) {
        Intrinsics.checkNotNullParameter(et, "$et");
        if (z) {
            et.post(new Runnable() { // from class: com.coveiot.android.theme.compundview.e
                @Override // java.lang.Runnable
                public final void run() {
                    OTPView.i(et);
                }
            });
        }
    }

    public static final void i(EditText et) {
        Intrinsics.checkNotNullParameter(et, "$et");
        et.setSelection(0);
    }

    public static final boolean k(OTPView this$0, int i, View view, int i2, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i2 == 67 && keyEvent.getAction() == 0) {
            this$0.L = true;
            this$0.I.get(i).setText("");
            this$0.n(false);
            this$0.L = false;
        }
        if (keyEvent.getAction() == 0 && i2 == 66 && this$0.isFilled()) {
            this$0.F.invoke(this$0.getStringFromFields());
        }
        return false;
    }

    public static final void l(final OTPView this$0, int i, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.J = i;
        }
        this$0.v();
        view.post(new Runnable() { // from class: com.coveiot.android.theme.compundview.g
            @Override // java.lang.Runnable
            public final void run() {
                OTPView.m(OTPView.this);
            }
        });
    }

    public static final void m(OTPView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.J < this$0.I.size()) {
            this$0.I.get(this$0.J).setSelection(0);
        }
    }

    public static final void r(OTPView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = this$0.I.get(this$0.J);
        editText.requestFocus();
        this$0.v();
        this$0.t(true, editText);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void callStyleDefault() {
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            u(this.I.get(i));
        }
    }

    public final void callStyleError() {
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            w(this.I.get(i));
        }
    }

    public final void clearText(boolean z) {
        this.L = true;
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).setText("");
        }
        this.J = 0;
        this.L = false;
        t(z, this.I.get(0));
    }

    public final void copyText() {
        Object systemService = getContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipData newPlainText = ClipData.newPlainText("Copied", getStringFromFields());
        Intrinsics.checkNotNullExpressionValue(newPlainText, "newPlainText(\"Copied\", getStringFromFields())");
        ((ClipboardManager) systemService).setPrimaryClip(newPlainText);
    }

    public final void fitToWidth(int i) {
        int p = ((i - (p(8) * 2)) / this.I.size()) - this.r;
        this.o = p;
        this.p = (int) (p * 1.25f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.o, this.p);
        int i2 = 0;
        for (Object obj : this.I) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            EditText editText = (EditText) obj;
            layoutParams.setMargins(i2 == 0 ? p(8) : p(0), p(8), this.r, p(8));
            editText.setLayoutParams(layoutParams);
            i2 = i3;
        }
    }

    public final void g(int i) {
        Unit unit;
        final EditText editText = new EditText(getContext());
        editText.setCursorVisible(this.i);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            Drawable drawable = this.k;
            if (drawable != null) {
                editText.setTextCursorDrawable(drawable);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null && this.j) {
                editText.setTextCursorDrawable(getResources().getDrawable(R.drawable.underscore));
            }
        }
        editText.setInputType(this.l);
        if (i2 >= 26) {
            editText.setImportantForAutofill(this.m);
            editText.setAutofillHints(new String[]{this.n});
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.o, this.p);
        editText.setAllCaps(this.q);
        layoutParams.setMargins(i == 0 ? p(8) : p(0), p(8), this.r, p(8));
        editText.setLayoutParams(layoutParams);
        editText.setGravity(17);
        u(editText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.coveiot.android.theme.compundview.b
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                OTPView.h(editText, view, z);
            }
        });
        this.I.add(editText);
        ((LinearLayout) _$_findCachedViewById(R.id.otp_wrapper)).addView(editText);
    }

    @Nullable
    public final Drawable getBackgroundImage() {
        return this.v;
    }

    @Nullable
    public final Drawable getErrorBackgroundImage() {
        return this.K;
    }

    @Nullable
    public final Drawable getFilledBackgroundImage() {
        return this.D;
    }

    @Nullable
    public final Drawable getHighlightedBackgroundImage() {
        return this.z;
    }

    @NotNull
    public final String getStringFromFields() {
        String str = "";
        for (EditText editText : this.I) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            Editable text = editText.getText();
            Intrinsics.checkNotNullExpressionValue(text, "it.text");
            sb.append(StringsKt___StringsKt.firstOrNull(text));
            str = sb.toString();
        }
        if (this.q) {
            String upperCase = str.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            return upperCase;
        }
        return str;
    }

    public final boolean isFilled() {
        boolean z;
        Iterator<T> it = this.I.iterator();
        do {
            z = true;
            if (!it.hasNext()) {
                return true;
            }
            Editable text = ((EditText) it.next()).getText();
            if (text != null && !m.isBlank(text)) {
                z = false;
                continue;
            }
        } while (!z);
        return false;
    }

    public final void j(final int i) {
        this.I.get(i).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.theme.compundview.OTPView$addListenerForIndex$1
            @NotNull
            public String h = "";
            @NotNull
            public String i = "";

            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                boolean z;
                List list;
                List list2;
                List list3;
                Function1 function1;
                z = OTPView.this.L;
                if (z) {
                    return;
                }
                list = OTPView.this.I;
                Editable text = ((EditText) list.get(i)).getText();
                Intrinsics.checkNotNullExpressionValue(text, "editTexts[index].text");
                if (text.length() == 0) {
                    OTPView.this.n(false);
                    return;
                }
                list2 = OTPView.this.I;
                if (((EditText) list2.get(i)).getText().length() <= 1) {
                    OTPView.this.n(true);
                } else if (editable != null) {
                    OTPView oTPView = OTPView.this;
                    int i2 = i;
                    if (isCopy()) {
                        oTPView.s(editable.toString(), i2, false);
                    } else {
                        list3 = oTPView.I;
                        ((EditText) list3.get(i2)).setText(String.valueOf(StringsKt___StringsKt.first(editable)));
                    }
                    function1 = oTPView.H;
                    function1.invoke(Boolean.valueOf(oTPView.isFilled()));
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                System.out.print((Object) ("Before Text Changed! " + ((Object) charSequence) + ' ' + i2 + ' ' + i3 + ' ' + i4));
                this.h = String.valueOf(charSequence);
            }

            @NotNull
            public final String getAfterText() {
                return this.i;
            }

            @NotNull
            public final String getBeforeText() {
                return this.h;
            }

            public final boolean isCopy() {
                return this.i.length() - this.h.length() > 1;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                System.out.print((Object) ("on Text Changed! " + ((Object) charSequence) + ' ' + i2 + ' ' + i4 + ' ' + i3));
                this.i = String.valueOf(charSequence);
            }

            public final void setAfterText(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.i = str;
            }

            public final void setBeforeText(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.h = str;
            }
        });
        this.I.get(i).setOnKeyListener(new View.OnKeyListener() { // from class: com.coveiot.android.theme.compundview.d
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
                boolean k;
                k = OTPView.k(OTPView.this, i, view, i2, keyEvent);
                return k;
            }
        });
        this.I.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.coveiot.android.theme.compundview.c
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                OTPView.l(OTPView.this, i, view, z);
            }
        });
        if (this.s) {
            for (EditText editText : this.I) {
                editText.setTransformationMethod(new com.coveiot.android.theme.compundview.a());
            }
        }
    }

    public final void n(boolean z) {
        this.J = z ? this.J + 1 : this.J - 1;
        int i = this.J;
        if (i < 0) {
            this.J = 0;
        } else if (i < this.I.size()) {
            this.I.get(this.J).requestFocus();
        } else {
            for (EditText editText : this.I) {
                editText.clearFocus();
            }
            t(false, (EditText) CollectionsKt___CollectionsKt.last((List<? extends Object>) this.I));
            if (isFilled()) {
                this.F.invoke(getStringFromFields());
            }
        }
        this.G.invoke(Boolean.valueOf(isFilled()));
        v();
    }

    public final Drawable o() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(p(8));
        gradientDrawable.setColor(-1);
        gradientDrawable.setStroke(p(2), ViewCompat.MEASURED_STATE_MASK);
        return gradientDrawable;
    }

    public final int p(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }

    public final void pasteText() {
        Object systemService = getContext().getSystemService("clipboard");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ClipData primaryClip = ((ClipboardManager) systemService).getPrimaryClip();
        if (primaryClip != null) {
            setText(primaryClip.getItemAt(0).getText().toString());
        }
    }

    public final void q() {
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            g(i2);
            j(i2);
        }
        v();
        this.I.get(0).postDelayed(new Runnable() { // from class: com.coveiot.android.theme.compundview.f
            @Override // java.lang.Runnable
            public final void run() {
                OTPView.r(OTPView.this);
            }
        }, 100L);
    }

    public final void s(String str, int i, boolean z) {
        String valueOf;
        String take = StringsKt___StringsKt.take(str, this.h - i);
        this.L = true;
        int size = this.I.size();
        while (i < size) {
            if (take.length() > 0) {
                EditText editText = this.I.get(i);
                if (this.q) {
                    valueOf = String.valueOf(StringsKt___StringsKt.first(take)).toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(valueOf, "this as java.lang.String).toUpperCase()");
                } else {
                    valueOf = String.valueOf(StringsKt___StringsKt.first(take));
                }
                editText.setText(valueOf);
                take = StringsKt__StringsKt.removeRange(take, 0, 1).toString();
            } else if (z) {
                this.I.get(i).setText("");
            }
            i++;
        }
        if (take.length() < this.I.size()) {
            int length = take.length();
            this.J = length;
            this.L = false;
            t(true, this.I.get(length));
        } else {
            for (EditText editText2 : this.I) {
                editText2.clearFocus();
            }
            this.J = this.I.size();
            this.L = false;
            t(false, (EditText) CollectionsKt___CollectionsKt.last((List<? extends Object>) this.I));
        }
        v();
    }

    public final void setBackgroundImage(@Nullable Drawable drawable) {
        this.v = drawable;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (EditText editText : this.I) {
            editText.setEnabled(z);
        }
    }

    public final void setErrorBackgroundImage(@Nullable Drawable drawable) {
        this.K = drawable;
    }

    public final void setFilledBackgroundImage(@Nullable Drawable drawable) {
        this.D = drawable;
    }

    public final void setHighlightedBackgroundImage(@Nullable Drawable drawable) {
        this.z = drawable;
    }

    public final void setOnCharacterUpdatedListener(@NotNull Function1<? super Boolean, Unit> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        this.G = func;
    }

    public final void setOnFilledListener(@NotNull Function1<? super Boolean, Unit> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        this.H = func;
    }

    public final void setOnFinishListener(@NotNull Function1<? super String, Unit> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        this.F = func;
    }

    public final void setText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        s(str, 0, true);
    }

    public final void t(boolean z, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (z) {
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(editText, 0);
            }
        } else if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(), 0);
        }
    }

    public final void u(EditText editText) {
        editText.setTextSize(0, this.t);
        editText.setTextColor(this.u);
        editText.setBackground(this.v);
        editText.setTypeface(this.w);
    }

    public final void v() {
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            EditText editText = this.I.get(i);
            int i2 = this.J;
            if (i < i2) {
                x(editText);
            } else if (i == i2) {
                y(editText);
            } else if (i > i2) {
                u(editText);
            }
        }
    }

    public final void w(EditText editText) {
        editText.setTextSize(0, this.x);
        editText.setTextColor(this.u);
        editText.setBackground(this.K);
        editText.setTypeface(this.w);
    }

    public final void x(EditText editText) {
        editText.setTextSize(0, this.B);
        editText.setTextColor(this.C);
        editText.setBackground(this.D);
        editText.setTypeface(this.E);
    }

    public final void y(EditText editText) {
        editText.setTextSize(0, this.x);
        editText.setTextColor(this.y);
        editText.setBackground(this.z);
        editText.setTypeface(this.A);
    }

    public /* synthetic */ OTPView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }
}
