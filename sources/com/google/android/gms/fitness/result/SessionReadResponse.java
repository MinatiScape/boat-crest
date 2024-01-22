package com.google.android.gms.fitness.result;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.List;
/* loaded from: classes6.dex */
public class SessionReadResponse extends Response<SessionReadResult> {
    @NonNull
    public List<DataSet> getDataSet(@NonNull Session session, @NonNull DataType dataType) {
        return getResult().getDataSet(session, dataType);
    }

    @NonNull
    public List<Session> getSessions() {
        return getResult().getSessions();
    }

    @NonNull
    public Status getStatus() {
        return getResult().getStatus();
    }

    @NonNull
    public List<DataSet> getDataSet(@NonNull Session session) {
        return getResult().getDataSet(session);
    }
}
