package com.example.brainrun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ben extends AppCompatActivity {

    private static String data = "hello";
    private static String main = "1";
    private static String data1 = "0";
    private static String data2 = "0";
    private static String data3 = "0";
    private static String data4 = "0";
    private static String data5 = "0";

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;

    int count1=0 ; String num1 = "0";
    int stopPosition1, stopPosition2;
    boolean continueThread=true;
    Thread t;int ans =0; private Button b1,b2,sub;
    public RadioButton m1,m2,m3;
    private LinearLayout cardView1,cardView2,cardView3;
    private String type;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ben.super.onBackPressed();
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben2);

        getSupportActionBar().hide();

        type = getIntent().getStringExtra("type");

        data = "0";
        Log.d("testing","Thread0 "+ data );

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        m1 = findViewById(R.id.radioButton1);
        m2 = findViewById(R.id.radioButton2);
        m3 = findViewById(R.id.radioButton3);

        b1 = findViewById(R.id.button6);
        b2 = findViewById(R.id.button5);
        sub = findViewById(R.id.button2);

        cardView1= findViewById(R.id.cardView);
        cardView2= findViewById(R.id.cardView1);
        cardView3= findViewById(R.id.cardView2);

        VideoView videoView1 = findViewById(R.id.videoView);
        String videoPath1 = "android.resource://" + getPackageName()+ "/" + R.raw.d1;
        Uri uri1 = Uri.parse(videoPath1);
        videoView1.setVideoURI(uri1);
        videoView1.start();

        MediaController mediaController1 = new MediaController(this);
        videoView1.setMediaController(mediaController1);
        mediaController1.setAnchorView(videoView1);

        VideoView videoView2 = findViewById(R.id.videoView1);
        String videoPath2 = "android.resource://" + getPackageName()+ "/" + R.raw.d2;
        Uri uri2 = Uri.parse(videoPath2);
        videoView2.setVideoURI(uri2);
//        videoView2.start();
        MediaController mediaController2 = new MediaController(this);
        videoView2.setMediaController(mediaController2);
        mediaController2.setAnchorView(videoView2);

        VideoView videoView3 = findViewById(R.id.videoView2);
        String videoPath3 = "android.resource://" + getPackageName()+ "/" + R.raw.d3;
        Uri uri3 = Uri.parse(videoPath3);
        videoView3.setVideoURI(uri3);
