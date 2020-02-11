package at.htlgkr.ticketingsystem;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter spinnerAdapter;
    private TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton();

        //implement this
        InputStream in = null;
        AssetManager assets = getAssets();
        try {
            in = assets.open(Configuration.CATEGORIES_CONF);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Kategorien laden
        List<Category> categoryList = null;
        try {
            categoryList = ConfigurationParser.getCategories(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Spinner fuellen
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categoryList);
        Spinner spinner = findViewById(R.id.sp_categories);
        spinner.setAdapter(spinnerAdapter);

        // Tickets laden
        TicketFileManager ticketFileManager = new TicketFileManager();

        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = openFileInput(Configuration.DATA_FILE);
            br = new BufferedReader(new InputStreamReader(fis));
            String line;

            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ticketFileManager.loadTickets();
        TicketManager manager = new TicketManager(categoryList);


        // Tickets in ListView laden
        ListView listView = findViewById(R.id.lv_mainListView);

        ticketAdapter = new TicketAdapter(this, manager.getTicketList(), R.layout.lvi_customlistviewitem);

        listView.setAdapter(ticketAdapter);
    }

    private void addButton() {
        Button addBtn = findViewById(R.id.btn_addticket);

        final View vDialog = getLayoutInflater().inflate(R.layout.dlg_addticketdialoglayout, null);
        new AlertDialog.Builder(this)
                .setMessage("Dein Tipp fuer Frankreich−Australien:")
                .setCancelable(false)
                .setView(vDialog)
                .setPositiveButton("Add", null);


//    private void handleDialog( final View vDialog) {
//        EditText txtShot = vDialog.findViewById(R.id.txtShot);
//        EditText txtReceived = vDialog.findViewById(R.id.txtReceived);
//        int shot = Integer.parseInt(txtShot.getText().toString());
//        int received = Integer.parseInt(txtReceived.getText().toString());
//        showToast("Ergebnis: " + shot + ":" + received);
//    }
    }
}
