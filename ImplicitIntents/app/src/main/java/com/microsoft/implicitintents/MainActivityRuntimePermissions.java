package com.microsoft.implicitintents;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

public class MainActivityRuntimePermissions extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_GALLERY = 201;

    private static final int REQUEST_CODE_PHONE_PERMISSION = 301;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cameraButton = findViewById(R.id.cameraButton);
        Button galleryButton = findViewById(R.id.galleryButton);

        Button dialButton = findViewById(R.id.dialButton);
        final Button callButton = findViewById(R.id.callButton);

        imageView = findViewById(R.id.imageView);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
        });

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "+918879133347";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });
    }

    private void callPhone() {
        String number = "+918879133347";
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));


        if (ContextCompat.checkSelfPermission(MainActivityRuntimePermissions.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivityRuntimePermissions.this,
                    Manifest.permission.CALL_PHONE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Please give us permission so we can help you with phone calls!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showBuiltInPermissionDialog();
                    }
                });
                builder.setNegativeButton("No!", null);
                builder.show();
            } else {
                showBuiltInPermissionDialog();
            }
        }
    }

    private void showBuiltInPermissionDialog() {
        ActivityCompat.requestPermissions
                (MainActivityRuntimePermissions.this, new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_PHONE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale
                        (MainActivityRuntimePermissions.this, Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(MainActivityRuntimePermissions.this, "Please go to settings!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (data != null
                    && data.getExtras() != null
                    && data.getExtras().get("data") != null) {
                if (data.getExtras().get("data") instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_GALLERY) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                try {
                    ParcelFileDescriptor descriptor = getContentResolver()
                            .openFileDescriptor(uri, "r");
                    if (descriptor != null) {
                        FileDescriptor fileDescriptor = descriptor.getFileDescriptor();
                        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Log.i("vipul", uri.toString());
            }
        }
    }
}
