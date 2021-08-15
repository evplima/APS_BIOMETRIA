/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author evert
 */
public class Singleton {
    private static Singleton uniqueInstance;
    private int ic_restricao;
	private Singleton() {
            
	}

    public int getIc_restricao() {
        return ic_restricao;
    }

    public void setIc_restricao(int ic_restricao) {
        this.ic_restricao = ic_restricao;
    }

	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Singleton();

		return uniqueInstance;
	}
}
