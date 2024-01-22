package com.coveiot.android.dynamictab.sports.model;

import java.util.List;
/* loaded from: classes4.dex */
public class SportsResponseModel {

    /* renamed from: a  reason: collision with root package name */
    public String f4341a;
    public String b;
    public DataBean c;

    /* loaded from: classes4.dex */
    public static class DataBean {

        /* renamed from: a  reason: collision with root package name */
        public SessionBean f4342a;
        public List<UiBean> b;
        public PayBean c;

        /* loaded from: classes4.dex */
        public static class PayBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4343a;

            public boolean isEnableUpi() {
                return this.f4343a;
            }

            public void setEnableUpi(boolean z) {
                this.f4343a = z;
            }
        }

        /* loaded from: classes4.dex */
        public static class SessionBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f4344a;

            public boolean isRequireRefresh() {
                return this.f4344a;
            }

            public void setRequireRefresh(boolean z) {
                this.f4344a = z;
            }
        }

        /* loaded from: classes4.dex */
        public static class UiBean {

            /* renamed from: a  reason: collision with root package name */
            public String f4345a;
            public String b;
            public String c;
            public List<ElementsBean> d;

            /* loaded from: classes4.dex */
            public static class ElementsBean {

                /* renamed from: a  reason: collision with root package name */
                public int f4346a;
                public ContentBean b;
                public StyleBean c;
                public List<EventsBean> d;

                /* loaded from: classes4.dex */
                public static class ContentBean {

                    /* renamed from: a  reason: collision with root package name */
                    public TitleBean f4347a;
                    public SubtitleBean b;
                    public DescriptionBean c;
                    public ImageBean d;

                    /* loaded from: classes4.dex */
                    public static class DescriptionBean {

                        /* renamed from: a  reason: collision with root package name */
                        public String f4348a;
                        public StyleBean b;

                        /* loaded from: classes4.dex */
                        public static class StyleBean {

                            /* renamed from: a  reason: collision with root package name */
                            public String f4349a;

                            public String getColor() {
                                return this.f4349a;
                            }

                            public void setColor(String str) {
                                this.f4349a = str;
                            }
                        }

                        public StyleBean getStyle() {
                            return this.b;
                        }

                        public String getText() {
                            return this.f4348a;
                        }

                        public void setStyle(StyleBean styleBean) {
                            this.b = styleBean;
                        }

                        public void setText(String str) {
                            this.f4348a = str;
                        }
                    }

                    /* loaded from: classes4.dex */
                    public static class ImageBean {

                        /* renamed from: a  reason: collision with root package name */
                        public String f4350a;

                        public String getSrc() {
                            return this.f4350a;
                        }

                        public void setSrc(String str) {
                            this.f4350a = str;
                        }
                    }

                    /* loaded from: classes4.dex */
                    public static class SubtitleBean {

                        /* renamed from: a  reason: collision with root package name */
                        public String f4351a;
                        public StyleBean b;

                        /* loaded from: classes4.dex */
                        public static class StyleBean {

                            /* renamed from: a  reason: collision with root package name */
                            public String f4352a;

                            public String getColor() {
                                return this.f4352a;
                            }

                            public void setColor(String str) {
                                this.f4352a = str;
                            }
                        }

                        public StyleBean getStyle() {
                            return this.b;
                        }

                        public String getText() {
                            return this.f4351a;
                        }

                        public void setStyle(StyleBean styleBean) {
                            this.b = styleBean;
                        }

                        public void setText(String str) {
                            this.f4351a = str;
                        }
                    }

                    /* loaded from: classes4.dex */
                    public static class TitleBean {

                        /* renamed from: a  reason: collision with root package name */
                        public String f4353a;
                        public StyleBean b;

                        /* loaded from: classes4.dex */
                        public static class StyleBean {

                            /* renamed from: a  reason: collision with root package name */
                            public String f4354a;

                            public String getColor() {
                                return this.f4354a;
                            }

                            public void setColor(String str) {
                                this.f4354a = str;
                            }
                        }

                        public StyleBean getStyle() {
                            return this.b;
                        }

                        public String getText() {
                            return this.f4353a;
                        }

                        public void setStyle(StyleBean styleBean) {
                            this.b = styleBean;
                        }

                        public void setText(String str) {
                            this.f4353a = str;
                        }
                    }

                    public DescriptionBean getDescription() {
                        return this.c;
                    }

                    public ImageBean getImage() {
                        return this.d;
                    }

                    public SubtitleBean getSubtitle() {
                        return this.b;
                    }

                    public TitleBean getTitle() {
                        return this.f4347a;
                    }

                    public void setDescription(DescriptionBean descriptionBean) {
                        this.c = descriptionBean;
                    }

                    public void setImage(ImageBean imageBean) {
                        this.d = imageBean;
                    }

                    public void setSubtitle(SubtitleBean subtitleBean) {
                        this.b = subtitleBean;
                    }

                    public void setTitle(TitleBean titleBean) {
                        this.f4347a = titleBean;
                    }
                }

                /* loaded from: classes4.dex */
                public static class EventsBean {

                    /* renamed from: a  reason: collision with root package name */
                    public String f4355a;
                    public String b;
                    public String c;
                    public String d;
                    public WindowBean e;

                    /* loaded from: classes4.dex */
                    public static class WindowBean {

                        /* renamed from: a  reason: collision with root package name */
                        public String f4356a;
                        public CoveJsInterfaceBean b;
                        public NavBarBean c;

                        /* loaded from: classes4.dex */
                        public static class CoveJsInterfaceBean {

                            /* renamed from: a  reason: collision with root package name */
                            public boolean f4357a;

                            public boolean isEnable() {
                                return this.f4357a;
                            }

                            public void setEnable(boolean z) {
                                this.f4357a = z;
                            }
                        }

                        /* loaded from: classes4.dex */
                        public static class NavBarBean {

                            /* renamed from: a  reason: collision with root package name */
                            public boolean f4358a;
                            public TitleBean b;
                            public CloseButtonBean c;

                            /* loaded from: classes4.dex */
                            public static class CloseButtonBean {

                                /* renamed from: a  reason: collision with root package name */
                                public boolean f4359a;

                                public boolean isEnable() {
                                    return this.f4359a;
                                }

                                public void setEnable(boolean z) {
                                    this.f4359a = z;
                                }
                            }

                            /* loaded from: classes4.dex */
                            public static class TitleBean {

                                /* renamed from: a  reason: collision with root package name */
                                public String f4360a;
                                public StyleBean b;

                                /* loaded from: classes4.dex */
                                public static class StyleBean {

                                    /* renamed from: a  reason: collision with root package name */
                                    public String f4361a;

                                    public String getColor() {
                                        return this.f4361a;
                                    }

                                    public void setColor(String str) {
                                        this.f4361a = str;
                                    }
                                }

                                public StyleBean getStyle() {
                                    return this.b;
                                }

                                public String getText() {
                                    return this.f4360a;
                                }

                                public void setStyle(StyleBean styleBean) {
                                    this.b = styleBean;
                                }

                                public void setText(String str) {
                                    this.f4360a = str;
                                }
                            }

                            public CloseButtonBean getCloseButton() {
                                return this.c;
                            }

                            public TitleBean getTitle() {
                                return this.b;
                            }

                            public boolean isDisplay() {
                                return this.f4358a;
                            }

                            public void setCloseButton(CloseButtonBean closeButtonBean) {
                                this.c = closeButtonBean;
                            }

                            public void setDisplay(boolean z) {
                                this.f4358a = z;
                            }

                            public void setTitle(TitleBean titleBean) {
                                this.b = titleBean;
                            }
                        }

                        public CoveJsInterfaceBean getCoveJsInterface() {
                            return this.b;
                        }

                        public NavBarBean getNavBar() {
                            return this.c;
                        }

                        public String getWindowId() {
                            return this.f4356a;
                        }

                        public void setCoveJsInterface(CoveJsInterfaceBean coveJsInterfaceBean) {
                            this.b = coveJsInterfaceBean;
                        }

                        public void setNavBar(NavBarBean navBarBean) {
                            this.c = navBarBean;
                        }

                        public void setWindowId(String str) {
                            this.f4356a = str;
                        }
                    }

                    public String getAction() {
                        return this.b;
                    }

                    public String getName() {
                        return this.f4355a;
                    }

                    public String getTarget() {
                        return this.d;
                    }

                    public String getUrl() {
                        return this.c;
                    }

                    public WindowBean getWindow() {
                        return this.e;
                    }

                    public void setAction(String str) {
                        this.b = str;
                    }

                    public void setName(String str) {
                        this.f4355a = str;
                    }

                    public void setTarget(String str) {
                        this.d = str;
                    }

                    public void setUrl(String str) {
                        this.c = str;
                    }

                    public void setWindow(WindowBean windowBean) {
                        this.e = windowBean;
                    }
                }

                /* loaded from: classes4.dex */
                public static class StyleBean {

                    /* renamed from: a  reason: collision with root package name */
                    public String f4362a;
                    public String b;

                    public String getBackgroundColor() {
                        return this.b;
                    }

                    public String getBackgroundImage() {
                        return this.f4362a;
                    }

                    public void setBackgroundColor(String str) {
                        this.b = str;
                    }

                    public void setBackgroundImage(String str) {
                        this.f4362a = str;
                    }
                }

                public ContentBean getContent() {
                    return this.b;
                }

                public List<EventsBean> getEvents() {
                    return this.d;
                }

                public int getIndex() {
                    return this.f4346a;
                }

                public StyleBean getStyle() {
                    return this.c;
                }

                public void setContent(ContentBean contentBean) {
                    this.b = contentBean;
                }

                public void setEvents(List<EventsBean> list) {
                    this.d = list;
                }

                public void setIndex(int i) {
                    this.f4346a = i;
                }

                public void setStyle(StyleBean styleBean) {
                    this.c = styleBean;
                }
            }

            public String getElementName() {
                return this.c;
            }

            public List<ElementsBean> getElements() {
                return this.d;
            }

            public String getPlaceholder() {
                return this.b;
            }

            public String getScreenName() {
                return this.f4345a;
            }

            public void setElementName(String str) {
                this.c = str;
            }

            public void setElements(List<ElementsBean> list) {
                this.d = list;
            }

            public void setPlaceholder(String str) {
                this.b = str;
            }

            public void setScreenName(String str) {
                this.f4345a = str;
            }
        }

        public PayBean getPay() {
            return this.c;
        }

        public SessionBean getSession() {
            return this.f4342a;
        }

        public List<UiBean> getUi() {
            return this.b;
        }

        public void setPay(PayBean payBean) {
            this.c = payBean;
        }

        public void setSession(SessionBean sessionBean) {
            this.f4342a = sessionBean;
        }

        public void setUi(List<UiBean> list) {
            this.b = list;
        }
    }

    public DataBean getData() {
        return this.c;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.f4341a;
    }

    public void setData(DataBean dataBean) {
        this.c = dataBean;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.f4341a = str;
    }
}
