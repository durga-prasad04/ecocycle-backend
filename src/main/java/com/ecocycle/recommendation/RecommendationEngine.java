package com.ecocycle.recommendation;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.Device;
import com.ecocycle.impact.EnvironmentalImpact;
import com.ecocycle.impact.EnvironmentalImpactCalculator;

@Service
public class RecommendationEngine {

    private final EnvironmentalImpactCalculator environmentalImpactCalculator;

    public RecommendationEngine(
            EnvironmentalImpactCalculator environmentalImpactCalculator) {

        this.environmentalImpactCalculator = environmentalImpactCalculator;
    }

    public RecommendationResult evaluate(Device device) {

        RecommendationResult result = new RecommendationResult();

        double repairScore = 0;
        double reuseScore = 0;
        double donateScore = 0;
        double sellScore = 0;
        double recycleScore = 0;

        // Rule 1: Repair cost compared with current value
        if (device.getRepairCost() != null && device.getCurrentValue() != null
                && device.getCurrentValue() > 0) {

            double repairRatio =
                    (device.getRepairCost() / device.getCurrentValue()) * 100;

            if (repairRatio <= 20) {
                repairScore += 40;

            } else if (repairRatio <= 40) {
                repairScore += 25;

            } else if (repairRatio <= 60) {
                repairScore += 10;
            }
        }

        // Rule 2: Device condition
        if (device.getCondition() != null) {

            String condition = device.getCondition().toLowerCase();

            if (condition.equals("excellent")) {

                repairScore += 30;
                reuseScore += 25;
                sellScore += 20;

            } else if (condition.equals("good")) {

                repairScore += 25;
                reuseScore += 20;
                sellScore += 15;

            } else if (condition.equals("fair")) {

                repairScore += 15;
                reuseScore += 10;
                donateScore += 10;

            } else if (condition.equals("poor")) {

                repairScore += 5;
                recycleScore += 20;
            }
        }

        // Rule 3: Device age
        if (device.getAgeYears() != null) {

            int age = device.getAgeYears();

            if (age <= 2) {

                repairScore += 20;
                reuseScore += 15;
                sellScore += 10;

            } else if (age <= 5) {

                repairScore += 15;
                reuseScore += 10;
                sellScore += 8;

            } else if (age <= 8) {

                repairScore += 5;
                reuseScore += 5;
                recycleScore += 10;

            } else {

                recycleScore += 25;
            }
        }

        // Rule 4: Expected remaining life
        if (device.getExpectedRemainingLife() != null) {

            int remainingLife = device.getExpectedRemainingLife();

            if (remainingLife >= 3) {

                repairScore += 20;
                reuseScore += 20;
                donateScore += 10;
                sellScore += 10;

            } else if (remainingLife >= 1) {

                repairScore += 10;
                reuseScore += 10;
                sellScore += 5;

            } else {

                recycleScore += 20;
            }
        }

        // Rule 5: Current value compared with purchase price
        if (device.getPurchasePrice() != null
                && device.getCurrentValue() != null
                && device.getPurchasePrice() > 0) {

            double valueRetention =
                    (device.getCurrentValue() / device.getPurchasePrice()) * 100;

            if (valueRetention >= 70) {

                sellScore += 20;
                repairScore += 15;
                reuseScore += 15;

            } else if (valueRetention >= 40) {

                sellScore += 15;
                repairScore += 10;
                reuseScore += 10;

            } else if (valueRetention >= 20) {

                sellScore += 5;
                reuseScore += 5;

            } else {

                recycleScore += 10;
            }
        }

        // Find the highest score
        double highestScore = repairScore;
        String recommendation = "REPAIR";

        if (reuseScore > highestScore) {
            highestScore = reuseScore;
            recommendation = "REUSE";
        }

        if (donateScore > highestScore) {
            highestScore = donateScore;
            recommendation = "DONATE";
        }

        if (sellScore > highestScore) {
            highestScore = sellScore;
            recommendation = "SELL";
        }

        if (recycleScore > highestScore) {
            recommendation = "RECYCLE";
        }

        // Generate explanation
        String reason;

        if (recommendation.equals("REPAIR")) {

            reason = "Repair is recommended because the device has a suitable "
                    + "repair cost, condition, value, and remaining useful life.";

        } else if (recommendation.equals("REUSE")) {

            reason = "Reuse is recommended because the device is still in "
                    + "usable condition and has useful remaining life.";

        } else if (recommendation.equals("DONATE")) {

            reason = "Donation is recommended because the device may still "
                    + "be useful to another person.";

        } else if (recommendation.equals("SELL")) {

            reason = "Selling is recommended because the device still has "
                    + "reasonable market value and can be used by another owner.";

        } else {

            reason = "Recycling is recommended because the device has limited "
                    + "remaining useful life, low value, or poor condition.";
        }

        // Store scores
        result.setRepairScore(repairScore);
        result.setReuseScore(reuseScore);
        result.setDonateScore(donateScore);
        result.setSellScore(sellScore);
        result.setRecycleScore(recycleScore);

        // Store final recommendation
        result.setRecommendation(recommendation);

        // Store explanation
        result.setReason(reason);

        // Calculate environmental impact
        EnvironmentalImpact environmentalImpact =
                environmentalImpactCalculator.calculate(
                        device,
                        recommendation
                );

        // Store environmental impact
        result.setEnvironmentalImpact(environmentalImpact);

        return result;
    }
}