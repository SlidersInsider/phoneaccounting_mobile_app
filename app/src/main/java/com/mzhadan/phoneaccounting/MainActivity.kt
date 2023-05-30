package com.mzhadan.phoneaccounting

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import android.provider.ContactsContract
import android.provider.Settings
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.mzhadan.phoneaccounting.databinding.ActivityMainBinding
import com.mzhadan.phoneaccounting.models.*
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.ui.fragments.notificationlist.NotificationListFragment
import com.mzhadan.phoneaccounting.ui.fragments.phonelist.PhoneListFragment
import com.mzhadan.phoneaccounting.ui.fragments.sdcardlist.SdcardListFragment
import com.mzhadan.phoneaccounting.ui.fragments.simcardlist.SimcardListFragment
import dagger.hilt.android.AndroidEntryPoint
import java.security.Key

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val PREFS_NAME = "StartPrefs"
    private val KEY_FIRST_RUN = "FirstRun"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean(KEY_FIRST_RUN, true)

        requestAllPermissions()

        if (isFirstRun) {
            getAllNeedPhoneInfo()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(KEY_FIRST_RUN, false)
            editor.apply()
        }

        setupStartFragment()
        setupBottomNavigationView()

    }

    private fun isNotificationListenerEnabled(): Boolean {
        val flat = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        return flat != null && flat.contains(packageName)
    }

    private fun requestAllPermissions() {
        if (!isNotificationListenerEnabled()) {
            val notificationIntent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(notificationIntent)
        }
        val permissions = getAllManifestPermissions()
        val permissionsToRequest = mutableListOf<String>()
        for (perm in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    perm
            ) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(perm)
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            val permsArray = permissionsToRequest.toTypedArray()
            ActivityCompat.requestPermissions(this, permsArray, 1)
        }
    }

    private fun getAllManifestPermissions(): Array<String> {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
            return info.requestedPermissions
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return emptyArray()
    }

    @SuppressLint("MissingPermission")
    private fun getAllNeedPhoneInfo() {
        val telephonyManager: TelephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val systemInfo = getPhoneSystemInfo()
        val user = "free"
        val simInfo = getSimCardsInfo(telephonyManager)
        var simcard1 = ""
        var simcard2 = ""
        for (i in 0 until simInfo.simSlotsCount) {
            if (i == 0) {
                simcard1 = simInfo.simCards[i].simPhoneNumber
            } else if (i == 1) {
                simcard2 = simInfo.simCards[i].simPhoneNumber
            }
        }
        val sdInfo = getSDCardInfo()
        val sdCount = if (sdInfo.haveSlot) {
            1
        } else {
            0
        }
        val phoneInfo = PhoneInfo(0, systemInfo.model, systemInfo.manufacturer,
            systemInfo.osVersion, systemInfo.firmWare, systemInfo.supportedArch, user,
            simInfo.simSlotsCount, simcard1, simcard2, sdCount, sdInfo.name)

        mainViewModel.addNewPhoneData(phoneInfo)
    }

    private fun getPhoneSystemInfo(): SystemInfo {
        val model = Build.MODEL
        val manufacturer = Build.MANUFACTURER
        val osVersion = Build.VERSION.SDK_INT.toString()
        val osVersionConverted = Build.VERSION.RELEASE.toString()
        val firmWare = Build.getRadioVersion()
        var supportedArch = ""
        Build.SUPPORTED_ABIS.forEach {
            supportedArch += "$it,"
        }
        return SystemInfo(model, manufacturer, osVersion, osVersionConverted, firmWare, supportedArch)
    }

    @SuppressLint("MissingPermission")
    private fun getSimCardsInfo(telephonyManager: TelephonyManager): SimCardsInfo {
        val simSlotsCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            telephonyManager.activeModemCount
        } else {
            telephonyManager.phoneCount
        }

        val simCardsInfo = ArrayList<SimInfo>()
        for (i in 0 until simSlotsCount) {
            val simState = telephonyManager.getSimState(i)
            val inPhone = checkIsInsert(simState)
            val stringSimState = convertSimStateToString(simState)
            val simPhoneNumber = getPhoneNumber(i)
            simCardsInfo.add(SimInfo(i, inPhone, stringSimState, simPhoneNumber))
        }
        return SimCardsInfo(simCardsInfo, simSlotsCount)
    }

    private fun getPhoneNumber(slotPos: Int): String {
        val selection = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY} = ?"
        val selectionArgs = arrayOf("Phone${slotPos+1}")
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        var phoneNumber = "no number"
        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                phoneNumber = it.getString(index)
            }
        }
        cursor?.close()
        return phoneNumber
    }

    private fun setupBottomNavigationView() {
        binding.mainBottomNavigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.phoneListPageMenuItem -> {
                    openFragment(PhoneListFragment(), "PhoneListFragment")
                    true
                }
                R.id.simcardListPageMenuItem -> {
                    openFragment(SimcardListFragment(), "SimcardListFragment")
                    true
                }
                R.id.sdcardListPageMenuItem -> {
                    openFragment(SdcardListFragment(), "SdcardListFragment")
                    true
                }
                R.id.notificationListPageMenuItem -> {
                    openFragment(NotificationListFragment(), "NotificationListFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun setupStartFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, PhoneListFragment(), "PhoneListFragment")
            .commit()
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment, tag)
            .commit()
    }

    private fun getSDCardInfo(): SDInfo {
        val storageManager = getSystemService(STORAGE_SERVICE) as StorageManager
        val storageVolumes: List<StorageVolume> = storageManager.storageVolumes

        storageVolumes.forEach {
            if (it.isRemovable) {
                val haveSlot = true
                val isInserted = true
                val name = it.getDescription(this)
                val size = getSdCardTotalSize()
                val serialNumber = it.uuid.toString()
                return SDInfo(haveSlot, isInserted, name, size, serialNumber)
            }
        }

        return SDInfo(false, false, "no sdcard", 0, "")
    }

    private fun getSdCardTotalSize(): Int {
        val sdDir = Environment.getExternalStorageDirectory()
        val statFs = StatFs(sdDir.path)
        val blockSize = statFs.blockSizeLong
        val totalBlocks = statFs.blockCountLong
        val totalSizeByte = blockSize * totalBlocks
        val totalSizeGB = totalSizeByte / (1024.0 * 1024.0 * 1024.0)

        return totalSizeGB.toInt()
    }

    private fun checkIsInsert(state: Int): Boolean {
        return when(state) {
            1 -> false
            else -> true
        }
    }

    private fun convertSimStateToString(state: Int): String {
        return when(state) {
            0 -> "Unknown"
            1 -> "Absent"
            2 -> "Pin required"
            3 -> "Puk required"
            4 -> "Network locked"
            5 -> "Ready"
            6 -> "Not Ready"
            7 -> "Perm disabled"
            8 -> "Card io error"
            else -> "Card restricted"
        }
    }
}