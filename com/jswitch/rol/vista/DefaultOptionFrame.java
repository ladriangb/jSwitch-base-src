/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.rol.vista;


import com.jswitch.rol.controlador.DefaultOptionFrameControler;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author adrian
 */
public abstract class DefaultOptionFrame extends InternalFrame {

    public abstract void inicializar(DefaultOptionFrameControler optionControl ,String valueObjectClassName, String titulo, boolean addToMDIFrame);

   
}
