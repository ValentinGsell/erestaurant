package fr.isen.gsell.erestaurant.BLEHandler

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import fr.isen.gsell.erestaurant.R

class BleServiceAdapter(bleService: List<BLEService>):
    ExpandableRecyclerViewAdapter<BleServiceAdapter.ServiceViewHolder, BleServiceAdapter.CharacteristicViewHolder>(bleService) {

    class ServiceViewHolder(itemView: View) : GroupViewHolder(itemView) {
        var serviceName: TextView = itemView.findViewById(R.id.serviceName)
        var serviceUUID: TextView = itemView.findViewById(R.id.serviceUUID)
        var serviceArrow: ImageView = itemView.findViewById(R.id.serviceArrow)
    }

    class CharacteristicViewHolder(itemView: View): ChildViewHolder(itemView){
        var characteristicName: TextView = itemView.findViewById(R.id.characteristicName)
        var characteristicUUID: TextView = itemView.findViewById(R.id.characteristicUUID)
        var readAction: Button = itemView.findViewById(R.id.ble_device_charac_read)
        var writeAction: Button = itemView.findViewById(R.id.ble_device_charac_write)
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_ble_service, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun onCreateChildViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacteristicViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindChildViewHolder(
        holder: CharacteristicViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        val characteristic = group.items[childIndex] as BluetoothGattCharacteristic
        holder.characteristicName.text =characteristic.uuid.toString()
    }

    override fun onBindGroupViewHolder(
        holder: ServiceViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {

    }
}