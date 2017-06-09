package com.jesusmacedo.balances.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class NewCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etNewCardName;
    private EditText etNewCurrentBalance;
    private Spinner sNewCardCurrency;
    private Spinner sNewCardType;
    private EditText etNewCreditLimit;
    private EditText etNewClosingDate;
    private EditText etNewDueDate;
    private EditText etNewDueDateReminder;

    private Card card;
    private String currency;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        // initialize views
        etNewCardName = (EditText) findViewById(R.id.et_new_card_name);
        etNewCurrentBalance = (EditText) findViewById(R.id.et_new_current_balance);

        sNewCardCurrency = (Spinner) findViewById(R.id.s_new_card_currency);
        ArrayAdapter<CharSequence> cardCurrenciesAdapter = ArrayAdapter.createFromResource(this, R.array.card_currencies, android.R.layout.simple_spinner_item);
        cardCurrenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewCardCurrency.setAdapter(cardCurrenciesAdapter);
        sNewCardCurrency.setOnItemSelectedListener(this);

        sNewCardType = (Spinner) findViewById(R.id.s_new_card_type);
        ArrayAdapter<CharSequence> cardTypeAdapter = ArrayAdapter.createFromResource(this, R.array.card_types, android.R.layout.simple_spinner_item);
        cardTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNewCardType.setAdapter(cardTypeAdapter);
        sNewCardType.setOnItemSelectedListener(this);

        etNewCreditLimit = (EditText) findViewById(R.id.et_new_credit_limit);
        etNewClosingDate = (EditText) findViewById(R.id.et_new_closing_date);
        etNewDueDate = (EditText) findViewById(R.id.et_new_due_date);
        etNewDueDateReminder = (EditText) findViewById(R.id.et_new_due_date_reminder);
    }

    /**
     * Add menu.
     * @param menu
     * @return
     */
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
     */
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
    }

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
        long cardId = Card.addNewCard(this, card);
        if (cardId > 0) {
            Toast.makeText(this, "The card has been added successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
