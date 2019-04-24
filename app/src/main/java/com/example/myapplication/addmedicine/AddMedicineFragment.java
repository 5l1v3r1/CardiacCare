package com.example.myapplication.addmedicine;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.alarm.ReminderActivity;
import com.example.myapplication.alarm.ReminderFragment;
import com.example.myapplication.data.source.MedicineAlarm;
import com.example.myapplication.data.source.Pills;
import com.example.myapplication.views.DayViewCheckBox;
import com.example.myapplication.views.RobotoBoldTextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Optional;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;
import static android.content.Context.ALARM_SERVICE;

/**
 * Created by gautam on 12/07/17.
 */

public class AddMedicineFragment extends Fragment implements AddMedicineContract.View {

    public static final String ARGUMENT_EDIT_MEDICINE_ID = "ARGUMENT_EDIT_MEDICINE_ID";

    public static final String ARGUMENT_EDIT_MEDICINE_NAME = "ARGUMENT_EDIT_MEDICINE_NAME";

    @BindView(R.id.edit_med_name)
    EditText editMedName;

    @BindView(R.id.every_day)
    AppCompatCheckBox everyDay;

    @BindView(R.id.dv_sunday)
    DayViewCheckBox dvSunday;

    @BindView(R.id.dv_monday)
    DayViewCheckBox dvMonday;

    @BindView(R.id.dv_tuesday)
    DayViewCheckBox dvTuesday;

    @BindView(R.id.dv_wednesday)
    DayViewCheckBox dvWednesday;

    @BindView(R.id.dv_thursday)
    DayViewCheckBox dvThursday;

    @BindView(R.id.dv_friday)
    DayViewCheckBox dvFriday;

    @BindView(R.id.dv_saturday)
    DayViewCheckBox dvSaturday;

    @BindView(R.id.checkbox_layout)
    LinearLayout checkboxLayout;

    @BindView(R.id.tv_medicine_time1)
    RobotoBoldTextView tvMedicineTime1;

    @BindView(R.id.tv_dose_quantity1)
    EditText tvDoseQuantity1;

    @BindView(R.id.spinner_dose_units1)
    AppCompatSpinner spinnerDoseUnits1;

    @BindView(R.id.tv_medicine_time2)
    RobotoBoldTextView tvMedicineTime2;

    @BindView(R.id.tv_dose_quantity2)
    EditText tvDoseQuantity2;

    @BindView(R.id.spinner_dose_units2)
    AppCompatSpinner spinnerDoseUnits2;

    @BindView(R.id.tv_medicine_time3)
    RobotoBoldTextView tvMedicineTime3;

    @BindView(R.id.tv_dose_quantity3)
    EditText tvDoseQuantity3;

    @BindView(R.id.spinner_dose_units3)
    AppCompatSpinner spinnerDoseUnits3;


    private List<String> doseUnitList;

    private boolean dayOfWeekList[] = new boolean[7];

    private AlarmManager alarmManager;

    private AlarmManager alarmManager2;

    private AlarmManager alarmManager3;

    private PendingIntent operation;

    private int hour1, minute1,hour2, minute2,hour3, minute3;

    Unbinder unbinder;

    private int alarmnumber;

    private AddMedicineContract.Presenter mPresenter;

    private View rootView;

    private String doseUnit1,doseUnit2,doseUnit3;


