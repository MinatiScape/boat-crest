package com.coveiot.coveaccess.respiratoryrate.model;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class PPGRecord {
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6695a;
    @SerializedName("durationSeconds")
    @Expose
    private int b;
    @SerializedName("ppgType")
    @Expose
    private int c;
    @SerializedName(DeviceKey.KPPGData)
    @Expose
    private List<Integer> d = new ArrayList();

    public int getDurationSeconds() {
        return this.b;
    }

    public List<Integer> getPpgData() {
        return this.d;
    }

    public int getPpgType() {
        return this.c;
    }

    public String getTime() {
        return this.f6695a;
    }

    public void setDurationSeconds(int i) {
        this.b = i;
    }

    public void setPpgData(ArrayList<Integer> arrayList) {
        this.d = arrayList;
    }

    public void setPpgType(int i) {
        this.c = i;
    }

    public void setTime(String str) {
        this.f6695a = str;
    }
}
