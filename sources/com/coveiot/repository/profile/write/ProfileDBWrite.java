package com.coveiot.repository.profile.write;

import android.content.Context;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.repository.profile.ProfileDBRepo;
/* loaded from: classes9.dex */
public class ProfileDBWrite {
    public static ProfileDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public ProfileDBRepo f7425a;

    public ProfileDBWrite(Context context) {
        this.f7425a = ProfileDBRepo.getInstance(context);
    }

    public static ProfileDBWrite getInstance(Context context) {
        if (b == null) {
            b = new ProfileDBWrite(context);
        }
        return b;
    }

    public void insertOrUpdateProfileData(EntityProfile entityProfile) {
        this.f7425a.daoProfile.insertOrUpdate(entityProfile);
    }

    public void insertProfileData(EntityProfile entityProfile) {
        this.f7425a.daoProfile.insert(entityProfile);
    }
}
