package fr.isen.gsell.erestaurant.BLEHandler

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.gsell.erestaurant.R

internal class BleAdapter(private var bleList: ArrayList<ScanResult>, val clickListener: (BluetoothDevice) -> Unit) :
    RecyclerView.Adapter<BleAdapter.MyViewHolder>(){
        internal inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            var macAddress: TextView = view.findViewById(R.id.macAddress)
            var deviceName: TextView = view.findViewById(R.id.deviceName)
            var rssi: TextView = view.findViewById(R.id.signalStrength)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ble_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ble = bleList[position]
        holder.deviceName.text = ble.device.name
        holder.macAddress.text = ble.device.address
        holder.rssi.text = ble.rssi.toString()

        holder.itemView.setOnClickListener{
            clickListener(ble.device)
        }
    }

    fun addElementToList(scanResult: ScanResult){
        val index: Int = bleList.indexOfFirst { it.device.address == scanResult.device.address }
        if(bleList.indexOf(scanResult) == -1){
            bleList.add(scanResult)
        }
        else{
            bleList[index] = scanResult
        }
        bleList.sortBy { kotlin.math.abs(it.rssi) }

    }

    override fun getItemCount(): Int {
        return bleList.size
    }
}