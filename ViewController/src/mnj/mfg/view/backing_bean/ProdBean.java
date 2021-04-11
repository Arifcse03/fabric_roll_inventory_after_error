package mnj.mfg.view.backing_bean;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adf.view.rich.util.ResetUtils;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.status.Severity;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class ProdBean {
    private RichInputText rollLenYrdsBean;
    ViewObject MnjMfgFabinsPtrnalocDView1,ProdPageShedDetVO1,ProdPageLinesVO1, FabricShrinkVie, FabricShrinkLines1, FebricOunceDetailView1, MnjMfgFabinsSecndD2View1, MnjMfgFabinsSecndDView1;
    private RichTable lineTable;
    private RichTable selectAllTable;
    private RichTable shrinkProdTable;
    private RichInputText blncRolls;
    private RichTable shadeRollTable;
    private RichTable fabInsTable;
    private RichTable defectCodeTable;
    private RichTable ounceDetTable;
    private RichTable ounceLineTable;
    private RichInputText detVariance;
    private RichInputText detVariPrcnt;
    private RichInputText point1;
    private RichInputText point2;
    private RichInputText point3;
    private RichInputText point4;
    private RichInputText totalPoints;
    private RichTable fabInsDetTable;
    private RichTable fabInsFirstTable;
    private RichInputText cutableWidth;
    private RichInputText actualLength;
    private RichInputText totalPanPoints;
    private RichInputText totalDetPoints;
    private RichTable fabricShadeTable;
    private RichTable fabricInspRollTable;
    private RichInputText rollWidthFabBind;
    private RichInputText cutWidthFabBind;
    private RichInputText allocatedYardsLine;
    private RichInputText allocatedInchesLine;
    private RichInputText adjustCutableWidth;
    private RichInputText headerNo;
    private RichInputText headerId;
    private RichColumn lineBuyerId;
    private RichInputComboboxListOfValues lineStyle;
    private RichInputText lineSeason;
    private RichInputComboboxListOfValues lineColor;
    private RichInputText lineBuyerIdNew;
    private RichInputText actualLengthYardsInspection;
    private RichInputText actualLengthInchInspection;
    private RichTable shadeTable;
    private RichPopup stylePopUpBinding;
    private RichTable skewMovementTable;
    private RichTable skewRollTable;
    private RichInputText skew_AC;
    private RichInputText skew_BD;
    private RichInputText skew_ACminusBD;
    private RichInputText skew_ACplusBD;
    private RichInputText skew_result;
    static DecimalFormat format = new DecimalFormat("##.00");
    private RichPanelCollection skewMovementPanel;
    private RichTable skewMovementCalculationTable;
    private RichTable codeOfDefect;
    private RichPopup skewPopup;
    private Object fatchpopu;


    public ProdBean() {
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        FabricShrinkLines1 = am.findViewObject("FabricShrinkLines1");
        ProdPageLinesVO1   = am.findViewObject("ProdPageLinesVO1");
        FebricOunceDetailView1 = am.findViewObject("FebricOunceDetailView1");
        FebricOunceDetailView1 = am.findViewObject("FebricOunceDetailView1");
        MnjMfgFabinsSecndD2View1 =
                am.findViewObject("MnjMfgFabinsSecndD2View1");
        MnjMfgFabinsSecndDView1 = am.findViewObject("MnjMfgFabinsSecndDView1");
        ProdPageShedDetVO1      = am.findViewObject("ProdPageShedDetVO1");
        MnjMfgFabinsPtrnalocDView1 = am.findViewObject("ProdPageShedDetVO1");

    }
    
    

    public void setRollLenYrdsBean(RichInputText rollLenYrdsBean) {
        this.rollLenYrdsBean = rollLenYrdsBean;
    }

    public RichInputText getRollLenYrdsBean() {
        return rollLenYrdsBean;
    }

    public void RollLengthListenerYrds(ValueChangeEvent valueChangeEvent) {
        // Add event code here...ro
        
        int flag = 0 ;
        
        System.out.println(" Delete Validation");
        
        Row currentRowCheck = FabricShrinkLines1.getCurrentRow();
        String RollCheck = currentRowCheck.getAttribute("RollNo").toString();
        
        System.out.println(" Delete RollCheck....... "+RollCheck);
        
        RowSetIterator itFabricShade =
            MnjMfgFabinsSecndDView1.createRowSetIterator("qq");

        String RollAssign;
        
        while (itFabricShade.hasNext()) 
        {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();
            
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollCheck In Loop....... "+RollCheck);
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollAssign....... "+RollAssign);
            
            if (RollAssign.equals(RollCheck)) //RollAssign==RollCheck
            {
                System.out.println("MnjMfgFabinsSecndDView1 Delete In If of flag....... "+flag);
                flag = flag + 1;  
            }
            
        }

        itFabricShade.closeRowSetIterator();
        
        
        
        
        System.out.println(" Delete flag....... "+flag);
        
        if (flag > 0) 
        {
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't Change the Value as it is already Assigned");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            ResetUtils.reset(valueChangeEvent.getComponent());

        
        }
        else 
        {
            
            System.out.println("RollLengthListenerYrds  Value Change Litener "+valueChangeEvent.getNewValue());
            setLinesBlnc("Y", MyMath.numeric3(valueChangeEvent.getNewValue()));
            allocatedYardsLine.setValue(valueChangeEvent.getNewValue().toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(allocatedYardsLine);
            
        }
        
        
    }
    
    public void RollLengthListenerInches(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        int flag = 0 ;
        
        System.out.println(" Delete Validation");
        
        Row currentRowCheck = FabricShrinkLines1.getCurrentRow();
        String RollCheck = currentRowCheck.getAttribute("RollNo").toString();
        
        System.out.println(" Delete RollCheck....... "+RollCheck);
        
        RowSetIterator itFabricShade =
            MnjMfgFabinsSecndDView1.createRowSetIterator("qq");

        String RollAssign;
        
        while (itFabricShade.hasNext()) 
        {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();
            
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollCheck In Loop....... "+RollCheck);
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollAssign....... "+RollAssign);
            
            if (RollAssign.equals(RollCheck)) //RollAssign==RollCheck
            {
                System.out.println("MnjMfgFabinsSecndDView1 Delete In If of flag....... "+flag);
                flag = flag + 1;  
            }
            
        }

        itFabricShade.closeRowSetIterator();
        
        
        
        
        System.out.println(" Delete flag....... "+flag);
        
        if (flag > 0) 
        {
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't Change the Value as it is already Assigned");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            ResetUtils.reset(valueChangeEvent.getComponent());

        
        }
        else 
        {
            
            int val = 0;
            
            try 
            {
                val = Integer.parseInt(valueChangeEvent.getNewValue().toString()) ;  
            } catch (Exception e) 
            {
                val = 0;
            }
            
            
            
            if( val  > 35 ){
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Can't Enter Value Greate than 35");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
                
                // Reset inputFile component after upload
                ResetUtils.reset(valueChangeEvent.getComponent());
            }
            else 
            {
            System.out.println("getNewValue().toString()  "+valueChangeEvent.getNewValue());
            allocatedInchesLine.setValue(valueChangeEvent.getNewValue());
            AdfFacesContext.getCurrentInstance().addPartialTarget(allocatedInchesLine);
            }
             
        }
        
        
       
        }

    public void setLineTable(RichTable lineTable) {
        this.lineTable = lineTable;
    }

    public RichTable getLineTable() {
        return lineTable;
    }

    public double setGetLineVal(String type, String column, double value) {

        oracle.adf.view.rich.component.UIXTable table = getLineTable();
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
                AdfFacesContext.getCurrentInstance().addPartialTarget(lineTable);
            } else if (type.equalsIgnoreCase("GET")) {
                val = MyMath.numeric3(selectedRow.getAttribute(column));

            }

        } //END OF LOOP
        return val;
    } //end if while loop

    public double getTotalFabShrinkTab(String name, String type,
                                       double currentVal) {


        RowSetIterator it = FabricShrinkLines1.createRowSetIterator("tt");
        Row currentRow = FabricShrinkLines1.getCurrentRow();
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            if (type.equals("Y")) {
                if (r == currentRow) {
                    total = total + currentVal;
                    continue;
                }
            }
            total = total + MyMath.numeric3(r.getAttribute(name)); //RollLength
        }
        it.closeRowSetIterator();
        return total;
    }

    public double getTotalRollsInch(String type, double currentVal) {


        RowSetIterator it = FabricShrinkLines1.createRowSetIterator("tt");
        Row currentRow = FabricShrinkLines1.getCurrentRow();
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            if (type.equals("Y")) {
                if (r == currentRow) {
                    total = total + currentVal;
                    continue;
                }
            }
            total = total + MyMath.numeric3(r.getAttribute("RollLength2"));
        }
        it.closeRowSetIterator();
        return total;
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createFabShrnk() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        setLinesBlnc("N", 0);
        return null;
    }

    public String DelFbShrnk() 
    {
        int flag = 0 ;
        
        System.out.println(" Delete Validation");
        
        Row currentRowCheck = FabricShrinkLines1.getCurrentRow();
        String RollCheck = currentRowCheck.getAttribute("RollNo").toString();
        
        System.out.println(" Delete RollCheck....... "+RollCheck);
        
        RowSetIterator itFabricShade =
            ProdPageShedDetVO1.createRowSetIterator("qq");

        String RollAssign;
        
        while (itFabricShade.hasNext()) 
        {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();
            
            System.out.println(" Delete RollCheck In Loop....... "+RollCheck);
            System.out.println(" Delete RollAssign....... "+RollAssign);
            
            if (RollAssign.equals(RollCheck)) //RollAssign==RollCheck
            {
                System.out.println(" Delete In If of flag....... "+flag);
                flag = flag + 1;  
            }
            
        }

        itFabricShade.closeRowSetIterator();
        
        
        
        
        System.out.println(" Delete flag....... "+flag);
        
        if (flag > 0) 
        {
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("This Roll is Already Assigned,Can't Delete Roll");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, message);

        
        }
        else 
        {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("Delete");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
            setLinesBlnc("N", 0);
            
        }
        


        return null;
    }

    public double getTotalRollsYardsProc(String type) {

        OperationBinding operationBinding =
            executeOperation("getTotalRollsYards");
        System.out.println("Type in bean------->" + type);
        operationBinding.getParamsMap().put("type", type);
        operationBinding.getParamsMap().put("headerid", "none");
        operationBinding.getParamsMap().put("color", "none");


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;


        double value = MyMath.numeric3(methodReturnValue);

        return value;

    }


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

    public String printDate(String date, int days) {


        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date1 = new java.util.Date();

        try {
            date1 = df.parse(date);
        } catch (ParseException e) {
            ;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.DATE, days);
        System.out.println(dateFormat.format(c.getTime()));

        return dateFormat.format(c.getTime());


    }


    public void SelectAll(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding operationBinding = executeOperation("selectAllLines");
        operationBinding.getParamsMap().put("flag", "Y");
        operationBinding.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectAllTable);
    }


    public void DeSelectAll(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding operationBinding = executeOperation("selectAllLines");
        operationBinding.getParamsMap().put("flag", "N");
        operationBinding.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectAllTable);
    }
    
    
   // 
    
   public void SelectAllFabricShade(ActionEvent actionEvent) {
       // Add event code here...
       OperationBinding operationBinding = executeOperation("selectAllLinesFabricShade");
       operationBinding.getParamsMap().put("flag", "Y");
       operationBinding.execute();
       AdfFacesContext.getCurrentInstance().addPartialTarget(fabricShadeTable);
   }


   public void DeSelectAllFabricShade(ActionEvent actionEvent) {
       // Add event code here...
       OperationBinding operationBinding = executeOperation("selectAllLinesFabricShade");
       operationBinding.getParamsMap().put("flag", "N");
       operationBinding.execute();
       AdfFacesContext.getCurrentInstance().addPartialTarget(fabricShadeTable);
   }
    
    
    
  //  
    
  // 
   
  public void SelectAllFabricRollInsp(ActionEvent actionEvent) {
      // Add event code here...
      OperationBinding operationBinding = executeOperation("selectAllLinesFabricRollInsp");
      operationBinding.getParamsMap().put("flag", "Y");
      operationBinding.execute();
      AdfFacesContext.getCurrentInstance().addPartialTarget(fabricInspRollTable);
  }


  public void DeSelectAllFabricRollInsp(ActionEvent actionEvent) {
      // Add event code here...
      OperationBinding operationBinding = executeOperation("selectAllLinesFabricRollInsp");
      operationBinding.getParamsMap().put("flag", "N");
      operationBinding.execute();
      AdfFacesContext.getCurrentInstance().addPartialTarget(fabricInspRollTable);
  }
   
   
   
  //

    public void setSelectAllTable(RichTable selectAllTable) {
        this.selectAllTable = selectAllTable;
    }

    public RichTable getSelectAllTable() {
        return selectAllTable;
    }


    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {

        OperationBinding operationBinding =
            executeOperation("setColorWiseWherClause");
        operationBinding.execute();
    }

    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {

    }


    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {


            OperationBinding operationBinding =
                executeOperation("populateShrink");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);

            setLinesBlnc("N", 0);

        }

    }

    public void editPopupFetchLisShade(PopupFetchEvent popupFetchEvent) {

        OperationBinding operationBinding =
            executeOperation("setProdShadeRolsWhereClause");
        operationBinding.execute();
    }

    public void editDialogListShade(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {


            OperationBinding operationBinding =
                executeOperation("populateShade");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(shadeRollTable);
            
            setLinesValues("TotalRolls", gettotalRols());

        }

    }

    public void setShrinkProdTable(RichTable shrinkProdTable) {
        this.shrinkProdTable = shrinkProdTable;
    }

    public RichTable getShrinkProdTable() {
        return shrinkProdTable;
    }


    public void setLinesBlnc(String type, double curntValue) {

        double totalAlocYrds =
            getTotalFabShrinkTab("AlocYard", type, curntValue); //TotalAlocYrds

        double TotalAlocInch = getTotalFabShrinkTab("AlocInch", type, curntValue); //TotalAlocInch //
        
        System.out.println("totalAlocYrds   "+totalAlocYrds);
        System.out.println("TotalAlocInch   "+TotalAlocInch);

       // double InvTotalYrds = getTotalRollsYardsProc("Y");
        long tempInch = 0;
        if (TotalAlocInch >=36) {
            
            tempInch = (long)(TotalAlocInch/36);            
            totalAlocYrds = totalAlocYrds +tempInch;
            //  TotalAlocInch = (TotalAlocInch/36) - tempInch ;
              TotalAlocInch = ( (TotalAlocInch/36) - tempInch ) * 36 ;
            
            
        }
        setGetLineVal("SET", "TotalAlocYrds", (totalAlocYrds));
        setGetLineVal("SET", "TotalAlocInch", (TotalAlocInch));
        
        // By Sabih
        
        double TotalYdsBalance = getTotalFabShrinkTab("BlncYards", type, curntValue);
        //double TotalYdsBalanceAct = 0.0;
        double TotalIncBalance=getTotalFabShrinkTab("BlncInch", type, curntValue);
        
        long tempTotInch = 0;
        if (TotalIncBalance >=36) {
            
            tempTotInch = (long)(TotalIncBalance/36);            
            TotalYdsBalance = TotalYdsBalance +tempTotInch;
            TotalIncBalance = (TotalIncBalance/36) - tempTotInch;
            TotalIncBalance = TotalIncBalance *36 ;
            
            
        }
//        
//        long TempInchBal = 0;
//                   
//            TempInchBal = (long)(TotalYdsBalance);            
//            TotalYdsBalanceAct = TempInchBal;
//            TotalIncBalanceAct = (TotalYdsBalance) - TempInchBal;
//            TotalIncBalanceAct = (TotalIncBalanceAct * 36) - TotalAlocInch ;
        
        setGetLineVal("SET", "BlncYrds",TotalYdsBalance );
        setGetLineVal("SET", "BlncInch",(TotalIncBalance));

       //
        
        RowSetIterator it = FabricShrinkLines1.createRowSetIterator("tt");
        double totRolls = it.getRowCount();
        it.closeRowSetIterator();
        double InvTotalRollLength = getTotalRollsYardsProc("R");

        setGetLineVal("SET", "TotalAlocRolls", totRolls);
        setGetLineVal("SET", "BlncRolls", (InvTotalRollLength - totRolls));

    }

    public void setBlncRolls(RichInputText blncRolls) {
        this.blncRolls = blncRolls;
    }

    public RichInputText getBlncRolls() {
        return blncRolls;
    }

    public void setShadeRollTable(RichTable shadeRollTable) {
        this.shadeRollTable = shadeRollTable;
    }

    public RichTable getShadeRollTable() {
        return shadeRollTable;
    }

    public String createShadeLines() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();

        OperationBinding operationBinding2 =
            bindings.getOperationBinding("Commit");
        Object result2 = operationBinding2.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }


    public void setFabInsTable(RichTable fabInsTable) {
        this.fabInsTable = fabInsTable;
    }

    public RichTable getFabInsTable() {
        return fabInsTable;
    }

    public void FetchLisDefectCode(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
    }

    public void editDialogueLisDefectCode(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().name().equals("ok")) {

            OperationBinding operationBinding =
                executeOperation("popDefectCode");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(defectCodeTable);
            AdfFacesContext.getCurrentInstance().addPartialTarget(codeOfDefect);


        }
    }

    public void setDefectCodeTable(RichTable defectCodeTable) {
        this.defectCodeTable = defectCodeTable;
    }

    public RichTable getDefectCodeTable() {
        return defectCodeTable;
    }

    public void FetchListenerOunce(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        OperationBinding operationBinding =
            executeOperation("setFabInsRollWhrCls");
        operationBinding.getParamsMap().put("type", "FBOUNC");
        operationBinding.execute();
    }

    public void EditDialogListenerOunce(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {

            OperationBinding operationBinding =
                executeOperation("popuOunceRoll");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(ounceDetTable);


        }
    }

    public void setOunceDetTable(RichTable ounceDetTable) {
        this.ounceDetTable = ounceDetTable;
    }

    public RichTable getOunceDetTable() {
        return ounceDetTable;
    }

    /*********
     * Fabric Ounce Calculations
     * *************************************************/

    public int getOunceDetRolLines() {

        RowSetIterator it = FebricOunceDetailView1.createRowSetIterator("ss");
        int count = it.getRowCount();
        it.closeRowSetIterator();
        
        System.out.println("count ......."+count);
        
        return count;
    }


    public void createFabOuncD(ActionEvent actionEvent) {
        OperationBinding operationBinding = executeOperation("CreateInsert5");
        operationBinding.execute();


        linsValues("SET", "NoumberRollChecked", getOunceDetRolLines());
    }

    public void fabOuncDelD(ActionEvent actionEvent) {
        // Add event code here..
        OperationBinding operationBinding = executeOperation("Delete5");
        operationBinding.execute();


        linsValues("SET", "NoumberRollChecked", getOunceDetRolLines());

    }


    public double linsValues(String type, String column, double value) {

        oracle.adf.view.rich.component.UIXTable table = getOunceLineTable();
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
            try {
                if (type.equalsIgnoreCase("SET")) {
                    selectedRow.setAttribute(column, value);
                } else if (type.equalsIgnoreCase("GET")) {
                    val = MyMath.numeric3(selectedRow.getAttribute(column));
                }
            } catch (Exception e) {
                ;
            }
        }

        return val;

    }

    public double getOunceDetSum(String coulmn, double currentVal) {

        RowSetIterator it = FebricOunceDetailView1.createRowSetIterator("tt");
        Row currentRow    = FebricOunceDetailView1.getCurrentRow();

        double total = 0.0, a = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            if (r == currentRow) {
                total = total + currentVal;
                continue;
            } //BwOunceDetail
            a = MyMath.numeric3(r.getAttribute(coulmn));
            total = total + a;
        }

        it.closeRowSetIterator();
        return total;


    }


    /******************************************************************/

    public void setOunceLineTable(RichTable ounceLineTable) {
        this.ounceLineTable = ounceLineTable;
    }

    public RichTable getOunceLineTable() {
        return ounceLineTable;
    }


    /*************************************************************************/
    public void bwOwncAction(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double val = MyMath.numeric3(valueChangeEvent.getNewValue());
        double t = getOunceDetSum("BwOunceDetail", val);
        linsValues("SET", "BwOunce", (t / getOunceDetRolLines()));
    }

    public void awOwncAction(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double val = MyMath.numeric3(valueChangeEvent.getNewValue());
        double reqFabOunc = linsValues("GET", "RequireFabricOunce", 0.0);
        double detVarianceValue = val - reqFabOunc;
        double detVariancePrcntVal =
            MyMath.round((detVarianceValue / reqFabOunc) * 100);

        setDetailsValues(detVarianceValue, detVariancePrcntVal);

        double t = getOunceDetSum("AwOunceDetail", val);
        t = t / getOunceDetRolLines();
        double linesVarinceVal = t - reqFabOunc;
        double lineVariancePrcntVal =
            MyMath.round((linesVarinceVal / reqFabOunc) * 100);

        linsValues("SET", "AwOunce", (t));
        linsValues("SET", "Variation", linesVarinceVal);
        linsValues("SET", "VariationPrcnt", lineVariancePrcntVal);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ounceLineTable);
    }

    public void setDetailsValues(double variance, double variancPrcnt) {

        detVariance.setValue(MyMath.toNumber(variance));
        detVariPrcnt.setValue(MyMath.toNumber(variancPrcnt));

        AdfFacesContext.getCurrentInstance().addPartialTarget(detVariance);
        AdfFacesContext.getCurrentInstance().addPartialTarget(detVariPrcnt);
    }

    public void setDetVariance(RichInputText detVariance) {
        this.detVariance = detVariance;
    }

    public RichInputText getDetVariance() {
        return detVariance;
    }

    public void setDetVariPrcnt(RichInputText detVariPrcnt) {
        this.detVariPrcnt = detVariPrcnt;
    }

    public RichInputText getDetVariPrcnt() {
        return detVariPrcnt;
    }

    /*************************************************
     * Fabric inspection Tab calculation
     * ************************************************/

    public void pointSystemListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double pont1Val = MyMath.numeric(getPoint1());
        double pont1Va2 = MyMath.numeric(getPoint2());
        double pont1Va3 = MyMath.numeric(getPoint3());
        double pont1Va4 = MyMath.numeric(getPoint4());
        pont1Va2 = pont1Va2 * 2;
        pont1Va3 = pont1Va3 * 3;
        pont1Va4 = pont1Va4 * 4;
        System.out.println("pont1Va1 "+pont1Val);
        System.out.println("pont1Va2 "+pont1Va2);
        System.out.println("pont1Va3 "+pont1Va3);
        System.out.println("pont1Va4 "+pont1Va4);
        double totalPointsVal = pont1Val + pont1Va2 + pont1Va3 + pont1Va4;
        totalPoints.setValue(MyMath.toNumber(totalPointsVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalPoints);

        double pointSum = getTotPointsSum(totalPointsVal);
        FabInsDetTabValues("SET", "TotPenPoints", pointSum);

        double actLengthYrdVal = FabInsDetTabValues("GET", "ActualLength", 0);
        double actLengthInchVal = FabInsDetTabValues("GET", "ActualLengthInch", 0);
        double actWidthVal = FabInsDetTabValues("GET", "CutWidth", 0);
    
    
        double tempVar = actLengthInchVal/36;;
//        if (actLengthInchVal >= 36 ){
//            
//            tempVar = (long)actLengthInchVal/36;
//            actLengthYrdVal = actLengthYrdVal + tempVar;
//            actLengthInchVal =  ((actLengthInchVal/36) - tempVar) * 36 ;   
//        }



        
        if ((actLengthYrdVal != 0) && (actWidthVal != 0)) {
            double totalpointsDet =
                (pointSum * 36 * 100 / ((actLengthYrdVal+tempVar) * actWidthVal));
            System.out.println("total --->" + totalpointsDet);
            FabInsDetTabValues("SET", "TotPoints", totalpointsDet);
        }


    }

    /**********/
    public double getTotPointsSum2() {


        RowSetIterator it =
            MnjMfgFabinsSecndD2View1.createRowSetIterator("aa");
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            total = total + MyMath.numeric3(r.getAttribute("TotalPoint"));
        }
        it.closeRowSetIterator();
        return total;
    }

    public double getTotPointsSum(double currentVal) {


        RowSetIterator it =
            MnjMfgFabinsSecndD2View1.createRowSetIterator("tt");
        Row currentRow = MnjMfgFabinsSecndD2View1.getCurrentRow();
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
        AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsDetTable);
        return val;
    } //end if while loop
    
    
    public void DefectCodePointsSum(String ColumnType,String Value) {

        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("MnjMfgFabinsSecndD2View1");//ImpSaleContractDetailEOView1
        
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double  
        val1 = 0.0, 
                      total1 = 0.0,
        val2 = 0.0, 
                      total2 = 0.0,
        val3 = 0.0, 
                      total3 = 0.0,
        val4 = 0.0, 
                      total4 = 0.0;
        while (it.hasNext())
        {

                Row r = it.next();
                try 
                {
                    val1 = Double.parseDouble(r.getAttribute("Point1").toString());
                }catch (Exception e )
                {
                    val1 = 0.0;
                 }
            
            try {
                val2 = Double.parseDouble(r.getAttribute("Point2").toString());

            }catch (Exception e )
                {
                    val2 = 0.0;
                 }
            
            try {
                val3 = Double.parseDouble(r.getAttribute("Point3").toString());

            } catch (Exception e )
                {
                    val3 = 0.0;
                 }
            
            try {
                val4 = Double.parseDouble(r.getAttribute("Point4").toString());

            } catch (Exception e )
                {
                    val4 = 0.0;
                 }
                total1 = total1 + val1 ;
                total2 = total2 + val2 ;
                total3 = total3 + val3 ;
                total4 = total4 + val4 ;
        } //end of while loop
        it.closeRowSetIterator();

//        double pont1Val = MyMath.numeric(getPoint1());
//        double pont1Va2 = MyMath.numeric(getPoint2());
//        double pont1Va3 = MyMath.numeric(getPoint3());
//        double pont1Va4 = MyMath.numeric(getPoint4());
        
        double pont1Val = total1;
        double pont1Va2 = total2;
        double pont1Va3 = total3;
        double pont1Va4 = total4;
        pont1Va2 = pont1Va2 * 2;
        pont1Va3 = pont1Va3 * 3;
        pont1Va4 = pont1Va4 * 4;
        System.out.println("pont1Va1 "+pont1Val);
        System.out.println("pont1Va2 "+pont1Va2);
        System.out.println("pont1Va3 "+pont1Va3);
        System.out.println("pont1Va4 "+pont1Va4);
     
        double totalPointsVal = pont1Val + pont1Va2 + pont1Va3 + pont1Va4;
     
        FabInsDetTabValues("SET", "TotPenPoints", totalPointsVal);

   //     double actLengthYrdVal  = FabInsDetTabValues("GET", "ActualLength", 0);
   //     double actLengthInchVal = FabInsDetTabValues("GET", "ActualLengthInch", 0);
   //     double actWidthVal      = FabInsDetTabValues("GET", "CutWidth", 0);
     
 //  System.out.println("Sabih 1 ..."+Integer.parseInt(valueChangeEvent.getNewValue().toString()));
   

 oracle.adf.view.rich.component.UIXTable table = getFabInsDetTable();
 java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
 double actLengthYrdVal = 0.0,actLengthInchVal = 0.0,actWidthVal = 0.0;
 while (selectionIt.hasNext()) 
 {
     Object rowKey = selectionIt.next();
     table.setRowKey(rowKey);
     int index = table.getRowIndex();
     FacesCtrlHierNodeBinding row =
         (FacesCtrlHierNodeBinding)table.getRowData(index);
     Row selectedRow = row.getRow();

     if (ColumnType=="LengthYards")
     {
         actLengthYrdVal  = Double.parseDouble(Value);
      }
     else
     {
         actLengthYrdVal  = Double.parseDouble( selectedRow.getAttribute("ActualLength").toString());
     }
     
     if (ColumnType=="LengthInch")
     {
        actLengthInchVal  = Double.parseDouble(Value);
      }
     else
     {
         actLengthInchVal = Double.parseDouble(selectedRow.getAttribute("ActualLengthInch").toString());
     }
     
     if (ColumnType=="CutWidth")
     {
          actWidthVal  = Double.parseDouble(Value);
      }
     else
     {
         actWidthVal   = Double.parseDouble(selectedRow.getAttribute("CutWidth").toString());
     }
     
    

 }
 
//        double actLengthYrdVal  = Double.parseDouble(getActualLengthYardsInspection().toString());
//        double actLengthInchVal = Double.parseDouble(getActualLengthInchInspection().toString());
//        double actWidthVal      = Double.parseDouble(getCutableWidth().toString());
//        
        System.out.println("actLengthYrdVal    "+actLengthYrdVal);
        System.out.println("actLengthInchVal   "+actLengthInchVal);
        System.out.println("actWidthVal        "+actWidthVal);
        
        double tempVar = actLengthInchVal/36;;
        //        if (actLengthInchVal >= 36 ){
        //
        //            tempVar = (long)actLengthInchVal/36;
        //            actLengthYrdVal = actLengthYrdVal + tempVar;
        //            actLengthInchVal =  ((actLengthInchVal/36) - tempVar) * 36 ;
        //        }



        
        if ((actLengthYrdVal != 0) && (actWidthVal != 0)) {
            double totalpointsDet =
                (totalPointsVal * 36 * 100 / ((actLengthYrdVal+tempVar) * actWidthVal));
            System.out.println("total --->" + totalpointsDet);
            FabInsDetTabValues("SET", "TotPoints", totalpointsDet);
            
        AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsDetTable);
        }
    } //end if while loop

    public void setFabricInsFirstTableValues() {


        //        System.out.println("Calculation called-----------------------.>>>???"+ProdPageLinesVO1.getCurrentRow().getAttribute("BuyerId").toString());
        oracle.adf.view.rich.component.UIXTable table = getFabInsFirstTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Object val = null;

        /**********************************************************************/
        RowSetIterator it = MnjMfgFabinsSecndDView1.createRowSetIterator("ss");

        // totalRolRcvd.setValue(MyMath.toNumber(it.getFetchedRowCount()));
        //AdfFacesContext.getCurrentInstance().addPartialTarget(totalRolRcvd);
        int totalRolRcvVal = it.getFetchedRowCount();
        double totRcvQtyVal = 0.0;
        while (it.hasNext()) {

            Row r = it.next();
            try {
                totRcvQtyVal =
                        totRcvQtyVal + MyMath.numeric3(r.getAttribute("ActualLength"));

            } catch (Exception e) {
                totRcvQtyVal = 0.0;  
            }
          
        }
        // totRcvQty.setValue(MyMath.toNumber(totRcvQtyVal));
        //  AdfFacesContext.getCurrentInstance().addPartialTarget(totRcvQty);
        it.closeRowSetIterator();

        RowSetIterator it2 =
            MnjMfgFabinsSecndDView1.createRowSetIterator("qq");

        int noOfRolchkdVal = it2.getFetchedRowCount();

        // noOfRolChkd.setValue(MyMath.toNumber(noOfRolchkdVal));


        double nochkdQtyVal = 0.0, avgCutWidthVal = 0.0, granSumPenPointsval =
            0.0, nochkdQtyInch=0.0;
        while (it2.hasNext()) {

            Row r = it2.next();
            
            try {
                nochkdQtyVal =
                        nochkdQtyVal + MyMath.numeric3(r.getAttribute("ActualLength"));
                 
            } catch (Exception e) {
                // TODO: Add catch code
                nochkdQtyVal = 0.0;
            }
            try {
                nochkdQtyInch =
                        nochkdQtyInch + MyMath.numeric3(r.getAttribute("ActualLengthInch"));
                  
            } catch (Exception e) {
                // TODO: Add catch code
                nochkdQtyInch = 0.0;
            }
            try {
                avgCutWidthVal =
                        avgCutWidthVal + MyMath.numeric3(r.getAttribute("CutWidth"));
                 
            } catch (Exception e) {
                // TODO: Add catch code
                avgCutWidthVal = 0.0;
            }
            try {
                granSumPenPointsval =
                        granSumPenPointsval + MyMath.numeric3(r.getAttribute("TotPenPoints"));

            } catch (Exception e) {
                // TODO: Add catch code
                granSumPenPointsval = 0.0;
            }
            
           
        }

        it2.closeRowSetIterator();
        
        long tempVar = 0;
        if (nochkdQtyInch >= 36 ){
            
            tempVar = (long)nochkdQtyInch/36;
            nochkdQtyVal = nochkdQtyVal + tempVar;
            nochkdQtyInch =  ((nochkdQtyInch/36) - tempVar) * 36 ;   
        }
        
        

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
    (granSumPenPointsval * 36 * 100 ) / ((nochkdQtyVal+(nochkdQtyInch/36)) * avgCutWidthVal) ; 
   //(nochkdQtyVal * 36 * 100) / (totRcvQtyVal * MyMath.numeric3(noOfRolchkdVal));
        }
        //        grandTotPoints.setValue(MyMath.toNumber(grandTotPointsVal));
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(grandTotPoints);

        int buyerId = (int)setGetLineVal("GET", "BuyerId", 0.0);
        
