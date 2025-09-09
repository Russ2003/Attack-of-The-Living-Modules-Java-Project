package com.example.gameplay;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class Audio {
    private final MediaPlayer backgroundMusic;
    private final MediaPlayer finalBossMusic;
    private final MediaPlayer bossBulletEffect;
    private final MediaPlayer midBulletEffect;
    private final MediaPlayer mobBulletEffect;
    private final MediaPlayer nailBulletEffect;
    private final MediaPlayer playerBulletEffect;
    private final MediaPlayer powerUpEffect;
    private final MediaPlayer powerDownEffect;
    private final MediaPlayer playerHitEffect;
    private final MediaPlayer enemyHitEffect;
    private final MediaPlayer nextStageEffect;
    private final MediaPlayer mainMenuMusic;

    public Audio() {
        backgroundMusic = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/BackgroundMusic.wav")).toString()));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        finalBossMusic = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/FinalBossMusic.wav")).toString()));
        finalBossMusic.setCycleCount(MediaPlayer.INDEFINITE);
        bossBulletEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/BossBullet.wav")).toString()));
        bossBulletEffect.setCycleCount(1);
        midBulletEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/MidBullet.wav")).toString()));
        midBulletEffect.setCycleCount(1);
        mobBulletEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/MobBullet.wav")).toString()));
        mobBulletEffect.setCycleCount(1);
        nailBulletEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/NailBullet.wav")).toString()));
        nailBulletEffect.setCycleCount(1);
        playerBulletEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/PlayerBullet.wav")).toString()));
        playerBulletEffect.setCycleCount(1);
        powerUpEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/PowerUp.wav")).toString()));
        powerUpEffect.setCycleCount(1);
        powerDownEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/PowerDown.wav")).toString()));
        powerDownEffect.setCycleCount(1);
        playerHitEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/PlayerHit.wav")).toString()));
        playerHitEffect.setCycleCount(1);
        enemyHitEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/EnemyHit.wav")).toString()));
        enemyHitEffect.setCycleCount(1);
        nextStageEffect = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/NextStage.wav")).toString()));
        nextStageEffect.setCycleCount(1);
        mainMenuMusic = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource("/com/example/attackofthelivingmodules/assets/Audio/MainMenu.wav")).toString()));
        mainMenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playBackgroundMusic() {backgroundMusic.play();}

    public void stopBackgroundMusic() {backgroundMusic.stop();}

    public void playBossBulletEffect() {
        bossBulletEffect.stop();
        bossBulletEffect.play();
    }

    public void playFinalBossMusic() {finalBossMusic.play();}

    public void stopFinalBossMusic() {finalBossMusic.stop();}

    public void playMidBulletEffect() {
        midBulletEffect.stop();
        midBulletEffect.play();
    }

    public void playMobBulletEffect() {
        mobBulletEffect.stop();
        mobBulletEffect.play();
    }

    public void playNailBulletEffect() {
        nailBulletEffect.stop();
        nailBulletEffect.play();
    }

    public void playPlayerBulletEffect() {
        playerBulletEffect.stop();
        playerBulletEffect.play();
    }

    public void playPowerDownEffect() {
        powerDownEffect.stop();
        powerDownEffect.play();
    }

    public void playPowerUpEffect() {
        powerUpEffect.stop();
        powerUpEffect.play();
    }

    public void playPlayerHitEffect() {
        playerHitEffect.stop();
        playerHitEffect.play();
    }

    public void playEnemyHitEffect() {
        enemyHitEffect.stop();
        enemyHitEffect.play();
    }

    public void playNextStageEffect() {
        nextStageEffect.stop();
        nextStageEffect.play();
    }

    public void playMainMenuMusic() {mainMenuMusic.play();}

    public void stopMainMenuMusic() {mainMenuMusic.stop();}

    public void disposeAll() {
        backgroundMusic.dispose();
        bossBulletEffect.dispose();
        finalBossMusic.dispose();
        midBulletEffect.dispose();
        mobBulletEffect.dispose();
        nailBulletEffect.dispose();
        playerBulletEffect.dispose();
        powerDownEffect.dispose();
        powerUpEffect.dispose();
    }
}
