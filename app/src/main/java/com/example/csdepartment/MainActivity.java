package com.example.csdepartment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// implement AdapterView.OnItemSelectedListener so that an OnItemSelectedListener may
// be set on the Spinner
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // declare EditText
    EditText nameEditText;

    // declare Spinners
    Spinner yearSpinner;
    Spinner majorSpinner;

    // declare Strings for data input by user
    String name;
    String year;
    String major;

    // declare DBHandler
    DBHandler dbHandler;

    /**
     * This method initializes the MainActivity.
     * @param savedInstanceState a Bundle object that is passed into the onCreate
     *                           method of every Android Activity. Activities have
     *                           the ability, under special circumstances, to restore
     *                           themselves to a previous state using the data stored
     *                           in this bundle. If there is no available instance data,
     *                           the savedInstanceState will be null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // code generated by Android Studio that initializes the View
        // and ActionBar of MainActivity Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditText
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        // initialize Spinners
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        majorSpinner = (Spinner) findViewById(R.id.majorSpinner);

        // initialize ArrayAdapter with values in year string array
        // and stylize it with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);

        // further stylize ArrayAdapter with style defined by simple_spinner_dropdown_item
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set ArrayAdapter on Spinner
        yearSpinner.setAdapter(yearAdapter);

        // set OnItemSelectedListener on Spinner
        yearSpinner.setOnItemSelectedListener(this);

        // initialize ArrayAdapter with values in year string array
        // and stylize it with style defined by simple_spinner_item
        ArrayAdapter<CharSequence> majorAdapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_item);

        // further stylize ArrayAdapter with style defined by simple_spinner_dropdown_item
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set ArrayAdapter on Spinner
        majorSpinner.setAdapter(majorAdapter);

        // set OnItemSelectedListener on Spinner
        majorSpinner.setOnItemSelectedListener(this);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);
    }

    /**
     * This method inflates (creates) the menu for the Main Activity.
     * @param menu menu_main
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method gets called when the ADD item in the Menu
     * gets pushed.  It validates that all required data has been input.
     * If it has been input, it adds it to the database and displays a Toast,
     * else it simply displays a Toast.
     * @param menuItem because the ADD Item that calls this method is
     *             in a Menu, we must pass the method a MenuItem.
     */
    public void addStudent (MenuItem menuItem){

        // get data input in EditText and store it in String
        name = nameEditText.getText().toString();

        // trim Strings and see if any are equal to an empty String
        if ((name.trim().equals("")) || (year.trim().equals("")) || (major.trim().equals(""))){
            // if any are equal to an empty String, then that means
            // required data hasn't been input, so display a toast
            Toast.makeText(this, "Please enter a name, year, and major!", Toast.LENGTH_LONG).show();
        } else {
            // if none are equal to an empty String, then that means
            // all required data has been input, so update the database
            // and display a toast
            dbHandler.addStudent(name, year, major);
            Toast.makeText(this, name + " added!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method gets called when an item in a Spinner is selected.
     * @param adapterView Spinner AdapterView
     * @param view MainActivity View
     * @param position position of item in Spinner that was selected
     * @param id database id of item in Spinner that was selected
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        // check if it was the year Spinner that was selected
        if (adapterView.equals(yearSpinner))
            // get item selected based on position, convert it to a String
            // and store it in String
            year = adapterView.getItemAtPosition(position).toString();
        else
            major = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}