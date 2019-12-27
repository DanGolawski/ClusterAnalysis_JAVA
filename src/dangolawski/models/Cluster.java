package dangolawski.models;

public class Cluster {

    // TODO sprawdzic czy preffered foot, atacking, defence moga zostac intami

    private int clusterNumber;

    private float sumOfSquaredDeviations;

    private float overall_ratingMean;

    private float potentialMean;

    private float preferred_footMean;

    private float attacking_work_rateMean;

    private float defensive_work_rateMean;

    private float crossingMean;

    private float finishingMean;

    private float heading_accuracyMean;

    private float short_passingMean;

    private float volleysMean;

    private float dribblingMean;

    private float curveMean;

    private float free_kick_accuracyMean;

    private float long_passingMean;

    private float ball_controlMean;

    private float accelerationMean;

    private float sprint_speedMean;

    private float agilityMean;

    private float reactionsMean;

    private float balanceMean;

    private float shot_powerMean;

    private float jumpingMean;

    private float staminaMean;

    private float strengthMean;

    private float long_shotsMean;

    private float aggressionMean;

    private float interceptionsMean;

    private float positioningMean;

    private float visionMean;

    private float penaltiesMean;

    private float markingMean;

    private float standing_tackleMean;

    private float sliding_tackleMean;

    private float gk_divingMean;

    private float gk_handlingMean;

    private float gk_kickingMean;

    private float gk_positioningMean;

    private float gk_reflexesMean;

    private float heightMean;

    private float weightMean;

    private float ageMean;

    // CONSTRUCTORS //

