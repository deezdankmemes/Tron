/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import java.util.ArrayList;

/**
 *
 * @author NathanielWard
 */
public class MySoundManager extends SoundManager {

    public static MySoundManager getSoundManager() {
        ArrayList<Track> tracks = new ArrayList<>();

        tracks.add(new Track(MySoundManager.DANKKAZOO, Source.RESOURCE, "/tron/takeoff.wav"));
        tracks.add(new Track(MySoundManager.LANDING, Source.RESOURCE, "/tron/landing.wav"));
        Playlist playlist = new Playlist(tracks);
        return new MySoundManager(playlist);
    }

    public MySoundManager(Playlist playlist) {
        super(playlist);
    }

    public static final String DANKKAZOO = "KAZOO";
public static final String LANDING = "LAND";
}
