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
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import android_serialport_api.SerialPort;

public abstract class SerialPortActivity extends Activity {

    protected Application mApplication;
    protected SerialPort mSerialPort;
    protected SerialPort mSerialPort2;
    protected OutputStream mOutputStream;
    protected OutputStream mOutputStream2;
    private InputStream mInputStream;
    private InputStream mInputStream2;
    private ReadThread mReadThread;
    private ReadThread2 mReadThread2;

    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
//            while (true) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
                    if (size > 0) {
                        onDataReceived(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }  private class ReadThread2 extends Thread {

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
//            while (true) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream2 == null) return;
                    size = mInputStream2.read(buffer);
                    if (size > 0) {
                        onDataReceived2(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private void DisplayError(int resourceId) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Error");
        b.setMessage(resourceId);
        b.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SerialPortActivity.this.finish();
            }
        });
        b.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (Application) getApplication();


        try {
            mSerialPort = mApplication.getSerialPort();


            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();

			/* Create a receiving thread */
            mReadThread = new ReadThread();
            mReadThread.start();

        } catch (SecurityException e) {
            e.printStackTrace();
            DisplayError(R.string.error_security);
        } catch (IOException e) {
            DisplayError(R.string.error_unknown);
        } catch (InvalidParameterException e) {
            DisplayError(R.string.error_configuration);
        }

        try {
            mSerialPort2 = mApplication.getSerialPort2();


            mOutputStream2 = mSerialPort2.getOutputStream();
            mInputStream2 = mSerialPort2.getInputStream();

			/* Create a receiving thread */
            mReadThread2 = new ReadThread2();
            mReadThread2.start();

        } catch (SecurityException e) {
            e.printStackTrace();
            DisplayError(R.string.error_security);
        } catch (IOException e) {
            DisplayError(R.string.error_unknown);
        } catch (InvalidParameterException e) {
            DisplayError(R.string.error_configuration);
        }
    }

    protected abstract void onDataReceived(final byte[] buffer, final int size);
    protected abstract void onDataReceived2(final byte[] buffer, final int size);

    @Override
    protected void onDestroy() {
        if (mReadThread != null)
            mReadThread.interrupt();
        mApplication.closeSerialPort();
        mSerialPort = null;
        if (mReadThread2 != null)
            mReadThread2.interrupt();
        mApplication.closeSerialPort2();
        mSerialPort2 = null;
        super.onDestroy();
    }
}
