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
  public void onCreate(){
    super.onCreate();

    Core.init(Support.getInstance());
    InstallConfig installConfig = new InstallConfig.Builder()
        .setEnableInAppNotification(true)
        .build();

    try {
      Core.install(this,
                   "5d3625c9ee54d8bd42c8e210a92be51e",
                   "gayatri.helpshift.com",
                   "gayatri_platform_20190723091304839-27d7d8537af76b1",
                   installConfig);
    } catch (InstallException e) {
      Log.e("Helpshift", "invalid install credentials : ", e);
    }

    //Delegates
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
