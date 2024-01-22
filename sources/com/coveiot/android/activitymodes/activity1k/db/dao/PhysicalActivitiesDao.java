package com.coveiot.android.activitymodes.activity1k.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityActivityCategory;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Dao
/* loaded from: classes2.dex */
public interface PhysicalActivitiesDao {
    @Query("DELETE FROM entity_physical_activities")
    void clearActivityTable();

    @Query("DELETE FROM entity_activity_category")
    void clearCategoryTable();

    @Query("SELECT * FROM entity_activity_category")
    @NotNull
    LiveData<List<ActivityCategoriesModel>> getActivitiesCategories();

    @Query("SELECT * FROM entity_activity_category where categoryId in (:categoryIds)")
    @NotNull
    LiveData<List<EntityActivityCategory>> getActivitiesCategories(@NotNull List<Integer> list);

    @Query("SELECT * FROM entity_activity_category where categoryId=:categoryId")
    @NotNull
    ActivityCategoriesModel getActivitiesCategory(int i);

    @Query("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId ")
    @NotNull
    LiveData<List<CategoryAndActivityModel>> getActivityAndCategories();

    @Query("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId ")
    @NotNull
    List<CategoryAndActivityModel> getActivityAndCategoriesWithoutLiveData();

    @Query("SELECT *  FROM entity_activity_category as c INNER JOIN entity_physical_activities as a ON c.categoryId = a.categoryId where a.categoryId=:categoryId and a.activityId=:activityId")
    @NotNull
    CategoryAndActivityModel getActivityAndCategoryList(int i, int i2);

    @Query("SELECT * FROM entity_physical_activities")
    @NotNull
    LiveData<List<ActivitiesListModel>> getAllPhysicalActivityList();

    @Query("SELECT * FROM entity_physical_activities  where categoryId=:categoryId")
    @NotNull
    LiveData<List<ActivitiesListModel>> getAllPhysicalActivityList(int i);

    @Query("SELECT * FROM entity_physical_activities WHERE inbuilt=1")
    @Nullable
    List<EntityPhysicalActivities> getDefaultActivities();

    @Query("SELECT * FROM entity_physical_activities WHERE fwActId=:fwactId")
    @NotNull
    EntityPhysicalActivities getPhysicalActivity(int i);

    @Query("SELECT * FROM entity_physical_activities WHERE categoryId=:categoryId AND activityId=:activityId")
    @NotNull
    EntityPhysicalActivities getPhysicalActivity(int i, int i2);

    @Query("SELECT * FROM entity_physical_activities WHERE categoryId=:categoryId AND fwActId=:fwActId")
    @NotNull
    EntityPhysicalActivities getPhysicalActivityByFWActivityId(int i, int i2);

    @Query("SELECT title FROM entity_activity_category WHERE categoryId=:categoryId")
    @NotNull
    String getPhysicalActivityCategoryName(int i);

    @Query("SELECT * FROM entity_physical_activities where categoryId=:categoryId")
    @NotNull
    LiveData<List<EntityPhysicalActivities>> getPhysicalActivityList(int i);

    @Query("SELECT * FROM entity_physical_activities WHERE activityCode=:activityCode")
    @NotNull
    EntityPhysicalActivities getPhysicalActivityN(@NotNull String str);

    @Query("SELECT titleInMetric FROM entity_physical_activities WHERE categoryId=:categoryId AND activityId=:activityId")
    @NotNull
    String getPhysicalActivityName(int i, int i2);

    @Insert(onConflict = 1)
    @NotNull
    long[] insertActivityCategories(@NotNull List<EntityActivityCategory> list);

    @Insert(onConflict = 1)
    @NotNull
    long[] insertPhysicalActivities(@NotNull List<EntityPhysicalActivities> list);
}
