package com.ecocycle.impact;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.Device;

@Service
public class EnvironmentalImpactCalculator {

    public EnvironmentalImpact calculate(
            Device device,
            String recommendation) {

        EnvironmentalImpact impact =
                new EnvironmentalImpact();

        // ----------------------------------------
        // Estimated device weight
        // ----------------------------------------

        double estimatedWeight =
                getEstimatedWeight(device);

        double ewasteDiverted = 0;
        double usefulLifeExtension = 0;


        // ----------------------------------------
        // Estimate environmental impact
        // based on recommendation
        // ----------------------------------------

        if (recommendation.equals("REPAIR")) {

            ewasteDiverted =
                    estimatedWeight;

            usefulLifeExtension =
                    2.0;

            impact.setImpactMessage(
                    "Repair may help keep the device in use and avoid premature disposal."
            );

        } else if (recommendation.equals("REUSE")) {

            ewasteDiverted =
                    estimatedWeight;

            usefulLifeExtension =
                    3.0;

            impact.setImpactMessage(
                    "Reuse may extend the useful life of the device and delay disposal."
            );

        } else if (recommendation.equals("DONATE")) {

            ewasteDiverted =
                    estimatedWeight;

            usefulLifeExtension =
                    3.0;

            impact.setImpactMessage(
                    "Donation may allow another person to use the device instead of replacing it."
            );

        } else if (recommendation.equals("SELL")) {

            ewasteDiverted =
                    estimatedWeight;

            usefulLifeExtension =
                    2.0;

            impact.setImpactMessage(
                    "Selling may allow the device to continue being used by another owner."
            );

        } else if (recommendation.equals("RECYCLE")) {

            ewasteDiverted =
                    estimatedWeight;

            usefulLifeExtension =
                    0.0;

            impact.setImpactMessage(
                    "Recycling can help divert electronic material from improper disposal."
            );
        }


        // ----------------------------------------
        // Store calculated values
        // ----------------------------------------

        impact.setEstimatedEwasteDivertedKg(
                ewasteDiverted
        );

        impact.setEstimatedUsefulLifeExtensionYears(
                usefulLifeExtension
        );


        return impact;
    }


    // ============================================
    // ESTIMATED DEVICE WEIGHT
    // ============================================

    private double getEstimatedWeight(
            Device device) {

        if (
                device.getCategory() != null
                &&
                device.getCategory().getName() != null
        ) {

            String category =
                    device.getCategory()
                            .getName()
                            .toLowerCase();


            // ------------------------------------
            // Laptop
            // ------------------------------------

            if (category.contains("laptop")) {
                return 1.8;
            }


            // ------------------------------------
            // Mobile Phone
            // ------------------------------------

            if (
                    category.contains("mobile")
                    ||
                    category.contains("phone")
            ) {
                return 0.2;
            }


            // ------------------------------------
            // Tablet
            // ------------------------------------

            if (category.contains("tablet")) {
                return 0.5;
            }


            // ------------------------------------
            // Desktop Computer
            // ------------------------------------

            if (
                    category.contains("desktop")
                    ||
                    category.contains("computer")
            ) {
                return 8.0;
            }


            // ------------------------------------
            // Monitor
            // ------------------------------------

            if (category.contains("monitor")) {
                return 5.0;
            }


            // ------------------------------------
            // Television
            // ------------------------------------

            if (
                    category.contains("television")
                    ||
                    category.contains("tv")
            ) {
                return 10.0;
            }


            // ------------------------------------
            // Printer
            // ------------------------------------

            if (category.contains("printer")) {
                return 6.0;
            }


            // ------------------------------------
            // Camera
            // ------------------------------------

            if (category.contains("camera")) {
                return 0.6;
            }


            // ------------------------------------
            // Gaming Console
            // ------------------------------------

            if (
                    category.contains("gaming")
                    ||
                    category.contains("console")
            ) {
                return 3.5;
            }


            // ------------------------------------
            // Smartwatch
            // ------------------------------------

            if (
                    category.contains("smartwatch")
                    ||
                    category.contains("watch")
            ) {
                return 0.1;
            }


            // ------------------------------------
            // Other Electronics
            // ------------------------------------

            if (category.contains("other")) {
                return 2.0;
            }
        }


        // ========================================
        // DEFAULT ESTIMATE
        // ========================================

        return 2.0;
    }
}