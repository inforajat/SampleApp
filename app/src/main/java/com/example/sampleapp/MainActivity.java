package com.example.sampleapp;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.helpshift.support.ApiConfig;
import com.helpshift.support.Support;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
  private SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Show FAQ button
    Button showfaq = (Button) findViewById(R.id.showfaq);
    showfaq.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Map<String, String[]> cif = new HashMap<>();
        cif.put("joining_date", new String[]{"dt", "1505927361535"});
        cif.put("stock_level", new String[]{"n", "123"});
        cif.put("is_vip", new String[]{"v", "true"});
        cif.put("employee_name", new String[]{"sl", "John Doe"});
        cif.put("employee_address", new String[]{"ml", "Yerwada, Pune"});

        ApiConfig.Builder configBuilder = new ApiConfig.Builder();
        configBuilder.setCustomIssueFields(cif);

        Support.showFAQs(MainActivity.this, configBuilder.build());
      }
  });

    //Show Conversation button
    Button showconversation = (Button) findViewById(R.id.showconversation);
    showconversation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ApiConfig.Builder configBuilder = new ApiConfig.Builder();
        configBuilder.setShowConversationInfoScreen(true);
        Support.showConversation(MainActivity.this,
                                 configBuilder.build()); }
    });

    //Show Single FAQ button
    Button showsinglefaq = (Button) findViewById(R.id.singlefaq);
    showsinglefaq.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ApiConfig.Builder configBuilder = new ApiConfig.Builder();
        configBuilder.setShowConversationInfoScreen(true);
        Support.showSingleFAQ(MainActivity.this,
                              "1005",
                              configBuilder.build()); }
    });
  }
}