    public Cluster(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    // SETTERS //

    public void setoverall_ratingMean(Float overall_rating) {
        this.overall_ratingMean = overall_rating;
    }

    public void setSumOfSquaredDeviations(float sumOfSquaredDeviations){ this.sumOfSquaredDeviations = sumOfSquaredDeviations; }

    public void setpotentialMean(Float potential) {
        this.potentialMean = potential;
    }

    public void setpreferred_footMean(Float preferred_foot) {
        this.preferred_footMean = preferred_foot;
    }

    public void setattacking_work_rateMean(Float attacking_work_rate) {
        this.attacking_work_rateMean = attacking_work_rate;
    }

    public void setdefensive_work_rateMean(Float defensive_work_rate) {
        this.defensive_work_rateMean = defensive_work_rate;
    }

    public void setcrossingMean(Float crossing) {
        this.crossingMean = crossing;
    }

    public void setfinishingMean(Float finishing) {
        this.finishingMean = finishing;
    }

    public void setheading_accuracyMean(Float heading_accuracy) {
        this.heading_accuracyMean = heading_accuracy;
    }

    public void setshort_passingMean(Float short_passing) {
        this.short_passingMean = short_passing;
    }

    public void setvolleysMean(Float volleys) {
        this.volleysMean = volleys;
    }

    public void setdribblingMean(Float dribbling) {
        this.dribblingMean = dribbling;
    }

    public void setcurveMean(Float curve) {
        this.curveMean = curve;
    }

    public void setfree_kick_accuracyMean(Float free_kick_accuracy) {
        this.free_kick_accuracyMean = free_kick_accuracy;
    }

    public void setlong_passingMean(Float long_passing) {
        this.long_passingMean = long_passing;
    }

    public void setball_controlMean(Float ball_control) {
        this.ball_controlMean = ball_control;
    }

    public void setaccelerationMean(Float acceleration) {
        this.accelerationMean = acceleration;
    }

    public void setsprint_speedMean(Float sprint_speed) {
        this.sprint_speedMean = sprint_speed;
    }

    public void setagilityMean(Float agility) {
        this.agilityMean = agility;
    }

    public void setreactionsMean(Float reactions) {
        this.reactionsMean = reactions;
    }

    public void setbalanceMean(Float balance) {
        this.balanceMean = balance;
    }

    public void setshot_powerMean(Float shot_power) {
        this.shot_powerMean = shot_power;
    }

    public void setjumpingMean(Float jumping) {
        this.jumpingMean = jumping;
    }

    public void setstaminaMean(Float stamina) {
        this.staminaMean = stamina;
    }

    public void setstrengthMean(Float strength) {
        this.strengthMean = strength;
    }

    public void setlong_shotsMean(Float long_shots) {
        this.long_shotsMean = long_shots;
    }

    public void setaggressionMean(Float aggression) {
        this.aggressionMean = aggression;
    }

    public void setinterceptionsMean(Float interceptions) {
        this.interceptionsMean = interceptions;
    }

    public void setpositioningMean(Float positioning) {
        this.positioningMean = positioning;
    }

    public void setvisionMean(Float vision) {
        this.visionMean = vision;
    }

    public void setpenaltiesMean(Float penalties) {
        this.penaltiesMean = penalties;
    }

    public void setmarkingMean(Float marking) {
        this.markingMean = marking;
    }

    public void setstanding_tackleMean(Float standing_tackle) {
        this.standing_tackleMean = standing_tackle;
    }

    public void setsliding_tackleMean(Float sliding_tackle) {
        this.sliding_tackleMean = sliding_tackle;
    }

    public void setgk_divingMean(Float gk_diving) {
        this.gk_divingMean = gk_diving;
    }

    public void setgk_handlingMean(Float gk_handling) {
        this.gk_handlingMean = gk_handling;
    }

    public void setgk_kickingMean(Float gk_kicking) {
        this.gk_kickingMean = gk_kicking;
    }

    public void setgk_positioningMean(Float gk_positioning) {
        this.gk_positioningMean = gk_positioning;
    }

    public void setgk_reflexesMean(Float gk_reflexes) {
        this.gk_reflexesMean = gk_reflexes;
    }

    public void setheightMean(Float height) {
        this.heightMean = height;
    }

    public void setweightMean(Float weight) {
        this.weightMean = weight;
    }

    public void setageMean(Float age) {
        this.ageMean = age;
    }

    // GETTERS //


    public int getClusterNumber() {
        return clusterNumber;
    }

    public float getSumOfSquaredDeviations() { return sumOfSquaredDeviations; }

    public float getoverall_ratingMean() {
        return overall_ratingMean;
    }

    public float getpotentialMean() {
        return potentialMean;
    }

    public float getpreferred_footMean() {
        return preferred_footMean;
    }

    public float getattacking_work_rateMean() {
        return attacking_work_rateMean;
    }

    public float getdefensive_work_rateMean() {
        return defensive_work_rateMean;
    }

    public float getcrossingMean() {
        return crossingMean;
    }

    public float getfinishingMean() {
        return finishingMean;
    }

    public float getheading_accuracyMean() {
        return heading_accuracyMean;
    }

    public float getshort_passingMean() {
        return short_passingMean;
    }

    public float getvolleysMean() {
        return volleysMean;
    }

    public float getdribblingMean() {
        return dribblingMean;
    }

    public float getcurveMean() {
        return curveMean;
    }

    public float getfree_kick_accuracyMean() {
        return free_kick_accuracyMean;
    }

    public float getlong_passingMean() {
        return long_passingMean;
    }

    public float getball_controlMean() {
        return ball_controlMean;
    }

    public float getaccelerationMean() {
        return accelerationMean;
    }

    public float getsprint_speedMean() {
        return sprint_speedMean;
    }

    public float getagilityMean() {
        return agilityMean;
    }

    public float getreactionsMean() {
        return reactionsMean;
    }

    public float getbalanceMean() {
        return balanceMean;
    }

    public float getshot_powerMean() {
        return shot_powerMean;
    }

    public float getjumpingMean() {
        return jumpingMean;
    }

    public float getstaminaMean() {
        return staminaMean;
    }

    public float getstrengthMean() {
        return strengthMean;
    }

    public float getlong_shotsMean() {
        return long_shotsMean;
    }

    public float getaggressionMean() {
        return aggressionMean;
    }

    public float getinterceptionsMean() {
        return interceptionsMean;
    }

    public float getpositioningMean() {
        return positioningMean;
    }

    public float getvisionMean() {
        return visionMean;
    }

    public float getpenaltiesMean() {
        return penaltiesMean;
    }

    public float getmarkingMean() {
        return markingMean;
    }

    public float getstanding_tackleMean() {
        return standing_tackleMean;
    }

    public float getsliding_tackleMean() {
        return sliding_tackleMean;
    }

    public float getgk_divingMean() {
        return gk_divingMean;
    }

    public float getgk_handlingMean() {
        return gk_handlingMean;
    }

    public float getgk_kickingMean() {
        return gk_kickingMean;
    }

    public float getgk_positioningMean() {
        return gk_positioningMean;
    }

    public float getgk_reflexesMean() {
        return gk_reflexesMean;
    }

    public float getheightMean() {
        return heightMean;
    }

    public float getweightMean() {
        return weightMean;
    }

    public float getageMean() {
        return ageMean;
    }
}
