package com.coveiot.sdk.ble.utils;

import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.SedentaryReminderInfo;
import java.util.List;
/* loaded from: classes9.dex */
public class CommandClass {

    /* renamed from: a  reason: collision with root package name */
    public final CommandNames f7586a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public String h;
    public String i;
    public int[] j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int[] p;
    public int q;
    public Object r;
    public BaseRequest s;
    public ResponseListener t;
    public boolean u;
    public boolean v;

    public CommandClass(CommandNames commandNames) {
        this.f7586a = commandNames;
    }

    public int getAlertEvent() {
        return this.o;
    }

    public int getAlertSwitch() {
        return this.g;
    }

    public int[] getBandSettings() {
        return this.j;
    }

    public BaseRequest getBaseRequest() {
        return this.s;
    }

    public CommandNames getCommandName() {
        return this.f7586a;
    }

    public Object getData() {
        return this.r;
    }

    public int getDay() {
        return this.b;
    }

    public int getEndHour() {
        return this.d;
    }

    public int getEndMin() {
        return this.m;
    }

    public int getId() {
        return this.k;
    }

    public String getMessage() {
        return this.h;
    }

    public int getMode() {
        return this.q;
    }

    public String getName() {
        return this.i;
    }

    public int getReminder() {
        return this.n;
    }

    public int[] getRepeat() {
        return this.p;
    }

    public ResponseListener getResponseListener() {
        return this.t;
    }

    public int getStartHour() {
        return this.c;
    }

    public int getStartMin() {
        return this.l;
    }

    public int getTarget() {
        return this.e;
    }

    public int getUvSwitch() {
        return this.f;
    }

    public boolean isPriority() {
        return this.u;
    }

    public boolean isRightHand() {
        return this.v;
    }

    public void setAlarmSettings(List<AlarmInfo> list) {
        this.r = list;
    }

    public void setAlertEvent(int i) {
        this.o = i;
    }

    public void setAlertSwitch(int i) {
        this.g = i;
    }

    public void setBandSettings(int[] iArr) {
        this.j = iArr;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.s = baseRequest;
    }

    public void setCallAlert(int i, String str) {
        this.g = i;
        this.i = str;
    }

    public void setData(Object obj) {
        this.r = obj;
    }

    public void setDay(int i) {
        this.b = i;
    }

    public void setEndHour(int i) {
        this.d = i;
    }

    public void setEndMin(int i) {
        this.m = i;
    }

    public void setId(int i) {
        this.k = i;
    }

    public void setMessage(String str) {
        this.h = str;
    }

    public void setMode(int i) {
        this.q = i;
    }

    public void setName(String str) {
        this.i = str;
    }

    public void setPriority(boolean z) {
        this.u = z;
    }

    public void setReminder(int i) {
        this.n = i;
    }

    public void setRepeat(int[] iArr) {
        this.p = iArr;
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.t = responseListener;
    }

    public void setRightHand(boolean z) {
        this.v = z;
    }

    public void setSedentaryReminder(SedentaryReminderInfo sedentaryReminderInfo) {
        this.r = sedentaryReminderInfo;
    }

    public void setSmsAlert(int i, String str) {
        this.k = i;
        this.i = str;
    }

    public void setStartHour(int i) {
        this.c = i;
    }

    public void setStartMin(int i) {
        this.l = i;
    }

    public void setTarget(int i) {
        this.e = i;
    }

    public void setUvSwitch(int i) {
        this.f = i;
    }
}
