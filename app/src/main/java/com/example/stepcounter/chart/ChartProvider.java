package com.example.stepcounter.chart;

public class ChartProvider {
    private String chartdate;
    private String chartbmi;

    public ChartProvider(String a, String b){
        chartdate = a;
        chartbmi = b;
    }

    public String getChartdate(){ return chartdate ;}
    public void setChartdate(String chartdate){this.chartdate=chartdate;}
    public String getChartbmi(){ return chartbmi ;}
    public void setChartbmi(String chartbmi){this.chartbmi=chartbmi;}
}
