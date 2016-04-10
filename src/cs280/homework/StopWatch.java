package cs280.homework;

import java.util.concurrent.TimeUnit;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


/**
 * A class that implements a simple stopwatch with one second update
 * 
 * CS280  Homework #4
 * 
 * @author mbanks
 *
 */
public class StopWatch
{
    private Timeline timer; // The timer
    private long elapsed;   // Number of seconds elapsed
    
    // The handler that will be called each second
    private EventHandler<ActionEvent> handler = null;
    
    /**
     * Constructor
     */
    public StopWatch()
    {
        this.elapsed = 0;

        timer = new Timeline(
            new KeyFrame(
                Duration.seconds(1),
                actionEvent -> fireEvent()
            )
        );

        timer.setCycleCount( Timeline.INDEFINITE );
    }
    
    /**
     * Fire the event for the timer
     * 
     * @param actionEvent   the event to fire
     */
    private void fireEvent()
    {
        elapsed++;
        handler.handle( new ActionEvent( this, null ) );
    }

    /**
     * Start the stopwatch counting
     */
    public void start()
    {
        this.timer.play();        
    }
    
    /**
     * Stop the stopwatch counting
     */
    public void stop()
    {
        this.timer.pause();
    }
    
    /**
     * Stop and reset the stopwatch count
     */
    public void reset()
    {
        stop();
        this.elapsed = 0;
    }
    
    /**
     * Test if the stopwatch is counting
     */
    public boolean isRunning()
    {
        return (this.timer.getStatus() == Status.RUNNING);
    }
    
    /**
     * Add an ActionListener.  Called once every second.
     * @param listener
     */
    public void setOnAction( EventHandler<ActionEvent> handler )
    {
        this.handler = handler;
    }
    
    /**
     * @return  the elapsed time in seconds
     */
    public long getElapsed()
    {
    	return this.elapsed;
    }

    @Override
    public String toString()
    {
        long hours = TimeUnit.SECONDS.toHours( this.elapsed );
        long minutes = TimeUnit.SECONDS.toMinutes( this.elapsed ) % 
                TimeUnit.HOURS.toMinutes(1); 
        long seconds = this.elapsed % TimeUnit.MINUTES.toSeconds(1);
        
        return String.format( "%02d:%02d:%02d", hours, minutes, seconds );
    }
}
