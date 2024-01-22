package com.coveiot.repository.profile;

import android.content.Context;
import com.coveiot.covedb.CoveAppDatabase;
import com.coveiot.covedb.profile.dao.DaoProfile;
/* loaded from: classes9.dex */
public class ProfileDBRepo {

    /* renamed from: a  reason: collision with root package name */
    public static ProfileDBRepo f7422a;
    public final DaoProfile daoProfile;

    public ProfileDBRepo(Context context) {
        this.daoProfile = CoveAppDatabase.getAppDatabase(context).daoProfile();
    }

    public static ProfileDBRepo getInstance(Context context) {
        if (f7422a == null) {
            f7422a = new ProfileDBRepo(context);
        }
        return f7422a;
    }
}
