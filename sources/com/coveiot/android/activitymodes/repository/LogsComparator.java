package com.coveiot.android.activitymodes.repository;

import com.coveiot.coveaccess.activitysession.TraqActivityLogs;
import java.util.Comparator;
/* loaded from: classes2.dex */
public class LogsComparator implements Comparator<TraqActivityLogs> {
    @Override // java.util.Comparator
    public int compare(TraqActivityLogs traqActivityLogs, TraqActivityLogs traqActivityLogs2) {
        if (traqActivityLogs != null && traqActivityLogs2 != null) {
            try {
                return traqActivityLogs.getSegmentId().compareTo(traqActivityLogs2.getSegmentId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
