package fr.isen.gsell.erestaurant

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CarouselAdapter(val activity: AppCompatActivity, val images: ArrayList<String>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int =images.count()

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(images[position])
    }

}
