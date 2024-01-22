package com.mappls.android.lms;

import com.mappls.android.util.MPLog;
import java.security.SecureRandom;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SessionMetadata {
    private long mEventsCounter;
    private long mPeopleCounter;
    private final SecureRandom mRandom;
    private String mSessionID;
    private long mSessionStartEpoch;

    public SessionMetadata() {
        initSession();
        this.mRandom = new SecureRandom();
    }

    private JSONObject getNewMetadata(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$mp_event_id", Long.toHexString(this.mRandom.nextLong()));
            jSONObject.put("$mp_session_id", this.mSessionID);
            jSONObject.put("$mp_session_seq_id", z ? this.mEventsCounter : this.mPeopleCounter);
            jSONObject.put("$mp_session_start_sec", this.mSessionStartEpoch);
            if (z) {
                this.mEventsCounter++;
            } else {
                this.mPeopleCounter++;
            }
        } catch (JSONException e) {
            MPLog.e(ConfigurationChecker.LOGTAG, "Cannot create session metadata JSON object", e);
        }
        return jSONObject;
    }

    public JSONObject getMetadataForEvent() {
        return getNewMetadata(true);
    }

    public JSONObject getMetadataForPeople() {
        return getNewMetadata(false);
    }

    public void initSession() {
        this.mEventsCounter = 0L;
        this.mPeopleCounter = 0L;
        this.mSessionID = Long.toHexString(new SecureRandom().nextLong());
        this.mSessionStartEpoch = System.currentTimeMillis() / 1000;
    }
}
