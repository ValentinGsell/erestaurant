package fr.isen.gsell.erestaurant.BLEHandler

import android.bluetooth.BluetoothGattCharacteristic
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class BLEService(val name: String, characteristics: MutableList<BluetoothGattCharacteristic>):
        ExpandableGroup<BluetoothGattCharacteristic>(name, characteristics)