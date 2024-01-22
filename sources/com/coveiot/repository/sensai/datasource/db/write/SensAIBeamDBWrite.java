package com.coveiot.repository.sensai.datasource.db.write;

import android.content.Context;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.repository.sensai.SensAIRepository;
import java.util.List;
/* loaded from: classes9.dex */
public class SensAIBeamDBWrite {
    public static SensAIBeamDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public final SensAIRepository f7435a;

    public SensAIBeamDBWrite(Context context) {
        this.f7435a = SensAIRepository.getInstance(context);
    }

    public static SensAIBeamDBWrite getInstance(Context context) {
        if (b == null) {
            b = new SensAIBeamDBWrite(context);
        }
        return b;
    }

    public void insertActivitySummaryDetails(SensAIActivitySummaryDetails sensAIActivitySummaryDetails) {
        this.f7435a.sensAIDataDao.insertActivitySummaryDetails(sensAIActivitySummaryDetails);
    }

    public void insertActivitySummaryList(List<SensAIActivitySummary> list) {
        this.f7435a.sensAIDataDao.insertActivitySummary(list);
    }

    public void updateActivitySummary(SensAIActivitySummary sensAIActivitySummary) {
        this.f7435a.sensAIDataDao.updateActivitySummary(sensAIActivitySummary);
    }

    public void updateAggregateSummaryData(boolean z, String str, String str2) {
        this.f7435a.sensAIDataDao.updateAggregateItem(z, str, str2);
    }

    public void updateFeedbackSummaryData(boolean z, String str, String str2) {
        this.f7435a.sensAIDataDao.updateFeedbackSavedToServerItem(z, str, str2);
    }

    public void updateSummaryData(boolean z, String str, String str2, String str3) {
        this.f7435a.sensAIDataDao.updateSavedToServerItem(z, str, str2, str3);
    }
}
