package mnj.mfg.model.lovviews;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Feb 20 14:33:42 PKT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TransferRollHeaderVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        DocumentNo {
            public Object get(TransferRollHeaderVORowImpl obj) {
                return obj.getDocumentNo();
            }

            public void put(TransferRollHeaderVORowImpl obj, Object value) {
                obj.setDocumentNo((String)value);
            }
        }
        ,
        ReferenceNo {
            public Object get(TransferRollHeaderVORowImpl obj) {
                return obj.getReferenceNo();
            }

            public void put(TransferRollHeaderVORowImpl obj, Object value) {
                obj.setReferenceNo((String)value);
            }
        }
        ,
        HeaderId {
            public Object get(TransferRollHeaderVORowImpl obj) {
                return obj.getHeaderId();
            }

            public void put(TransferRollHeaderVORowImpl obj, Object value) {
                obj.setHeaderId((Number)value);
            }
        }
        ,
        TransferHeaderFlag {
            public Object get(TransferRollHeaderVORowImpl obj) {
                return obj.getTransferHeaderFlag();
            }

            public void put(TransferRollHeaderVORowImpl obj, Object value) {
                obj.setTransferHeaderFlag((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(TransferRollHeaderVORowImpl object);

        public abstract void put(TransferRollHeaderVORowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int DOCUMENTNO = AttributesEnum.DocumentNo.index();
    public static final int REFERENCENO = AttributesEnum.ReferenceNo.index();
    public static final int HEADERID = AttributesEnum.HeaderId.index();
    public static final int TRANSFERHEADERFLAG = AttributesEnum.TransferHeaderFlag.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TransferRollHeaderVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute DocumentNo.
     * @return the DocumentNo
     */
    public String getDocumentNo() {
        return (String) getAttributeInternal(DOCUMENTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DocumentNo.
     * @param value value to set the  DocumentNo
     */
    public void setDocumentNo(String value) {
        setAttributeInternal(DOCUMENTNO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ReferenceNo.
     * @return the ReferenceNo
     */
    public String getReferenceNo() {
        return (String) getAttributeInternal(REFERENCENO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ReferenceNo.
     * @param value value to set the  ReferenceNo
     */
    public void setReferenceNo(String value) {
        setAttributeInternal(REFERENCENO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute HeaderId.
     * @return the HeaderId
     */
    public Number getHeaderId() {
        return (Number) getAttributeInternal(HEADERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute HeaderId.
     * @param value value to set the  HeaderId
     */
    public void setHeaderId(Number value) {
        setAttributeInternal(HEADERID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TransferHeaderFlag.
     * @return the TransferHeaderFlag
     */
    public String getTransferHeaderFlag() {
        return (String) getAttributeInternal(TRANSFERHEADERFLAG);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TransferHeaderFlag.
     * @param value value to set the  TransferHeaderFlag
     */
    public void setTransferHeaderFlag(String value) {
        setAttributeInternal(TRANSFERHEADERFLAG, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
