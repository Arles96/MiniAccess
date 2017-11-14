/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniaccesseddii;

import javax.swing.JProgressBar;

/**
 *
 * @author arles96
 */
public class PresentationThread extends Thread{
    
    private final JProgressBar progress;

    public PresentationThread(JProgressBar progress) {
        super();
        this.progress = progress;
    }
    
    @Override
    public void run(){
        for (int i = 0; i <= 100; i++) {
            progress.setValue(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }
    
}
