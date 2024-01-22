package com.coveiot.covedb.profile.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.covedb.profile.entities.EntityProfile;
@Dao
/* loaded from: classes8.dex */
public interface DaoProfile {
    @Query("Select * from profile order by timestamp desc limit 1")
    EntityProfile getTheLatestProfileData();

    @Query("Select * from profile where timestamp<:timeStamp order by timestamp desc limit 1")
    EntityProfile getTheLatestProfileDataTillDate(Long l);

    @Insert(onConflict = 5)
    void insert(EntityProfile entityProfile);

    @Insert(onConflict = 1)
    void insertOrUpdate(EntityProfile entityProfile);
}
