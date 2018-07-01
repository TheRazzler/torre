/**
 * 
 */
package state;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import model.MouseWatcher;
import view.LayerManager;
import view.SpriteSheet;

/**
 * A section of the game responsible for loading and managing assets as well as displaying them to the screen
 * Analogous to a screen (menu screen, settings screen, game screen, etc.)
 * @author Spencer Yoder
 */
public abstract class State {
    /** A canvas for the benefit of the {@link model.MouseWatcher}*/
    protected Canvas canvas;
    /** The MouseWatcher */
    protected MouseWatcher mouseWatcher;
    /** The {@link view.LayerManager} */
    protected LayerManager layerManager;
    
    /** The current State */
    private static State currentState;
    
    /**
     * Constructs a new State with the given Canvas
     * @param canvas a canvas for the MouseWatcher
     */
    public State(Canvas canvas) {
        this.canvas = canvas;
        mouseWatcher = new MouseWatcher(canvas);
        layerManager = new LayerManager();
    }
    
    /**
     * Sets the current State to be the given State
     * @param state the new State
     */
    public static void setState(State state) {
        if(currentState != null) {
            State previous = currentState;
            currentState = null;
            previous.unload();
            state.load();
        }
        currentState = state;
    }
    
    /**
     * @return the current State
     */
    public static State getState() {
        return currentState;
    }
    
    /**
     * Calculates the state of all assets in the State (non-visible)
     */
    public abstract void tick();
    /**
     * Draws all assets in the State to the screen (visible)
     * @param g the Graphics to which the assets will be drawn
     */
    public abstract void render(Graphics g);
    /**
     * The code that is run when the State is clicked on
     */
    public abstract void handleClick(MouseEvent e);
    
    public abstract void handlePress(MouseEvent e);
    /**
     * Loads all assets associated with the State into memory
     * @see assets.Assets
     */
    protected abstract void load();
    /**
     * Unloads all assets associated with the State
     * @see assets.Assets
     */
    protected abstract void unload();

    /**
     * 
     */
    public abstract void handleRelease(MouseEvent e);
}
