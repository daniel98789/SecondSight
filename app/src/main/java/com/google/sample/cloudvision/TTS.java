package com.google.sample.cloudvision;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class TTS {

    private TextToSpeech tts;
    private Context context;

    public TTS(Context context)
    {
        this.context = context;
        tts=new TextToSpeech(this.context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    public void speak(String text)
    {
        Toast.makeText(context, text,Toast.LENGTH_SHORT).show();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void stop()
    {
        tts.stop();
    }

    public void shutdown()
    {
        tts.shutdown();
    }
}
