package com.example.grocerytotal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {

    private EditText itemValue;
    private TextView runningTotal;
    private Spinner itemMultiple;
    private float item = 0.0f;
    private float total = 0.0f;
    private CheckBox taxable;
    private float addTax = 0.0f;
    private float taxCalc = 1.0f;

    private final static String CURRENT_TOTAL = "currentRunningTotalAmount";
    private final static String CURRENT_TAX = "currentAddTaxValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            addTax = savedInstanceState.getFloat(CURRENT_TAX);
            total = savedInstanceState.getFloat(CURRENT_TOTAL);
        }
        setContentView(R.layout.activity_main);

        addTax = .15f;
        taxCalc = addTax + 1.0f;
        itemValue = (EditText) findViewById(R.id.groceryItem);
        runningTotal = (TextView) findViewById(R.id.runningTotal);
        runningTotal.setText(NumberFormat.getCurrencyInstance().format(total));
        taxable = (CheckBox) findViewById(R.id.checkBox);
        itemMultiple = (Spinner)findViewById(R.id.itemMultiplier);
    }

    public void addItemToTotal(View view) {
        item = Float.parseFloat(itemValue.getText().toString());
        String multiple = (String) itemMultiple.getSelectedItem();
        switch (multiple) {
            case "x1":
                item = item * 1;
                break;
            case "x2":
                item = item * 2;
                break;
            case "x3":
                item = item * 3;
                break;
            case "x4":
                item = item * 4;
                break;
            case "x5":
                item = item * 5;
                break;
            case "x6":
                item = item * 6;
                break;
            case "x7":
                item = item * 7;
                break;
            case "x8":
                item = item * 8;
                break;
            case "x9":
                item = item * 9;
                break;
            case "x10":
                item = item * 10;
                break;
            case "x12":
                item = item * 12;
                break;
            case "x24":
                item = item * 24;
                break;
            default:
                item = item * 1;
                break;
        }
        if(taxable.isChecked())
            total = total + (item * taxCalc);
        else
            total = total + item;

        runningTotal.setText(NumberFormat.getCurrencyInstance().format(total));
        taxable.setChecked(false);
        itemValue.setText("");
        itemMultiple.setSelection(0);
    }

    public void resetTotal(View view) {
        total = 0.0f;
        runningTotal.setText("");
        taxable.setChecked(false);
        itemValue.setText("");
        itemMultiple.setSelection(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(CURRENT_TAX, addTax);
        outState.putFloat(CURRENT_TOTAL, total);
    }
}
