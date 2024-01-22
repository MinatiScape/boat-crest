package com.coveiot.android.femalewellness.model;

import android.graphics.drawable.Drawable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FemaleWellnessSymptoms {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4395a;
    @Nullable
    public final Drawable b;
    @Nullable
    public final Drawable c;
    public boolean d;

    public FemaleWellnessSymptoms(@NotNull String symptomName, @Nullable Drawable drawable, @Nullable Drawable drawable2, boolean z) {
        Intrinsics.checkNotNullParameter(symptomName, "symptomName");
        this.f4395a = symptomName;
        this.b = drawable;
        this.c = drawable2;
        this.d = z;
    }

    public static /* synthetic */ FemaleWellnessSymptoms copy$default(FemaleWellnessSymptoms femaleWellnessSymptoms, String str, Drawable drawable, Drawable drawable2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = femaleWellnessSymptoms.f4395a;
        }
        if ((i & 2) != 0) {
            drawable = femaleWellnessSymptoms.b;
        }
        if ((i & 4) != 0) {
            drawable2 = femaleWellnessSymptoms.c;
        }
        if ((i & 8) != 0) {
            z = femaleWellnessSymptoms.d;
        }
        return femaleWellnessSymptoms.copy(str, drawable, drawable2, z);
    }

    @NotNull
    public final String component1() {
        return this.f4395a;
    }

    @Nullable
    public final Drawable component2() {
        return this.b;
    }

    @Nullable
    public final Drawable component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    @NotNull
    public final FemaleWellnessSymptoms copy(@NotNull String symptomName, @Nullable Drawable drawable, @Nullable Drawable drawable2, boolean z) {
        Intrinsics.checkNotNullParameter(symptomName, "symptomName");
        return new FemaleWellnessSymptoms(symptomName, drawable, drawable2, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FemaleWellnessSymptoms) {
            FemaleWellnessSymptoms femaleWellnessSymptoms = (FemaleWellnessSymptoms) obj;
            return Intrinsics.areEqual(this.f4395a, femaleWellnessSymptoms.f4395a) && Intrinsics.areEqual(this.b, femaleWellnessSymptoms.b) && Intrinsics.areEqual(this.c, femaleWellnessSymptoms.c) && this.d == femaleWellnessSymptoms.d;
        }
        return false;
    }

    @Nullable
    public final Drawable getSelectedSymptomImage() {
        return this.c;
    }

    @Nullable
    public final Drawable getSymptomImage() {
        return this.b;
    }

    @NotNull
    public final String getSymptomName() {
        return this.f4395a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.f4395a.hashCode() * 31;
        Drawable drawable = this.b;
        int hashCode2 = (hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Drawable drawable2 = this.c;
        int hashCode3 = (hashCode2 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        boolean z = this.d;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public final boolean isSelected() {
        return this.d;
    }

    public final void setSelected(boolean z) {
        this.d = z;
    }

    @NotNull
    public String toString() {
        return "FemaleWellnessSymptoms(symptomName=" + this.f4395a + ", symptomImage=" + this.b + ", selectedSymptomImage=" + this.c + ", isSelected=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
