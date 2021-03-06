package br.com.casadocodigo.boaviagem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class GastoActivity extends Activity {

    private int ano, mes, dia;
    private Button dataGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto);

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataGasto = (Button) findViewById(R.id.data);
        dataGasto.setText(dia+"/"+(mes + 1)+"/"+ano);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categoria_gasto, android.R.layout.simple_spinner_item);
        Spinner categoria = (Spinner) findViewById(R.id.categoria);
        categoria.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featuredId, MenuItem item){
        switch (item.getItemId()){

            case R.id.remover_gasto:
                                Toast.makeText(this, R.string.remover,Toast.LENGTH_LONG).show();
                                return true;
            default:
                                return super.onMenuItemSelected(featuredId,item);

        }

    }

    public void selecionarData( View view ){
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog( int id ){
        if( R.id.data == id ){
            return new DatePickerDialog(this, listener, ano,mes,dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void  onDateSet( DatePicker view, int year, int monthOfYear, int dayOfMonth){
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataGasto.setText(dia+"/"+(mes+1)+"/"+ano);
        }
    };

}
