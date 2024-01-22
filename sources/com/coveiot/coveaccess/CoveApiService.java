package com.coveiot.coveaccess;

import com.coveiot.coveaccess.activityfilter.ActivityFilter;
import com.coveiot.coveaccess.activitysession.PostActivitySessionDataRequest;
import com.coveiot.coveaccess.activitysession.SActivitySessionDataResponse;
import com.coveiot.coveaccess.activitysession.SGetActivitySessionDataResponse;
import com.coveiot.coveaccess.alexa.model.request.ActivateAlexaReq;
import com.coveiot.coveaccess.alexa.model.response.ActivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.DeactivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.GetStatusAlexaRes;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequest;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequestResponse;
import com.coveiot.coveaccess.contacttracing.model.NearByDeviceReq;
import com.coveiot.coveaccess.contacttracing.model.NearByDeviceRes;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.PostDailyDataAggregate;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.SGetDailyFitnessAggregateResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.PostDailyFitnessDataRequest;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SGetDailyFitnessDataResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SPostDailyFitnessDataResponse;
import com.coveiot.coveaccess.diagnostics.model.DiagnosticResultRequest;
import com.coveiot.coveaccess.drowsiness.DrowsyEstimationReq;
import com.coveiot.coveaccess.drowsiness.SDrowsyEstimationPostRes;
import com.coveiot.coveaccess.drowsyfatiguestress.DrowsyFatigueStressDataReq;
import com.coveiot.coveaccess.drowsyfatiguestress.SDrowsyFatigueStressDataPostRes;
import com.coveiot.coveaccess.ecgsession.model.PostECGSessionData;
import com.coveiot.coveaccess.energyscore.EnergyScoreApiReq;
import com.coveiot.coveaccess.energyscore.EnergyScoreApiRes;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.DefaultFitnessConfigDataResponse;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessSummaryResponse;
import com.coveiot.coveaccess.fitness.model.ActivityData;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.fitness.model.AllFitnessRecordsResponse;
import com.coveiot.coveaccess.fitness.model.BpFitnessRecords;
import com.coveiot.coveaccess.fitness.model.CreateFitnessGoalRequest;
import com.coveiot.coveaccess.fitness.model.FitnessRecords;
import com.coveiot.coveaccess.fitness.model.GetGoalHistoryResponse;
import com.coveiot.coveaccess.fitness.model.MensurationSymptomRecordResponse;
import com.coveiot.coveaccess.fitness.model.UpdateFitnessGoalRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendReactionRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyDetailsModel;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyListModel;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.GetBuddyLookUpResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.SendFitnessBuddyLookUpRequest;
import com.coveiot.coveaccess.fitnesschallenge.model.AddParticipantsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.FitnessChallengeStatsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.coveaccess.fitnesschallenge.model.RemoveParticipantsReq;
import com.coveiot.coveaccess.fitnesscomputeddataapi.GetFitnessComputedDataResponse;
import com.coveiot.coveaccess.fitnesscomputeddataapi.SaveFitnessComputedDataRequest;
import com.coveiot.coveaccess.healthbuddies.HealthBuddyRequest;
import com.coveiot.coveaccess.leaderboard.model.AddressModel;
import com.coveiot.coveaccess.leaderboard.model.AddressReq;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.BadgesCategoryModel;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.coveaccess.livedata.model.LiveHealthDataModel;
import com.coveiot.coveaccess.manualdata.model.SManualMixedData;
import com.coveiot.coveaccess.model.server.CardItemsBean;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.model.server.DeviceConfigurationRes;
import com.coveiot.coveaccess.model.server.FCMTokenDataReq;
import com.coveiot.coveaccess.model.server.FitnessPlanHistoryRes;
import com.coveiot.coveaccess.model.server.FitnessPlanProgressReq;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.coveaccess.model.server.GenericResponse;
import com.coveiot.coveaccess.model.server.GetAppConfigRes;
import com.coveiot.coveaccess.model.server.GetAuthSessionTokenReq;
import com.coveiot.coveaccess.model.server.GetAuthSessionTokenRes;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import com.coveiot.coveaccess.model.server.GuestUserSessionDataRes;
import com.coveiot.coveaccess.model.server.OTPAuthReq;
import com.coveiot.coveaccess.model.server.PersonalBestResponse;
import com.coveiot.coveaccess.model.server.RRateResponse;
import com.coveiot.coveaccess.model.server.RRateTrainingRequest;
import com.coveiot.coveaccess.model.server.RankDetail;
import com.coveiot.coveaccess.model.server.SActivatePlanResponse;
import com.coveiot.coveaccess.model.server.SAllUserAppSettingsRes;
import com.coveiot.coveaccess.model.server.SAllUserDeviceSettingRes;
import com.coveiot.coveaccess.model.server.SAppReferralModel;
import com.coveiot.coveaccess.model.server.SCardItemUploadBean;
import com.coveiot.coveaccess.model.server.SConsentRequest;
import com.coveiot.coveaccess.model.server.SCreateFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SCurrentFitnessPlanResponse;
import com.coveiot.coveaccess.model.server.SCurrentPreparationPlanRes;
import com.coveiot.coveaccess.model.server.SDeactivatePlanResponse;
import com.coveiot.coveaccess.model.server.SDeleteBuddiesMessageResponse;
import com.coveiot.coveaccess.model.server.SDeleteFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SDrowbsinessFeedbackReq;
import com.coveiot.coveaccess.model.server.SEGLoginRequest;
import com.coveiot.coveaccess.model.server.SEGLoginResponse;
import com.coveiot.coveaccess.model.server.SEgGetUserDetails;
import com.coveiot.coveaccess.model.server.SEgLogoutRequest;
import com.coveiot.coveaccess.model.server.SEgLogoutResponse;
import com.coveiot.coveaccess.model.server.SFeedBackCategories;
import com.coveiot.coveaccess.model.server.SFitnessBuddiesInviteResponse;
import com.coveiot.coveaccess.model.server.SFitnessBuddiesMessagesResponse;
import com.coveiot.coveaccess.model.server.SGetAmbientSoundDataRes;
import com.coveiot.coveaccess.model.server.SGetAuthUserInfo;
import com.coveiot.coveaccess.model.server.SGetBpHeartRateDataRes;
import com.coveiot.coveaccess.model.server.SGetConsentData;
import com.coveiot.coveaccess.model.server.SGetEGInfo;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesGoalSpecificResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesGoalsResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddiesResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessBuddyRequestsAndBuddiesResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SGetFitnessPlanResponse;
import com.coveiot.coveaccess.model.server.SGetHealthBuddiesResponse;
import com.coveiot.coveaccess.model.server.SGetHealthStatusRes;
import com.coveiot.coveaccess.model.server.SGetHeartRateDataRes;
import com.coveiot.coveaccess.model.server.SGetHrvDataRes;
import com.coveiot.coveaccess.model.server.SGetIotMqttCredInfoResponse;
import com.coveiot.coveaccess.model.server.SGetIotMqttInfoResponse;
import com.coveiot.coveaccess.model.server.SGetManualBpData;
import com.coveiot.coveaccess.model.server.SGetManualMixedData;
import com.coveiot.coveaccess.model.server.SGetManualSPO2Data;
import com.coveiot.coveaccess.model.server.SGetSleepData;
import com.coveiot.coveaccess.model.server.SGetSmartAlertAppsData;
import com.coveiot.coveaccess.model.server.SGetSpo2DataRes;
import com.coveiot.coveaccess.model.server.SGetStressDataRes;
import com.coveiot.coveaccess.model.server.SGetTemperatureDataRes;
import com.coveiot.coveaccess.model.server.SGetUserByNumberModel;
import com.coveiot.coveaccess.model.server.SGetUserDetails;
import com.coveiot.coveaccess.model.server.SGymSpaResponse;
import com.coveiot.coveaccess.model.server.SHealthBuddyResponse;
import com.coveiot.coveaccess.model.server.SIOTUserDeviceResponseModel;
import com.coveiot.coveaccess.model.server.SIOTUserDevicesRequestModel;
import com.coveiot.coveaccess.model.server.SMediaListResponse;
import com.coveiot.coveaccess.model.server.SMediaUploadResponse;
import com.coveiot.coveaccess.model.server.SModifyPhoneNumberReq;
import com.coveiot.coveaccess.model.server.SNewGetHrvDataRes;
import com.coveiot.coveaccess.model.server.SNewSaveHrvDataReq;
import com.coveiot.coveaccess.model.server.SPhoneNumberVerificationModel;
import com.coveiot.coveaccess.model.server.SPlanProgressUpdateReq;
import com.coveiot.coveaccess.model.server.SPostAppRefererDataReq;
import com.coveiot.coveaccess.model.server.SPostAppRefererDataRes;
import com.coveiot.coveaccess.model.server.SPostFeedbackToServer;
import com.coveiot.coveaccess.model.server.SPostHeartRateAndBP;
import com.coveiot.coveaccess.model.server.SPostRatingReq;
import com.coveiot.coveaccess.model.server.SPostRatingRes;
import com.coveiot.coveaccess.model.server.SPostReportIssue;
import com.coveiot.coveaccess.model.server.SPromotionalOffers;
import com.coveiot.coveaccess.model.server.SRefreshTokenRequest;
import com.coveiot.coveaccess.model.server.SRefreshTokenResponse;
import com.coveiot.coveaccess.model.server.SRegisterNewUserModel;
import com.coveiot.coveaccess.model.server.SRegisterReturningUserModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.model.server.SRemoveProfilePicture;
import com.coveiot.coveaccess.model.server.SRemoveUserModel;
import com.coveiot.coveaccess.model.server.SSaveAmbientSoundDataReq;
import com.coveiot.coveaccess.model.server.SSaveBpDataReq;
import com.coveiot.coveaccess.model.server.SSaveFitnessRecordReq;
import com.coveiot.coveaccess.model.server.SSaveHeartBeatData;
import com.coveiot.coveaccess.model.server.SSaveHeartRateDataReq;
import com.coveiot.coveaccess.model.server.SSaveHrvDataReq;
import com.coveiot.coveaccess.model.server.SSaveLocationBreachInfo;
import com.coveiot.coveaccess.model.server.SSaveManualData;
import com.coveiot.coveaccess.model.server.SSaveManualSPO2Data;
import com.coveiot.coveaccess.model.server.SSaveMensurationFitnessRecordReq;
import com.coveiot.coveaccess.model.server.SSavePeriodicStressDataReq;
import com.coveiot.coveaccess.model.server.SSaveSleepData;
import com.coveiot.coveaccess.model.server.SSaveSpo2DataReq;
import com.coveiot.coveaccess.model.server.SSaveTemperatureDataReq;
import com.coveiot.coveaccess.model.server.SSoftwareUpdateReq;
import com.coveiot.coveaccess.model.server.STimelineCardDataResponse;
import com.coveiot.coveaccess.model.server.STimelineCardSaveResponse;
import com.coveiot.coveaccess.model.server.STrackDetailsModel;
import com.coveiot.coveaccess.model.server.STrackListModel;
import com.coveiot.coveaccess.model.server.SUpdateEgDeviceHeartbeat;
import com.coveiot.coveaccess.model.server.SUpdateEgHeartBeat;
import com.coveiot.coveaccess.model.server.SUpdateEgInfo;
import com.coveiot.coveaccess.model.server.SUpdateEgSession;
import com.coveiot.coveaccess.model.server.SUpdateFitnessGoalResponse;
import com.coveiot.coveaccess.model.server.SUpdateLocationApiReq;
import com.coveiot.coveaccess.model.server.SUpdateProfilePicModel;
import com.coveiot.coveaccess.model.server.SUpdateRegistrationTokenModel;
import com.coveiot.coveaccess.model.server.SUpdateUserModel;
import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
import com.coveiot.coveaccess.model.server.SUserDeviceSettingsReq;
import com.coveiot.coveaccess.model.server.SWatchFaceList;
import com.coveiot.coveaccess.model.server.SWorkoutCategoryList;
import com.coveiot.coveaccess.model.server.SWorkoutVideosList;
import com.coveiot.coveaccess.model.server.UserPlanInfoRes;
import com.coveiot.coveaccess.model.server.UserRemoteConfigResponse;
import com.coveiot.coveaccess.model.server.WatchFaceRequest;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.SaveFavouritePlaceRes;
import com.coveiot.coveaccess.navigation.SavePlaceTrackHistoryRes;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsRes;
import com.coveiot.coveaccess.onboarding.model.FCMRegistrationReq;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.coveaccess.onboarding.model.UpdateUserReq;
import com.coveiot.coveaccess.onekactivity.SPhysicalActivityCategoriesRes;
import com.coveiot.coveaccess.onekactivity.SPhysicalActivityListRes;
import com.coveiot.coveaccess.preparationplan.requestmodel.ActivateFitnessPlanReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.DeactivateFitnessPlanReq;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodeIDsReq;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveReq;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveRes;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiReq;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiRes;
import com.coveiot.coveaccess.runsession.PostSessionDataRequest;
import com.coveiot.coveaccess.runsession.SGetAllSessionResponse;
import com.coveiot.coveaccess.runsession.SGetSessionDataResponse;
import com.coveiot.coveaccess.runsession.SPostSessionDataResponse;
import com.coveiot.coveaccess.sedentaryalerts.model.SGetSedentaryAlertsDataRes;
import com.coveiot.coveaccess.sedentaryalerts.model.SSaveSedentaryAlertsDataReq;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiReq;
import com.coveiot.coveaccess.sleepscore.SleepScoreApiRes;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import com.coveiot.coveaccess.sos.model.SosEventReq;
import com.coveiot.coveaccess.spo2.SPO2EstimationReq;
import com.coveiot.coveaccess.spo2.SSPO2EstimationPostRes;
import com.coveiot.coveaccess.sports.SGetSportsUserPrefRes;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.coveaccess.sports.SportsUserPrefRequest;
import com.coveiot.coveaccess.sports.SportsUserPrefResponse;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenRequest;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenResponse;
import com.coveiot.coveaccess.tappy.model.SApduCommand;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningRequest;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningResponse;
import com.coveiot.coveaccess.tappy.model.SDeletePaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SDeviceInfo;
import com.coveiot.coveaccess.tappy.model.SEncryptionKey;
import com.coveiot.coveaccess.tappy.model.SErrorLogRequest;
import com.coveiot.coveaccess.tappy.model.SGetTAndCResponse;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsByTransactionIdResponse;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsResponse;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementRequest;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementResponse;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SProduct;
import com.coveiot.coveaccess.tappy.model.SPutPostPersoCommandsExecutedResponse;
import com.coveiot.coveaccess.tappy.model.SPutStepUpOptionsRequest;
import com.coveiot.coveaccess.tappy.model.SRegisterProductRequest;
import com.coveiot.coveaccess.tappy.model.SRegisterUserRequest;
import com.coveiot.coveaccess.tappy.model.SRegisteredProduct;
import com.coveiot.coveaccess.tappy.model.SResumePaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SResumePaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SSECardPersoScript;
import com.coveiot.coveaccess.tappy.model.SSendPaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SStepUpRequest;
import com.coveiot.coveaccess.tappy.model.SSuspendPaymentInstrumentTokensRequest;
import com.coveiot.coveaccess.tappy.model.SSuspendPaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SUpdateRegisteredProductFriendlyNameRequest;
import com.coveiot.coveaccess.tappy.model.SUserDataResponse;
import com.coveiot.coveaccess.tappy.model.SUserDataResponseNew;
import com.coveiot.coveaccess.tappy.model.SValidateOTPRequest;
import com.coveiot.coveaccess.tappy.model.SValidateOTPResponse;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.coveaccess.watchface.model.WatchFaceCategoriesResponse;
import com.coveiot.coveaccess.watchface.model.WatchfaceByIdRequest;
import com.coveiot.coveaccess.weeklyreport.request.GenerateOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.VerifyOtpReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportSubscriptionReq;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportUnsubscribeReq;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.coveaccess.weeklyreport.response.GenerateVerifyOtpRes;
import com.coveiot.coveaccess.weeklyreport.response.WeekReportSubscriptionResponse;
import com.coveiot.coveaccess.weeklyreport.response.WeeklyReportSubscriptionStatusResponse;
import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
/* loaded from: classes8.dex */
public interface CoveApiService {
    @PUT
    Call<SAcceptTermsAndGenerateTokenResponse> acceptTermsAndGenerateToken(@HeaderMap Map<String, String> map, @Url String str, @Body SAcceptTermsAndGenerateTokenRequest sAcceptTermsAndGenerateTokenRequest);

