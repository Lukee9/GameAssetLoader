import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceLoader {
    private boolean loaded = false;
    private static final Map<String, Image> imageResources = new HashMap<>();
    private static final Map<String, AudioClip> audioResources = new HashMap<>();

    //Load all assets
    public ResourceLoader(String folder) {
        int ilength = 0;
        int alength = 0;
        File assets;
        String filename;
        String path;
        try {
            assets = new File(folder);
			//Filter for image files
            FileFilter imageFilter = pathname -> pathname.getAbsolutePath().toLowerCase().endsWith(".png") || pathname.getAbsolutePath().toLowerCase().endsWith(".gif") || pathname.getAbsolutePath().toLowerCase().endsWith(".jpg") || pathname.getAbsolutePath().toLowerCase().endsWith(".jpeg");
            ilength = Objects.requireNonNull(assets.listFiles(imageFilter)).length;

			//Load image files
            for (File iasset : Objects.requireNonNull(assets.listFiles(imageFilter))) {
                path = iasset.getAbsolutePath();
                filename = iasset.getName();
                imageResources.put(filename.substring(0, filename.lastIndexOf(".")), new ImageIcon(path).getImage());
            }
			
			//Filter for audio files
            FileFilter audioFilter = pathname -> {
                return pathname.getAbsolutePath().toLowerCase().endsWith(".wav") || pathname.getAbsolutePath().toLowerCase().endsWith(".mp3") || pathname.getAbsolutePath().toLowerCase().endsWith(".aif") || pathname.getAbsolutePath().toLowerCase().endsWith(".aiff"); //Not including .fxm or .flv as the audio is embedded in video
            };
			
			//Load audio files
            for (File aasset : Objects.requireNonNull(assets.listFiles(audioFilter))) {
                filename = aasset.getName();
                audioResources.put(filename.substring(0, filename.lastIndexOf(".")), new AudioClip(aasset.toURI().toString()));
            }
            loaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loadingSuccessful() {
        return loaded;
    }

    public static Image getImage(String name) {
        return imageResources.get(name);
    }

    public static AudioClip getAudio(String name) {
        return audioResources.get(name);
    }

    public static boolean playAudio(String name, double volume) {
        AudioClip ac = audioResources.get(name);
        if (ac == null) return false;
        else ac.play(volume);
        return true;
    }

    public static boolean stopAudio(String name) {
        AudioClip ac = audioResources.get(name);
        if (ac == null || !ac.isPlaying()) return false;
        else ac.stop();
        return true;
    }
}
