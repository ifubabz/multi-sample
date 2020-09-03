package com.openlabs.sample.model;

import java.io.Serializable;

/**
 * Td1111Key definition.
 * 
 * This file was generated by Ignite Web Console (08/10/2020, 15:05)
 **/
public class Td1111Key implements Serializable {
    /** */
    private static final long serialVersionUID = 0L;

    /** Value for ordDt. */
    private String ordDt;

    /** Value for acmtBrCod. */
    private String acmtBrCod;

    /** Value for ordNo. */
    private long ordNo;

    /** Empty constructor. **/
    public Td1111Key() {
        // No-op.
    }

    /** Full constructor. **/
    public Td1111Key(String ordDt,
        String acmtBrCod,
        long ordNo) {
        this.ordDt = ordDt;
        this.acmtBrCod = acmtBrCod;
        this.ordNo = ordNo;
    }

    /**
     * Gets ordDt
     * 
     * @return Value for ordDt.
     **/
    public String getOrdDt() {
        return ordDt;
    }

    /**
     * Sets ordDt
     * 
     * @param ordDt New value for ordDt.
     **/
    public void setOrdDt(String ordDt) {
        this.ordDt = ordDt;
    }

    /**
     * Gets acmtBrCod
     * 
     * @return Value for acmtBrCod.
     **/
    public String getAcmtBrCod() {
        return acmtBrCod;
    }

    /**
     * Sets acmtBrCod
     * 
     * @param acmtBrCod New value for acmtBrCod.
     **/
    public void setAcmtBrCod(String acmtBrCod) {
        this.acmtBrCod = acmtBrCod;
    }

    /**
     * Gets ordNo
     * 
     * @return Value for ordNo.
     **/
    public long getOrdNo() {
        return ordNo;
    }

    /**
     * Sets ordNo
     * 
     * @param ordNo New value for ordNo.
     **/
    public void setOrdNo(long ordNo) {
        this.ordNo = ordNo;
    }

    /** {@inheritDoc} **/
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (!(o instanceof Td1111Key))
            return false;
        
        Td1111Key that = (Td1111Key)o;

        if (ordDt != null ? !ordDt.equals(that.ordDt) : that.ordDt != null)
            return false;
        

        if (acmtBrCod != null ? !acmtBrCod.equals(that.acmtBrCod) : that.acmtBrCod != null)
            return false;
        

        if (ordNo != that.ordNo)
            return false;
        
        return true;
    }

    /** {@inheritDoc} **/
    @Override public int hashCode() {
        int res = ordDt != null ? ordDt.hashCode() : 0;

        res = 31 * res + (acmtBrCod != null ? acmtBrCod.hashCode() : 0);

        res = 31 * res + (int)(ordNo ^ (ordNo >>> 32));

        return res;
    }

    /** {@inheritDoc} **/
    @Override public String toString() {
        return "Td1111Key [" + 
            "ordDt=" + ordDt + ", " + 
            "acmtBrCod=" + acmtBrCod + ", " + 
            "ordNo=" + ordNo +
        "]";
    }
}