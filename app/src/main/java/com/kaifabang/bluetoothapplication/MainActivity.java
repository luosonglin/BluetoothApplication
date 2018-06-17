package com.kaifabang.bluetoothapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();

    //传递给 startActivityForResult() 的 REQUEST_ENABLE_BT 常量是在局部定义的整型（必须大于 0），
    //系统会将其作为 requestCode 参数传递回您的 onActivityResult() 实现。
    static final int REQUEST_ENABLE_BT = 1;

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取 BluetoothAdapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        //启用蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Log.i(TAG, mBluetoothAdapter.getName()
                + " " + mBluetoothAdapter.getAddress());

        //获取已配对蓝牙设备
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        Log.d(TAG, "bonded device size =" + devices.size());
        for (BluetoothDevice bonddevice : devices) {
            Log.d(TAG, "bonded device name =" + bonddevice.getName() + " address" + bonddevice.getAddress());
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, requestCode + " " + resultCode); //成功启动，返回1 -1

    }


    public void search(View view) {
        //搜索设备
        mBluetoothAdapter.startDiscovery();


        IntentFilter filter = new IntentFilter();
        //发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        //设备连接状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothReceiver, filter);

    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "mBluetoothReceiver action =" + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {//每扫描到一个设备，系统都会发送此广播。
                //获取蓝牙设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device == null || device.getName() == null) return;
                Log.d(TAG, "name=" + device.getName() + "address=" + device.getAddress());
                //蓝牙设备名称
//                String name = scanDevice.getName();
//                if (name != null && name.equals(BLUETOOTH_NAME)) {
//                    //停止搜索
//                    mBluetoothAdapter.cancelDiscovery();
//                    //取消扫描
//                    mProgressDialog.setTitle(getResources().getString(R.string.progress_connecting));                    //连接到设备。
//                    mBlthChatUtil.connect(scanDevice);
//                }
                Log.i(TAG, device.getName() + " " + device.getAddress() + " " + device.getBondState() + " " + device.getType() + " " + device.getUuids());


//                devices.add(device);
//                deviceName.add(device.getName());
//                arrayAdapter.notifyDataSetChanged();

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Toast.makeText(MainActivity.this, "扫描完毕", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
