package com.jesusmacedo.balances.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jesusmacedo.balances.R;
import com.jesusmacedo.balances.models.Card;

import java.util.Calendar;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewRecordDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, SegmentedButtonGroup.OnClickedButtonPosition {

    public static final String TAG = "NEW_RECORD";
    private View view;

    private SegmentedButtonGroup sbg;
    private ImageButton ibNewRecordDate, ibNewRecordTime;
    private TextView tvNewRecordSign, tvNewRecordDate, tvNewRecordTime;
    private Spinner sNewRecordCard, sNewRecordCategory, sNewRecordCurrency;

    private String currency;

    public NewRecordDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_record_dialog, container, false);

        sbg = (SegmentedButtonGroup) view.findViewById(R.id.sbg_record_types);
        tvNewRecordSign = (TextView) view.findViewById(R.id.tv_new_record_sign);
        ibNewRecordDate = (ImageButton) view.findViewById(R.id.ib_new_record_date);
        tvNewRecordDate = (TextView) view.findViewById(R.id.tv_new_record_date);
        ibNewRecordTime = (ImageButton) view.findViewById(R.id.ib_new_record_time);
        tvNewRecordTime = (TextView) view.findViewById(R.id.tv_new_record_time);

        // get cards and display the name in the spinner
        sNewRecordCard = (Spinner) view.findViewById(R.id.s_new_record_card);
        ArrayAdapter<Card> cardsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, Card.getCards(view.getContext()));
        cardsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewRecordCard.setAdapter(cardsAdapter);
        sNewRecordCard.setOnItemSelectedListener(this);

        sNewRecordCategory = (Spinner) view.findViewById(R.id.s_new_record_category);
        ArrayAdapter<CharSequence> categoriesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.card_currencies, android.R.layout.simple_spinner_item);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewRecordCategory.setAdapter(categoriesAdapter);
        sNewRecordCategory.setOnItemSelectedListener(this);

        sNewRecordCurrency = (Spinner) view.findViewById(R.id.s_new_record_currency);
        ArrayAdapter<CharSequence> cardCurrenciesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.card_currencies, android.R.layout.simple_spinner_item);
        cardCurrenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewRecordCurrency.setAdapter(cardCurrenciesAdapter);
        sNewRecordCurrency.setOnItemSelectedListener(this);

        // add click listeners to image buttons
        ibNewRecordDate.setOnClickListener(this);
        ibNewRecordTime.setOnClickListener(this);
        sbg.setOnClickedButtonPosition(this);

        return view;
    }

    /**
     * Set DialogFragment to fullscreen.
     */
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        window.setGravity(Gravity.CENTER);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }

    /**
     * Click listener for ImageButtons
     * @param v
     */
    @Override
    public void onClick(View v) {
        final Calendar c  = Calendar.getInstance();

        if (v == ibNewRecordDate) {
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNewRecordDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, year, month, day);

            datePickerDialog.show();
        } else if (v == ibNewRecordTime) {
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minutes = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog =  new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    tvNewRecordTime.setText(hourOfDay + ":" + minute);
                }
            }, hours, minutes, false);

            timePickerDialog.show();
        }
    }

    /**
     * For spinners selection.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.s_new_record_currency) {
            currency = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * SegmentedButtonGroup listener.
     * @param position
     */
    @Override
    public void onClickedButtonPosition(int position) {
        switch (position) {
            case 0:
                tvNewRecordSign.setText("+");
                break;
            case 1:
                tvNewRecordSign.setText("-");
                break;
            case 2:
                tvNewRecordSign.setText("=");
                break;
            default:
                tvNewRecordSign.setText("-");
        }
    }
}
