package mnj.mfg.view.backing_bean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

public class PatAlocBean {
    private RichTable detTable;
    private ViewObject detialVo;
    private RichTable lineTable;
    private RichTable patternAllocRollsTable;

    public PatAlocBean() {
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        detialVo = am.findViewObject("MnjMfgFabinsPtrnalocDView1");
    }

    public void PopupFetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        OperationBinding operationBinding =
            executeOperation("setPtrnAlocRollsWhere");
        operationBinding.execute();
    }

    public void PopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    public void DialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().name().equals("ok")) {


            OperationBinding operationBinding =
                executeOperation("populatePatternAloc");
            operationBinding.execute();
            AdfFacesContext.getCurrentInstance().addPartialTarget(detTable);
            
            OperationBinding operationBinding1 =
                executeOperation("Commit");
            operationBinding1.execute();

            setLinesValues("TotalRolls", gettotalRols());
            setLinesValues("TotalYrds", getDetValueSum());

        }
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

    public void setDetTable(RichTable detTable) {
        this.detTable = detTable;
    }

    public RichTable getDetTable() {
        return detTable;
    }
    
    public double getDetValueSum() {


        RowSetIterator it = detialVo.createRowSetIterator("aa");
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            total = total + MyMath.numeric3(r.getAttribute("RollLength"));
        }
        it.closeRowSetIterator();
        return total;
    }
    
    public int gettotalRols(){
        
        RowSetIterator it = detialVo.createRowSetIterator("tt");
        int totYrds = it.getRowCount();
        it.closeRowSetIterator();    
        
        return totYrds;
    }
    
    public void  setLinesValues(String column,
                                      double value) {

        oracle.adf.view.rich.component.UIXTable table = getLineTable();
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
         AdfFacesContext.getCurrentInstance().addPartialTarget(lineTable);
          

        } //END OF LOOP
     
    } //end if while loop


    public void setLineTable(RichTable lineTable) {
        this.lineTable = lineTable;
    }

    public RichTable getLineTable() {
        return lineTable;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String deleteDet() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete9");
        Object result = operationBinding.execute();
        
        setLinesValues("TotalRolls", gettotalRols());
        setLinesValues("TotalYrds", getDetValueSum());
        
        
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
    
    
    public void PatternAllocRollsSelectAll(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding operationBinding = executeOperation("SelectAllLinesPatAlloc");
        operationBinding.getParamsMap().put("flag", "Y");
        operationBinding.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(patternAllocRollsTable);
    }


    public void DeSelectPatternAllocRolls(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding operationBinding = executeOperation("SelectAllLinesPatAlloc");
        operationBinding.getParamsMap().put("flag", "N");
        operationBinding.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(patternAllocRollsTable);
    }

    public void setPatternAllocRollsTable(RichTable patternAllocRollsTable) {
        this.patternAllocRollsTable = patternAllocRollsTable;
    }

    public RichTable getPatternAllocRollsTable() {
        return patternAllocRollsTable;
    }
}
