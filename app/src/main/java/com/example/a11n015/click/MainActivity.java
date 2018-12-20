package com.example.a11n015.click;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;


public class MainActivity extends AppCompatActivity {

    //Declaramos el boton de capture y el View//
    Button click;
    ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recopilamos//
        click = (Button) findViewById(R.id.CLICK);
        img = (ImageView) findViewById(R.id.mostrar);

        click.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // Se crea el intent para llamar la camara//
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // se crea la carpeta en la terminal,//
                // se usa getExternalStoragePublicDirectory para que no se borren los archivos//
                File imgfiles = new File(getExternalStoragePublicDirectory (Environment.DIRECTORY_PICTURES), "CArpetaAPP");
                imgfiles.mkdirs();

                // se da nombre a como se llamara la imagen//
                File imag= new File(imgfiles, "foto.jpg");
                Uri uri = Uri.fromFile(imag);

                //le indicamos al itent que guarde la imagen//
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                //mandamos a retornar
                startActivityForResult(intent, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // se confirma que fue tomada
        if (requestCode == 1 && requestCode == RESULT_OK){
            // se almacena en la memoria y se crea una miniatura
            Bitmap bitmap= BitmapFactory.decodeFile(getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"CArpetaAPP"+ "foto.jgp");
            //mostrarlo en pantalla
            img.setImageBitmap(bitmap);

        }

    }

    private String getCode(){
        // se crea un formato para guardar la foto
        //formato qeu sera de fecha y hora
        SimpleDateFormat dateFort = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFort.format(new Date());
        String fotoCode = "fotos.jpg" + date;
        return fotoCode;


    }
}
