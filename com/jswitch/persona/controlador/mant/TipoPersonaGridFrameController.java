

package com.jswitch.persona.controlador.mant;

import com.jswitch.base.controlador.util.DefaultAllGridFrameController;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import java.util.ArrayList;
import java.util.Map;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class TipoPersonaGridFrameController extends DefaultAllGridFrameController {

    public TipoPersonaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        //filteredColumns= new HashMap<Object, Object>();
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }

    @Override
    public boolean isCellEditable(GridControl grid, int row, String attributeName) {
        if ((Consts.EDIT == grid.getMode())
                && (attributeName.equals("idPropio")
                || attributeName.equals("nombre")
                || attributeName.equals("bloqueado"))) {
            TipoPersona tp = (TipoPersona) grid.getVOListTableModel().getObjectForRow(row);
            if (tp.getId() != null) {
                return !tp.isBloqueado();
            }
        }
        return super.isCellEditable(grid, row, attributeName);
    }
}
