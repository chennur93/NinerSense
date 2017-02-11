package meghashyam.ninersense;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class IMsend extends Activity {
    /** Called when the activity is first created. */

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imsend);
        System.out.println("34");
        img = (ImageView) findViewById(R.id.ivPic);
        System.out.println("36");
        ((Button) findViewById(R.id.bBrowse))
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        System.out.println("40");
                        Intent intent = new Intent();
                        intent.setType("video/mp4");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(
                                Intent.createChooser(intent, "Select File"),
                                SELECT_PICTURE);
                        System.out.println("47");
                    }
                });
        ;
        System.out.println("51");
        Button send = (Button) findViewById(R.id.bSend);
        final TextView status = (TextView) findViewById(R.id.tvStatus);

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Socket sock;
                try {
                    sock = new Socket("192.168.1.3", 1149);
                    System.out.println("Connecting...");

                    // sendfile
                    File myFile = new File (selectedImagePath);
                    byte [] mybytearray  = new byte [(int)myFile.length()];
                    FileInputStream fis = new FileInputStream(myFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    bis.read(mybytearray,0,mybytearray.length);
                    OutputStream os = sock.getOutputStream();
                    System.out.println("Sending...");
                    os.write(mybytearray,0,mybytearray.length);
                    os.flush();

                    sock.close();
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                TextView path = (TextView) findViewById(R.id.tvPath);
                path.setText("Image Path : " + selectedImagePath);
                img.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}