package com.minhaj.coffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        EditText text = (EditText) findViewById(R.id.name_field_editText);
        final String name = text.getText().toString();

        CheckBox whippedCreamChecBox = (CheckBox) findViewById(R.id.whipped_cream_checkbobx);
        boolean hasWhippedCream = whippedCreamChecBox.isChecked();

        CheckBox chocolateChecBox = (CheckBox) findViewById(R.id.chocolate_checkbobx);
        boolean hasChocolate = chocolateChecBox.isChecked();

        int price = calculatePrice();
        final String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //only email app should handle this
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {

        String priceMessage = getString(R.string.order_summ_name) + name;
        priceMessage += "\n" + getString(R.string.order_summ_whippedCream) + addWhippedCream;
        priceMessage += "\n" + getString(R.string.order_summ_chocolate) + addChocolate;
        priceMessage += "\n" + getString(R.string.order_summ_quantity) + quantity;
        priceMessage += "\n" + getString(R.string.order_summ_total) + price;
        priceMessage += "\n" + getString(R.string.order_summ_thanks);
        return priceMessage;
    }

    // calculate the price of the order..
    private int calculatePrice() {
        return quantity * 5;
    }

    public void increment(View view) {
        if (quantity == 10) {
            Toast.makeText(this, getString(R.string.increment_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.decrement_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_View);
        orderSummaryTextView.setText(message);
    }

    private void display(int numberOfCoffee) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffee);
    }
}
