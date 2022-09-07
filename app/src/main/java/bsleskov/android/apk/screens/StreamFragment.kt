package bsleskov.android.apk.screens

import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import bsleskov.android.apk.Preference
import bsleskov.android.apk.R
import com.haishinkit.event.Event
import com.haishinkit.event.EventUtils
import com.haishinkit.event.IEventListener
import com.haishinkit.media.Camera2Source
import com.haishinkit.rtmp.RtmpConnection
import com.haishinkit.rtmp.RtmpStream
import com.haishinkit.view.HkView

class StreamFragment : Fragment(R.layout.fragment_stream), IEventListener {
    private lateinit var connection: RtmpConnection
    private lateinit var stream: RtmpStream
    private lateinit var cameraView: HkView
    private lateinit var cameraSource: Camera2Source
    private lateinit var statusTextView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            val permissionCheck = ContextCompat.checkSelfPermission(it, android.Manifest.permission.CAMERA)
            if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.CAMERA), 1)
            }

        }


        connection = RtmpConnection()
        stream = RtmpStream(connection)
        cameraSource = Camera2Source(requireContext())
        stream.attachVideo(cameraSource)
        connection.addEventListener(Event.RTMP_STATUS, this)

    }

    //@SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_stream, container, false)
         statusTextView = v.findViewById<TextView>(R.id.status_textView)
        val streamButton = v.findViewById<ImageButton>(R.id.stream_button)

        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.stream)

        streamButton.setOnClickListener {
            if (!connection.isConnected) {
                connection.connect(Preference.shared.rtmpURL)
                    streamButton.setBackgroundResource(R.drawable.stop)
                    statusTextView.visibility = View.VISIBLE


            } else {
                connection.close()
                streamButton.setBackgroundResource(R.drawable.record)
                statusTextView.visibility = TextView.INVISIBLE;
            }
        }

            stream.videoEffect = null

        cameraView = if (Preference.useSurfaceView) {
            v.findViewById(R.id.surface_view)
        } else {
            v.findViewById(R.id.texture_view)
        }
        cameraView.attachStream(stream)
        return v
    }



    override fun onResume() {
        super.onResume()
        cameraSource.open(CameraCharacteristics.LENS_FACING_BACK)
    }

    override fun onPause() {
        cameraSource.close()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        connection.dispose()
    }

    override fun handleEvent(event: Event) {
        Log.i("$TAG#handleEvent", event.toString())
        val data = EventUtils.toMap(event)
        val code = data["code"].toString()
        if (code == RtmpConnection.Code.CONNECT_SUCCESS.rawValue) {
            stream.publish(Preference.shared.streamName)

        }
    }



    companion object {
        fun newInstance(): StreamFragment {
            return StreamFragment()
        }

        private val TAG = StreamFragment::class.java.simpleName
    }
}




