package com.ecocycle.impact;

public class EnvironmentalImpact {

    private double estimatedEwasteDivertedKg;
    private double estimatedUsefulLifeExtensionYears;
    private String impactMessage;

    public EnvironmentalImpact() {
    }

    public double getEstimatedEwasteDivertedKg() {
        return estimatedEwasteDivertedKg;
    }

    public void setEstimatedEwasteDivertedKg(double estimatedEwasteDivertedKg) {
        this.estimatedEwasteDivertedKg = estimatedEwasteDivertedKg;
    }

    public double getEstimatedUsefulLifeExtensionYears() {
        return estimatedUsefulLifeExtensionYears;
    }

    public void setEstimatedUsefulLifeExtensionYears(
            double estimatedUsefulLifeExtensionYears) {
        this.estimatedUsefulLifeExtensionYears =
                estimatedUsefulLifeExtensionYears;
    }

    public String getImpactMessage() {
        return impactMessage;
    }

    public void setImpactMessage(String impactMessage) {
        this.impactMessage = impactMessage;
    }
}