    @POST("/alexa/skill/activate")
    Call<ActivateAlexaRes> activateAlexaAccountLinking(@HeaderMap Map<String, String> map, @Body ActivateAlexaReq activateAlexaReq);

    @POST("fitness/plan/activate")
    Call<SActivatePlanResponse> activateFitnessPlan(@HeaderMap Map<String, String> map, @Body ActivateFitnessPlanReq activateFitnessPlanReq);

    @POST("/fitness/challenge/participant")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> addParticipants(@HeaderMap Map<String, String> map, @Body AddParticipantsReq addParticipantsReq);

    @DELETE("fitness/buddy/request/{requestId}")
    Call<ResponseBody> cancelBuddyRequest(@HeaderMap Map<String, String> map, @Path("requestId") int i);

    @DELETE("relation/guardian/request/{requestId}")
    Call<SHealthBuddyResponse> cancelHealthBuddyRequest(@HeaderMap Map<String, String> map, @Path("requestId") int i);

    @PUT
    Call<SConfirmProvisioningResponse> confirmProvisioning(@HeaderMap Map<String, String> map, @Url String str, @Body SConfirmProvisioningRequest sConfirmProvisioningRequest);

    @POST("fitness/goal")
    Call<SCreateFitnessGoalResponse> createFitnessGoal(@HeaderMap Map<String, String> map, @Body CreateFitnessGoalRequest createFitnessGoalRequest);

