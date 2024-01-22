package com.github.anastr.speedviewlib.components;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.anastr.speedviewlib.Gauge;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 92\u00020\u0001:\u00019B5\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010'\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010/\u001a\u00020(¢\u0006\u0004\b4\u00105B\u0011\b\u0016\u0012\u0006\u00106\u001a\u00020\u0000¢\u0006\u0004\b4\u00107B\u0011\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b4\u00108J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0017\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016R*\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR*\u0010 \u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR*\u0010'\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00118\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R*\u0010/\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020(8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR$\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010\u0019\"\u0004\b3\u0010\u001b¨\u0006:"}, d2 = {"Lcom/github/anastr/speedviewlib/components/Section;", "Landroid/os/Parcelable;", "", "startOffset", "endOffset", "", "setStartEndOffset", "Lcom/github/anastr/speedviewlib/Gauge;", "gauge", "inGauge$speedviewlib_release", "(Lcom/github/anastr/speedviewlib/Gauge;)Lcom/github/anastr/speedviewlib/components/Section;", "inGauge", "clearGauge$speedviewlib_release", "()V", "clearGauge", "Landroid/os/Parcel;", "parcel", "", "flags", "writeToParcel", "describeContents", "value", "i", WeatherCriteria.UNIT_FARENHEIT, "getWidth", "()F", "setWidth", "(F)V", Property.ICON_TEXT_FIT_WIDTH, "j", "getPadding", "setPadding", "padding", "m", "I", "getColor", "()I", "setColor", "(I)V", "color", "Lcom/github/anastr/speedviewlib/components/Style;", "n", "Lcom/github/anastr/speedviewlib/components/Style;", "getStyle", "()Lcom/github/anastr/speedviewlib/components/Style;", "setStyle", "(Lcom/github/anastr/speedviewlib/components/Style;)V", "style", "getStartOffset", "setStartOffset", "getEndOffset", "setEndOffset", "<init>", "(FFIFLcom/github/anastr/speedviewlib/components/Style;)V", "section", "(Lcom/github/anastr/speedviewlib/components/Section;)V", "(Landroid/os/Parcel;)V", "CREATOR", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class Section implements Parcelable {
    @NotNull
    public static final CREATOR CREATOR = new CREATOR(null);
    @Nullable
    public Gauge h;
    public float i;
    public float j;
    public float k;
    public float l;
    public int m;
    @NotNull
    public Style n;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u001f\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/github/anastr/speedviewlib/components/Section$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/github/anastr/speedviewlib/components/Section;", "Landroid/os/Parcel;", "parcel", "createFromParcel", "", "size", "", "newArray", "(I)[Lcom/github/anastr/speedviewlib/components/Section;", "<init>", "()V", "speedviewlib_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes9.dex */
    public static final class CREATOR implements Parcelable.Creator<Section> {
        public CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public Section createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Section(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public Section[] newArray(int i) {
            return new Section[i];
        }
    }

    @JvmOverloads
    public Section(float f, float f2, int i) {
        this(f, f2, i, 0.0f, null, 24, null);
    }

    @JvmOverloads
    public Section(float f, float f2, int i, float f3) {
        this(f, f2, i, f3, null, 16, null);
    }

    @JvmOverloads
    public Section(float f, float f2, int i, float f3, @NotNull Style style) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.i = f3;
        this.k = f;
        this.l = f2;
        this.m = i;
        this.n = style;
    }

    public final void clearGauge$speedviewlib_release() {
        this.h = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getColor() {
        return this.m;
    }

    public final float getEndOffset() {
        return this.l;
    }

    public final float getPadding() {
        return this.j;
    }

    public final float getStartOffset() {
        return this.k;
    }

    @NotNull
    public final Style getStyle() {
        return this.n;
    }

    public final float getWidth() {
        return this.i;
    }

    @NotNull
    public final Section inGauge$speedviewlib_release(@NotNull Gauge gauge) {
        Intrinsics.checkNotNullParameter(gauge, "gauge");
        if (this.h == null) {
            this.h = gauge;
            return this;
        }
        throw new IllegalArgumentException("This instance of Section is already attached to a Gauge.".toString());
    }

    public final void setColor(int i) {
        this.m = i;
        Gauge gauge = this.h;
        if (gauge == null) {
            return;
        }
        gauge.invalidateGauge();
    }

    public final void setEndOffset(float f) {
        setStartEndOffset(getStartOffset(), f);
    }

    public final void setPadding(float f) {
        this.j = f;
        Gauge gauge = this.h;
        if (gauge == null) {
            return;
        }
        gauge.invalidateGauge();
    }

    public final void setStartEndOffset(float f, float f2) {
        this.k = f;
        this.l = f2;
        Gauge gauge = this.h;
        if (gauge != null) {
            gauge.checkSection$speedviewlib_release(this);
        }
        Gauge gauge2 = this.h;
        if (gauge2 != null) {
            gauge2.checkSectionChange$speedviewlib_release();
        }
        Gauge gauge3 = this.h;
        if (gauge3 == null) {
            return;
        }
        gauge3.invalidateGauge();
    }

    public final void setStartOffset(float f) {
        setStartEndOffset(f, getEndOffset());
    }

    public final void setStyle(@NotNull Style value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.n = value;
        Gauge gauge = this.h;
        if (gauge == null) {
            return;
        }
        gauge.invalidateGauge();
    }

    public final void setWidth(float f) {
        this.i = f;
        Gauge gauge = this.h;
        if (gauge == null) {
            return;
        }
        gauge.invalidateGauge();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeFloat(getStartOffset());
        parcel.writeFloat(getEndOffset());
        parcel.writeInt(this.m);
        parcel.writeFloat(this.i);
        parcel.writeSerializable(Integer.valueOf(this.n.ordinal()));
        parcel.writeFloat(this.j);
    }

    public /* synthetic */ Section(float f, float f2, int i, float f3, Style style, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, i, (i2 & 8) != 0 ? 0.0f : f3, (i2 & 16) != 0 ? Style.BUTT : style);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Section(@NotNull Section section) {
        this(section.getStartOffset(), section.getEndOffset(), section.m, section.i, section.n);
        Intrinsics.checkNotNullParameter(section, "section");
        setPadding(section.j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Section(@org.jetbrains.annotations.NotNull android.os.Parcel r8) {
        /*
            r7 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            float r2 = r8.readFloat()
            float r3 = r8.readFloat()
            int r4 = r8.readInt()
            float r5 = r8.readFloat()
            java.io.Serializable r0 = r8.readSerializable()
            java.lang.String r1 = "null cannot be cast to non-null type com.github.anastr.speedviewlib.components.Style"
            java.util.Objects.requireNonNull(r0, r1)
            r6 = r0
            com.github.anastr.speedviewlib.components.Style r6 = (com.github.anastr.speedviewlib.components.Style) r6
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            float r8 = r8.readFloat()
            r7.setPadding(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.anastr.speedviewlib.components.Section.<init>(android.os.Parcel):void");
    }
}
