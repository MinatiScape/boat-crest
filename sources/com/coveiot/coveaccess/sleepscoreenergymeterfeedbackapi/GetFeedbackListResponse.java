package com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFeedbackListResponse {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Data f6724a;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("questionnaireId")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6725a;
        @SerializedName("subject")
        @Expose
        private String b;
        @SerializedName("createdDate")
        @Expose
        private String c;
        @SerializedName("questionnaire")
        @Expose
        private List<Questionnaire> d = null;

        /* loaded from: classes8.dex */
        public class Questionnaire {
            @SerializedName("type")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6726a;
            @SerializedName("questionId")
            @Expose
            private String b;
            @SerializedName("question")
            @Expose
            private Question c;

            /* loaded from: classes8.dex */
            public class Question {
                @SerializedName("text")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private String f6727a;
                @SerializedName("options")
                @Expose
                private List<Option> b = null;

                /* loaded from: classes8.dex */
                public class Option {
                    @SerializedName("optionId")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6728a;
                    @SerializedName("text")
                    @Expose
                    private String b;
                    @SerializedName("iconUrl")
                    @Expose
                    private String c;
                    @SerializedName("activeIconUrl")
                    @Expose
                    private String d;
                    @SerializedName("inactiveIconUrl")
                    @Expose
                    private String e;
                    @SerializedName("userDefined")
                    @Expose
                    private Boolean f;

                    public Option(Question question) {
                    }

                    public String getActiveIconUrl() {
                        return this.d;
                    }

                    public String getIconUrl() {
                        return this.c;
                    }

                    public String getInactiveIconUrl() {
                        return this.e;
                    }

                    public String getOptionId() {
                        return this.f6728a;
                    }

                    public String getText() {
                        return this.b;
                    }

                    public Boolean getUserDefined() {
                        return this.f;
                    }

                    public void setActiveIconUrl(String str) {
                        this.d = str;
                    }

                    public void setIconUrl(String str) {
                        this.c = str;
                    }

                    public void setInactiveIconUrl(String str) {
                        this.e = str;
                    }

                    public void setOptionId(String str) {
                        this.f6728a = str;
                    }

                    public void setText(String str) {
                        this.b = str;
                    }

                    public void setUserDefined(Boolean bool) {
                        this.f = bool;
                    }
                }

                public Question(Questionnaire questionnaire) {
                }

                public List<Option> getOptions() {
                    return this.b;
                }

                public String getText() {
                    return this.f6727a;
                }

                public void setOptions(List<Option> list) {
                    this.b = list;
                }

                public void setText(String str) {
                    this.f6727a = str;
                }
            }

            public Questionnaire(Data data) {
            }

            public Question getQuestion() {
                return this.c;
            }

            public String getQuestionId() {
                return this.b;
            }

            public String getType() {
                return this.f6726a;
            }

            public void setQuestion(Question question) {
                this.c = question;
            }

            public void setQuestionId(String str) {
                this.b = str;
            }

            public void setType(String str) {
                this.f6726a = str;
            }
        }

        public Data(GetFeedbackListResponse getFeedbackListResponse) {
        }

        public String getCreatedDate() {
            return this.c;
        }

        public List<Questionnaire> getQuestionnaire() {
            return this.d;
        }

        public String getQuestionnaireId() {
            return this.f6725a;
        }

        public String getSubject() {
            return this.b;
        }

        public void setCreatedDate(String str) {
            this.c = str;
        }

        public void setQuestionnaire(List<Questionnaire> list) {
            this.d = list;
        }

        public void setQuestionnaireId(String str) {
            this.f6725a = str;
        }

        public void setSubject(String str) {
            this.b = str;
        }
    }

    public Data getData() {
        return this.f6724a;
    }

    public void setData(Data data) {
        this.f6724a = data;
    }
}
