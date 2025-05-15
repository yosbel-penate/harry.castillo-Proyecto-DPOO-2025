package app.main;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    private static final Map<String, MediaPlayer> sounds = new HashMap<>();
    private static final Map<String, AudioClip> clips = new HashMap<>();
    private static double MusicVolumen = 0.4;
    private static double ClipsVolumen = 0.4;
    private static final Map<String, MediaPlayer> activeSounds = new HashMap<>();
    private static final Map<String, AudioClip> activeClips = new HashMap<>();

    public static void initMediaPlayer() {
        sounds.put("mainMenu", new MediaPlayer(new Media(Paths.get("src/DAO/audios/music/mainMenu.mp3").toUri().toString())));
        sounds.put("combatMusic", new MediaPlayer(new Media(Paths.get("src/DAO/audios/music/combatMusic.mp3").toUri().toString())));

        for (MediaPlayer player : sounds.values()) {
            player.setVolume(MusicVolumen);
        }
    }

    public static void initAudioClips() {
        clips.put("selectedCharacter", new AudioClip(Paths.get("src/DAO/audios/sound_effects/Personajeseleccionao.mp3").toUri().toString()));
        clips.put("buttonSound", new AudioClip(Paths.get("src/DAO/audios/sound_effects/buttonMenu.mp3").toUri().toString()));
        clips.put("roasterButton", new AudioClip(Paths.get("src/DAO/audios/sound_effects/roasterButtonActivated.mp3").toUri().toString()));
    }

    public static void playSelectedCharacter() {
        AudioClip clip = clips.get("selectedCharacter");
        if (clip != null) {
            clip.setVolume(ClipsVolumen);
            clip.play();
            activeClips.put("selectedCharacter", clip);
        }
    }

    public static void playMainMenu() {
        stopIfPlaying("mainMenu");
        MediaPlayer player = sounds.get("mainMenu");
        if (player != null) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.setVolume(MusicVolumen);
            player.play();
            activeSounds.put("mainMenu", player);
        }
    }

    public static void stopMainMenu() {
        MediaPlayer player = activeSounds.get("mainMenu");
        if (player != null) {
            player.stop();
            activeSounds.remove("mainMenu");
        }
    }

    public static void playButtonSound() {
        AudioClip clip = clips.get("buttonSound");
        if (clip != null) {
            clip.setVolume(ClipsVolumen);
            clip.play();
            activeClips.put("buttonSound", clip);
        }
    }

    public static void playRoasterButtonSound() {
        AudioClip clip = clips.get("roasterButton");
        if (clip != null) {
            clip.setVolume(ClipsVolumen);
            clip.play();
            activeClips.put("roasterButton", clip);
        }
    }

    public static void playCombatMusic() {
        stopIfPlaying("combatMusic");
        MediaPlayer player = sounds.get("combatMusic");
        if (player != null) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.setVolume(MusicVolumen);
            player.play();
            activeSounds.put("combatMusic", player);
        }
    }

    public static void stopIfPlaying(String soundKey) {
        MediaPlayer player = activeSounds.get(soundKey);
        if (player != null) {
            player.stop();
            activeSounds.remove(soundKey);
        }
    }

    public static void setVolumeForAllSounds(double newVolume) {
        MusicVolumen = newVolume;
        ClipsVolumen = newVolume;

        for (MediaPlayer player : activeSounds.values()) {
            player.setVolume(MusicVolumen);
        }
        for (AudioClip clip : activeClips.values()) {
            clip.setVolume(ClipsVolumen);
        }
    }

    public static void setVolumenForMusic(double newVolumen) {
        MusicVolumen = newVolumen;
        for (MediaPlayer player: activeSounds.values()){
            player.setVolume(MusicVolumen);
        }
    }

    public static void setVolumenForEffect(double newVolumen){
        ClipsVolumen = newVolumen;
        for (AudioClip clip: activeClips.values()){
            clip.setVolume(ClipsVolumen);
        }
    }
}