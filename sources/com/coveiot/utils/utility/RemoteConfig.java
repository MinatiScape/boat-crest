package com.coveiot.utils.utility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class RemoteConfig {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Data f7627a;

    /* loaded from: classes9.dex */
    public class Data {
        @SerializedName("allow_state")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f7628a;
        @SerializedName("allowed_ids")
        @Expose
        private ArrayList<String> b = null;

        public Data(RemoteConfig remoteConfig) {
        }

        public String getAllowState() {
            return this.f7628a;
        }

        public ArrayList<String> getAllowedIds() {
            return this.b;
        }

        public void setAllowState(String str) {
            this.f7628a = str;
        }

        public void setAllowedIds(ArrayList<String> arrayList) {
            this.b = arrayList;
        }
    }

    public Data getData() {
        return this.f7627a;
    }

    public void setData(Data data) {
        this.f7627a = data;
    }
}
