package com.coveiot.android.sportsnotification.database.cricket.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.sportsnotification.database.cricket.entity.EntityAPIScoreCardData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes7.dex */
public interface EntityAPIScoreCardDataDao {
    @Query("SELECT * from EntityAPIScoreCardData WHERE match_id=:match_id")
    @Nullable
    EntityAPIScoreCardData getMatchScoreCardData(@NotNull String str);

    @Insert(onConflict = 1)
    void insert(@NotNull EntityAPIScoreCardData entityAPIScoreCardData);

    @Insert(onConflict = 1)
    void insertAll(@NotNull List<EntityAPIScoreCardData> list);
}