//        System.out.println(setGetLineVal("GET", "BuyerId", 0.0)+"Buyer Id -->"+buyerId);
        OperationBinding operationBinding = executeOperation("getPassFailVal");
        operationBinding.getParamsMap().put("buyer",buyerId );
        Object result = operationBinding.execute();
        double qltyVal = MyMath.numeric3(result);
        String pasFailVal = null;
        
        System.out.println(qltyVal);
        System.out.println(grandTotPointsVal);
        System.out.println(granSumPenPointsval);

        if (grandTotPointsVal > qltyVal) {
            pasFailVal = "Fail";
        } else {
            pasFailVal = "Pass";
        }
        //        passFail.setValue(pasFailVal);
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(passFail);

        //        String insVal = MyMath.getStringVal(getInspection().getValue());


        /*********************************************************************************/
        
        System.out.println("TotalRolRcv  "+ totalRolRcvVal);
        System.out.println("TotalRcvQty  "+ totRcvQtyVal);
        System.out.println("NoOfRolChkd  "+ noOfRolchkdVal);
        System.out.println("NoChkdQty    "+ nochkdQtyVal);
        System.out.println("AvgCutWidth  "+ avgCutWidthVal);
        System.out.println("TotPnltyPnts "+ granSumPenPointsval);
        System.out.println("TotPoints    "+ grandTotPointsVal);
        System.out.println("PasFail      "+ pasFailVal);


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
            selectedRow.setAttribute("NoChkdQtyInch", nochkdQtyInch); // newly added
            selectedRow.setAttribute("AvgCutWidth", avgCutWidthVal);
            selectedRow.setAttribute("TotPnltyPnts", granSumPenPointsval);
            selectedRow.setAttribute("TotPoints", grandTotPointsVal);
            selectedRow.setAttribute("PasFail", pasFailVal);

            if ((pasFailVal.equalsIgnoreCase("Fail")) && (insVal != null) &&
                (insVal.equalsIgnoreCase("1st"))) {
                selectedRow.setAttribute("ActionReqFail",
                                         pasFailVal + " 2nd");

            } else if ((pasFailVal.equalsIgnoreCase("Fail")) &&
                       (insVal != null) &&
                       (insVal.equalsIgnoreCase("2nd"))) {
                selectedRow.setAttribute("ActionReqFail",
                                         pasFailVal + " 3rd");

            } else {
                selectedRow.setAttribute("ActionReqFail", "");

            }


        } //END OF LOOP

        AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsFirstTable);
    }

    public void fabInsDetListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double actLengthVal = MyMath.numeric(getActualLength());
        double actWidthVal = MyMath.numeric(getCutableWidth());
