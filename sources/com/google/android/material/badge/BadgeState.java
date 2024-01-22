package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.annotation.XmlRes;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class BadgeState {

    /* renamed from: a  reason: collision with root package name */
    public final State f10220a;
    public final State b;
    public final float c;
    public final float d;
    public final float e;

    public BadgeState(Context context, @XmlRes int i, @AttrRes int i2, @StyleRes int i3, @Nullable State state) {
        CharSequence charSequence;
        int i4;
        int i5;
        int i6;
        int intValue;
        int intValue2;
        int intValue3;
        int intValue4;
        int intValue5;
        int intValue6;
        Locale locale;
        State state2 = new State();
        this.b = state2;
        state = state == null ? new State() : state;
        if (i != 0) {
            state.h = i;
        }
        TypedArray b = b(context, state.h, i2, i3);
        Resources resources = context.getResources();
        this.c = b.getDimensionPixelSize(R.styleable.Badge_badgeRadius, resources.getDimensionPixelSize(R.dimen.mtrl_badge_radius));
        this.e = b.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding));
        this.d = b.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, resources.getDimensionPixelSize(R.dimen.mtrl_badge_with_text_radius));
        state2.k = state.k == -2 ? 255 : state.k;
        if (state.o != null) {
            charSequence = state.o;
        } else {
            charSequence = context.getString(R.string.mtrl_badge_numberless_content_description);
        }
        state2.o = charSequence;
        if (state.p != 0) {
            i4 = state.p;
        } else {
            i4 = R.plurals.mtrl_badge_content_description;
        }
        state2.p = i4;
        if (state.q != 0) {
            i5 = state.q;
        } else {
            i5 = R.string.mtrl_exceed_max_badge_number_content_description;
        }
        state2.q = i5;
        state2.s = Boolean.valueOf(state.s == null || state.s.booleanValue());
        if (state.m != -2) {
            i6 = state.m;
        } else {
            i6 = b.getInt(R.styleable.Badge_maxCharacterCount, 4);
        }
        state2.m = i6;
        if (state.l != -2) {
            state2.l = state.l;
        } else {
            int i7 = R.styleable.Badge_number;
            if (b.hasValue(i7)) {
                state2.l = b.getInt(i7, 0);
            } else {
                state2.l = -1;
            }
        }
        if (state.i != null) {
            intValue = state.i.intValue();
        } else {
            intValue = v(context, b, R.styleable.Badge_backgroundColor);
        }
        state2.i = Integer.valueOf(intValue);
        if (state.j != null) {
            state2.j = state.j;
        } else {
            int i8 = R.styleable.Badge_badgeTextColor;
            if (b.hasValue(i8)) {
                state2.j = Integer.valueOf(v(context, b, i8));
            } else {
                state2.j = Integer.valueOf(new TextAppearance(context, R.style.TextAppearance_MaterialComponents_Badge).getTextColor().getDefaultColor());
            }
        }
        if (state.r != null) {
            intValue2 = state.r.intValue();
        } else {
            intValue2 = b.getInt(R.styleable.Badge_badgeGravity, 8388661);
        }
        state2.r = Integer.valueOf(intValue2);
        if (state.t != null) {
            intValue3 = state.t.intValue();
        } else {
            intValue3 = b.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0);
        }
        state2.t = Integer.valueOf(intValue3);
        if (state.t != null) {
            intValue4 = state.u.intValue();
        } else {
            intValue4 = b.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0);
        }
        state2.u = Integer.valueOf(intValue4);
        if (state.v != null) {
            intValue5 = state.v.intValue();
        } else {
            intValue5 = b.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, state2.t.intValue());
        }
        state2.v = Integer.valueOf(intValue5);
        if (state.w != null) {
            intValue6 = state.w.intValue();
        } else {
            intValue6 = b.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, state2.u.intValue());
        }
        state2.w = Integer.valueOf(intValue6);
        state2.x = Integer.valueOf(state.x == null ? 0 : state.x.intValue());
        state2.y = Integer.valueOf(state.y != null ? state.y.intValue() : 0);
        b.recycle();
        if (state.n != null) {
            state2.n = state.n;
        } else {
            if (Build.VERSION.SDK_INT >= 24) {
                locale = Locale.getDefault(Locale.Category.FORMAT);
            } else {
                locale = Locale.getDefault();
            }
            state2.n = locale;
        }
        this.f10220a = state;
    }

    public static int v(Context context, @NonNull TypedArray typedArray, @StyleableRes int i) {
        return MaterialResources.getColorStateList(context, typedArray, i).getDefaultColor();
    }

    public void A(int i) {
        this.f10220a.r = Integer.valueOf(i);
        this.b.r = Integer.valueOf(i);
    }

    public void B(@ColorInt int i) {
        this.f10220a.j = Integer.valueOf(i);
        this.b.j = Integer.valueOf(i);
    }

    public void C(@StringRes int i) {
        this.f10220a.q = i;
        this.b.q = i;
    }

    public void D(CharSequence charSequence) {
        this.f10220a.o = charSequence;
        this.b.o = charSequence;
    }

    public void E(@PluralsRes int i) {
        this.f10220a.p = i;
        this.b.p = i;
    }

    public void F(@Dimension(unit = 1) int i) {
        this.f10220a.v = Integer.valueOf(i);
        this.b.v = Integer.valueOf(i);
    }

    public void G(@Dimension(unit = 1) int i) {
        this.f10220a.t = Integer.valueOf(i);
        this.b.t = Integer.valueOf(i);
    }

    public void H(int i) {
        this.f10220a.m = i;
        this.b.m = i;
    }

    public void I(int i) {
        this.f10220a.l = i;
        this.b.l = i;
    }

    public void J(Locale locale) {
        this.f10220a.n = locale;
        this.b.n = locale;
    }

    public void K(@Dimension(unit = 1) int i) {
        this.f10220a.w = Integer.valueOf(i);
        this.b.w = Integer.valueOf(i);
    }

    public void L(@Dimension(unit = 1) int i) {
        this.f10220a.u = Integer.valueOf(i);
        this.b.u = Integer.valueOf(i);
    }

    public void M(boolean z) {
        this.f10220a.s = Boolean.valueOf(z);
        this.b.s = Boolean.valueOf(z);
    }

    public void a() {
        I(-1);
    }

    public final TypedArray b(Context context, @XmlRes int i, @AttrRes int i2, @StyleRes int i3) {
        AttributeSet attributeSet;
        int i4;
        if (i != 0) {
            AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, i, "badge");
            i4 = parseDrawableXml.getStyleAttribute();
            attributeSet = parseDrawableXml;
        } else {
            attributeSet = null;
            i4 = 0;
        }
        return ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Badge, i2, i4 == 0 ? i3 : i4, new int[0]);
    }

    @Dimension(unit = 1)
    public int c() {
        return this.b.x.intValue();
    }

    @Dimension(unit = 1)
    public int d() {
        return this.b.y.intValue();
    }

    public int e() {
        return this.b.k;
    }

    @ColorInt
    public int f() {
        return this.b.i.intValue();
    }

    public int g() {
        return this.b.r.intValue();
    }

    @ColorInt
    public int h() {
        return this.b.j.intValue();
    }

    @StringRes
    public int i() {
        return this.b.q;
    }

    public CharSequence j() {
        return this.b.o;
    }

    @PluralsRes
    public int k() {
        return this.b.p;
    }

    @Dimension(unit = 1)
    public int l() {
        return this.b.v.intValue();
    }

    @Dimension(unit = 1)
    public int m() {
        return this.b.t.intValue();
    }

    public int n() {
        return this.b.m;
    }

    public int o() {
        return this.b.l;
    }

    public Locale p() {
        return this.b.n;
    }

    public State q() {
        return this.f10220a;
    }

    @Dimension(unit = 1)
    public int r() {
        return this.b.w.intValue();
    }

    @Dimension(unit = 1)
    public int s() {
        return this.b.u.intValue();
    }

    public boolean t() {
        return this.b.l != -1;
    }

    public boolean u() {
        return this.b.s.booleanValue();
    }

    public void w(@Dimension(unit = 1) int i) {
        this.f10220a.x = Integer.valueOf(i);
        this.b.x = Integer.valueOf(i);
    }

    public void x(@Dimension(unit = 1) int i) {
        this.f10220a.y = Integer.valueOf(i);
        this.b.y = Integer.valueOf(i);
    }

    public void y(int i) {
        this.f10220a.k = i;
        this.b.k = i;
    }

    public void z(@ColorInt int i) {
        this.f10220a.i = Integer.valueOf(i);
        this.b.i = Integer.valueOf(i);
    }

    /* loaded from: classes10.dex */
    public static final class State implements Parcelable {
        public static final Parcelable.Creator<State> CREATOR = new a();
        @XmlRes
        public int h;
        @ColorInt
        public Integer i;
        @ColorInt
        public Integer j;
        public int k;
        public int l;
        public int m;
        public Locale n;
        @Nullable
        public CharSequence o;
        @PluralsRes
        public int p;
        @StringRes
        public int q;
        public Integer r;
        public Boolean s;
        @Dimension(unit = 1)
        public Integer t;
        @Dimension(unit = 1)
        public Integer u;
        @Dimension(unit = 1)
        public Integer v;
        @Dimension(unit = 1)
        public Integer w;
        @Dimension(unit = 1)
        public Integer x;
        @Dimension(unit = 1)
        public Integer y;

        /* loaded from: classes10.dex */
        public class a implements Parcelable.Creator<State> {
            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: a */
            public State createFromParcel(@NonNull Parcel parcel) {
                return new State(parcel);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: b */
            public State[] newArray(int i) {
                return new State[i];
            }
        }

        public State() {
            this.k = 255;
            this.l = -2;
            this.m = -2;
            this.s = Boolean.TRUE;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeInt(this.h);
            parcel.writeSerializable(this.i);
            parcel.writeSerializable(this.j);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
            parcel.writeInt(this.m);
            CharSequence charSequence = this.o;
            parcel.writeString(charSequence == null ? null : charSequence.toString());
            parcel.writeInt(this.p);
            parcel.writeSerializable(this.r);
            parcel.writeSerializable(this.t);
            parcel.writeSerializable(this.u);
            parcel.writeSerializable(this.v);
            parcel.writeSerializable(this.w);
            parcel.writeSerializable(this.x);
            parcel.writeSerializable(this.y);
            parcel.writeSerializable(this.s);
            parcel.writeSerializable(this.n);
        }

        public State(@NonNull Parcel parcel) {
            this.k = 255;
            this.l = -2;
            this.m = -2;
            this.s = Boolean.TRUE;
            this.h = parcel.readInt();
            this.i = (Integer) parcel.readSerializable();
            this.j = (Integer) parcel.readSerializable();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
            this.m = parcel.readInt();
            this.o = parcel.readString();
            this.p = parcel.readInt();
            this.r = (Integer) parcel.readSerializable();
            this.t = (Integer) parcel.readSerializable();
            this.u = (Integer) parcel.readSerializable();
            this.v = (Integer) parcel.readSerializable();
            this.w = (Integer) parcel.readSerializable();
            this.x = (Integer) parcel.readSerializable();
            this.y = (Integer) parcel.readSerializable();
            this.s = (Boolean) parcel.readSerializable();
            this.n = (Locale) parcel.readSerializable();
        }
    }
}
