package com.tech.disablecopypaste.secureView

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import android.text.InputType
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.tech.disablecopypaste.secureView.SecureConst.GBOARD
import com.tech.disablecopypaste.secureView.SecureConst.SAMSUNGBOARD_NEW
import com.tech.disablecopypaste.secureView.SecureConst.SAMSUNGBOARD_OLD
import com.tech.disablecopypaste.secureView.SecureConst.SWIFTBOARD

/*
  Copyright (C) Downloader - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh  on 21,December,2022 at 12:27 AM for Downloader.
  New Delhi,India
 */


object Utils {
    var NEW_TYPE_NUMBER_VARIATION_PASSWORD : Int=18

    fun getInputType(editText: TextInputEditText) : Int{
        val inputType: Int = editText.getInputType()
        when (inputType) {
            InputType.TYPE_TEXT_FLAG_CAP_WORDS or InputType.TYPE_CLASS_TEXT -> {
                return InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            }
            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT -> {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS -> {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_CLASS_TEXT -> {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            NEW_TYPE_NUMBER_VARIATION_PASSWORD -> {
                return TYPE_CLASS_NUMBER or TYPE_NUMBER_VARIATION_PASSWORD
            }
            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or InputType.TYPE_CLASS_TEXT -> {
                return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            InputType.TYPE_CLASS_PHONE -> {
                return InputType.TYPE_CLASS_PHONE
            }
            InputType.TYPE_CLASS_DATETIME -> {
                return InputType.TYPE_CLASS_DATETIME
            }
            InputType.TYPE_CLASS_NUMBER -> {
                return InputType.TYPE_CLASS_NUMBER
            }
            InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS -> {
                return InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
            }
            else -> {
                return editText.inputType
            }
        }
    }

}