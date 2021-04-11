package mnj.mfg.view.backing_bean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adf.view.rich.util.ResetUtils;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jdbc.OracleTypes;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class Main {
    private RichInputText bwLengthCM;
    private RichInputText bwWidthCM;
    private RichInputText awLengthCM;
    private RichInputText shrinkLength;
    private RichInputText shrinkLengthPercnt;
    private RichInputText awWidthCM;
    private RichInputText shrinkWidth;
    private RichInputText shrinkWidthPrcnt;
    private RichInputText point1;
    private RichInputText point2;
    private RichInputText point3;
    private RichInputText point4;
    private RichInputText totalPoints;
    private RichTable fabInsDetTable;
    private RichInputText totalPanPoints;
    private RichInputText totalDetPoints;
    private RichInputText actualLength;
    private RichTable fabInsLinesTable;
    private RichInputText cutableWidth;

    static DecimalFormat format = new DecimalFormat("#.##");
    private RichTable shrinkProdTable;
    private RichTable selectAllTable;
    private RichInputText totalRolRcvd;
    private RichInputText totRcvQty;
    ViewObject findViewObject;
    ViewObject FabricShrinkLines1;
    ViewObject fabrInsDetVO;
    ViewObject FebricOunceDetailView1;
    ViewObject FebricOunceLineView1;
    ViewObject InvPageLinesDet2VO1;
    ViewObject ProdPageLinesVO1, MnjMfgFabinsPtrnalocDView1;
    ViewObject InvoiceVo ;
    private RichInputText noOfRolChkd;
    private RichInputText noChkdQty;
    private RichInputText avgCutWidth;
    private RichInputText grandSumPenPoints;
    private RichInputText grandTotPoints;
    private RichInputText passFail;
    private RichInputComboboxListOfValues inspection;
    private RichInputText actionReqFail;
    private RichTable ratioPlanTable;
    private RichInputText noumberRollChecked;
    private RichInputComboboxListOfValues rcptNo;
    private RichInputText rollLengthYdsBind;
    private RichTable invLinesTable;
    private RichInputText blncYards;
    private RichInputText rolLengthMeter;
    private RichInputText rolLengthInch;
    private RichSelectOneChoice rolUOM;
    private RichTable rollTable;
    private RichColumn rolUOMNew;
    private RichInputComboboxListOfValues rolUOM2;
    private RichInputText rolLengthYrdsNew;
    private RichInputText rolLengthInchNew;
    private RichTable fabInsFirstTable;
    private RichInputText headerId;
    private RichInputFile uploadFile;
    private RichInputText documentNo;
    private RichInputText invoiceNo;
    private RichInputFile uploadFabricRollFile;
    private RichForm inventoryForm;
    private RichTable fillRollTable;
    private RichTable fillRollNewPopUpTable;
    private RichInputText orgid;


    public Main() {

        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        findViewObject = am.findViewObject("MnjMfgFabinsSecndD2View1");
        FabricShrinkLines1 = am.findViewObject("FabricShrinkLines1");
        fabrInsDetVO = am.findViewObject("MnjMfgFabinsSecndDView1");
        FebricOunceDetailView1 = am.findViewObject("FebricOunceDetailView1");
        FebricOunceLineView1 = am.findViewObject("FebricOunceLineView1");

        InvPageLinesDet2VO1 = am.findViewObject("InvPageLinesDet2VO1");
        ProdPageLinesVO1 = am.findViewObject("ProdPageLinesVO1");
        MnjMfgFabinsPtrnalocDView1 =
                am.findViewObject("MnjMfgFabinsPtrnalocDView1");
        
        InvoiceVo =   am.findViewObject("InvoiceVo1");
        
        


    }
    
    public AppModuleImpl getAppM(){
          DCBindingContainer bindingContainer =
              (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
          //BindingContext bindingContext = BindingContext.getCurrent();
          DCDataControl dc =
              bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
          AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
          return appM;
      }
    AppModuleImpl am = (AppModuleImpl)this.getAppM();

    

    public void setBwLengthCM(RichInputText bwLengthCM) {
        this.bwLengthCM = bwLengthCM;
    }

    public RichInputText getBwLengthCM() {
        return bwLengthCM;
    }

    public void setBwWidthCM(RichInputText bwWidthCM) {
        this.bwWidthCM = bwWidthCM;
    }

    public RichInputText getBwWidthCM() {
        return bwWidthCM;
    }

    public void setAwLengthCM(RichInputText awLengthCM) {
        this.awLengthCM = awLengthCM;
    }

    public RichInputText getAwLengthCM() {
        return awLengthCM;
    }

    public void setShrinkLength(RichInputText shrinkLength) {
        this.shrinkLength = shrinkLength;
    }

    public RichInputText getShrinkLength() {
        return shrinkLength;
    }

    public void setShrinkLengthPercnt(RichInputText shrinkLengthPercnt) {
        this.shrinkLengthPercnt = shrinkLengthPercnt;
    }

    public RichInputText getShrinkLengthPercnt() {
        return shrinkLengthPercnt;
    }

    public void setAwWidthCM(RichInputText awWidthCM) {
        this.awWidthCM = awWidthCM;
    }

    public RichInputText getAwWidthCM() {
        return awWidthCM;
    }

    public void setShrinkWidth(RichInputText shrinkWidth) {
        this.shrinkWidth = shrinkWidth;
    }

    public RichInputText getShrinkWidth() {
        return shrinkWidth;
    }

    public void setShrinkWidthPrcnt(RichInputText shrinkWidthPrcnt) {
        this.shrinkWidthPrcnt = shrinkWidthPrcnt;
    }

    public RichInputText getShrinkWidthPrcnt() {
        return shrinkWidthPrcnt;
    }

    public void refreshValues() {

        double bwLengthCMVal = MyMath.numeric(getBwLengthCM());
        double bwWidthCMVal = MyMath.numeric(getBwWidthCM());
        double awLengthCMVal = MyMath.numeric(getAwLengthCM());
        double awWidthCMVal = MyMath.numeric(getAwWidthCM());

        System.out.println("bwLengthCMVal  " + bwLengthCMVal);
        System.out.println("bwWidthCMVal   " + bwWidthCMVal);
        System.out.println("awLengthCMVal  " + awLengthCMVal);
        System.out.println("awWidthCMVal   " + awWidthCMVal);

        double shrinkLengthPercntVal = 0.0, shrinkWidthPrcntVal = 0.00;

        double shrinkLengthVal = awLengthCMVal - bwLengthCMVal;
        shrinkLength.setValue(MyMath.toNumber(shrinkLengthVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkLength);

        if (shrinkLengthVal != 0 && bwLengthCMVal != 0)
            shrinkLengthPercntVal = shrinkLengthVal / bwLengthCMVal * 100;

        shrinkLengthPercnt.setValue(MyMath.toNumber(MyMath.round(shrinkLengthPercntVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkLengthPercnt);

        double shrinkWidthVal = awWidthCMVal - bwWidthCMVal;
        shrinkWidth.setValue(MyMath.toNumber(shrinkWidthVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkWidth);


        if (shrinkWidthVal != 0 && bwWidthCMVal != 0)
            shrinkWidthPrcntVal = shrinkWidthVal / bwWidthCMVal * 100;

        shrinkWidthPrcnt.setValue(MyMath.toNumber(MyMath.round(shrinkWidthPrcntVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkWidthPrcnt);


    }


    public void commonListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        int flag = 0;

        System.out.println(" Delete Validation");

        Row currentRowCheck = FabricShrinkLines1.getCurrentRow();
        String RollCheck = currentRowCheck.getAttribute("RollNo").toString();

        System.out.println(" Delete RollCheck....... " + RollCheck);

        RowSetIterator itFabricShade =
            MnjMfgFabinsPtrnalocDView1.createRowSetIterator("qq");

        String RollAssign;

        while (itFabricShade.hasNext()) {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();

            System.out.println("MnjMfgFabinsPtrnalocDView1 Delete RollCheck In Loop....... " +
                               RollCheck);
            System.out.println("MnjMfgFabinsPtrnalocDView1 Delete RollAssign....... " +
                               RollAssign);

            if (RollAssign.equals(RollCheck)) //RollAssign==RollCheck
            {
                System.out.println("MnjMfgFabinsPtrnalocDView1 Delete In If of flag....... " +
                                   flag);
                flag = flag + 1;
            }

        }

        itFabricShade.closeRowSetIterator();


        System.out.println(" Delete flag....... " + flag);

        if (flag > 0) {

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message =
                new FacesMessage("Can't Change the Value as it is already Assigned");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context),
                               message);
            ResetUtils.reset(valueChangeEvent.getComponent());


        } else {

            refreshValues();

        }


    }


    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {

            BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc =
                bindingContext.findDataControl("AppModuleDataControl"); //
            ApplicationModule am = dc.getApplicationModule();
            ViewObject findViewObject =
                am.findViewObject("FabricShrinkLLinesInv1");
            //findViewObject.executeQuery();


            OperationBinding operationBinding =
                executeOperation("populateShrink");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);
            setFabricInspHeaderVal();

        }

    }


    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {

    }

    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        

    }


    /*****Generic Method to Get BindingContainer**/
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);
        return createParam;

    }

    public void setShrinkProdTable(RichTable shrinkProdTable) {
        this.shrinkProdTable = shrinkProdTable;
    }

    public RichTable getShrinkProdTable() {
        return shrinkProdTable;
    }


    public void setSelectAllTable(RichTable selectAllTable) {
        this.selectAllTable = selectAllTable;
    }

    public RichTable getSelectAllTable() {
        return selectAllTable;
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

   /* public String saveAll() {

        // Below Method By Sabih - 11-05-2016

        System.out.println("Here Sabih 1 ......");

        // getShrinkTabCalculation();
        // End

        OperationBinding operationBinding = executeOperation("Commit");

        Object result = operationBinding.execute();

        //        setFabricInspHeaderVal();

        //        OperationBinding operationBinding1 = executeOperation("Commit");
        //
        //        Object result1 = operationBinding.execute();
        //
        //
        //        if (!operationBinding.getErrors().isEmpty()) {
        //            return null;
        //        }
        return null;
    }
*/
    /*****************
     *
     * New code added for fabric inspection form
     * ********************************************/


    public void pointSystemListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double pont1Val = MyMath.numeric(getPoint1());
        double pont1Va2 = MyMath.numeric(getPoint2());
        double pont1Va3 = MyMath.numeric(getPoint3());
        double pont1Va4 = MyMath.numeric(getPoint4());
        pont1Va2 = pont1Va2 * 2;
        pont1Va3 = pont1Va3 * 3;
        pont1Va4 = pont1Va4 * 4;
        double totalPointsVal = pont1Val + pont1Va2 + pont1Va3 + pont1Va4;
        totalPoints.setValue(MyMath.toNumber(totalPointsVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalPoints);

        double pointSum = getTotPointsSum(totalPointsVal);
        FabInsDetTabValues("SET", "TotPenPoints", pointSum);

        double actLengthVal = FabInsDetTabValues("GET", "ActualLength", 0);
        double actWidthVal = FabInsDetTabValues("GET", "CutWidth", 0);


        if ((actLengthVal != 0) && (actWidthVal != 0)) {
            double totalpointsDet =
                (pointSum * 36 * 100 / (actLengthVal * actWidthVal));
            System.out.println("total --->" + totalpointsDet);
            FabInsDetTabValues("SET", "TotPoints", totalpointsDet);
        }


    }

    public void setPoint1(RichInputText point1) {
        this.point1 = point1;
    }

    public RichInputText getPoint1() {
        return point1;
    }

    public void setPoint2(RichInputText point2) {
        this.point2 = point2;
    }

    public RichInputText getPoint2() {
        return point2;
    }

    public void setPoint3(RichInputText point3) {
        this.point3 = point3;
    }

    public RichInputText getPoint3() {
        return point3;
    }

    public void setPoint4(RichInputText point4) {
        this.point4 = point4;
    }

    public RichInputText getPoint4() {
        return point4;
    }

    public void setTotalPoints(RichInputText totalPoints) {
        this.totalPoints = totalPoints;
    }

    public RichInputText getTotalPoints() {
        return totalPoints;
    }

    public double getTotPointsSum(double currentVal) {


        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        Row currentRow = findViewObject.getCurrentRow();
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            if (r == currentRow) {
                total = total + currentVal;
                continue;
            }
            total = total + MyMath.numeric3(r.getAttribute("TotalPoint"));
        }
        it.closeRowSetIterator();
        return total;
    }

    public double getTotPointsSum2() {


        RowSetIterator it = findViewObject.createRowSetIterator("aa");
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            total = total + MyMath.numeric3(r.getAttribute("TotalPoint"));
        }
        it.closeRowSetIterator();
        return total;
    }

    public double FabInsDetTabValues(String type, String column,
                                     double value) {

        oracle.adf.view.rich.component.UIXTable table = getFabInsDetTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        double val = 0.0;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            if (type.equalsIgnoreCase("SET")) {
                selectedRow.setAttribute(column, value);
                AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsDetTable);
            } else if (type.equalsIgnoreCase("GET")) {
                val = MyMath.numeric3(selectedRow.getAttribute(column));

            }

        } //END OF LOOP
        return val;
    } //end if while loop


    public void setFabInsDetTable(RichTable fabInsDetTable) {
        this.fabInsDetTable = fabInsDetTable;
    }

    public RichTable getFabInsDetTable() {
        return fabInsDetTable;
    }

    public void fabInsDetListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double actLengthVal = MyMath.numeric(getActualLength());
        double actWidthVal = MyMath.numeric(getCutableWidth());
        double pointSum = getTotPointsSum2();

        totalPanPoints.setValue(MyMath.toNumber(pointSum));
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalPanPoints);


        if ((actLengthVal != 0) && (actWidthVal != 0)) {
            double totalpointsDetVal =
                (pointSum * 36 * 100 / (actLengthVal * actWidthVal));
            System.out.println("total --->" + totalpointsDetVal);
            totalDetPoints.setValue(MyMath.toNumber(totalpointsDetVal));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalDetPoints);
        }


    }

    public void setActualLength(RichInputText actualLength) {
        this.actualLength = actualLength;
    }

    public RichInputText getActualLength() {
        return actualLength;
    }

    public void setCutableWidth(RichInputText cutableWidth) {
        this.cutableWidth = cutableWidth;
    }

    public RichInputText getCutableWidth() {
        return cutableWidth;
    }

    public void setTotalPanPoints(RichInputText totalPanPoints) {
        this.totalPanPoints = totalPanPoints;
    }

    public RichInputText getTotalPanPoints() {
        return totalPanPoints;
    }

    public void setTotalDetPoints(RichInputText totalDetPoints) {
        this.totalDetPoints = totalDetPoints;
    }

    public RichInputText getTotalDetPoints() {
        return totalDetPoints;
    }

    public void setFabInsLinesTabVal(String column, double value) {

        oracle.adf.view.rich.component.UIXTable table = getFabInsLinesTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        double val = 0.0;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            selectedRow.setAttribute(column, value);
            AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsLinesTable);

        } //END OF LOOP

    } //end if while loop

    public void setFabInsLinesTable(RichTable fabInsLinesTable) {
        this.fabInsLinesTable = fabInsLinesTable;
    }

    public RichTable getFabInsLinesTable() {
        return fabInsLinesTable;
    }

    public String createShrinl() {


        OperationBinding operationBinding = executeOperation("CreateInsert");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        setFabricInspHeaderVal();

        return null;


    }

    public void setFabricInspHeaderVal() {

        setFabricInsFirstTableValues();

    }

    ////////////////////////////////////

    //    public void setFebOncRolCnt() {
    //
    //        RowSetIterator it = FebricOunceDetailView1.createRowSetIterator("sss");
    //
    //        noumberRollChecked.setValue(MyMath.toNumber(it.getFetchedRowCount()));
    //        AdfFacesContext.getCurrentInstance().addPartialTarget(noumberRollChecked);
    //    }

    ///////////////////////


    public String deleteShrinkAction() {

        OperationBinding operationBinding = executeOperation("Delete");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        setFabricInspHeaderVal();
        return null;
    }


    public String fabricInsLCreatAction() {


        OperationBinding operationBinding = executeOperation("CreateInsert2");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        //        setFabricInspHeaderVal();
        return null;
    }


    public String fabricInsLCreatAction1() {


        OperationBinding operationBinding = executeOperation("CreateInsert5");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        setFabricInspHeaderVal();
        return null;
    }

    public String fabricInsLDelAction() {

        OperationBinding operationBinding = executeOperation("Delete2");

        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        //        setFabricInspHeaderVal();
        return null;
    }


    public void setInspection(RichInputComboboxListOfValues inspection) {
        this.inspection = inspection;
    }

    public RichInputComboboxListOfValues getInspection() {
        return inspection;
    }


    public static Number toNumber(double n) {

        try {
            return new Number(n);
        } catch (SQLException e) {
            ;
        }
        return new Number(0);
    }

    public static double round(double val) {


        double rounded = 0.00;
        try {
            format.setMinimumFractionDigits(0);
            rounded = Double.parseDouble(format.format(val));
            System.out.println("Rounded Value----------->" + rounded);
        } catch (Exception e) {
            ;
        }
        return rounded;
    }

    public static double numeric(RichInputText ob) {


        try {
            if (ob != null)
                return Double.parseDouble(String.valueOf(ob.getValue()));
            else
                return 0;
        } catch (Exception e) {
            ;
        }

        return 0;
    }

    ////////////////END//////////////////////////////////////////////


    /////////////////////////////////////coding by usman calculation AW ONCE/////////////////////////


    ////////////////END//////////////////////////////////////////////

    public void setRatioPlanTable(RichTable ratioPlanTable) {
        this.ratioPlanTable = ratioPlanTable;
    }

    public RichTable getRatioPlanTable() {
        return ratioPlanTable;
    }

    public void setNoumberRollChecked(RichInputText noumberRollChecked) {
        this.noumberRollChecked = noumberRollChecked;
    }

    public RichInputText getNoumberRollChecked() {
        return noumberRollChecked;
    }

    public void setRcptNo(RichInputComboboxListOfValues rcptNo) {
        this.rcptNo = rcptNo;
        //        FacesContext fctx = FacesContext.getCurrentInstance();
        //        ExternalContext ectx = fctx.getExternalContext();
        //        HttpSession userSession = (HttpSession)ectx.getSession(false);
        //        userSession.setAttribute("recptNoS", rcptNo.getValue());
    }

    public RichInputComboboxListOfValues getRcptNo() {
        return rcptNo;
    }

    public void setRollLengthYdsBind(RichInputText rollLengthYdsBind) {
        this.rollLengthYdsBind = rollLengthYdsBind;
    }

    public RichInputText getRollLengthYdsBind() {
        return rollLengthYdsBind;
    }

    public void rollLengthYdsListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double totalRollLengthYds =
            getTotalYrds(MyMath.numeric3(valueChangeEvent.getNewValue()));
        setTotalInv("SET", "TotalYards", totalRollLengthYds);
        setRollLengthValues();

    }

    public double getTotalYrds(double currentVal) {
      

        RowSetIterator it = InvPageLinesDet2VO1.createRowSetIterator("tt");
        //        Row currentRow = InvPageLinesDet2VO1.getCurrentRow();
        double yrds = 0.0, inches = 0.0;
        // edit 0.4.01.2018
        
        while (it.hasNext()) {
            Row r = it.next();
            //            if (r == currentRow) {
            //                total = total + currentVal;
            //                continue;
            //            }
            yrds = yrds + MyMath.numeric3(r.getAttribute("RollLength"));//..........edit 4.03
            //yrds = yrds + MyMath.numeric3(r.getAttribute("RolLength3"));//edit
            inches = inches + MyMath.numeric3(r.getAttribute("RollLength2"));

        }
        //yrds = yrds + (inches / 36);
        //yrds=yrds*1.09361;// my edit

        it.closeRowSetIterator();
        return yrds;
    }

    public double setTotalInv(String type, String column, double value) {

        oracle.adf.view.rich.component.UIXTable table = getInvLinesTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        double val = 0.0;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            if (type.equalsIgnoreCase("SET")) {
                selectedRow.setAttribute(column, value);
                AdfFacesContext.getCurrentInstance().addPartialTarget(invLinesTable);
            } else if (type.equalsIgnoreCase("GET")) {
                String s=String.valueOf(value);
                val = MyMath.numeric3(selectedRow.getAttribute(column));
                //val = MyMath.numeric3(selectedRow.getAttribute(s));

            }

        } //END OF LOOP
        return val;
    } //end if while loop

    public void setInvLinesTable(RichTable invLinesTable) {
        this.invLinesTable = invLinesTable;
    }

    public RichTable getInvLinesTable() {
        return invLinesTable;
    }

    //    public String createInvDetDet() {
    //        BindingContainer bindings = getBindings();
    //        OperationBinding operationBinding =
    //            bindings.getOperationBinding("CreateInsert2");
    //        Object result = operationBinding.execute();
    //        if (!operationBinding.getErrors().isEmpty()) {
    //            return null;
    //        }
    //
    //        RowSetIterator it = InvPageLinesDet2VO1.createRowSetIterator("tt");
    //        double totYrds = it.getRowCount();
    //        it.closeRowSetIterator();
    //        setTotalInv("SET", "TotalRolls", totYrds);
    //
    //        return null;
    //    }

    //    public String DelDetDet() {
    //        BindingContainer bindings = getBindings();
    //        OperationBinding operationBinding =
    //            bindings.getOperationBinding("Delete2");
    //        Object result = operationBinding.execute();
    //        if (!operationBinding.getErrors().isEmpty()) {
    //            return null;
    //        }
    //        RowSetIterator it = InvPageLinesDet2VO1.createRowSetIterator("tt");
    //        double totYrds = it.getRowCount();
    //        it.closeRowSetIterator();
    //        setTotalInv("SET", "TotalRolls", totYrds);
    //
    //        return null;
    //
    //    }


    public void setBlncYards(RichInputText blncYards) {
        this.blncYards = blncYards;
    }

    public RichInputText getBlncYards() {
        return blncYards;
    }

    public void editPopupFetchFabIns(PopupFetchEvent popupFetchEvent) {

        OperationBinding operationBinding =
            executeOperation("setFabInsRollWhrCls");
        operationBinding.execute();
    }

    public void editDialogFabInsp(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {


            OperationBinding operationBinding =
                executeOperation("popuFabInsRoll");
            operationBinding.execute();
            //AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsDetTable);


        }

    }

    public void setRolLengthMeter(RichInputText rolLengthMeter) {
        this.rolLengthMeter = rolLengthMeter;
    }

    public RichInputText getRolLengthMeter() {
        return rolLengthMeter;
    }

    public void setRolLengthInch(RichInputText rolLengthInch) {
        this.rolLengthInch = rolLengthInch;
    }

    public RichInputText getRolLengthInch() {
        return rolLengthInch;
    }

    public void setRolUOM(RichSelectOneChoice rolUOM) {
        this.rolUOM = rolUOM;
    }

    public RichSelectOneChoice getRolUOM() {
        return rolUOM;
    }

    public void setRollLengthValues() {

        String uom =
            MyMath.getStringVal(setGetRolTableValue("GET", "Uom", 0.0));
        //  String uom = MyMath.getStringVal(getRolUOM().getValue());
        double lengthMeter = MyMath.numeric(getRolLengthMeter());
        double lengthInch = MyMath.numeric(getRolLengthInch());

        System.out.println("UOM--->" + uom);
        System.out.println("Meter Value --->" + lengthMeter);
        System.out.println("Length inch ---->" + lengthInch);

        //double value = ((lengthMeter * 39.37) + lengthInch) / 36;
        double value = lengthMeter * 1.09361;// my edit

        long iPart = (long)value;
        double fPart = value - iPart;
       /* int i = d.intValue();
                 System.out.println(i);
                 d=d-i;
                 Double c=d*36;
        */
        //double fPart = value - iPart;
       fPart=fPart*36;//my edit
        
        System.out.println("Deciaml part -->" + iPart);
        System.out.println("Fractional part --->" + fPart);

        if (uom.equalsIgnoreCase("Meter")) {
            rolLengthYrdsNew.setValue(MyMath.toNumber(iPart));
            rolLengthInchNew.setValue(MyMath.toNumber(fPart));
        } else {
            rolLengthYrdsNew.setValue(MyMath.toNumber(lengthMeter));
            rolLengthInchNew.setValue(MyMath.toNumber(lengthInch));
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(rolLengthYrdsNew);
        AdfFacesContext.getCurrentInstance().addPartialTarget(rolLengthInchNew);


    }

    public Object setGetRolTableValue(String type, String column,
                                      double value) {

        oracle.adf.view.rich.component.UIXTable table = getRollTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Object val = null;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            if (type.equalsIgnoreCase("SET")) {
                selectedRow.setAttribute(column, value);
                AdfFacesContext.getCurrentInstance().addPartialTarget(rollTable);
            } else if (type.equalsIgnoreCase("GET")) {
                val = selectedRow.getAttribute(column);

            }

        } //END OF LOOP
        return val;
    } //end if while loop


    public void setRollTable(RichTable rollTable) {
        this.rollTable = rollTable;
    }

    public RichTable getRollTable() {
        return rollTable;
    }


    public void rolUOMListener(ValueChangeEvent vce) {
        // Add event code here...


        String uom = MyMath.getStringVal(vce.getNewValue());
        //  String uom = MyMath.getStringVal(getRolUOM().getValue());
        double lengthMeter = MyMath.numeric(getRolLengthMeter());
        double lengthInch = MyMath.numeric(getRolLengthInch());

        System.out.println("UOM--->" + uom);
        System.out.println("Meter Value --->" + lengthMeter);
        System.out.println("Length inch ---->" + lengthInch);
        //[{Lenght in mtr * 39.37} + Lenght in inch}] / 36

       // double value = ((lengthMeter * 39.37) + lengthInch) / 36;
       double value = lengthMeter * 1.09361;//my edit
        long iPart = (long)value;
        //double fPart = value - iPart;
        //--------my edit
       // double fPart = value - iPart;
        
        double fPart = value - iPart;
        fPart=fPart*36;//my edit
        //------my edit
        System.out.println("Deciaml part -->" + iPart);
        System.out.println("Fractional part --->" + fPart);

        if (uom.equalsIgnoreCase("Meter")) {
            rolLengthYrdsNew.setValue(MyMath.toNumber(iPart));
            rolLengthInchNew.setValue(MyMath.toNumber(fPart));
        } else {
            rolLengthYrdsNew.setValue(MyMath.toNumber(lengthMeter));
            rolLengthInchNew.setValue(MyMath.toNumber(lengthInch));
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(rolLengthYrdsNew);
        AdfFacesContext.getCurrentInstance().addPartialTarget(rolLengthInchNew);

    }


    public void setRolUOM2(RichInputComboboxListOfValues rolUOM2) {
        this.rolUOM2 = rolUOM2;
    }

    public RichInputComboboxListOfValues getRolUOM2() {
        return rolUOM2;
    }

    public void setRolLengthYrdsNew(RichInputText rolLengthYrdsNew) {
        this.rolLengthYrdsNew = rolLengthYrdsNew;
    }

    public RichInputText getRolLengthYrdsNew() {
        return rolLengthYrdsNew;
    }

    public void setRolLengthInchNew(RichInputText rolLengthInchNew) {
        this.rolLengthInchNew = rolLengthInchNew;
    }

    public RichInputText getRolLengthInchNew() {
        return rolLengthInchNew;
    }

    public void setFabInsFirstTable(RichTable fabInsFirstTable) {
        this.fabInsFirstTable = fabInsFirstTable;
    }

    public RichTable getFabInsFirstTable() {
        return fabInsFirstTable;
    }

    public void setFabricInsFirstTableValues() {


        System.out.println("Calculation called-----------------------.>>>???" +
                           ProdPageLinesVO1.getCurrentRow().getAttribute("BuyerId").toString());
        oracle.adf.view.rich.component.UIXTable table = getFabInsFirstTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Object val = null;

        /**********************************************************************/
        RowSetIterator it = FabricShrinkLines1.createRowSetIterator("ss");

        // totalRolRcvd.setValue(MyMath.toNumber(it.getFetchedRowCount()));
        //AdfFacesContext.getCurrentInstance().addPartialTarget(totalRolRcvd);
        int totalRolRcvVal = it.getFetchedRowCount();
        double totRcvQtyVal = 0.0;
        while (it.hasNext()) {

            Row r = it.next();
            totRcvQtyVal =
                    totRcvQtyVal + MyMath.numeric3(r.getAttribute("RollLength"));

        }
        // totRcvQty.setValue(MyMath.toNumber(totRcvQtyVal));
        //  AdfFacesContext.getCurrentInstance().addPartialTarget(totRcvQty);
        it.closeRowSetIterator();

        RowSetIterator it2 = fabrInsDetVO.createRowSetIterator("qq");

        int noOfRolchkdVal = it2.getFetchedRowCount();

        // noOfRolChkd.setValue(MyMath.toNumber(noOfRolchkdVal));


        double nochkdQtyVal = 0.0, avgCutWidthVal = 0.0, granSumPenPointsval =
            0.0;
        while (it2.hasNext()) {

            Row r = it2.next();
            nochkdQtyVal =
                    nochkdQtyVal + MyMath.numeric3(r.getAttribute("ActualLength"));
            avgCutWidthVal =
                    avgCutWidthVal + MyMath.numeric3(r.getAttribute("CutWidth"));
            granSumPenPointsval =
                    granSumPenPointsval + MyMath.numeric3(r.getAttribute("TotPenPoints"));

        }

        // noChkdQty.setValue(MyMath.toNumber(nochkdQtyVal));
        //AdfFacesContext.getCurrentInstance().addPartialTarget(noChkdQty);
        it2.closeRowSetIterator();

        if ((avgCutWidthVal != 0) && (noOfRolchkdVal != 0)) {

            avgCutWidthVal = avgCutWidthVal / MyMath.numeric3(noOfRolchkdVal);
        }

        // avgCutWidth.setValue(MyMath.toNumber(avgCutWidthVal));
        //AdfFacesContext.getCurrentInstance().addPartialTarget(avgCutWidth);
        //grandSumPenPoints.setValue(MyMath.toNumber(granSumPenPointsval));
        //AdfFacesContext.getCurrentInstance().addPartialTarget(grandSumPenPoints);

        double grandTotPointsVal = 0.0;

        if ((totRcvQtyVal != 0) && (noOfRolchkdVal != 0)) {
            grandTotPointsVal =
                    (nochkdQtyVal * 36 * 100) / (totRcvQtyVal * MyMath.numeric3(noOfRolchkdVal));
        }
        //        grandTotPoints.setValue(MyMath.toNumber(grandTotPointsVal));
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(grandTotPoints);


        OperationBinding operationBinding = executeOperation("getPassFailVal");
        Object result = operationBinding.execute();
        double qltyVal = MyMath.numeric3(result);
        String pasFailVal = null;

        if (grandTotPointsVal < qltyVal) {
            pasFailVal = "Fail";
        } else {
            pasFailVal = "Pass";
        }
        //        passFail.setValue(pasFailVal);
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(passFail);

        //        String insVal = MyMath.getStringVal(getInspection().getValue());


        /*********************************************************************************/


        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            String insVal =
                MyMath.getStringVal(selectedRow.getAttribute("Inspection"));

            selectedRow.setAttribute("TotalRolRcv", totalRolRcvVal);
            //totalRolRcvd
            selectedRow.setAttribute("TotalRcvQty", totRcvQtyVal);
            selectedRow.setAttribute("NoOfRolChkd", noOfRolchkdVal);
            selectedRow.setAttribute("NoChkdQty", nochkdQtyVal);
            selectedRow.setAttribute("AvgCutWidth", avgCutWidthVal);
            selectedRow.setAttribute("TotPnltyPnts", granSumPenPointsval);
            selectedRow.setAttribute("TotPoints", grandTotPointsVal);
            selectedRow.setAttribute("PasFail", pasFailVal);

            if ((pasFailVal.equalsIgnoreCase("Fail")) && (insVal != null) &&
                (insVal.equalsIgnoreCase("1st (10%)"))) {
                selectedRow.setAttribute("ActionReqFail",
                                         pasFailVal + " 2nd (15%)");

            } else if ((pasFailVal.equalsIgnoreCase("Fail")) &&
                       (insVal != null) &&
                       (insVal.equalsIgnoreCase("2nd (15%)"))) {
                selectedRow.setAttribute("ActionReqFail",
                                         pasFailVal + " 3rd (100%)");

            } else {
                selectedRow.setAttribute("ActionReqFail", "");

            }


        } //END OF LOOP

        AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsFirstTable);
    }

    /*****************
     * File uploading Code
     * *****************************/
    public void fileUploadRoll(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("Roll Upload");
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        try {

            parseFile(file.getInputStream());

            System.out.println("Call Serial No");
            getRollSrNo();
            System.out.println("exit Serial No");

            // AdfFacesContext.getCurrentInstance().addPartialTarget(rollTable);

        } catch (IOException e) {
            // TODO add more
        }
    }

    public void parseFile(java.io.InputStream file) {


        System.out.println("Parse File --->" + file);

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(file));
        String strLine = "";
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
        Row hrw = null, lineRow = null;

        HashMap map = new HashMap();
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl");
        ApplicationModule am = dc.getApplicationModule();

        ViewObject lineVo = am.findViewObject("InvPageLinesDet2VO1");


        try {
            while ((strLine = reader.readLine()) != null) {
                lineNumber++;
                st = new StringTokenizer(strLine, ",");
                System.out.println("Line No --->" + lineNumber);
                if (lineNumber > 1) {
                    hrw = lineVo.createRow();
                    hrw.setNewRowState(Row.STATUS_INITIALIZED);
                    lineVo.insertRow(hrw);
                }
                //System.out.println("View object --->" + hvo);

                while (st.hasMoreTokens()) {
                    //display csv values
                    tokenNumber++;


                    String theToken = st.nextToken();
                    //                    System.out.println("Line # " + lineNumber + ", Token # " +


                    if (lineNumber > 1) {


                        switch (tokenNumber) {
                        case 1:
                            hrw.setAttribute("RollNo",
                                             theToken); //DeliveryDate

                            break;
                        case 2:
                            hrw.setAttribute("RollLength", theToken);
                            break;
                        case 3:
                            hrw.setAttribute("RollLength2", theToken);
                            break;
                        case 4:
                            hrw.setAttribute("Uom", theToken);
                            break;
                        case 5:
                            hrw.setAttribute("RollWidth", theToken);
                            break;
                        case 6:
                            hrw.setAttribute("ShrinkLength", theToken);
                            break;
                        case 7:
                            hrw.setAttribute("ShrinkWidth", theToken);
                            break;
                        case 8:
                            hrw.setAttribute("Shade", theToken); //Shade

                            break;
                        case 9:
                            hrw.setAttribute("Remarks", theToken); //Shade

                            break;

                        }

                    } //end of outer if
                } //end of inner loop
                //reset token number
                tokenNumber = 0;
            } //end of outer loop
            map = null;
            reader = null;

        } catch (Exception e) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Error in Uploaded file",
                                             e.getMessage()));

        }
        //   saveFile();

        System.out.println("Exiting File Upload");

    } //END OF METHOD

    
    
    public String SaveAll() {
        try {
            /*--------Invoice wise Set Supplier Name code by Mr. Sakibul Islam on 27.Feb.2020-----------*/
            setSupplierName(am.getHeaderVO1(), am.getInvoiceWiseSupplierLOV1());            
            
            /*-----------------------------------------convertion of meter into yards-------------------------------------------------------*/
            ViewObject vo = am.getInvPageLinesVO1();
            RowSetIterator ri = vo.createRowSetIterator(null);
            while(ri.hasNext()){
                Row r = ri.next();
                RowSet rs = (RowSet)r.getAttribute("InvPageLinesDet2VO");
                while(rs.hasNext()){
                    Row r2 = rs.next();
                    int RollLengthYds = (int)(MyMath.numeric3(r2.getAttribute("RollLength"))* 1.09361);
                    double RollLengthInch =(MyMath.numeric3(r2.getAttribute("RollLength"))* 1.09361) - RollLengthYds;
                   // RollLengthInch=RollLengthInch*36;
                    double rollLengthInch=RollLengthInch*36;// my edit
                    String uom = r2.getAttribute("Uom").toString();
                    
                    if(uom.equalsIgnoreCase("Meter")){
                        r2.setAttribute("RolLength3", RollLengthYds);
                        //r2.setAttribute("RolLength4", RollLengthInch);
                        r2.setAttribute("RolLength4", rollLengthInch);//my edit
                    }else{
                        
                    }
                }
            }
            
            am.getDBTransaction().commit();
            /*------------------------------------------------------fabric roll no------------------------------------------------------------*/
            ViewObject hvo = am.getHeaderVO1();
            String HeaderId = hvo.getCurrentRow().getAttribute("HeaderId").toString();
            String stmt =
                "BEGIN APPS.auto_roll_sr_no(:1); end;";
            java.sql.CallableStatement cs =
                am.getDBTransaction().createCallableStatement(stmt, 1);
            try {
                cs.setString(1, HeaderId);
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.execute();
                cs.close();
            } catch (Exception e) {
                //status = e.getMessage();
                ;
            }
            
            am.getDBTransaction().commit();
            ViewObject dvo = am.getInvPageLinesDet2VO1();
            dvo.executeQuery();
            /*--------------------------------------------------------------------------------------------------------------------------------*/
            System.out.println("In Save All");
            RowSetIterator it = am.getInvPageLinesDet2VO1().createRowSetIterator("aa");

            /************This is save all for inevntory form ************/

            setTotalInv("SET", "TotalYards", getTotalYrds(0.0));
            /******************************************************/
            double totYrds = it.getRowCount();        
            setTotalInv("SET", "TotalRolls", totYrds);
            /**************************************************************/
            
            it.closeRowSetIterator();
            
            System.out.println("In SAve All Before Commit");

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("Commit");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void setSupplierName(ViewObject voHeader, ViewObject voSupplier){
        try {
            if (voHeader.getCurrentRow().getAttribute("InvoiceNo") !=  null){
                voSupplier.setWhereClause("INVOICE_NO = " + "'" + getInvoice(voHeader).trim() + "'");
                voSupplier.executeQuery();
                System.out.println("SQL: " + '\n' + voSupplier.getQuery());
                
                Row[] rowArray =  voSupplier.getAllRowsInRange();
                System.out.println("Total Rows: " + rowArray.length);
                if (rowArray.length == 0){
                    voHeader.getCurrentRow().setAttribute("VendorName", null);
                    voHeader.getCurrentRow().setAttribute("VendorId", null);
                } else{
                    voHeader.getCurrentRow().setAttribute("VendorName", voSupplier.getCurrentRow().getAttribute("SupplierName"));
                    voHeader.getCurrentRow().setAttribute("VendorId", voSupplier.getCurrentRow().getAttribute("SupplierId"));
                }                
            }
            else {
                System.out.println("found no invoice!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getInvoice(ViewObject vo){
        try {
            String invoice = vo.getCurrentRow().getAttribute("InvoiceNo").toString();
            return invoice;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return null;
    }

    public void bwLengthCmListenerHeader(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void bwWidthCmListenerHeader(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    /************** Code To Get Total ***********************/
    public void getShrinkTabCalculation() {

        System.out.println("in Sabih Coding of Totals .....");

        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =
            am.findViewObject("FabricShrinkLines1"); //ImpSaleContractDetailEOView1

        System.out.println("in Sabih Coding of Totals 2 .....");

        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.0, total = 0.0, awLengthCMVal = 0.0, awWidthCMVal =
            0.0, shrinkLengthPercntVal = 0.0, shrinkWidthPrcntVal = 0.00;

        double shrinkLengthVal = 0.0;
        double shrinkWidthVal = 0.0;
        double bwLengthCMVal = 0.0;
        double bwWidthCMVal = 0.0;
        //    double bwLengthCMVal = MyMath.numeric(getBwLengthCM());
        //    double bwWidthCMVal = MyMath.numeric(getBwWidthCM());


        try {
            //  System.out.println("getBwLengthCM().getValue()   "+getBwLengthCM().getValue());

            //    bwLengthCMVal = Double.parseDouble(getBwLengthCM().getValue().toString())  ;
            bwLengthCMVal = MyMath.numeric(getBwLengthCM());
        } catch (Exception e) {
            // TODO: Add catch code
            bwLengthCMVal = 0.0;
            e.printStackTrace();
        }
        try {

            //   System.out.println("getBwWidthCM().getValue()  "+getBwWidthCM().getValue());
            // bwWidthCMVal =  Double.parseDouble(getBwWidthCM().getValue().toString());
            bwWidthCMVal = MyMath.numeric(getBwWidthCM());
        } catch (Exception e) {
            // TODO: Add catch code
            bwWidthCMVal = 0.0;
            e.printStackTrace();
        }

        System.out.println("in Sabih Coding of Totals 2.4 .....");

        while (it.hasNext()) {
            System.out.println("in Sabih Coding of Totals 2.5 .....");

            Row r = it.next();
            try {

                System.out.println("in Sabih Coding of Totals 2.6 .....");

                try {
                    awLengthCMVal =
                            Double.parseDouble(r.getAttribute("AwLength").toString());
                } catch (Exception e) {
                    // TODO: Add catch code
                    System.out.println("in Sabih Coding of Totals 3 .....");
                    awLengthCMVal = 0.0;
                    e.printStackTrace();
                }

                try {

                    awWidthCMVal =
                            Double.parseDouble(r.getAttribute("AwWidth").toString());

                } catch (Exception e) {
                    // TODO: Add catch code
                    System.out.println("in Sabih Coding of Totals 4 .....");
                    awWidthCMVal = 0.0;
                    e.printStackTrace();
                }

                try {


                    System.out.println("in Sabih Coding of Totals bwLengthCMVal ....." +
                                       bwLengthCMVal);
                    System.out.println("in Sabih Coding of Totals bwWidthCMVal ....." +
                                       bwWidthCMVal);
                    shrinkLengthVal = awLengthCMVal - bwLengthCMVal;

                } catch (Exception e) {
                    // TODO: Add catch code

                    shrinkLengthVal = 0.0;
                    e.printStackTrace();
                }

                System.out.println("in Sabih Coding of Totals 4.5 .....");
                r.setAttribute("ShrinkLength", shrinkLengthVal);
                System.out.println("in Sabih Coding of Totals 5 shrinkLengthVal ....." +
                                   shrinkLengthVal);

                try {


                    if (shrinkLengthVal != 0 && bwLengthCMVal != 0)
                        shrinkLengthPercntVal =
                                shrinkLengthVal / bwLengthCMVal * 100;

                } catch (Exception e) {
                    // TODO: Add catch code

                    shrinkLengthPercntVal = 0.0;
                    e.printStackTrace();
                }
                System.out.println("in Sabih Coding of Totals 5.5 .....");
                r.setAttribute("ShrinkPrcLength", shrinkLengthPercntVal);
                System.out.println("in Sabih Coding of Totals 5.6 shrinkLengthPercntVal....." +
                                   shrinkLengthPercntVal);
                try {

                    shrinkWidthVal = awWidthCMVal - bwWidthCMVal;

                } catch (Exception e) {
                    // TODO: Add catch code
                    System.out.println("in Sabih Coding of Totals 6 .....");
                    shrinkWidthVal = 0.0;
                    e.printStackTrace();
                }
                System.out.println("in Sabih Coding of Totals 5.7 .....");
                r.setAttribute("ShrinkWidth", shrinkWidthVal);
                System.out.println("in Sabih Coding of Totals 5.8 shrinkWidthVal....." +
                                   shrinkWidthVal);
                try {


                    if (shrinkWidthVal != 0 && bwWidthCMVal != 0)
                        shrinkWidthPrcntVal =
                                shrinkWidthVal / bwWidthCMVal * 100;

                } catch (Exception e) {
                    // TODO: Add catch code
                    System.out.println("in Sabih Coding of Totals 6 .....");
                    shrinkWidthPrcntVal = 0.0;
                    e.printStackTrace();
                }

                System.out.println("in Sabih Coding of Totals 5.9 .....");
                r.setAttribute("ShrinkPrcWidth", shrinkWidthPrcntVal);
                System.out.println("in Sabih Coding of Totals 5.10 shrinkWidthPrcntVal....." +
                                   shrinkWidthPrcntVal);
            } catch (Exception e) {
                ;
            }

        } //end of while loop
        it.closeRowSetIterator();
    }


    public void getRollSrNo() {
        OperationBinding operationBinding =
            executeOperation("getRollSerialNo");
        Object result = operationBinding.execute();
    }

    public void setHeaderId(RichInputText headerId) {
        this.headerId = headerId;
    }

    public RichInputText getHeaderId() {
        return headerId;
    }

    public void setUploadFile(RichInputFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public RichInputFile getUploadFile() {
        return uploadFile;
    }

    //    public void TransferRolls(ActionEvent actionEvent) {
    //        // Add event code here...
    //        OperationBinding operationBinding = executeOperation("RollTransfer");
    //        Object result = operationBinding.execute();
    //    }


    public void TransferRolls(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("In Roll Transfer Creation");
        OperationBinding operationBinding = executeOperation("RollTransfer");
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        int value = Integer.parseInt(methodReturnValue.toString());
        //     return value;

        if (value == 1) {

            /* Message Printing */

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message =
                new FacesMessage("Transferred Scuccesfully!!");
            context.addMessage(null, message);


            /* End Message */
        }

        if (value > 1) {

            /* Message Printing */

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Transferred Failed!!");
            context.addMessage(null, message);


            /* End Message */
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(rollTable); // Bind of Table where you are populating data

    }

    public void setDocumentNo(RichInputText documentNo) {
        this.documentNo = documentNo;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(false);
        userSession.setAttribute("DocumentNo", documentNo.getValue());
    }

    public RichInputText getDocumentNo() {
        return documentNo;
    }

    public void setInvoiceNo(RichInputText invoiceNo) {
        this.invoiceNo = invoiceNo;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(false);
        userSession.setAttribute("recptNoS", invoiceNo.getValue());
    }

    public RichInputText getInvoiceNo() {
        return invoiceNo;
    }

    //    public void selectAllCheckBoxVCL(ValueChangeEvent valueChangeEvent) {
    //        // Add event code here...
    //    }

    public void selectAllButtonAction(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println(" Action Source " + actionEvent.getSource());
        BindingContext bindingContext = BindingContext.getCurrent();
        BindingContainer bindingContainer =
            bindingContext.getCurrentBindingsEntry();
        System.out.println("OK");
        OperationBinding ob =
            bindingContainer.getOperationBinding("popupSelectActionListener");
        ob.execute();
        AdfFacesContext adfFaceContext = AdfFacesContext.getCurrentInstance();
        adfFaceContext.addPartialTarget(rollTable);
    }

    public void deselectAllButtonAction(ActionEvent actionEvent) {
        // Add event code here...
        BindingContext bindingContext = BindingContext.getCurrent();
        BindingContainer bindingContainer =
            bindingContext.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindingContainer.getOperationBinding("popupDeselectActionListener");
        System.out.println("OK....");
        operationBinding.execute();
        /*
         AdfFacesContext Object contain the all the facet and Components on the current page.
        */
        AdfFacesContext adfFaces = AdfFacesContext.getCurrentInstance();
        /*
         addPartialTarget("") Method of AdfFacesContext object Refresh the component.
         Accepts String in parameter (Name of the bean of component).
        */
        adfFaces.addPartialTarget(rollTable);
    }

    public void FullTransferRolls(ActionEvent actionEvent) {

        // Add event code here...
        OperationBinding operationBinding =
            executeOperation("RollTransferWhole");
        operationBinding.execute();
    }

    public void DeleteAll(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding operationBinding =
            executeOperation("Detele_All_RecordsLines");
        operationBinding.execute();
        
      
    }
    
    
   

    public void importRoll(ValueChangeEvent valueChangeEvent) {
        
          // Add event code here...
        ViewObject headerVo = am.getHeaderVO1();
        ViewObject pageLineVo = am.getInvPageLinesVO1();
        ViewObject detail2Vo = am.getInvPageLinesDet2VO1();
        
        
        int lineId ;
        String styleSeason ;
         
       
            lineId =Integer.parseInt(pageLineVo.getCurrentRow().getAttribute("LineId").toString()) ;
        /**
         * commented by Sakibul Islam on 06-Dec-2018 for the requirement of Rabiul Alam and set styleseaon to null
         * styleSeason = pageLineVo.getCurrentRow().getAttribute("StyleSeason").toString(); 
         */
         
        styleSeason = " ";
             
           
          
         
        String status;
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        
        uploadRollIntoTempTable(file );
        
        
        // this procedure inserts rolls from temp table to roll table
        
        String stmt  = "BEGIN  IEDOC_BBLC_PKG.MNJ_FAB_ROLL_UPLOAD(:1,:2); end;";
        
        CallableStatement cs = am.getDBTransaction().createCallableStatement(stmt, 1);
        
        
        try {        
                          
            cs.setInt(1,lineId);
            cs.setString(2, styleSeason);
            
            cs.execute();
              cs.close();

        } catch (Exception e) {
            status = e.getMessage();
        }
        
        System.out.println("===============> IEDOC_BBLC_PKG.MNJ_FAB_ROLL_UPLOAD executed... ");
         
       //  refresh the value of file upload input field  
       // refreshQueryKeepingCurrentRow(headerVo);
        
        
        refreshQueryKeepingCurrentRow(pageLineVo);
        detail2Vo.executeQuery();
       
     
       
        getUploadFabricRollFile().setValue(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(uploadFabricRollFile);
        AdfFacesContext.getCurrentInstance().addPartialTarget(inventoryForm);
        

      
        
    }
    
    public void clearSizeBreakDownTable(){
             
             am.getDBTransaction().commit();
             
             ViewObject vo =  am.getInvPageLinesDet2VO1();
             RowSetIterator it = vo.createRowSetIterator("aa");
             while(it.hasNext()){
                 it.next().remove();
             }
             vo.executeEmptyRowSet();
            it.closeRowSetIterator();
             
         }
         
         public void parseFile2(java.io.InputStream file) {
             BufferedReader reader = new BufferedReader(new InputStreamReader(file));
             String strLine = "";
             StringTokenizer st = null;
             int lineNumber = 0, tokenNumber = 0;
             Row rw = null;
              
             ViewObject vo = am.getInvPageLinesDet2VO1();
             
             //read comma separated file line by line
             try
             {
                 while ((strLine = reader.readLine()) != null)
                 {
                     lineNumber++;
                     // create a new row skip the header (header has linenumber 1)
                     if (lineNumber>1) {
                         rw = vo.createRow();
                         rw.setNewRowState(Row.STATUS_INITIALIZED);
                         vo.insertRow(rw);
                     }
                      
                     //break comma separated line using ","
                     //st = new StringTokenizer(strLine, ",");
                     //while (st.hasMoreTokens()){

                   String[] csvCols = strLine.split(",");   
                       
                   for (;tokenNumber < csvCols.length ; tokenNumber++)
                   {
                         //display csv values
                         //tokenNumber++;
                         //String theToken = st.nextToken();

                         String theToken = csvCols[tokenNumber];

                         System.out.println("Line # " + lineNumber + ", Token # " +
                         tokenNumber +
                         ", Token : " + theToken);
                         
                         if (lineNumber>1){
                             // set Attribute Values
                             switch (tokenNumber) {
                                 case 0: rw.setAttribute("SupplierSerialNo", theToken);
                                         break;
                                 case 1: rw.setAttribute("RollNo", theToken);
                                         break;
                                 //case 2: rw.setAttribute("RollSrNo", theToken);
                                  //       break;
                                 case 2: rw.setAttribute("RollLength", theToken);
                                         break;
                                 case 3: rw.setAttribute("RollLength2", theToken);
                                         break;
                                 case 4: rw.setAttribute("Uom", theToken);
                                         break;
                                 case 5: rw.setAttribute("RollWidth", theToken);
                                         break;
                                 case 6: rw.setAttribute("ShrinkLength", theToken);
                                         break;
                                 case 7: rw.setAttribute("ShrinkWidth", theToken);
                                         break;
                                 case 8: rw.setAttribute("Shade", theToken);
                                         break;
                                 case 9: rw.setAttribute("Remarks", theToken);
                                         break;
                                        
                             }
                         }
                     }
                     //reset token number
                     tokenNumber = 0;
                 }
             }
             catch (IOException e) {
             // TODO add more
                 FacesContext fctx = FacesContext.getCurrentInstance();
                 fctx.addMessage(rollTable.getClientId(fctx), new FacesMessage(FacesMessage.SEVERITY_ERROR,
                 "Content Error in Uploaded file", e.getMessage()));
             }
             catch (Exception e) {
             FacesContext fctx = FacesContext.getCurrentInstance();
             fctx.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
             "Data Error in Uploaded file", e.getMessage()));
             }
         }
         
   
    public void exportToCSV(FacesContext facesContext,
                               OutputStream outputStream) throws IOException {
           // Add event code here...
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
           
           writer.write("Supplier Serial No.");writer.write(",");
           writer.write("Supplier Roll No.");writer.write(",");
           //writer.write("Factory Roll No.");writer.write(",");
           writer.write("Roll length Yards");writer.write(",");
           writer.write("Roll length Inch");writer.write(",");
           writer.write("UOM");writer.write(",");
           writer.write("Roll Width (Inch)");writer.write(",");
           writer.write("Shrinkage% length");writer.write(",");
           writer.write("Shrinkage% width");writer.write(",");
           writer.write("Shade");writer.write(",");
           writer.write("Remarks");
           writer.newLine();
           writer.flush();
           writer.close();
    }


    public void fillInvoiceDialogueListener(DialogEvent dialogEvent) {
       
    ViewObject lineVo =  am.getInvPageLinesVO1();
        
        ViewObject InvoiceVo = am.getInvoiceVo1();
        InvoiceVo.setRangeSize(1000);
        
        Row[] invoiceRows = InvoiceVo.getAllRowsInRange();
        
        String checkBox; 
        
        for (Row invoiceRow : invoiceRows){
            
            try{
                checkBox = invoiceRow.getAttribute("CheckBox").toString();
            } catch(Exception e){
                checkBox="n";
             }
            
            
             if(checkBox.equals("y")){
                 
                  Row lineRow  = lineVo.createRow();
                  populateInvoiceRow(invoiceRow, lineRow );                                  
                
            }
                
            
        }
        
        
        
    }


    private void populateInvoiceRow(Row invoiceRow, Row lineRow ) {
        
        try{
            lineRow.setAttribute("Color", invoiceRow.getAttribute("Color").toString());
        }catch(Exception e){
            lineRow.setAttribute("Color","");
        }
        try{
            lineRow.setAttribute("FabConst", invoiceRow.getAttribute("Construction").toString());
        }catch(Exception e){
            lineRow.setAttribute("FabConst", "");
        }
        
        try{
            lineRow.setAttribute("FabComp", invoiceRow.getAttribute("Composition").toString());
        }catch(Exception e){
            lineRow.setAttribute("FabConst","");
        }
        
        try{
            lineRow.setAttribute("FabOunce", invoiceRow.getAttribute("FabOunce").toString());
        }catch(Exception e){
            lineRow.setAttribute("FabOunce","");
        }
        
       try{
           lineRow.setAttribute("StyleSeason", invoiceRow.getAttribute("StyleSeason").toString());
       }catch(Exception e){
           lineRow.setAttribute("StyleSeason","");
       }
       
       try{
           lineRow.setAttribute("ArticleNo", invoiceRow.getAttribute("ArticleNo").toString());
       }catch(Exception e){
           lineRow.setAttribute("ArticleNo","");
       }
        
     
   }


    private void parseFabricRollFile(java.io.InputStream file) {
          

        System.out.println("Parse File --->" + file);

        BufferedReader reader =
            new BufferedReader(new InputStreamReader(file));
        String strLine = "";
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
        Row hrw = null, lineRow = null;

        HashMap map = new HashMap();
        ViewObject lineVo = am.getMnjMfgFabinsDetailTempVO1();


        try {
            while ((strLine = reader.readLine()) != null) {
                lineNumber++;
                st = new StringTokenizer(strLine, ",");
                System.out.println("Line No --->" + lineNumber);
                if (lineNumber > 1) {
                    hrw = lineVo.createRow();
                    hrw.setNewRowState(Row.STATUS_INITIALIZED);
                    lineVo.insertRow(hrw);
                }
                /*  */

                while (st.hasMoreTokens()) {
                    //display csv values
                    tokenNumber++;


                    String theToken = st.nextToken();


                    if (lineNumber > 1) {


                        System.out.println("Line No Case --->" + theToken);

                        switch (tokenNumber) {
                        case 1:
                                hrw.setAttribute("SupplierSerialNo", theToken);
                                System.out.println("SupplierSerialNo 123 --->" +
                                                   tokenNumber + theToken);
                                break;
                        case 2:
                            hrw.setAttribute("RollNo", theToken);
                            System.out.println("RollNo 123 --->" +
                                               tokenNumber + theToken);
                            break;

                            /*
                            System.out.println("Line No Case --->" + theToken);
                            hrw.setAttribute("RollSrNo", theToken);
                            System.out.println("Line No Case 123 --->" +
                                               theToken);
                            break;
                            */

                        case 3:
                        
                            hrw.setAttribute("RollLength", theToken);
                            System.out.println("RollLength 123--->" +
                                               tokenNumber + theToken);

                            break;

                        /*
                            hrw.setAttribute("RollNo", theToken);
                            System.out.println("RollNo 123 --->" +
                                               tokenNumber + theToken);
                            break;
                        */

                        case 4:
                            hrw.setAttribute("RollLength2", theToken);
                            System.out.println("RollLength2 --->" +
                                               tokenNumber + theToken);

                            break;
                            
                           /*
                            hrw.setAttribute("RollLength", theToken);
                            System.out.println("RollLength 123--->" +
                                               tokenNumber + theToken);

                            break;
                          */
                        case 5:
                            hrw.setAttribute("Uom", theToken);
                            System.out.println("Uom --->" + tokenNumber +
                                               theToken);
                            break;
                            
                            /*
                            hrw.setAttribute("RollLength2", theToken);
                            System.out.println("RollLength2 --->" +
                                               tokenNumber + theToken);

                            break;
                            */
                        case 6:
                            hrw.setAttribute("RollWidth", theToken);
                            System.out.println("RollWidth --->" + tokenNumber +
                                               theToken);

                            break;
                            
                            /*
                            hrw.setAttribute("Uom", theToken);
                            System.out.println("Uom --->" + tokenNumber +
                                               theToken);
                            break;
                            */
                        case 7:
                            hrw.setAttribute("ShrinkLength", theToken);
                            System.out.println("ShrinkLength --->" +
                                               tokenNumber + theToken);
                            break;
                            
                            /*
                            hrw.setAttribute("RollWidth", theToken);
                            System.out.println("RollWidth --->" + tokenNumber +
                                               theToken);

                            break;
                            */
                        case 8:
                            hrw.setAttribute("ShrinkWidth", theToken);
                            System.out.println("ShrinkWidth --->" +
                                               tokenNumber + theToken);
                            break;
                            /*
                            hrw.setAttribute("ShrinkLength", theToken);
                            System.out.println("ShrinkLength --->" +
                                               tokenNumber + theToken);
                            break;
                            */
                        case 9:
                           
                            hrw.setAttribute("Shade", theToken);
                            System.out.println("Shade --->" +
                                               tokenNumber + theToken);
                            break;
                            
                        case 10:
                               
                            hrw.setAttribute("Remarks", theToken);
                            System.out.println("Remarks --->" +
                                               tokenNumber + theToken);
                                break;
                            
                        
                        }

                    }
                }

                tokenNumber = 0;
            }
            map = null;
            reader = null;

        } catch (Exception e) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Error in Uploaded file",
                                             e.getMessage()));

        }
        
        
    }


    private void uploadRollIntoTempTable(UploadedFile file) {
        try {

            parseFabricRollFile(file.getInputStream());
          //  AdfFacesContext.getCurrentInstance().addPartialTarget(shipmentTable);

          } catch (IOException e) {
            // TODO add more
            System.out.println("if errors-->" + e.getMessage());

        }
        
        
        BindingContainer bindings = getBindings();

        OperationBinding operationBinding1 =
            bindings.getOperationBinding("Commit");
        Object result = operationBinding1.execute();
        
    }

    public void setUploadFabricRollFile(RichInputFile uploadFabricRollFile) {
        this.uploadFabricRollFile = uploadFabricRollFile;
    }

    public RichInputFile getUploadFabricRollFile() {
        return uploadFabricRollFile;
    }
    
    
    
    
    
    public void refreshQueryKeepingCurrentRow(ViewObject viewObject )  {
        
        
         Row currentRow;
         Key currentRowKey;
         
         // added on 7.jan.18 to handle exception if view object has no current row
        try{
           currentRow = viewObject.getCurrentRow();
           currentRowKey = currentRow.getKey();
        }
        catch(Exception e){
            return;
        }
        
     
       
      
       int rangePosOfCurrentRow = viewObject.getRangeIndexOf(currentRow);
       int rangeStartBeforeQuery = viewObject.getRangeStart();
       viewObject.executeQuery();
       viewObject.setRangeStart(rangeStartBeforeQuery);
       /*
        * In 10.1.2, there is this convenience method we could use
        * instead of the remaining lines of code
        *
        *  findAndSetCurrentRowByKey(currentRowKey,rangePosOfCurrentRow);
        *  
        */
       
         
       Row[] rows = viewObject.findByKey(currentRowKey, 1);
       if (rows != null && rows.length == 1)
       {
          viewObject.scrollRangeTo(rows[0], rangePosOfCurrentRow);
          viewObject.setCurrentRowAtRangeIndex(viewObject.getRangeIndexOf(rows[0]));
       }
       
               
     }

    private void saveAllData() {
    }

    public void setInventoryForm(RichForm inventoryForm) {
        this.inventoryForm = inventoryForm;
    }

    public RichForm getInventoryForm() {
        return inventoryForm;
    }

    public void selectAllFillRoll(ActionEvent actionEvent) {
        
                OperationBinding operationBinding = executeOperation("SelectAllRoll");
                operationBinding.getParamsMap().put("flag", "Y");
                
                System.out.println("Call Select All " );
                operationBinding.execute();
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(fillRollNewPopUpTable);
            }

    public void setFillRollTable(RichTable fillRollTable) {
        this.fillRollTable = fillRollTable;
    }

    public RichTable getFillRollTable() {
        return fillRollTable;
    }

    public void deselectAllFillRoll(ActionEvent actionEvent) {
       
                OperationBinding operationBinding = executeOperation("SelectAllRoll");
                operationBinding.getParamsMap().put("flag", "N");
                
                System.out.println();
                System.out.println("Call De-Select All " );
                operationBinding.execute();
                AdfFacesContext.getCurrentInstance().addPartialTarget(fillRollNewPopUpTable);
            }

    public void editPopUpFetchListenerFillRoll(PopupFetchEvent popupFetchEvent) {
       
            OperationBinding operationBinding =
                executeOperation("resetViewObject");
            operationBinding.execute();
              
//            ViewObject vo1 = am.getFillRollVO1();
//            
//            //        String Query = vo1.getQuery();
//            //        System.out.println("Pop Up Query ---> " + Query);
//            String org =  getOrgId();
//            System.out.println("Org Id ---> " + org);
//
//                        Map sessionScope = ADFContext.getCurrent().getSessionScope();
//                
//                        String org = (String)sessionScope.get("orgId");
//                   String org ="340";
//                
//                vo1.setWhereClause("ORG_ID     = '" + org + "'");
//                vo1.executeQuery();
        }

    public void editPopUpCancelListenerFillRoll(PopupCanceledEvent popupCanceledEvent) {
        OperationBinding operationBinding =
            executeOperation("resetViewObject");
        operationBinding.execute();
    }
    
    private String getOrgId() {
        ViewObject vo = am.getHeaderVO1();

        String OrgId = vo.getCurrentRow().getAttribute("OrgId").toString();

        return OrgId;


    }

    public void editDialogListenerFillRoll(DialogEvent dialogEvent) {
        System.out.println("Inside  @editDialogListenerFillRoll()... ");
        ViewObject popvo = am.getFillRollNewVO2();
        RowSetIterator it = popvo.createRowSetIterator("aa");
        
        while (it.hasNext()){
                Row row = it.next();
                try{
                    //String Flag = String.valueOf(row.getAttribute("CheckBox"));
                    //System.out.println("ItemMappingVO flag -->" + populatevo.getCurrentRow().getAttribute("Selection") ); 
                    System.out.println("FillRollVO Flag --> " + (String)row.getAttribute("SelctionBox"));
                    if (row.getAttribute("SelctionBox") != null && row.getAttribute("SelctionBox").equals("Y")){
                        System.out.println(" Inside @Y Condition... ");
                        insertIntoRollTable(row);
                        
                        }
                    
                } catch (Exception e){
                    
                    e.printStackTrace();
                    
                    }
                }
                
                it.closeRowSetIterator();
                AdfFacesContext.getCurrentInstance().addPartialTarget(rollTable);
            }
        
        
        
       
    

        public void insertIntoRollTable(Row PopUpRow) { //insert into Roll table from FillRoll PopUp
        System.out.println(" In @Insert Method ");
        
        Row RollTableRow = createRollTableLines();
        
        RollTableRow.setAttribute("SupplierSerialNo",
                         getPopulateValue(PopUpRow, "SupplierSerialNo"));
    
        
        RollTableRow.setAttribute("RollNo",
                         getPopulateValue(PopUpRow, "SuppRollNo")); //getPopulateValue() returns column value
        
        RollTableRow.setAttribute("RollSrNo",
                         getPopulateValue(PopUpRow, "RollNo"));
        
        RollTableRow.setAttribute("RollLength", getPopulateValue(PopUpRow,"RollLength"));
         

        RollTableRow.setAttribute("RollLength2", getPopulateValue(PopUpRow, "RollLength2"));
        
//        RollTableRow.setAttribute("Uom", getPopulateValue(PopUpRow, "Uom"));
//        
        RollTableRow.setAttribute("RolLength3", getPopulateValue(PopUpRow,"RollLength"));
       
        RollTableRow.setAttribute("RolLength4",  getPopulateValue(PopUpRow,"RollLength2"));
//         
//        RollTableRow.setAttribute("RollWidth",  getPopulateValue(PopUpRow,"RollWidth")); 
//
//        RollTableRow.setAttribute("ShrinkLength",  getPopulateValue(PopUpRow,"ShrinkLength"));
//            
//        RollTableRow.setAttribute("ShrinkWidth",   getPopulateValue(PopUpRow,"ShrinkWidth"));
//            
//        RollTableRow.setAttribute("Shade", getPopulateValue(PopUpRow, "Shade"));
//            
//        RollTableRow.setAttribute("Remarks", getPopulateValue(PopUpRow, "Remarks"));
//            
//            
//        RollTableRow.setAttribute("TransferSelection",
//                             getPopulateValue(PopUpRow, "TransferSelection"));
    
        
        
        
        }

        public Row createRollTableLines() {

        ViewObject RollTableVO = am.getInvPageLinesDet2VO1();
        Row row =
        RollTableVO.createRow(); //Creates a new Row object, but does not insert it into the Row Set
        RollTableVO.insertRow(row); //Inserts a row to the Row Set, before the current row
        row.setNewRowState(Row.STATUS_INITIALIZED); //Sets a new unposted row, created in this transaction,
        //to either STATUS_NEW or STATUS_INITIALIZED(new row but temporary row)
        //mode
        return row;
        }
        
    public String getPopulateValue(Row r, String columnName) {

        String value = null;
        try {
            value = r.getAttribute(columnName).toString();
        } catch (Exception e) {
            ;
        }
        return value;
    }
    
    
        
//    public Object getValueInNumber(Row r, String columnName) {
//
//        String value = null;
//        int valueInNumber = 0;
//        try {
//            value = r.getAttribute(columnName).toString();
//            valueInNumber = Integer.parseInt(value);
//        } catch (Exception e) {
//            ;
//        }
//        return valueInNumber;
//    }

    public void setFillRollNewPopUpTable(RichTable fillRollNewPopUpTable) {
        this.fillRollNewPopUpTable = fillRollNewPopUpTable;
    }

    public RichTable getFillRollNewPopUpTable() {
        return fillRollNewPopUpTable;
    }

    public String filterMethod() {
        // Add event code here...
        ViewObject ovj = am.getFillRollNewVO2();
        ViewObject searchVO = am.getFilterView1();
        String buyer, season, unit, fit, itemDescription, enduser,brand, style, invoice;
        String filter = null;
        StringBuilder setVal = null;
        setVal = new StringBuilder();
        String[] array = new String[4];
        int i = 0;

        try {
            buyer = searchVO.getCurrentRow().getAttribute("Buyer").toString();
            //  String buyerwhrcls="BUYER =byer;
            array[i] = "BUYER_NAME = '" + buyer + "'";
            System.out.println("Value of Buyer: " + array[i]);
            i++;
            //                 setVal.append(String.format("BUYER = '"+buyer+"'"));
            //                 filter=setVal.toString();
            //                 setVal=null;
        } catch (Exception e) {
            buyer = null;
        }

        try {
            season =
                    searchVO.getCurrentRow().getAttribute("Season").toString();
            array[i] = "SEASON = '" + season + "'";
            System.out.println("Value of Season: " + array[i]);
            i++;
        } catch (Exception e) {
            season = null;
        }

//        try {
//            unit = searchVO.getCurrentRow().getAttribute("Unit").toString();
//            array[i] = "PRODUCTION_UNIT = '" + unit + "'";
//            i++;
//        } catch (Exception e) {
//            unit = null;
//        }
//        try {
//            fit = searchVO.getCurrentRow().getAttribute("Fit").toString();
//            array[i] = "FIT= '" + fit + "'";
//            i++;
//        } catch (Exception e) {
//            fit = null;
//        }
//        try {
//            enduser =
//                    searchVO.getCurrentRow().getAttribute("EndUser").toString();
//            array[i] = "END_USER= '" + enduser + "'";
//            i++;
//        } catch (Exception e) {
//            enduser = null;
//        }
//        try {
//            itemDescription =
//                    searchVO.getCurrentRow().getAttribute("ItemDescription").toString();
//            array[i] = "ITEM_DESCRIPTION= '" + itemDescription + "'";
//            i++;
//        } catch (Exception e) {
//            itemDescription = null;
//        }
//        try {
//            brand =
//                    searchVO.getCurrentRow().getAttribute("Brand").toString();
//            array[i] = "BRAND = '" + brand + "'";
//            i++;
//        } catch (Exception e) {
//            brand = null;
//        }
        
        try {
            style =
                    searchVO.getCurrentRow().getAttribute("Style").toString();
            array[i] = "STYLE = '" + style + "'";
            System.out.println("Value of Style: " + array[i]);
            i++;
        } catch (Exception e) {
            brand = null;
        }
        
        try {
            invoice =
                    searchVO.getCurrentRow().getAttribute("Invoice").toString();
            array[i] = "INVOICE_NO = '" + invoice + "'";
            System.out.println("Value of Invoice: " + array[i]);
            i++;
        } catch (Exception e) {
            brand = null;
        }
        for (int j = 0; j < i; j++) {
            if(array[j]==null) {
                
                break;
            }
            setVal.append(String.format(array[j])); 
            if(j+1 != i){
                setVal.append(String.format(" AND "));
            }

           
        }

        filter = setVal.toString();
        System.out.println(filter);
        ovj.setWhereClause(filter);
        ovj.executeQuery();
    //        ovj.setWhereClause("BUYER = '" + buyer + "' AND SEASON = '" + season +
    //                           "'");
        return null;
    }

    public void fillPIPopUpEvent(PopupFetchEvent popupFetchEvent) {
        try {
            ViewObject vo = am.getInvoiceVo1();
            vo.executeQuery();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    public void CusToMsave(ActionEvent actionEvent) {
        // Add event code here...
        
        am.getDBTransaction().commit();
                   System.out.println("Commit sucessfully......");
                   ViewObject vo=am.getInvPageDetailsVO1();
                   vo.executeQuery();
        
        
    }
  
    public void setOrgid(RichInputText orgid) {
        this.orgid = orgid;
        FacesContext fctx = FacesContext.getCurrentInstance();
                ExternalContext ectx = fctx.getExternalContext();
                HttpSession userSession = (HttpSession)ectx.getSession(false);
                userSession.setAttribute("OrgId", orgid.getValue());
            
        System.out.println(orgid.getValue());
        
    }

    public RichInputText getOrgid() {
        return orgid;
    }
}
