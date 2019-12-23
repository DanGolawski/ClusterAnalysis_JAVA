package dangolawski.models;

import dangolawski.services.Globals;

import java.util.Arrays;

public class Player {

    private int clusterNumber;

    private String playerName;

    private float overall_rating;

    private float potential;

    private int preferred_foot; // 0-left, 1-right

    private int attacking_work_rate; // 1-low, 2-medium, 3-high

    private int defensive_work_rate; // 1-low, 2-medium, 3-high

    private float crossing;

    private float finishing;

    private float heading_accuracy;

    private float short_passing;

    private float volleys;

    private float dribbling;

    private float curve;

    private float free_kick_accuracy;

    private float long_passing;

    private float ball_control;

    private float acceleration;

    private float sprint_speed;

    private float agility;

    private float reactions;

    private float balance;

    private float shot_power;

    private float jumping;

    private float stamina;

    private float strength;

    private float long_shots;

    private float aggression;

    private float interceptions;

    private float positioning;

    private float vision;

    private float penalties;

    private float marking;

    private float standing_tackle;

    private float sliding_tackle;

    private float gk_diving;

    private float gk_handling;

    private float gk_kicking;

    private float gk_positioning;

    private float gk_reflexes;

    private float height;

    private int weight;

    private int age;



    // SETTERS //

