package com.mzhadan.phoneaccounting

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.telephony.TelephonyManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mzhadan.phoneaccounting.databinding.ActivityMainBinding
import com.mzhadan.phoneaccounting.models.*
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.ui.fragments.phonelist.PhoneListFragment
import com.mzhadan.phoneaccounting.ui.fragments.sdcardlist.SdcardListFragment
import com.mzhadan.phoneaccounting.ui.fragments.simcardlist.SimcardListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStartFragment()
        setupBottomNavigationView()

//        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
////        mainViewModel.phoneInfoList.observe(this) {
////            if (it != null) {
////                it.forEach {
////                    Log.d("PhoneInfo ->", "PhoneInfo")
////                    Log.d("PhoneInfo ->", "model -> ${it.model}")
////                    Log.d("PhoneInfo ->", "manufacturer -> ${it.manufacturer}")
////                    Log.d("PhoneInfo ->", "osVersion -> ${it.osVersion}")
////                    Log.d("PhoneInfo ->", "firmWare -> ${it.firmWare}")
////                    Log.d("PhoneInfo ->", "supportedArch -> ${it.supportedArch}")
////                    Log.d("PhoneInfo ->", "user -> ${it.user}")
////                    Log.d("PhoneInfo ->", "simSlotsCount -> ${it.simSlotsCount}")
////                    Log.d("PhoneInfo ->", "simcard1 -> ${it.simcard1}")
////                    Log.d("PhoneInfo ->", "simcard2 -> ${it.simcard2}")
////                    Log.d("PhoneInfo ->", "sdSlotsCount -> ${it.sdSlotsCount}")
////                    Log.d("PhoneInfo ->", "sdcard -> ${it.sdcard}")
////                }
////            } else {
////                Toast.makeText(this, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
////            }
////        }
//
//        val telephonyManager: TelephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
//
//        val systemInfo = getPhoneSystemInfo()
//        val simInfo = getSimCardsInfo(telephonyManager)
//        val sdInfo = getSDCardInfo()
//
//        val textView: TextView = findViewById(R.id.textview)
//        val button: Button = findViewById(R.id.btn)
//        var counter = 1
//        val phoneInfo = getPhoneInfo(systemInfo, simInfo, sdInfo)
//        button.setOnClickListener {
//            mainViewModel.getPhoneInfoById(counter)
//            counter++
//        }
//        mainViewModel.phoneInfoList.observe(this) {
//            if (it != null) {
//                textView.text = "${it[0].manufacturer} ${it[0].model}"
//            } else {
//                Toast.makeText(this, "Failed to fetch data!", Toast.LENGTH_SHORT).show()
//            }
//        }
////        mainViewModel.addNewPhoneData(phoneInfo)
//
//        Log.d("SystemInfo","SystemInfo")
//        Log.d("Model -> ", systemInfo.model)
//        Log.d("Manufacturer -> ", systemInfo.manufacturer)
//        Log.d("OSVersion -> ", systemInfo.osVersion)
//        Log.d("FirmWare -> ", systemInfo.firmWare)
//        Log.d("SupportedArch -> ", systemInfo.supportedArch)
//
//        Log.d("SimCardInfo","SimCardInfo")
//        Log.d("SimHubsCount -> ", simInfo.simSlotsCount.toString())
//        simInfo.simCards.forEach {
//            Log.d("SimSlotIndex -> ", it.slotIndex.toString())
//            Log.d("SimIsInserted -> ", it.isInserted.toString())
//            Log.d("SimState -> ", it.simState)
//            Log.d("SimPhoneNumber -> ", it.simPhoneNumber)
//        }
//
//        Log.d("SDCardInfo","SDCardInfo")
//        Log.d("HaveTray -> ", sdInfo.haveSlot.toString())
//        Log.d("IsInserted -> ", sdInfo.isInserted.toString())
//        Log.d("sdCardSize -> ", sdInfo.sdCardSize.toString())
//        Log.d("serialNumber -> ", sdInfo.serialNumber)
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
        val phoneArch = System.getProperty("os.arch")
        return SystemInfo(model, manufacturer, osVersion, firmWare, supportedArch)
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
            simCardsInfo.add(SimInfo(i, inPhone, stringSimState, ""))
        }

        return SimCardsInfo(simCardsInfo, simSlotsCount)
    }

    private fun getSDCardInfo(): SDInfo {
        val haveSlot = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
//        val inPhone
//        val size
//        val serialNumber

        return SDInfo(true, true, 0, "")
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

    private fun getPhoneInfo(systemInfo: SystemInfo, simInfo: SimCardsInfo, sdInfo: SDInfo): PhoneInfo {
        val sdSlots = if (sdInfo.haveSlot) 1 else 0
        var simcard1 = ""
        var simcard2 = ""
//        for (i in simInfo.simCards.indices) {
//            if (i == 0) {
//                simcard1 = simInfo.simCards[i].simPhoneNumber
//            }
//            if (i == 1) {
//                simcard2 = simInfo.simCards[i].simPhoneNumber
//            }
//        }

        return PhoneInfo(8, systemInfo.model, systemInfo.manufacturer, systemInfo.osVersion,
            systemInfo.firmWare, systemInfo.supportedArch, "", simInfo.simSlotsCount,
            "", "", sdSlots, "")

    }
}