package com.apkdoandroid.helpdesk10.view.intefaces

import com.apkdoandroid.helpdesk10.model.entities.HelpDesk

interface OnHelpDeskListener {
    fun onClick(helpDesk: HelpDesk)
}