package com.coveiot.android.leonardo.dashboard.model;

import com.coveiot.android.leonardo.dashboard.HealthNavigationElement;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class VitalsModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4796a;
    public int b;
    @NotNull
    public HealthNavigationElement c;

    public VitalsModel(@NotNull String vitalName, int i, @NotNull HealthNavigationElement healthNavigationElement) {
        Intrinsics.checkNotNullParameter(vitalName, "vitalName");
        Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
        this.f4796a = vitalName;
        this.b = i;
        this.c = healthNavigationElement;
    }

    public static /* synthetic */ VitalsModel copy$default(VitalsModel vitalsModel, String str, int i, HealthNavigationElement healthNavigationElement, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = vitalsModel.f4796a;
        }
        if ((i2 & 2) != 0) {
            i = vitalsModel.b;
        }
        if ((i2 & 4) != 0) {
            healthNavigationElement = vitalsModel.c;
        }
        return vitalsModel.copy(str, i, healthNavigationElement);
    }

    @NotNull
    public final String component1() {
        return this.f4796a;
    }

    public final int component2() {
        return this.b;
    }

    @NotNull
    public final HealthNavigationElement component3() {
        return this.c;
    }

    @NotNull
    public final VitalsModel copy(@NotNull String vitalName, int i, @NotNull HealthNavigationElement healthNavigationElement) {
        Intrinsics.checkNotNullParameter(vitalName, "vitalName");
        Intrinsics.checkNotNullParameter(healthNavigationElement, "healthNavigationElement");
        return new VitalsModel(vitalName, i, healthNavigationElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(VitalsModel.class, obj != null ? obj.getClass() : null)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.model.VitalsModel");
            return this.c == ((VitalsModel) obj).c;
        }
        return false;
    }

    @NotNull
    public final HealthNavigationElement getHealthNavigationElement() {
        return this.c;
    }

    public final int getVitalIcon() {
        return this.b;
    }

    @NotNull
    public final String getVitalName() {
        return this.f4796a;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public final void setHealthNavigationElement(@NotNull HealthNavigationElement healthNavigationElement) {
        Intrinsics.checkNotNullParameter(healthNavigationElement, "<set-?>");
        this.c = healthNavigationElement;
    }

    public final void setVitalIcon(int i) {
        this.b = i;
    }

    public final void setVitalName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4796a = str;
    }

    @NotNull
    public String toString() {
        return "VitalsModel(vitalName=" + this.f4796a + ", vitalIcon=" + this.b + ", healthNavigationElement=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
