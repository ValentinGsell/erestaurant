package fr.isen.gsell.erestaurant.BLEHandler

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.gsell.erestaurant.MenuAdapter
import fr.isen.gsell.erestaurant.R
import fr.isen.gsell.erestaurant.databinding.ActivityBlescanBinding
import java.util.jar.Manifest

class BLEScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlescanBinding
    private var bleAdapter: BleAdapter?=null
    private var isScanning = false
    private val bleList = ArrayList<ScanResult>()

    private val bluetoothAdapter:BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlescanBinding.inflate(layoutInflater)
        setContentView(binding.root)



        when {
            bluetoothAdapter?.isEnabled  == true ->
                startScanBLEWithPermission(true)

            bluetoothAdapter != null ->
                askBluetoothPermission()

            else -> {
                displayBLEUnavailable()
            }
        }

        bleAdapter = BleAdapter(arrayListOf()) {
            val intent = Intent(this, BLEDeviceActivity::class.java)
            intent.putExtra(DEVICE_KEY, it)
            startActivity(intent)
        }

        binding.listBle.layoutManager = LinearLayoutManager(this)
        binding.listBle.adapter = bleAdapter

    }

    override fun onStop() {
        super.onStop()
        startScanBLEWithPermission(false)
    }

    private fun startScanBLEWithPermission(enable: Boolean) {
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            startScanBLE(enable)
            Log.d("La liste de ble :", bleList.toString())
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), ALL_PERMISSIONS_REQUEST_CODE)
        }
    }

    private fun startScanBLE(enable: Boolean) {

        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if(enable)
            {
                isScanning = true
                startScan(scanCallBack)
            }
            else{
                isScanning = false
                stopScan(scanCallBack)
            }
            handlePlayPauseAction()
        }
    }

    private val scanCallBack = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            (binding.listBle.adapter as BleAdapter).apply {
                addElementToList(result)
                notifyDataSetChanged()
            }

        }
    }


    private fun askBluetoothPermission() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.BLUETOOTH_CONNECT
        ) == PackageManager.PERMISSION_GRANTED)
        {
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)
        }
    }

    private fun displayBLEUnavailable() {
        binding.bleScanStateImg.isVisible = false
        binding.bleScanStateTitle.text = getString(R.string.ble_scan_device_error)
        binding.progressBar.isIndeterminate = false
    }



    private fun handlePlayPauseAction() {
        if (!isScanning) {
            binding.bleScanStateImg.setImageResource(R.drawable.ic_play)
            binding.bleScanStateTitle.text = getString(R.string.ble_scan_play)
            binding.progressBar.isIndeterminate = false
            binding.progressBar.isVisible = false
        } else {
            binding.progressBar.isVisible = true
            binding.bleScanStateImg.setImageResource(R.drawable.ic_pause)
            binding.bleScanStateTitle.text = getString(R.string.ble_scan_pause)
            binding.progressBar.isIndeterminate = true
        }
    }

    private fun addToList(res: ScanResult){
        val index: Int = bleList.indexOfFirst { it.device.address == res.device.address }
        if(index == -1){
            bleList.add(res)
        }else{
            bleList[index] = res
        }
        bleList.sortBy { kotlin.math.abs(it.rssi) }

    }

    companion object{
        private const val ENABLE_BLUETOOTH_REQUEST_CODE = 1
        private const val ALL_PERMISSIONS_REQUEST_CODE = 100
        const val DEVICE_KEY = "device"
    }
}