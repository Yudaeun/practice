package com.pro.customgalary;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private ImageView imageView;
    private Button btnCapture;
    private Button btnGallery;

    private ActivityResultLauncher<Intent> galleryLauncher;

    private ExecutorService executorService;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnCapture = findViewById(R.id.btnCapture);
        btnGallery = findViewById(R.id.btn_open_gallery);

        executorService = Executors.newSingleThreadExecutor(); // 단일 스레드 풀 생성하여 백그라운드 작업 수행
        handler = new Handler(Looper.getMainLooper()); // UI스레드를 ExecutorService는 Handler.post()를 사용하고, Main 스레드에서 실행

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            openGalleryFragment(selectedImageUri);
                        }
                    }
                }
        );

        btnGallery.setOnClickListener(v -> openGallery());

        btnCapture.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA);
            } else {
                openCamera();
            }
        });
    }

    private void openCamera() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("GALLERY_FRAGMENT");
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    private void openGallery() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("GALLERY_FRAGMENT");
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private void openGalleryFragment(Uri imageUri) {
        GalleryFragment fragment = GalleryFragment.Companion.newInstance(imageUri.toString());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
        transaction.add(R.id.fragment_container, fragment, "GALLERY_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openGalleryFragment(Bitmap photo) {
        GalleryFragment fragment = GalleryFragment.Companion.newInstance(photo);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);
        transaction.add(R.id.fragment_container, fragment, "GALLERY_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data"); // getExtras()를 이용해서 데이터를 받음

//            handler.postDelayed(() -> { // imageView의 크기가 없을 때 크래시 방지를 위한 post() 사용
//                int targetWidth = imageView.getWidth();
//                int targetHeight = imageView.getHeight();
//
//                if (targetWidth > 0 && targetHeight > 0) {
//                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(photo, targetWidth, targetHeight, true);
//                    processImage(scaledBitmap);
//                } else {
//                    processImage(photo);
//                }
//            }, 100);

            openGalleryFragment(photo);
        }
    }

    private void processImage(Bitmap photo) {
        Future<Bitmap> future = executorService.submit(() -> { // 비동기 작업 실행

            return photo;

        });

        executorService.execute(() -> {

            try {
                Bitmap result = future.get(); // 결과를 가져옴. 결과가 반환될 때까지 기다림
                handler.post(() -> imageView.setImageBitmap(result)); // UI 스레드에서 실행
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // 스레드 정리(정리하지 않으면 메모리 누수 발생 가능성 있음)
    }
}
