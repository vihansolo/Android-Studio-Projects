package ga.vihanggarud.www.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCamera = findViewById(R.id.btnCamera);
        pic =findViewById(R.id.pic);

        btnCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        pic =findViewById(R.id.pic);

        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
        pic.setImageBitmap(bitmap);
    }
}
