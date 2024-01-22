package com.coveiot.android.dynamictab.sports.model;

import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
/* loaded from: classes4.dex */
public class DashboardModel {

    /* renamed from: a  reason: collision with root package name */
    public String f4339a;
    public String b;
    public Integer c;
    public String d;
    public int e;
    public String f;
    public String g;
    public boolean h = true;
    public SRemoteConfigResponse.DataBean.UiBean.ElementsBean i;
    public SRemoteConfigResponse j;

    public boolean equals(Object obj) {
        if (obj instanceof DashboardModel) {
            return ((DashboardModel) obj).getTitle().equals(this.f4339a);
        }
        return super.equals(obj);
    }

    public String getElementName() {
        return this.f;
    }

    public SRemoteConfigResponse.DataBean.UiBean.ElementsBean getElementsBean() {
        return this.i;
    }

    public Integer getIcon() {
        return this.c;
    }

    public int getIndex() {
        return this.e;
    }

    public String getSportsIcon() {
        return this.d;
    }

    public SRemoteConfigResponse getSportsResponseModel() {
        return this.j;
    }

    public String getSubTitle() {
        return this.b;
    }

    public String getTitle() {
        return this.f4339a;
    }

    public String getWindowId() {
        return this.g;
    }

    public boolean isCoveJsInterface() {
        return this.h;
    }

    public void setCoveJsInterface(boolean z) {
        this.h = z;
    }

    public void setElementName(String str) {
        this.f = str;
    }

    public void setElementsBean(SRemoteConfigResponse.DataBean.UiBean.ElementsBean elementsBean) {
        this.i = elementsBean;
    }

    public void setIcon(Integer num) {
        this.c = num;
    }

    public void setIndex(int i) {
        this.e = i;
    }

    public void setSportsIcon(String str) {
        this.d = str;
    }

    public void setSportsResponseModel(SRemoteConfigResponse sRemoteConfigResponse) {
        this.j = sRemoteConfigResponse;
    }

    public void setSubTitle(String str) {
        this.b = str;
    }

    public void setTitle(String str) {
        this.f4339a = str;
    }

    public void setWindowId(String str) {
        this.g = str;
    }
}
