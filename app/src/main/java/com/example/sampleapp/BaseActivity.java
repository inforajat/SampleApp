package com.example.sampleapp;

import android.app.Application;
import android.widget.Toast;
import com.helpshift.Core;
import com.helpshift.HelpshiftUser;
import com.helpshift.InstallConfig;
import com.helpshift.delegate.AuthenticationFailureReason;
import com.helpshift.exceptions.InstallException;
import com.helpshift.support.Log;
import com.helpshift.support.Support;
import java.io.File;

public class BaseActivity extends Application implements Support.Delegate {

  @Override
  public void onCreate() {
    super.onCreate();

    Core.init(Support.getInstance());
    InstallConfig installConfig = new InstallConfig.Builder()
        .setEnableInAppNotification(true)
        .build();
    try {
      Core.install(this,
                   "2a5bd2674746cfeea5acfcba1a1e76e0",
                   "gayatri.helpshift.com",
                   "gayatri_platform_20181018063833353-6d863ba814cb367",
                   installConfig);
    } catch (InstallException e) {
      android.util.Log.e("Helpshift", "install call : ", e);
    }

    android.util.Log.d("Helpshift", Support.libraryVersion + " - is the version for gradle");

    //Set Helpshift Delegate
    Support.setDelegate(this);

  }


  @Override
  public void sessionBegan() {
    Toast.makeText(getApplicationContext(), "Helpshift Session Began", Toast.LENGTH_SHORT);

  }

  @Override
  public void sessionEnded() {
    Toast.makeText(getApplicationContext(), "Helpshift Session Ended", Toast.LENGTH_SHORT);

  }

  @Override
  public void newConversationStarted(String newConversation) {
    Log.d("Helpshift", "New Conversation started" +newConversation );

  }

  @Override
  public void conversationEnded() {
    Log.d("Helpshuft","Conversation ended");

  }

  @Override
  public void userRepliedToConversation(String userMessage) {
    Log.d("Helpshift", "User replied to conversation with: " +userMessage);

  }

  @Override
  public void userCompletedCustomerSatisfactionSurvey(int rating, String feedbackMsg) {
    Log.d("Helpshift", "User replied to Survey with rating: " + rating +" and message : " +feedbackMsg );

  }

  @Override
  public void displayAttachmentFile(File attachedFile) {
    Log.d("Helpshift", "Attached filepath is : "+attachedFile.getAbsolutePath());

  }

  @Override
  public void didReceiveNotification(int notificationCount) {
    Log.d("Helpshift", "Count of received notifications is: " +notificationCount);

  }

  @Override
  public void authenticationFailed(HelpshiftUser helpshiftUser,
                                   AuthenticationFailureReason authenticationFailureReason) {
    Log.d("Helpshift", "Authentication failed for the user, identifier : " + helpshiftUser.getIdentifier() + ", email : " + helpshiftUser.getEmail() + " and reason : " + authenticationFailureReason);

  }
}
