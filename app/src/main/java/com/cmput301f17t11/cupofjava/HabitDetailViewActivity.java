/* HabitDetailViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Opens activity which lets user view the details of a certain habit.
 *
 * @see Habit
 * @version 1.0
 */
public class HabitDetailViewActivity extends AppCompatActivity {

    private TextView habitTitleTextView;
    private TextView habitReasonTextView;
    private TextView habitDateTextView;
    private String habitDateCalendar;
    private ListView habitEventList;
    private String userName;
    private int userIndex;
    private int habitIndex;
    private Habit habit;

    /**
     * This method is called when HabitDetailViewActivity is instantiated.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see SaveFileController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail_view);

        final Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);
        this.habitIndex = intent.getIntExtra("habitIndex", 0);

        habitTitleTextView = (TextView) findViewById(R.id.title_text_view);
        habitReasonTextView = (TextView) findViewById(R.id.reason_text_view);
        habitDateTextView = (TextView) findViewById(R.id.date_added_text_view);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

        SaveFileController saveFileController = new SaveFileController();
        this.habit = saveFileController.getHabit(getApplicationContext(), userIndex, habitIndex);

        habitTitleTextView.setText(("What: "+ habit.getHabitTitle()));
        habitReasonTextView.setText(("Why: "+ habit.getHabitReason()));
        habitDateTextView.setText(("Start date: " + sdf.format(habit.getHabitStartDate().getTime())));
    }

    /**
     * Opens the new habit event activity page so that the user
     * can create new habit event.
     *
     * @param view instance of View
     * @see NewHabitEventActivity
     */
    public void addNewHabitEventButton(View view) {
        //todo: new habit event
        Intent intent2 = new Intent(HabitDetailViewActivity.this, NewHabitEventActivity.class);
        intent2.putExtra("userName", userName);
        intent2.putExtra("userIndex", userIndex);
        intent2.putExtra("habitIndex", habitIndex);
        startActivity(intent2);
    }

    /**
     * Button implementation to delete current habit.
     *
     * @param view instance of View
     * @see TodayViewActivity
     */
    public void deleteCurrentHabitButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HabitDetailViewActivity.this);
        builder.setTitle("Edit record")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveFileController saveFileController = new SaveFileController();
                        saveFileController.deleteHabit(getApplicationContext(), userIndex, habitIndex);
                        Intent intent3 = new Intent(HabitDetailViewActivity.this, TodayViewActivity.class);
                        intent3.putExtra("userName", userName);
                        intent3.putExtra("userIndex", userIndex);
                        startActivity(intent3);
                    }
                })
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
