package com.tech.disablecopypaste.secureView

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.InputType
import android.util.AttributeSet
import android.view.*
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.tech.disablecopypaste.secureView.Utils.NEW_TYPE_NUMBER_VARIATION_PASSWORD


/*
  Copyright (C)  - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh  on 20,December,2022 at 9:49 PM for Downloader.
  New Delhi,India
 */


open class SecureInputView : TextInputEditText {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    override fun getAutofillType(): Int {
        return AUTOFILL_TYPE_NONE
    }

    public override fun isSuggestionsEnabled() : Boolean
    {
        return false
    }

    override fun onTextContextMenuItem(id: Int): Boolean {
        when (id) {
            android.R.id.paste, android.R.id.pasteAsPlainText -> return false
        }
        return super.onTextContextMenuItem(id)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action==MotionEvent.ACTION_DOWN){
            setInsertionDisabled()
        }
        return super.onTouchEvent(event)
    }
    private fun setInsertionDisabled() {
        try {
            val field= TextView::class.java.getDeclaredField("mEditor")
            field.isAccessible=true
            val obj=field.get(this)
            @SuppressLint("PrivateApi") val editorClass = Class.forName("android.widget.Editor")
            val classfields=editorClass.getDeclaredField("mInsertionControllerEnabled")
            classfields.isAccessible=true
            classfields.set(obj,false)
        }catch (e:Exception){e.printStackTrace()}

    }

    @SuppressLint("NewApi")
    public fun init(context: Context, attrs: AttributeSet?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            this.customInsertionActionModeCallback= object : ActionMode.Callback{
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }
                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }
                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    return false
                }
                override fun onDestroyActionMode(mode: ActionMode?) {
                }
            }
        }
        this.customSelectionActionModeCallback = object : ActionMode.Callback{
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }
            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }
            override fun onDestroyActionMode(mode: ActionMode?) {
            }

        }

        this.isLongClickable = false
        this.setTextIsSelectable(false)

        this@SecureInputView.privateImeOptions="disableToolbar=true"
        if (Utils.getInputType(this@SecureInputView)!= InputType.TYPE_TEXT_VARIATION_PASSWORD && Utils.getInputType(this@SecureInputView)!=NEW_TYPE_NUMBER_VARIATION_PASSWORD){
            this@SecureInputView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or this.inputType )
        }
        this.setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                this@SecureInputView.clearFocus()
                return false
            }

        })

    }

}
