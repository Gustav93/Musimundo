package com.musimundo.feeds.beans;

public class MerchandiseReport {
    private Long countTotal, countOk, countWarning, countError, countNotProcessed;

    public MerchandiseReport() {
        countTotal = 0L;
        this.countOk = 0L;
        this.countWarning = 0L;
        this.countError = 0L;
        this.countNotProcessed = 0L;
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }

    public Long getCountOk() {
        return countOk;
    }

    public void setCountOk(Long countOk) {
        this.countOk = countOk;
    }

    public Long getCountWarning() {
        return countWarning;
    }

    public void setCountWarning(Long countWarning) {
        this.countWarning = countWarning;
    }

    public Long getCountError() {
        return countError;
    }

    public void setCountError(Long countError) {
        this.countError = countError;
    }

    public Long getCountNotProcessed() {
        return countNotProcessed;
    }

    public void setCountNotProcessed(Long countNotProcessed) {
        this.countNotProcessed = countNotProcessed;
    }

}
