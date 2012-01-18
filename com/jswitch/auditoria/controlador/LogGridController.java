

package com.jswitch.auditoria.controlador;

import com.jswitch.auditoria.modelo.AtributoValor;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.entidades.auditoria.AuditLogRecord;
import java.util.ArrayList;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class LogGridController extends DefaultGridFrameController {

    public LogGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
        for (int i = 0; i < ((AuditLogRecord) persistentObject).getNombres().size(); i++) {
            atributoValors.add(
                    new AtributoValor(
                    ((AuditLogRecord) persistentObject).getNombres().get(i),
                    ((AuditLogRecord) persistentObject).getValores().get(i)));
        }
        new AtributeLogGridFrameController(atributoValors);
        //new DefaultDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
    }
}