//        System.out.println("Actual length ----------->" + actLengthVal);
//        System.out.println("Cutable width ----------->" + actWidthVal);
        double pointSum = getTotPointsSum2();

        totalPanPoints.setValue(MyMath.toNumber(pointSum));
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalPanPoints);


        if ((actLengthVal != 0) && (actWidthVal != 0)) {
            double totalpointsDetVal =
                (pointSum * 36 * 100 / (actLengthVal * actWidthVal));
//            System.out.println("total --->" + totalpointsDetVal);
            totalDetPoints.setValue(MyMath.toNumber(totalpointsDetVal));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalDetPoints);
        }
        
        DefectCodePointsSum("CutWidth",valueChangeEvent.getNewValue().toString());

    }


    /************************************************************************************/
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

    public void setFabInsDetTable(RichTable fabInsDetTable) {
        this.fabInsDetTable = fabInsDetTable;
    }

    public RichTable getFabInsDetTable() {
        return fabInsDetTable;
    }


    /***************************End Fabric inspection Tab**************************************/
    public void setFabInsFirstTable(RichTable fabInsFirstTable) {
        this.fabInsFirstTable = fabInsFirstTable;
    }

    public RichTable getFabInsFirstTable() {
        return fabInsFirstTable;
    }

    public void setCutableWidth(RichInputText cutableWidth) {
        this.cutableWidth = cutableWidth;
    }

    public RichInputText getCutableWidth() {
        return cutableWidth;
    }

    public void setActualLength(RichInputText actualLength) {
        this.actualLength = actualLength;
    }

    public RichInputText getActualLength() {
        return actualLength;
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

    public String calcFabInsp() {
        // Add event code here...
        setFabricInsFirstTableValues();
        return null;
    }


    public double getTotalFabShrinkTabValueWise(String FieldName, String name,
                                                String type,
                                                double currentVal) {


        RowSetIterator it = FabricShrinkLines1.createRowSetIterator("tt");
        Row currentRow = FabricShrinkLines1.getCurrentRow();
        String value = null;
        try {
            value = currentRow.getAttribute(FieldName).toString();
        } catch (Exception e) {
            ;
        }
        String value2 = null;
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();

            if (type.equals("Y")) {

                if (r == currentRow) {
                    total = total + currentVal;
                    continue;
                }
            }

            try {
                value2 = r.getAttribute(FieldName).toString();
            } catch (Exception e) {
                ;
            }
            if ((value != null && value2 != null) && (value.equals(value2)))
                total =
                        total + MyMath.numeric3(r.getAttribute(name)); //RollLength
        }
        it.closeRowSetIterator();
        return total;
    }
    
    public double setGetFabShrinkValues(String type, String column,
                                     double value) {

        oracle.adf.view.rich.component.UIXTable table = getShrinkProdTable();
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
                
            } else if (type.equalsIgnoreCase("GET")) {
                val = MyMath.numeric3(selectedRow.getAttribute(column));

            }

        } //END OF LOOP
        AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);
        return val;
    } //end if while loop
    

    public double getFabShrinkTabCuurentVal(String name) {
        
        Row currentRow = FabricShrinkLines1.getCurrentRow();

        double val = MyMath.numeric3(currentRow.getAttribute(name));

        return val;

    }


    public void alocListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        int flag = 0 ;
        
        System.out.println(" Delete Validation");
        
        Row currentRowCheck = FabricShrinkLines1.getCurrentRow();
        String RollCheck = currentRowCheck.getAttribute("RollNo").toString();
        
        System.out.println(" Delete RollCheck....... "+RollCheck);
        
        RowSetIterator itFabricShade =
            MnjMfgFabinsSecndDView1.createRowSetIterator("qq");

        String RollAssign;
        
        while (itFabricShade.hasNext()) 
        {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();
            
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollCheck In Loop....... "+RollCheck);
            System.out.println("MnjMfgFabinsSecndDView1 Delete RollAssign....... "+RollAssign);
            
            if (RollAssign.equals(RollCheck)) //RollAssign==RollCheck
            {
                System.out.println("MnjMfgFabinsSecndDView1 Delete In If of flag....... "+flag);
                flag = flag + 1;  
            }
            
        }

        itFabricShade.closeRowSetIterator();
        
        
        
        
        System.out.println(" Delete flag....... "+flag);
        
        if (flag > 0) 
        {
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't Change the Value as it is already Assigned");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            ResetUtils.reset(valueChangeEvent.getComponent());

        
        }
        else 
        {
            
            String id = valueChangeEvent.getComponent().getId();
            double totAlocYrds = 0.0;
            double totAlocInch = 0.0;

            if (id.equals("ALYRDSID")) {
                totAlocYrds =
                        getTotalFabShrinkTabValueWise("RollNo", "AlocYard", "Y",
                                                      MyMath.numeric3(valueChangeEvent.getNewValue()));
                totAlocInch =
                        getTotalFabShrinkTabValueWise("RollNo", "AlocInch", "N",
                                                      0.0);
            } else if (id.equals("ALYRDINCH")) {
                totAlocYrds =
                        getTotalFabShrinkTabValueWise("RollNo", "AlocYard", "N",
                                                      0.0);
                totAlocInch =
                        getTotalFabShrinkTabValueWise("RollNo", "AlocInch", "Y",
                                                      MyMath.numeric3(valueChangeEvent.getNewValue()));
            }
            
            double actualYrds = getFabShrinkTabCuurentVal("RollLength"); //get actual yards
            double actualInches = getFabShrinkTabCuurentVal("RollLength2"); //get actual inches
            
            
            
            //        System.out.println("Item Id ---" + id);
            //        System.out.println("Total Yards ---->" + totAlocYrds);
            //        System.out.println("Total Aloc inches--->" + totAlocInch);
            
            if(actualYrds  < totAlocYrds ){
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Can't allocate more than actual length");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
                
                // Reset inputFile component after upload
                ResetUtils.reset(valueChangeEvent.getComponent());
            }
            else 
            {
                setGetFabShrinkValues("SET", "BlncYards", (actualYrds -totAlocYrds));    
            }
            if(actualInches < totAlocInch && totAlocYrds >= actualYrds)
            {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage("Can't allocate more than actual length");
               message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
                
                // Reset inputFile component after upload
                ResetUtils.reset(valueChangeEvent.getComponent());
                
            }
            else {
                setGetFabShrinkValues("SET", "BlncInch", (actualInches -totAlocInch));
            }
            
        }
        
        
        
       
        
        
    }

    public String saveAll() {
        // Add event code here...
        
       /* am.getDBTransaction().commit();
                   System.out.println("Commit sucessfully......");
                   ViewObject vo=am.getProdPageLinesVO1();
                   vo.executeQuery();*/
        
        
        linsValues("SET", "NoumberRollChecked", getOunceDetRolLines());
        
        System.out.println("Sabih Method .......");
        
        ////   AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);
        
        ////   System.out.println("Sabih Method After Partial Trigger.......");
        
        setLinesBlnc("N", 0);
        
        OperationBinding operationBinding = executeOperation("Commit");

        Object result = operationBinding.execute();
        
        OperationBinding operationBindingNew = executeOperation("SmartCalculation");
        operationBindingNew.execute();
        
       return null;
    }
    
    public void editPopupFetchFabIns(PopupFetchEvent popupFetchEvent) {

        OperationBinding operationBinding =
            executeOperation("setFabInsRollWhrCls");
        operationBinding.getParamsMap().put("type", "FBIN");
      //  System.out.println("Here 1");
        operationBinding.execute();
    }
    
    public void editDialogFabInsp(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {


            OperationBinding operationBinding =
                executeOperation("popuFabInsRoll");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(fabInsDetTable);
             AdfFacesContext.getCurrentInstance().addPartialTarget(fabricInspRollTable);


        }

    }

    public void setFabricShadeTable(RichTable fabricShadeTable) {
        this.fabricShadeTable = fabricShadeTable;
    }

    public RichTable getFabricShadeTable() {
        return fabricShadeTable;
    }

    public void setFabricInspRollTable(RichTable fabricInspRollTable) {
        this.fabricInspRollTable = fabricInspRollTable;
    }

    public RichTable getFabricInspRollTable() {
        return fabricInspRollTable;
    }

    public void setRollWidthFabBind(RichInputText rollWidthFabBind) {
        this.rollWidthFabBind = rollWidthFabBind;
    }

    public RichInputText getRollWidthFabBind() {
        return rollWidthFabBind;
    }

    public void setCutWidthFabBind(RichInputText cutWidthFabBind) {
        this.cutWidthFabBind = cutWidthFabBind;
    }

    public RichInputText getCutWidthFabBind() {
        return cutWidthFabBind;
    }

    public void CutWidthListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double rollWidth = MyMath.numeric(getRollWidthFabBind());
        double CutWidth = MyMath.numeric3(valueChangeEvent.getNewValue());
        if(CutWidth > rollWidth){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't allocate cut width more than actual width");
           message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            
            // Reset inputFile component after upload
            ResetUtils.reset(valueChangeEvent.getComponent());
            
        }
        
        
    }

    public void setAllocatedYardsLine(RichInputText allocatedYardsLine) {
        this.allocatedYardsLine = allocatedYardsLine;
    }

    public RichInputText getAllocatedYardsLine() {
        return allocatedYardsLine;
    }

    public void setAllocatedInchesLine(RichInputText allocatedInchesLine) {
        this.allocatedInchesLine = allocatedInchesLine;
    }

    public RichInputText getAllocatedInchesLine() {
        return allocatedInchesLine;
    }


    public void RollWidthListenerFabShrink(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        double val = 0;
        double value = 0;
        String NewVal;
        
        oracle.jbo.domain.Number number = new oracle.jbo.domain.Number(0);
        
        RowSetIterator it = ProdPageLinesVO1.createRowSetIterator("tt");
        Row currentRow = ProdPageLinesVO1.getCurrentRow();

        try {
            value = Double.parseDouble(currentRow.getAttribute("DjCutWidth").toString());
        } catch (Exception e) {
            ;
        }

        it.closeRowSetIterator(); 
        
        System.out.println("Sabih 1 ..."+value);
        val = Double.parseDouble(valueChangeEvent.getNewValue().toString()) - value ;
        NewVal = String.valueOf(val);
        try 
        {
             number = 
                new oracle.jbo.domain.Number(NewVal);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        System.out.println(val);
        cutWidthFabBind.setValue(number);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cutWidthFabBind);    
            
    }

    public void setAdjustCutableWidth(RichInputText adjustCutableWidth) {
        this.adjustCutableWidth = adjustCutableWidth;
    }

    public RichInputText getAdjustCutableWidth() {
        return adjustCutableWidth;
    }
    
   /*
    public double getTotalRollsYardsLengthBal() {

        OperationBinding operationBinding =
            executeOperation("getTotalRollYardLengthBal");
        operationBinding.getParamsMap().put("type", type);
        operationBinding.getParamsMap().put("headerid", "none");
        operationBinding.getParamsMap().put("color", "none");


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;


        double value = MyMath.numeric3(methodReturnValue);

        return value;

    }

    */


    public void RollLengthListenerInchesFabInsp(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
       
        int val = 0 ;
        try 
        {
            val = Integer.parseInt(valueChangeEvent.getNewValue().toString()) ;   
        } catch (Exception e) {
            // TODO: Add catch code
            val = 0 ;
        }
        
        System.out.println("RollLengthListenerInchesFabInsp");
        System.out.println(val);
        
        if( val  > 35 )
        {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't Enter Value Greate than 35");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            
            // Reset inputFile component after upload
            ResetUtils.reset(valueChangeEvent.getComponent());
        }
        else
        {
                DefectCodePointsSum("LengthInch",valueChangeEvent.getNewValue().toString());
            }
        
    }
    
    public void getSessionLineValues() {

            System.out.println("Check Flag   1");
            
            RowSetIterator it = ProdPageLinesVO1.createRowSetIterator("tt");
            Row currentRow    = ProdPageLinesVO1.getCurrentRow();
            
            String BuyerId,Color,Season,Style,HeaderNo;
            
            System.out.println("Check Flag   3");
            
                try {
                   //     BuyerId  = lineBuyerIdNew.getValue().toString();
                        BuyerId = currentRow.getAttribute("BuyerId").toString();

                    } catch (Exception e) {
                    // TODO: Add catch code
                    BuyerId  = "0";
                    }
                try {
                    //    Color    = lineColor.getValue().toString();
                        Color = currentRow.getAttribute("Color").toString();

                    } catch (Exception e) {
                    // TODO: Add catch code
                    Color    = "0";
                    }
                try {
                    //    Season   = lineSeason.getValue().toString();  
                        Season = currentRow.getAttribute("Season").toString();

                    } catch (Exception e) {
                    // TODO: Add catch code
                        Season   = "0";
                    }
                try {
                     //   Style    = lineStyle.getValue().toString();
                        Style = currentRow.getAttribute("Style").toString();

                    } catch (Exception e) {
                    // TODO: Add catch code
                    Style    = "0";
                    }

            it.closeRowSetIterator(); 
            
                try {
                        HeaderNo = headerId.getValue().toString(); 

                    } catch (Exception e) {
                    // TODO: Add catch code
                    HeaderNo = "0";
                    }
            
            
               FacesContext fctx = FacesContext.getCurrentInstance();
               ExternalContext ectx = fctx.getExternalContext();
               HttpSession userSession = (HttpSession)ectx.getSession(false);
            
               userSession.setAttribute("BuyerIdSession", BuyerId);
               userSession.setAttribute("colorSession", Color);
               userSession.setAttribute("SeasonSession", Season);
               userSession.setAttribute("StyleSession", Style);
               userSession.setAttribute("HeaderNoSession", HeaderNo);
            
                System.out.println("BuyerIdSession   "+BuyerId);
                System.out.println("colorSession     "+Color);
                System.out.println("SeasonSession    "+Season);
                System.out.println("StyleSession     "+Style);
                System.out.println("HeaderNoSession  "+HeaderNo);
           
           

        }



    public void setHeaderId(RichInputText headerId) {
        this.headerId = headerId;
        getSessionLineValues();
    }

    public RichInputText getHeaderId() {
        return headerId;
    }

    public void setLineBuyerId(RichColumn lineBuyerId) {
        this.lineBuyerId = lineBuyerId;
    }

    public RichColumn getLineBuyerId() {
        return lineBuyerId;
    }

    public void setLineStyle(RichInputComboboxListOfValues lineStyle) {
        this.lineStyle = lineStyle;
    }

    public RichInputComboboxListOfValues getLineStyle() {
        return lineStyle;
    }

    public void setLineSeason(RichInputText lineSeason) {
        this.lineSeason = lineSeason;
    }

    public RichInputText getLineSeason() {
        return lineSeason;
    }

    public void setLineColor(RichInputComboboxListOfValues lineColor) {
        this.lineColor = lineColor;
    }

    public RichInputComboboxListOfValues getLineColor() {
        return lineColor;
    }

    public void setLineBuyerIdNew(RichInputText lineBuyerIdNew) {
        this.lineBuyerIdNew = lineBuyerIdNew;
    }

    public RichInputText getLineBuyerIdNew() {
        return lineBuyerIdNew;
    }

    public void BwChangeListner(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        int flag = 0 ;
        
        RowSetIterator itFabricShade =
            MnjMfgFabinsPtrnalocDView1.createRowSetIterator("qq");

        String RollAssign;
        
        while (itFabricShade.hasNext()) 
        {

            Row r = itFabricShade.next();
            RollAssign = r.getAttribute("RollNo").toString();
            
            flag = flag + 1;  
            
        }

        itFabricShade.closeRowSetIterator();
        
        System.out.println(" Delete flag....... "+flag);
        
        if (flag > 0) 
        {
        
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Can't Change the Value ,Rolls Pattern Already Allocated.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(valueChangeEvent.getComponent().getClientId(context), message);
            ResetUtils.reset(valueChangeEvent.getComponent());

        
        }
        
    }

    public void ActualLengthListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        DefectCodePointsSum("LengthYards",valueChangeEvent.getNewValue().toString());
    }

    public void setActualLengthYardsInspection(RichInputText actualLengthYardsInspection) {
        this.actualLengthYardsInspection = actualLengthYardsInspection;
    }

    public RichInputText getActualLengthYardsInspection() {
        return actualLengthYardsInspection;
    }

    public void setActualLengthInchInspection(RichInputText actualLengthInchInspection) {
        this.actualLengthInchInspection = actualLengthInchInspection;
    }

    public RichInputText getActualLengthInchInspection() {
        return actualLengthInchInspection;
    }

    public void setShadeTable(RichTable shadeTable) {
        this.shadeTable = shadeTable;
    }

    public RichTable getShadeTable() {
        return shadeTable;
    }
    
    public void  setLinesValues(String column,
                                      double value) {

        oracle.adf.view.rich.component.UIXTable table = getShadeTable();
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
         
         selectedRow.setAttribute(column, value);
         AdfFacesContext.getCurrentInstance().addPartialTarget(shadeTable);
          

        } //END OF LOOP
     
    } //end if while loop
    
    public int gettotalRols(){
        
        RowSetIterator it = MnjMfgFabinsPtrnalocDView1.createRowSetIterator("tt");
        int totYrds = it.getRowCount();
        it.closeRowSetIterator();    
        
        return totYrds;
    }
    
    
    ///// Balance Pop - Up
    
    public void editPopupFetchListenerBalance(PopupFetchEvent popupFetchEvent) {
        // Add event code here...setPopUpWhereClause
        
        OperationBinding operationBinding = executeOperation("setPopUpWhereClauseBalance");                
        operationBinding.execute();
    }


    public void editDialogListenerBalance(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().name().equals("ok")) 
        {
           ;
        }
        
    }


    public String DelShadeNew() {
        setLinesValues("TotalRolls", gettotalRols());
        return null;
    }

    public void TransferRollPopUp(DialogEvent dialogEvent) {
        // Add event code here...
        
        if (dialogEvent.getOutcome().name().equals("ok")) {     
      
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getStylePopUpBinding().show(hints);
      
     //       AdfFacesContext.getCurrentInstance().addPartialTarget(piTable);    
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {          
         ;
        }
        
    }

    public void setStylePopUpBinding(RichPopup stylePopUpBinding) {
        this.stylePopUpBinding = stylePopUpBinding;
    }

    public RichPopup getStylePopUpBinding() {
        return stylePopUpBinding;
    }

    public void HeaderSelectionPopUpDialog(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().name().equals("ok")) {     
        
            OperationBinding operationBinding = executeOperation("RollTransfertoHeader");
            operationBinding.execute();
        
         AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {          
         ;
        }    
        
        
    }
    
    
    public ApplicationModule getAppM(){
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl am = (AppModuleImpl)this.getAppM();

    public void uploadShrinkage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
          try {
              clearSizeBreakDownTable();
              parseFile2(file.getInputStream());
              am.getDBTransaction().commit();
              AdfFacesContext.getCurrentInstance().addPartialTarget(shrinkProdTable);
          } catch (IOException e) {
          // TODO
          }
    }
    
    public void clearSizeBreakDownTable(){
              
          am.getDBTransaction().commit();
          
          ViewObject vo =  am.getFabricShrinkLines1();
          RowSetIterator it = vo.createRowSetIterator(null);
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
          int lineNumber = 0, tokenNumber = -1;
          Row rw = null;
           
          ViewObject vo =  am.getFabricShrinkLines1();
          
          //read comma separated file line by line
          try
          {
              while ((strLine = reader.readLine()) != null)
              {
                  lineNumber++;
                  // create a new row skip the header (header has linenumber 1)
                  if (lineNumber>2) {
                      rw = vo.createRow();
                      rw.setNewRowState(Row.STATUS_INITIALIZED);
                      vo.insertRow(rw);
                  }
                   
                  //break comma separated line using ","
                  st = new StringTokenizer(strLine, ",");
                      
                  double sizeProjQty=0, sizeActualQty=0,addDeductQty;
                      
                  while (st.hasMoreTokens())
                  {
                      //display csv values
                      tokenNumber++;
                       
                      String theToken = st.nextToken();
    //                      System.out.println("Line # " + lineNumber + ", Token # " +
    //                      tokenNumber +
    //                      ", Token : " + theToken);
                      
                      if (lineNumber>2){
                          // set Attribute Values
                          switch (tokenNumber) {
                              case 1: rw.setAttribute("RollNo", theToken);
                                      break;
                              case 2: rw.setAttribute("SuppRollNo", theToken);
                                      break;
                              case 3: rw.setAttribute("RollLength", theToken);
                                      break;
                              case 4: rw.setAttribute("RollLength2", theToken);
                                      break;
                              case 5: rw.setAttribute("RollWidth", theToken);
                                      break;
                              case 6: rw.setAttribute("AwLength", theToken);
                                      break;
                              case 7: rw.setAttribute("ShrinkLength", theToken);
                                      break;
                              case 8: rw.setAttribute("ShrinkPrcLength", theToken);
                                      break;
                              case 9: rw.setAttribute("AwWidth", theToken);
                                      break;
                              case 10: rw.setAttribute("ShrinkWidth", theToken);
                                      break;
                              case 11: rw.setAttribute("ShrinkPrcWidth", theToken);
                                      break;
                              case 12: rw.setAttribute("CutWidth", theToken);
                                      break;
                              case 13: rw.setAttribute("AlocYard", theToken);
                                      break;
                              case 14: rw.setAttribute("AlocInch", theToken);
                                      break;
                              case 15: rw.setAttribute("BlncYards", theToken);
                                      break;
                              case 16: rw.setAttribute("BlncInch", theToken);
                                      break;
                              case 17: rw.setAttribute("Remarks", theToken);
                                      break;
                                     
                          }
                      }
                  }
                  //reset token number
                  tokenNumber = -1;
              }
          }
          catch (IOException e) {
          // TODO add more
              FacesContext fctx = FacesContext.getCurrentInstance();
              fctx.addMessage(shrinkProdTable.getClientId(fctx), new FacesMessage(FacesMessage.SEVERITY_ERROR,
              "Content Error in Uploaded file", e.getMessage()));
          }
          catch (Exception e) {
          FacesContext fctx = FacesContext.getCurrentInstance();
          fctx.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
          "Data Error in Uploaded file", e.getMessage()));
          }
          
      }
      
    //-----------------------------------------------fabric shade upload------------------------------------------------------------------  
    public void exportToCSV2(FacesContext facesContext,
                               OutputStream outputStream) throws IOException {
           // Add event code here...
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
           
           writer.write("Shade");writer.write(",");
           writer.write("Total Rolls");writer.write(",");
           writer.write("Remarks");writer.write(",");

           writer.newLine();
           writer.flush();
           writer.close();
    }

      public void ShadeRollUpload(ValueChangeEvent valueChangeEvent) {
          // Add event code here...
          UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
          try {
              clearShadeTable();
              parseFile(file.getInputStream());
              AdfFacesContext.getCurrentInstance().addPartialTarget(shadeTable);
              am.getDBTransaction().commit();
          } catch (IOException e) {
          // TODO
          }
      }
      
            
            public void clearShadeTable(){
                
                am.getDBTransaction().commit();
                
                ViewObject vo =  am.getFabricShadeLine1();
                RowSetIterator it = vo.createRowSetIterator("aa");
                while(it.hasNext()){
                    it.next().remove();
                }
                vo.executeEmptyRowSet();
               it.closeRowSetIterator();
                
            }
            
            public void parseFile(java.io.InputStream file) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(file));
                String strLine = "";
                StringTokenizer st = null;
                int lineNumber = 0, tokenNumber = 0;
                Row rw = null;
                 
                ViewObject vo =  am.getFabricShadeLine1();
                
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
                                    case 0: rw.setAttribute("Shade", theToken);
                                            break;
                                    case 1: rw.setAttribute("TotalRolls", theToken);
                                            break;
                                    case 2: rw.setAttribute("Remarks", theToken);
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
                    fctx.addMessage(shadeTable.getClientId(fctx), new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Content Error in Uploaded file", e.getMessage()));
                }
                catch (Exception e) {
                FacesContext fctx = FacesContext.getCurrentInstance();
                fctx.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Data Error in Uploaded file", e.getMessage()));
                }
            }

    public void skewMovementRolls(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        ViewObject vo = am.getRollForSkewMovementVO1();
        ViewObject skewVO=am.getMnjSkewMovementVO1();
        ViewObject ProdPageLineVo=am.getProdPageLinesVO1();
        ViewObject vo2 = am.getMnjSkewMovementDVO1();
        vo2.executeQuery();
        vo.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(skewRollTable);
    }

    public void skewMovementRollsDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            ViewObject rollForSkewVO = am.getRollForSkewMovementVO1(); 
            //ViewObject vo2 = am.getMnjSkewMovementVO1();
            populateRollsForSkewMovement();
            am.getDBTransaction().commit();
            rollForSkewVO.executeQuery();
            AdfFacesContext.getCurrentInstance().addPartialTarget(skewMovementTable);
            AdfFacesContext.getCurrentInstance().addPartialTarget(skewRollTable);
           // vo2.executeQuery();
            //setLinesValues("TotalRolls", gettotalRols());
            
            

        }

        // Add event code here...
    }
    
    
    public void populateRollsForSkewMovement() {

        
        ViewObject populatevo = am.getRollForSkewMovementVO1();
       // populatevo.executeQuery();

        Row[] r = populatevo.getAllRowsInRange();
        System.out.println("Drop ------->" + r.length);
        for (Row row : r) {

            if (row.getAttribute("SkewRollFlag") != null &&
                row.getAttribute("SkewRollFlag").equals("Y")) {

              populateLinesInSkewMovement(row);
            }
        }

        OperationBinding operationBinding = executeOperation("Commit");

        Object result2 = operationBinding.execute();
      //  am.getDBTransaction().commit();
    }
    public void populateLinesInSkewMovement(Row poprow) {


        Row linerow = createSkewMovementLine();

        linerow.setAttribute("RollNo", getPopulateValue(poprow, "RollNo"));
        


    }
    
    public Row createSkewMovementLine() {

        ViewObject vo = am.getMnjSkewMovementDVO1();
        Row row = vo.createRow();
        vo.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        return row;
    } //end of createHeader
    
    public String getPopulateValue(Row r, String columnName) {

        String value = null;
        try {
            value = r.getAttribute(columnName).toString();
        } catch (Exception e) {
            ;
        }
        return value;
    }

    public void setSkewMovementTable(RichTable skewMovementTable) {
        this.skewMovementTable = skewMovementTable;
    }

    public RichTable getSkewMovementTable() {
        return skewMovementTable;
    }

    public void setSkewRollTable(RichTable skewRollTable) {
        this.skewRollTable = skewRollTable;
    }

    public RichTable getSkewRollTable() {
        return skewRollTable;
    }

    public void DoGeneralSkewCalculation(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("enter common listener ac");
        ViewObject VO=am.getMnjSkewMovementDVO1();
        Row r = VO.getCurrentRow();
     //  double currentVal = Double.parseDouble(valueChangeEvent.getNewValue().toString());
               double ac = 0.0, bd = 0.0,acMINUSbd=0.0,acPLUSbd=0.0,result=0.0;
                
        try {
            ac = Double.parseDouble(getSkew_AC().getValue().toString());

        } catch (Exception e) {

            ;
        }
     
                      // ac=currentVal;
                       System.out.println("AC value--- >" + ac);
                       try {
                           bd = Double.parseDouble(getSkew_BD().getValue().toString());

                       } catch (Exception e) {

                           ;
                       }
                       System.out.println("BD value ---->" + ac);              
                       acMINUSbd=ac-bd;     
                       System.out.println("acMINUSbd value ---->" + acMINUSbd);
                       acPLUSbd = ac + bd;    
                       System.out.println("acPLUSbd value ---->" + acPLUSbd);    
                                  if(acPLUSbd!=0){
                                      result=(2*(acMINUSbd/acPLUSbd))*100;
                                  }
                                  
                       System.out.println(" result ---->" + result);          
                       
                       r.setAttribute("AcMinusBd",Double.parseDouble(format.format(acMINUSbd)));
                       r.setAttribute("AcAddBd",Double.parseDouble(format.format(acPLUSbd)));
                       r.setAttribute("Result",Double.parseDouble(format.format(result)));
                       
                     
        am.getDBTransaction().commit(); 
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_result); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_ACplusBD); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_ACminusBD); 
       // AdfFacesContext.getCurrentInstance().addPartialTarget(skewMovementTable); 
     // AdfFacesContext.getCurrentInstance().addPartialTarget(skewMovementPanel);
       // VO.executeQuery();
      // am.getDBTransaction().commit(); 
    }

    public void setSkew_AC(RichInputText skew_AC) {
        this.skew_AC = skew_AC;
    }

    public RichInputText getSkew_AC() {
        return skew_AC;
    }

    public void setSkew_BD(RichInputText skew_BD) {
        this.skew_BD = skew_BD;
    }

    public RichInputText getSkew_BD() {
        return skew_BD;
    }

    public void setSkew_ACminusBD(RichInputText skew_ACminusBD) {
        this.skew_ACminusBD = skew_ACminusBD;
    }

    public RichInputText getSkew_ACminusBD() {
        return skew_ACminusBD;
    }

    public void setSkew_ACplusBD(RichInputText skew_ACplusBD) {
        this.skew_ACplusBD = skew_ACplusBD;
    }

    public RichInputText getSkew_ACplusBD() {
        return skew_ACplusBD;
    }

    public void setSkew_result(RichInputText skew_result) {
        this.skew_result = skew_result;
    }

    public RichInputText getSkew_result() {
        return skew_result;
    }


    public void skewValueChangeEventForBD(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        ViewObject VO= am.getMnjSkewMovementDVO1();
        // Add event code here...
        System.out.println("enter common listener bd");
        Row r =VO.getCurrentRow();
        //double currentVal = Double.parseDouble(valueChangeEvent.getNewValue().toString());
               double ac = 0.0, bd = 0.0,acMINUSbd=0.0,acPLUSbd=0.0,result=0.0;
                
             
             
        try {
            bd = Double.parseDouble(getSkew_BD().getValue().toString());

        } catch (Exception e) {

            ;
        }
                  
                  
                       //bd=currentVal;
                       System.out.println("BD value--- >" + bd);
                    
                       try {
                           ac = Double.parseDouble(getSkew_AC().getValue().toString());
                          

                       } catch (Exception e) {

                           ;
                       }
                       System.out.println("AC value ---->" + ac);             
                       acMINUSbd=ac-bd;     
                       System.out.println("acMINUSbd value ---->" + acMINUSbd);
                       acPLUSbd = ac + bd;    
                       System.out.println("acPLUSbd value ---->" + acPLUSbd);    
                                  if(acPLUSbd!=0){
                                      result=(2*(acMINUSbd/acPLUSbd))*100;
                                  }
                                  
                       System.out.println(" result ---->" + result);          
                       
                       r.setAttribute("AcMinusBd",Double.parseDouble(format.format(acMINUSbd)));
                       r.setAttribute("AcAddBd",Double.parseDouble(format.format(acPLUSbd)));
                       r.setAttribute("Result",Double.parseDouble(format.format(result)));
                       
       
        am.getDBTransaction().commit();   
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_result); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_ACplusBD); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(skew_ACminusBD); 
        
        //VO.executeQuery();
       // AdfFacesContext.getCurrentInstance().addPartialTarget(skewMovementTable);
        //am.getDBTransaction().commit();
    }

    public void selectAllForSkewRolls(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("enter select rolls number ------->");
        ViewObject populatevo = am.getRollForSkewMovementVO1();
        // populatevo.executeQuery();
            String Flag="Y";
        Row[] r = populatevo.getAllRowsInRange();
        System.out.println("total rolls number ------->" + r.length);
        for (Row row : r) {
           row.setAttribute("SkewRollFlag",Flag);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(skewRollTable);
        
    }

    public void deSelectAllForSkewRolls(ActionEvent actionEvent) {
        // Add event code here...
        
        System.out.println("enter deselect rolls number ------->");
        ViewObject populatevo = am.getRollForSkewMovementVO1();
        // populatevo.executeQuery();
            String Flag="N";
        Row[] r = populatevo.getAllRowsInRange();
        System.out.println("total rolls number ------->" + r.length);
        for (Row row : r) {
           row.setAttribute("SkewRollFlag", Flag);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(skewRollTable);
        
    }

    public void setSkewMovementPanel(RichPanelCollection skewMovementPanel) {
        this.skewMovementPanel = skewMovementPanel;
    }

    public RichPanelCollection getSkewMovementPanel() {
        return skewMovementPanel;
    }

    public String skewMovementCalculation() {
        // Add event code here...
        
        System.out.println("enter deselect rolls number ------->");
        ViewObject skew= am.getMnjSkewMovementVO1();
        ViewObject skewD = am.getMnjSkewMovementDVO1();
        // populatevo.executeQuery();
        double ac=0.0,bd=0.0,acAvg = 0.0, bdAvg = 0.0,acMINUSbd=0.0,acPLUSbd=0.0,result=0.0;
        Row[] r = skewD.getAllRowsInRange();
       int actotal=0,bdtotal=0;
        for (Row row : r) {
            try{
             ac = Double.parseDouble(row.getAttribute("Ac").toString());
                actotal=actotal+1;  
            }catch(Exception e){
              ac=0.0  ;
            }
            acAvg=acAvg+ac;
            try{
             bd = Double.parseDouble(row.getAttribute("Bd").toString());
                bdtotal=bdtotal+1;
            }catch(Exception e){
              bd=0.0  ;
            }
            bdAvg=bdAvg+bd;
            
        }
        
        Row row=skew.getCurrentRow();
        if(actotal!=0){
            row.setAttribute("Ac",Double.parseDouble(format.format(acAvg/actotal)));
        }
        if(bdtotal!=0){
            row.setAttribute("Bd",Double.parseDouble(format.format(bdAvg/bdtotal)));
        }
        
        if(actotal!=0 && bdtotal!=0){
            acMINUSbd=(acAvg/actotal)-(bdAvg/bdtotal);     
            System.out.println("acMINUSbd value ---->" + acMINUSbd);
            acPLUSbd =(acAvg/actotal)+ (bdAvg/bdtotal);    
            System.out.println("acPLUSbd value ---->" + acPLUSbd);    
                       if(acPLUSbd!=0){
                           result=(2*(acMINUSbd/acPLUSbd))*100;
                       }
        }
        
        row.setAttribute("AcMinusBd",Double.parseDouble(format.format(acMINUSbd)));
        row.setAttribute("AcAddBd",Double.parseDouble(format.format(acPLUSbd)));
        row.setAttribute("Result",Double.parseDouble(format.format(result)));
        row.setAttribute("RollNo",r.length);
        am.getDBTransaction().commit();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(skewMovementCalculationTable);
        
        return null;
    }

    public void setSkewMovementCalculationTable(RichTable skewMovementCalculationTable) {
        this.skewMovementCalculationTable = skewMovementCalculationTable;
    }

    public RichTable getSkewMovementCalculationTable() {
        return skewMovementCalculationTable;
    }

    public void setCodeOfDefect(RichTable codeOfDefect) {
        this.codeOfDefect = codeOfDefect;
    }

    public RichTable getCodeOfDefect() {
        return codeOfDefect;
    }


    public void filterSkewRoll(ActionEvent actionEvent) {
        // Add event code here...
        fatchpopup();
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getSkewPopup().show(hints);
        System.out.println("show pup up");
        
    }

    public void setSkewPopup(RichPopup skewPopup) {
        this.skewPopup = skewPopup;
    }

    public RichPopup getSkewPopup() {
        return skewPopup;
    }

    private void fatchpopup() {
        
        ViewObject vo = am.getRollForSkewMovementVO1();
        ViewObject skewVO=am.getMnjSkewMovementVO1();
        ViewObject ProdPageLineVo=am.getProdPageLinesVO1();
        ViewObject vo2 = am.getMnjSkewMovementDVO1();
        vo2.executeQuery();
        vo.executeQuery();
        int ProdLId=Integer.valueOf(ProdPageLineVo.getCurrentRow().getAttribute("ProdLId").toString());
        int skewId=Integer.valueOf(skewVO.getCurrentRow().getAttribute("SkewLineId").toString());
        System.out.println("skew_id="+skewId);
        System.out.println("line_id="+ProdLId);
        
        // vo.setWhereClause("PROD_L_ID = '" +ProdLId+ "'");
        vo.setNamedWhereClauseParam("line",ProdLId);
        vo.setNamedWhereClauseParam("skew",skewId);
        
        //vo.setWhereClause("HEADER_ID = "+getHeaderId());
        vo.executeQuery();
    }

    public void deleteAll(ActionEvent actionEvent) {
       ViewObject vo = am.getMnjSkewMovementDVO1();
       Row[] row = vo.getAllRowsInRange();
       System.out.println("Total rows to be deleted: " + row.length);
       for (Row rowEach : row){
           rowEach.remove();
       }
       vo.executeQuery();
    }
} //end of class
