package com.coveiot.repository.ambientsound.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.repository.ambientsound.datasources.db.AmbientSoundRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class AmbientSoundDBWrite {
    public static AmbientSoundDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public AmbientSoundRepo f7312a;

    public AmbientSoundDBWrite(Context context) {
        this.f7312a = AmbientSoundRepo.getInstance(context);
    }

    public static AmbientSoundDBWrite getInstance(Context context) {
        if (b == null) {
            b = new AmbientSoundDBWrite(context);
        }
        return b;
    }

    public long insertAmbientSound(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
        return this.f7312a.ambientSoundDao.insert(entityHourlyAmbientSoundData);
    }

    public void insertDailyAmbientSound(EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
        this.f7312a.ambientSoundDao.insertDailyAmbientSound(entityDailyAmbientSoundData);
    }

    public void insertDailyAmbientSoundDataList(List<EntityDailyAmbientSoundData> list) {
        this.f7312a.ambientSoundDao.insertAllDailyAmbientSound(list);
    }

    public void insertHourlyAmbientSoundDataList(List<EntityHourlyAmbientSoundData> list) {
        this.f7312a.ambientSoundDao.insertAll(list);
    }

    public int updateHourlyAmbientSoundData(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
        return this.f7312a.ambientSoundDao.updateHourlyAmbientSoundData(entityHourlyAmbientSoundData);
    }
}
