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
import com.example.deekshasharma.pennyapp.Collections.CategoriesEndPoint;
import com.example.deekshasharma.pennyapp.model.CategoryItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class AddActivity extends ActionBarActivity {


    private TextView date;
    private Calendar myCalender;
    private DatePickerDialog datePickerDialog;
    private String[] allMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
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
        CategoryItem item = CategoriesEndPoint.allCategories.get(position);
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
                if(validateFields())
                {
                    postTransaction();
                    Intent intent = new Intent(getApplicationContext(),ViewTransactionActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext()," Please verify all Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*
    Validate all the form fields before saving transaction
     */
    private boolean validateFields()
    {
        transactionName = (EditText) findViewById(R.id.transaction_name);
        amount = (EditText) findViewById(R.id.amount);
        date = (TextView) findViewById(R.id.date);
        categoryName = (TextView) findViewById(R.id.category);


        if(transactionName.getText().toString() == "" ||
                amount.getText().toString() == "" ||
                date.getText().toString() == "" ||
                categoryName.getText().toString() == "")
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /*
    Saves transaction in the database.
     */
    private void postTransaction() {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "https://api-pennyapp.rhcloud.com/rest/transactions";
        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(getParams()),
                new Response.Listener<JSONObject>()
                {
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

        params.put("transactionDate", getDateInUTC());

        params.put("debit", "true");
        return params;
    }

    private String getDateInUTC()
    {
        SimpleDateFormat f1 = new SimpleDateFormat("d-MMM-yyyy");

        Date d = null;
        try {
            d = (f1.parse(date.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        Calendar current = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, current.get(Calendar.HOUR));
        cal.add(Calendar.MINUTE, current.get(Calendar.MINUTE));
        cal.add(Calendar.SECOND, current.get(Calendar.SECOND));
        cal.add(Calendar.MILLISECOND,current.get(Calendar.MILLISECOND) );
        return(f.format(cal.getTime()));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