//        videoView3.start();
        MediaController mediaController3 = new MediaController(this);
        videoView3.setMediaController(mediaController3);
        mediaController3.setAnchorView(videoView3);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardView1.getVisibility() == View.VISIBLE){
                    //b1.setVisibility(View.VISIBLE);
                    b1.setText("Previous");
                    stopPosition1 = videoView1.getCurrentPosition();
                    videoView1.pause();
                    cardView1.setVisibility(View.GONE);
                    cardView2.setVisibility(View.VISIBLE);
                    videoView2.start();

                }
                else if(cardView2.getVisibility() == View.VISIBLE){
                    b2.setVisibility(View.INVISIBLE);
                    if(sub.getVisibility()== View.INVISIBLE){
                        sub.setVisibility(View.VISIBLE);
                    }
                    stopPosition2 = videoView2.getCurrentPosition();
                    videoView2.pause();
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.VISIBLE);
                    videoView3.start();
                }
                else {
                    int k1=0;
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cardView1.getVisibility()==View.VISIBLE){
                    moveBack();
                }

                if(cardView2.getVisibility() == View.VISIBLE){
                    //b1.setVisibility(View.INVISIBLE);
                    b1.setText("Back");
                    videoView2.pause();
                    cardView2.setVisibility(View.GONE);
                    cardView1.setVisibility(View.VISIBLE);
                    videoView1.seekTo(stopPosition1);
                    videoView1.start();
                }
                else if(cardView3.getVisibility() == View.VISIBLE){
                    b2.setVisibility(View.VISIBLE);
                    videoView3.pause();
                    cardView3.setVisibility(View.GONE);
                    cardView2.setVisibility(View.VISIBLE);
                    videoView2.seekTo(stopPosition2);
                    videoView2.start();
                    sub.setVisibility(View.INVISIBLE);
                }
                else {
                    int k1=0;
                }

            }
        });

        final TextView textView=(TextView)findViewById(R.id.timer);
        t=new Thread(){

            @Override
            public void run(){

                while(continueThread){

                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                ans++;
                                count1++;
                                //if(ans>=9){ count1++; textView.setVisibility(View.VISIBLE); btn.setVisibility(View.VISIBLE); }

                                textView.setText(String.valueOf(count1));
                                data = String.valueOf(count1);

                                Log.d("testing","Thread"+ data );
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        t.start();


    }

    private void moveBack() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        ben.super.onBackPressed();
                        setResult(Activity.RESULT_CANCELED);
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void sub (View view){
        continueThread=false;
        if (m1.isChecked()){ data1 = "1";}
        else { data1 = "0";}
        if (m2.isChecked()){ data2 = "1";}
        else { data2 = "0";}
        if (m3.isChecked()){ data3 = "1";}
        else { data3 = "0";}

        float t=100;          // Threshold time
        int a1,a2,a3;                                           // answers
        int r;                                         // time taken to solve the quest.
        int m1 = 30, m2 =30, m3 = 40;                           // Marks
        int inf;

        a1 = Integer.parseInt(data1);
        a2 = Integer.parseInt(data2);
        a3 = Integer.parseInt(data3);
        r = count1;

        if(r <= t) {r = (int)t;}

        inf = (int)((a1*m1 + a2*m2 + a3*m3)*(t/r));

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference1 = fstore.collection("users").document(userID).collection("What's Next").document(userID);

        Map<String,Object> user1 = new HashMap<>();
        user1.put("Dane's Drawing",inf);

        documentReference1.set(user1, SetOptions.merge());



//        if (m4.isChecked()){ data4 = "1";}
//        else { data4 = "0";}
//        if (m5.isChecked()){ data5 = "1";}
//        else { data5 = "0";}
        SharedPreferences shrd = getSharedPreferences("Interpretation",MODE_PRIVATE);
        SharedPreferences.Editor shared = shrd.edit();

        shared.putString("time", getData());
        shared.putString("status1", getData1());
        shared.putString("status2", getData2());
        shared.putString("status3", getData3());
        shared.putString("type", type);
        shared.putBoolean("success", true);
        shared.apply();

        setResult(Activity.RESULT_OK);
        finish();
//        Intent intent = new Intent(ben.this, bscore3.class);
//        startActivity(intent);
    }

    public void move1 (View view){
        int z =0;//move front
//        if(cardView1.getVisibility() == View.VISIBLE){
//            b1.setVisibility(View.VISIBLE);
//            cardView1.setVisibility(View.GONE);
//            cardView2.setVisibility(View.VISIBLE);
//
//        }
//        else if(cardView2.getVisibility() == View.VISIBLE){
//            b2.setVisibility(View.INVISIBLE);
//            cardView2.setVisibility(View.GONE);
//            cardView3.setVisibility(View.VISIBLE);
//        }
////        else if(cardView3.getVisibility() == View.VISIBLE){
////            cardView3.setVisibility(View.GONE);
////            cardView4.setVisibility(View.VISIBLE);
////        }
////        else if(cardView4.getVisibility() == View.VISIBLE){
////            cardView4.setVisibility(View.GONE);
////            cardView5.setVisibility(View.VISIBLE);
////        }
//        else {
//            int k1=0;
//        }
    }

    public void move (View view){

        int n1 =0;
//        if(cardView2.getVisibility() == View.VISIBLE){
//            b1.setVisibility(View.INVISIBLE);
//            cardView2.setVisibility(View.GONE);
//            cardView1.setVisibility(View.VISIBLE);
//        }
//        else if(cardView3.getVisibility() == View.VISIBLE){
//            b2.setVisibility(View.VISIBLE);
//            cardView3.setVisibility(View.GONE);
//            cardView2.setVisibility(View.VISIBLE);
//        }
////        else if(cardView4.getVisibility() == View.VISIBLE){
////            cardView4.setVisibility(View.GONE);
////            cardView3.setVisibility(View.VISIBLE);
////        }
////        else if(cardView5.getVisibility() == View.VISIBLE){
////            cardView5.setVisibility(View.GONE);
////            cardView4.setVisibility(View.VISIBLE);
////        }
//        else {
//            int k1=0;
//        }
    }

    public static String getData(){ return data; }
    public static String getData11(){ return main; }
    public static String getData1(){ return data1; }
    public static String getData2(){ return data2; }
    public static String getData3(){ return data3; }
    public static String getData4(){ return data4; }
    public static String getData5(){ return data5; }

}