package com.coveiot.coveaccess.homescreen.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
/* loaded from: classes8.dex */
public class HomeScreenBannerDataRes extends CoveApiResponseBaseModel {
    public String htmlSring;

    public HomeScreenBannerDataRes(int i) {
        super(i);
    }

    public String getHtmlSring() {
        return this.htmlSring;
    }

    public void setHtmlSring(String str) {
        this.htmlSring = str;
    }
}
