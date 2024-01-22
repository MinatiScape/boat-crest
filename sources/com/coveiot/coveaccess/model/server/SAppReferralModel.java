package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class SAppReferralModel {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private List<ReferralTemplatesBean> referralTemplates;

        /* loaded from: classes8.dex */
        public static class ReferralTemplatesBean {
            private String inviteImage;
            private String inviteLink;
            private String inviteText;
            private String inviteType;

            public String getInviteImage() {
                return this.inviteImage;
            }

            public String getInviteLink() {
                return this.inviteLink;
            }

            public String getInviteText() {
                return this.inviteText;
            }

            public String getInviteType() {
                return this.inviteType;
            }

            public void setInviteImage(String str) {
                this.inviteImage = str;
            }

            public void setInviteLink(String str) {
                this.inviteLink = str;
            }

            public void setInviteText(String str) {
                this.inviteText = str;
            }

            public void setInviteType(String str) {
                this.inviteType = str;
            }
        }

        public List<ReferralTemplatesBean> getReferralTemplates() {
            return this.referralTemplates;
        }

        public void setReferralTemplates(List<ReferralTemplatesBean> list) {
            this.referralTemplates = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
