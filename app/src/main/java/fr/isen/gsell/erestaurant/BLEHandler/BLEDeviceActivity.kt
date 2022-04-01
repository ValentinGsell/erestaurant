package fr.isen.gsell.erestaurant.BLEHandler

import android.bluetooth.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.gsell.erestaurant.R
import fr.isen.gsell.erestaurant.databinding.ActivityBledeviceBinding
import fr.isen.gsell.erestaurant.databinding.ActivityBlescanBinding

class BLEDeviceActivity : AppCompatActivity() {
    private var bluetoothGatt: BluetoothGatt? = null
    private lateinit var binding: ActivityBledeviceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bledevice)

        binding = ActivityBledeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val device = intent.getParcelableExtra<BluetoothDevice?>(BLEScanActivity.DEVICE_KEY)
        binding.deviceName.text = device?.name ?: "Nom inconnu"
        binding.deviceStatus.text = getString(R.string.ble_device_disconnected)

        connectToDevice(device)
    }

    private fun connectToDevice(device: BluetoothDevice?) {
        device?.connectGatt(this, true, object: BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServicesDiscovered(gatt, status)
                val bleServices =
                gatt?.services?.map { BLEService(it.uuid.toString(), it.characteristics) } ?: listOf()
                val adapter = BleServiceAdapter(bleServices as MutableList<BLEService>)
                runOnUiThread{
                    binding.serviceList.layoutManager = LinearLayoutManager(this@BLEDeviceActivity)
                    binding.serviceList.adapter = adapter as RecyclerView.Adapter<*>
                }
            }

            override fun onCharacteristicRead(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                super.onCharacteristicRead(gatt, characteristic, status)
            }
        })
        bluetoothGatt?.connect()
    }

    override fun onStop() {
        super.onStop()
        closeBluetoothGatt()
    }

    private fun closeBluetoothGatt() {
        bluetoothGatt?.close()
        bluetoothGatt = null
    }

    private fun connectionStateChance(gatt: BluetoothGatt?, newState: Int){
        val state = if(newState == BluetoothProfile.STATE_CONNECTED) {
            gatt?.discoverServices()
            getString(R.string.ble_device_connected)
        } else {
            getString(R.string.ble_device_disconnected)
        }
        binding.deviceStatus.text = state
    }
}