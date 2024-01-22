package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class FitnessConfigCommandInterpreter extends CommandInterpreter<SFitnessConfigInfo, SetFitnessInfoRequest> {
    @NotNull
    public final Context b;
    @NotNull
    public final SFitnessConfigInfo c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessConfigCommandInterpreter(@NotNull Context context, @NotNull SFitnessConfigInfo sFitnessConfigInfo) {
        super(sFitnessConfigInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sFitnessConfigInfo, "sFitnessConfigInfo");
        this.b = context;
        this.c = sFitnessConfigInfo;
    }

    public final MeasurementUnitType a(boolean z) {
        return z ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.coveiot.android.bleabstract.request.SetFitnessInfoRequest getBleRequestObject() {
        /*
            r7 = this;
            com.coveiot.repository.profile.ProfileRepository r0 = com.coveiot.repository.profile.ProfileRepository.getInstance()
            android.content.Context r1 = r7.b
            com.coveiot.covedb.profile.entities.EntityProfile r0 = r0.getLatestProfileData(r1)
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            r2 = 0
            if (r1 == 0) goto L14
            java.lang.String r1 = r1.getHeight()
            goto L15
        L14:
            r1 = r2
        L15:
            r3 = 0
            if (r1 == 0) goto L38
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            java.lang.String r1 = r1.getHeight()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            double r5 = java.lang.Double.parseDouble(r1)
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L38
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            java.lang.String r1 = r1.getHeight()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            double r5 = java.lang.Double.parseDouble(r1)
            goto L3b
        L38:
            int r1 = r0.height
            double r5 = (double) r1
        L3b:
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            if (r1 == 0) goto L43
            java.lang.String r2 = r1.getWeight()
        L43:
            if (r2 == 0) goto L64
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            java.lang.String r1 = r1.getWeight()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            double r1 = java.lang.Double.parseDouble(r1)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L64
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            java.lang.String r1 = r1.getWeight()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            double r1 = java.lang.Double.parseDouble(r1)
            goto L66
        L64:
            double r1 = r0.weight
        L66:
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder r3 = new com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder
            int r4 = (int) r5
            r3.<init>(r4, r1)
            int r1 = r0.walkStrideLength
            double r1 = (double) r1
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder r1 = r3.setStride(r1)
            int r2 = r0.runStrideLength
            double r2 = (double) r2
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder r1 = r1.setRunStride(r2)
            int r0 = r0.gender
            r2 = 1
            if (r0 != r2) goto L80
            goto L81
        L80:
            r2 = 0
        L81:
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder r0 = r1.setMale(r2)
            com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo r1 = r7.c
            boolean r1 = r1.isDistanceInMile()
            com.coveiot.android.bleabstract.models.MeasurementUnitType r1 = r7.a(r1)
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest$Builder r0 = r0.setUnitType(r1)
            com.coveiot.android.bleabstract.request.SetFitnessInfoRequest r0 = r0.builder()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.alexa.commandinterpreter.FitnessConfigCommandInterpreter.getBleRequestObject():com.coveiot.android.bleabstract.request.SetFitnessInfoRequest");
    }
}