    @POST("/fitness/challenge/buddy")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> createNewBuddiesChallenge(@HeaderMap Map<String, String> map, @Body CreateFitnessChallengeReq createFitnessChallengeReq);

    @POST("/fitness/challenge")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> createNewGlobalChallenge(@HeaderMap Map<String, String> map, @Body CreateFitnessChallengeReq createFitnessChallengeReq);

    @POST("fitness/plan/deactivate")
    Call<SDeactivatePlanResponse> deActivateFitnessPlan(@HeaderMap Map<String, String> map, @Body DeactivateFitnessPlanReq deactivateFitnessPlanReq);

    @POST("/alexa/skill/deactivate")
    Call<DeactivateAlexaRes> deactivateAlexaAccountLinking(@HeaderMap Map<String, String> map);

    @DELETE("/fitness/challenge/buddy/{challengeId}")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> deleteBuddyChallenge(@HeaderMap Map<String, String> map, @Path("challengeId") Object obj);

    @DELETE("fitness/buddy/message/{messageId}")
    Call<SDeleteBuddiesMessageResponse> deleteBuddyNudgeMessages(@HeaderMap Map<String, String> map, @Path("messageId") String str);

    @DELETE("fitness/goal/{goalId}")
    Call<SDeleteFitnessGoalResponse> deleteFitnessGoal(@HeaderMap Map<String, String> map, @Path("goalId") int i);

    @DELETE
    Call<Void> deletePaymentInstrument(@HeaderMap Map<String, String> map, @Url String str, @Query("deleteTokens") boolean z);

    @DELETE
    Call<SDeletePaymentInstrumentTokensResponse> deletePaymentInstrumentTokens(@HeaderMap Map<String, String> map, @Url String str, @Query("reason") String str2);

    @HTTP(hasBody = true, method = "DELETE", path = "/user/device/qrcode")
    Call<CommonResponseClass> deleteQRCodes(@HeaderMap Map<String, String> map, @Body QRTrayCodeIDsReq qRTrayCodeIDsReq);

    @DELETE
    Call<Void> deleteRegisteredProduct(@HeaderMap Map<String, String> map, @Url String str);

    @DELETE
    Call<Void> deleteTappyUser(@HeaderMap Map<String, String> map, @Url String str);

    @DELETE
    Call<CommonResponseClass> deleteTappyUserNew(@HeaderMap Map<String, String> map, @Url String str);

    @DELETE("timeline/feed/{timelineLogId}")
    Call<JSONObject> deleteTimeLineEntry(@HeaderMap Map<String, String> map, @Path("timelineLogId") String str);

    @DELETE("/user/session")
    Call<CommonResponseClass> deleteUserSession(@HeaderMap Map<String, String> map);

    @HTTP(hasBody = true, method = "DELETE", path = "/watchfaces")
    Call<CommonResponseClass> deleteWatchFace(@HeaderMap Map<String, String> map, @Body WatchFaceRequest watchFaceRequest);

    @Streaming
    @POST("/device/dx/report")
    Call<ResponseBody> diagnosticReportDownload(@HeaderMap Map<String, String> map, @Body DiagnosticResultRequest diagnosticResultRequest);

    @Streaming
    @GET("/fitness/challenge/leaderboard/report/download")
    Call<ResponseBody> downloadLeaderboardReport(@HeaderMap Map<String, String> map, @Query("challengeId") Object obj);

    @PUT("/fitness/challenge/buddy/{challengeId}")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> editBuddyChallenge(@HeaderMap Map<String, String> map, @Path("challengeId") Object obj, @Body CreateFitnessChallengeReq createFitnessChallengeReq);

    @PUT("/user/place/favs/{id}")
    Call<CommonResponseGeneric<SaveFavouritePlaceRes>> editFavouritePlace(@HeaderMap Map<String, String> map, @Body FavouritePlace favouritePlace, @Path("id") Object obj);

    @PUT("/user/device/qrcode/{id}")
    Call<CommonResponseGeneric<QRTraySaveRes>> editQRTray(@HeaderMap Map<String, String> map, @Body QRTraySaveReq qRTraySaveReq, @Path("id") Object obj);

    @POST("/v2/auth/otp")
    Call<CommonResponseClass> generateAuthOtp(@HeaderMap Map<String, String> map, @Body OTPAuthReq oTPAuthReq);

    @POST("/user/profile/otp")
    Call<GenerateVerifyOtpRes> generateOtpWithEmail(@HeaderMap Map<String, String> map, @Body GenerateOtpReq generateOtpReq);

    @GET("virtualrun/tracks")
    Call<STrackListModel> getALlTrack(@HeaderMap Map<String, String> map);

