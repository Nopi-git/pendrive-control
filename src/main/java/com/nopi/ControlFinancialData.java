package com.nopi;

import java.math.BigDecimal;

public class ControlFinancialData {

    private BigDecimal income;
    private BigDecimal outcome;
    private BigDecimal chitanta;

    public ControlFinancialData() {
    }

    public BigDecimal getIncome() {
        return income;
    }

    public ControlFinancialData setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    public BigDecimal getOutcome() {
        return outcome;
    }

    public ControlFinancialData setOutcome(BigDecimal outcome) {
        this.outcome = outcome;
        return this;
    }

    public BigDecimal getChitanta() {
        return chitanta;
    }

    public ControlFinancialData setChitanta(BigDecimal chitanta) {
        this.chitanta = chitanta;
        return this;
    }
}
