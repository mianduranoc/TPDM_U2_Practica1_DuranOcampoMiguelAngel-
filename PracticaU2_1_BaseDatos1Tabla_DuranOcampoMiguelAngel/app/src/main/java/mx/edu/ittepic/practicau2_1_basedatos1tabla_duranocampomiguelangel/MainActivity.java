package mx.edu.ittepic.practicau2_1_basedatos1tabla_duranocampomiguelangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        lista=findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                Rutina rutin=new Rutina(MainActivity.this);
                final Rutina rutinas[]=rutin.consultar();
                final int pos=position;

                alerta.setTitle("Detalle de "+rutinas[pos].descripcion)
                        .setMessage("Deseas modificar/eliminar la rutina:"+rutinas[pos].descripcion+" con ID:"+rutinas[pos].id+"?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(MainActivity.this,Main3Activity.class);
                                i.putExtra("id",rutinas[pos].id);
                                i.putExtra("dias",rutinas[pos].dias);
                                i.putExtra("descripcion",rutinas[pos].descripcion);
                                i.putExtra("calorias",rutinas[pos].calorias);
                                startActivity(i);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();
        Rutina rutina=new Rutina(this);
        Rutina[] rutinas=rutina.consultar();
        String vacio[]={};
        ArrayAdapter<String> adap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,vacio);
        lista.setAdapter(adap);
        if(rutinas==null){
            Toast.makeText(this,"NO HAY RUTINAS",Toast.LENGTH_LONG).show();
        }
        else {
            String NoRutinas[]=new String[rutinas.length];
            for (int i=0;i<NoRutinas.length;i++){
                NoRutinas[i]="Rutina "+rutinas[i].id+"\n"+rutinas[i].descripcion+"\n"+rutinas[i].calorias;
            }
            ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NoRutinas);
            lista.setAdapter(adapter);
        }
    }
}
