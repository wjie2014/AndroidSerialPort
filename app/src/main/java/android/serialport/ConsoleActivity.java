/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package android.serialport;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ConsoleActivity extends SerialPortActivity {

    EditText mReception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.console);

//		setTitle("Loopback test");
        mReception = (EditText) findViewById(R.id.EditTextReception);

        EditText Emission = (EditText) findViewById(R.id.EditTextEmission);
        Emission.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int i;
                CharSequence t = v.getText();
                char[] text = new char[t.length()];
                for (i = 0; i < t.length(); i++) {
                    text[i] = t.charAt(i);
                }
                try {
                    mOutputStream.write(new String(text).getBytes());
                    mOutputStream.write('\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    @Override
    protected void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (mReception != null) {
                    try {
                        Log.e("onDataReceived1", printHexString(buffer));
                        String result = printHexString(buffer);
                        mReception.append(result + "\n");

                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    @Override
    protected void onDataReceived2(final byte[] buffer,final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (mReception != null) {
                    try {
                        Log.e("onDataReceived2", printHexString(buffer));
                        String result = printHexString(buffer);
                        mReception.append(result + "\n");

                    } catch (Exception e) {

                    }
                }
            }
        });
    }


    /**
     * 将字节转换为hex字符串
     *
     * @param bytes
     * @return
     */
    public String printHexString(byte[] bytes) {
        String a = "";
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            a = a + hex;
        }
        String t = a.replaceAll("(00){1,}$", "");
        return t;
    }
}
