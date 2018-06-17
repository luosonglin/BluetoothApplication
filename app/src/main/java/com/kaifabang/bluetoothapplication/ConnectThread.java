//package com.kaifabang.bluetoothapplication;
//
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.util.Log;
//
//import java.io.IOException;
//
//
///**
// * Created by luosonglin on 6/17/18.
// */
//
//private class ConnectThread extends Thread {
//
//    final String TAG = ConnectThread.class.getSimpleName();
//
//    private BluetoothSocket mmSocket;
//    private final BluetoothDevice mmDevice;
//    public ConnectThread(BluetoothDevice device) {
//        mmDevice = device;
//        BluetoothSocket tmp = null;
//        // 得到一个bluetoothsocket
//        try {
//            mmSocket = device.createRfcommSocketToServiceRecord
//                    (SERVICE_UUID);
//        } catch (IOException e) {
//            Log.e(TAG, "create() failed", e);
//            mmSocket = null;
//        }
//    }
//
//    public void run() {
//        Log.i(TAG, "BEGIN mConnectThread");
//        try {
//            // socket 连接,该调用会阻塞，直到连接成功或失败
//            mmSocket.connect();
//        } catch (IOException e) {
//            connectionFailed();
//            try {//关闭这个socket
//                mmSocket.close();
//            } catch (IOException e2) {
//                e2.printStackTrace();
//            }
//            return;
//        }
//        // 启动连接线程
//        connected(mmSocket, mmDevice);
//    }
//
//    public void cancel() {
//        try {
//            mmSocket.close();
//        } catch (IOException e) {
//            Log.e(TAG, "close() of connect socket failed", e);
//        }
//    }
//}