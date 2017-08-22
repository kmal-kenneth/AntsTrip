/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

/**
 *
 * @author kenma
 */
public enum State {
    
    SOBER("Sober"), DRUNK("Drunk"), DEAD("Dead"), POISONED("Poisoned");
    
    private String name;

    private State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
