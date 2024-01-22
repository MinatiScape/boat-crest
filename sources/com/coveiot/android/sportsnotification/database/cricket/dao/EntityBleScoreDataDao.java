package com.coveiot.android.sportsnotification.database.cricket.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityBleScoreCardData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes7.dex */
public interface EntityBleScoreDataDao {
    @Query("SELECT * from EntityBleScoreCardData WHERE matchId=:match_id")
    @Nullable
    EntityBleScoreCardData getMatchScoreCardData(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull EntityBleScoreCardData entityBleScoreCardData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityBleScoreCardData> list);
}
