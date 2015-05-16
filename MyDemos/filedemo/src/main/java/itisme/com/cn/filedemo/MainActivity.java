package itisme.com.cn.filedemo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import org.apache.http.util.EncodingUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import itisme.com.cn.filedemo.Jni.SimpleOperation;


public class MainActivity extends Activity {

    private TextView textView;
    private String message = "welcome to android studio!";
    private String fileName = "hello.txt";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);
//        writeFileData(fileName,message);
//        textView.setText(readFileData(fileName));
        textView.setText(new SimpleOperation().add(2,3));
    }

    /**
     * write a message to a file through the name of the file
     * @param fileName
     * @param message
     */

    void writeFileData(String fileName, String message){
        FileOutputStream fout = null;
        try {
            fout = openFileOutput(fileName,MODE_PRIVATE);
            byte[] bytes = message.getBytes();
            try {
                fout.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fout!=null){
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get a string from a txt file
     * @param fileName
     * @return
     */

    String readFileData(String fileName){
        FileInputStream fin;
        String result = "";
        try {
            int length;
            fin = openFileInput(fileName);
            try {
                length = fin.available();
                byte[] buffer = new byte[length];
                fin.read(buffer);
                result = EncodingUtils.getString(buffer,"utf-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * create new file through path and fileName
     * @param path
     * @param fileName
     * @return
     */
    public File createFile(String path, String fileName){
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file = new File(path+fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        if(file!=null) {
            return file;
        }
        return null;
    }

    /**
     * delete all file on the particular folder
     * @param folderPath
     */

    public void deleteFolder(String folderPath){

        deleteAllFile(folderPath);
        String filePath = folderPath;
        File myFilePath = new File(filePath);
        myFilePath.delete();
    }

    /**
     * delete all file in the path
     * @param path
     */
    public void deleteAllFile(String path) {
        File file = new File(path);
        if(!file.exists()){
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for(int i =0; i < tempList.length; i++){
            if(path.endsWith(File.separator)){
                temp = new File(path +tempList[i]);
            }else{
                temp = new File(path + File.separator + tempList[i]);
            }
            if(temp.isFile()){
                temp.delete();
            }
            if(temp.isDirectory()){
                deleteAllFile(path+"/"+tempList[i]);//first of all ,delete the file all in the folder
                deleteFolder(path+"/"+tempList[i]);// meanwhile ,delete empty folder all
            }
        }
    }

}
