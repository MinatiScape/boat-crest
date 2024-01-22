package com.coveiot.repository.sensai;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.sensai.SensAIDataDao;
/* loaded from: classes9.dex */
public class SensAIRepository {

    /* renamed from: a  reason: collision with root package name */
    public static SensAIRepository f7433a;
    public SensAIDataDao sensAIDataDao;

    public SensAIRepository(Context context) {
        this.sensAIDataDao = CoveAppDatabase.getAppDatabase(context).stanceBeamDataDao();
    }

    public static SensAIRepository getInstance(Context context) {
        if (f7433a == null) {
            f7433a = new SensAIRepository(context);
        }
        return f7433a;
    }
}
