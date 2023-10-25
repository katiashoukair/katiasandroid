package com.example.katiasproject.Admin;

import static com.example.katiasproject.DataTables.TablesString.ProductTable.*;

import static java.lang.Double.*;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.katiasproject.classes.Product;
import com.example.katiasproject.DataTables.DBHelper;
import com.example.katiasproject.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etname, etdisc, etstock, etprice, etcolor;
    ImageButton imageButton;
    Button btadd;
    Product p;
    Uri selectedImageUri;
    DBHelper dbHelper;
    ProgressBar addItemProgressBar;
    Button btupdate;
    Button btdelete;
    byte[] image;
    boolean SelectedNewImage;
    String selectedId;

        protected byte[] imageByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etname = findViewById(R.id.etProdName);
        etdisc = findViewById(R.id.etDesc);
        etstock = findViewById(R.id.etStock);
        etprice = findViewById(R.id.etPrice);
        etcolor = findViewById(R.id.etColor);
        imageButton = findViewById(R.id.imageButton);
        btadd = findViewById(R.id.addButton);
        btadd.setOnClickListener(this);
        btupdate = findViewById(R.id.btupdate);
        btupdate.setOnClickListener(this);
        btdelete = findViewById(R.id.btdelete);
        btdelete.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        addItemProgressBar = findViewById(R.id.addItemProgressBar);
        dbHelper = new DBHelper(this);
        dbHelper.OpenWriteAble();

        Intent i = getIntent();
        if (i.getStringExtra("Selected_Id") == null) {
            btdelete.setVisibility(View.GONE);
            btupdate.setVisibility(View.GONE);
        }
        //change
        else {
            btadd.setVisibility(View.GONE);
            selectedId = i.getStringExtra("Selected_Id");
            setProduct();
        }

    }

    private void setProduct() {

        dbHelper.OpenReadAble();
        p = new Product(p);
        Cursor c = p.SelectById(dbHelper.getDb(), selectedId);
        if (c != null) {
            c.moveToFirst();
            etname.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME)));
            etdisc.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            etprice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE)));
            etstock.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));
            image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            etcolor.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));

            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageButton.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addButton) {
            addItemProgressBar.setVisibility(View.VISIBLE);
            dbHelper = new DBHelper(this);

            byte[] data = imageViewToByte();
            p = new Product(etname.getText().toString(), etdisc.getText().toString(),
                    Integer.parseInt(etstock.getText().toString()),
                    parseDouble(etprice.getText().toString()), 0,
                    etcolor.getText().toString(), data);
            dbHelper.OpenWriteAble();
            if (p.Add(dbHelper.getDb()) > -1) {
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
                Intent i = new Intent(this, ShowProduct.class);
                startActivity(i);
            }

        }
        if (view.getId() == R.id.imageButton) {
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }

        if(view.getId() == R.id.btupdate)

        {
            p.setPid(Integer.parseInt(selectedId));
            p.setProdname(etname.getText().toString());
            p.setProddisc(etdisc.getText().toString());
            p.setPrice(parseDouble(etprice.getText().toString()));

            p.setStock(Integer.parseInt(etstock.getText().toString()));
            if (SelectedNewImage)
                p.setImageByte(imageViewToByte());
            else
                p.setImageByte(image);
            dbHelper.OpenWriteAble();
            p.Update(dbHelper.getDb(), Integer.parseInt(selectedId));
            dbHelper.Close();
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ShowProduct.class);
            startActivity(i);
        }
        if(view.getId()==R.id.btdelete)

        {
            dbHelper.OpenWriteAble();
            p.Delete(dbHelper.getDb(), Integer.parseInt(selectedId));
            dbHelper.Close();
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ShowProduct.class);
            startActivity(i);
        }
    }
    public byte[] imageViewToByte() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() ,selectedImageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);
            SelectedNewImage = true;
        }
    }
}
