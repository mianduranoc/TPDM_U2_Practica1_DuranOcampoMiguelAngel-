package mx.edu.ittepic.practicau2_1_basedatos1tabla_duranocampomiguelangel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText dias,descripcion,calorias;
    Button guardar,regresar;
    Rutina rutina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dias=findViewById(R.id.dias);
        descripcion=findViewById(R.id.descripcion);
        calorias=findViewById(R.id.calorias);
        guardar=findViewById(R.id.guardar);
        regresar=findViewById(R.id.regresar);
        rutina=new Rutina(this);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean resultado=rutina.insertar(new Rutina(0,dias.getText().toString(),descripcion.getText().toString(),Integer.parseInt(calorias.getText().toString())));
                if (resultado){
                    Toast.makeText(Main2Activity.this,"RUTINA INSERTADA CORRECTAMENTE",Toast.LENGTH_LONG).show();
                    dias.setText("");
                    descripcion.setText("");
                    calorias.setText("");
                }
                else {
                    Toast.makeText(Main2Activity.this,"ERROR: RUTINA NO INSERTADA",Toast.LENGTH_LONG).show();
                }
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
