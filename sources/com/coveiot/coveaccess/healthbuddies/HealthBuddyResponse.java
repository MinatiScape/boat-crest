package com.coveiot.coveaccess.healthbuddies;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class HealthBuddyResponse extends CoveApiResponseBaseModel {
    private List<HealthBuddy> healthBuddies;
    private String message;
    private String status;

    public HealthBuddyResponse(int i, String str, String str2, List<HealthBuddy> list) {
        super(i);
        this.healthBuddies = null;
        this.status = null;
        this.message = null;
        this.healthBuddies = list;
        this.status = str;
        this.message = str2;
    }

    public List<HealthBuddy> getHealthBuddyRequests() {
        return this.healthBuddies;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }
}
