package me.vrekt.oasis.level.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import me.vrekt.oasis.Oasis;
import me.vrekt.oasis.entity.player.network.NetworkEntityPlayer;
import me.vrekt.oasis.entity.rotation.Rotation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains information about the level
 */
public final class LevelWorld implements Disposable {

    /**
     * Default world step
     */
    private static final float DEFAULT_STEP = 1 / 60f;

    /**
     * The world
     */
    private final World world;

    /**
     * List of network players for this world
     */
    private final Map<Integer, NetworkEntityPlayer> networkPlayers = new ConcurrentHashMap<>();

    /**
     * Game
     */
    private final Oasis game;

    /**
     * Accumulator
     */
    private float worldStepAccumulator;

    public LevelWorld() {
        world = new World(new Vector2(0, 0), true);
        game = Oasis.get();
    }

    /**
     * @return the box 2d world.
     */
    public World box2dWorld() {
        return world;
    }

    /**
     * Spawn a player in this world
     *
     * @param player the player
     * @param x      spawn X
     * @param y      spawn Y
     */
    public void spawnPlayer(NetworkEntityPlayer player, float x, float y) {
        networkPlayers.put(player.entityId(), player);
        player.createPlayerAnimations(game.assets().get("player/Character.atlas"));
        player.spawnPlayerInWorld(world, new Vector2(x, y));
    }

    /**
     * Remove a player from this world
     *
     * @param entityId the entity ID
     */
    public void removePlayer(int entityId) {
        final NetworkEntityPlayer player = networkPlayers.remove(entityId);
        if (player != null) world.destroyBody(player.entityBody());
    }

    /**
     * Update a network players velocity
     *
     * @param entityId  entity ID
     * @param velocityX velocity X
     * @param velocityY velocity Y
     * @param rotation  the rotation
     */
    public void updateNetworkPlayerVelocity(int entityId, float velocityX, float velocityY, int rotation) {
        final NetworkEntityPlayer player = networkPlayers.get(entityId);
        if (player != null) {
            player.updateVelocity(velocityX, velocityY, Rotation.values()[rotation]);
        } else {
            // TODO: Network ask?
        }
    }

    /**
     * Update the world
     *
     * @param delta the delta
     */
    public void update(float delta) {
        worldStepAccumulator += delta;
        while (worldStepAccumulator >= DEFAULT_STEP) {
            world.step(DEFAULT_STEP, 6, 3);
            worldStepAccumulator -= DEFAULT_STEP;
        }

        networkPlayers.values().forEach(player -> player.update(delta));
    }

    /**
     * Render the world
     *
     * @param delta the delta
     * @param batch the batch
     */
    public void render(float delta, SpriteBatch batch) {
        networkPlayers.values().forEach(player -> player.render(delta, batch));
    }

    @Override
    public void dispose() {
        networkPlayers.clear();
        world.dispose();
    }
}
