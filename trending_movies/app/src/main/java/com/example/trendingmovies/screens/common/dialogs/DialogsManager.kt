package com.example.trendingmovies.screens.common.dialogs

import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

@UiThread
class DialogsManager (fragmentManager: FragmentManager){

    val ARGUMENT_DIALOG_ID = "ARGUMENT_DIALOG_ID"
    val DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG"

    private var mFragmentManager: FragmentManager
    private var mCurrentlyShownDialog: DialogFragment? = null

    init {
        mFragmentManager = fragmentManager

        val fragmentWithDialogTag: Fragment? = fragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG)
        if (fragmentWithDialogTag != null
            && DialogFragment::class.java.isAssignableFrom(fragmentWithDialogTag.javaClass)
        ) {
            mCurrentlyShownDialog = fragmentWithDialogTag as DialogFragment
        }
    }

    fun getCurrentlyShownDialog(): DialogFragment?{
        return mCurrentlyShownDialog
    }

    fun getCurrentlyShownDialogId(): String{
        if (mCurrentlyShownDialog == null || mCurrentlyShownDialog!!.arguments == null ||
            !mCurrentlyShownDialog!!.arguments!!.containsKey(ARGUMENT_DIALOG_ID)) {
            return ""
        } else {
            return mCurrentlyShownDialog!!.arguments!!.getString(ARGUMENT_DIALOG_ID)!!
        }
    }

    fun isDialogCurrentlyShown(id: String): Boolean{
        val shownDialogId = getCurrentlyShownDialogId()
        return !TextUtils.isEmpty(shownDialogId) && shownDialogId == id
    }

    fun dismissCurrentlyShownDialog() {
        if (mCurrentlyShownDialog != null) {
            mCurrentlyShownDialog!!.dismissAllowingStateLoss();
            mCurrentlyShownDialog = null
        }
    }

    fun showRetainedDialogWithId(dialog: DialogFragment, id: String?) {
        dismissCurrentlyShownDialog()
        dialog.setRetainInstance(true)
        setId(dialog, id)
        showDialog(dialog)
    }

    private fun setId(dialog: DialogFragment , id: String?) {
            val args = if( dialog.arguments != null){
                dialog.arguments
            } else {
                Bundle(1)
            }

        args?.putString(ARGUMENT_DIALOG_ID, id);
        dialog.arguments = args;
    }

    private fun showDialog(dialog: DialogFragment) {
        mFragmentManager.beginTransaction()
            .add(dialog, DIALOG_FRAGMENT_TAG)
            .commitAllowingStateLoss();
        mCurrentlyShownDialog = dialog;
    }
}