# SampleDisableCopyPaste
 In this sample i am handling disable copy paste in EditText as well as desable clipboard. This is working fine for all devices.


Normally you achive this functionlity with this code

        android:importantForAutofill="no"
        android:longClickable="false"


But in some of device not working 

Please add some more code

<h4>Note:</h4>customInsertionActionModeCallback method added in android api level 23 and customSelectionActionModeCallback introduce with android api 11 


     
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



<h3> Handle imeoptions in Samsung device </h3>

     
     this@SecureInputView.privateImeOptions="disableToolbar=true"



<h3>Handle Chinese devices like RedMi</h3>


      
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















