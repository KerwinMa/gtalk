package com.googlecode.gtalksms.cmd;

import android.app.Service;
import android.text.ClipboardManager;
import android.util.Log;

import com.googlecode.gtalksms.MainService;
import com.googlecode.gtalksms.R;
import com.googlecode.gtalksms.tools.Tools;

public class ClipboardCmd extends CommandHandlerBase {
    ClipboardManager _clipboardMgr;
    
    public ClipboardCmd(MainService mainService) {
        super(mainService, new String[] {"copy"}, CommandHandlerBase.TYPE_COPY);
        _clipboardMgr = (ClipboardManager) mainService.getSystemService(Service.CLIPBOARD_SERVICE);
    }
    
    @Override
    public void execute(Command cmd) {
        try {
        	String text = cmd.getAllArguments();
            if (text.length() > 0) {
                _clipboardMgr.setText(text);
                cmd.respond(getString(R.string.chat_text_copied));
            } else {
            	cmd.respond(getString(R.string.chat_clipboard, _clipboardMgr.getText()));
            }
        } catch (Exception ex) {
            Log.w(Tools.LOG_TAG, "Clipboard error", ex);
            send(R.string.chat_error_clipboard);
        }
    }
    
    public String[] help() {
        String[] s = { 
                getString(R.string.chat_help_copy, makeBold("\"copy:#text#\""))
            };
        return s;
    }

}
