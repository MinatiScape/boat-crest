package com.coveiot.repository.profile;

import android.content.Context;
import com.coveiot.android.bleabstract.models.UserProfileDetails;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.profile.write.ProfileDBWrite;
/* loaded from: classes9.dex */
public class ProfileRepository {

    /* renamed from: a  reason: collision with root package name */
    public static ProfileRepository f7423a = new ProfileRepository();

    public static ProfileRepository getInstance() {
        return f7423a;
    }

    public EntityProfile getLatestProfileData(Context context) {
        return ProfileDBRead.getInstance(context).getLatestProfileData();
    }

    public EntityProfile getLatestProfileDataTillDate(Context context, Long l) {
        return ProfileDBRead.getInstance(context).getLatestProfileDataTillDate(l);
    }

    public void insertProfileData(Context context, EntityProfile entityProfile) {
        ProfileDBWrite.getInstance(context).insertProfileData(entityProfile);
    }

    public void saveProfileDetailsToAbstractPreference(Context context, EntityProfile entityProfile) {
        PreferenceManagerAbstract.getInstance(context).saveProfileData(new UserProfileDetails(entityProfile.height, entityProfile.weight, entityProfile.age, entityProfile.gender));
    }

    public void updateAge(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setAge(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
        saveProfileDetailsToAbstractPreference(context, latestProfileData);
    }

    public void updateBandUpdateStatus(Context context, boolean z) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            return;
        }
        latestProfileData.updatedToBand = z;
        ProfileDBWrite.getInstance(context).insertOrUpdateProfileData(latestProfileData);
    }

    public void updateGender(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setGender(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
        saveProfileDetailsToAbstractPreference(context, latestProfileData);
    }

    public void updateHeightWeight(Context context, int i, double d) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setHeight(i);
        latestProfileData.setWeight(d);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
        saveProfileDetailsToAbstractPreference(context, latestProfileData);
    }

    public void updatePhysicalActivityScore(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setAge(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
    }

    public void updateRestingHr(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setAge(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
    }

    public void updateSleepTarget(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setSleepTarget(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
    }

    public void updateStepsTarget(Context context, int i) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setStepsTarget(i);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
    }

    public void updateStrideLength(Context context, int i, int i2) {
        EntityProfile latestProfileData = ProfileDBRead.getInstance(context).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        latestProfileData.setTimestamp(System.currentTimeMillis());
        latestProfileData.setWalkStrideLength(i);
        latestProfileData.setRunStrideLength(i2);
        ProfileDBWrite.getInstance(context).insertProfileData(latestProfileData);
    }
}
