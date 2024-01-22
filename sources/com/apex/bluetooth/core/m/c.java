package com.apex.bluetooth.core.m;

import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.MotionDataReportCallback;
import com.apex.bluetooth.callback.MotionDataResponseCallback;
import com.apex.bluetooth.core.EABleManager;
import com.apex.bluetooth.enumeration.CommonFlag;
import com.apex.bluetooth.enumeration.MotionReportType;
import com.apex.bluetooth.model.EABleGeneralSportRespond;
import com.apex.bluetooth.utils.LogData2File;
import com.apex.bluetooth.utils.LogUtils;
/* loaded from: classes.dex */
public class c implements EABleCallback {

    /* renamed from: a  reason: collision with root package name */
    public boolean f2194a;
    public MotionDataReportCallback b;
    public com.apex.bluetooth.core.a c;
    public final String d = c.class.getSimpleName();
    public Thread e;

    /* loaded from: classes.dex */
    public class a implements MotionDataResponseCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f2195a;

        public a(int i) {
            this.f2195a = i;
        }

        @Override // com.apex.bluetooth.callback.EABleCallback
        public void mutualFail(int i) {
            LogUtils.i(c.this.d, "大数据回应失败");
            LogData2File.getInstance().saveLogData("大数据回应失败");
            c.a(c.this, this.f2195a);
        }

        @Override // com.apex.bluetooth.callback.MotionDataResponseCallback
        public void mutualSuccess() {
            LogUtils.i(c.this.d, "大数据回应成功");
        }
    }

    public c(MotionDataReportCallback motionDataReportCallback, com.apex.bluetooth.core.a aVar) {
        this.b = motionDataReportCallback;
        this.c = aVar;
    }

    public final void a(int i, int i2) {
        CommonFlag commonFlag;
        EABleGeneralSportRespond eABleGeneralSportRespond = new EABleGeneralSportRespond();
        eABleGeneralSportRespond.setRequest_id(i);
        if (i2 == 0) {
            commonFlag = CommonFlag.begin;
        } else if (i2 == 1) {
            commonFlag = CommonFlag.proceed;
        } else if (i2 == 2) {
            commonFlag = CommonFlag.end;
        } else {
            commonFlag = i2 == 3 ? CommonFlag.begin_end : null;
        }
        eABleGeneralSportRespond.setE_common_flag(commonFlag);
        com.apex.bluetooth.core.a aVar = this.c;
        if (aVar != null && !this.f2194a) {
            aVar.b(new com.apex.bluetooth.data_package.c.b().a(eABleGeneralSportRespond), new a(i));
            return;
        }
        LogUtils.i(this.d, "大数据回应的时候已断连");
        LogData2File.getInstance().saveLogData("大数据回应的时候已断连");
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        MotionDataReportCallback motionDataReportCallback = this.b;
        if (motionDataReportCallback != null) {
            motionDataReportCallback.mutualFail(i);
        }
    }

    public static void a(c cVar, int i) {
        if (cVar.f2194a || cVar.c == null) {
            return;
        }
        MotionReportType motionReportType = MotionReportType.multi_sports_data_req;
        if (i == 3001) {
            motionReportType = MotionReportType.sport_data_req;
        } else if (i == 3002) {
            motionReportType = MotionReportType.sleep_data_req;
        } else if (i == 3003) {
            motionReportType = MotionReportType.hr_data_req;
        } else if (i == 3004) {
            motionReportType = MotionReportType.gps_data_req;
        } else if (i != 3005) {
            if (i == 3006) {
                motionReportType = MotionReportType.blood_oxygen_data_req;
            } else if (i == 3007) {
                motionReportType = MotionReportType.pressure_data_req;
            } else if (i == 3008) {
                motionReportType = MotionReportType.step_freq_data_req;
            } else if (i == 3009) {
                motionReportType = MotionReportType.pace_data_req;
            } else if (i == 3010) {
                motionReportType = MotionReportType.resting_hr_data_req;
            }
        }
        EABleManager.getInstance().requestSyncMotionData(motionReportType, new d(cVar, i));
    }
}
