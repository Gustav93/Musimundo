package com.musimundo.feeds.beans;

public class ClassificationReport {
    private Long countTotal, countOk, countWarning, countError, countNotProcessed;
    private String importOrigin;

    public ClassificationReport() {
        this.importOrigin = "";
        this.countTotal = 0L;
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

    public String getImportOrigin() {
        return importOrigin;
    }

    public void setImportOrigin(String importOrigin) {
        this.importOrigin = importOrigin;
    }

    @Override
    public String toString() {
        return "Nombre del Archivo: " + importOrigin + ", Cantidad Total de Registros: " + countTotal + ", Procesados Correctamente: " + countOk + ", Procesados con Error: " + countError +", Procesados con Warning: " + countWarning + ", No Procesados: " + countNotProcessed;
    }
}
