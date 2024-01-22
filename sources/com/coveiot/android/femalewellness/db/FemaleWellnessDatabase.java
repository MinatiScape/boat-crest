package com.coveiot.android.femalewellness.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.coveiot.android.femalewellness.db.dao.FemaleWellnessSymptomsDao;
import com.coveiot.android.femalewellness.db.entities.EntityFemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.utils.Converter;
@TypeConverters({Converter.class})
@Database(entities = {EntityFemaleWellnessSymptoms.class}, exportSchema = false, version = 1)
/* loaded from: classes4.dex */
public abstract class FemaleWellnessDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static FemaleWellnessDatabase f4390a = null;
    public static final String covedatabase = "recordSymptomsDb";

    public static FemaleWellnessDatabase getAppDatabase(Context context) {
        if (f4390a == null) {
            f4390a = (FemaleWellnessDatabase) Room.databaseBuilder(context.getApplicationContext(), FemaleWellnessDatabase.class, covedatabase).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return f4390a;
    }

    public abstract FemaleWellnessSymptomsDao symptomsDao();
}
