package com.coveiot.android.jstyle1860Sdk.api;

import com.jstyle.blesdk1860.Util.BleSDK;
import com.jstyle.blesdk1860.model.MyPersonalInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/coveiot/android/jstyle1860Sdk/api/JstylePersonalInfoReq;", "Lcom/coveiot/android/jstyle1860Sdk/api/JstyleBaseReq;", "", "getCommandBytes", "", "isMultiPacket", "isPriority", "Lcom/jstyle/blesdk1860/model/MyPersonalInfo;", "personalInfo", "Lcom/jstyle/blesdk1860/model/MyPersonalInfo;", "getPersonalInfo", "()Lcom/jstyle/blesdk1860/model/MyPersonalInfo;", "setPersonalInfo", "(Lcom/jstyle/blesdk1860/model/MyPersonalInfo;)V", "<init>", "()V", "jstyle1860Sdk_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class JstylePersonalInfoReq extends JstyleBaseReq {
    @NotNull
    public MyPersonalInfo g = new MyPersonalInfo();

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    @Nullable
    public byte[] getCommandBytes() {
        return BleSDK.SetPersonalInfo(this.g);
    }

    @NotNull
    public final MyPersonalInfo getPersonalInfo() {
        return this.g;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.jstyle1860Sdk.api.JstyleBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setPersonalInfo(@NotNull MyPersonalInfo myPersonalInfo) {
        Intrinsics.checkNotNullParameter(myPersonalInfo, "<set-?>");
        this.g = myPersonalInfo;
    }
}
