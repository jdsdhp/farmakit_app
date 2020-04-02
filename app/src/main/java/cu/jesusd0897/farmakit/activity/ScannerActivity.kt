package cu.jesusd0897.farmakit.activity

import android.Manifest.permission.CAMERA
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.zxing.Result
import cu.jesusd0897.farmakit.R
import cu.jesusd0897.farmakit.database.repo.ProductRepo
import cu.jesusd0897.farmakit.util.KAlert
import cu.jesusd0897.farmakit.util.KNav
import cu.jesusd0897.farmakit.util.KUtil
import cu.jesusd0897.farmakit.util.REQUEST_CODE_CAMERA
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : BaseActivity(), ZXingScannerView.ResultHandler {

    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        scannerView.setResultHandler(this)
        KUtil.requestDialogPermissions(this, CAMERA, REQUEST_CODE_CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, results: IntArray
    ) {
        var allGrant = true
        for (result in results) if (result != PackageManager.PERMISSION_GRANTED) {
            allGrant = false
            break
        }
        if (allGrant) when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                scannerView.startCamera()
                return
            }
        } else {
            Toast.makeText(this, R.string.permissions_required, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    override fun handleResult(result: Result) {
        ProductRepo(this).findByCode("%${result.text}%").observe(this, Observer { product ->
            if (product != null) {
                KNav.navToProductDetail(this@ScannerActivity, product.id)
                finish()
            } else KAlert.buildDialog(
                this@ScannerActivity, R.string.code_scanner, R.string.scanner_no_results, null,
                R.string.accept, R.drawable.ic_qrcode, false, null,
                DialogInterface.OnClickListener { _, _ -> finish() }
            ).show()
        })

    }
}