    public static AddMedicineFragment newInstance() {
        Bundle args = new Bundle();
        AddMedicineFragment fragment = new AddMedicineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(setClickListener);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setCurrentTime1();
        setCurrentTime3();
        setCurrentTime2();
        setSpinnerDoseUnits();

        RadioGroup radioGroup = (RadioGroup) rootView .findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                LinearLayout two = getActivity().findViewById(R.id.two_dose);
                LinearLayout three = getActivity().findViewById(R.id.three_dose);
                switch(checkedId) {
                    case R.id.one:
                        two.setVisibility(View.GONE);
                        three.setVisibility(View.GONE);
                        alarmnumber = 1;
                        break;
                    case R.id.two:
                        two.setVisibility(View.VISIBLE);
                        three.setVisibility(View.GONE);
                        alarmnumber = 2;
                        break;
                    case R.id.three:
                        two.setVisibility(View.VISIBLE);
                        three.setVisibility(View.VISIBLE);
                        alarmnumber = 3;
                        break;


                }
            }
        });


        return rootView;
    }

    /*
        public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();
            RadioButton two = getActivity().findViewById(R.id.two_dose);
            RadioButton three = getActivity().findViewById(R.id.three_dose);
            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.one:
                    if (checked)
                        two.setVisibility(View.GONE);
                        three.setVisibility(View.GONE);
                    break;

                case R.id.two:
                    if (checked)
                        three.setVisibility(View.GONE);
                    break;

                case R.id.three:
                    if (checked)
                    break;


            }
        }
    */
    @Override
    public void setPresenter(AddMedicineContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showEmptyMedicineError() {
        // Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMedicineList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.every_day, R.id.dv_monday, R.id.dv_tuesday, R.id.dv_wednesday,
            R.id.dv_thursday, R.id.dv_friday, R.id.dv_saturday, R.id.dv_sunday})
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        /** Checking which checkbox was clicked */
        switch (view.getId()) {
            case R.id.dv_monday:
                if (checked) {
                    dayOfWeekList[1] = true;
                } else {
                    dayOfWeekList[1] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_tuesday:
                if (checked) {
                    dayOfWeekList[2] = true;
                } else {
                    dayOfWeekList[2] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_wednesday:
                if (checked) {
                    dayOfWeekList[3] = true;
                } else {
                    dayOfWeekList[3] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_thursday:
                if (checked) {
                    dayOfWeekList[4] = true;
                } else {
                    dayOfWeekList[4] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_friday:
                if (checked) {
                    dayOfWeekList[5] = true;
                } else {
                    dayOfWeekList[5] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_saturday:
                if (checked) {
                    dayOfWeekList[6] = true;
                } else {
                    dayOfWeekList[6] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.dv_sunday:
                if (checked) {
                    dayOfWeekList[0] = true;
                } else {
                    dayOfWeekList[0] = false;
                    everyDay.setChecked(false);
                }
                break;
            case R.id.every_day:
                LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.checkbox_layout);
                for (int i = 0; i < ll.getChildCount(); i++) {
                    View v = ll.getChildAt(i);
                    ((DayViewCheckBox) v).setChecked(checked);
                    onCheckboxClicked(v);
                }
                break;
        }
    }

    @OnClick(R.id.tv_medicine_time1)
    void onMedicineTimeClick() {
        showTimePicker1();
    }

    private void showTimePicker1() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour1 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute1 = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour1 = selectedHour;
                minute1 = selectedMinute;
                tvMedicineTime1.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));
            }
        }, hour1, minute1, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void setCurrentTime1() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour1 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute1 = mCurrentTime.get(Calendar.MINUTE);

        tvMedicineTime1.setText(String.format(Locale.getDefault(), "%d:%d", hour1, minute1));
    }

    @OnClick(R.id.tv_medicine_time2)
    void onMedicineTimeClick2() {
        showTimePicker2();
    }

    private void showTimePicker2() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour2 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute2 = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour2 = selectedHour;
                minute2 = selectedMinute;
                tvMedicineTime2.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));
            }
        }, hour2, minute2, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void setCurrentTime2() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour2 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute2 = mCurrentTime.get(Calendar.MINUTE);

        tvMedicineTime2.setText(String.format(Locale.getDefault(), "%d:%d", hour2, minute2));
    }

    @Optional
    @OnClick(R.id.tv_medicine_time3)
    void onMedicineTimeClick3() {
        showTimePicker3();
    }

    private void showTimePicker3() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour3 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute3 = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour3 = selectedHour;
                minute3 = selectedMinute;
                tvMedicineTime3.setText(String.format(Locale.getDefault(), "%d:%d", selectedHour, selectedMinute));
            }
        }, hour3, minute3, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void setCurrentTime3() {
        Calendar mCurrentTime = Calendar.getInstance();
        hour3 = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        minute3 = mCurrentTime.get(Calendar.MINUTE);

        tvMedicineTime3.setText(String.format(Locale.getDefault(), "%d:%d", hour3, minute3));
    }

    private void setSpinnerDoseUnits() {
        doseUnitList = Arrays.asList(getResources().getStringArray(R.array.medications_shape_array));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, doseUnitList);
        spinnerDoseUnits1.setAdapter(adapter);
    }

    @OnItemSelected(R.id.spinner_dose_units1)
    void onSpinnerItemSelected(int position) {
        if (doseUnitList == null || doseUnitList.isEmpty()) {
            return;
        }

        doseUnit1 = doseUnitList.get(position);
        Log.d("TAG", doseUnit1);
    }

    @OnItemSelected(R.id.spinner_dose_units2)
    void onSpinnerItemSelected1(int position) {
        if (doseUnitList == null || doseUnitList.isEmpty()) {
            return;
        }

        doseUnit2 = doseUnitList.get(position);
        Log.d("TAG", doseUnit2);
    }
    @OnItemSelected(R.id.spinner_dose_units3)
    void onSpinnerItemSelected2(int position) {
        if (doseUnitList == null || doseUnitList.isEmpty()) {
            return;
        }

        doseUnit3 = doseUnitList.get(position);
        Log.d("TAG", doseUnit3);
    }


    View.OnClickListener setClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            for(int z=0;z<alarmnumber;z++){
                if(z==1){

                    hour1=hour2;
                    minute1=minute2;
                    tvDoseQuantity1=tvDoseQuantity2;
                    doseUnit1 = doseUnit2;
                }
                if(z==2){

                    hour1=hour3;
                    minute1=minute3;
                    tvDoseQuantity1=tvDoseQuantity3;
                    doseUnit1 = doseUnit3;
                }


                Log.e(TAG,"in main content " +hour1);
                Log.e(TAG,"in main content" +minute1);


                int checkBoxCounter = 0;

                String pill_name = editMedName.getText().toString();
                String doseQuantity1 = tvDoseQuantity1.getText().toString();

                Calendar takeTime = Calendar.getInstance();
                Date date = takeTime.getTime();
                String dateString = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date);

                /** Updating model */
                MedicineAlarm alarm = new MedicineAlarm();

                /** If Pill does not already exist */
                if (!mPresenter.isMedicineExits(pill_name)) {
                    Pills pill = new Pills();
                    pill.setPillName(pill_name);
                    alarm.setDateString(dateString);
                    alarm.setHour(hour1);
                    alarm.setMinute(minute1);
                    alarm.setPillName(pill_name);
                    alarm.setDayOfWeek(dayOfWeekList);
                    alarm.setDoseUnit(doseUnit1);
                    alarm.setDoseQuantity(doseQuantity1);
                    pill.addAlarm(alarm);
                    long pillId = mPresenter.addPills(pill);
                    pill.setPillId(pillId);
                    mPresenter.saveMedicine(alarm, pill);
                } else { // If Pill already exists
                    Pills pill = mPresenter.getPillsByName(pill_name);
                    alarm.setDateString(dateString);
                    alarm.setHour(hour1);
                    alarm.setMinute(minute1);
                    alarm.setPillName(pill_name);
                    alarm.setDayOfWeek(dayOfWeekList);
                    alarm.setDoseUnit(doseUnit1);
                    alarm.setDoseQuantity(doseQuantity1);
                    pill.addAlarm(alarm);
                    mPresenter.saveMedicine(alarm, pill);
                }

                List<Long> ids = new LinkedList<>();
                try {
                    List<MedicineAlarm> alarms = mPresenter.getMedicineByPillName(pill_name);
                    for (MedicineAlarm tempAlarm : alarms) {
                        if (tempAlarm.getHour() == hour1 && tempAlarm.getMinute() == minute1) {
                            ids = tempAlarm.getIds();
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 7; i++) {
                    if (dayOfWeekList[i] && pill_name.length() != 0) {

                        int dayOfWeek = i + 1;
                        long _id = ids.get(checkBoxCounter);
                        int id = (int) _id;
                        checkBoxCounter++;

                        /** This intent invokes the activity ReminderActivity, which in turn opens the AlertAlarm window */
                        Intent intent = new Intent(getActivity(), ReminderActivity.class);
                        intent.putExtra(ReminderFragment.EXTRA_ID, _id);

                        operation = PendingIntent.getActivity(getActivity(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        /** Getting a reference to the System Service ALARM_SERVICE */
                        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

                        /** Creating a calendar object corresponding to the date and time set by the user */
                        Calendar calendar = Calendar.getInstance();

                        calendar.set(Calendar.HOUR_OF_DAY, hour1);
                        calendar.set(Calendar.MINUTE, minute1);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

                        /** Converting the date and time in to milliseconds elapsed since epoch */
                        long alarm_time = calendar.getTimeInMillis();

                        if (calendar.before(Calendar.getInstance()))
                            alarm_time += AlarmManager.INTERVAL_DAY * 7;

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarm_time,
                                AlarmManager.INTERVAL_DAY * 7, operation);
                    }
                }
                Toast.makeText(getContext(), "Alarm for " + pill_name + " is set successfully", Toast.LENGTH_SHORT).show();
                showMedicineList();
            }

        }
    };
}



