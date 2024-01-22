package com.coveiot.covedb.ppg;

import androidx.room.Dao;
import androidx.room.Insert;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface RawPPGDao {
    @Insert(onConflict = 1)
    void insert(EntityRawPPGData entityRawPPGData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityRawPPGData> list);
}
