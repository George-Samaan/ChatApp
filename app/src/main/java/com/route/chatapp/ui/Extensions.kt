package com.route.todo.ui.home

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.route.chatapp.ui.ViewMessage


object Extensions {
    private fun showDialog(
        context: Context,
        message: String,
        posActionName: String? = null,
        posActionCallBack: (() -> Unit)? = null,
        negActionName: String? = null,
        negActionCallBack: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ): AlertDialog {

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton(
            posActionName
        ) { dialog, id ->
            dialog.dismiss()
            posActionCallBack?.invoke()
        }

        alertDialogBuilder.setNegativeButton(
            negActionName
        ) { dialog, id ->
            dialog.dismiss()
            negActionCallBack?.invoke()
        }

        alertDialogBuilder.setCancelable(isCancelable)

        return alertDialogBuilder.show()

    }


    fun Fragment.showDialog(
        message: String,
        posActionName: String? = null,
        posActionCallBack: (() -> Unit)? = null,
        negActionName: String? = null,
        negActionCallBack: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ): AlertDialog {
        return Extensions.showDialog(
            requireContext(),
            message,
            posActionName,
            posActionCallBack,
            negActionName,
            negActionCallBack,
            isCancelable
        )
    }

    fun Fragment.showDialog(
        viewMessage: ViewMessage
    ): AlertDialog {

        return showDialog(
            message = viewMessage.message,
            posActionName = viewMessage.posActionName,
            negActionName = viewMessage.negActionName,
            posActionCallBack = viewMessage.posAction,
            negActionCallBack = viewMessage.negAction,
            isCancelable = viewMessage.isDismissible
        )

    }

    fun Activity.showDialog(
        message: String,
        posActionName: String? = null,
        posActionCallBack: (() -> Unit)? = null,
        negActionName: String? = null,
        negActionCallBack: (() -> Unit)? = null,
        isCancelable: Boolean = true
    ): AlertDialog {
        return Extensions.showDialog(
            this,
            message,
            posActionName,
            posActionCallBack,
            negActionName,
            negActionCallBack,
            isCancelable
        )
    }

    fun Activity.showDialog(
        viewMessage: ViewMessage
    ): AlertDialog {

        return showDialog(
            message = viewMessage.message,
            posActionName = viewMessage.posActionName,
            negActionName = viewMessage.negActionName,
            posActionCallBack = viewMessage.posAction,
            negActionCallBack = viewMessage.negAction,
            isCancelable = viewMessage.isDismissible
        )

    }
}

