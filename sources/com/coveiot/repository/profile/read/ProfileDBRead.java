package com.coveiot.repository.profile.read;

import android.content.Context;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.repository.profile.ProfileDBRepo;
/* loaded from: classes9.dex */
public class ProfileDBRead {
    public static ProfileDBRead b;

    /* renamed from: a  reason: collision with root package name */
    public ProfileDBRepo f7424a;

    public ProfileDBRead(Context context) {
        this.f7424a = ProfileDBRepo.getInstance(context);
    }

    public static ProfileDBRead getInstance(Context context) {
        if (b == null) {
            b = new ProfileDBRead(context);
        }
        return b;
    }

    public EntityProfile getLatestProfileData() {
        return this.f7424a.daoProfile.getTheLatestProfileData();
    }

    public EntityProfile getLatestProfileDataTillDate(Long l) {
        return this.f7424a.daoProfile.getTheLatestProfileDataTillDate(l);
    }
}
