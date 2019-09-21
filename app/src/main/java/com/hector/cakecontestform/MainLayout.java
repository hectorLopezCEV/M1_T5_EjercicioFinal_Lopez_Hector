package com.hector.cakecontestform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainLayout extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    /**
     * Primero se declaran todas las propiedades de la vista:
     */
    EditText name, lastName, age;
    CheckBox otherContest;
    TextView whichContestTextView;
    EditText whichContestEditText;
    Button inscription;

    /**
     *
     * Método que se carga al iniciar la vista
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // Se inicializan todas las variables al crear la vista
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        otherContest = findViewById(R.id.otherContest);
        whichContestTextView = findViewById(R.id.whichContestTextView);
        whichContestEditText = findViewById(R.id.whichContestEditText);
        inscription = findViewById(R.id.inscription);

        // Se crea el listener para cuando cambia el checkbox que comprueba si ha participado en
        // otro concurso
        otherContest.setOnCheckedChangeListener(this);
    }

    /**
     *
     * Método que recibe el evento onClick del botón de inscripción
     *
     * @param view
     */
    public void checkInscription(View view) {
        // Primero se establece la variable que guardará si el formulario es correcto
        boolean formReady = true;

        // Se comprueba que el nombre no esté vacío
        if (name.getText().toString().trim().length() == 0) {
            formReady = false;
        }

        // Se comprueba que el apellido no esté vacío
        if (lastName.getText().toString().trim().length() == 0) {
            formReady = false;
        }

        // Se comprueba primero que el campo de la edad no esté vacío
        if(age.getText().toString().trim().length() == 0){
            formReady = false;
        }else{
            // Si estubiera relleno, se comrpueba que tenga 18 años o más
            if (Integer.parseInt(age.getText().toString()) < 18) {
                formReady = false;
            }
        }

        // Se comprueba que, si está marcado el checkbox de otro concurso, que el campo del otro
        // concurso no esté vacío
        if (otherContest.isChecked() &&
                whichContestEditText.getText().toString().trim().length() == 0) {
            formReady = false;
        }

        // Si no ha fallado en ninguna de las comprobaciones, se muestra el Toast de formulario
        // recibido correctamente, en caso de que alguno haya fallado, se muestra el de error
        if (formReady) {
            Toast.makeText(this, "El formulario se ha recibido correctamente", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Hay algo mal en el formulario", Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     * Método que recibe el evento onCheckedChanged del checkbox de otros eventos
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Si está marcado, muestra la petición de escribir otro evento, en caso contrario, lo
        // oculta
        if (isChecked) {
            whichContestTextView.setVisibility(View.VISIBLE);
            whichContestEditText.setVisibility(View.VISIBLE);
        } else {
            whichContestTextView.setVisibility(View.GONE);
            whichContestEditText.setVisibility(View.GONE);
        }
    }
}
