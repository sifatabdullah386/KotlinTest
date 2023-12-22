package com.smilesifat.kotlinboilerplate.view.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings.Secure
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.smilesifat.kotlinboilerplate.R
import java.util.Timer


open class BaseActivity : AppCompatActivity() {
    private var progressDialog: ProgressDialog? = null
    var isConnected = true
    private var monitoringConnectivity = false
    private var connectivityCallback: NetworkCallback? = null
    private val timer: Timer? = null
    private val timers: CountDownTimer? = null
    private var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this@BaseActivity)
        dialog = Dialog(this@BaseActivity)
        connectivityCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
                Log.d(ContentValues.TAG, "INTERNET CONNECTED")
            }
            override fun onLost(network: Network) {
                isConnected = false
                Log.d(ContentValues.TAG, "INTERNET LOST")
            }
        }
    }

    fun ShowLoading() {
        dialog?.setContentView(R.layout.custom_dialog_loader)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun HideLoading() {
        dialog!!.dismiss()
    }

    fun ShowAlert(msg: String?) {
        dialog = Dialog(this@BaseActivity)
        dialog!!.setContentView(R.layout.custom_alert_dialog)
        val message = dialog!!.findViewById<View>(R.id.message_string) as TextView
        val ok = dialog!!.findViewById<View>(R.id.ok) as TextView
        message.text = msg
        ok.setOnClickListener { dialog!!.dismiss() }
        dialog!!.show()
    }

    //Remove Html tag
    fun removeHtml(value: String): String {
        var value = value
        value = value.replace("<(.*?)>".toRegex(), " ")
        value = value.replace("<(.*?)\n".toRegex(), " ")
        value = value.replaceFirst("(.*?)>".toRegex(), " ")
        value = value.replace("&nbsp;".toRegex(), " ")
        value = value.replace("&amp;".toRegex(), " ")
        return value
    }

    //logout user
    fun Logout() {
//        sqLiteHandler.deleteUsers();
//        sessionManager.setLogin(false);
//        startActivity(new Intent(BaseActivity.this, LoginActivity.class));
//        finish();
    }

    val userInfo: Map<*, *>
        get() =//        user = sqLiteHandler.getUserDetails();
            HashMap<Any?, Any?>()

    @get:SuppressLint("HardwareIds")
    val deviceID: String
        get() = Secure.getString(baseContext.contentResolver, Secure.ANDROID_ID)

    //Retrofit error handler
    fun retrofitErrorHandler(integer: Int): String? {
        HideLoading()
        Log.d("Response Error", integer.toString())
        var message: String? = null
        if (integer.toString() == "400") {
            message = "Bad Request... Please check your Internet connection!!!!"
        } else if (integer.toString() == "401") {
            message = "Unauthorized...Please Login again!!"
        } else if (integer.toString() == "403") {
            message = "Forbidden Request... The client does not have access rights to the content!!"
        } else if (integer.toString() == "404") {
            message = "Not Found... The server can not find the requested resource!!"
        } else if (integer.toString() == "405") {
            message =
                "Method not Allowed...The request HTTP method is known by the server but has been disabled and cannot be used for that resource."
        } else if (integer.toString() == "408") {
            message = "Request Time Out... Please check your Internet connection!!"
        } else if (integer.toString() == "500") {
            message = "Internal server error... Please try again after some time!!"
        } else if (integer.toString() == "502") {
            message = "Bad Gateway... Please try again after some time!!"
        }
        ShowAlert(message)
        return message
    }

    // Null remove
    fun checkNull(value: String): String {
        var value = value
        if (value == "null") {
            value = ""
        }
        return value
    }

    private fun checkConnectivity() {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        if (!isConnected) {
            Log.d(ContentValues.TAG, "NO NETWORK!")
            val dialog = Dialog(applicationContext)
            dialog.setContentView(R.layout.layout_internet_connection)
            //            dialog.setTitle("Bla Bla");
//            ImageView image = (ImageView) dialog.findViewById(R.id.no_internet_image);
//            image.setImageResource(R.drawable.no_data_found);
            val dialogButton = dialog.findViewById<View>(R.id.try_again_button) as MaterialButton
            dialogButton.setOnClickListener { checkConnectivity() }
            dialog.show()
            connectivityManager.registerNetworkCallback(
                NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build(),
                connectivityCallback!!
            )
            monitoringConnectivity = true
        }
    }
}
