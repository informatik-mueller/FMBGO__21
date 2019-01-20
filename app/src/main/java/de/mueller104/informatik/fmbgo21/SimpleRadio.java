package de.mueller104.informatik.fmbgo21;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Player;


//TODO: Benachrichtigung mit Player, um Radio zu togglen, wenn es minimiert ist
//TODO: Archiv implementieren

public class SimpleRadio extends Form implements HandlesEventDispatching {
    private Button Btn1;
    private Label Space1;
    private Player Player1;
    private Label Ueberschrift;
    private Button Archiv;
    private static final String RadioUrl = "http://stream.radiosaw.de/stream.mp3";

    public void $define(){
        this.AlignHorizontal(3);
        this.BackgroundColor(0xFFF89432);
        Player1 = new Player(this);
        Player1.Source(RadioUrl);

        MediaSession mSession = new MediaSession(this, "MusicService");
        mSession.setCallback(new MediaSession.Callback() {
            @Override
            public void onCommand(String command,  Bundle args, ResultReceiver cb) {
                super.onCommand(command, args, cb);
            }
        });
        mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

        Ueberschrift = new Label(this);
        Ueberschrift.Text("Schulradio");
        Ueberschrift.FontSize(30.0f);
        Ueberschrift.FontBold(true);
        Ueberschrift.TextColor(0xFF444444);

        Space1 = new Label(this);
        Space1.HeightPercent(30);
        Btn1 = new Button(this);
        Btn1.Text("Abspielen");
        Btn1.FontSize(30);
        Archiv = new Button(this);
        Archiv.Text("Archiv");
        Archiv.FontSize(30);
        EventDispatcher.registerEventForDelegation( this, "1", "Click" );
    }

    @Override
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args) {
        if(component.equals(Btn1) && eventName.equals("Click")){
            if (Player1.playerState == Player.State.PLAYING){
                Btn1.Text("Abspielen");
                Player1.Pause();
            }
            else{
                Btn1.Text("Pausieren");
                Player1.Start();
            }
            return true;
        }
        return false;
    }
}