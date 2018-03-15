package com.example.lawati97.deliveryapplication;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.net.Uri;

public class SuggestARestaurant extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE =1;

    private Spinner spinner;
    private Button submitRestOrCoff;
    private Button cancelRequest;
    private Button  uploadImageButton;
    private EditText namOfTheRestaurant;
    private EditText phoneOfTheRestaurant;
    private ImageView imageViewMenu;
    private String text;
    private Uri selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_arestaurant);
        spinner = (Spinner) findViewById(R.id.suggestRestaurantOrCoffeeShop);
        submitRestOrCoff = (Button) findViewById(R.id.submitButtoninSR);
        cancelRequest = (Button) findViewById(R.id.cancelButtonInSR);
        namOfTheRestaurant = (EditText) findViewById(R.id.namOfTheRestaurant);
        phoneOfTheRestaurant = (EditText) findViewById(R.id.restPhoneNumber);
        uploadImageButton = (Button) findViewById(R.id.uploadAnImage);
        imageViewMenu = (ImageView) findViewById(R.id.imageOfTheUploadedMenu);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.restOrCof, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        submitRestOrCoff.setOnClickListener(this);
        cancelRequest.setOnClickListener(this);
        uploadImageButton.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view == cancelRequest){
            Toast.makeText(SuggestARestaurant.this, "Canceling Request", Toast.LENGTH_SHORT).show();
            SuggestARestaurant.this.startActivity(new Intent(SuggestARestaurant.this, Homepage.class));
            finish();
        }
        if(view == uploadImageButton){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

        }
        if(view == submitRestOrCoff){
            String nameOfTheRest = namOfTheRestaurant.getText().toString().trim();
            String phoneNumber = phoneOfTheRestaurant.getText().toString().trim();

            if(TextUtils.isEmpty(nameOfTheRest)){
                Toast.makeText(SuggestARestaurant.this,"Please enter the name of the restaurant", Toast.LENGTH_SHORT).show();
                return;

            }
            if(TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(SuggestARestaurant.this,"Please enter your the restaurant phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            if(text.equals("")){
                Toast.makeText(SuggestARestaurant.this,"Please enter your the restaurant category", Toast.LENGTH_SHORT).show();
                return;
            }
            if(selectedImage == null){
                Toast.makeText(SuggestARestaurant.this,"Please upload the restaurants menu", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(SuggestARestaurant.this, "Thank you for submiting a request", Toast.LENGTH_SHORT).show();
            finish();
            SuggestARestaurant.this.startActivity(new Intent(SuggestARestaurant.this, Homepage.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == RESULT_LOAD_IMAGE &&  resultCode == RESULT_OK &&  data != null){
            selectedImage = data.getData();
            imageViewMenu.setImageURI(selectedImage);
        }
    }
}
