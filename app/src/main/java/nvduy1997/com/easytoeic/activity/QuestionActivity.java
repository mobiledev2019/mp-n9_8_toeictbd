package nvduy1997.com.easytoeic.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.adapter.MyPagerAdapter;
import nvduy1997.com.easytoeic.adapter.QuestionTestAdapter;
import nvduy1997.com.easytoeic.fragment.QuestionTestFragment;
import nvduy1997.com.easytoeic.fragment.ScoreQuestionFragment;


public class QuestionActivity extends AppCompatActivity {
//Bây giơông
    private TextView tvTime;
    QuestionTestFragment questionTestFragment = new QuestionTestFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ViewPager my_Pager_Content = findViewById(R.id.my_pager_content_question);
        TabLayout my_Tablayout = findViewById(R.id.tab_layout);
        my_Tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.design_default_color_primary_dark));
        my_Tablayout.setupWithViewPager(my_Pager_Content);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pagerAdapter.AddFragmentPager(questionTestFragment,"Question");
        pagerAdapter.AddFragmentPager(new ScoreQuestionFragment(),"Score");
        my_Pager_Content.setAdapter(pagerAdapter);

        Intent intent = getIntent();
        if (intent != null){
            int a = intent.getIntExtra("KEY",0);
            Bundle bundle = new Bundle();
            bundle.putInt("IDTEST",a);
            questionTestFragment.setArguments(bundle);
        }

        initializeComponents();

        CountTime countTime = new CountTime(900000, 1000);
        countTime.start();


    }

    private void initializeComponents() {
        tvTime = findViewById(R.id.tvTimer);

        TextView tvKiemTra = findViewById(R.id.tvKiemTra);
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    public class CountTime extends CountDownTimer {


        public CountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            @SuppressLint("DefaultLocale") String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTime.setText(countTime);

        }

        @Override
        public void onFinish() {
            tvTime.setText("00:00");
        }
    }


}