    @GET("fitness/log")
    Call<ActivityData> getActivityDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("activityTypes") ActivityType activityType, @Query("timeLog") boolean z);

    @GET("/fitness/activitySession/filterOptions")
    Call<ActivityFilter> getActivityFilterOption(@HeaderMap Map<String, String> map, @Query("firmwareVersion") String str);

    @GET("leaderboard/badges")
    Call<AllBadgesModel> getAllBadges(@HeaderMap Map<String, String> map);

    @GET("leaderboard/badges")
    Call<AllBadgesModel> getAllBadgesByCategories(@HeaderMap Map<String, String> map, @Query("categoryId") String str);

    @GET("eg/app/user/setting")
    Call<SAllUserAppSettingsRes> getAllEgUserAppSettings(@HeaderMap Map<String, String> map);

    @GET("fitness/records")
    Call<AllFitnessRecordsResponse> getAllFitnessRecords(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("/fitness/challenge/participants")
    Call<CommonResponseGeneric<GetAllParticipantsFitnessChallengeRes>> getAllParticipants(@HeaderMap Map<String, String> map, @Query("challengeId") Object obj, @Query("type") String str, @Query("participantType") String str2, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET("/fitness/session?sessionTypes=RUN")
    Call<SGetAllSessionResponse> getAllSessions(@HeaderMap Map<String, String> map);

    @GET("app/user/setting")
    Call<SAllUserAppSettingsRes> getAllUserAppSettings(@HeaderMap Map<String, String> map);

    @GET("/user/device/setting")
    Call<SAllUserDeviceSettingRes> getAllUserDevicesSettings(@HeaderMap Map<String, String> map);

    @GET("fitness/data")
    Call<SGetAmbientSoundDataRes> getAmbientSoundDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("/app/config")
    Call<GetAppConfigRes> getAppConfig(@HeaderMap Map<String, String> map, @Query("type") String str, @Query("v") String str2);

    @GET("app/referral")
    Call<SAppReferralModel> getAppReferalLinks(@HeaderMap Map<String, String> map);

    @POST("/oa/authSessionToken")
    Call<GetAuthSessionTokenRes> getAuthSessionToken(@HeaderMap Map<String, String> map, @Body GetAuthSessionTokenReq getAuthSessionTokenReq);

    @GET("eg/auth/user")
    Call<SGetAuthUserInfo> getAuthUserInfo(@HeaderMap Map<String, String> map);

    @POST("v2/auth/otp/get/serviceList")
    Call<CommonResponseGeneric<GetOTPServicesRes>> getAvailableOtpServices(@HeaderMap Map<String, String> map, @Body JsonObject jsonObject);

    @GET("leaderboard/badges/categories")
    Call<BadgesCategoryModel> getBadgesCategory(@HeaderMap Map<String, String> map);

    @GET("/fitness/challenge/bannerImages")
    Call<CommonResponseGeneric<BannerImagesRes>> getBannerImageForChallengeCreation(@HeaderMap Map<String, String> map);

    @GET("/user/coins/profile")
    Call<BoatCoinsDetailsResponse> getBoatCoinsDetails(@HeaderMap Map<String, String> map);

    @GET("fitness/data")
    Call<SGetBpHeartRateDataRes> getBpDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("fitness/records")
    Call<BpFitnessRecords> getBpFitnessRecords(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("fitness/buddy/goal")
    Call<SGetFitnessBuddiesGoalSpecificResponse> getBuddiesActivitySpecificGoals(@HeaderMap Map<String, String> map, @Query("activityTypes") String str);

    @GET("fitness/buddy/goal")
    Call<SGetFitnessBuddiesGoalsResponse> getBuddiesGoals(@HeaderMap Map<String, String> map);

    @GET("leaderboard/user/buddies/topRanks")
    Call<TopRankModel> getBuddiesTopRank(@HeaderMap Map<String, String> map, @Query("criterion") String str, @Query("placeType") String str2, @Query("rankType") String str3, @Query("date") String str4);

    @GET("/fitness/challenge/buddy/{challengeId}")
    Call<CommonResponseGeneric<BuddiesChallengeDetail>> getBuddyChallengeDetails(@HeaderMap Map<String, String> map, @Path("challengeId") Object obj);

    @GET("fitness/buddy/message")
    Call<SFitnessBuddiesMessagesResponse> getBuddyMessages(@HeaderMap Map<String, String> map);

    @GET("/user/device/qrcode/categories")
    Call<CommonResponseGeneric<QRTrayCategoriesRes>> getCategories(@HeaderMap Map<String, String> map);

    @GET("/fitness/videos/categories")
    Call<SWorkoutCategoryList> getCategoriesList(@HeaderMap Map<String, String> map);

    @GET("/fitness/challenges/userData")
    Call<CommonResponseGeneric<GetChallengeStartNEndDateRes>> getChallengeDates(@HeaderMap Map<String, String> map);

    @GET("/fitness/challenges")
    Call<CommonResponseGeneric<BuddiesChallengeRes>> getChallenges(@HeaderMap Map<String, String> map, @Query("type") String str, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET("user/consent")
    Call<SGetConsentData> getConsentDataFromServer(@HeaderMap Map<String, String> map);

    @GET("fitness/plan/current")
    Call<SCurrentFitnessPlanResponse> getCurrentPlan(@HeaderMap Map<String, String> map);

    @GET("/fitness/plan/current")
    Call<SCurrentPreparationPlanRes> getCurrentPreparationPlan(@HeaderMap Map<String, String> map, @Query("serializePlanTemplate") boolean z);

    @GET("/fitness/data")
    Call<SGetDailyFitnessDataResponse> getDailyFitnessDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("/fitness/challenges/context/dashboard")
    Call<CommonResponseGeneric<BuddiesChallengeRes>> getDashboardFitnessChallenge(@HeaderMap Map<String, String> map);

    @GET("fitness/config")
    Call<DefaultFitnessConfigDataResponse> getDefaultFitnessConfigData(@HeaderMap Map<String, String> map);

    @GET
    Call<DeviceConfigurationRes> getDeviceConfiguration(@HeaderMap Map<String, String> map, @Url String str);

    @GET("eg/info")
    Call<SGetEGInfo> getEGInfo(@HeaderMap Map<String, String> map);

    @GET("eg/user/profile")
    Call<SEgGetUserDetails> getEgUserDetails(@HeaderMap Map<String, String> map);

    @GET
    Call<SEncryptionKey> getEncryptionKey(@HeaderMap Map<String, String> map, @Url String str);

    @POST
    Call<EnergyScoreApiRes> getEnergyScore(@HeaderMap Map<String, String> map, @Url String str, @Body EnergyScoreApiReq energyScoreApiReq);

    @GET("/user/place/favs")
    Call<CommonResponseGeneric<GetFavouritePlacesRes>> getFavouritePlaces(@HeaderMap Map<String, String> map);

    @GET("user/feedback/form")
    Call<SFeedBackCategories> getFeedbackCategories(@HeaderMap Map<String, String> map);

    @GET("user/questionnaire")
    Call<GetFeedbackListResponse> getFeedbackQuestionarieList(@HeaderMap Map<String, String> map, @Query("subject") String str);

    @GET("user/questionnaire")
    Call<GetFeedbackListResponse> getFeedbackQuestionarieListByQuentionnaireID(@HeaderMap Map<String, String> map, @Query("questionnaireId") String str);

    @GET("fitness/buddy")
    Call<SGetFitnessBuddiesResponse> getFitnessBuddies(@HeaderMap Map<String, String> map);

    @GET("fitness/buddy/request")
    Call<SGetFitnessBuddyRequestsAndBuddiesResponse> getFitnessBuddiesAndRequests(@HeaderMap Map<String, String> map);

    @GET("fitness/buddy/request")
    Call<SGetFitnessBuddyRequestsAndBuddiesResponse> getFitnessBuddiesAndRequestsNew(@HeaderMap Map<String, String> map, @Query("apiRevision") int i, @Query("fields") String str);

    @GET("/fitness/buddy/{buddyId}/details")
    Call<GetBuddyDetailsModel> getFitnessBuddiesDetails(@HeaderMap Map<String, String> map, @Path("buddyId") String str, @Query("fields") String str2);

    @POST("/user/lookup")
    Call<GetBuddyLookUpResponse> getFitnessBuddiesLookUp(@HeaderMap Map<String, String> map, @Body SendFitnessBuddyLookUpRequest sendFitnessBuddyLookUpRequest);

    @GET("fitness/buddy/stats")
    Call<GetBuddyListModel> getFitnessBuddiesStats(@HeaderMap Map<String, String> map, @Query("apiRevision") int i, @Query("fields") String str);

    @GET("fitness/data/computed")
    Call<GetFitnessComputedDataResponse> getFitnessComputedDataFromSever(@HeaderMap Map<String, String> map, @Query("fromDate") String str);

    @GET("fitness/summary")
    Call<FitnessSummaryResponse> getFitnessConfigData(@HeaderMap Map<String, String> map);

    @GET("/fitness/data/aggregate")
    Call<SGetDailyFitnessAggregateResponse> getFitnessDataAggregate(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2);

    @GET("fitness/goal?activityTypes=WALK,SLEEP,ANY")
    Call<SGetFitnessGoalResponse> getFitnessGoal(@HeaderMap Map<String, String> map);

    @GET("/fitness/plan/history")
    Call<FitnessPlanHistoryRes> getFitnessHistory(@HeaderMap Map<String, String> map);

    @GET("/fitness/activitySession/stats")
    Call<GetOverallStatsResponse> getFitnessOverallStats(@HeaderMap Map<String, String> map, @Query("range") String str, @Query("sessionType") String str2);

    @GET("fitness/plan")
    Call<SGetFitnessPlanResponse> getFitnessPlan(@HeaderMap Map<String, String> map);

    @GET("fitness/records")
    Call<FitnessRecords> getFitnessRecords(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("/fitness/challenge/{challengeId}")
    Call<CommonResponseGeneric<BuddiesChallengeDetail>> getGlobalChallengeDetails(@HeaderMap Map<String, String> map, @Path("challengeId") Object obj);

    @GET("/fitness/goal/history")
    Call<GetGoalHistoryResponse> getGoalHistory(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("activityType") String str2, @Query("activityBaseUnits") String str3);

    @Headers({"Content-Type: application/json"})
    @GET("places/search/near")
    Call<SGymSpaResponse> getGymSpaByLocation(@HeaderMap Map<String, String> map, @Query("location") String str, @Query("radius") int i, @Query("placeType") String str2);

    @Headers({"Content-Type: application/json"})
    @GET("places/search/name")
    Call<SGymSpaResponse> getGymSpaByName(@HeaderMap Map<String, String> map, @Query("name") String str, @Query("radius") int i, @Query("placeType") String str2);

    @GET("relation/guardian")
    Call<SGetHealthBuddiesResponse> getHealthBuddies(@HeaderMap Map<String, String> map);

    @GET("relation/guardian/request")
    Call<SGetHealthBuddiesResponse> getHealthBuddiesRequestLog(@HeaderMap Map<String, String> map);

    @GET("user/health/status")
    Call<SGetHealthStatusRes> getHealthStatus(@HeaderMap Map<String, String> map);

    @GET("fitness/data")
    Call<SGetHeartRateDataRes> getHeartRateDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("homeScreen?contentType=html")
    Call<ResponseBody> getHomeScreenData(@HeaderMap Map<String, String> map);

    @GET("fitness/data")
    Call<SGetHrvDataRes> getHrvDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("physicalActivity/listInbuilt")
    Call<SPhysicalActivityListRes> getInBuiltPhysicalActivityList(@HeaderMap Map<String, String> map);

    @GET("iot/user/device/{userDeviceId}/mqtt/creds")
    Call<SGetIotMqttCredInfoResponse> getIotMqttCredInfoFromServer(@HeaderMap Map<String, String> map, @Path("userDeviceId") String str);

    @GET("iot/user/device/{userDeviceId}/mqtt")
    Call<SGetIotMqttInfoResponse> getIotMqttInfoFromServer(@HeaderMap Map<String, String> map, @Path("userDeviceId") String str);

    @GET("/legal/history")
    Call<LegalDocsAcceptedListRes> getLegalAcceptedDocs(@HeaderMap Map<String, String> map, @Query("types") String str);

    @GET("/fitness/data/session")
    Call<SGetManualBpData> getManualData(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("/fitness/data/session")
    Call<SGetManualMixedData> getManualMixedData(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("/fitness/data/session")
    Call<SGetManualSPO2Data> getManualSPO2Data(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("place/{placeId}/media")
    Call<SMediaListResponse> getMediaListFor(@HeaderMap Map<String, String> map, @Path("placeId") String str);

    @GET("fitness/records")
    Call<MensurationSymptomRecordResponse> getMensesSymptomRecords(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3);

    @GET("/fitness/challenges/context/myAchievement")
    Call<CommonResponseGeneric<BuddiesChallengeRes>> getMyAchievement(@HeaderMap Map<String, String> map, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET("leaderboard/user/badges")
    Call<MyBadgesModel> getMyBadges(@HeaderMap Map<String, String> map);

    @GET("/fitness/myChallenges")
    Call<CommonResponseGeneric<BuddiesChallengeRes>> getMyChallenges(@HeaderMap Map<String, String> map, @Query("status") String str, @Query("pageIndex") Integer num, @Query("itemsPerPage") Integer num2);

    @GET("leaderboard/user/rank")
    Call<MyRankModel> getMyRank(@HeaderMap Map<String, String> map);

    @GET("leaderboard/user/rank")
    Call<MyRankModel> getMyRank(@HeaderMap Map<String, String> map, @Query("placeType") String str);

    @GET("/user/place/trips/last")
    Call<CommonResponseGeneric<FavouritePlace>> getNavigationLastTrip(@HeaderMap Map<String, String> map);

    @GET("fitness/data")
    Call<SNewGetHrvDataRes> getNewHrvDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET
    Call<List<SPaymentInstrument>> getPaymentInstrumentByUerId(@HeaderMap Map<String, String> map, @Url String str);

    @GET("fitness/summary?fitnessStats=MAX_DISTANCE_BY_DAY")
    Call<PersonalBestResponse> getPersonalBest(@HeaderMap Map<String, String> map);

    @GET("physicalActivity/categories")
    Call<SPhysicalActivityCategoriesRes> getPhysicalActivityCategories(@HeaderMap Map<String, String> map);

    @GET("physicalActivity/list")
    Call<SPhysicalActivityListRes> getPhysicalActivityList(@HeaderMap Map<String, String> map, @Query("categoryId") int i);

    @GET
    Call<List<SApduCommand>> getPostPersoCommands(@HeaderMap Map<String, String> map, @Url String str, @Query("initUpdateResponse") String str2);

    @GET("/content/offers")
    Call<SPromotionalOffers> getPromotionalOffers(@HeaderMap Map<String, String> map);

    @GET("/user/device/qrcode")
    Call<CommonResponseGeneric<QRTrayCodesRes>> getQRCodes(@HeaderMap Map<String, String> map);

    @GET("leaderboard/user/ranks")
    Call<RankDetail> getRankDetails(@HeaderMap Map<String, String> map, @Query("placeTypes") String str);

    @GET("leaderboard/user/rankHistory/{ranktype}/{filter}")
    Call<RankHistoryModel> getRankHistory(@HeaderMap Map<String, String> map, @Path("ranktype") String str, @Path("filter") String str2);

    @GET("app/remote/config")
    Call<SRemoteConfigResponse> getRemoteConfig(@HeaderMap Map<String, String> map);

    @GET("app/remote/config")
    Call<SRemoteConfigResponse> getRemoteConfig(@HeaderMap Map<String, String> map, @Query("uiRevision") String str);

    @GET("v2/app/remote/config")
    Call<SRemoteConfigResponse> getRemoteConfigV2(@HeaderMap Map<String, String> map, @Query("rcVersion") String str);

    @POST
    Call<RespiratoryRateApiRes> getRespiratoryRateByUsingPPGData(@HeaderMap Map<String, String> map, @Url String str, @Body RespiratoryRateApiReq respiratoryRateApiReq);

    @GET("fitness/data")
    Call<SGetSedentaryAlertsDataRes> getSedentaryAlertsDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("fitness/session/{fitnessSessionId}")
    Call<SGetSessionDataResponse> getSessionDataForId(@HeaderMap Map<String, String> map, @Path("fitnessSessionId") String str);

    @GET("fitness/activitySession/list")
    Call<SGetActivitySessionDataResponse> getSessionHeaderListFromServer(@HeaderMap Map<String, String> map, @Query("apiRevision") int i, @Query("fromDate") String str, @Query("toDate") String str2, @Query("userDeviceId") String str3);

    @GET("fitness/activitySession")
    Call<SActivitySessionDataResponse> getSessionOverallDataFromServer(@HeaderMap Map<String, String> map, @Query("apiRevision") int i, @Query("userDeviceId") int i2, @Query("clientRefId") String str, @Query("sessionType") ActivityType activityType);

    @GET("/fitness/data")
    Call<SGetSleepData> getSleepDataV2For(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @POST
    Call<SleepScoreApiRes> getSleepScore(@HeaderMap Map<String, String> map, @Url String str, @Body SleepScoreApiReq sleepScoreApiReq);

    @GET("user/device/smartAlertApps")
    Call<SGetSmartAlertAppsData> getSmartAlertApps(@HeaderMap Map<String, String> map, @Query("firmwareVersion") String str);

    @GET("/content/smartGrid")
    Call<CommonResponseGeneric<GetSmartGridRes>> getSmartGridItems(@HeaderMap Map<String, String> map, @Query("firmwareVersion") String str);

    @GET("fitness/data")
    Call<SGetSpo2DataRes> getSpo2DataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @POST("/sport/auth")
    Call<SportsTokenRes> getSportAuthAccessToken(@HeaderMap Map<String, String> map, @Body SportsAuthTokenRequest sportsAuthTokenRequest);

    @GET("/sport/userPrefs?apiRevision=1")
    Call<SportsUserPrefResponse> getSportsUserPref(@HeaderMap Map<String, String> map, @Query("userDeviceId") Integer num);

    @GET("/alexa/skill/status")
    Call<GetStatusAlexaRes> getStatusAlexaAccountLinking(@HeaderMap Map<String, String> map);

    @GET
    Call<List<SStepUpRequest>> getStepUpOptions(@HeaderMap Map<String, String> map, @Url String str, @Query("deviceId") long j);

    @GET("fitness/data")
    Call<SGetStressDataRes> getStressDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @GET("/msg/subscription/status")
    Call<WeeklyReportSubscriptionStatusResponse> getSubscriptionStatus(@HeaderMap Map<String, String> map);

    @GET
    Call<List<SProduct>> getTappyAllProducts(@HeaderMap Map<String, String> map, @Url String str, @Query("includeDeleted") boolean z);

    @GET
    Call<SProduct> getTappyProductDetailsByProductId(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SProduct> getTappyProductDetailsBySerialNumber(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<List<SRegisteredProduct>> getTappyRegisteredProductDetailsByUserId(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SRegisteredProduct> getTappyRegisteredProductDetailsByUserIdAndPRegId(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SGetTAndCResponse> getTappyTAndC(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SGetTransactionDetailsResponse> getTappyTransactionDetails(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SGetTransactionDetailsByTransactionIdResponse> getTappyTransactionDetailsById(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SUserDataResponse> getTappyUserByEmail(@HeaderMap Map<String, String> map, @Url String str, @Query("email") String str2, @Query("includeAddress") boolean z, @Query("includeCustomFieldAnswers") boolean z2);

    @GET
    Call<List<SDeviceInfo>> getTappyUserDeviceInfo(@HeaderMap Map<String, String> map, @Url String str);

    @GET
    Call<SUserDataResponse> getTappyUserInfo(@HeaderMap Map<String, String> map, @Url String str);

    @GET("fitness/data")
    Call<SGetTemperatureDataRes> getTemperatureDataFromServer(@HeaderMap Map<String, String> map, @Query("fromDate") String str, @Query("toDate") String str2, @Query("types") String str3, @Query("timeLog") boolean z);

    @POST("/legal/accept")
    Call<AcceptLegalTermsRes> getTermsAndConditionsAcceptance(@HeaderMap Map<String, String> map, @Body AcceptLegalTermsReq acceptLegalTermsReq);

    @GET("timeline/feed")
    Call<STimelineCardDataResponse> getTimeLineDataFor(@HeaderMap Map<String, String> map, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET
    Call<SSECardPersoScript> getTokenPersoScript(@HeaderMap Map<String, String> map, @Url String str);

    @GET("leaderboard/user/topRank/{rankId}")
    Call<TopRankModel> getTopRank(@HeaderMap Map<String, String> map, @Path("rankId") String str);

    @GET("/virtualrun/track/{id}")
    Call<STrackDetailsModel> getTrackDetailsById(@HeaderMap Map<String, String> map, @Path("id") String str);

    @GET("userbymobile/{phoneNumber}")
    Call<SGetUserByNumberModel> getUserByPhoneNumber(@HeaderMap Map<String, String> map, @Path("phoneNumber") String str);

    @GET("user/profile")
    Call<SGetUserDetails> getUserDetails(@HeaderMap Map<String, String> map);

    @GET("/fitness/plan/{userPlanId}")
    Call<UserPlanInfoRes> getUserFitnessPlanInfo(@HeaderMap Map<String, String> map, @Path("userPlanId") String str);

    @GET("/fitness/plans/{planTemplateId}")
    Call<FitnessPlanTemplateRes> getUserFitnessPlanTemplate(@HeaderMap Map<String, String> map, @Path("planTemplateId") String str);

    @GET("user/remote/config")
    Call<UserRemoteConfigResponse> getUserRemoteConfig(@HeaderMap Map<String, String> map);

    @GET("/fitness/videos/categories")
    Call<SWorkoutCategoryList> getVideoCategoriesList(@HeaderMap Map<String, String> map, @Query("subject") String str);

    @GET("/fitness/videos/recommendation")
    Call<SWorkoutVideosList> getVideoRecommendationsList(@HeaderMap Map<String, String> map, @Query("subject") String str);

    @GET("/fitness/videos/list")
    Call<SWorkoutVideosList> getVideosList(@HeaderMap Map<String, String> map, @Query("categoryId") String str, @Query("pageIndex") int i, @Query("itemsPerPage") int i2, @Query("subject") String str2);

    @GET("watchfaces")
    Call<SWatchFaceList> getWatchFaceList(@HeaderMap Map<String, String> map, @Query("edition") String str, @Query("faceType") String str2, @Query("firmwareVersion") String str3, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET("watchfaces")
    Call<SWatchFaceList> getWatchFaceListByCategory(@HeaderMap Map<String, String> map, @Query("edition") String str, @Query("faceType") String str2, @Query("categoryId") String str3, @Query("firmwareVersion") String str4, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @POST("GET/watchface/details")
    Call<SWatchFaceList> getWatchFaceListById(@HeaderMap Map<String, String> map, @Body WatchfaceByIdRequest watchfaceByIdRequest);

    @GET("watchfaces/categories")
    Call<WatchFaceCategoriesResponse> getWatchFacesCategory(@HeaderMap Map<String, String> map, @Query("faceType") String str);

    @GET("/fitness/reports")
    Call<CommonResponseGeneric<FitnessReportRes>> getWeeklyFitnessReport(@HeaderMap Map<String, String> map);

    @GET("/fitness/videos/list")
    Call<SWorkoutVideosList> getWorkoutVideosList(@HeaderMap Map<String, String> map, @Query("categoryId") String str, @Query("pageIndex") int i, @Query("itemsPerPage") int i2);

    @GET("fitness/buddy/request/{requestId}/{action}")
    Call<ResponseBody> handleBuddyRequest(@HeaderMap Map<String, String> map, @Path("requestId") int i, @Path("action") String str);

    @POST
    Call<SInstallFoundationToSecureElementResponse> installFoundationToSecureElement(@HeaderMap Map<String, String> map, @Url String str, @Body SInstallFoundationToSecureElementRequest sInstallFoundationToSecureElementRequest);

    @POST("/fitness/challenge/join")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> joinChallenge(@HeaderMap Map<String, String> map, @Body JoinChallengeReq joinChallengeReq);

    @POST("/fitness/challenge/leave")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> leaveBuddyOrGlobalChallenge(@HeaderMap Map<String, String> map, @Body JoinChallengeReq joinChallengeReq);

    @Streaming
    @GET("/legal/download")
    Call<ResponseBody> legalFileDownload(@HeaderMap Map<String, String> map, @Query("type") String str, @Query("version") String str2);

    @POST
    Call<Void> logTappyError(@HeaderMap Map<String, String> map, @Url String str, @Body SErrorLogRequest sErrorLogRequest);

    @POST("eg/auth/token")
    Call<SEGLoginResponse> login(@HeaderMap Map<String, String> map, @Body SEGLoginRequest sEGLoginRequest);

    @POST("eg/auth/revoke")
    Call<SEgLogoutResponse> logout(@HeaderMap Map<String, String> map, @Body SEgLogoutRequest sEgLogoutRequest);

    @POST("fitness/log")
    Call<ActivityRes> postActivityDataToServer(@HeaderMap Map<String, String> map, @Body ActivityData activityData);

    @POST("fitness/data")
    Call<ActivityRes> postAmbientSoundDataToServer(@HeaderMap Map<String, String> map, @Body SSaveAmbientSoundDataReq sSaveAmbientSoundDataReq);

    @POST("app/referral")
    Call<SPostAppRefererDataRes> postApprefererData(@HeaderMap Map<String, String> map, @Body SPostAppRefererDataReq sPostAppRefererDataReq);

    @POST("fitness/data")
    Call<ActivityRes> postBpDataToServer(@HeaderMap Map<String, String> map, @Body SSaveBpDataReq sSaveBpDataReq);

    @POST("user/consent")
    Call<ActivityRes> postConsentDataToServer(@HeaderMap Map<String, String> map, @Body SConsentRequest sConsentRequest);

    @POST("/fitness/data")
    Call<SPostDailyFitnessDataResponse> postDailyFitnessDataToServer(@HeaderMap Map<String, String> map, @Body PostDailyFitnessDataRequest postDailyFitnessDataRequest);

    @POST("eg/fitness/log")
    Call<ActivityRes> postEgActivityDataToServer(@HeaderMap Map<String, String> map, @Body ActivityData activityData);

    @POST("eg/fitness/data")
    Call<ActivityRes> postEgAmbientSoundDataToServer(@HeaderMap Map<String, String> map, @Body SSaveAmbientSoundDataReq sSaveAmbientSoundDataReq);

    @POST("eg/fitness/data")
    Call<ActivityRes> postEgBpDataToServer(@HeaderMap Map<String, String> map, @Body SSaveBpDataReq sSaveBpDataReq);

    @POST("eg/fitness/data")
    Call<SPostDailyFitnessDataResponse> postEgDailyFitnessDataToServer(@HeaderMap Map<String, String> map, @Body PostDailyFitnessDataRequest postDailyFitnessDataRequest);

    @POST("eg/iot/user/heartbeat")
    Call<GenericResponse> postEgDeviceHeartBeat(@HeaderMap Map<String, String> map, @Body SUpdateEgDeviceHeartbeat sUpdateEgDeviceHeartbeat);

    @POST("eg/iot/heartbeat")
    Call<GenericResponse> postEgHeartBeatInfo(@HeaderMap Map<String, String> map, @Body SUpdateEgHeartBeat sUpdateEgHeartBeat);

    @POST("eg/fitness/data")
    Call<ActivityRes> postEgHeartRateDataToServer(@HeaderMap Map<String, String> map, @Body SSaveHeartRateDataReq sSaveHeartRateDataReq);

    @POST("eg/fitness/data")
    Call<ActivityRes> postEgHrvDataToServer(@HeaderMap Map<String, String> map, @Body SSaveHrvDataReq sSaveHrvDataReq);

    @POST("eg/fitness/data")
    Call<ActivityRes> postEgTemperatureDataToServer(@HeaderMap Map<String, String> map, @Body SSaveTemperatureDataReq sSaveTemperatureDataReq);

    @POST("user/feedback")
    Call<JSONObject> postFeedbackForm(@HeaderMap Map<String, String> map, @Body SPostFeedbackToServer sPostFeedbackToServer);

    @POST("/fitness/challenge/stats")
    Call<CommonResponseClass> postFitnessChallenge(@HeaderMap Map<String, String> map, @Body FitnessChallengeStatsReq fitnessChallengeStatsReq);

    @POST("/fitness/data/aggregate")
    Call<SPostDailyFitnessDataResponse> postFitnessDataAggregate(@HeaderMap Map<String, String> map, @Body PostDailyDataAggregate postDailyDataAggregate);

    @POST("guest/user/session")
    Call<GuestUserSessionDataRes> postGuestUserSession(@HeaderMap Map<String, String> map, @Body FCMTokenDataReq fCMTokenDataReq);

    @POST("temp/user/liveData")
    Call<JSONObject> postHeartRateAndBp(@HeaderMap Map<String, String> map, @Body SPostHeartRateAndBP sPostHeartRateAndBP);

    @POST("fitness/data")
    Call<ActivityRes> postHeartRateDataToServer(@HeaderMap Map<String, String> map, @Body SSaveHeartRateDataReq sSaveHeartRateDataReq);

    @POST("fitness/data")
    Call<ActivityRes> postHrvDataToServer(@HeaderMap Map<String, String> map, @Body SSaveHrvDataReq sSaveHrvDataReq);

    @PUT
    Call<SPutPostPersoCommandsExecutedResponse> postPersoCommandsExecuted(@HeaderMap Map<String, String> map, @Url String str);

    @POST("fitness/data")
    Call<ActivityRes> postSedentaryAlertsDataToServer(@HeaderMap Map<String, String> map, @Body SSaveSedentaryAlertsDataReq sSaveSedentaryAlertsDataReq);

    @POST("fitness/activitySession")
    Call<SActivitySessionDataResponse> postSessionDataToServer(@HeaderMap Map<String, String> map, @Body PostActivitySessionDataRequest postActivitySessionDataRequest);

    @POST("fitness/session")
    Call<SPostSessionDataResponse> postSessionDataToServer(@HeaderMap Map<String, String> map, @Body PostSessionDataRequest postSessionDataRequest);

    @POST("/user/sos/event")
    Call<CommonResponseClass> postSosEvent(@HeaderMap Map<String, String> map, @Body SosEventReq sosEventReq);

    @POST("fitness/data")
    Call<ActivityRes> postSpo2DataToServer(@HeaderMap Map<String, String> map, @Body SSaveSpo2DataReq sSaveSpo2DataReq);

    @POST("/sport/userPrefs?apiRevision=1")
    Call<SGetSportsUserPrefRes> postSportsUserPreference(@HeaderMap Map<String, String> map, @Body SportsUserPrefRequest sportsUserPrefRequest);

    @POST("fitness/data")
    Call<ActivityRes> postStressDataToServer(@HeaderMap Map<String, String> map, @Body SSavePeriodicStressDataReq sSavePeriodicStressDataReq);

    @POST("fitness/data")
    Call<ActivityRes> postTemperatureDataToServer(@HeaderMap Map<String, String> map, @Body SSaveTemperatureDataReq sSaveTemperatureDataReq);

    @POST("/user/app/rating")
    Call<SPostRatingRes> postUserRatingFeedback(@HeaderMap Map<String, String> map, @Body SPostRatingReq sPostRatingReq);

    @PUT
    Call<Void> putStepUpOptions(@HeaderMap Map<String, String> map, @Url String str, @Body SPutStepUpOptionsRequest sPutStepUpOptionsRequest);

    @POST("eg/auth/token")
    Call<SRefreshTokenResponse> refreshAccessToken(@HeaderMap Map<String, String> map, @Body SRefreshTokenRequest sRefreshTokenRequest);

    @POST("user/{userId}/register/returning")
    @Multipart
    Call<SRegisterReturningUserModel> registerExistingUser(@HeaderMap Map<String, String> map, @Path("userId") String str, @Part("updation") RequestBody requestBody);

    @POST("user/{userId}/register/returning")
    @Multipart
    Call<SRegisterReturningUserModel> registerExistingUser(@HeaderMap Map<String, String> map, @Path("userId") String str, @Part("updation") RequestBody requestBody, @Part MultipartBody.Part part);

    @POST
    Call<SDeviceInfo> registerNewTappyDevice(@HeaderMap Map<String, String> map, @Url String str, @Body SDeviceInfo sDeviceInfo);

    @POST
    Call<SRegisteredProduct> registerNewTappyProduct(@HeaderMap Map<String, String> map, @Url String str, @Body SRegisterProductRequest sRegisterProductRequest);

    @POST
    Call<SUserDataResponse> registerNewTappyUser(@HeaderMap Map<String, String> map, @Url String str, @Body SRegisterUserRequest sRegisterUserRequest);

    @POST
    Call<CommonResponseGeneric<SUserDataResponseNew>> registerNewTappyUserNew(@HeaderMap Map<String, String> map, @Url String str);

    @POST("users")
    @Multipart
    Call<SRegisterNewUserModel> registerNewUser(@HeaderMap Map<String, String> map, @Part("registration") RequestBody requestBody, @Part MultipartBody.Part part);

    @POST("relation/guardian/request/{requestId}/reinvite")
    Call<SHealthBuddyResponse> reinviteHealthBuddy(@HeaderMap Map<String, String> map, @Path("requestId") int i);

    @DELETE("relation/guardian/{buddyId}")
    Call<SHealthBuddyResponse> removeHealthBuddyRequest(@HeaderMap Map<String, String> map, @Path("buddyId") int i);

    @HTTP(hasBody = true, method = "DELETE", path = "/fitness/challenge/participants")
    Call<CommonResponseGeneric<CreateFitnessChallengeRes>> removeParticipants(@HeaderMap Map<String, String> map, @Body RemoveParticipantsReq removeParticipantsReq);

    @DELETE("user/dp")
    Call<SRemoveProfilePicture> removeProfilePicture(@HeaderMap Map<String, String> map);

    @DELETE("removeUser")
    Call<SRemoveUserModel> removeUser(@HeaderMap Map<String, String> map);

    @POST("auth/feedback")
    Call<JSONObject> reportIssue(@HeaderMap Map<String, String> map, @Body SPostReportIssue sPostReportIssue);

    @PUT
    Call<SResumePaymentInstrumentTokensResponse> resumePaymentInstrumentToken(@HeaderMap Map<String, String> map, @Url String str, @Body SResumePaymentInstrumentTokensRequest sResumePaymentInstrumentTokensRequest);

    @POST("eg/fitness/live")
    Call<JSONObject> saveEgLiveHealthData(@HeaderMap Map<String, String> map, @Body LiveHealthDataModel liveHealthDataModel);

    @POST("eg/iot/user/nearby")
    Call<NearByDeviceRes> saveEgNearByDevice(@HeaderMap Map<String, String> map, @Body NearByDeviceReq nearByDeviceReq);

    @POST("eg/fitness/data")
    Call<JsonObject> saveEgSleepDataV2(@HeaderMap Map<String, String> map, @Body SSaveSleepData sSaveSleepData);

    @POST("/user/place/favs")
    Call<CommonResponseGeneric<SaveFavouritePlaceRes>> saveFavouritePlace(@HeaderMap Map<String, String> map, @Body FavouritePlace favouritePlace);

    @POST("user/questionnaire/answers")
    Call<JsonObject> saveFeedbackDataToServer(@HeaderMap Map<String, String> map, @Body SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest);

    @POST("/fitness/data/computed")
    Call<JsonObject> saveFitnessComputedData(@HeaderMap Map<String, String> map, @Body SaveFitnessComputedDataRequest saveFitnessComputedDataRequest);

    @POST("fitness/config")
    Call<FitnessConfigResponse> saveFitnessConfigData(@HeaderMap Map<String, String> map, @Body FitnessConfigRequest fitnessConfigRequest);

    @POST("/fitness/records")
    Call<JsonObject> saveFitnessRecords(@HeaderMap Map<String, String> map, @Body SSaveFitnessRecordReq sSaveFitnessRecordReq);

    @POST("fitness/data/session")
    Call<FitnessConfigResponse> saveFitnessSessionData(@HeaderMap Map<String, String> map, @Body PostECGSessionData postECGSessionData);

    @POST("iot/user/device")
    Call<SIOTUserDeviceResponseModel> saveIotDevice(@HeaderMap Map<String, String> map, @Body SIOTUserDevicesRequestModel sIOTUserDevicesRequestModel);

    @POST("/iot/user/heartbeat")
    Call<JsonObject> saveIotHeartBeatData(@HeaderMap Map<String, String> map, @Body SSaveHeartBeatData sSaveHeartBeatData);

    @POST("/fitness/live")
    Call<JSONObject> saveLiveHealthData(@HeaderMap Map<String, String> map, @Body LiveHealthDataModel liveHealthDataModel);

    @POST("user/baseLocation")
    Call<AddressModel> saveLocation(@HeaderMap Map<String, String> map, @Body AddressReq addressReq);

    @POST("user/location/breach")
    Call<JsonObject> saveLocationBreaches(@HeaderMap Map<String, String> map, @Body SSaveLocationBreachInfo sSaveLocationBreachInfo);

    @POST("/fitness/data/session")
    Call<JsonObject> saveManualData(@HeaderMap Map<String, String> map, @Body SSaveManualData sSaveManualData);

    @POST("/fitness/data/session")
    Call<JsonObject> saveManualMixedData(@HeaderMap Map<String, String> map, @Body SManualMixedData sManualMixedData);

    @POST("/fitness/data/session")
    Call<JsonObject> saveManualSPO2Data(@HeaderMap Map<String, String> map, @Body SSaveManualSPO2Data sSaveManualSPO2Data);

    @POST("/fitness/records")
    Call<JsonObject> saveMensesSymptomRecords(@HeaderMap Map<String, String> map, @Body SSaveMensurationFitnessRecordReq sSaveMensurationFitnessRecordReq);

    @POST("iot/user/nearby")
    Call<NearByDeviceRes> saveNearByDevice(@HeaderMap Map<String, String> map, @Body NearByDeviceReq nearByDeviceReq);

    @POST("fitness/data")
    Call<ActivityRes> saveNewHRVDataToServer(@HeaderMap Map<String, String> map, @Body SNewSaveHrvDataReq sNewSaveHrvDataReq);

    @POST("/user/device/qrcode")
    Call<CommonResponseGeneric<QRTraySaveRes>> saveQRTrayCode(@HeaderMap Map<String, String> map, @Body QRTraySaveReq qRTraySaveReq);

    @POST("fitness/data")
    Call<JsonObject> saveSleepDataV2(@HeaderMap Map<String, String> map, @Body SSaveSleepData sSaveSleepData);

    @POST("timeline/feed")
    Call<STimelineCardSaveResponse> saveTimeLineData(@HeaderMap Map<String, String> map, @Body SCardItemUploadBean sCardItemUploadBean);

    @POST("app/user/setting")
    Call<JSONObject> saveUserAppSettings(@HeaderMap Map<String, String> map, @Body SUserAppSettingsReq sUserAppSettingsReq);

    @POST("/user/device/setting")
    Call<JSONObject> saveUserDeviceSettings(@HeaderMap Map<String, String> map, @Body SUserDeviceSettingsReq sUserDeviceSettingsReq);

    @POST("/user/place/trips")
    Call<CommonResponseGeneric<SavePlaceTrackHistoryRes>> saveUserNavigationTrip(@HeaderMap Map<String, String> map, @Body FavouritePlace favouritePlace);

    @POST("user/coins/gift")
    Call<CoinsDataRequestResponse> sendCoinsData(@HeaderMap Map<String, String> map, @Body CoinsDataRequest coinsDataRequest);

    @POST
    Call<SDrowsyEstimationPostRes> sendDrowsyEstimation(@HeaderMap Map<String, String> map, @Url String str, @Body DrowsyEstimationReq drowsyEstimationReq);

    @POST
    Call<JsonObject> sendDrowsyEstimationFeedback(@HeaderMap Map<String, String> map, @Url String str, @Body SDrowbsinessFeedbackReq sDrowbsinessFeedbackReq);

    @POST
    Call<SDrowsyFatigueStressDataPostRes> sendDrowsyFatigueStressData(@HeaderMap Map<String, String> map, @Url String str, @Body DrowsyFatigueStressDataReq drowsyFatigueStressDataReq);

    @POST("fitness/buddy/request")
    Call<SFitnessBuddiesInviteResponse> sendFitnessBuddyRequest(@HeaderMap Map<String, String> map, @Body SendFitnessBuddyRequest sendFitnessBuddyRequest);

    @POST("relation/guardian/request")
    Call<SGetHealthBuddiesResponse> sendHealthBuddyRequest(@HeaderMap Map<String, String> map, @Body HealthBuddyRequest healthBuddyRequest);

    @POST
    Call<SPaymentInstrumentTokensResponse> sendPaymentInstrumentTokenByUserId(@HeaderMap Map<String, String> map, @Url String str, @Body SSendPaymentInstrumentTokensRequest sSendPaymentInstrumentTokensRequest);

    @POST
    Call<RRateResponse> sendRRateEstimation(@HeaderMap Map<String, String> map, @Url String str, @Body RRateTrainingRequest rRateTrainingRequest);

    @POST("fitness/goal/{goalId}/cheer")
    Call<ResponseBody> sendReaction(@HeaderMap Map<String, String> map, @Path("goalId") Integer num, @Body SendReactionRequest sendReactionRequest);

    @POST
    Call<SSPO2EstimationPostRes> sendSPO2Estimation(@HeaderMap Map<String, String> map, @Url String str, @Body SPO2EstimationReq sPO2EstimationReq);

    @POST("software/update?apiRevision=1")
    Call<SoftwareUpdateRes> softwareUpdate(@HeaderMap Map<String, String> map, @Body SSoftwareUpdateReq sSoftwareUpdateReq);

    @POST("/msg/subscribe")
    Call<WeekReportSubscriptionResponse> subscribeForFitnessWeeklyReport(@HeaderMap Map<String, String> map, @Body WeeklyReportSubscriptionReq weeklyReportSubscriptionReq);

    @PUT
    Call<SSuspendPaymentInstrumentTokensResponse> suspendPaymentInstrumentToken(@HeaderMap Map<String, String> map, @Url String str, @Body SSuspendPaymentInstrumentTokensRequest sSuspendPaymentInstrumentTokensRequest);

    @DELETE("fitness/buddy/{buddyId}")
    Call<ResponseBody> unfriendBuddy(@HeaderMap Map<String, String> map, @Path("buddyId") int i);

    @POST("/msg/unsubscribe")
    Call<JsonObject> unsubscribeFromFitnessWeeklyReport(@HeaderMap Map<String, String> map, @Body WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq);

    @POST("user/dp")
    @Multipart
    Call<SUpdateProfilePicModel> updateDp(@HeaderMap Map<String, String> map, @Part MultipartBody.Part part);

    @PUT("eg/info")
    Call<GenericResponse> updateEgInfo(@HeaderMap Map<String, String> map, @Body SUpdateEgInfo sUpdateEgInfo);

    @PUT("eg/session")
    Call<GenericResponse> updateEgSession(@HeaderMap Map<String, String> map, @Body SUpdateEgSession sUpdateEgSession);

    @PUT("fitness/goal/{goalId}")
    Call<SUpdateFitnessGoalResponse> updateFitnessGoal(@HeaderMap Map<String, String> map, @Path("goalId") int i, @Body UpdateFitnessGoalRequest updateFitnessGoalRequest);

    @PUT("fitness/plan/progress")
    Call<CommonResponseClass> updatePlanProgress(@HeaderMap Map<String, String> map, @Body FitnessPlanProgressReq fitnessPlanProgressReq);

    @PUT("fitness/plan/progress")
    Call<JSONObject> updatePlanProgress(@HeaderMap Map<String, String> map, @Body SPlanProgressUpdateReq sPlanProgressUpdateReq);

    @PUT
    Call<SRegisteredProduct> updateRegisteredProductFriendlyName(@HeaderMap Map<String, String> map, @Url String str, @Body SUpdateRegisteredProductFriendlyNameRequest sUpdateRegisteredProductFriendlyNameRequest);

    @PUT("timeline/feed/{logid}")
    Call<JSONObject> updateTimeLineDetails(@HeaderMap Map<String, String> map, @Body CardItemsBean cardItemsBean, @Path("logid") String str);

    @PUT("user")
    Call<SUpdateUserModel> updateUser(@HeaderMap Map<String, String> map, @Body UpdateUserReq updateUserReq);

    @POST("updatelocation")
    Call<JSONObject> updateUserLocation(@HeaderMap Map<String, String> map, @Body SUpdateLocationApiReq sUpdateLocationApiReq);

    @PUT("updateUserPhoneNo")
    Call<JSONObject> updateUserMobileNumber(@HeaderMap Map<String, String> map, @Body SModifyPhoneNumberReq sModifyPhoneNumberReq);

    @POST("user/media")
    @Multipart
    Call<SMediaUploadResponse> uploadImage(@HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @Part("mediaClass") RequestBody requestBody);

    @POST("user/media")
    @Multipart
    Call<SMediaUploadResponse> uploadImage(@HeaderMap Map<String, String> map, @Part MultipartBody.Part part, @Part("mediaClass") RequestBody requestBody, @Part("placeId") RequestBody requestBody2);

    @PUT("usersgcmupdate/{userId}")
    Call<SUpdateRegistrationTokenModel> uploadRegIdToServer(@HeaderMap Map<String, String> map, @Path("userId") String str, @Body FCMRegistrationReq fCMRegistrationReq);

    @POST
    Call<SValidateOTPResponse> validateOtp(@HeaderMap Map<String, String> map, @Url String str, @Body SValidateOTPRequest sValidateOTPRequest);

    @POST("/user/profile/verify")
    Call<GenerateVerifyOtpRes> verifyEmailOtp(@HeaderMap Map<String, String> map, @Body VerifyOtpReq verifyOtpReq);

    @GET("phoneNoVerification/{phoneNumber}")
    Call<SPhoneNumberVerificationModel> verifyPhoneNumber(@HeaderMap Map<String, String> map, @Path("phoneNumber") String str);

    @POST("auth/otp")
    Call<SPhoneNumberVerificationModel> verifyPhoneNumberCustomeOtpLength(@HeaderMap Map<String, String> map, @Body JsonObject jsonObject);

    @POST("v2/auth/otp")
    Call<SPhoneNumberVerificationModel> verifyPhoneNumberCustomeOtpLengthV2(@HeaderMap Map<String, String> map, @Body JsonObject jsonObject);
}
