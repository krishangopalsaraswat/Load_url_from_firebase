package com.example.krish.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private WebView web_view;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference reference=firebaseDatabase.getReference();
    private DatabaseReference childRefrence=reference.child("url");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web_view=findViewById(R.id.webView);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        //web_view.getSettings().setLoadWithOverviewMode(true);
        web_view.getSettings().setUseWideViewPort(true);
        web_view.getSettings().setBuiltInZoomControls(true);
    }


    @Override
    protected void onStart() {
        super.onStart();
        childRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String message=dataSnapshot.getValue(String.class);
                web_view.loadUrl(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
       if(web_view.canGoBack()){
           web_view.goBack();
       }
       else {
           super.onBackPressed();
       }
    }
}