    public void setClusterNumber(Integer clusterNumber) {
        if(clusterNumber < 1 || clusterNumber > Globals.numberOfClusters){
            throw new IllegalArgumentException("invalid cluster number");
        }
        this.clusterNumber = clusterNumber;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setoverall_rating(String overallRating) {
        this.overall_rating = overallRating.equals("None") ? -1 : Float.parseFloat(overallRating);
    }

    public void setpotential(String potential) {
        this.potential = potential.equals("None") ? -1 : Float.parseFloat(potential);
    }

    public void setpreferred_foot(String preferred_foot) {
        if(!preferred_foot.equals("left") && !preferred_foot.equals("right") && !preferred_foot.equals("None"))
            throw new IllegalArgumentException("prefferedRightFoot should be 1 or 0");
        this.preferred_foot = preferred_foot.equals("None") ? -1 : Arrays.asList(Globals.footNominalValues).indexOf(preferred_foot);
    }

    public void setattacking_work_rate(String attacking_work_rate) {
        if(!Arrays.asList(Globals.nominalValues).contains(attacking_work_rate) && !attacking_work_rate.equals("None"))
            throw new IllegalArgumentException("attacking_work_rate should be between 1 and 3");
        this.attacking_work_rate = attacking_work_rate.equals("None") ? -1 : Arrays.asList(Globals.nominalValues).indexOf(attacking_work_rate)+1;
    }

    public void setdefensive_work_rate(String defensive_work_rate) {
        if(defensive_work_rate.length() == 1) {
            int value = Integer.parseInt(defensive_work_rate);
            if(value < 1 || value > 3) this.defensive_work_rate = -1;
            else this.defensive_work_rate = Integer.parseInt(defensive_work_rate);
        }
        else if(!Arrays.asList(Globals.nominalValues).contains(defensive_work_rate) && !defensive_work_rate.equals("None"))
            throw new IllegalArgumentException("defensive_work_rate should be between 1 and 3");
        else this.defensive_work_rate = defensive_work_rate.equals("None") ? -1 : Arrays.asList(Globals.nominalValues).indexOf(defensive_work_rate)+1;
    }

    public void setcrossing(String crossing) {
        this.crossing = crossing.equals("None") ? -1 : Float.parseFloat(crossing);
    }

    public void setfinishing(String finishing) {
        this.finishing = finishing.equals("None") ? -1 : Float.parseFloat(finishing);
    }

    public void setheading_accuracy(String heading_accuracy) {
        this.heading_accuracy = heading_accuracy.equals("None") ? -1 : Float.parseFloat(heading_accuracy);
    }

    public void setshort_passing(String short_passing) {
        this.short_passing = short_passing.equals("None") ? -1 : Float.parseFloat(short_passing);
    }

    public void setvolleys(String volleys) {
        this.volleys = volleys.equals("None") ? -1 : Float.parseFloat(volleys);
    }

    public void setdribbling(String dribbling) {
        this.dribbling = dribbling.equals("None") ? -1 : Float.parseFloat(dribbling);
    }

    public void setcurve(String curve) {
        this.curve = curve.equals("None") ? -1 : Float.parseFloat(curve);
    }

    public void setfree_kick_accuracy(String free_kick_accuracy) {
        this.free_kick_accuracy = free_kick_accuracy.equals("None") ? -1 : Float.parseFloat(free_kick_accuracy);
    }

    public void setlong_passing(String long_passing) {
        this.long_passing = long_passing.equals("None") ? -1 : Float.parseFloat(long_passing);
    }

    public void setball_control(String ball_control) {
        this.ball_control = ball_control.equals("None") ? -1 : Float.parseFloat(ball_control);
    }

    public void setacceleration(String acceleration) {
        this.acceleration = acceleration.equals("None") ? -1 : Float.parseFloat(acceleration);
    }

    public void setsprint_speed(String sprint_speed) {
        this.sprint_speed = sprint_speed.equals("None") ? -1 : Float.parseFloat(sprint_speed);
    }

    public void setagility(String agility) {
        this.agility = agility.equals("None") ? -1 : Float.parseFloat(agility);
    }

    public void setreactions(String reactions) {
        this.reactions = reactions.equals("None") ? -1 : Float.parseFloat(reactions);
    }

    public void setbalance(String balance) {
        this.balance = balance.equals("None") ? -1 : Float.parseFloat(balance);
    }

    public void setshot_power(String shot_power) {
        this.shot_power = shot_power.equals("None") ? -1 : Float.parseFloat(shot_power);
    }

    public void setjumping(String jumping) {
        this.jumping = jumping.equals("None") ? -1 : Float.parseFloat(jumping);
    }

    public void setstamina(String stamina) {
        this.stamina = stamina.equals("None") ? -1 : Float.parseFloat(stamina);
    }

    public void setstrength(String strength) {
        this.strength = strength.equals("None") ? -1 : Float.parseFloat(strength);
    }

    public void setlong_shots(String long_shots) {
        this.long_shots = long_shots.equals("None") ? -1 : Float.parseFloat(long_shots);
    }

    public void setaggression(String aggression) {
        this.aggression = aggression.equals("None") ? -1 : Float.parseFloat(aggression);
    }

    public void setinterceptions(String interception) {
        this.interceptions = interception.equals("None") ? -1 : Float.parseFloat(interception);
    }

    public void setpositioning(String positioning) {
        this.positioning = positioning.equals("None") ? -1 : Float.parseFloat(positioning);
    }

    public void setvision(String vision) {
        this.vision = vision.equals("None") ? -1 : Float.parseFloat(vision);
    }

    public void setpenalties(String penalties) {
        this.penalties = penalties.equals("None") ? -1 : Float.parseFloat(penalties);
    }

    public void setmarking(String marking) {
        this.marking = marking.equals("None") ? -1 : Float.parseFloat(marking);
    }

    public void setstanding_tackle(String standing_tackle) {
        this.standing_tackle = standing_tackle.equals("None") ? -1 : Float.parseFloat(standing_tackle);
    }

    public void setsliding_tackle(String sliding_tackle) {
        this.sliding_tackle = sliding_tackle.equals("None") ? -1 : Float.parseFloat(sliding_tackle);
    }

    public void setgk_diving(String gk_diving) {
        this.gk_diving = gk_diving.equals("None") ? -1 : Float.parseFloat(gk_diving);
    }

    public void setgk_handling(String gk_handling) {
        this.gk_handling = gk_handling.equals("None") ? -1 : Float.parseFloat(gk_handling);
    }

    public void setgk_kicking(String gk_kicking) {
        this.gk_kicking = gk_kicking.equals("None") ? -1 : Float.parseFloat(gk_kicking);
    }

    public void setgk_positioning(String gk_positioning) {
        this.gk_positioning = gk_positioning.equals("None") ? -1 : Float.parseFloat(gk_positioning);
    }

    public void setgk_reflexes(String gk_reflexes) {
        this.gk_reflexes = gk_reflexes.equals("None") ? -1 : Float.parseFloat(gk_reflexes);
    }

    public void setheight(String height) {
        this.height = height.equals("None") ? -1 : Float.parseFloat(height);
    }

    public void setweight(String weight) {
        this.weight = weight.equals("None") ? -1 : Integer.parseInt(weight);
    }

    public void setage(String age) {
        this.age = age.equals("None") ? -1 : Integer.parseInt(age);
    }




    // GETTERS //


    public int getClusterNumber() {
        return clusterNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public float getoverall_rating() {
        return overall_rating;
    }

    public float getpotential() {
        return potential;
    }

    public int getpreferred_foot() {
        return preferred_foot;
    }

    public int getattacking_work_rate() {
        return attacking_work_rate;
    }

    public int getdefensive_work_rate() {
        return defensive_work_rate;
    }

    public float getcrossing() {
        return crossing;
    }

    public float getfinishing() {
        return finishing;
    }

    public float getheading_accuracy() {
        return heading_accuracy;
    }

    public float getshort_passing() {
        return short_passing;
    }

    public float getvolleys() {
        return volleys;
    }

    public float getdribbling() {
        return dribbling;
    }

    public float getcurve() {
        return curve;
    }

    public float getfree_kick_accuracy() {
        return free_kick_accuracy;
    }

    public float getlong_passing() {
        return long_passing;
    }

    public float getball_control() {
        return ball_control;
    }

    public float getacceleration() {
        return acceleration;
    }

    public float getsprint_speed() {
        return sprint_speed;
    }

    public float getreactions() {
        return reactions;
    }

    public float getbalance() {
        return balance;
    }

    public float getshot_power() {
        return shot_power;
    }

    public float getjumping() {
        return jumping;
    }

    public float getstamina() {
        return stamina;
    }

    public float getstrength() {
        return strength;
    }

    public float getlong_shots() {
        return long_shots;
    }

    public float getaggression() {
        return aggression;
    }

    public float getInterceptions() {
        return interceptions;
    }

    public float getpositioning() {
        return positioning;
    }

    public float getvision() {
        return vision;
    }

    public float getpenalties() {
        return penalties;
    }

    public float getmarking() {
        return marking;
    }

    public float getstanding_tackle() {
        return standing_tackle;
    }

    public float getgk_diving() {
        return gk_diving;
    }

    public float getgk_handling() {
        return gk_handling;
    }

    public float getgk_kicking() {
        return gk_kicking;
    }

    public float getgk_positioning() {
        return gk_positioning;
    }

    public float getgk_reflexes() {
        return gk_reflexes;
    }

    public float getheight() {
        return height;
    }

    public int getweight() {
        return weight;
    }

    public int getage() {
        return age;
    }

    public float getagility() {
        return agility;
    }

    public float getsliding_tackle() {
        return sliding_tackle;
    }
}
