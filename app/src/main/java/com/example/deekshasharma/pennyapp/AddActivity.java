package com.example.deekshasharma.pennyapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deekshasharma.pennyapp.model.Categories;
import com.example.deekshasharma.pennyapp.model.CategoryItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddActivity extends ActionBarActivity {


    private TextView date;
    private Calendar myCalender;
    private DatePickerDialog datePickerDialog;
    private String[] allMonths = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    private TextView categoryName;
    private Button addTransactionButton;
    private EditText amount;
    private String selectedCategoryId;
    private String selectedCategoryGroup;
    private EditText transactionName;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        onDateClickListener();
        setDate();
        onCategoryClickListener();
        onAddTransactionClickListener();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        CategoryItem item = Categories.allCategories.get(position);
        Log.d("name of category is: ", "this "+item.getName());
        selectedCategoryId = item.getId();
        selectedCategoryGroup = item.getGroupName();
        categoryName = (TextView) findViewById(R.id.category);
        categoryName.setText(item.getName());
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        position = intent.getIntExtra("position", 0);
        Log.d("position is ", "this "+position);
    }


    /*
    Allows user to select the date in a date picker and set the value in date field.
     */
    private void setDate() {
        myCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);
                        String month = allMonths[selectedDate.get(Calendar.MONTH)];
                        date.setText(new StringBuilder()
                                .append(selectedDate.get(Calendar.DAY_OF_MONTH)).append("-")
                                .append(month).append("-")
                                .append(selectedDate.get(Calendar.YEAR)));
                    }
                }
                , myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH));
    }

    /*
    Listens to the date EditText
     */
    public void onDateClickListener() {
        date = (TextView) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }



    /*
    Listens to the categoryName TextView
    */
    private void onCategoryClickListener()
    {
        categoryName = (TextView) findViewById(R.id.category);
        categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    Listener for Add Transaction Button
     */
    private void onAddTransactionClickListener() {
        addTransactionButton = (Button) findViewById(R.id.add_transaction_button);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTransaction();
                Toast.makeText(getApplicationContext(), "Transaction Added", Toast.LENGTH_SHORT).show();



                //Go to View Transaction Fragment
//                Fragment viewTransactionFragment = new ViewTransactionFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_container,viewTransactionFragment).commit();
            }
        });
    }

    /*
    Saves transaction in the database.
     */
    private void postTransaction() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "https://api-pennyapp.rhcloud.com/rest/transactions";
        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(getParams()),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Log.d("Error on Response", error.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("BEARER", "55b885274e7912280095ef80ac1cb937:d8922b44-75af-4810-a87e-77adcf433cfd:760000000");
                return headers;
            }
        };
        queue.add(req);
    }

    /*
    Gets the data from the Add Transaction form fields
     */
    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        params.put("memberId", "d8922b44-75af-4810-a87e-77adcf433cfd");
        params.put("categoryId", selectedCategoryId);

        transactionName = (EditText) findViewById(R.id.transaction_name);
        params.put("name", transactionName.getText().toString());

        amount = (EditText) findViewById(R.id.amount);
        params.put("amount", amount.getText().toString());

        params.put("transactionDate", "2015-02-25T21:10:47.863");

        params.put("debit", "true");
        return params;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
