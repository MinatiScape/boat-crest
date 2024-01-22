package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SScheduleInfo;
import com.coveiot.repository.RepositoryUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class DndCommandInterpreter extends CommandInterpreter<SDNDInfo, SetDNDModeRequest> {
    @NotNull
    public final Context b;
    @NotNull
    public final SDNDInfo c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DndCommandInterpreter(@NotNull Context context, @NotNull SDNDInfo sDNDInfo) {
        super(sDNDInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sDNDInfo, "sDNDInfo");
        this.b = context;
        this.c = sDNDInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetDNDModeRequest getBleRequestObject() {
        SScheduleInfo sScheduleInfo;
        SScheduleInfo sScheduleInfo2;
        if (this.c.getSchedules() != null) {
            SDNDInfo sDNDInfo = this.c;
            Intrinsics.checkNotNull(sDNDInfo);
            List<SScheduleInfo> schedules = sDNDInfo.getSchedules();
            Intrinsics.checkNotNull(schedules);
            if (!schedules.isEmpty()) {
                SDNDInfo sDNDInfo2 = this.c;
                Intrinsics.checkNotNull(sDNDInfo2);
                List<SScheduleInfo> schedules2 = sDNDInfo2.getSchedules();
                String str = null;
                String startTime = (schedules2 == null || (sScheduleInfo2 = schedules2.get(0)) == null) ? null : sScheduleInfo2.getStartTime();
                Intrinsics.checkNotNull(startTime);
                SDNDInfo sDNDInfo3 = this.c;
                Intrinsics.checkNotNull(sDNDInfo3);
                List<SScheduleInfo> schedules3 = sDNDInfo3.getSchedules();
                if (schedules3 != null && (sScheduleInfo = schedules3.get(0)) != null) {
                    str = sScheduleInfo.getEndTime();
                }
                Intrinsics.checkNotNull(str);
                int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                String str2 = str;
                int parseInt3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                int parseInt4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                if (parseInt == 0 && parseInt2 == 0 && parseInt3 == 0 && parseInt4 == 0) {
                    if (!RepositoryUtils.isKaHaDevice(this.b) && !RepositoryUtils.isKaHaDeviceREM(this.b)) {
                        return new SetDNDModeRequest.Builder(this.c.getActive(), 0, 0, 23, 59).build();
                    }
                    return new SetDNDModeRequest.Builder(this.c.getActive(), 0, 0, 0, 0).build();
                }
                return new SetDNDModeRequest.Builder(this.c.getActive(), parseInt, parseInt2, parseInt3, parseInt4).build();
            }
        }
        if (!RepositoryUtils.isKaHaDevice(this.b) && !RepositoryUtils.isKaHaDeviceREM(this.b)) {
            return new SetDNDModeRequest.Builder(this.c.getActive(), 0, 0, 23, 59).build();
        }
        return new SetDNDModeRequest.Builder(this.c.getActive(), 0, 0, 0, 0).build();
    }
}
