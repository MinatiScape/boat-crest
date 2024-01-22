package com.coveiot.coveaccess.appreferal;

import java.util.List;
/* loaded from: classes8.dex */
public final class AppReferralModelRes {

    /* renamed from: a  reason: collision with root package name */
    public DataBean f6422a;

    /* loaded from: classes8.dex */
    public static class DataBean {

        /* renamed from: a  reason: collision with root package name */
        public List<ReferralTemplatesBean> f6423a;

        /* loaded from: classes8.dex */
        public static class ReferralTemplatesBean {

            /* renamed from: a  reason: collision with root package name */
            public String f6424a;
            public String b;
            public String c;
            public String d;

            public String getInviteImage() {
                return this.d;
            }

            public String getInviteLink() {
                return this.c;
            }

            public String getInviteText() {
                return this.b;
            }

            public String getInviteType() {
                return this.f6424a;
            }

            public void setInviteImage(String str) {
                this.d = str;
            }

            public void setInviteLink(String str) {
                this.c = str;
            }

            public void setInviteText(String str) {
                this.b = str;
            }

            public void setInviteType(String str) {
                this.f6424a = str;
            }
        }

        public List<ReferralTemplatesBean> getReferralTemplates() {
            return this.f6423a;
        }

        public void setReferralTemplates(List<ReferralTemplatesBean> list) {
            this.f6423a = list;
        }
    }

    public DataBean getData() {
        return this.f6422a;
    }

    public void setData(DataBean dataBean) {
        this.f6422a = dataBean;
    }
}
