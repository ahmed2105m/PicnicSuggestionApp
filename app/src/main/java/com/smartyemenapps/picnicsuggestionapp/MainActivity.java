package com.smartyemenapps.picnicsuggestionapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Views
    private ImageView placeImageView;
    private TextView txtPlace, txtActivity;
    private static final String BUNDLE_INDEX_OF_PLACE = "place";
    private static final String BUNDLE_INDEX_OF_ACTIVITY = "activity";

    /*
     *   local Var
     *   mIndexOfPlace to the images array
     *   mIndexOfActivity القيمة الابتدائية للبعد الثاني في مصفوفة activitiesArr
     * */
    int mIndexOfPlace = 0;
    int mIndexOfActivity = 0;

    int[] imageArr = {
            R.drawable.hawf,
            R.drawable.qsery,
            R.drawable.soqtra,
            R.drawable.shibam,
            R.drawable.swimming,
            R.drawable.running
    };
    int[] placeNameArr = {
            R.string.place1,
            R.string.place2,
            R.string.place3,
            R.string.place4,
            R.string.place5,
            R.string.place6
    };
    int[][] activitiesArr = {
            {R.string.activity11, R.string.activity12},
            {R.string.activity21, R.string.activity22},
            {R.string.activity31, R.string.activity32},
            {R.string.activity41, R.string.activity42},
            {R.string.activity51, R.string.activity52},
            {R.string.activity61, R.string.activity62}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting
        placeImageView = findViewById(R.id.place_imageView);

        Button btnNext = findViewById(R.id.btn_next_place);
        Button btnPrevious = findViewById(R.id.btn_previous_place);
        Button btnChangeActivity = findViewById(R.id.btn_change_activity);
        Button btnPicnicSuggestion = findViewById(R.id.btn_picnic_suggestion);

        txtPlace = findViewById(R.id.txt_place);
        txtActivity = findViewById(R.id.txt_activity);

        displayInfo();

        btnNext.setOnClickListener(v -> {
            ++mIndexOfPlace;
            if (mIndexOfPlace > imageArr.length-1) {
                mIndexOfPlace = imageArr.length-1;
                Toast.makeText(getApplicationContext(),"تم انتهاء النشاطات",Toast.LENGTH_SHORT).show();
            } else
                displayInfo();
        });

        btnPrevious.setOnClickListener(v -> {
            --mIndexOfPlace;
            if (mIndexOfPlace < 0) {
                mIndexOfPlace = 0;
                Toast.makeText(getApplicationContext(),"لا توجد نشاطات",Toast.LENGTH_SHORT).show();
            } else
                displayInfo();
        });

        btnChangeActivity.setOnClickListener(v -> checkUpIndexOfActivity());

        btnPicnicSuggestion.setOnClickListener(v -> {
            Random random = new Random();
            mIndexOfPlace = random.nextInt(imageArr.length-1);
            mIndexOfActivity = random.nextInt(2);
            checkUpIndexOfActivity();
        });
    }

    public void checkUpIndexOfActivity() {
        if (mIndexOfActivity == activitiesArr[mIndexOfPlace].length-1) {
            Log.i("TAG",mIndexOfPlace + " /// " + mIndexOfActivity);
            mIndexOfActivity = 0;
            displayInfo();
        } else {
            Log.i("TAG",mIndexOfPlace + " /// " + mIndexOfActivity);
            mIndexOfActivity++;
            displayInfo();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_INDEX_OF_PLACE,mIndexOfPlace);
        outState.putInt(BUNDLE_INDEX_OF_ACTIVITY,mIndexOfActivity);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mIndexOfPlace = savedInstanceState.getInt(BUNDLE_INDEX_OF_PLACE);
        mIndexOfActivity = savedInstanceState.getInt(BUNDLE_INDEX_OF_ACTIVITY);
        displayInfo();
    }

    public void displayInfo() {
        txtPlace.setText(getString(placeNameArr[mIndexOfPlace]));
        txtActivity.setText(getString(activitiesArr[mIndexOfPlace][mIndexOfActivity]));
        showImage(mIndexOfPlace);
    }

    public void showImage(int index) {
        Drawable setPlaceImage = ContextCompat.getDrawable(this, imageArr[index]);
        placeImageView.setImageDrawable(setPlaceImage);
    }
}