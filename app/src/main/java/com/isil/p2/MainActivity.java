package com.isil.p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtNombre, txtPosicion;
    TextView txtResultado;
    ArrayList<String> nombres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pregunta 2");

        txtNombre = findViewById(R.id.edtNombre);
        txtPosicion = findViewById(R.id.edtPosicion);
        txtResultado = findViewById(R.id.tvResultado);

        Button btnAlmacenar = findViewById(R.id.btnAlmacenar);
        Button btnBuscar = findViewById(R.id.btnBuscar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        Button btnInsertar = findViewById(R.id.btnInsertar);
        Button btnModificar = findViewById(R.id.btnModificar);

        btnAlmacenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                almacenar();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar();
            }
        });
    }

    private void almacenar() {
        String nuevoAlumno = txtNombre.getText().toString().trim();
        nombres.add(nuevoAlumno);
        mostrar();
        txtNombre.getText().clear();
    }

    private void mostrar() {
        txtResultado.setText("");
        for (int i = 0; i < nombres.size(); i++) {
            int posicion = i + 1;
            txtResultado.append(posicion + ". " + nombres.get(i) + "\n");
        }
    }

    private void buscar() {
        String nAlumno = txtNombre.getText().toString().toUpperCase();
        int salida = buscarNombre(nAlumno);

        if (salida == -1) {
            Toast.makeText(this, "Nombre no encontrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nombre encontrado en la posición " + (salida + 1), Toast.LENGTH_SHORT).show();
        }
    }

    private int buscarNombre(String nombre) {
        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).toUpperCase().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private void eliminar() {
        String nNombre = txtNombre.getText().toString().toUpperCase();
        int posicion = buscarNombre(nNombre);
        if (posicion != -1) {
            nombres.remove(posicion);
            Toast.makeText(this, "El Nombre " + nNombre + " ha sido Eliminado", Toast.LENGTH_SHORT).show();
            mostrar();
        } else {
            Toast.makeText(this, "Nombre no encontrado para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertar() {
        String nNombre = txtNombre.getText().toString().trim();
        int pos = Integer.parseInt(txtPosicion.getText().toString());
        if (pos >= 1 && pos <= nombres.size()) {
            nombres.add(pos - 1, nNombre);
            mostrar();
            txtPosicion.getText().clear();
        } else {
            Toast.makeText(this, "Posición inválida para insertar", Toast.LENGTH_SHORT).show();
        }
    }

    private void modificar() {
        String nNombre = txtNombre.getText().toString().trim();
        String nPosicion = txtPosicion.getText().toString().trim();

        if (nombres.isEmpty()) {
            Toast.makeText(this, "La lista está vacía, no se puede modificar", Toast.LENGTH_SHORT).show();
        } else if (nPosicion.isEmpty()) {
            Toast.makeText(this, "Ingrese la posición del nombre a modificar", Toast.LENGTH_SHORT).show();
        } else {
            int posicion = Integer.parseInt(nPosicion);
            if (posicion >= 1 && posicion <= nombres.size()) {
                nombres.set(posicion - 1, nNombre);
                Toast.makeText(this, "Nombre Modificado", Toast.LENGTH_SHORT).show();
                mostrar();
                txtNombre.getText().clear();
                txtPosicion.getText().clear();
            } else {
                Toast.makeText(this, "Posición inválida para modificar", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
