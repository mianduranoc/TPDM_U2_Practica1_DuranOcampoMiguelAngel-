package mx.edu.ittepic.practicau2_1_basedatos1tabla_duranocampomiguelangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText idae,dias,descripcion,calorias;
    Button modificar,eliminar,regresar;
    int id, caloria;
    String dia,description;
    Rutina rutina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        idae=findViewById(R.id.idae);
        dias=findViewById(R.id.diasae);
        descripcion=findViewById(R.id.descripcionae);
        calorias=findViewById(R.id.caloriasae);
        modificar=findViewById(R.id.actualizar);
        eliminar=findViewById(R.id.eliminar);
        regresar=findViewById(R.id.regresarae);
        id=getIntent().getExtras().getInt("id");
        caloria=getIntent().getExtras().getInt("calorias");
        dia=getIntent().getExtras().getString("dias");
        description=getIntent().getExtras().getString("descripcion");

        idae.setText(id+"");
        dias.setText(dia);
        descripcion.setText(description);
        calorias.setText(caloria+"");

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rutina=new Rutina(Main3Activity.this);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultado=rutina.eliminar(new Rutina(id,dia,description,caloria));
                if (resultado){
                    Toast.makeText(Main3Activity.this,"Eliminado exitosamente",Toast.LENGTH_LONG).show();
                    idae.setEnabled(false);
                    dias.setEnabled(false);
                    descripcion.setEnabled(false);
                    calorias.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                }
                else {
                    Toast.makeText(Main3Activity.this,"Error: Registro no eliminado",Toast.LENGTH_LONG).show();
                }
            }
        });
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultado=rutina.actualizar(new Rutina(Integer.parseInt(idae.getText().toString()),dias.getText().toString(),descripcion.getText().toString(),Integer.parseInt(calorias.getText().toString())));
                if (resultado){
                    Toast.makeText(Main3Activity.this,"Actualizado exitosamente",Toast.LENGTH_LONG).show();
                    idae.setEnabled(false);
                    dias.setEnabled(false);
                    descripcion.setEnabled(false);
                    calorias.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                }
                else {
                    Toast.makeText(Main3Activity.this,"Error: Registro no actualizado",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
