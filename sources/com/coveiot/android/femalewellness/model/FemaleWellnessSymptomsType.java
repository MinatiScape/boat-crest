package com.coveiot.android.femalewellness.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FemaleWellnessSymptomsType {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4396a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final List<FemaleWellnessSymptoms> d;

    public FemaleWellnessSymptomsType(@NotNull String symptomType, @NotNull String symptomRecordedDate, @NotNull String symptomTypeInfo, @NotNull List<FemaleWellnessSymptoms> symptomsList) {
        Intrinsics.checkNotNullParameter(symptomType, "symptomType");
        Intrinsics.checkNotNullParameter(symptomRecordedDate, "symptomRecordedDate");
        Intrinsics.checkNotNullParameter(symptomTypeInfo, "symptomTypeInfo");
        Intrinsics.checkNotNullParameter(symptomsList, "symptomsList");
        this.f4396a = symptomType;
        this.b = symptomRecordedDate;
        this.c = symptomTypeInfo;
        this.d = symptomsList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FemaleWellnessSymptomsType copy$default(FemaleWellnessSymptomsType femaleWellnessSymptomsType, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = femaleWellnessSymptomsType.f4396a;
        }
        if ((i & 2) != 0) {
            str2 = femaleWellnessSymptomsType.b;
        }
        if ((i & 4) != 0) {
            str3 = femaleWellnessSymptomsType.c;
        }
        if ((i & 8) != 0) {
            list = femaleWellnessSymptomsType.d;
        }
        return femaleWellnessSymptomsType.copy(str, str2, str3, list);
    }

    @NotNull
    public final String component1() {
        return this.f4396a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final List<FemaleWellnessSymptoms> component4() {
        return this.d;
    }

    @NotNull
    public final FemaleWellnessSymptomsType copy(@NotNull String symptomType, @NotNull String symptomRecordedDate, @NotNull String symptomTypeInfo, @NotNull List<FemaleWellnessSymptoms> symptomsList) {
        Intrinsics.checkNotNullParameter(symptomType, "symptomType");
        Intrinsics.checkNotNullParameter(symptomRecordedDate, "symptomRecordedDate");
        Intrinsics.checkNotNullParameter(symptomTypeInfo, "symptomTypeInfo");
        Intrinsics.checkNotNullParameter(symptomsList, "symptomsList");
        return new FemaleWellnessSymptomsType(symptomType, symptomRecordedDate, symptomTypeInfo, symptomsList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FemaleWellnessSymptomsType) {
            FemaleWellnessSymptomsType femaleWellnessSymptomsType = (FemaleWellnessSymptomsType) obj;
            return Intrinsics.areEqual(this.f4396a, femaleWellnessSymptomsType.f4396a) && Intrinsics.areEqual(this.b, femaleWellnessSymptomsType.b) && Intrinsics.areEqual(this.c, femaleWellnessSymptomsType.c) && Intrinsics.areEqual(this.d, femaleWellnessSymptomsType.d);
        }
        return false;
    }

    @NotNull
    public final String getSymptomRecordedDate() {
        return this.b;
    }

    @NotNull
    public final String getSymptomType() {
        return this.f4396a;
    }

    @NotNull
    public final String getSymptomTypeInfo() {
        return this.c;
    }

    @NotNull
    public final List<FemaleWellnessSymptoms> getSymptomsList() {
        return this.d;
    }

    public int hashCode() {
        return (((((this.f4396a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }

    @NotNull
    public String toString() {
        return "FemaleWellnessSymptomsType(symptomType=" + this.f4396a + ", symptomRecordedDate=" + this.b + ", symptomTypeInfo=" + this.c + ", symptomsList=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }
}
