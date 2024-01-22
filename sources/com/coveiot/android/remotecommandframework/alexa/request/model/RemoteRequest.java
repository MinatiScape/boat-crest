package com.coveiot.android.remotecommandframework.alexa.request.model;

import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public class RemoteRequest {
    @SerializedName("cmds")
    @Nullable
    private List<Command> items;
    @SerializedName("msg")
    @Nullable
    private String msg;
    private transient int priority = Integer.parseInt(Constants.DEFAULT_REQUEST_PRIORITY.getValue());
    @SerializedName(CTProductConfigConstants.KEY_LAST_FETCHED_TIMESTAMP)
    @Nullable
    private Long ts;

    @Nullable
    public final List<Command> getItems() {
        return this.items;
    }

    @Nullable
    public final String getMsg() {
        return this.msg;
    }

    public final int getPriority() {
        return this.priority;
    }

    @Nullable
    public final Long getTs() {
        return this.ts;
    }

    public final void setItems(@Nullable List<Command> list) {
        this.items = list;
    }

    public final void setMsg(@Nullable String str) {
        this.msg = str;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }

    public final void setTs(@Nullable Long l) {
        this.ts = l;
    }
}
