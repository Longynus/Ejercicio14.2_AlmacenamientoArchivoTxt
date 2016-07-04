package happyapps.ejercicio142_almacenamientoarchivotxt;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);

    }

    public void grabar(View view){
        //Extraemos fecha ingresada y cambiamos las / por -
        String nomarchivo=et1.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        try {
            OutputStreamWriter archivo= new OutputStreamWriter(openFileOutput(nomarchivo, Activity.MODE_PRIVATE));
            archivo.write(et2.getText().toString());
            archivo.flush();
            archivo.close();
            }catch (IOException e){

        }
        Toast noti = Toast.makeText(this,"Los datos fueron grabados", Toast.LENGTH_SHORT);
        noti.show();
        et1.setText("");
        et2.setText("");
    }


    public void recuperar(View view){
        String nomarchivo=et1.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        //Obtenemos  la lista de todos los archivos que almacena la aplicacion
        String[] archivos=fileList();
        for (int f =0; f<archivos.length; f++){
            if (nomarchivo.equals(archivos[f])){
                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput(nomarchivo));
                    BufferedReader br = new BufferedReader(archivo);
                    String linea= br.readLine();
                    String todo="";
                    while (linea != null){
                        todo = todo + linea+ "\n";
                        linea=br.readLine();
                    }
                    Toast not = Toast.makeText(this,"Datos recuperados",Toast.LENGTH_SHORT);
                    not.show();
                    br.close();
                    archivo.close();
                    et2.setText(todo);

                }catch(IOException e){

                }
            }else{
                Toast t = Toast.makeText(this, "No hay datos grabados para dicha fecha", Toast.LENGTH_SHORT);
                t.show();
                et2.setText("");
            }

            }

        }
    }






