package mnj.mfg.model.services.common;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 18 14:44:00 BDT 2015
// ---------------------------------------------------------------------
public interface AppModule extends ApplicationModule {
    void setSessionValues(String orgId, String userId, String respId,
                          String respAppl);

    void populateShrink();

    void selectAllLines(String flag);

    double getPassFailVal(String buyer);

    double getTotalRollsYards(String type, String headerid, String color);

    void setColorWiseWherClause();

    void populateShade();

    Row createShadeLines();

    void popuFabInsRoll();


    void popDefectCode();

    void popuOunceRoll();

    void populatePatternAloc();

    void setPtrnAlocRollsWhere();

    void setProdShadeRolsWhereClause();

    void selectAllLinesFabricShade(String flag);

    void selectAllLinesFabricRollInsp(String flag);

    void creatFabricOunceLine();

    void setFabInsRollWhrCls(String type);

    void SelectAllLinesPatAlloc(String flag);

    void SmartCalculation();

    void getRollSerialNo();


    void setPopUpWhereClauseBalance();

    int RollTransfer();

    void popupSelectActionListener();

    void popupDeselectActionListener();

    String RollTransferWhole();

    void Detele_All_RecordsLines();

    void RollTransfertoHeader();

    void SelectAllRoll(String flag);

    void resetViewObject();
}
