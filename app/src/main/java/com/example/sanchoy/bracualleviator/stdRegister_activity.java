package com.example.sanchoy.bracualleviator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class stdRegister_activity extends Activity {
    EditText etID;
    EditText etEmail;
    EditText etPass;
    Bitmap IdCardImage;

    private static int LOAD_IMAGE_RESULTS = 1;
    ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_register_activity);
        imv = (ImageView) findViewById(R.id.imvfrmGallary);
        etID = (EditText) findViewById(R.id.stdID);
        etEmail = (EditText) findViewById(R.id.stdEmail);
        etPass = (EditText) findViewById(R.id.facultyName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.

        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            //getting the image path
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            //storing bitmap with the help of image path
            IdCardImage = BitmapFactory.decodeFile(imagePath);
            // Now we need to set the GUI ImageView data with data read from the picked file.
            imv.setImageBitmap(IdCardImage);

            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }

    /*Image onClick*/
    public void upImgFrmGlry(View view) {
        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i, LOAD_IMAGE_RESULTS);
    }

    /*SUBMIT Button onClick*/
   public void studentSubmit(View v)
    {
        //getting from edit texts
        String stID = etID.getText().toString();
        String stEmail = etEmail.getText().toString();
        String stPass = etPass.getText().toString();

        /*//image storing in byte array with byte streaming and creating parse file with it
        ByteArrayOutputStream bstream = new ByteArrayOutputStream();
        IdCardImage.compress(Bitmap.CompressFormat.PNG, 100, bstream);
        byte[] imageByteData = bstream.toByteArray();
        ParseFile imgFile = new ParseFile ("idImage.png", imageByteData);
        imgFile.saveInBackground();
*/

        //parse user object
        ParseUser userStudent = new ParseUser();
        userStudent.setUsername(stID);
        userStudent.setPassword(stPass);
        userStudent.setEmail(stEmail);
        //userStudent.put("stdIdCardImg", imgFile);//saved parse file including image saved in user

        userStudent.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(stdRegister_activity.this,"Congratulation",Toast.LENGTH_LONG).show();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(stdRegister_activity.this," "+e.toString()+" ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }//onClick_submit_button

}