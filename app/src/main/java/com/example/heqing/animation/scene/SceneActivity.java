package com.example.heqing.animation.scene;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.heqing.animation.R;

public class SceneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView textView = (TextView) findViewById(R.id.jump);
        final View img = findViewById(R.id.img);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SceneActivity.this,SecondActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SceneActivity.this,img,"img");
                startActivity(intent,options.toBundle());
            }
        });

    }
}
