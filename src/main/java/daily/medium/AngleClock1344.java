package daily.medium;

public class AngleClock1344 {
    public static void main(String[] args) {
        System.out.println(angleClock(12, 30));
    }

    public static double angleClock(int hour, int minutes) {
        double minuteAngle = 6.0 * minutes;
        double hourAngle = 30.0 * (hour % 12) + 0.5 * minutes;
        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360.0 - diff);
    }
}


/*
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
Answers within 10^-5 of the actual value will be accepted as correct.
Example 1:
Input: hour = 12, minutes = 30
Output: 165
Example 2:
Input: hour = 3, minutes = 30
Output: 75
Example 3:
Input: hour = 3, minutes = 15
Output: 7.5

Constraints:
1 <= hour <= 12
0 <= minutes <= 59
 */


/*
To cover 360 degree, minute hand takes 60 minutes => 360/60 = 6 degree is covered per minute by minute hand.
To cover 360 degree, hour hand takes 360/12 = 30 degree, but note that this is not a clean motion, of 1 spoke to the
other unlike the minute hand. The hour hand makes a constant slow motion every minute too to get to the other hour
spoke at the end of 1 hour, so you need to account for that movement too.
In the time it takes the minute hand to go all the way around, the hour hand moves 30 degree.
This means for every single minute that passes, the hour hand creeps forward by a tiny fraction 30/60 = 0.5 per minute.
that is in 30 degree there are 60 minutes covered, so use that.

Instead, you take the difference. The minute hand has covered $180^\circ$ of space. The hour hand has already
"eaten up" the first $105^\circ$ of that space. The angle between them is simply the remaining gap that the hour
hand hasn't caught up to yet:$$180^\circ - 105^\circ = 75^\circ$$.
Subtraction is used because they move in the same direction. You would only add the angles if the minute hand moved
clockwise and the hour hand moved counter-clockwise away from 12 o'clock! Because they share a direction, subtraction naturally cancels out the overlapping distance they both traveled from the 12 o'clock anchor.
 */