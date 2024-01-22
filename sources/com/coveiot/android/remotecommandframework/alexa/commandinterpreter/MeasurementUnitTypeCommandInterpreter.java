package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SMeasurementUnitInfo;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.repository.profile.ProfileRepository;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class MeasurementUnitTypeCommandInterpreter extends CommandInterpreter<SMeasurementUnitInfo, SetFitnessInfoRequest> {
    @NotNull
    public final Context b;
    @NotNull
    public final SMeasurementUnitInfo c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MeasurementUnitTypeCommandInterpreter(@NotNull Context context, @NotNull SMeasurementUnitInfo sMeasurementUnitInfo) {
        super(sMeasurementUnitInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sMeasurementUnitInfo, "sMeasurementUnitInfo");
        this.b = context;
        this.c = sMeasurementUnitInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetFitnessInfoRequest getBleRequestObject() {
        Integer unit;
        EntityProfile latestProfileData = ProfileRepository.getInstance().getLatestProfileData(this.b);
        return new SetFitnessInfoRequest.Builder(latestProfileData.height, latestProfileData.weight).setStride(latestProfileData.walkStrideLength).setRunStride(latestProfileData.runStrideLength).setMale(latestProfileData.gender == 1).setUnitType((this.c.getUnit() == null || ((unit = this.c.getUnit()) != null && unit.intValue() == 0)) ? MeasurementUnitType.METRIC : MeasurementUnitType.IMPERIAL).builder();
    }
}
