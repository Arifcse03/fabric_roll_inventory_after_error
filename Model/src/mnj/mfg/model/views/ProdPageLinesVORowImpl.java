package mnj.mfg.model.views;

import java.math.BigDecimal;

import mnj.mfg.model.entities.MnjMfgFabinsProdLEOImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat May 07 16:49:16 PKT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProdPageLinesVORowImpl extends ViewRowImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ProdPageLinesVORowImpl() {
    }

    /**
     * Gets MnjMfgFabinsProdLEO entity object.
     * @return the MnjMfgFabinsProdLEO
     */
    public MnjMfgFabinsProdLEOImpl getMnjMfgFabinsProdLEO() {
        return (MnjMfgFabinsProdLEOImpl)getEntity(0);
    }
}
