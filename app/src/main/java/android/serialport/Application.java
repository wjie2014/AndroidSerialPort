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

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;


import android.content.SharedPreferences;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class Application extends android.app.Application {

    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;
    private SerialPort mSerialPort2 = null;

    public SerialPort getSerialPort() throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort == null) {
            /* Read serial port parameters */
            SharedPreferences sp = getSharedPreferences("android_serialport_api.sample_preferences", MODE_PRIVATE);
            String path = sp.getString("DEVICE", "");
            int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));

			/* Check parameters */
//			if ( (path.length() == 0) || (baudrate == -1)) {
//				throw new InvalidParameterException();
//			}
////
//			/* Open the serial port */
//			mSerialPort = new SerialPort(new File(path), baudrate, 0);

            mSerialPort = new SerialPort(new File("/dev/ttyS3"), 9600, 0);

        }
        return mSerialPort;
    }


    public SerialPort getSerialPort2() throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort2 == null) {
            /* Read serial port parameters */
//			SharedPreferences sp = getSharedPreferences("android_serialport_api.sample_preferences", MODE_PRIVATE);
//			String path = sp.getString("DEVICE", "");
//			int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));

			/* Check parameters */
//			if ( (path.length() == 0) || (baudrate == -1)) {
//				throw new InvalidParameterException();
//			}
////
//			/* Open the serial port */
//			mSerialPort = new SerialPort(new File(path), baudrate, 0);

            mSerialPort2 = new SerialPort(new File("/dev/ttyS2"), 9600, 0);

        }
        return mSerialPort2;
    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }

    }

    public void closeSerialPort2() {
        if (mSerialPort2 != null) {
            mSerialPort2.close();
            mSerialPort2 = null;
        }

    }
}
