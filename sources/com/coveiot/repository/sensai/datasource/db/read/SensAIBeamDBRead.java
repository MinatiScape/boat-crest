package com.coveiot.repository.sensai.datasource.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.repository.sensai.SensAIRepository;
import java.util.List;
/* loaded from: classes9.dex */
public class SensAIBeamDBRead {
    public static SensAIBeamDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public final SensAIRepository f7434a;

    public SensAIBeamDBRead(Context context) {
        this.f7434a = SensAIRepository.getInstance(context);
    }

    public static SensAIBeamDBRead getInstance(Context context) {
        if (b == null) {
            b = new SensAIBeamDBRead(context);
        }
        return b;
    }

    public List<SensAIActivitySummary> getActivityBattingSummaryList(String str) {
        return this.f7434a.sensAIDataDao.getActivityBattingSummaryList(str);
    }

    public List<SensAIActivitySummary> getActivityBowlingSummaryList(String str) {
        return this.f7434a.sensAIDataDao.getActivityBowlingSummaryList(str);
    }

    public SensAIActivitySummary getActivitySummary(String str, String str2) {
        return this.f7434a.sensAIDataDao.getActivitySummary(str, str2);
    }

    public SensAIActivitySummaryDetails getActivitySummaryDetails(String str, String str2) {
        return this.f7434a.sensAIDataDao.getActivitySummaryDetails(str, str2);
    }

    public LiveData<List<SensAIActivitySummary>> getActivitySummaryLiveData(String str, List<String> list, int i) {
        if (list != null && !list.isEmpty()) {
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            int i7 = -1;
            int i8 = -1;
            for (int i9 = 0; i9 < list.size(); i9++) {
                if (list.contains("Batting")) {
                    i2 = 1;
                }
                if (list.contains("Bowling")) {
                    i3 = 2;
                }
                if (!list.contains("Batting") && !list.contains("Bowling")) {
                    i2 = 1;
                    i3 = 2;
                }
                if (list.contains("Right Hand")) {
                    i4 = 0;
                }
                if (list.contains("Left Hand")) {
                    i5 = 1;
                }
                if (!list.contains("Right Hand") && !list.contains("Left Hand")) {
                    i4 = 0;
                    i5 = 1;
                }
                if (list.contains("Fast")) {
                    i6 = 0;
                }
                if (list.contains("Medium Pace")) {
                    i7 = 1;
                }
                if (list.contains("Spin")) {
                    i8 = 2;
                }
                if (!list.contains("Fast") && !list.contains("Medium Pace") && !list.contains("Spin")) {
                    i6 = 0;
                    i7 = 1;
                    i8 = 2;
                }
            }
            return this.f7434a.sensAIDataDao.getFilteredByActivityAndHandAndBowlingType(str, i2, i3, i4, i5, i6, i7, i8, i);
        }
        return this.f7434a.sensAIDataDao.getActivitySummaryLiveData(str, i);
    }

    public List<SensAIActivitySummary> getAggregateSummaryList(String str) {
        return this.f7434a.sensAIDataDao.getAggregateSummaryList(str);
    }

    public int isSessionIdExists(String str, String str2) {
        return this.f7434a.sensAIDataDao.isSessionIdExists(str, str2);
    }
}
