package com.jesusmacedo.balances.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jesusmacedo.balances.R;
import com.jesusmacedo.balances.models.Card;

/**
 * For adding a new card.
 */
public class NewCardDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private EditText etNewCardName;
    private EditText etNewCurrentBalance;
    private Spinner sNewCardCurrency;
    private Spinner sNewCardType;
    private EditText etNewCreditLimit;
    private EditText etNewClosingDate;
    private EditText etNewDueDate;
    private EditText etNewDueDateReminder;
    private Callback callback;

    private Card card;
    private String currency;
    private String type;

    public static String TAG = "HOLA";

    /**
     * Connect the implementation of the callback defined here but being called from the main activity.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (Callback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_fragment_new_card, container, false);

        // initialize views
        etNewCardName = (EditText) view.findViewById(R.id.et_new_card_name);
        etNewCurrentBalance = (EditText) view.findViewById(R.id.et_new_current_balance);

        sNewCardCurrency = (Spinner) view.findViewById(R.id.s_new_card_currency);
        ArrayAdapter<CharSequence> cardCurrenciesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.card_currencies, android.R.layout.simple_spinner_item);
        cardCurrenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewCardCurrency.setAdapter(cardCurrenciesAdapter);
        sNewCardCurrency.setOnItemSelectedListener(this);

        sNewCardType = (Spinner) view.findViewById(R.id.s_new_card_type);
        ArrayAdapter<CharSequence> cardTypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.card_types, android.R.layout.simple_spinner_item);
        cardTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewCardType.setAdapter(cardTypeAdapter);
        sNewCardType.setOnItemSelectedListener(this);

        etNewCreditLimit = (EditText) view.findViewById(R.id.et_new_credit_limit);
        etNewClosingDate = (EditText) view.findViewById(R.id.et_new_closing_date);
        etNewDueDate = (EditText) view.findViewById(R.id.et_new_due_date);
        etNewDueDateReminder = (EditText) view.findViewById(R.id.et_new_due_date_reminder);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_new_card);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCard();
            }
        });

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

    /*
     * Add menu.
     * @param menu
     * @return

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.activity_new_card, menu);
        return true;
    }

    /**
     * Handle item selection for menu.
     * @param item
     * @return

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        int id = item.getItemId();

        switch (id) {
            case R.id.action_save_card:
                saveCard();
                break;
        }
        return true;
    }*/

    /**
     * For spinners, assign the selected (or default) value.
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.s_new_card_currency) {
            currency = parent.getItemAtPosition(position).toString();
        } else if (spinner.getId() == R.id.s_new_card_type) {
            type = parent.getItemAtPosition(position).toString();
        }
    }

    /**
     * For spinners.
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Store new card in the device's database.
     */
    private void saveCard() {
        String creditLimit = etNewCreditLimit.getText().toString();
        card = new Card(
                etNewCardName.getText().toString(),
                Double.parseDouble(etNewCurrentBalance.getText().toString()),
                currency,
                type,
                (creditLimit.equals("") ? 0 : Double.parseDouble(creditLimit)),
                Integer.parseInt(etNewClosingDate.getText().toString()),
                Integer.parseInt(etNewDueDate.getText().toString()),
                Integer.parseInt(etNewDueDateReminder.getText().toString())
        );
        long cardId = Card.addNewCard(getContext(), card);
        if (cardId > 0) {
            Toast.makeText(getContext(), "The card has been added successfully", Toast.LENGTH_LONG).show();
            card.setCardId(cardId);
            callback.newCard(card);
            dismiss();
        }
    }

    /**
     * To send a signal to the main activity in order to update the main fragment (OverviewTabsFragment) after a new card has been added.
     */
    public interface Callback {
        void newCard(Card card);
    }
}
