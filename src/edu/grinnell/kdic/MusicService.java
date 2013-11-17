package edu.grinnell.kdic;

import java.io.IOException;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
	private String STREAMURL = "http://kdic.grinnell.edu:8001/kdic128";
	private MediaPlayer kdicStream = new MediaPlayer(); // KDIC stream

	// Binder given to clients
	private final IBinder mBinder = new LocalBinder();
	// Random number generator
	private final Random mGenerator = new Random();

	/**
	 * Class used for the client Binder. Because we know this service always
	 * runs in the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		MusicService getService() {
			// Return this instance of LocalService so clients can call public
			// methods
			return MusicService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	// Sets stream's type and URL
	public void setupPlayer() {
		kdicStream.setAudioStreamType(AudioManager.STREAM_MUSIC);

		try {
			kdicStream.setDataSource(STREAMURL);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopStream() {
		kdicStream.stop();
	}

	public void playStream() {
		kdicStream.start();
	}

	public void pauseStream() {
		kdicStream.pause();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		setupPlayer();
		kdicStream.setOnPreparedListener((OnPreparedListener) this);
		kdicStream.prepareAsync(); // prepare async to not block main thread
		return 0;
	}

	/** Called when MediaPlayer is ready */
	public void onPrepared(MediaPlayer player) {
		player.start();
	}

	@Override
	public void onDestroy() {
		if (kdicStream != null)
			kdicStream.release();
	}
	
	public boolean isPlaying(){
		return kdicStream.isPlaying();
	}

}
