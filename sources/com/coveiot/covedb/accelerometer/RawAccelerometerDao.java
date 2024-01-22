package com.coveiot.covedb.accelerometer;

import androidx.room.Dao;
import androidx.room.Insert;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import java.util.List;
@Dao
/* loaded from: classes8.dex */
public interface RawAccelerometerDao {
    @Insert(onConflict = 1)
    void insert(EntityRawAccelerometerData entityRawAccelerometerData);

    @Insert(onConflict = 1)
    void insertAll(List<EntityRawAccelerometerData> list);
}
