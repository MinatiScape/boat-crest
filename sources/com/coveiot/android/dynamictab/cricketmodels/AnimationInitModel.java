package com.coveiot.android.dynamictab.cricketmodels;

import java.util.List;
/* loaded from: classes4.dex */
public class AnimationInitModel {

    /* renamed from: a  reason: collision with root package name */
    public String f4323a;
    public int b;
    public String c;
    public String d;
    public DataBean e;

    /* loaded from: classes4.dex */
    public static class DataBean {

        /* renamed from: a  reason: collision with root package name */
        public String f4324a;
        public DefinitionBean b;

        /* loaded from: classes4.dex */
        public static class DefinitionBean {

            /* renamed from: a  reason: collision with root package name */
            public FlyRateBean f4325a;
            public String b;
            public boolean c;
            public List<ElementsBean> d;

            /* loaded from: classes4.dex */
            public static class ElementsBean {

                /* renamed from: a  reason: collision with root package name */
                public String f4326a;
                public String b;
                public String c;
                public boolean d;

                public String getImageUrl() {
                    return this.b;
                }

                public String getStickerId() {
                    return this.f4326a;
                }

                public String getVisibility() {
                    return this.c;
                }

                public boolean isFlyRandom() {
                    return this.d;
                }

                public void setFlyRandom(boolean z) {
                    this.d = z;
                }

                public void setImageUrl(String str) {
                    this.b = str;
                }

                public void setStickerId(String str) {
                    this.f4326a = str;
                }

                public void setVisibility(String str) {
                    this.c = str;
                }
            }

            /* loaded from: classes4.dex */
            public static class FlyRateBean {

                /* renamed from: a  reason: collision with root package name */
                public int f4327a;
                public int b;
                public int c;
                public int d;

                public int getTimeMax() {
                    return this.d;
                }

                public int getTimeMin() {
                    return this.c;
                }

                public int getVolMax() {
                    return this.b;
                }

                public int getVolMin() {
                    return this.f4327a;
                }

                public void setTimeMax(int i) {
                    this.d = i;
                }

                public void setTimeMin(int i) {
                    this.c = i;
                }

                public void setVolMax(int i) {
                    this.b = i;
                }

                public void setVolMin(int i) {
                    this.f4327a = i;
                }
            }

            public String getDefaultVisibility() {
                return this.b;
            }

            public List<ElementsBean> getElements() {
                return this.d;
            }

            public FlyRateBean getFlyRate() {
                return this.f4325a;
            }

            public boolean isEnableFlyRandom() {
                return this.c;
            }

            public void setDefaultVisibility(String str) {
                this.b = str;
            }

            public void setElements(List<ElementsBean> list) {
                this.d = list;
            }

            public void setEnableFlyRandom(boolean z) {
                this.c = z;
            }

            public void setFlyRate(FlyRateBean flyRateBean) {
                this.f4325a = flyRateBean;
            }
        }

        public String getAction() {
            return this.f4324a;
        }

        public DefinitionBean getDefinition() {
            return this.b;
        }

        public void setAction(String str) {
            this.f4324a = str;
        }

        public void setDefinition(DefinitionBean definitionBean) {
            this.b = definitionBean;
        }
    }

    public DataBean getData() {
        return this.e;
    }

    public String getResId() {
        return this.c;
    }

    public String getResType() {
        return this.f4323a;
    }

    public int getResVer() {
        return this.b;
    }

    public String getStatus() {
        return this.d;
    }

    public void setData(DataBean dataBean) {
        this.e = dataBean;
    }

    public void setResId(String str) {
        this.c = str;
    }

    public void setResType(String str) {
        this.f4323a = str;
    }

    public void setResVer(int i) {
        this.b = i;
    }

    public void setStatus(String str) {
        this.d = str;
    }
}
