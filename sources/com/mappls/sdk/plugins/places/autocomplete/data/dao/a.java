package com.mappls.sdk.plugins.places.autocomplete.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
/* loaded from: classes10.dex */
public interface a {
    @Query("SELECT * FROM searchhistory WHERE place_name LIKE '%' || :query || '%' OR place_address LIKE '%' || :query || '%' OR placeId LIKE '%' || :query || '%' order by timestamp desc")
    LiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> a(String str);

    @Query("DELETE FROM searchhistory")
    void a();

    @Insert(onConflict = 1)
    void a(com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar);

    @Query("SELECT * FROM searchhistory order by timestamp desc")
    LiveData<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> getAll();
}
