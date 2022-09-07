package bsleskov.android.apk.screens

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import bsleskov.android.apk.R
import bsleskov.android.apk.data.latitudeKey
import bsleskov.android.apk.data.longitudeKey
import bsleskov.android.apk.data.numberKey
import bsleskov.android.apk.data.plateCodeKey

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment() {


    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val latitude = arguments?.getDouble(latitudeKey)
        val longitude = arguments?.getDouble(longitudeKey)
        val number = arguments?.getString(numberKey)
        val plateCode = arguments?.getString(plateCodeKey)
        val archiveItemCoordinates = LatLng(latitude!!, longitude!!)
        googleMap.addMarker(MarkerOptions().position(archiveItemCoordinates).title(number + plateCode))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(archiveItemCoordinates, 17f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


}
