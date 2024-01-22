package com.coveiot.repository.ambientsound.datasources.db;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.ambientsound.AmbientSoundDao;
/* loaded from: classes9.dex */
public class AmbientSoundRepo {

    /* renamed from: a  reason: collision with root package name */
    public static AmbientSoundRepo f7310a;
    public AmbientSoundDao ambientSoundDao;

    public AmbientSoundRepo(Context context) {
        this.ambientSoundDao = CoveAppDatabase.getAppDatabase(context).ambientSoundDao();
    }

    public static AmbientSoundRepo getInstance(Context context) {
        if (f7310a == null) {
            f7310a = new AmbientSoundRepo(context);
        }
        return f7310a;
    }
}
