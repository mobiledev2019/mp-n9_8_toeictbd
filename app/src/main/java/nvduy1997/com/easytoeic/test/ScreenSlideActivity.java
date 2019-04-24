package nvduy1997.com.easytoeic.test;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import nvduy1997.com.easytoeic.R;
import nvduy1997.com.easytoeic.activity.QuestionActivity;
import nvduy1997.com.easytoeic.adapter.QuestionTestAdapter;
import nvduy1997.com.easytoeic.model.Question;
import nvduy1997.com.easytoeic.server.APIService;
import nvduy1997.com.easytoeic.server.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private TextView Time;
    private ArrayList<Question> array;
    static Dialog dialog;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private TextView tvKiemTra1;


    int idtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        Time = findViewById(R.id.tvTimer1);
        array = new ArrayList<>();

        Intent intent = getIntent();
        mPager = findViewById(R.id.pager);
        if (intent != null) {
            idtest = intent.getIntExtra("KEY", 0);

            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), idtest);
            mPager.setAdapter(mPagerAdapter);
        }
        tvKiemTra1 = findViewById(R.id.tvKiemTra1);
        tvKiemTra1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllQuestionTest(String.valueOf(idtest));
            }
        });


        CountTime countTime = new CountTime(900000, 1000);
        countTime.start();


    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private int mId;

        public ScreenSlidePagerAdapter(FragmentManager fm, int id) {
            super(fm);
            mId = id;
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position, mId);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

    public class CountTime extends CountDownTimer {


        public CountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            @SuppressLint("DefaultLocale") String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            Time.setText(countTime);

        }

        @Override
        public void onFinish() {
            Time.setText("00:00");
        }
    }


    private void getAllQuestionTest(String id) {
        final DataService dataService = APIService.getService();
        Call<List<Question>> callback = dataService.getQuestionTest(id);
        callback.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                ArrayList<Question> arrayQuestion = (ArrayList<Question>) response.body();

                dialog = new Dialog(ScreenSlideActivity.this);
                dialog.setContentView(R.layout.check_ansewer_dialog);
                dialog.setTitle("Danh sách câu trả lời");
                GirdViewAdapter adapter = new GirdViewAdapter(arrayQuestion, getApplicationContext());
                GridView gridView = dialog.findViewById(R.id.girdView);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mPager.setCurrentItem(position);
                        dialog.dismiss();
                    }
                });
                Button btnCanncel, btnOK;
                btnCanncel = dialog.findViewById(R.id.btn_Cancel);
                btnCanncel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                btnOK = dialog.findViewById(R.id.btn_ok);

                dialog.show();

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("onFailure", "onFailure: " + t.toString());
            }
        });
    }


}