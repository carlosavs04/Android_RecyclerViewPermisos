package com.example.recyclerviewpermisos;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recyclerviewpermisos.Modelos.Permiso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PermisoAdaptador.ItemListener {

    String numeroTelefono = "+528713321257";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Permiso> p = new ArrayList<>();

        p.add(new Permiso("Llamar", 1, Manifest.permission.CALL_PHONE));
        p.add(new Permiso("Ubicación", 2, Manifest.permission.ACCESS_FINE_LOCATION));
        p.add(new Permiso("Micrófono", 3, Manifest.permission.RECORD_AUDIO));
        p.add(new Permiso("Contactos", 4, Manifest.permission.READ_CONTACTS));
        p.add(new Permiso("Galería", 5, Manifest.permission.READ_EXTERNAL_STORAGE));

        PermisoAdaptador adPermiso = new PermisoAdaptador(this, p, this);
        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(adPermiso);
        recycler.setLayoutManager(linearManager);
        recycler.setHasFixedSize(true);
    }

    @Override
    public void onItemClick(Permiso item) {
        Toast.makeText(getApplicationContext(), item.getId() + " is clicked", Toast.LENGTH_SHORT).show();
        switch (item.getId()) {
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        llamar();
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    break;
                }

            case 2:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    cuadroDialogo("Ubicación utilizada en primer plano", "Te encuentras en UTT");
                } else {
                  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                }
                break;

            case 3:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    cuadroDialogo("Audio grabado", "Grabar audio");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 3);
                }
                break;

            case 4:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    cuadroDialogo("Acceso a contactos", "Contaco obtenido");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 4);
                }
                break;

            case 5:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    cuadroDialogo("Acceso a galería", "Buena foto bro");
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode)
        {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    llamar();
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;

            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    cuadroDialogo("Ubicación utilizada en primer plano", "Te encuentras en UTT");
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;

            case 3:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    cuadroDialogo("Audio grabado", "Grabar audio");
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;

            case 4:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    cuadroDialogo("Acceso a contactos", "Contacto obtenido");
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;

            case 5:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    cuadroDialogo("Acceso a galería", "Buena foto bro");
                }

                else {
                    System.out.println("No se tienen los permisos necesarios para realizar esta acción.");
                }

                return;
        }
    }

    public void llamar()
    {
        android.content.Intent llamada = new android.content.Intent(android.content.Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel:" + numeroTelefono));
        startActivity(llamada);
    }

    public void cuadroDialogo(String titulo, String dialogo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(titulo)
                .setMessage(dialogo)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}